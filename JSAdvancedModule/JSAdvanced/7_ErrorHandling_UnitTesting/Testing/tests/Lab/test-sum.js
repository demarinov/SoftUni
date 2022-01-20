const expect = require('chai').expect;
const sumNums = require('../../main/Lab/sumNums').sumNums;

describe('sum numbers', function() {

    it('case 1: sum 1 and 2', function() {
        let expectedSum = 3;
        let actualSum = sumNums(1,2);

        expect(expectedSum).to.be.equal(actualSum);
    });

    it('case 2: sum 0', function () {
        let expectedSum = 0;
        let actualSum = sumNums(0,0);

        expect(expectedSum).to.be.equal(actualSum);
    });
});