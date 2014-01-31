package tictac

class Game(val board: Array[Array[Char]]) {
    def getState: Char = {
        if (hasThree('o'))
            return 'o'
        if (hasThree('x'))
            return 'x'
        '-'
    }

    def put(mark: Char, position: (Int, Int)) = {
        board(position._1 - 1)(position._2 - 1) = mark
    }

    def hasThree(mark: Char): Boolean = {
        val rows = board
        val columns = Array(
            Array(board(0)(0), board(1)(0), board(2)(0)),
            Array(board(0)(1), board(1)(1), board(2)(1)),
            Array(board(0)(2), board(1)(2), board(2)(2)))
        val diagonals = Array(
            Array(board(0)(0), board(1)(1), board(2)(2)),
            Array(board(0)(2), board(1)(1), board(2)(0)))
        (rows ++ columns ++ diagonals).exists(r => isRowOf(r, mark))
    }

    def isRowOf(row: Array[Char], mark: Char): Boolean = {
        row.deep == Array.fill(3)(mark).deep
    }
}
