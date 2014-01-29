import org.scalatest.junit.JUnitSuite
import org.junit.Test
import org.scalatest.prop._
import org.scalatest._
import tictac._

class GameTests extends JUnitSuite with Matchers with TableDrivenPropertyChecks {
    def createGame(board: Array[Array[Char]]): Game = {
        new Game(board)
    }

    def AssertState(board: Array[Array[Char]], state: Char) = {
        val game = createGame(board)

        val actual = game.getState

        actual shouldBe state
    }

    def AssertState(boards: TableFor1[Array[Array[Char]]], state: Char) = {
        forAll(boards) {
            (board) =>
                AssertState(board, state)
        }
    }

    @Test
    def state_noMarks_isUnknown() = {
        val emptyBoard = Array.fill(3, 3)('-')
        AssertState(emptyBoard, '-')
    }

    @Test
    def state_noThrees_isUnknown() = {
        AssertState(Table(
            "board",
            Array(
                Array('o', 'o', '-'),
                Array('-', '-', '-'),
                Array('-', '-', '-')
            ),
            Array(
                Array('o', '-', 'o'),
                Array('-', '-', '-'),
                Array('-', '-', '-')
            ),
            Array(
                Array('-', '-', '-'),
                Array('o', '-', '-'),
                Array('-', '-', '-')
            ),
            Array(
                Array('-', '-', '-'),
                Array('o', 'o', '-'),
                Array('-', '-', '-')
            ),
            Array(
                Array('-', '-', '-'),
                Array('o', '-', '-'),
                Array('o', '-', 'o')
            )
        ), '-')
    }

    @Test
    def state_threeOsInRow_isOwon() = {
        AssertState(Table(
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
}
