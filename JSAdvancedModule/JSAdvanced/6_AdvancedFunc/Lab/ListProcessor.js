function solve(commandArr) {

    let obj = {
        valueList: [],
        add: function (val) {
            this.valueList.push(val);
        },
        remove: function (val) {
            while(this.valueList.some((a) => a === val)) {
                const index = this.valueList.indexOf(val);
                this.valueList.splice(index,1);
            }
        },
        print: function () {
            console.log(this.valueList.join(','));
        }
    };

    for(const command of commandArr) {

        const arr = command.split(' ');

        obj[arr[0]](arr[1]);
    }

}

function test() {

    solve(['add hello', 'add again', 'remove hello', 'add again', 'print']);

    solve(['add pesho', 'add george', 'add peter', 'remove peter','print']);
}

test();