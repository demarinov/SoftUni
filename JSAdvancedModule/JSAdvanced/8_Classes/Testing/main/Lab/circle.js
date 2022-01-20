class Circle {

    constructor(radius) {
        this.radius = radius;
    }


    set diameter(diameter) {
        this.radius = diameter / 2;
    }

    get diameter() {
        return Number(this.radius * 2);
    }

    get area() {
        return Number(this.radius * this.radius * Math.PI);
    }
}

function test() {
    let c = new Circle(2);
    console.log(`Radius: ${c.radius}`);
    console.log(`Diameter: ${c.diameter}`);
    console.log(`Area: ${c.area}`);
    c.diameter = 1.6;
    console.log(`Radius: ${c.radius}`);
    console.log(`Diameter: ${c.diameter}`);
    console.log(`Area: ${c.area}`);

    c.diameter = '1.4';
    console.log(`Radius: ${c.radius}`);

}

test();