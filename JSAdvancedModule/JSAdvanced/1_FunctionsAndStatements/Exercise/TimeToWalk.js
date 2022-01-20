function timeToWalk(steps, meters, speed) {

    let kilometers = Number(meters / 1000);
    let distance = Number(kilometers * steps);
    let kmPerSecond = Number(speed / 3600);
    
    let seconds = distance / kmPerSecond;

    let breakInSeconds = 0;
    for(let i = 1; i<meters*steps;i++) {

        if (i % 500 == 0) {
            breakInSeconds += 60;
        }
    }

    let result = Math.round(seconds + breakInSeconds);

    let secs = result % 60;
    let mins = ((result - secs) / 60) % 60;
    let hours = ((result - secs - mins*60) / 60) % 60;
    
    function addZero(value) {

        if (value >= 0 && value <= 9) {
            return '0' + value;
        }

        return value;
    }

    let hoursStr = addZero(hours);

    let minsStr = addZero(mins);

    let secsStr = addZero(secs);

    console.log(`${hoursStr}:${minsStr}:${secsStr}`);
}

function testTimeToWalk() {
    timeToWalk(4000, 0.60, 5);
    timeToWalk(2564, 0.70, 5.5);
}

testTimeToWalk();