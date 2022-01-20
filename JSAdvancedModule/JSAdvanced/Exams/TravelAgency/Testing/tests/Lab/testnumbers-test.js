const expect = require('chai').expect;
const testNumbers = require('../../main/Lab/testNumbers').testNumbers;


describe("Test numbers", function() {
    describe("sumNumber", function() {
        it("1. nums are undefined", function() {
            let expected = undefined;
            let res = testNumbers.sumNumbers('1',2);

            expect(expected).to.equal(res);

            testNumbers.sumNumbers(1,'2');

            expect(expected).to.equal(res);
        });

        it("2. right case of sum", function() {
            let expected = 5.00;
            let res = testNumbers.sumNumbers(3,2);

            expect(Number(res)).to.be.closeTo(5.00,0.01);

            expected = 5.56;
            res = testNumbers.sumNumbers(3,2.567);

            expect(Number(res)).to.be.closeTo(5.57,0.01);
        });
     });
     // TODO: …
     describe("numberChecker", function() {
        it("1. NaN check", function() {
            let expected = NaN;

            expect(testNumbers.numberChecker.bind(testNumbers, 'a1')).
            to.throw('The input is not a number!');


        });

        it("2. even check", function() {
            let expected = 'The number is even!';
            let res = testNumbers.numberChecker(4);

            expect(res).to.be.equal(expected);

            testNumbers.numberChecker('4');

            expect(res).to.be.equal(expected);
        });

        it("3. odd check", function() {
            let expected = 'The number is odd!';
            let res = testNumbers.numberChecker(5);

            expect(res).to.be.equal(expected);

            res = testNumbers.numberChecker('5');

            expect(res).to.be.equal(expected);
        });
     });
     describe("averageSumArray", function() {
        it("1. empty array - NaN", function() {
            let expected = NaN;
            let res = testNumbers.averageSumArray([]);

            expect(res).to.NaN;
        });

        it("2. zero sum", function() {
            let expected = 0;
            let res = testNumbers.averageSumArray([0]);

            expect(res).to.equal(expected);
        });

        it("3. right avg sum", function() {
            let expected = 2;
            let res = testNumbers.averageSumArray([2,2,2]);

            expect(res).to.equal(expected);

            expected = 2.33;
            res = testNumbers.averageSumArray([3,2,2]);

            expect(res).to.be.closeTo(expected,0.01);
        });
     });
});
