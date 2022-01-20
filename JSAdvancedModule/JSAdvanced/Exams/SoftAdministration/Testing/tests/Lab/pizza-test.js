const expect = require('chai').expect;
const pizzUni = require('../../main/Lab/pizza').pizzUni;


describe("Tests …", function () {
    describe("makeAnOrder", function () {

        it("1. Order pizza - error at least 1 pizza to order", function () {
            const expected = 'You must order at least 1 Pizza to finish the order.';

            expect(pizzUni.makeAnOrder.bind(pizzUni,{})).to.throw(expected);

        });

        it("2. Order pizza - 1 pizza no drink", function () {
            const expected = 'You just ordered Calcone';

            let res = pizzUni.makeAnOrder({ orderedPizza: 'Calcone' });
            expect(expected).to.be.equal(res);

        });

        it("3. Order pizza - 1 pizza 1 drink", function () {
            const expected = 'You just ordered Calcone and Cola.';

            let res = pizzUni.makeAnOrder({ orderedPizza: 'Calcone', orderedDrink: 'Cola' });
            expect(expected).to.be.equal(res);

        });



    });

    describe("getRemainingWork", function () {

        it("1. remaining pizzas - 2 pizzas not ready", function () {
            const expected = 'The following pizzas are still preparing: Calcone, Diablo.';

            let statusArr = [{ pizzaName: 'Calcone', status: 'preparing' }
                , { pizzaName: 'Diablo', status: 'preparing' }];
            let res = pizzUni.getRemainingWork(statusArr);
            expect(expected).to.be.equal(res);

        });

        it("2. remaining pizzas - 2 pizzas all ready", function () {
            const expected = 'All orders are complete!';

            let statusArr = [{ pizzaName: 'Calcone', status: 'ready' }
                , { pizzaName: 'Diablo', status: 'ready' }];
            let res = pizzUni.getRemainingWork(statusArr);
            expect(expected).to.be.equal(res);

        });

        it("3. remaining pizzas - 2 pizzas 1 not ready 1 ready", function () {
            const expected = 'The following pizzas are still preparing: Diablo.';


            let statusArr = [{ pizzaName: 'Calcone', status: 'ready' }
                , { pizzaName: 'Diablo', status: 'preparing' }];
            let res = pizzUni.getRemainingWork(statusArr);
            expect(expected).to.be.equal(res);

        });



    });

    describe("orderType", function () {

        it("1. order type and sum - delivery type", function () {
            const expected = 10;


            let res = pizzUni.orderType(10, 'Delivery');
            expect(expected).to.be.equal(res);

        });

        it("2. order type and sum - carry out type", function () {
            const expected = 9;


            let res = pizzUni.orderType(10, 'Carry Out');
            expect(expected).to.be.equal(res);

        });



    });

});
