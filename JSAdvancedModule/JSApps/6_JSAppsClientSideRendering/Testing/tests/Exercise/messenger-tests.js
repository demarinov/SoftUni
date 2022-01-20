const chromium = require('playwright-chromium').chromium;
const expect = require('chai').expect;
let browser, page; // Declare reusable variables

let mockData = '{' +
    '"-LxHVtajG3N1sU714pVj":{' +
    '"author":"Spami",' +
    '"content":"Hello, are you there?"' +
    '},' +
    '"-LxIDxC-GotWtf4eHwV8":{' +
    '"author":"Garry",' +
    '"content":"Yep, whats up :?"' +
    '},' +
    '"-LxIDxPfhsNipDrOQ5g_":{' +
    '"author":"Spami",' +
    '"content":"How are you? Long time no see? :)"' +
    '},' +
    '"-LxIE-dM_msaz1O9MouM":{' +
    '"author":"George",' +
    '"content":"Hello, guys! :))"' +
    '},' +
    '"-LxLgX_nOIiuvbwmxt8w":{' +
    '"author":"Spami",' +
    '"content":"Hello, George nice to see you! :)))"' +
    '}' +
    '}';

function getJson(data) {
    return {
        status: 200,
        headers: {
            'Access-Control-Allow-Origin': '*',
            'Content-Type': 'application/json'
        },
        body: data
    }
}

// web-server should be started as well as the rest service
describe('E2E messenger tests', function () {
    before(async () => { browser = await chromium.launch({ headless: false, sloMo:1000 }); });
    after(async () => { await browser.close(); });
    beforeEach(async () => { page = await browser.newPage(); });
    afterEach(async () => { await page.close(); });

    it('1. Testing: load messages', async function () {
        await page.goto('http://127.0.0.1:5500/main/Exercise/01.Messenger/');

        await page.waitForSelector('#messages')

        const [response] = await Promise.all([
            page.waitForResponse(r => r.request().url()
                .includes('/jsonstore/messenger') && r.request().method() === 'GET'),
            page.click('#refresh')
        ])

        const responseData = await response.json()

        expect(JSON.stringify(responseData)).to.eq(mockData);

        // load msgs and check textarea

        const messagesSelector = "#messages";
        const messagesText = await page.inputValue(messagesSelector);

        const expectedOutput = "Spami: Hello, are you there?\n" +
            "Garry: Yep, whats up :?\n" +
            "Spami: How are you? Long time no see? :)\n" +
            "George: Hello, guys! :))\n" +
            "Spami: Hello, George nice to see you! :)))";

        expect(messagesText).to.be.equal(expectedOutput);

    });

    it('2. Testing: send message', async function () {
        await page.goto('http://127.0.0.1:5500/main/Exercise/01.Messenger/');

        await page.waitForSelector('#controls');
        // clicking send and right data 

        await page.route(
            '**/jsonstore/messenger*',
            request => request.fulfill(getJson({ author: 'Petko', content: 'Petko drives mad!' }))
        )

        await page.fill('#author','Petko');
        await page.fill('#content','Petko drives mad!');

        const [response] = await Promise.all([
            page.waitForRequest(r => r.url()
                .includes('/jsonstore/messenger') && r.method() === 'POST'),
            page.click('#submit')
        ])

        const responseData = JSON.parse(await response.postData());
        console.log(JSON.stringify(responseData));

        expect(responseData).to.deep.eq({ 'author': 'Petko', 'content': 'Petko drives mad!' });
    });


});
