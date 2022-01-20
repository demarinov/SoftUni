function heroInventory(input) {

    // array of heroes
    // hero format
    // “{heroName} / {heroLevel} / {item1}, {item2}, {item3}...” 

    let result = [];

    for(const heroData of input) {

        let [name, level, items] = heroData.split(' / ');

        items = items ? items.split(', '): [];

        result.push({name, 'level': Number(level), items});
    }

    console.log(JSON.stringify(result));

}

function test() {
    heroInventory(['Isacc / 25 / Apple, GravityGun',
    'Derek / 12 / BarrelVest, DestructionSword',
    'Hes / 1 / Desolator, Sentinel, Antara']
    );

    heroInventory(['Jake / 1000 / Gauss, HolidayGrenade']);
}

test();