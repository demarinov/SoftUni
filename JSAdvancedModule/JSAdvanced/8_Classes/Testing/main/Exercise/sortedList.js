class List {

    constructor() {
        this.size = 0;
        this.arr = [];
    }

    add(element) {
        this.arr.push(Number(element));
        this.size++;
        this.arr.sort((e1, e2) => e1 - e2);

        return this;
    };

    remove(index) {

        if (index >= 0 && index < this.arr.length) {
            this.arr.splice(index, 1);
            this.size--;
            this.arr.sort((e1, e2) => e1 - e2);
        }

        return this;
    };

    get(index) {
        if (index >= 0 && index < this.arr.length) {
            return this.arr[index];
        }

        return;
    }

}

function test() {
    let list = new List();
    list.add(5);
    list.add(6);
    list.add(7);
    console.log(list.get(1));
    list.remove(1);
    console.log(list.get(1));

}

test();