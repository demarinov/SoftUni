function juicy(inputArr) {

    let juiceStore = new Map();
    const bottles = new Map();
    for(const input of inputArr) {
        
        const juiceData = input.split(' => ');

        const juice = juiceData[0];
        const qty = Number(juiceData[1]);

        if (!juiceStore.has(juice)) {
            juiceStore.set(juice, 0);
        } 


        juiceStore.set(juice,juiceStore.get(juice) + qty);

        const bottleQty = Math.floor(juiceStore.get(juice) / 1000);
        const remQty = juiceStore.get(juice) % 1000;


        if (bottleQty > 0) {

            if (!bottles.has(juice)) {
                bottles.set(juice, 0);
            }

            bottles.set(juice, bottles.get(juice) + bottleQty);
        } 
        
        if (remQty > 0) {
            juiceStore.set(juice, remQty);
        }
        
        

    }

    console.log(bottles);
    let output = '';
    bottles.forEach((v,k) => {
       
        const name = k;
        const value = v;

        output += `${name} => ${value}\n`;
    });

    return output.substring(0, output.length-1);
}

function test() {


    juicy(['Orange => 2000',
    'Peach => 1432',
    'Banana => 450',
    'Peach => 600',
    'Strawberry => 549']
    );

    juicy(['Kiwi => 234',
'Pear => 2345',
'Watermelon => 3456',
'Kiwi => 4567',
'Pear => 5678',
'Watermelon => 6789']);


}

test();