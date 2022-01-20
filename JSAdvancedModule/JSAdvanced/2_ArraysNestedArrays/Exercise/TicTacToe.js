function ticTacToe(input) {

    let board = [
        [false, false, false],
        [false, false, false],
        [false, false, false]
    ];

    let previousTurnSign = 'O';
    for(let i=0; i< input.length;i++) {

        let choices = input[i]; 
        const choicePlayer = choices.split(' ');

        let placeTaken = false;
        let currentSign;

        if (previousTurnSign === 'O') {
            currentSign = 'X';
        } else {
            currentSign = 'O';
        }

        if (board[choicePlayer[0]][choicePlayer[1]] == false) {
            board[choicePlayer[0]][choicePlayer[1]] = currentSign;
        } else {
            placeTaken = true;
        }

        function checkWinner(board) {

            let winner = true;
            let winPositionCount = 0;
            let winnerSign;
            // column level winner
            for(let row = 0; row< board.length; row++) {

                let comparisonValue;
                winPositionCount = 0;

                for(let col = 0; col < board[row].length; col++) {

                    if (board[row][col] === false) {
                        winner = false;
                        break;
                    }

                    if (comparisonValue == undefined) {
                        comparisonValue = board[row][col];
                    }

                    if (comparisonValue !== board[row][col]) {
                        winner = false;
                        break;
                    }

                    winPositionCount++;

                }

                if (winPositionCount == board.length) {
                    winner = true;
                    winnerSign = comparisonValue;    
                    break;
                }
            }

            if (winner) {
                return [winner, winnerSign];
            }

            // row level winner
            winPositionCount = 0;
            for(let col=0; col< board.length; col++) {
                let comparisonValue;
                winPositionCount = 0;

                for(let row = 0; row < board.length; row++) {

                    if (board[row][col] === false) {
                        winner = false;
                        break;
                    }

                    if (comparisonValue == undefined) {
                        comparisonValue = board[row][col];
                    }

                    if (comparisonValue !== board[row][col]) {
                        winner = false;
                        break;
                    }

                    winPositionCount++;
                }

                if (winPositionCount == board.length) {
                    winner = true;
                    winnerSign = comparisonValue; 
                    break;
                }
            }

            if (winner) {
                return [winner, winnerSign];
            }

            // diagonal level winner left downward
            let comparisonValue = board[0][0];
            winPositionCount = 0;
            for(let row = 1; row < board.length; row++) {

                if (board[row][row] === false) {
                    winner = false;
                    break;
                }

                if (comparisonValue !== board[row][row]) {
                    winner = false;
                    break;
                }

                winPositionCount++;
            }

            if (winPositionCount == board.length-1) {
                winner = true;
                winnerSign = comparisonValue; 
            }

            if (winner) {
                return [winner, winnerSign];
            }

            // diagonal level winner left upward
            comparisonValue = board[board.length-1][0];
            winPositionCount = 0;
            for(let row = board.length-2; row >= 0; row--) {

                if (board[row][board.length-1-row] === false) {
                    winner = false;
                    break;
                }

                if (comparisonValue !== board[row][board.length-1-row]) {
                    winner = false;
                    break;
                }
                winPositionCount++;
            }

            if (winPositionCount == board.length-1) {
                winner = true;
                winnerSign = comparisonValue; 
            }

            return [winner, winnerSign];
        }

        function printBoard(board) {

            for(const row of board) {

                console.log(row.join('\t'));
            }
            // console.log(board.map((n) => n.join('\t')).join('\n'));
        }

        let winnerData = checkWinner(board);
        let winnerFound = winnerData[0];

        if (winnerFound) {
            console.log(`Player ${winnerData[1]} wins!`);
            printBoard(board);
            break;
        }

        function fullBoard(board) {

            let result = true;
            board.forEach((row) => {
                
                row.forEach((col) => {
                    if (col === false) {
                        result = false;
                    }
                });
            });

            return result;
        }

        if (fullBoard(board)) {
            console.log('The game ended! Nobody wins :(');
            printBoard(board);
            break;
        }

        if (placeTaken) {
            console.log("This place is already taken. Please choose another!");
            continue;
        }

        previousTurnSign = currentSign;
    }
}

function testTicTacToe() {
    ticTacToe(["0 1",
    "0 0",
    "0 2", 
    "2 0",
    "1 0",
    "1 1",
    "1 2",
    "2 2",
    "2 1",
    "0 0"]
   );

   console.log();

   ticTacToe(["0 0",
   "0 0",
   "1 1",
   "0 1",
   "1 2",
   "0 2",
   "2 2",
   "1 2",
   "2 2",
   "2 1"]
  );

  console.log();

  ticTacToe(["0 1",
  "0 0",
  "0 2",
  "2 0",
  "1 0",
  "1 2",
  "1 1",
  "2 1",
  "2 2",
  "0 0"]
 );
}

testTicTacToe();