const expect = require('chai').expect;
const createCalculator = require('../../main/Lab/addSubtract').createCalculator;


describe('Add or subtract value check', function () {

    it('1. add value and get right result', function () {
        const calculator = createCalculator();
        let expected = 2;
        calculator.add('2');
        let actual = calculator.get();
        expect(expected).to.equal(actual);
    });

    it('2. subtract value and get right result', function () {
        const calculator = createCalculator();
        let expected = 2;
        calculator.add('5');
        calculator.subtract('3');
        let actual = calculator.get();
        expect(expected).to.equal(actual);
    });

    it('3. get initial value', function() {
        const calculator = createCalculator();
        let expected = 0;
        let actual = calculator.get();
        expect(expected).to.equal(actual);
    });

    it(`4. obj has method add`, () => {
        expect(typeof createCalculator().add).to.equals('function')
    })
    it(`5. obj has method subtract`, () => {
        expect(typeof createCalculator().subtract).to.equals('function')
    })
    it(`6. obj has method get`, () => {
        expect(typeof createCalculator().get).to.equals('function')
    })
    it(`7. internal sum cannot be modified`, () => {
        expect(createCalculator().value).to.equals(undefined)
    })
});