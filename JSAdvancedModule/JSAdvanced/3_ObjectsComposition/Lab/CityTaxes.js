function cityTaxes(name, population, treasury) {

    let cityObj = {

        name,
        population,
        treasury,
        taxRate : taxRate = 10,
        collectTaxes: function collectTaxes() {

            this.treasury = Math.round(this.treasury + (this.population * this.taxRate));
        },
        applyGrowth : function applyGrowth(percentage) {
            this.population = Math.round(this.population + (this.population * percentage/100));
        },
        applyRecession : function applyRecession(percentage) {
            this.treasury = Math.round(this.treasury - (this.treasury * percentage/100));
        }
    };

    return cityObj;
}


function testCityTaxes(){
    const city = cityTaxes('Tortuga',
    7000,
    15000
    );

    console.log(city);

    const city2 =
  cityTaxes('Tortuga',
  7000,
  15000);
city2.collectTaxes();
console.log(city2.treasury);
city2.applyGrowth(5);
console.log(city2.population);

}

testCityTaxes();