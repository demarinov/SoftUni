const expect = require('chai').expect;
const PaymentPackage = require('../../main/Exercise/payment-package.js').PaymentPackage;



describe("Test payment package", function () {
    it("Case 1.1: invalid value", function () {

        // Should throw an error
        try {
            const hrPack = new PaymentPackage('HR Services');

        } catch (err) {
            let expected = 'Value must be a non-negative number';
            expect(expected).to.be.equal(err.message);
        }

        try {
            const hrPack = new PaymentPackage('HR Services',-2);

        } catch (err) {
            let expected = 'Value must be a non-negative number';
            expect(expected).to.be.equal(err.message);
        }

    });

    it("Case 1.2: invalid name", function () {

        // Should throw an error
        try {
            const hrPack = new PaymentPackage('', 1);
        } catch (err) {
            let expected = 'Name must be a non-empty string';
            expect(expected).to.be.equal(err.message);
        }



        try {
            const hrPack = new PaymentPackage(123, 1);
        } catch (err) {
            let expected = 'Name must be a non-empty string';
            expect(expected).to.be.equal(err.message);
        }

    });

    it("Case 1.3: invalid VAT", function () {

        // Should throw an error
        try {
            const hrPack = new PaymentPackage('HRServices', 200);
            hrPack.VAT = -10;
        } catch (err) {
            let expected = 'VAT must be a non-negative number';
            expect(expected).to.be.equal(err.message);
        }

        try {
            const hrPack = new PaymentPackage('HRServices', 200);
            hrPack.VAT = '10';
        } catch (err) {
            let expected = 'VAT must be a non-negative number';
            expect(expected).to.be.equal(err.message);
        }

    });

    it("Case 1.4: invalid active type", function () {

        // Should throw an error
        try {
            const hrPack = new PaymentPackage('HRServices', 200);
            hrPack.active = 10;
        } catch (err) {
            let expected = 'Active status must be a boolean';
            expect(expected).to.be.equal(err.message);
        }

        try {
            const hrPack = new PaymentPackage('HRServices', 200);
            hrPack.active = null;
        } catch (err) {
            let expected = 'Active status must be a boolean';
            expect(expected).to.be.equal(err.message);
        }

    });

    it("Case 2.1: test multiple correct packages", function () {

        const packages = [
            new PaymentPackage('HR Services', 1500),
            new PaymentPackage('Consultation', 800),
            new PaymentPackage('Partnership Fee', 7000),
        ];
        // console.log(packages.join('\n'));

        expect(true).to.be.equal(packages[0] instanceof PaymentPackage);
        expect(true).to.be.equal(packages[1] instanceof PaymentPackage);
        expect(true).to.be.equal(packages[2] instanceof PaymentPackage);

        expect(1500).to.be.equal(packages[0].value);
        expect(800).to.be.equal(packages[1].value);
        expect(7000).to.be.equal(packages[2].value);

        expect('HR Services').to.be.equal(packages[0].name);
        expect('Consultation').to.be.equal(packages[1].name);
        expect('Partnership Fee').to.be.equal(packages[2].name);

        expect(20).to.be.equal(packages[0].VAT);
        expect(20).to.be.equal(packages[1].VAT);
        expect(20).to.be.equal(packages[2].VAT);

        expect(true).to.be.equal(packages[0].active);
        expect(true).to.be.equal(packages[1].active);
        expect(true).to.be.equal(packages[2].active);

        let expectedOne = 'Package: HR Services\n- Value (excl. VAT): 1500\n- Value (VAT 20%): 1800';
        let expectedTwo = 'Package: Consultation\n- Value (excl. VAT): 800\n- Value (VAT 20%): 960';
        let expectedThree = 'Package: Partnership Fee\n- Value (excl. VAT): 7000\n- Value (VAT 20%): 8400';

        expect(expectedOne).to.be.equal(packages[0].toString());
        expect(expectedTwo).to.be.equal(packages[1].toString());
        expect(expectedThree).to.be.equal(packages[2].toString());
    });

    it("Case 2.2: test multiple correct packages and their setters", function () {

        const packages = [
            new PaymentPackage('HR Services', 1500),
            new PaymentPackage('Consultation', 800),
            new PaymentPackage('Partnership Fee', 7000),
        ];
        // console.log(packages.join('\n'));

        packages[0].value = 1600;
        packages[1].value = 1600;
        packages[2].value = 1600;

        expect(1600).to.be.equal(packages[0].value);
        expect(1600).to.be.equal(packages[1].value);
        expect(1600).to.be.equal(packages[2].value);

        packages[0].name = 'Barnie';
        packages[1].name = 'Barnie';
        packages[2].name = 'Barnie';
        expect('Barnie').to.be.equal(packages[0].name);
        expect('Barnie').to.be.equal(packages[1].name);
        expect('Barnie').to.be.equal(packages[2].name);

        packages[0].VAT = 10;
        packages[1].VAT = 10;
        packages[2].VAT = 10;
        expect(10).to.be.equal(packages[0].VAT);
        expect(10).to.be.equal(packages[1].VAT);
        expect(10).to.be.equal(packages[2].VAT);

        packages[0].actual = false;
        packages[1].actual = false;
        packages[2].actual = false;
        expect(false).to.be.equal(packages[0].actual);
        expect(false).to.be.equal(packages[1].actual);
        expect(false).to.be.equal(packages[2].actual);

    });

    it("Case 3: appending inactive in toString() method", function () {

        const pack = new PaymentPackage('Transfer Fee', 100);
        pack.active = false;

        let expected = 'Package: Transfer Fee (inactive)\n- Value (excl. VAT): 100\n- Value (VAT 20%): 120';

        expect(expected).to.be.equal(pack.toString());

    });

    it("Case 4: appending inactive in toString() method changed VAT", function () {

        const pack = new PaymentPackage('Transfer Fee', 100);
        pack.active = false;
        pack.VAT = 30;
        // console.log(pack.toString());

        let expected = 'Package: Transfer Fee (inactive)\n- Value (excl. VAT): 100\n- Value (VAT 30%): 130';

        expect(expected).to.be.equal(pack.toString());

    });

    it("Case 5: no inactive in toString() method changed VAT", function () {

        const pack = new PaymentPackage('Transfer Fee', 100);
        pack.active = true;
        pack.VAT = 30;
        // console.log(pack.toString());

        let expected = 'Package: Transfer Fee\n- Value (excl. VAT): 100\n- Value (VAT 30%): 130';

        expect(expected).to.be.equal(pack.toString());

    });

    it("Case 6: value 0", function () {

        const pack = new PaymentPackage('Transfer Fee', 100);
        pack.value = 0;

        let expected = 0;

        expect(expected).to.be.equal(pack.value);

    });

    it ('Case 7: Should throw errow when the new Active is a string', () => {
        let flagClass = new PaymentPackage('abc', 123);
        expect(() => flagClass.active = 'abc').to.throw('Active status must be a boolean');
    });

    it ('Case 8: Should return the new Active if the input is good', () => {
        let flagClass = new PaymentPackage('abc', 123);
        expect(() => flagClass.active = true).not.to.throw('Active status must be a boolean');
    });

    it ('Case 9: Should throw errow when the new VAT is an array', () => {
        let flagClass = new PaymentPackage('abc', 123);
        expect(() => flagClass.VAT = [123]).to.throw('VAT must be a non-negative number');
    });
    
    it ('Case 10: Should throw errow when the new VAT is negative', () => {
        let flagClass = new PaymentPackage('abc', 123);
        expect(() => flagClass.VAT = -123).to.throw('VAT must be a non-negative number');
    });
    
    it ('Case 11: Should return the new VAT if the input is good', () => {
        let flagClass = new PaymentPackage('abc', 123);
        expect(() => flagClass.VAT = 123).not.to.throw('VAT must be a non-negative number');
    });

    it ('Case 12: Should throw errow when the new Value is negative', () => {
        expect(() => new PaymentPackage('abc', -123)).to.throw('Value must be a non-negative number');
    });
    
    it ('Case 13: Should return the new Value if the input is good', () => {
        expect(() => new PaymentPackage('abc', 123)).not.to.throw('Value must be a non-negative number');
    });

    it ('Case 14: Should throw errow when the new Value is a string', () => {
        expect(() => new PaymentPackage('abc', 'abc')).to.throw('Value must be a non-negative number');
    });

    it ('Case 15: Should throw errow when the new Name is empty', () => {
        expect(() => new PaymentPackage('', 123)).to.throw('Name must be a non-empty string');
    });
    
    it ('Case 16: Should return the new Name if the input is good', () => {
        expect(() => new PaymentPackage('abc', 123)).not.to.throw('Name must be a non-empty string');
    });

    it ('Case 17: Should throw errow when the new Name is a number', () => {
        expect(() => new PaymentPackage(123, 123)).to.throw('Name must be a non-empty string');
    });

    // TODO: …
});
