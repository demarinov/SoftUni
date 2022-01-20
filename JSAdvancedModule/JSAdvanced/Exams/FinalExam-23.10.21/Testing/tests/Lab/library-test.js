const expect = require('chai').expect;
const library = require('../../main/Lab/library/library').library;

describe("Library tests", function() {
    describe("calcPriceOfBook", function() {
        it("1. invalid name", function() {
            let expected = 'Invalid input';

            expect(library.calcPriceOfBook.bind(library,1,2009)).to.throw(expected);
            expect(library.calcPriceOfBook.bind(library,[],2009)).to.throw(expected);
            expect(library.calcPriceOfBook.bind(library,{},2009)).to.throw(expected);
            expect(library.calcPriceOfBook.bind(library,null,2009)).to.throw(expected);
            expect(library.calcPriceOfBook.bind(library,undefined,2009)).to.throw(expected);
            expect(library.calcPriceOfBook.bind(library,NaN,2009)).to.throw(expected);

        });

        it("2. invalid year", function() {
            let expected = 'Invalid input';

            expect(library.calcPriceOfBook.bind(library,'Tango',2.5)).to.throw(expected);
            expect(library.calcPriceOfBook.bind(library,'Tango','2.5')).to.throw(expected);
            expect(library.calcPriceOfBook.bind(library,'Tango',null)).to.throw(expected);
            expect(library.calcPriceOfBook.bind(library,'Tango',undefined)).to.throw(expected);
            expect(library.calcPriceOfBook.bind(library,'Tango',NaN)).to.throw(expected);
            expect(library.calcPriceOfBook.bind(library,'Tango',{})).to.throw(expected);
            expect(library.calcPriceOfBook.bind(library,'Tango',[])).to.throw(expected);

        });

        it("3. Valid year <= 1980, price discounted", function() {

            let expected = `Price of Tango is 10.00`;
            let res = library.calcPriceOfBook('Tango',1980);

            expect(res).to.be.equal(expected);

            res = library.calcPriceOfBook('Tango',1979);

            expect(res).to.be.equal(expected);

        });

        it("4. Valid year > 1980, price", function() {

            let expected = `Price of Tango is 20.00`;
            let res = library.calcPriceOfBook('Tango',1981);

            expect(res).to.be.equal(expected);

            res = library.calcPriceOfBook('Tango',1990);

            expect(res).to.be.equal(expected);

        });

     });

     describe("findBook", function() {
        it("1. invalid length", function() {
            let expected = "No books currently available";

            expect(library.findBook.bind(library,[],'Tango')).to.throw(expected);
        });

        it("2. found book", function() {
            let expected = "We found the book you want.";
            let res = library.findBook(['Tango','Venetoo'],'Tango')
            expect(res).to.be.equal(expected);

            res = library.findBook(['Tango','Venetoo'],'Venetoo')
            expect(res).to.be.equal(expected);
        });

        it("3. book not found", function() {
            let expected = "The book you are looking for is not here!";
            let res = library.findBook(['Tango','Venetoo'],'Tango1')
            expect(res).to.be.equal(expected);

            res = library.findBook(['Tango','Venetoo'],'Venetoo2')
            expect(res).to.be.equal(expected);
        });
     });

     describe("arrangeTheBooks", function() {
        it("1. invalid number", function() {
            let expected = "Invalid input";
            

            expect(library.arrangeTheBooks.bind(library,'')).to.throw(expected);
            expect(library.arrangeTheBooks.bind(library,2.5)).to.throw(expected);
            expect(library.arrangeTheBooks.bind(library,{})).to.throw(expected);
            expect(library.arrangeTheBooks.bind(library,[])).to.throw(expected);
            expect(library.arrangeTheBooks.bind(library,null)).to.throw(expected);
            expect(library.arrangeTheBooks.bind(library,undefined)).to.throw(expected);
            expect(library.arrangeTheBooks.bind(library,NaN)).to.throw(expected);
            expect(library.arrangeTheBooks.bind(library,-0.5)).to.throw(expected);
        });

        it("2. invalid count", function() {
            let expected = "Invalid input";
            expect(library.arrangeTheBooks.bind(library,-10)).to.throw(expected);
            expect(library.arrangeTheBooks.bind(library,-100)).to.throw(expected);
        });

        it("3. not enough space", function() {
            let expected = "Insufficient space, more shelves need to be purchased.";
            let res = library.arrangeTheBooks(41);

            expect(res).to.be.equal(expected);

            res = library.arrangeTheBooks(43);

            expect(res).to.be.equal(expected);
        });

        it("4. books are arranged", function() {
            let expected = "Great job, the books are arranged.";
            let res = library.arrangeTheBooks(40);

            expect(res).to.be.equal(expected);

            res = library.arrangeTheBooks(39);

            expect(res).to.be.equal(expected);

            res = library.arrangeTheBooks(0);

            expect(res).to.be.equal(expected);
        });

     });
     
});
