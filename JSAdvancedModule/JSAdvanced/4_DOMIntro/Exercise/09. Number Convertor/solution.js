function solve() {

    const button = document.querySelector('#container button');

    button.addEventListener('click', onClick);

    function onClick() {

        const inputNumber = document.querySelector('#input');

        const options = Array.from(document.querySelectorAll('#selectMenuTo option'));

        let op = '';
        for(const option of options) {
            if (option.selected) {
                op = option.value;
                break;
            }
        }

        const convert = {
            'binary': (num) => {

                let arr = [];

                // return Number(num).toString(2);

                let div = num;

                if (div == 0) {
                    return '0';
                }

                while(div > 0) {

                    const rem = div % 2;
                    div = Math.floor(div / 2);
                    arr.push(rem);
                }

                return arr.reverse().join('');
            },
            'hexadecimal': (num) => {
                let arr = [];

                //  return Number(num).toString(16).toUpperCase();
                const hexTable = {
                    10: 'A',
                    11: 'B',
                    12: 'C',
                    13: 'D',
                    14: 'E',
                    15: 'F'
                }

                let div = num;

                if (div == 0) {
                    return '0';
                }
                while(div > 0) {
                    
                    let rem = div % 16;
                    div = Math.floor(div / 16);
                    if (rem >= 10) {
                        rem = hexTable[rem];
                    }
                    arr.push(rem);
                }

                return arr.reverse().join('');
            }
        };

        let resultedNum = convert[op](inputNumber.value);

        const result = document.querySelector('#result');
        result.value = resultedNum;
    }

    const selectedMenuTo = document.querySelector('#selectMenuTo');

    let optionToOne = Array.from(selectedMenuTo.getElementsByTagName('option'))[0];

    if (optionToOne.value.length == 0) { 
        optionToOne.value = 'binary';
        optionToOne.textContent = optionToOne.value.charAt(0).toUpperCase()+optionToOne.value.substring(1);
        const selectOptionToTwo = document.createElement('option');
        selectOptionToTwo.value = 'hexadecimal';
        selectOptionToTwo.textContent = 
        selectOptionToTwo.value.charAt(0).toUpperCase() + selectOptionToTwo.value.substring(1);
        selectedMenuTo.appendChild(selectOptionToTwo);
    }

}