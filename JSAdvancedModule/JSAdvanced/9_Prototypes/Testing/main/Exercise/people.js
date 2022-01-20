function solution() {

    class Employee {
        constructor(name, age) {

            if (new.target === Employee) {
                throw new Error("Cannot make instance of abstract class Employee.");
            }

            this.name = name;
            this.age = Number(age);
            this.salary = 0;
            this.tasks = [];
            // this._currentTask = 0;
        };

        getSalary() {
            return this.salary;
        }

        work() {

            // works ...
            // if(this._currentTask === this.tasks.length) {
            //     this._currentTask = 0;
            // }
            
            // console.log(`${this.name} `+this.tasks[this._currentTask++]);

            let current = this.tasks.shift();
            console.log(`${this.name} ${current}`);
            this.tasks.push(current);
        };
        collectSalary() {
            console.log(`${this.name} received ${this.getSalary()} this month.`);
        }
    }

    class Junior extends Employee {

        constructor(name, age) {
            super(name, age);
            this.tasks = [`is working on a simple task.`];
        };


    }

    class Senior extends Employee {

        constructor(name, age) {
            super(name, age);
            this.tasks = [`is working on a complicated task.`];
            this.tasks.push(`is taking time off work.`);
            this.tasks.push(`is supervising junior workers.`);
        };



    }

    class Manager extends Employee {

        constructor(name, age) {
            super(name, age);
            this.tasks = [];
            this.tasks.push(`scheduled a meeting.`);
            this.tasks.push(`is preparing a quarterly report.`);
            this.dividend = 0;
        };

        getSalary() {

            return super.getSalary() + this.dividend;
        }

    }

    return {
        Employee, Junior, Senior, Manager
    };
}

function test() {
    const classes = solution();
    const junior = new classes.Junior('Ivan', 25);

    junior.work();
    junior.work();

    junior.salary = 5811;
    junior.collectSalary();

    const sinior = new classes.Senior('Alex', 31);

    sinior.work();
    sinior.work();
    sinior.work();
    sinior.name = 'Bob';
    sinior.work();

    sinior.salary = 12050;
    sinior.collectSalary();

    const manager = new classes.Manager('Tom', 55);

    manager.salary = 15000;
    manager.collectSalary();
    manager.dividend = 2500;
    manager.collectSalary();

    var guy1 = new classes.Junior('Peter', 27);
    guy1.salary = 1200;
    guy1.collectSalary();

}

test();