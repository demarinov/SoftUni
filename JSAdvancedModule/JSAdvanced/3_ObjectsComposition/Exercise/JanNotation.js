function postFixCalc(input) {

    let nums = [];

    const ops = {
        "+": (a,b) => {
            return a+b;
        },
        "-": (a,b) => {
            return a-b;
        },
        "*": (a,b) => {
            return a * b;
        },
        "/": (a,b) => {
            return a / b;
        }
    };

    for(const el of input) {

        if (typeof el === 'number') {
            nums.push(el);
        } else if (typeof el === 'string') {
            
            let num2 = nums.pop();
            let num1 = nums.pop();

            if (num1 === undefined || num2 === undefined) {
                console.log('Error: not enough operands!');
                return;
            }

            let result = ops[el](num1, num2);

            nums.push(result);
        } 
    }

    if (nums.length === 1) {
        console.log(nums[0]);
    } else {
        console.log('Error: too many operands!');
    }
}

function test() {
    postFixCalc([3,
        4,
        '+']
       );

    postFixCalc([5,
        3,
        4,
        '*',
        '-']
       );   
    postFixCalc([7,
        33,
        8,
        '-']
       );   

    postFixCalc([15,
        '/']
       );   
}

test();