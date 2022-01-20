const chromium = require('playwright-chromium').chromium;
const expect = require('chai').expect;
let browser, page; // Declare reusable variables

let baseUrl = 'http://127.0.0.1:5500/main/Lab/Cookbook/';

// web-server should be started as well as the rest service
describe('E2E cookbook3 refactoring tests', function () {
    before(async () => { browser = await chromium.launch({headless:false, slowMo: 1000}); });
    after(async () => { await browser.close(); });
    beforeEach(async () => { page = await browser.newPage(); });
    afterEach(async () => { await page.close(); });

    it('1. Loads catalog page', async function () {
        await page.goto(baseUrl+'index.html');
        // await page.screenshot({ path: `index.png` });

        // click catalog
        await page.click('#catalogLink');

        const selectorTitle1 = '#catalog .preview:nth-child(2) .title h2';
        const selectorTitle2 = '#catalog .preview:nth-child(3) .title h2';
        const selectorTitle3 = '#catalog .preview:nth-child(4) .title h2';

        const content1 = await page.textContent(selectorTitle1);
        const src1 = await page.getAttribute('#catalog .preview:nth-child(2) .small img', 'src');
        const content2 = await page.textContent(selectorTitle2);
        const src2 = await page.getAttribute('#catalog .preview:nth-child(3) .small img', 'src');
        const content3 = await page.textContent(selectorTitle3);
        const src3 = await page.getAttribute('#catalog .preview:nth-child(4) .small img', 'src');

        // console.log(content1);
        // console.log(content2);
        // console.log(content3);

        expect("Easy Lasagna").to.be.equal(content1);
        expect('assets/lasagna.jpg').to.be.equal(src1);
        expect("Grilled Duck Fillet").to.be.equal(content2);
        expect('assets/roast.jpg').to.be.equal(src2);
        expect("Roast Trout").to.be.equal(content3);
        expect('assets/fish.jpg').to.be.equal(src3);


        await page.click(`text=${content1}`);
        const contentDetails1 = await page.textContent('#details article h2');
        const contentDetailsDesc1 = await page.textContent('#details article .description h3');
        const contentDetailsIngDesc1 = await page.textContent('#details article .band .ingredients h3');

        await page.click('#catalogLink');
        await page.click(`text=${content2}`);
        const contentDetails2 = await page.textContent('#details article h2');
        const contentDetailsDesc2 = await page.textContent('#details article .description h3');
        const contentDetailsIngDesc2 = await page.textContent('#details article .band .ingredients h3');

        await page.click('#catalogLink');
        await page.click(`text=${content3}`);
        const contentDetails3 = await page.textContent('#details article h2');
        const contentDetailsDesc3 = await page.textContent('#details article .description h3');
        const contentDetailsIngDesc3 = await page.textContent('#details article .band .ingredients h3');

        expect("Easy Lasagna").to.be.equal(contentDetails1);
        expect("Preparation:").to.be.equal(contentDetailsDesc1);
        expect("Ingredients:").to.be.equal(contentDetailsIngDesc1);

        expect("Grilled Duck Fillet").to.be.equal(contentDetails2);
        expect("Preparation:").to.be.equal(contentDetailsDesc2);
        expect("Ingredients:").to.be.equal(contentDetailsIngDesc2);

        expect("Roast Trout").to.be.equal(contentDetails3);
        expect("Preparation:").to.be.equal(contentDetailsDesc3);
        expect("Ingredients:").to.be.equal(contentDetailsIngDesc3);

    });

    it('2. Testing authentication', async function () {
        await page.goto(baseUrl);
        // after screenshot click to main again, to avoid stupid behaviour ...
        // await page.screenshot({ path: `index.png` });


        // const [response] = await Promise.all([
        //     page.waitForResponse('**/register.html'),
        //     await page.click('a[href="register.html"]')
        // ]);

        await page.click('#registerLink');

        await page.waitForSelector('#register');
        const sectionRegister = '#register article h2';
        const registerText = await page.textContent(sectionRegister);

        const selectorEmail = 'input[name="email"]';
        await page.fill(selectorEmail, 'syro@mail.bg');          // Text
        const selectorPass = 'input[name="password"]';
        await page.fill(selectorPass, '123');     // Date
        const selectorRePass = 'input[name="rePass"]';
        await page.fill(selectorRePass, '123');

        const [response] = await Promise.all([
            page.waitForResponse('**/register'),
            page.click('input[type="submit"]'),
        ]);

        // console.log(JSON.parse(response.request().postData()));

        // await page.click('#logoutBtn');

        await page.click('a[href="login.html"]');

        const sectionLogin = '#login article h2';

        const loginText = await page.textContent(sectionLogin);
        console.log(loginText);

        const selectorEmailLogin = 'input[name="email"]';
        await page.fill(selectorEmailLogin, 'syro@mail.bg');          // Text
        const selectorPassLogin = 'input[name="password"]';
        await page.fill(selectorPassLogin, '123');     // Date

        const [loginResponse] = await Promise.all([
            page.waitForResponse('**/login'),
            page.click('input[type="submit"]'),
        ]);

        expect("Register").to.be.equal(registerText);
        expect('Login').to.be.equal(loginText);



    });

    it('3. Testing crud ops on meals', async function () {
        await page.goto(baseUrl);
        // after screenshot click to main again, to avoid stupid behaviour ...
        // await page.screenshot({ path: `index.png` });

        // await page.click('#logoutBtn');

        await page.click('a[href="login.html"]');

        const sectionLogin = '#login article h2';

        const loginText = await page.textContent(sectionLogin);

        const selectorEmailLogin = 'input[name="email"]';
        await page.fill(selectorEmailLogin, 'syro@mail.bg');          // Text
        const selectorPassLogin = 'input[name="password"]';
        await page.fill(selectorPassLogin, '123');     // Date

        const [loginResponse] = await Promise.all([
            page.waitForResponse('**/login'),
            page.click('input[type="submit"]'),
        ]);


        expect('Login').to.be.equal(loginText);

        await page.click('#createLink');

        const recipeTestName = 'Musaka';
        const recipeTestIngredi = 'eggs, potatoes';
        const recipeTestPreps = 'potatoes + eggs + more';

        const selectorRecipeName = 'input[name="name"]';
        await page.fill(selectorRecipeName, recipeTestName);          // Text
        const selectorRecipeIngredi = 'textarea[name="ingredients"]';
        await page.fill(selectorRecipeIngredi, recipeTestIngredi);     // Date
        const selectorRecipePreps = 'textarea[name="steps"]';
        await page.fill(selectorRecipePreps, recipeTestPreps);     // Date


        const [createResponse] = await Promise.all([
            page.waitForResponse('**/data/recipes'),
            page.click('input[type="submit"]'),
        ]);

        // can see edit and delete btns
        const editSelector = '.controls button:nth-child(1)';
        const editVisible = await page.isVisible(editSelector);

        expect(editVisible).to.be.true;
        expect(await page.textContent('.controls button:nth-child(1)')).contains('Edit');

        const deleteSelector = '.controls button:nth-child(2)';
        const deleteVisible = await page.isVisible(deleteSelector);

        expect(deleteVisible).to.be.true;
        expect(await page.textContent('.controls button:nth-child(2)')).contains('Delete');
        
        // correct data is edited
        await page.click(editSelector);

        await page.waitForSelector('#editForm');
        const nameEdit = await page.inputValue('#editForm input[name="name"]');
        const ingrediEdit = await page.inputValue(selectorRecipeIngredi);
        const prepsEdit = await page.inputValue(selectorRecipePreps);

        expect(nameEdit).to.be.equal(recipeTestName);
        expect(ingrediEdit).to.be.equal(recipeTestIngredi);
        expect(prepsEdit).to.be.equal(recipeTestPreps);

        // edit makes correct API call for logged user
        await Promise.all([
            page.waitForRequest(req => {
                return req.url().includes('/data/recipes') && (req.method() === 'PUT');
            }),
            page.click('input[type="submit"]')
        ]);

        // include dialogs to prevent alerts during testing
        await page.once('dialog', async dialog => {
			await dialog.accept()
		});

        // delete makes correct API call for logged user
        await Promise.all([
            page.waitForRequest(req => {
                return req.url().includes('/data/recipes') && (req.method() === 'DELETE');
            }),
            page.click(deleteSelector)
        ]);
    });

});