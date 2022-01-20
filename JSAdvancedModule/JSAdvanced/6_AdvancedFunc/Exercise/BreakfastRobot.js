function solution() {

    let meals = {
        "apple": {
            "carbohydrate": 1,
            "flavour": 2
        },
        "lemonade": {
            "carbohydrate": 10,
            "flavour": 20
        },
        "burger": {
            "carbohydrate": 5,
            "fat": 7,
            "flavour": 3
            
        },
        "eggs": {
            "protein": 5,
            "fat": 1,
            "flavour": 1
            
        },
        "turkey": {
            "protein": 10,
            "carbohydrate": 10,
            "fat": 10,
            "flavour": 10
            
        }

    };

    let elementStore = {
        "protein": 0,
        "carbohydrate": 0,
        "flavour": 0,
        "fat": 0
    };

    function prepareMeal(commandStr) {

        const commands = {

            "restock": function (arr) {

                let microElement = arr[1];
                let qty = Number(arr[2]);

                elementStore[microElement] += qty;
                return "Success";
            },
            "prepare": function (arr) {

                let recipe = arr[1];
                let qty = Number(arr[2]);
                let expectedCarbs = 0;
                let expectedFlavour = 0;
                let expectedProtein = 0;
                let expectedFat = 0;


                switch (recipe) {
                    case "apple":
                    case "lemonade":
                        expectedCarbs = qty * meals[recipe]['carbohydrate'];
                        expectedFlavour = qty * meals[recipe]['flavour'];
                        

                        if (expectedCarbs > elementStore['carbohydrate']) {
                            return "Error: not enough carbohydrate in stock";
                        } else if (expectedFlavour > elementStore['flavour']) {
                            return "Error: not enough flavour in stock";
                        } else {
                            elementStore['carbohydrate'] -= expectedCarbs;
                            elementStore['flavour'] -= expectedFlavour;
                            
                            return "Success";
                        }
                        break;
                    case "burger":
                        expectedCarbs = qty * meals[recipe]['carbohydrate'];
                        expectedFlavour = qty * meals[recipe]['flavour'];
                        expectedFat = qty * meals[recipe]['fat'];

                        if (expectedCarbs > elementStore['carbohydrate']) {
                                return "Error: not enough carbohydrate in stock";
                        } else if (expectedFlavour > elementStore['flavour']) {
                            return "Error: not enough flavour in stock";
                        } else if (expectedFat > elementStore['fat']) {
                            return "Error: not enough fat in stock";
                        } else {
                            elementStore['carbohydrate'] -= expectedCarbs;
                            elementStore['flavour'] -= expectedFlavour;
                            elementStore['fat'] -= expectedFat;
                            return 'Success';
                        }
                        break;
                    case "eggs":
                        expectedProtein = qty * meals[recipe]['protein'];
                        expectedFlavour = qty * meals[recipe]['flavour'];
                        expectedFat = qty * meals[recipe]['fat'];

                        if (expectedProtein > elementStore['protein']) {
                            return "Error: not enough protein in stock";
                        } else if (expectedFlavour > elementStore['flavour']) {
                            return "Error: not enough flavour in stock";
                        } else if (expectedFat > elementStore['fat']) {
                            return "Error: not enough fat in stock";
                        } else {
                            elementStore['protein'] -= expectedProtein;
                            elementStore['flavour'] -= expectedFlavour;
                            elementStore['fat'] -= expectedFat;
                            return "Success";
                        }
                        break;  
                    case "turkey":
                        expectedProtein = qty * meals[recipe]['protein'];
                        expectedFlavour = qty * meals[recipe]['flavour'];
                        expectedFat = qty * meals[recipe]['fat'];
                        expectedCarbs = qty * meals[recipe]['carbohydrate'];

                        if (expectedProtein > elementStore['protein']) {
                            return "Error: not enough protein in stock";
                        } else if (expectedCarbs > elementStore['carbohydrate']) {
                            return "Error: not enough carbohydrate in stock";
                        }  else if (expectedFat > elementStore['fat']) {
                            return "Error: not enough fat in stock";
                        } else if (expectedFlavour > elementStore['flavour']) {
                            return "Error: not enough flavour in stock";
                        }  else {
                            elementStore['protein'] -= expectedProtein;
                            elementStore['flavour'] -= expectedFlavour;
                            elementStore['fat'] -= expectedFat;
                            elementStore['carbohydrate'] -= expectedCarbs;

                            return "Success";
                        }
                        break;     
                }
            },
            "report": function () {
                let report = '';
                report += `protein=${elementStore['protein']} `;
                 report += `carbohydrate=${elementStore['carbohydrate']} `;
                 report += `fat=${elementStore['fat']} `;
                 report += `flavour=${elementStore['flavour']}`;
                return report;
            }
        };

        let arr = commandStr.split(' ');
        return commands[arr[0]](arr);
    }

    return prepareMeal;
}

function test() {
    let manager = solution (); 
    console.log (manager ("restock flavour 50")); // Success 
    console.log (manager ("prepare lemonade 4")); // Error: not enough carbohydrate in stock 
    console.log(manager('restock carbohydrate 10'));
    console.log(manager('restock flavour 10'));
    console.log(manager('prepare apple 1'));
    console.log(manager('restock fat 10'));
    console.log(manager('prepare burger 1'));
    console.log(manager('report'));

    console.log();

    

}

test();

function test2() {
    let manager = solution ();
    console.log(manager('prepare turkey 1'));
    console.log(manager('restock protein 10'));
    console.log(manager('prepare turkey 1'));
    console.log(manager('restock carbohydrate 10'));
    console.log(manager('prepare turkey 1'));
    console.log(manager('restock fat 10'));
    console.log(manager('prepare turkey 1'));
    console.log(manager('restock flavour 10'));
    console.log(manager('prepare turkey 1'));
    console.log(manager('report'));
}

test2();