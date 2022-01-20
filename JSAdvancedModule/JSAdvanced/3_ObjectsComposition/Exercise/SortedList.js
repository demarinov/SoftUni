function createSortedList() {

    class SortedList {
        constructor() {
            this.arr = [];
            this.size = 0;
        };

        add(element) {
            this.arr.push(element);
            this.arr.sort((a,b) => a-b);
            this.size++;
        };

        remove(index) {
            if (index < 0 || index >= this.arr.length) {
                throw new Error('');
            }
            this.arr.splice(index,1);
            this.arr.sort((a,b) => a-b);
            this.size--;
        };

        get(index) {
            return this.arr[index];
        };

    };

    let result = {
        arr: [],
        size: 0
    };

    const functionObject = (result) => {

        return {
            add: function (element) {
                result.arr.push(element);
                result.arr.sort((a,b) => a-b);
                result.size++;
            },
            remove: function (index) {
                if (index < 0 || index >= result.arr.length) {
                    throw new Error('');
                }

                result.arr.splice(index,1);
                result.arr.sort((a,b) => a-b);
                result.size--;
            },
            get: function (index) {
                return result.arr[index];
            },
        };
    }

    let result2 = new SortedList();

    return Object.assign(result, functionObject(result));
    
}

function test() {
    let list = createSortedList();
list.add(-5);
list.add(6);
list.add(-7);
console.log(list.get(1)); 
const buddy = list.remove;
// buddy(1);
console.log(list);

}

test();