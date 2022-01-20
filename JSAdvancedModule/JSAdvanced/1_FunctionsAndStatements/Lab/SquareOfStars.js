function squareOfStars(...arg) {


    if (arg.length == 0) {
       size = 5;     
    } else {
        var size = Number(arg[0]);
    }
    
    for (let row = 0; row < size; row++) {
        for(let col = 0; col < size; col++) {
            process.stdout.write('* ');
        }
        console.log();
    }

}

function testSquareOfStars() {
    squareOfStars(1);
    squareOfStars(2);
    squareOfStars(5);

    squareOfStars();
}

testSquareOfStars();