class SummerCamp {

    constructor(organizer, location) {

        this.organizer = organizer;
        this.location = location;
        this.priceForTheCamp = {
            "child": 150,
            "student": 300,
            "collegian": 500
        };

        this.listOfParticipants = [];
    }

    registerParticipant(name, condition, money) {

        if (!(condition in this.priceForTheCamp)) {
            throw new Error("Unsuccessful registration at the camp.");
        }

        let participantPresent = this.listOfParticipants
            .some(p => p.name === name);
        if (participantPresent) {

            return `The ${name} is already registered at the camp.`;
        }

        money = Number(money);
        if (money < this.priceForTheCamp[condition]) {
            return `The money is not enough to pay the stay at the camp.`;
        }

        this.listOfParticipants.push({
            name,
            condition,
            'power': 100,
            'wins': 0
        });

        return `The ${name} was successfully registered.`;
    };

    unregisterParticipant(name) {

        let participantPresent = this.listOfParticipants
            .some(p => p.name === name);
        if (!participantPresent) {

            throw new Error(`The ${name} is not registered in the camp.`);
        }

        const index = this.listOfParticipants.findIndex(p => p.name === name);
        this.listOfParticipants.splice(index, 1);

        return `The ${name} removed successfully.`;
    };

    timeToPlay(typeOfGame, participant1, participant2) {

        let participantPresent = this.listOfParticipants
            .some(p => p.name === participant1);
        if (typeOfGame === 'WaterBalloonFights') {

            if (!participantPresent) {

                throw new Error(`Invalid entered name/s.`);
            }

            participantPresent = this.listOfParticipants
                .some(p => p.name === participant2);
            if (!participantPresent) {

                throw new Error(`Invalid entered name/s.`);
            }


            const participant1Obj = this.listOfParticipants
                .find(p => p.name === participant1);

            const participant2Obj = this.listOfParticipants
                .find(p => p.name === participant2);

            if (participant1Obj['condition'] !== participant2Obj['condition']) {

                throw new Error(`Choose players with equal condition.`);
            }


            if (participant1Obj['power'] > participant2Obj['power']) {

                participant1Obj['wins']++;
                return `The ${participant1} is winner in the game ${typeOfGame}.`;
            } else if (participant1Obj['power'] < participant2Obj['power']) {
                participant2Obj['wins']++;
                return `The ${participant2} is winner in the game ${typeOfGame}.`;
            } else {
                return `There is no winner.`;
            }

        } else if (typeOfGame === 'Battleship') {

            if (!participantPresent) {

                throw new Error(`Invalid entered name/s.`);
            }

            const participant1Obj = this.listOfParticipants
                .find(p => p.name === participant1);

            participant1Obj['power'] += 20;

            return `The ${participant1} successfully completed the game ${typeOfGame}.`;

        }
    };

    toString() {
        let output = '';

        output += `${this.organizer} will take ${this.listOfParticipants.length} participants on camping to ${this.location}`;

        this.listOfParticipants.sort((p1, p2) => p2.wins - p1.wins);

        this.listOfParticipants.forEach(p => {

            output += `\n${p.name} - ${p.condition} - ${p.power} - ${p.wins}`;
        });

        return output;
    }

}

function test1() {
    const summerCamp = new SummerCamp("Jane Austen", "Pancharevo Sofia 1137, Bulgaria");
    console.log(summerCamp.registerParticipant("Petar Petarson", "student", 200));
    console.log(summerCamp.registerParticipant("Petar Petarson", "student", 300));
    console.log(summerCamp.registerParticipant("Petar Petarson", "student", 300));
    console.log(summerCamp.registerParticipant("Leila Wolfe", "childd", 200));

    console.log();
}

function test2() {
    const summerCamp = new SummerCamp("Jane Austen", "Pancharevo Sofia 1137, Bulgaria");
    console.log(summerCamp.registerParticipant("Petar Petarson", "student", 300));
    // console.log(summerCamp.unregisterParticipant("Petar"));
    console.log(summerCamp.unregisterParticipant("Petar Petarson"));

    console.log();
}

function test3() {
    const summerCamp = new SummerCamp("Jane Austen", "Pancharevo Sofia 1137, Bulgaria");
    console.log(summerCamp.registerParticipant("Petar Petarson", "student", 300));
    console.log(summerCamp.timeToPlay("Battleship", "Petar Petarson"));
    console.log(summerCamp.registerParticipant("Sara Dickinson", "child", 200));
    // console.log(summerCamp.timeToPlay("WaterBalloonFights", "Petar Petarson", "Sara Dickinson"));
    console.log(summerCamp.registerParticipant("Dimitur Kostov", "student", 300));
    console.log(summerCamp.timeToPlay("WaterBalloonFights", "Petar Petarson", "Dimitur Kostov"));

    console.log();
}

function test4() {
    const summerCamp = new SummerCamp("Jane Austen", "Pancharevo Sofia 1137, Bulgaria");
    console.log(summerCamp.registerParticipant("Petar Petarson", "student", 300));
    console.log(summerCamp.timeToPlay("Battleship", "Petar Petarson"));
    console.log(summerCamp.registerParticipant("Sara Dickinson", "child", 200));
    // console.log(summerCamp.timeToPlay("WaterBalloonFights", "Petar Petarson", "Sara Dickinson"));
    console.log(summerCamp.registerParticipant("Dimitur Kostov", "student", 300));
    console.log(summerCamp.timeToPlay("WaterBalloonFights", "Petar Petarson", "Dimitur Kostov"));

    console.log(summerCamp.toString());

    console.log();
}

// test1();
// test2();
// test3();
test4();
