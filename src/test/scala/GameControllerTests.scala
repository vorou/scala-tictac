import org.junit.Test
import org.mockito.Matchers.{eq => thisOne}
import org.mockito.Matchers._
import org.scalatest.junit.JUnitSuite
import tictac.{GameController, Game}
import org.mockito.Mockito._

class GameControllerTests extends JUnitSuite {
    @Test
    def makeMove_noMovesMadeYet_putsXinProperPlace() = {
        val game = mock(classOf[Game])
        val sut = new GameController(game)

        sut.makeMove((0, 0))

        verify(game).put('x', (0, 0))
    }

    @Test
    def makeMove_secondMove_putsOinProperPlace() = {
        val game = mock(classOf[Game])
        val sut = new GameController(game)
        sut.makeMove((0, 0))

        sut.makeMove((0, 1))

        verify(game).put(thisOne('o'), any())
    }
}
