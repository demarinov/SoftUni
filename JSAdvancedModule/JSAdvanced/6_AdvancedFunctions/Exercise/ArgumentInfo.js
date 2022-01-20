function solve(...args) {

    let typeObj = {

    };
    for(const arg of args) {
        const type = typeof arg;
        console.log(`${type}: ${arg}`);

        if (typeObj[type] === undefined) {
            typeObj[type] = 1;
        } else {
            typeObj[type] += 1;
        }
    }

    for(const entry of Object.entries(typeObj).sort((a,b) => b[1] - a[1])) {
        console.log(`${entry[0]} = ${entry[1]}`);
    }
}

function test() {

    solve('cat', 42, function () { console.log('Hello world!'); });
    console.log();
    solve('cat', 42, function () { console.log('Hello world!'); }, 'cat', 52);
}

test();