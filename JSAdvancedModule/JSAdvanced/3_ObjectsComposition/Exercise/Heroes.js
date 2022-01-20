function heroes() {
    
    return {
        mage: (name) => {
            let state = {
                name,
                health: 100,
                mana: 100
            };



            const fighter = (state) => {
                
                return {
                    cast: (spell) => {
                        state.mana--;
                        console.log(`${state.name} cast ${spell}`);
                    }
                };
            };

            return Object.assign(state, fighter(state));
        },
        fighter: (name) => {
            let state = {
                name,
                health: 100,
                stamina: 100
            };

            const fighter = (state) => {

                return {
                    fight: () => {
                        state.stamina--;
                        console.log(`${state.name} slashes at the foe!`);
                    }
                };
            };

            return Object.assign(state, fighter(state));
        }
    };
}

function test() {

    let create = heroes();
const scorcher = create.mage("Scorcher");
scorcher.cast("fireball")
scorcher.cast("thunder")
scorcher.cast("light")

const scorcher2 = create.fighter("Scorcher 2");
const buddy = scorcher2.fight;
buddy();

console.log(scorcher2.stamina);
console.log(scorcher.mana);

}

test();