const expect = require('chai').expect;
const numberOperations = require('../../main/Lab/numberOperations').numberOperations;


describe("Tests", function() {

    describe("powNumber", function() {

        it("1. Check power of number …", function() {
            const expected = 4;
            let res = numberOperations.powNumber(2);
            expect(res).to.be.equal(expected);

            res = numberOperations.powNumber(-2);
            expect(res).to.be.equal(expected);
        });
     });

     describe("numberChecker", function() {

        it("1. NaN case - throw error", function() {
            const expected = 4;
            let res;
            expect(numberOperations.numberChecker.bind(numberOperations, 'true'))
            .to.throw('The input is not a number!');
        });

        it("2. Number is lower than 100", function() {
            const expected = 'The number is lower than 100!';
            let res = numberOperations.numberChecker(4);
            expect(res).to.be.equal(expected);
        });

        it("3. Number is greater or equal to 100", function() {
            const expected = 'The number is greater or equal to 100!';
            let res = numberOperations.numberChecker(110);
            expect(res).to.be.equal(expected);

            res = numberOperations.numberChecker(100);
            expect(res).to.be.equal(expected);
        });

     });

     describe("sumArrays", function() {

        it("1. arr1 > arr2", function() {
            const expected = [2,4,3];
            let res = numberOperations.sumArrays([1,2,3], [1,2]);
            expect(res).to.deep.equal(expected);
        });

        it("2. arr2 > arr1", function() {
            const expected = [2,5,4];
            let res = numberOperations.sumArrays([1,2], [1,3,4]);
            expect(res).to.deep.equal(expected);
        });

        it("3. arr2 == arr1", function() {
            const expected = [2,5,7];
            let res = numberOperations.sumArrays([1,2,3], [1,3,4]);
            expect(res).to.deep.equal(expected);
        });

     });
     
});
