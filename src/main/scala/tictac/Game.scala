package tictac

class Game(val board: Array[Array[Char]]) {
    val oRow = Array('o', 'o', 'o')

    def getState: Char = {
        if (board.exists(row => isRowOfO(row)))
            return 'o'
        '-'
    }

    def isRowOfO(row: Array[Char]): Boolean = {
        row.deep == oRow.deep
    }
}
