function wordsUpperCase(str) {

    let re = /(\w+)/g;
    let arr = str.match(re);

    let result = arr.join(', ').toUpperCase();
    console.log(result);
}

function testWordsUpperCase() {
    wordsUpperCase('Hi, how are you?');
    wordsUpperCase('hello');
}

testWordsUpperCase();