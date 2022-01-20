

function registerTickets(inputArr, sortingBy) {

    class Ticket {

        constructor(destination, price, status) {
            this.destination = destination;
            this.price = Number(price);
            this.status = status;
        }
    
    
    }

    const tickets = [];
    for(const input of inputArr) {

        const [destination, price, status] = input.split('\|');
        const ticket = new Ticket(destination, price, status);
        // <destinationName>|<price>|<status>

        tickets.push(ticket);
    }

    const sortingObj = {

        'destination': function () {
            tickets.sort((t1,t2) => t1.destination.localeCompare(t2.destination));
        },
        'price' : function () {
            tickets.sort((t1,t2) => t1.price - t2.price);
        },
        'status' : function () {
            tickets.sort((t1,t2) => t1.status.localeCompare(t2.status));
        }
    }

    sortingObj[sortingBy]();
    return tickets;
}

function test() {
    let res = registerTickets(['Philadelphia|94.20|available',
    'New York City|95.99|available',
    'New York City|95.99|sold',
    'Boston|126.20|departed'],
    'destination');

    console.log(res);

    res = registerTickets(['Philadelphia|94.20|available',
    'New York City|95.99|available',
    'New York City|95.99|sold',
    'Boston|126.20|departed'],
   'status');

   console.log(res);
}

test();