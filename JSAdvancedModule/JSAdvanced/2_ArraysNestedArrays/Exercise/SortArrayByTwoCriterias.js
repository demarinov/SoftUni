function sortArrayByCriteria(arr) {

    arr.sort((a,b) => {
        const res = a.length-b.length;

        return res == 0 ? a.localeCompare(b) : res;
    });

    console.log(arr.join('\n'));
}

function testSortArrayByCriteria() {

    sortArrayByCriteria(['alpha', 
    'beta', 
    'gamma']
    );

    console.log();
    sortArrayByCriteria(['Isacc', 
    'Theodor', 
    'Jack', 
    'Harrison', 
    'George']
    );

    console.log();
    sortArrayByCriteria(['test', 
    'Deny', 
    'omen', 
    'Default']
    );
}

testSortArrayByCriteria();