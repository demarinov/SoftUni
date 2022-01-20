const chromium = require('playwright-chromium').chromium;
const expect = require('chai').expect;
let browser, page; // Declare reusable variables

let mockDataObj = {
    'd953e5fb-a585-4d6b-92d3-ee90697398a0':{
        author: 'J.K.Rowling', title: 'Harry Potter and the Philosopher\'s Stone'
    },
    'd953e5fb-a585-4d6b-92d3-ee90697398a1':{
        author: 'Svetlin Nakov', title: 'C# Fundamentals'
    }
}

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

    it('1. Testing: load books', async function () {
        await page.goto('http://127.0.0.1:5500/main/Exercise/02.Book-Library/');

        // books loaded
        const [response] = await Promise.all([
            page.waitForResponse(r => {

                return r.request().url().includes('collections/books')
                    && r.request().method() === 'GET';
            }),
            page.click('#loadBooks')
        ]);

        const responseData = await response.json();
        
        expect(responseData).to.deep.eq(mockDataObj);

        const firstBookNameSelector = 'table tbody tr:nth-child(1) td:nth-child(1)';
        const firstBookAuthorSelector = 'table tbody tr:nth-child(1) td:nth-child(2)';
        const secondBookNameSelector = 'table tbody tr:nth-child(2) td:nth-child(1)';
        const secondBookAuthorSelector = 'table tbody tr:nth-child(2) td:nth-child(2)';

        page.waitForSelector('table tbody tr');
        const firstBookName = await page.textContent(firstBookNameSelector);
        const firstBookAuthor = await page.textContent(firstBookAuthorSelector);
        const secondBookName = await page.textContent(secondBookNameSelector);
        const secondBookAuthor = await page.textContent(secondBookAuthorSelector);

        expect('Harry Potter and the Philosopher\'s Stone').to.be.eq(firstBookName);
        expect('J.K.Rowling').to.be.eq(firstBookAuthor);
        expect('C# Fundamentals').to.be.eq(secondBookName);
        expect('Svetlin Nakov').to.be.eq(secondBookAuthor);

    });

    it('2. Testing: add book', async function () {
        await page.goto('http://127.0.0.1:5500/main/Exercise/02.Book-Library/');

        const createFormSelector = '#createForm button';

        await page.route('**/collections/books',
        req => req.fulfill(getJson({title: 'The return of Petko', author:'Petko Petkov'})));

        await page.fill('#createForm input[name="title"]','The return of Petko');
        await page.fill('#createForm input[name="author"]','Petko Petkov');

        const [response] = await Promise.all([
            page.waitForRequest(r => {

                return r.url().includes('collections/books') 
                    && r.method() === 'POST';
            }),
            page.click(createFormSelector)
        ]);

        const postData = JSON.parse(await response.postData());

        expect({title: 'The return of Petko', author:'Petko Petkov'})
        .to.deep.eq(postData);

        // no empty fields allowed
        await page.fill('#createForm input[name="title"]','The return of Petko');
        await page.fill('#createForm input[name="author"]','');

        // include dialogs to prevent alerts during testing
        let emptyField = false;
        await page.once('dialog', async dialog => {
			await dialog.accept();
            emptyField = true;
		});

        const [responseEmpty] = await Promise.all([
            page.click(createFormSelector)
        ]);

        expect(emptyField).to.be.true;
    });

    it('3. Testing: edit book', async function () {
        await page.goto('http://127.0.0.1:5500/main/Exercise/02.Book-Library/');

        // load books first
        page.click('#loadBooks');

        await page.waitForSelector('table tbody tr');

        // click edit button
        const editBtnSelector = 'table tbody tr:nth-child(1) td:nth-child(3) .editBtn';
        await page.click(editBtnSelector);
        await page.waitForSelector('#editForm[style="display: block;"]');

        // check input fields

        expect('Harry Potter and the Philosopher\'s Stone').to.
        be.eq(await page.inputValue('#editForm input[name="title"]'));

        expect('J.K.Rowling').to.
        be.eq(await page.inputValue('#editForm input[name="author"]'));

        const editFormSelector = '#editForm button';

        // handle edit submit request/response
        await page.route('**/collections/books/*',
        req => req.fulfill(getJson({title: 'The return of Petko2', author:'Petko Petkov'})));
        
        await page.fill('#editForm input[name="title"]','The return of Petko2');
        await page.fill('#editForm input[name="author"]','Petko Petkov');

        // send put request
        const [response] = await Promise.all([
            page.waitForRequest(r => {

                return r.url().includes('collections/books') 
                    && r.method() === 'PUT';
            }),
            page.click(editFormSelector)
        ]);

        const putData = JSON.parse(await response.postData());

        expect({title: 'The return of Petko2', author:'Petko Petkov'}).to.deep.eq(putData);
    });

    it('4. Testing: delete book', async function () {
        await page.goto('http://127.0.0.1:5500/main/Exercise/02.Book-Library/');

        // load books first
        page.click('#loadBooks');

        await page.waitForSelector('table tbody tr');

        // accept confirmation and send delete request
        const deleteSelector = 'table tbody tr:nth-child(1) td:nth-child(3) .deleteBtn';
        
        await page.route('**/collections/books/*',
        req => req.fulfill(getJson({title: 'The return of Petko2', author:'Petko Petkov'})));

        let deleteBook = false;
        await page.once('dialog', async dialog => {
			await dialog.accept();
            deleteBook = true;
		}); 

        await Promise.all([
            page.waitForRequest(r => {

                return r.url().includes('collections/books') && r.method() === 'DELETE';
            }),
            page.click(deleteSelector)
        ]);

        expect(deleteBook).to.be.true;
    });

});
