const board = createBoard();
const turn = "X";

document.addEventListener("DOMContentLoaded", function() {
    renderBoard();
});

const winLines = [
    [0,1,2], [3,4,5],[6,7,8],
    [0,3,6], [1,4,7], [2,5,8],
    [0,4,8], [2,4,6]
];

function createBoard() {
    return [null, null, null, null, null, null, null, null, null];
}

function checkWinner(board) {
    for (const line of winLines) {
        const cells = board[line[0]] + board[line[1]] + board[line[2]];
        if (cells === "XXX") return "X";
        if (cells === "OOO") return "O";
    }
    const hasEmpty = board.includes(null);
    return hasEmpty ? null : "DRAW";
}

function findEmptySpots(board) {
    const emptySpots = [];
    for (let i = 0; i < board.length; i++) {
        if (board[i] === null) {
            emptySpots.push(i)
        }
    }
    return emptySpots;
}

function findBestMove(board) {
    let bestScore = -Infinity;
    let bestMove = null;

    function miniMax(board, depth, isAiTurn) {
        const emptySpots = findEmptySpots(board);

        const winner = checkWinner(board);
        if (winner != null) {
            if (winner === "X") {
                return -10;
            } else if (winner === "O") {
                return 10;
            } else if (winner === "DRAW") {
                return 0;
            }
        }

        const scores = [];

        for (const spot of emptySpots) {
            board[spot] = isAiTurn ? "O" : "X";
            const currentScore = miniMax(board, depth + 1, !isAiTurn);
            scores.push(currentScore);

            if (isAiTurn && depth === 0) {
                if(currentScore > bestScore) {
                    bestScore = currentScore;
                    bestMove = spot;
                }
            }
            board[spot] = null;
        }
        return isAiTurn ? Math.max(...scores) : Math.min(...scores);
    }

    miniMax(board, 0, true);
    return bestMove;
}

function renderBoard() {

    const container = document.getElementById("board");

    container.replaceChildren();

    const fragment = document.createDocumentFragment();

    board.forEach((cell, index) => {
        const button = document.createElement("button");
        button.id = index;
        button.addEventListener("click", function() {
            if (cell === null) {
               board[index] = turn;
               renderBoard();
            }
        });

        if (cell != null) {
            button.textContent = cell;
        }

        fragment.append(button);
    })

    container.append(fragment);
}