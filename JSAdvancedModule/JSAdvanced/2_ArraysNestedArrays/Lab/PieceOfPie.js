function pieceOfPie(pie, ...flavours) {

    let startIndex = -1;
    if (pie.includes(flavours[0])) {
        startIndex = pie.indexOf(flavours[0]);
    }

    let endIndex = -1;
    if (pie.includes(flavours[1])) {
        endIndex = pie.indexOf(flavours[1]);
    }

    let result = pie.slice(startIndex, endIndex+1);

    // console.log(result);
    return result;
}

function testPieceOfPie() {
    pieceOfPie(['Pumpkin Pie',
    'Key Lime Pie',
    'Cherry Pie',
    'Lemon Meringue Pie',
    'Sugar Cream Pie'],
   'Key Lime Pie',
   'Lemon Meringue Pie'
   );

   pieceOfPie(['Apple Crisp',
   'Mississippi Mud Pie',
   'Pot Pie',
   'Steak and Cheese Pie',
   'Butter Chicken Pie',
   'Smoked Fish Pie'],
  'Pot Pie',
  'Smoked Fish Pie'
  );

}

testPieceOfPie();