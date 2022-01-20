function calculateAverageSum(strOne, strTwo, strThree) {

    let sum = strOne.length + strTwo.length + strThree.length;
    let avgSum = Math.floor(sum / 3);

    console.log(sum);
    console.log(avgSum);
}

function testCalculateAverageSum() {
    calculateAverageSum('chocolate', 'ice cream', 'cake');
    calculateAverageSum('pasta', '5', '22.3');
}

testCalculateAverageSum();