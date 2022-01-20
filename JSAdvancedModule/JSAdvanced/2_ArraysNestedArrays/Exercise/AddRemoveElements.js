function addRemoveElements(arr) {

    

    let count = 0;
    let result = [];
    for(let command of arr) {
        count++;
        switch(command) {

            case 'add': result.push(count); break;
            case 'remove': result.pop();  break;
        }
    }

    if (result.length === 0) {
        console.log('Empty');
        return;
    }
    console.log(result.join('\n'));
}

function testAddRemoveElements() {

    addRemoveElements(['add', 
    'add', 
    'add', 
    'add']
    );

    addRemoveElements(['add', 
    'add', 
    'remove', 
    'add', 
    'add']
    );

    addRemoveElements(['remove', 
    'remove', 
    'remove']
    );

    addRemoveElements([]);
}

testAddRemoveElements();