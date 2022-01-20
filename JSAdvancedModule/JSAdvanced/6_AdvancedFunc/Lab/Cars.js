function solve(commandArr) {

    let obj = {

        // better object hash
        valueList: [],
        create: function(val) {
            this.valueList.push({name:val});
        },
        create_inherit: function (valOne,valTwo) {
            
            const foundObj = this.valueList.find((a) => a.name === valTwo);
        
            const newObj = Object.create(foundObj);
            newObj.name = valOne; 
            this.valueList.push(newObj);
        },
        set: function(...valArr) {
            const name = valArr[0];
            const key = valArr[1];
            const val = valArr[2];

            this.valueList.find((a) => a.name === name)[key] = val;
        },
        print: function (val) {
            const entry = []
            const obj = this.valueList.find((a) => a.name === val);
            for (const key in obj) {
                if (key !== 'name') {
                    entry.push(`${key}:${obj[key]}`);
                }
            }
            console.log(entry.join(","))
        }
    }

    for(const command of commandArr) {
        const arr = command.split(' ');

        if (arr.length > 2) {
            if (arr[0] === 'create') {
                obj['create_inherit'](arr[1],arr[3]);
            } else if (arr[0] === 'set') {
                obj['set'](arr[1],arr[2],arr[3]);
            }
        } else {
            obj[arr[0]](arr[1]);
        }
    }
    
}

function test() {
    // solve(['create c1',
    // 'create c2 inherit c1',
    // 'set c1 color red',
    // 'set c2 model new',
    // 'print c1',
    // 'print c2']
    // );

    solve(['create pesho','create gosho inherit pesho','create stamat inherit gosho','set pesho rank number1','set gosho nick goshko','print stamat']);
}

test();