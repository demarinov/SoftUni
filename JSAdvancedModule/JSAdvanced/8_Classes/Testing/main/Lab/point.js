class Point {

    constructor(x, y) {
        this.x = Number(x);
        this.y = Number(y);
    }

    static distance(p1, p2) {

        let xDistance = Math.abs(p2.x - p1.x);
        let yDistance = Math.abs(p2.y - p1.y);

        return Math.sqrt(xDistance * xDistance + yDistance * yDistance);
    }
}

function test() {
    let p1 = new Point('5', '5');
    let p2 = new Point(9, '8');
    console.log(Point.distance(p1, p2));

    console.log(typeof Point.distance(p1,p2));
}

test();