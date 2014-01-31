import org.scalatest.junit.JUnitSuite
import org.junit.Test
import org.scalatest.prop._
import org.scalatest._
import tictac._

class GameTests extends JUnitSuite with Matchers with TableDrivenPropertyChecks {
    def createGame(board: Array[Array[Char]]): Game = {
        new Game(board)
    }

    def assertState(board: Array[Array[Char]], state: Char) = {
        val game = createGame(board)

        val actual = game.getState

        actual shouldBe state
    }

    def assertStateForAll(boards: TableFor1[Array[Array[Char]]], state: Char) = {
        forAll(boards) { board: Array[Array[Char]] => assertState(board, state) }
    }

    @Test
    def state_noMarks_-() = {
        val emptyBoard = Array.fill(3, 3)('-')
        assertState(emptyBoard, '-')
    }

    @Test
    def state_noThrees_-() = {
        assertStateForAll(Table(
            "board",
            Array(
                Array('x', 'x', '-'),
                Array('-', '-', '-'),
                Array('-', '-', '-')
            ),
            Array(
                Array('x', '-', 'x'),
                Array('-', '-', '-'),
                Array('-', '-', '-')
            ),
            Array(
                Array('-', '-', '-'),
                Array('x', '-', '-'),
                Array('-', '-', '-')
            ),
            Array(
                Array('-', '-', '-'),
                Array('x', 'x', '-'),
                Array('-', '-', '-')
            ),
            Array(
                Array('-', '-', '-'),
                Array('x', '-', '-'),
                Array('x', '-', 'x')
            )
        ), '-')
    }

    @Test
    def state_threeOsInRow_o() = {
        assertStateForAll(Table(
            "board",
            Array(
                Array('o', 'o', 'o'),
                Array('-', '-', '-'),
                Array('-', '-', '-')
            ),
            Array(
                Array('-', '-', '-'),
                Array('o', 'o', 'o'),
                Array('-', '-', '-')
            ),
            Array(
                Array('-', '-', '-'),
                Array('-', '-', '-'),
                Array('o', 'o', 'o')
            )
        ), 'o')
    }

    @Test
    def state_threeOsInColumn_o() = {
        assertStateForAll(Table(
            "board",
            Array(
                Array('o', '-', '-'),
                Array('o', '-', '-'),
                Array('o', '-', '-')
            ),
            Array(
                Array('-', 'o', '-'),
                Array('-', 'o', '-'),
                Array('-', 'o', '-')
            ),
            Array(
                Array('-', '-', 'o'),
                Array('-', '-', 'o'),
                Array('-', '-', 'o')
            )
        ), 'o')
    }

    @Test
    def state_threeOsInDiagonal_o() = {
        assertStateForAll(Table(
            "board",
            Array(
                Array('o', '-', '-'),
                Array('-', 'o', '-'),
                Array('-', '-', 'o')
            ),
            Array(
                Array('-', '-', 'o'),
                Array('-', 'o', '-'),
                Array('o', '-', '-')
            )
        ), 'o')
    }

    @Test
    def state_threeXsInDiagonal_x() = {
        assertStateForAll(Table(
            "board",
            Array(
                Array('x', '-', '-'),
                Array('-', 'x', '-'),
                Array('-', '-', 'x')
            ),
            Array(
                Array('-', '-', 'x'),
                Array('-', 'x', '-'),
                Array('x', '-', '-')
            )
        ), 'x')
    }

    @Test
    def put_putsThirdX_xWins() = {
        val sut = createGame(Array(
            Array('x','x','-'),
            Array('-','-','-'),
            Array('-','-','-')
        ))

        sut.put('x', (1, 3))

        val actual = sut.getState
        actual shouldBe 'x'
    }
}
