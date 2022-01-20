function carFactory(input) {

    let car = {};

    car.model = input['model'];

    if (input.power >= 0 && input.power <=90) {

        car.engine = {power: 90, volume: 1800};
    } else if (input.power > 90 && input.power <= 120) {
        car.engine = {power: 120, volume: 2400};
    } else if (input.power >= 200) {
        car.engine = {power: 200, volume: 3500};
    }

    car.carriage = {type: input.carriage, color: input.color};
    
    if (input.wheelsize % 2 == 0) {
        car.wheels = [input.wheelsize-1,input.wheelsize-1,input.wheelsize-1,input.wheelsize-1];
    } else {
        car.wheels = [input.wheelsize,input.wheelsize,input.wheelsize,input.wheelsize]; 
    }

    return car;
}

function test() {

    console.log(carFactory({ model: 'VW Golf II',
    power: 90,
    color: 'blue',
    carriage: 'hatchback',
    wheelsize: 14 }
  ));

    console.log(carFactory({ model: 'Opel Vectra',
    power: 110,
    color: 'grey',
    carriage: 'coupe',
    wheelsize: 17 }
  ));

    console.log(carFactory({ model: 'Opel Vectra',
    power: 200,
    color: 'grey',
    carriage: 'coupe',
    wheelsize: 17 }
  ));
}

test();