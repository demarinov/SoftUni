const expect = require('chai').expect;
const rgbToHexColor = require('../../main/Lab/rgb').rgbToHexColor;


describe('Convert rgb string to hexadecimal check', function () {

    it('1. get correct hex value', function () {

        let expected = '#FFFFFF';
        let actual = rgbToHexColor(255,255,255);
        expect(expected).to.be.equal(actual);

        expected = '#FF00FF';
        actual = rgbToHexColor(255,0,255);
        expect(expected).to.be.equal(actual);

        expected = '#000000';
        actual = rgbToHexColor(0,0,0);
        expect(expected).to.be.equal(actual);
    });

    it('2. Invalid input color value', function () {
        let expected = undefined;
        let actual = rgbToHexColor(256,255,255);
        expect(expected).to.be.equal(actual);

        actual = rgbToHexColor(-1,255,255);
        expect(expected).to.be.equal(actual);

        // float
        actual = rgbToHexColor(255.5,255,255);
        expect(expected).to.be.equal(actual);

        // string
        actual = rgbToHexColor('255',255,255);
        expect(expected).to.be.equal(actual);

        actual = rgbToHexColor(255,256,255);
        expect(expected).to.be.equal(actual);

        actual = rgbToHexColor(255,-1,255);
        expect(expected).to.be.equal(actual);

        actual = rgbToHexColor(255,256.6,255);
        expect(expected).to.be.equal(actual);

        actual = rgbToHexColor(255,'256',255);
        expect(expected).to.be.equal(actual);

        actual = rgbToHexColor(255,255,256);
        expect(expected).to.be.equal(actual);

        actual = rgbToHexColor(255,255,-256);
        expect(expected).to.be.equal(actual);

        actual = rgbToHexColor(256,255,256.6);
        expect(expected).to.be.equal(actual);

        actual = rgbToHexColor(256,255,'256');
        expect(expected).to.be.equal(actual);
    });
});