function cookingByNumbers(...arr) {

    let num = Number(arr[0]);

    let result = num;
    for(let i = 1; i <= arr.length-1;i++) {

        let op = arr[i];

        switch(op) {
            case 'chop': result /= 2; break;
            case 'dice': result = Math.sqrt(result); break;
            case 'spice': result +=1; break;
            case 'bake': result *= 3; break;
            case 'fillet':result -= (result * 0.20); break;
        }

        console.log(result);
    }


}

function testCookingByNumbers() {

    cookingByNumbers('32', 'chop', 'chop', 'chop', 'chop', 'chop');
    cookingByNumbers('9', 'dice', 'spice', 'chop', 'bake', 'fillet');

}

testCookingByNumbers();