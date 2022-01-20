function getPersons() {

    class Person {

        constructor(firstName, lastName, age, email) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
            this.email = email;
        }
    
        toString() {
            let output = `${this.firstName} ${this.lastName} (age: ${this.age}, email: ${this.email})`;
            return output;
        }
    };

    const personOne = new Person('Anna','Simpson',22,'anna@yahoo.com');
    const personTwo = new Person('SoftUni');
    const personThree = new Person('Stephan','Johnson',25);
    const personFour = new Person('Gabriel','Peterson',24,'g.p@gmail.com');

    return [personOne,personTwo,personThree, personFour];

}

function test() {
    console.log(getPersons());

    console.log(getPersons()[0].constructor.name);
    console.log(getPersons()[2].lastName);
}

test();