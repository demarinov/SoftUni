import * as yearSelection from './yearSelection.js';

function main() {
    console.log('App hello!');
    // // hide other sections except year

    const monthCalendars = Array.from(document.querySelectorAll('.monthCalendar table'));
    monthCalendars.forEach(mc => {
        mc.style.display = 'none';
    });

    const dayCalendars = Array.from(document.querySelectorAll('.daysCalendar table'));

    dayCalendars.forEach(dc => {
        dc.style.display = 'none';
    });

    yearSelection.year();

}

main();