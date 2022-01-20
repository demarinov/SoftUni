const expect = require('chai').expect;
const StringBuilder = require('../../main/Exercise/stringBuilder').StringBuilder;

describe("String builder checking", function () {
    it("Case 1: test append", function () {
        let str = new StringBuilder('hello');
        str.append(', there');
        console.log(str.toString());

        expect('hello, there').to.be.equal(str.toString());

    });

    it("Case 2: test prepend", function () {
        let str = new StringBuilder('hello');
        str.prepend('User, ');
        console.log(str.toString());

        expect('User, hello').to.be.equal(str.toString());

    });

    it("Case 3: test insert", function () {
        let str = new StringBuilder('hello1');
        str.insertAt('woop', 5);
        console.log(str.toString());

        expect('hellowoop1').to.be.equal(str.toString());

    });

    it("Case 4: test remove", function () {
        let str = new StringBuilder('helloThere');
        str.remove(6, 3);
        console.log(str.toString());

        expect('helloTe').to.be.equal(str.toString());
    });

    it("Case 5: test verifyParam constructor", function () {
        
        
        expect(() => str = new StringBuilder(2)).to.throw('Argument must be a string');
        try {
            let str = new StringBuilder(2);
        } catch (ex) {
            expect('Argument must be a string').to.be.equal(ex.message);
        }
    });

    it("Case 6: test verifyParam appending", function () {

        expect(() => str = new StringBuilder('2').append(1))
        .to.throw('Argument must be a string');
        try {
            let str = new StringBuilder('2');
            str.append(1);
        } catch (ex) {
            expect('Argument must be a string').to.be.equal(ex.message);
        }
    });

    it("Case 7: test verifyParam prepending", function () {

        expect(() => str = new StringBuilder('2').prepend(1))
        .to.throw('Argument must be a string');
        try {
            let str = new StringBuilder('2');
            str.prepend(1);
        } catch (ex) {
            expect('Argument must be a string').to.be.equal(ex.message);
        }
    });

    it("Case 8: test verifyParam inserting", function () {
        expect(() => str = new StringBuilder('2').insertAt(2, 1))
        .to.throw('Argument must be a string');
        try {
            let str = new StringBuilder('2');
            str.insertAt(2, 1);
        } catch (ex) {
            expect('Argument must be a string').to.be.equal(ex.message);
        }
    });

    it("Case 9: test constructor positive", function () {

        let str = new StringBuilder('2');

        expect('2').to.be.equal(str.toString());

    });

    it("Case 8: test constructor empty", function () {

            let str = new StringBuilder('');
            expect(0).to.be.equal(str.toString().length);
        
    });

    it("Case 9: test constructor with value 0", function () {

        expect(() => str = new StringBuilder(0)).to.throw('Argument must be a string');
    
    });

    it('Case 10: toString works correctly - special case', () => {
        const expected = '\n A \n\r B\t123   ';
        const obj = new StringBuilder();
    
        expected.split('').forEach(s => {obj.append(s); obj.prepend(s); });
    
        obj.insertAt('test', 4);
    
        obj.remove(2, 4);
    
        expect(obj.toString()).to.equal('  st21\tB \r\n A \n\n A \n\r B\t123   ');
    });

    it('Case 11: test insertAt positive', () => {
        let instance = new StringBuilder('abc');
            instance.insertAt('123', 1);
            expect(instance.toString()).to.equal('a123bc');
    });

    it('Case 12: test prepend positive', () => {
        let instance = new StringBuilder('abc');
        instance.prepend('123');
        expect(instance.toString()).to.equal('123abc');
    });

    it('Case 13: test append positive', () => {
        let instance = new StringBuilder('abc');
        instance.append('123');
        expect(instance.toString()).to.equal('abc123');
    });

    it('Case 14: test constructor positive letters', () => {
        let instance = new StringBuilder('a');
        expect(() => instance).not.to.throw(TypeError, 'Argument must be a string');
        expect(instance.toString()).to.equal('a');
    });

});
