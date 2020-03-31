import org.junit.Test
import java.io.File
import kotlin.test.assertEquals

const val TEST_FILES_PATH = "src/test/resources/"
const val NUMBER_OF_FILE_TESTS = 5

class TreeWalkerTest {
    private val treeWalker = TreeWalker()


    @Test
    fun singleLine() {
        assertEquals("foo", treeWalker.solve(listOf("foo")))
    }

    @Test
    fun fileTest() {
        for (i in 1..NUMBER_OF_FILE_TESTS) {
            val testCase = File(TEST_FILES_PATH).resolve("test$i").readLines()
            val testAns = File(TEST_FILES_PATH).resolve("ans$i").bufferedReader().readLine()
            assertEquals(testAns, treeWalker.solve(testCase))
        }

    }


}