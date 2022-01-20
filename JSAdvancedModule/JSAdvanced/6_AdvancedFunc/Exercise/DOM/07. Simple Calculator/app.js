function calculator() {

    let calculate = {
        selectorOne : {},
        selectorTwo : {},
        resultSelector:{},
        init : (selector1, selector2, resultSel) => {

            this.selectorOne = document.querySelector(selector1);
            this.selectorTwo = document.querySelector(selector2);
            this.resultSelector = document.querySelector(resultSel);
        },
        add : () => {
            this.resultSelector.value = Number(this.selectorOne.value) + Number(this.selectorTwo.value);
        },
        subtract: () => {
            this.resultSelector.value = Number(this.selectorOne.value) -
             Number(this.selectorTwo.value);

        }
    }

    return calculate;
}

const calculate = calculator (); 
calculate.init ('#num1', '#num2', '#result'); 




