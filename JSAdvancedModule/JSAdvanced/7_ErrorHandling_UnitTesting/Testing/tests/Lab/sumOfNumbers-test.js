const expect = require('chai').expect;
const sum = require('../../main/Lab/sumOfNumbers').sum;

describe('Calculate sum of numbers in array', function () {
    it('Case 1: check sum of array values', function () {
        let expected = 3;
        let actual = sum([1, 2]);

        expect(expected).to.be.equal(actual);
    });

    it('Case 2: check if array of numbers is passed', function () {

        let expected = true;
        let actual = sum(['a', 1, 2]);
        expect(expected).to.be.equal(isNaN(actual));

    });
});