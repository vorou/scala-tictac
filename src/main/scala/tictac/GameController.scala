package tictac

class GameController(val game: Game) {
    var xTurn = true

    def makeMove(position: (Int, Int)) = {
        val mark = if (xTurn)
            'x'
        else
            'o'
        game.put(mark, (0, 0))
        xTurn = !xTurn
    }
}
