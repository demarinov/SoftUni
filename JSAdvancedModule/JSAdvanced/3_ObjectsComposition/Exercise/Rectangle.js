function rectangle(width, height, color) {

    return {
        width,
        height,
        color: color.substring(0,1).toUpperCase() + color.substring(1),
        calcArea: () => {
            return width * height;
        }
    };
}

function test() {
    let rect = rectangle(4, 5, 'red');
    console.log(rect.width);
    console.log(rect.height);
    console.log(rect.color);
    console.log(rect.calcArea());
}

test();