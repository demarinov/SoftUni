function factory(libFunc, orderArr) {


    let result = [];
    for(const order of orderArr) {
        let {template, parts} = order;


        let resultObj = Object.assign({}, order.template);

        for(const part of parts) {
            resultObj[part] = libFunc[part];
        }

        result.push(resultObj);
    }
    
    
    return result;
}


function testObjectFactory() {

    function test1() {
        const library = {
            print: function () {
              console.log(`${this.name} is printing a page`);
            },
            scan: function () {
              console.log(`${this.name} is scanning a document`);
            },
            play: function (artist, track) {
              console.log(`${this.name} is playing '${track}' by ${artist}`);
            },
          };
          const orders = [
            {
              template: { name: 'ACME Printer'},
              parts: ['print']      
            },
            {
              template: { name: 'Initech Scanner'},
              parts: ['scan']      
            },
            {
              template: { name: 'ComTron Copier'},
              parts: ['scan', 'print']      
            },
            {
              template: { name: 'BoomBox Stereo'},
              parts: ['play']      
            },
          ];
          const products = factory(library, orders);
          console.log(products);
          
    };

    test1();
}

testObjectFactory();