
const monthsMap = {
    "Jan":"1",
    "Feb":"2",
    "Mar":"3",
    "Apr":"4",
    "Jun":"6",
    "Jul":"7",
    "Aug":"8",
    "Sep":"9",
    "Oct":"10",
    "Nov":"11",
    "Dec":"12",

}

let previousDaysCalendar = '';
function showMonth(year,month, monthCalendar) {

    if (previousDaysCalendar !== '') {
        previousDaysCalendar.style.display = "none";
    }
    const prepId = '#month-'+year+"-"+monthsMap[month];

    const daysCalendar = document.querySelector(prepId+' table');

    const daysCalendarCaption = document.querySelector(prepId+' table caption');

    daysCalendarCaption.addEventListener('click', function(e) {

        daysCalendar.style.display = 'none';
        monthCalendar.style.display = 'grid';
    });
    // console.log(daysCalendar);
    daysCalendar.style.display = "grid";
    previousDaysCalendar = daysCalendar;
}

export {showMonth};