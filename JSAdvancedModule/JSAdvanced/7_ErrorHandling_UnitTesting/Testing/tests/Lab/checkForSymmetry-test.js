const expect = require('chai').expect;
const isSymmetric = require('../../main/Lab/checkForSymmetry').isSymmetric;


describe('Check for symmetric array', function () {

    it('Case 1: check if input is not array', function () {
        let expected = false;

        let actual = isSymmetric(1);

        expect(expected).to.be.equal(actual);

        actual = isSymmetric('1');

        expect(expected).to.be.equal(actual);
    });

    it('Case 2: check for non-symmetry', function () {

        let expected = false;
        let actual = isSymmetric([1,'a','2',1]);

        expect(expected).to.be.equal(actual);

        actual = isSymmetric([1,2,'a','a','2',1]);

        expect(expected).to.be.equal(actual);
    });

    it('Case 3: check for symmetry', function() {
        let expected = true;
        let actual = isSymmetric([1,2,'a','a',2,1]);

        expect(expected).to.be.equal(actual);
    });

    it('Case 4: check empty array', function () {
        let expected = true;
        let actual = isSymmetric([]);

        expect(expected).to.be.equal(actual);
    })
});