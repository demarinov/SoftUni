const expect = require('chai').expect;
const cinema = require('../../main/Lab/cinema/cinema').cinema;

describe("Tests", function() {
    describe("showMovies", function() {
        it("1. zero array", function() {
            let expected = 'There are currently no movies to show.';
            let res = cinema.showMovies([]);

            expect(res).to.be.equal(expected);
        });

        it("2. correct case - joined result", function() {
            let expected = 'Cindy, Bertha, Maria';
            let res = cinema.showMovies(['Cindy','Bertha','Maria']);

            expect(res).to.be.equal(expected);

            expected = 'Cindy';
            res = cinema.showMovies(['Cindy']);

            expect(res).to.be.equal(expected);
        });
     });
     
     describe("ticketPrice", function() {
        it("1. no projection", function() {

            expect(cinema.ticketPrice.bind(cinema,'Funky')).to.throw('Invalid projection type.');
            expect(cinema.ticketPrice.bind(cinema,'')).to.throw('Invalid projection type.');
            expect(cinema.ticketPrice.bind(cinema,undefined)).to.throw('Invalid projection type.');
            expect(cinema.ticketPrice.bind(cinema,null)).to.throw('Invalid projection type.');
            expect(cinema.ticketPrice.bind(cinema,NaN)).to.throw('Invalid projection type.');

            
        });

        it("2. correct price and projection", function() {
            let expected = 12.00;
            let res = cinema.ticketPrice('Premiere');

            expect(res).to.be.equal(expected);

            expected = 7.50;
            res = cinema.ticketPrice('Normal');

            expect(res).to.be.equal(expected);

            expected = 5.50;
            res = cinema.ticketPrice('Discount');

            expect(res).to.be.equal(expected);
        });
     });

     describe("swapSeatsInHall", function() {
        it("1. no change of seats", function() {

            let expected = "Unsuccessful change of seats in the hall.";

            expect(cinema.swapSeatsInHall('1.0',2)).to.be.equal(expected);
            
            expect(cinema.swapSeatsInHall(1,'2')).to.be.equal(expected);
            expect(cinema.swapSeatsInHall('1','2')).to.be.equal(expected);
            expect(cinema.swapSeatsInHall(0,2)).to.be.equal(expected);
            expect(cinema.swapSeatsInHall(1,0)).to.be.equal(expected);
            expect(cinema.swapSeatsInHall(0,0)).to.be.equal(expected);
            expect(cinema.swapSeatsInHall(-1,2)).to.be.equal(expected);
            expect(cinema.swapSeatsInHall(1,-2)).to.be.equal(expected);
            expect(cinema.swapSeatsInHall(-1,-2)).to.be.equal(expected);
            expect(cinema.swapSeatsInHall(21,2)).to.be.equal(expected);
            expect(cinema.swapSeatsInHall(1,22)).to.be.equal(expected);
            expect(cinema.swapSeatsInHall(23,22)).to.be.equal(expected);
            expect(cinema.swapSeatsInHall(2,2)).to.be.equal(expected);

            expect(cinema.swapSeatsInHall('',2)).to.be.equal(expected);
            expect(cinema.swapSeatsInHall(1,'')).to.be.equal(expected);
            expect(cinema.swapSeatsInHall('','')).to.be.equal(expected);
            expect(cinema.swapSeatsInHall(undefined,1)).to.be.equal(expected);
            expect(cinema.swapSeatsInHall(1,undefined)).to.be.equal(expected);
            expect(cinema.swapSeatsInHall(undefined,undefined)).to.be.equal(expected);
            expect(cinema.swapSeatsInHall(null,2)).to.be.equal(expected);
            expect(cinema.swapSeatsInHall(2,null)).to.be.equal(expected);
            expect(cinema.swapSeatsInHall(null,null)).to.be.equal(expected);
            expect(cinema.swapSeatsInHall(null)).to.be.equal(expected);
            expect(cinema.swapSeatsInHall()).to.be.equal(expected);
            expect(cinema.swapSeatsInHall(1.5,1)).to.be.equal(expected);
            expect(cinema.swapSeatsInHall(2,2.5)).to.be.equal(expected);
            expect(cinema.swapSeatsInHall(2.3,2.5)).to.be.equal(expected);
            expect(cinema.swapSeatsInHall(NaN,2)).to.be.equal(expected);
            expect(cinema.swapSeatsInHall(2,NaN)).to.be.equal(expected);
            expect(cinema.swapSeatsInHall(NaN,NaN)).to.be.equal(expected);

        });

        it("2. success in changing seats", function() {
            let expected = 'Successful change of seats in the hall.';
            let res = cinema.swapSeatsInHall(1,2);

            expect(res).to.be.equal(expected);

            res = cinema.swapSeatsInHall(18,19);

            expect(res).to.be.equal(expected);

            expect(cinema.swapSeatsInHall(20,2)).to.be.equal(expected);
            expect(cinema.swapSeatsInHall(2,20)).to.be.equal(expected);
        });
        
     });
});

// mind boundary conditions - positive and negative