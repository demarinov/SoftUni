function solve() {

    class Parking {
        constructor(capacity) {

            this.capacity = capacity;
            this.vehicles = [];

        };

        addCar(carModel, carNumber) {

            if (this.vehicles.length == this.capacity) {
                throw new Error('Not enough parking space.');
            }

            this.vehicles.push({ carModel, carNumber, payed: false });

            return `The ${carModel}, with a registration number ${carNumber}, parked.`;
        };

        removeCar(carNumber) {

            const idx = this.vehicles.findIndex(e => e.carNumber === carNumber);

            if (idx === -1) {
                throw new Error("The car, you're looking for, is not found.");
            }

            if (!this.vehicles[idx].payed) {
                throw new Error(`${carNumber} needs to pay before leaving the parking lot.`);
            }

            delete this.vehicles[idx];

            return `${carNumber} left the parking lot.`;
        };

        pay(carNumber) {
            const idx = this.vehicles.findIndex(e => e.carNumber === carNumber);

            if (idx === -1) {
                throw new Error(`${carNumber} is not in the parking lot.`);
            }

            if (this.vehicles[idx].payed) {
                throw new Error(`${carNumber}'s driver has already payed his ticket.`);
            }

            this.vehicles[idx].payed = true;
            return `${carNumber}'s driver successfully payed for his stay.`;
        };

        getStatistics(carNumber) {

            if (carNumber === undefined) {

                let emptySlots = this.capacity - this.vehicles.length;

                let output = `The Parking Lot has ${emptySlots} empty spots left.`;

                const partTwo = this.vehicles
                .sort((c1,c2) => {
                    return c1.carModel.localeCompare(c2.carModel);
                })
                .reduce((a, c) => {
                    a += `\n${c.carModel} == ${c.carNumber} - ${c.payed ? 'Has payed' : 'Not payed'}`;
                    return a;
                }, '');

                if (partTwo !== undefined) {
                    output += partTwo;
                }
                return output;
            }

            const idx = this.vehicles.findIndex(e => e.carNumber === carNumber);

            const car = this.vehicles[idx];
            return `${car.carModel} == ${car.carNumber} - ${car.payed ? 'Has payed' : 'Not payed'}`;
        }
    }


    // test it
    // const parking = new Parking(12);

    // console.log(parking.addCar("Volvo t600", "TX3691CA"));
    // console.log(parking.addCar("Volvo a600", "TX3691CA"));
    // console.log(parking.getStatistics());

    // console.log(parking.pay("TX3691CA"));
    // console.log(parking.removeCar("TX3691CA"));

}

solve();

