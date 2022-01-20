function area() {
    return Math.abs(this.x) * Math.abs(this.y);
}

function vol() {

    return Math.abs(this.x) * Math.abs(this.y) * Math.abs(this.z);
}

function solve(area, vol, strArr) {

    let arr = JSON.parse(strArr);

    let result = [];

    for(const input of arr) {

        let areaRes = area.call(input);
        let volRes = vol.call(input);
        result.push({"area":areaRes, "volume":volRes});
    }

    return result;
}

function test() {

    console.log(solve(area, vol, 
        '[{"x":"1","y":"2","z":"10"},\
    {"x":"7","y":"7","z":"10"},\
    {"x":"5","y":"2","z":"10"}]'));

    console.log(solve(area, vol, '[\
        {"x":"10","y":"-22","z":"10"},\
        {"x":"47","y":"7","z":"-5"},\
        {"x":"55","y":"8","z":"0"},\
        {"x":"100","y":"100","z":"100"},\
        {"x":"55","y":"80","z":"250"}]'));
}

test();