function add(num) {

    let state = 0;

    function changeNum(num) {
        state+=num;
        return changeNum;
    }
    
    changeNum.toString = () => state;
    
    return changeNum(num);
}

function test() {

    console.log(add(1).toString());
    console.log(add(1)(6)(-3).toString());
}
test();