import * as monthly from './monthly.js';

let previousMonthCalendar = '';
function showYear(year, yearCalendar) {

    if (previousMonthCalendar !== '') {
        previousMonthCalendar.style.display = 'none';
    }
    const prepId = '#year-'+year;
    const monthCalendar = document.querySelector(prepId +' table'); 

    monthCalendar.style.display = 'grid';
    previousMonthCalendar = monthCalendar;

    const month = '';

    const months = Array.from(document.querySelectorAll(prepId+' tr .day'));

    months.forEach(m => {

        m.addEventListener('click', function(e) {

            showDays(e, year);
        });
    });

    const monthCalendarCaption = document.querySelector(prepId +' table caption');

    monthCalendarCaption.addEventListener('click', function(e) {

        monthCalendar.style.display = 'none';
        yearCalendar.style.display = 'grid';
    });
}

function showDays(e, year) {

    let month = '';
    if (e.target.className === 'date') {
        month = e.target.textContent;
    } else if (e.target.className === 'day') {
        month = Array.from(e.target.children)[0].textContent;
    }

    const prepId = '#year-'+year;
    const monthCalendar = document.querySelector(prepId +' table'); 
    monthCalendar.style.display = 'none';
    monthly.showMonth(year,month, monthCalendar);
}

export {showYear};