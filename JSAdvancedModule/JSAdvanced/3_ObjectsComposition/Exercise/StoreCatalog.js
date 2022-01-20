function storeCatalog(input) {

    // “{productName} : {productPrice}”

    let result = [];
    for(const product of input) {

        let [name, price] = product.split(' : ');
        price = Number(price);

        result.push({name, price});
    }

    result.sort((p1, p2) => p1.name.localeCompare(p2.name));

    let firstLetter;
    for(const sortedProduct of result) {
        
        let currentFirstLetter = sortedProduct.name.substring(0,1);

        if (firstLetter !== currentFirstLetter) {
            firstLetter = currentFirstLetter;
            console.log(firstLetter);
        } 

        console.log(`  ${sortedProduct.name}: ${sortedProduct.price}`);

    }

}

function test() {
    storeCatalog(['Appricot : 20.4',
    'Fridge : 1500',
    'TV : 1499',
    'Deodorant : 10',
    'Boiler : 300',
    'Apple : 1.25',
    'Anti-Bug Spray : 15',
    'T-Shirt : 10']
    );

    storeCatalog(['Banana : 2',
    'Rubic\'s Cube : 5',
    'Raspberry P : 4999',
    'Rolex : 100000',
    'Rollon : 10',
    'Rali Car : 2000000',
    'Pesho : 0.000001',
    'Barrel : 10']
    );
}

test();