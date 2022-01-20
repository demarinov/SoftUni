class Hex {

    constructor(value) {
        this.value = value;
    };

    valueOf() {
        return this.value;
    };

    toString() {

        let hexValue = '0x';

        hexValue += this.value.toString(16).toUpperCase();
        

        return hexValue;
    };
    plus(obj) {

        if (typeof obj === 'number') {

            return new Hex(this.value + obj);
        } else {
            return new Hex(this.value + obj.value);
        }

    };
    minus(obj) {
        if (typeof obj === 'number') {

            return new Hex(this.value - obj);
        } else {
            return new Hex(this.value - obj.value);
        }
    };
    static parse(str) {

        return parseInt(str, 16);
    }
}


function test() {
    let FF = new Hex(255);
    console.log(FF.toString());
    FF.valueOf() + 1 == 256;
    let a = new Hex(10);
    let b = new Hex(5);
    console.log(a.plus(b).toString());
    console.log(a.plus(b).toString() === '0xF');
    console.log(Hex.parse('AAA'));

    console.log(a.minus(b).toString());

}

test();