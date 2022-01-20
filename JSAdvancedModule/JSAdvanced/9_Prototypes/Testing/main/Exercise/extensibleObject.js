function extensibleObject() {

    let obj = {

        extend: function (template) {

            let proto = Object.getPrototypeOf(this);
            
            for(const prop in template) {

                if (typeof template[prop] === 'function') {
                    proto[prop] = template[prop];
                } else {
                    this[prop] = template[prop];
                }
            }
        }
    };
    return obj;
}

function test() {

    const myObj = extensibleObject(); 
 
    console.log(myObj);

    const template = { 
        extensionMethod: function () {}, 
        extensionProperty: 'someString' 
      } 

    myObj.extend(template); 
    console.log();  
}

test();