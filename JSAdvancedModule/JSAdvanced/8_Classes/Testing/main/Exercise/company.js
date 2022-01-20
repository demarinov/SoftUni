class Company {

    constructor() {
        this.departments = [];

    }

    addEmployee(username, salary, position, department) {

        function validField(field) {

            if (field === '' || field === undefined || field === null) {
                return false;
            }

            return true;
        }

        if (!validField(username) 
        || !validField(salary)
        || !validField(position)
        || !validField(department)) {
            throw new Error("Invalid input!");
        }

        if (salary < 0) {
            throw new Error("Invalid input!");
        }

        const employee = {
            name: username,
            salary,
            position,
            department
        };

        let result = `New employee is hired. Name: ${username}. Position: ${position}`;

        this.departments.push(employee);

        return result;
    };  

    bestDepartment() {

        const departmentsWithTotalSalary = {
        };

        for(const dep of this.departments) {

            if (departmentsWithTotalSalary[dep.department] === undefined) {
                departmentsWithTotalSalary[dep.department] = {
                    totalSalary: 0,  
                    employees: []      
                };
            } 

            departmentsWithTotalSalary[dep.department]['totalSalary'] += dep.salary;
            departmentsWithTotalSalary[dep.department]['employees'].push(dep);
        }

        let [department, highestAvgSalary, employees] = ['',0,[]];

        for(const key in departmentsWithTotalSalary) {
            const value = departmentsWithTotalSalary[key];

            const avgSalary = (value['totalSalary'] / value.employees.length).toFixed(2);

            if (highestAvgSalary < avgSalary) {
                highestAvgSalary = avgSalary;
                department = key;
                employees = value['employees'];
            }
        }

        let output = `Best Department is: ${department}\nAverage salary: ${highestAvgSalary}\n`;

        employees
        .sort((e1,e2) => {
            let result = e2.salary - e1.salary;

            return result == 0 ? e1.name.localeCompare(e2.name): result;
        })
        .forEach((e) => {
            output += `${e.name} ${e.salary} ${e.position}\n`;
        })
        return output.substring(0, output.length-1);
    };
}

function test() {
    let c = new Company();
c.addEmployee("Stanimir", 2000, "engineer", "Construction");
c.addEmployee("Pesho", 1500, "electrical engineer", "Construction");
c.addEmployee("Slavi", 500, "dyer", "Construction");
c.addEmployee("Stan", 2000, "architect", "Construction");
c.addEmployee("Stanimir", 1200, "digital marketing manager", "Marketing");
c.addEmployee("Pesho", 1000, "graphical designer", "Marketing");
c.addEmployee("Gosho", 1350, "HR", "Human resources");
console.log(c.bestDepartment());

}

function testInvalid() {
    let c = new Company();
c.addEmployee("sssds", 0, "engineer", "Construction");
c.addEmployee("Pesho", 1500, "electrical engineer","Construction");
c.addEmployee("Slavi", 500, "dyer", "Construction");
c.addEmployee("Stan", 2000, "architect", "Construction");
c.addEmployee("Stanimir", 1200, "digital marketing manager", "Marketing");
c.addEmployee("Pesho", 1000, "graphical designer", "Marketing");
c.addEmployee("Gosho", 1350, "HR", "Human resources");
console.log(c.bestDepartment());
}

testInvalid();