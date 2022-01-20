
class Person {

    constructor(firstName, lastName) {

        this._firstName = firstName;
        this._lastName = lastName;
        this._fullName = firstName + ' ' + lastName;
    }

    set firstName(name) {
        this._firstName = name;
        this._fullName = this._firstName + ' ' + this._lastName;
    }

    get firstName() {
        return this._firstName;
    }

    set lastName(name) {
        this._lastName = name;
        this._fullName = this._firstName + ' ' + this._lastName;
    }

    get lastName() {
        return this._lastName;
    }

    set fullName(name) {

        this._fullName = name;
        const names = this._fullName.split(' ');

        if (names.length !== 2) {
            return;
        }
        this._firstName = names[0];
        this._lastName = names[1];
    }

    get fullName() {
        return this._fullName;
    }
};

function test() {
    let person = new Person("Peter", "Ivanov");
    console.log(person.fullName); //Peter Ivanov
    person.firstName = "George";
    console.log(person.fullName); //George Ivanov
    person.lastName = "Peterson";
    console.log(person.fullName); //George Peterson
    person.fullName = "Nikola Tesla";
    console.log(person.firstName); //Nikola
    console.log(person.lastName); //Tesla

}

function test2() {
    let person = new Person("Albert", "Simpson");
    console.log(person.fullName); //Albert Simpson
    person.firstName = "Simon";
    console.log(person.fullName); //Simon Simpson
    person.fullName = "Peter";
    console.log(person.firstName);  // Simon
    console.log(person.lastName);  // Simpson

}

test();

test2();