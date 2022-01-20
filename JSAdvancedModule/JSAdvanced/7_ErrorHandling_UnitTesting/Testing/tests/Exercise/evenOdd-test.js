const expect = require('chai').expect;
const isOddOrEven = require('../../main/Exercise/evenOdd.js').isOddOrEven;

describe('Check for odd or even', function () {
    it('Case 1: invalid type number', function () {
        let expected = undefined;
        let actual = isOddOrEven(2);

        expect(expected).to.be.equal(actual);

        actual = isOddOrEven(10);

        expect(expected).to.be.equal(actual);
    });

    it('Case 2: get even', function () {
        let expected = 'even';
        let actual = isOddOrEven('dodo');

        expect(expected).to.be.equal(actual);
    });

    it('Case 3: get odd', function () {
        let expected = 'odd';
        let actual = isOddOrEven('dod');

        expect(expected).to.be.equal(actual);
    });

    it('Case 4: mutiple inputs in a row', function () {
        let expected = 'odd';
        let actual = isOddOrEven('dod');

        expect(expected).to.be.equal(actual);

        actual = isOddOrEven('dodao');

        expect(expected).to.be.equal(actual);

        actual = isOddOrEven('dodas');

        expect(expected).to.be.equal(actual);
    });
});