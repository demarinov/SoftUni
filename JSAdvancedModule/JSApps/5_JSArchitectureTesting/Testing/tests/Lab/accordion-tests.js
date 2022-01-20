const chromium  = require('playwright-chromium').chromium;
const expect = require('chai').expect;
const solution = require('../../main/Lab/01. Accordion/accordion').solution;
let browser, page; // Declare reusable variables

// web-server should be started as well as the rest service
describe('E2E accordion tests',function() {
  before(async () => { browser = await chromium.launch({headless:true}); });
  after(async () => { await browser.close(); });
  beforeEach(async () => {page = await browser.newPage(); });
  afterEach(async () => {await page.close(); }); 

  it('1. Loads titles', async function() {
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

    expect("Scalable Vector Graphics").to.be.equal(content1);
    expect("Open standard").to.be.equal(content2);
    expect("Unix").to.be.equal(content3);
    expect("ALGOL").to.be.equal(content4);

  });

  it('2. Show article details',async function() {
    await page.goto('http://127.0.0.1:5500/main/Lab/01.%20Accordion/index.html');
    await page.screenshot({ path: `index.png` });

    const selectorBtn1 = '.accordion:nth-child(1) .head button';
    const selectorBtn2 = '.accordion:nth-child(2) .head button';
    const selectorBtn3 = '.accordion:nth-child(3) .head button';
    const selectorBtn4 = '.accordion:nth-child(4) .head button';
      
    await page.click(selectorBtn1);
    const details1 = '.accordion:nth-child(1) .extra>p';
    const content1 = await page.textContent(details1);
    const btn1 = await page.textContent(selectorBtn1);

    await page.click(selectorBtn2);
    const details2 = '.accordion:nth-child(2) .extra>p';
    const content2 = await page.textContent(details2);
    const btn2 = await page.textContent(selectorBtn2);

    await page.click(selectorBtn3);
    const details3 = '.accordion:nth-child(3) .extra>p';
    const content3 = await page.textContent(details3);
    const btn3 = await page.textContent(selectorBtn3);

    await page.click(selectorBtn4);
    const details4 = '.accordion:nth-child(4) .extra>p';
    const content4 = await page.textContent(details4);
    const btn4 = await page.textContent(selectorBtn4);
    // const content4 = await page.textContent(selectorTitle4);

    console.log(content1);
    console.log(content2);
    console.log(content3);
    console.log(content4);

    const expected1 = "Scalable Vector Graphics (SVG) is an Extensible Markup Language (XML)-based vector image format for two-dimensional graphics with support for interactivity and animation. The SVG specification is an open standard developed by the World Wide Web Consortium (W3C) since 1999.";
    expect(expected1).to.be.equal(content1);
    expect('Less').to.be.equal(btn1);

    const expected2 = "An open standard is a standard that is publicly available and has various rights to use associated with it and may also have various properties of how it was designed (e.g. open process). There is no single definition, and interpretations vary with usage.";
    expect(expected2).to.be.equal(content2);
    expect('Less').to.be.equal(btn2);

    const expected3 = "Unix (trademarked as UNIX) is a family of multitasking, multiuser computer operating systems that derive from the original AT&T Unix, development starting in the 1970s at the Bell Labs research center by Ken Thompson, Dennis Ritchie, and others.";
    expect(expected3).to.be.equal(content3);
    expect('Less').to.be.equal(btn3);

    const expected4 = "ALGOL (short for \"Algorithmic Language\") is a family of imperative computer programming languages originally developed in 1958. ALGOL heavily influenced many other languages and was the standard method for algorithm description used by the Association for Computing Machinery (ACM) in textbooks and academic sources until object-oriented languages came around, for more than thirty years.";
    expect(expected4).to.be.equal(content4);
    expect('Less').to.be.equal(btn4);


  });

  it('3. Hide article details',async function() {
    await page.goto('http://127.0.0.1:5500/main/Lab/01.%20Accordion/index.html');
    await page.screenshot({ path: `index.png` });

    const selectorBtn1 = '.accordion:nth-child(1) .head button';
    const selectorBtn2 = '.accordion:nth-child(2) .head button';
    const selectorBtn3 = '.accordion:nth-child(3) .head button';
    const selectorBtn4 = '.accordion:nth-child(4) .head button';

    await page.dblclick(selectorBtn1);
    const details1 = '.accordion:nth-child(1) .extra';
    const style1 = await page.getAttribute(details1,'style');
    const btn1 = await page.textContent(selectorBtn1);

    await page.dblclick(selectorBtn2);
    const details2 = '.accordion:nth-child(2) .extra';
    const style2 = await page.getAttribute(details2,'style');
    const btn2 = await page.textContent(selectorBtn2);

    await page.dblclick(selectorBtn3);
    const details3 = '.accordion:nth-child(3) .extra';
    const style3 = await page.getAttribute(details3,'style');
    const btn3 = await page.textContent(selectorBtn3);

    await page.dblclick(selectorBtn4);
    const details4 = '.accordion:nth-child(4) .extra';
    const style4 = await page.getAttribute(details4,'style');
    const btn4 = await page.textContent(selectorBtn4);

    console.log(style1);
    console.log(style2);
    console.log(style3);
    console.log(style4);

    expect('More').to.be.equal(btn1);
    expect('display: none;').to.be.equal(style1);
    expect('More').to.be.equal(btn2);
    expect('display: none;').to.be.equal(style2);
    expect('More').to.be.equal(btn3);
    expect('display: none;').to.be.equal(style3);
    expect('More').to.be.equal(btn4);
    expect('display: none;').to.be.equal(style4);


  });
  
});
