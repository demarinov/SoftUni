class Textbox {
    
    constructor(selector, regex) {
        this.selector  =selector;
        this.regex = regex;
        this._elements = document.querySelectorAll(this.selector);

        this._elements[0].parentNode.addEventListener('change', (e) => {
            this._value = e.target.value; 
        });
        this._elements[0].parentNode.addEventListener('change', function (e) {

            for(const nextElement of Array.from(e.target.parentNode.children)) {
                            nextElement.value = e.target.value;
            }
        });
        this._invalidSymbols = regex;
    }

    set value(val) {
        this._value = val;

        for(const element of Array.from(this._elements)) {
            element.value = val;
        }
    }

    get value() {
        return this._value;
    }

    get elements() {
        return this._elements;
    };

    isValid() {
        const rg = new RegExp(this.regex);

        for(const element of Array.from(this._elements)) {

            if (element.value === '' || rg.test(element.value)) {
                return false;
            }
        }

        return true;
    }
}

let textbox = new Textbox(".textbox",/[^a-zA-Z0-9]/);
let inputs = document.getElementsByClassName('.textbox');

// inputs.addEventListener('click',function(){console.log(textbox.value);});
