function listOfNames(arr) {

    arr.sort((n1,n2) => n1.localeCompare(n2));
    
    let count=0;
    for(const name of arr) {

        console.log(`${++count}.${name}`);
    }
}

function testListOfNames() {
    listOfNames(["John", "Bob", "Christina", "Ema"]);
    listOfNames([]);
}

testListOfNames();