function findCircleArea(arg) {

    let area;
    if (typeof arg === 'number') {
        area = (Math.PI * arg * arg).toFixed(2);
        console.log(area);
    } else {
        console.log(`We can not calculate the circle area, because we receive a ${typeof arg}.`);
    }
}

function testFindCircleArea() {

    findCircleArea(5);
    findCircleArea('name');
}

testFindCircleArea();