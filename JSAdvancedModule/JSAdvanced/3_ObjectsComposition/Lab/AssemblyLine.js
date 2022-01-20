function createAssemblyLine() {

    let assemblyObject = {};

    assemblyObject.hasClima = (obj) => {

        obj.temp = 21;
        obj.tempSettings = 21;
        obj.adjustTemp = () => {

            if (obj.temp < obj.tempSettings) {
                obj.temp += 1;
            } if (obj.temp > obj.tempSettings) {
                obj.temp -= 1;
            }
        };
    };

    assemblyObject.hasAudio = (obj) => {

        obj.currentTrack = {
            name : null,
            artist: null
        };

        obj.nowPlaying = () => {
            if (obj.currentTrack !== null) {
                console.log(`Now playing '${obj.currentTrack.name}' by ${obj.currentTrack.artist}`); 
            }
        };
    };

    assemblyObject.hasParktronic = (obj) => {

        obj.checkDistance = (distance) => {
            if (distance < 0.1) {
                console.log("Beep! Beep! Beep!");
            } else if (0.1 <= distance && distance < 0.25) {
                console.log("Beep! Beep!");
            } else if (0.25 <= distance && distance < 0.5) {
                console.log("Beep!");
            } else {
                console.log("");
            }

        };

    }; 

    return assemblyObject;
}

function testAssembly() {
    const assemblyLine = createAssemblyLine();

    const myCar = {
        make: 'Toyota',
        model: 'Avensis'
    };

    // test 1
    assemblyLine.hasClima(myCar);
console.log(myCar.temp);
myCar.tempSettings = 18;
myCar.adjustTemp();
console.log(myCar.temp);

    // test 2
    assemblyLine.hasAudio(myCar);
myCar.currentTrack = {
    name: 'Never Gonna Give You Up',
    artist: 'Rick Astley'
};
myCar.nowPlaying();

    // test 3
    assemblyLine.hasParktronic(myCar);
myCar.checkDistance(0.4);
myCar.checkDistance(0.2);

    // test 4
    console.log(myCar);

}

testAssembly();

