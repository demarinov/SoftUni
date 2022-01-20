function extendProrotype(classToExtend) {
    
    

    classToExtend.prototype.species = 'Human';
    classToExtend.prototype.toSpeciesString = function () {

        return `I am a ${this.species}. ${this.toString()}`;
    }
}

function test() {

    function Person(name, email) {
        this.name = name;
        this.email = email;
    }

    extendProrotype(Person);

    let personStr = new Person("Maria", "maria@gmail.com").toSpeciesString()

    console.log(personStr);
}

test();
