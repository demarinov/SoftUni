function solution(command) {


    this.upvote = function upvote() {
        this.upvotes++;
    }

    this.downvote = function downvote() {
        this.downvotes++;
    }

    
    this.score = function score() {
        const report = [];
        const totalVotes = this.downvotes + this.upvotes;

        report[0] = this.upvotes;
        report[1] = this.downvotes;
        if (totalVotes > 50) {
            let greaterNum = this.downvotes;

            if (greaterNum < this.upvotes) {
                greaterNum = this.upvotes;
            }

            greaterNum = Math.ceil(greaterNum * 0.25);

            report[0] += greaterNum;
            report[1] += greaterNum;

        }

        // calculate rating
        let rating = '';
        let balance = report[0] - report[1];

        if (totalVotes < 10) {
            rating = 'new';
        }
        else if (this.upvotes > (0.66 * totalVotes)) {
            rating = 'hot';
        } else if (balance >= 0 && totalVotes > 100) {
            rating = 'controversial';
        } else if (balance < 0) {
            rating = 'unpopular';
        } else {
            rating = 'new';
        }

        report[2] = balance;
        report[3] = rating;
        console.log(report);
        return report;
    }


    return this[command]();
    
}


function test() {

    let post = {
        id: '3',
        author: 'emil',
        content: 'wazaaaaa',
        upvotes: 100,
        downvotes: 100
    };
    solution.call(post, 'upvote');
    solution.call(post, 'downvote');
    let score = solution.call(post, 'score'); // [127, 127, 0, 'controversial']

    for(let i=1; i <=50; i++) {
        solution.call(post, 'downvote');       // (executed 50 times)
    }
    score = solution.call(post, 'score');     // [139, 189, -50, 'unpopular']

}

// test();

function test2() {

    let result = solution;
    var forumPost = {
        id: '1',
        author: 'pesho',
        content: 'hi guys',
        upvotes: 0,
        downvotes: 0
    };

    result.call(forumPost, 'upvote');

var answer = result.call(forumPost, 'score');
var expected = [1, 0, 1, 'new'];

// compareScore(expected, answer);

console.log('Expected: '+expected);
console.log(answer);

// function compareScore(expected, answer) {
//     expect(expected[0]).to.equal(answer[0], 'Incorrect number of upvotes');
//     expect(expected[1]).to.equal(answer[1], 'Incorrect number of downvotes');
//     expect(expected[2]).to.equal(answer[2], 'Incorrect score');
//     expect(expected[3]).to.equal(answer[3], 'Incorrect rating');
// }
}

test2();