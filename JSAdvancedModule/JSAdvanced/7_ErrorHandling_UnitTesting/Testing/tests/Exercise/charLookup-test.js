const expect = require('chai').expect;
const lookupChar = require('../../main/Exercise/charLookup.js').lookupChar;

describe('Lookup for char', function () {
    it('Case 1: invalid type', function () {
        let expected = undefined;
        let actual = lookupChar(2,1);

        expect(expected).to.be.equal(actual);

        actual = lookupChar(10,1);

        expect(expected).to.be.equal(actual);
    });

    it('Case 2: Invalid type of index', function () {
        let expected = undefined;
        let actual = lookupChar('dodo','1');

        expect(expected).to.be.equal(actual);

        actual = lookupChar('dod',4.1);
        expect(expected).to.be.equal(actual);
    });

    it('Case 3: incorrect index', function () {
        let expected = 'Incorrect index';
        let actual = lookupChar('dod',4);

        expect(expected).to.be.equal(actual);

        actual = lookupChar('dod',-1);
        expect(expected).to.be.equal(actual);
    });

    it('Case 4: right outcome', function () {
        let expected = 'o';
        let actual = lookupChar('dod',1);

        expect(expected).to.be.equal(actual);

        actual = lookupChar('dodao',4);

        expect(expected).to.be.equal(actual);

        actual = lookupChar('dodas',1);

        expect(expected).to.be.equal(actual);
    });
});