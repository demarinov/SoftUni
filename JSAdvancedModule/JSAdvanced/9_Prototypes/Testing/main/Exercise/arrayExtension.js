(function solve() {

// •	last() - returns the last element of the array
// •	skip(n) - returns a new array which includes all original elements, except the first n elements; n is a Number parameter
// •	take(n) - returns a new array containing the first n elements from the original array; n is a Number parameter
// •	sum() - returns a sum of all array elements
// •	average() - returns the average of all array elements

    Array.prototype.last = function () {

        return this[this.length - 1];
    };

    Array.prototype.skip = function (n) {

        let newArr = [];
        for(let m = n; m < this.length; m++) {
            newArr.push(this[m]);    
        }

        return newArr;
    };

    Array.prototype.take = function (n) {
        let newArr = [];

        for(let m = 0; m < n; m++) {
            newArr.push(this[m]);    
        }

        return newArr;
    };

    Array.prototype.sum = function (n) {

        let sum = 0;
        for(let m = 0; m < this.length; m++) {
            sum += this[m];    
        }

        return sum;
    };

    Array.prototype.average = function () {

        return this.sum() / this.length;
    };

    // let arr = [1,2,3];

    // console.log(arr.last());
    // console.log(arr.skip(1));
    // console.log(arr.take(1));
    // console.log(arr.sum());
    // console.log(arr.average());

    // console.log(Array.prototype.hasOwnProperty('last'));

})();

