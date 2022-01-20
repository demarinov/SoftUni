function attachEventsListeners() {
    
    const inputDistance = document.querySelector('#inputDistance');
    const outputDistance = document.querySelector('#outputDistance');


    const inputUnits = Array.from(document.querySelectorAll('#inputUnits option'));
    const outputUnits = Array.from(document.querySelectorAll('#outputUnits option'));

    const toMetersTable = {
        'km':1000,
        'm': 1,
        'cm': 0.01,
        'mm': 0.001,
        'mi': 1609.34,
        'yrd': 0.9144,
        'ft': 0.3048,
        'in': 0.0254
    };

    // can use selectedIndex ...

    function getConvertedValuePerUnit(type) {

        for(const key of Object.keys(toMetersTable)) {

            if (type === key) {
                return toMetersTable[type];
            }
        }

        return;
    }

    const convert = document.querySelector('#convert');
    

    convert.addEventListener('click',(e) => {
        let meters;
        for(const input of inputUnits) {
    
            if (input.selected) {
                console.log('selected '+input.value);
    
                console.log(getConvertedValuePerUnit(input.value));
                meters = getConvertedValuePerUnit(input.value);
    
            }
        }
    
        let output = -1;
        outputUnits.forEach(element => {
            if (element.selected) {
                output = meters / getConvertedValuePerUnit(element.value);
            }
        });
    
        output *= inputDistance.value;
       
        if (inputDistance.value.length == 0) {
            outputDistance.value = '';
            return;
        }
        outputDistance.value = output;
    });

    
}