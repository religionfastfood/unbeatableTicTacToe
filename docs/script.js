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