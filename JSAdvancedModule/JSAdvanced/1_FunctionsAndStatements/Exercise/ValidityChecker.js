function validatePoints(x1,y1,x2,y2) {

    let isValid = true;
    // x1,y1 to 0,0
    let result = Math.sqrt((x1**2 + y1**2));

    if (!isValidInt(result)) {
        console.log(`{${x1}, ${y1}} to {0, 0} is invalid`);
    } else {
        console.log(`{${x1}, ${y1}} to {0, 0} is valid`);
    }

    // x2,y2 to 0,0
    result = Math.sqrt((x2**2 + y2**2));

    if (!isValidInt(result)) {
        console.log(`{${x2}, ${y2}} to {0, 0} is invalid`);
    } else {
        console.log(`{${x2}, ${y2}} to {0, 0} is valid`);
    }

    // x1,y1 to x2,y2
    result = Math.sqrt(((x1-x2)**2 + (y1-y2)**2));

    if (!isValidInt(result)) {
        console.log(`{${x1}, ${y1}} to {${x2}, ${y2}} is invalid`);
    } else {
        console.log(`{${x1}, ${y1}} to {${x2}, ${y2}} is valid`);
    }


    function isValidInt(result) {
        if (String(result).includes('.')) {
            return false;
        } else {
            return true;
        }
    }
}

function testValidatePoints() {
    validatePoints(3,0,0,4);
    validatePoints(2,1,1,1);
}

testValidatePoints();