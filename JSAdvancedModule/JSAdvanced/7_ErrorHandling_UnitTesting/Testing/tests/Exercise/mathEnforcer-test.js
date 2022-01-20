const expect = require('chai').expect;
const mathEnforcer = require('../../main/Exercise/mathEnforcer.js').mathEnforcer;

describe('addFive', function () {
    it('Case 1: invalid type string', function () {
        let expected = undefined;
        let actual = mathEnforcer.addFive('2');

        expect(expected).to.be.equal(actual);
    });

    it('Case 2: right result integer', function () {
        let expected = 15;
        let num = 10;
        let actual = mathEnforcer.addFive(num);

        expect(expected).to.be.equal(actual);

        // negative num
        expected = 0;
        num = -5;
        actual = mathEnforcer.addFive(num);
        expect(expected).to.be.equal(actual);
    });

    it('Case 3: right result float', function () {
        let expected = 15.5;
        let num = 10.51;
        let actual = mathEnforcer.addFive(num);

        expect(expected).to.be.closeTo(actual,0.01);
    });
});

describe('subtractTen', function () {
    it('Case 1: invalid type string', function () {
        let expected = undefined;
        let num = 20;
        let actual = mathEnforcer.subtractTen('d');

        expect(expected).to.be.equal(actual);

    });

    it('Case 2: right result integer', function () {
        let expected = 10;
        let num = 20;
        let actual = mathEnforcer.subtractTen(num);

        expect(expected).to.be.equal(actual);

        expected = -15;
        num = -5;
        actual = mathEnforcer.subtractTen(num);

        expect(expected).to.be.equal(actual);
    });

    it('Case 3: right result float', function () {
        let expected = 10.5;
        let num = 20.5;
        let actual = mathEnforcer.subtractTen(num);

        expect(expected).to.be.closeTo(actual,0.01);
    });

});

describe('sum', function () {
    it('Case 1: invalid type string', function () {
        let expected = undefined;
        let actual = mathEnforcer.sum('dod',10);

        expect(expected).to.be.equal(actual);

        actual = mathEnforcer.sum('dod','1');

        expect(expected).to.be.equal(actual);

        actual = mathEnforcer.sum(2,'1');

        expect(expected).to.be.equal(actual);

    });

    it('Case 2: right result integer', function () {
        let expected = 30;
        let num = 20;
        let actual = mathEnforcer.sum(num,10);

        expect(expected).to.be.equal(actual);

        expected = 10;
        num = 20;
        actual = mathEnforcer.sum(num,-10);

        expect(expected).to.be.equal(actual);
    });

    it('Case 3: right result float', function () {
        let expected = 31;
        let num = 20.5;
        let actual = mathEnforcer.sum(num,10.5);

        expect(expected).to.be.closeTo(actual,0.01);
    });

});