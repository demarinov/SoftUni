import * as yearly from './yearly.js';

function year() {

    const yearDays = Array.from(document.querySelectorAll('#years .calendar .day'));

    yearDays.forEach(yd => {

        yd.addEventListener('click', showMonths);
    });

    
}

function showMonths(e) {

    const yearCalendar = document.querySelector('#years table');

    let year = '';
    if (e.target.className === 'date') {
        year = e.target.textContent;
    } else if (e.target.className === 'day') {
        year = Array.from(e.target.children)[0].textContent;
    }
    yearCalendar.style.display = 'none';
    yearly.showYear(year, yearCalendar);
}

export {year};