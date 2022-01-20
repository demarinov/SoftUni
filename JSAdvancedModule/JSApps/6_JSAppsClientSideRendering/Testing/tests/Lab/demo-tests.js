const { chromium } = require('playwright-chromium');
const { expect } = require('chai');
const solution = require('../../main/Lab/01. Accordion/accordion').solution;
let browser, page; // Declare reusable variables

// web-server should be started as well as the rest service
describe('E2E tests', function() {
  before(async () => { browser = await chromium.launch(); });
  after(async () => { await browser.close(); });
  beforeEach(async () => { page = await browser.newPage(); });
  afterEach(async () => { await page.close(); }); 

  it('loads static page', async function() {
    await page.goto('http://127.0.0.1:5500/main/Lab/01.%20Accordion/index.html');
    await page.screenshot({ path: `index.png` });

    const selectorTitle1 = '.accordion:nth-child(1) .head span';
    const selectorTitle2 = '.accordion:nth-child(2) .head span';
    const selectorTitle3 = '.accordion:nth-child(3) .head span';
    const selectorTitle4 = '.accordion:nth-child(4) .head span';

    const content1 = await page.textContent(selectorTitle1);
    const content2 = await page.textContent(selectorTitle2);
    const content3 = await page.textContent(selectorTitle3);
    const content4 = await page.textContent(selectorTitle4);

    console.log(content1);
    console.log(content2);
    console.log(content3);
    console.log(content4);
    await browser.close();
  });
  
});
