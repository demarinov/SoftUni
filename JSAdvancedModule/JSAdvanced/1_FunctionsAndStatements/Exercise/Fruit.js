function fruitPrice(type, qty, pricePerKilo) {

    let qtyKilos = (qty / 1000).toFixed(2);

    let result = (qtyKilos * pricePerKilo).toFixed(2);

    // if (!type || !qty || !pricePerKilo) {
    //     console.log(`I need ${result} to buy ${qtyKilos} kilograms ${type}.`);
    //     return;
    // }
    
    console.log(`I need $${result} to buy ${qtyKilos} kilograms ${type}.`);
}

function fruitPriceV2(...input) {

    let type = input[0];
    let qty = input[1];
    let pricePerKilo = input[2];

    let qtyKilos = (qty / 1000).toFixed(2);

    let result = (qtyKilos * pricePerKilo).toFixed(2);
    
    console.log(`I need $${result} to buy ${qtyKilos} kilograms ${type}.`);
}

function testFruitPrice() {
    // fruitPrice('orange', 2500, 1.80);
    // fruitPrice('apple', 1563, 2.35);

    // fruitPrice('apple', '1563');

    // fruitPrice('apple', '1', '1');

    // fruitPrice();

    // fruitPrice('apple');

    // fruitPrice('apple', 'a', '1',2);

    fruitPriceV2('orange', 2500, 1.80);
    fruitPriceV2('apple', 1563, 2.35);

    fruitPriceV2('apple', '1563');

    fruitPriceV2('apple', '1', '1');

    fruitPriceV2();

    fruitPriceV2('apple');

    fruitPriceV2('apple', 'a', '1',2);
}

testFruitPrice();

console.log("New way ...");
function solve(...input) {
    let fruit = input[0];
    let weight = Number(input[1] / 1000);
    let money = Number(input[2] * weight);
    console.log(`I need $${money.toFixed(2)} to buy ${weight.toFixed(2)} kilograms ${fruit}.`);
}

solve('orange', 2500, 1.80);
    solve('apple', 1563, 2.35);

    solve('apple', '1563');

    solve('apple', '1', '1');

    solve();

    solve('apple');

    solve('apple', 'a', '1',2);

    function fruit(...input) {
        let fruit = input[0];
        let weight = Number(input[1] / 1000);
        let money = Number(input[2] * weight);
        console.log(`I need $${money.toFixed(2)} to buy ${weight.toFixed(2)} kilograms ${fruit}.`);
    }   