function previousDay(year, month, day) {

    const date = new Date();
    date.setDate(day);
    date.setFullYear(year);
    date.setMonth(month-1);


    let newDateMillis = date.getTime() - 86400000;
    date.setTime(newDateMillis);

    console.log(`${date.getFullYear()}-${date.getMonth()+1}-${date.getDate()}`);
}

function test() {
    previousDay(2016, 9, 30);
    previousDay(2016, 10, 1);
}

test();