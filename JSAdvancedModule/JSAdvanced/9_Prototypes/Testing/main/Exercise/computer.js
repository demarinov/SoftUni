function createComputerHierarchy() {

    class AbstractMachine {
        constructor(manufacturer) {
            

            if (this.constructor === AbstractMachine) {
                throw new 
                Error('Abstract class cannot be instantiated!!!');
            }

            this.manufacturer = manufacturer;
        }
    }

    class Keyboard extends AbstractMachine{
        constructor(manufacturer, responseTime) {
            super(manufacturer);
            this.responseTime = Number(responseTime);
        }
    }

    class Monitor extends AbstractMachine{
        constructor(manufacturer, width, height) {
            super(manufacturer);
            this.width = Number(width);
            this.height = Number(height);
        }
    }

    class Battery extends AbstractMachine {
        constructor(manufacturer, expectedLife) {
            super(manufacturer);
            this.expectedLife = Number(expectedLife);
        }
    }

    class Computer extends AbstractMachine {
        constructor(manufacturer, processorSpeed, 
            ram, hardDiskSpace) {
                super(manufacturer);

                if (this.constructor === Computer) {
                    throw new
                    Error('Abstract class Computer cannot be instantiated!!!');
                }

                this.processorSpeed = Number(processorSpeed);
                this.ram = Number(ram);
                this.hardDiskSpace = Number(hardDiskSpace);
            }

    }

    class Laptop extends Computer {
        constructor(manufacturer, 
            processorSpeed, 
            ram, 
            hardDiskSpace, 
            weight, color, battery) {

            super(manufacturer, processorSpeed, ram, hardDiskSpace);

            this.weight = Number(weight);
            this.color = color;
            this.battery = battery;
        }

        set battery(batteryIn) {

            if (!(batteryIn instanceof Battery)) {
                throw new
                TypeError('Error - wrong type!!!');
            }
            this._battery = batteryIn;
        };
        
        get battery() {
            return this._battery;
        }

    }

    class Desktop extends Computer {
        constructor(manufacturer, 
            processorSpeed, 
            ram, 
            hardDiskSpace, 
            keyboard, monitor) {

            super(manufacturer, processorSpeed, ram, hardDiskSpace);

            this.keyboard = keyboard;
            this.monitor = monitor;
        }

        get keyboard() {
            return this._keyboard;
        }

        set keyboard(keyboardIn) {
            
            if (!(keyboardIn instanceof Keyboard)) {
                throw new
                TypeError('Error - wrong type!!!');
            }
            this._keyboard = keyboardIn;
        }

        get monitor() {
            return this._monitor;
        }

        set monitor(monitorIn) {

            if (!(monitorIn instanceof Monitor)) {
                throw new
                TypeError('Error - wrong type!!!');
            }
            this._monitor = monitorIn;
        }
    }

    return {
        AbstractMachine,
        Battery,
        Keyboard,
        Monitor,
        Computer,
        Laptop,
        Desktop
    };


}

function test() {
    let classes = createComputerHierarchy();
    let Computer = classes.Computer;
    let Laptop = classes.Laptop;
    let Desktop = classes.Desktop;
    let Monitor = classes.Monitor;
    let Battery = classes.Battery;
    let Keyboard = classes.Keyboard;

    let battery = new Battery('Energy', 3);
    console.log(battery);
    let laptop = new Laptop("Hewlett Packard", 2.4, 4, 0.5, 3.12, "Silver", battery);
    console.log(laptop);

    let keyboard = new Keyboard('SolidTech',1);
    let monitor = new Monitor('Dell', 10.4, 12.3);
    let desktop = 
    new Desktop("IBM", 2.4, 4, 0.5, keyboard, monitor);
    console.log(desktop);
}

test();