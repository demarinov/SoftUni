function roadRadar(speed, area) {

    let speedDiff;
    let speedLimit;
    let overLimit = false;
    switch(area) {

        case 'motorway': {  
            speedLimit = 130;
            if (speed > speedLimit) {
                overLimit = true;
                speedDiff = speed - speedLimit;
            }
            break;
        }
        case 'interstate':{ 
            speedLimit = 90;
            if (speed > speedLimit) {
                overLimit = true;
                speedDiff = speed - speedLimit;
            }
            break;
        }
        case 'city':{
            speedLimit = 50;
            if (speed > speedLimit) {
                overLimit = true;
                speedDiff = speed - speedLimit;
            }
            break;
        } 
        case 'residential': { 
            speedLimit = 20;
            if (speed > speedLimit) {
                overLimit = true;
                speedDiff = speed - speedLimit;
            }
            break;
        }
    }

    if (overLimit) {

        let status;
        if (speedDiff <= 20) {
            status = 'speeding';
        } else if (speedDiff <= 40) {
            status = 'excessive speeding';
        } else {
            status = 'reckless driving';
        }

        console.log(`The speed is ${speedDiff} km/h faster than the allowed speed of ${speedLimit} - ${status}`);
    } else {
        console.log(`Driving ${speed} km/h in a ${speedLimit} zone`);
    }

}

function testRoadRadar() {
    roadRadar(40, 'city');
    roadRadar(21, 'residential');
    roadRadar(120, 'interstate');
    roadRadar(200, 'motorway');
}

testRoadRadar();