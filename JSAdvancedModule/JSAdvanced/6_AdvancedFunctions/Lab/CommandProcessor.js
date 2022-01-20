function solution() {

    let obj = {
        value:'',
        append: function (val) {
            this.value = this.value + val;
        },
        removeStart: function (n) {
            this.value = this.value.substr(n);
        },
        removeEnd: function (n) {
            this.value = this.value.substr(0,this.value.length - n);
        },
        print: function () {
            console.log(this.value);
        }
    }

    return obj;
}

function test() {
    let firstZeroTest = solution();

firstZeroTest.append('hello');
firstZeroTest.append('again');
firstZeroTest.removeStart(3);
firstZeroTest.removeEnd(4);
firstZeroTest.print();

let secondZeroTest = solution();

secondZeroTest.append('123');
secondZeroTest.append('45');
secondZeroTest.removeStart(2);
secondZeroTest.removeEnd(1);
secondZeroTest.print();


}

test();