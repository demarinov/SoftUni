function lowestPrice(input) {

    let result = [];
    let removedTowns = {};
    for (const townData of input) {
        let [town, product, price] = townData.split(' | ');

        price = Number(price);

        // each product with its lowest price and the town at which the product 
        // is sold at that price. 
        // If two towns share the same lowest price, print the one that was entered first
        let foundProduct = result.find((a) => a.product === product);

        if (foundProduct !== undefined) {

            if (foundProduct.price > price || foundProduct.town === town) {
                

                if (removedTowns[product] !== undefined 
                    && removedTowns[product]['price'] === price) {
                    foundProduct.town = removedTowns[product]['town'];
                } else {
                    removedTowns[product] = {'price':foundProduct.price,
                    'town':foundProduct.town};
                    foundProduct.town = town;
                }


                foundProduct.price = price;
            }
        } else {
            result.push({town, product, price});
        } 

    }

    result.forEach((t) => console.log(`${t.product} -> ${t.price} (${t.town})`));
}

function test() {
    lowestPrice(['Sample Town | Sample Product | 1000',
    'Sample Town | Orange | 2',
    'Sample Town | Peach | 1',
    'Sofia | Orange | 3',
    'Sofia | Peach | 2',
    'New York | Sample Product | 1000.1',
    'New York | Burger | 10']
    );

    console.log();

    lowestPrice(['Sofia City | Audi | 100000',
        'Sofia City | BMW | 100000',
        'Sofia City | Mitsubishi | 10000',
        'Sofia City | Mercedes | 10000',
        'Sofia City | NoOffenseToCarLovers | 0',
        'Mexico City | Audi | 1000',
        'Mexico City | BMW | 99999',
        'New York City | Mitsubishi | 10000',
        'New York City | Mitsubishi | 1000',
        'Mexico City | Audi | 100000',
        'Washington City | Mercedes | 1000']
    );


}

test();

function foo(arr) {
    const data = arr
        .map(x => x.split(" | "))
        .reduce((a, v) => {
            const [town, product, price] = v.map(x => (isNaN(x) ? x : Number(x)))
            a[product] = a[product] || { price, town }
            if (a[product].price > price || a[product].town === town) {
                a[product] = { price, town }
            }
            return a
        }, {})

    return `${Object.entries(data)
        .map(([name, product]) => `${name} -> ${product.price} (${product.town})`)
        .join("\n")}`;
}