class Restaurant {

    constructor(budget) {

        this.budgetMoney = budget;
        this.menu = {};
        this.stockProducts = {};
        this.history = [];
    }

    loadProducts(productArray) {

        productArray.forEach(product => {
            let [productName, productQty, productTotalPrice] = product.split(' ');
            productQty = Number(productQty);
            productTotalPrice = Number(productTotalPrice);

            if (this.budgetMoney >= productTotalPrice) {
                if (!(productName in this.stockProducts)) {
                    this.stockProducts[productName] = 0;
                }

                this.stockProducts[productName] += productQty;

                this.budgetMoney -= productTotalPrice;

                this.history
                    .push(`Successfully loaded ${productQty} ${productName}`);


            } else {
                this.history
                    .push(`There was not enough money to load ${productQty} ${productName}`);

            }

        });

        return this.history.join('\n');
    }

    addToMenu(meal, neededProducts, price) {

        if (meal in this.menu) {

            return `The ${meal} is already in the our menu, try something different.`;
        }

        this.menu[meal] = {};
        this.menu[meal]['products'] = {};
        neededProducts.forEach(p => {
            let [productName, productQty] = p.split(' ');

            productQty = Number(productQty);
            this.menu[meal]['products'][productName] = productQty;
        });

        this.menu[meal]['price'] = price;
        const mealCount = Object.keys(this.menu).length;

        if (mealCount === 1) {

            return `Great idea! Now with the ${meal} we have 1 meal in the menu, other ideas?`;
        }

        if (mealCount === 0 || mealCount >= 2) {

            return `Great idea! Now with the ${meal} we have ${mealCount} meals in the menu, other ideas?`;
        }

    };

    showTheMenu() {

        const mealCount = Object.keys(this.menu).length;

        if (mealCount === 0) {

            return `Our menu is not ready yet, please come later...`;
        }

        let output = '';
        for (const mealKey in this.menu) {

            output += `${mealKey} - $ ${this.menu[mealKey]['price']}\n`;
        }

        return output.substring(0, output.length-1);
    };

    makeTheOrder(meal) {

        const foundMeal = this.menu[meal];

        if (foundMeal === undefined) {
            return `There is not ${meal} yet in our menu, do you want to order something else?`;
        }

        let haveAllProducts = true;

        for (const productName in foundMeal['products']) {

            const neededProductQty = foundMeal['products'][productName];

            if (!(productName in this.stockProducts)
                || !(this.stockProducts[productName] >= neededProductQty)) {

                haveAllProducts = false;
                break;
            }
        }

        if (haveAllProducts) {

            Object.keys(foundMeal['products'])
                .forEach(pn => {
                    const neededProductQty = foundMeal['products'][pn];
                    this.stockProducts[pn] -= neededProductQty;
                });
            this.budgetMoney += foundMeal.price;
            return `Your order (${meal}) will be completed in the next 30 minutes and will cost you ${foundMeal.price}.`;
        } else {
            return `For the time being, we cannot complete your order (${meal}), we are very sorry...`;
        }
    }
}

function test1() {
    let kitchen = new Restaurant(1000);
    console.log(kitchen
        .loadProducts(
            ['Banana 10 5', 'Banana 20 10', 'Strawberries 50 30', 'Yogurt 10 10', 'Yogurt 500 1500', 'Honey 5 50']));

    console.log();
}

test1();

function test2() {
    let kitchen = new Restaurant(1000);
    console.log(kitchen.addToMenu('frozenYogurt', ['Yogurt 1', 'Honey 1', 'Banana 1', 'Strawberries 10'], 9.99));
    console.log(kitchen.addToMenu('Pizza', ['Flour 0.5', 'Oil 0.2', 'Yeast 0.5', 'Salt 0.1', 'Sugar 0.1', 'Tomato sauce 0.5', 'Pepperoni 1', 'Cheese 1.5'], 15.55));

    console.log();
}

test2();

function test3() {
    let kitchen = new Restaurant(1000);

    kitchen.addToMenu('frozenYogurt', ['Yogurt 1', 'Honey 1', 'Banana 1', 'Strawberries 10'], 9.99);
    kitchen.addToMenu('Pizza', ['Flour 0.5', 'Oil 0.2', 'Yeast 0.5', 'Salt 0.1', 'Sugar 0.1', 'Tomato sauce 0.5', 'Pepperoni 1', 'Cheese 1.5'], 15.55);


    console.log(kitchen.showTheMenu());

    console.log();
}

test3();

function test4() {
    let kitchen = new Restaurant(1000);
    kitchen.loadProducts(['Yogurt 30 3', 'Honey 50 4', 'Strawberries 20 10', 'Banana 5 1']);
    kitchen.addToMenu('frozenYogurt', ['Yogurt 1', 'Honey 1', 'Banana 1', 'Strawberries 10'], 9.99);
    console.log(kitchen.makeTheOrder('frozenYogurt'));

}

test4();