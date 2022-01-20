function arrayWithDelmiter(arr, delimiter) {

    console.log(arr.join(delimiter));
}

function testArrayWithDelimiter() {
    arrayWithDelmiter(['One', 
    'Two', 
    'Three', 
    'Four', 
    'Five'], 
    '-'
    );

    arrayWithDelmiter(['How about no?', 
    'I',
    'will', 
    'not', 
    'do', 
    'it!'], 
    '_'
    );
}

testArrayWithDelimiter();