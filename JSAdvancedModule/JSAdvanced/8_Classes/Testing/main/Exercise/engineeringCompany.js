function registerCar(inputArr) {

    let carBrands = new Map();
    for(const input of inputArr) {
        const carData = input.split(' \| ');

        const brand = carData[0];
        const model = carData[1];
        const qty = Number(carData[2]);

        if (!carBrands.has(brand)) {
            carBrands.set(brand, new Map());
        }

        const carModels = carBrands.get(brand);

        if (!carModels.has(model)) {
            carModels.set(model, 0);
        }

        carModels.set(model, carModels.get(model)+qty);

    }

    let output = '';
    // {carBrand}
    // ###{carModel} -> {producedCars}
  
    carBrands.forEach((v,k) => {
        output += `${k}\n`;    
        v.forEach((cv,ck) => {
            output += `###${ck} -> ${cv}\n`;
        })
    });

    output = output.substring(0, output.length-1);
    console.log(output);

    return output;
}

function test() {

    registerCar(['Audi | Q7 | 1000',
    'Audi | Q6 | 100',
    'BMW | X5 | 1000',
    'BMW | X6 | 100',
    'Citroen | C4 | 123',
    'Volga | GAZ-24 | 1000000',
    'Lada | Niva | 1000000',
    'Lada | Jigula | 1000000',
    'Citroen | C4 | 22',
    'Citroen | C5 | 10']
    );
}

test();