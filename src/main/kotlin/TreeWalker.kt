class TreeWalker() {
    private val ans: MutableList<String> = mutableListOf()
    private lateinit var lines: List<String>
    private fun goRight(index: Int, lineNum: Int) {
        var curIndex = index
        var curLineNum = lineNum
        while (curIndex + 1 < lines[curLineNum + 1].length && lines[curLineNum + 1][curIndex + 1] == '\\') {
            curIndex++
            curLineNum++
        }

        if (lines[curLineNum + 1][curIndex].isLetter()) {
            /*
                \
                a
            */

            workWithVertex(curIndex, curLineNum + 1)
        } else {
            /*
                \
                 a
            */
            workWithVertex(curIndex + 1, curLineNum + 1)
        }
    }

    private fun goLeft(index: Int, lineNum: Int) {
        var curIndex = index
        var curLineNum = lineNum
        while (curIndex - 1 > 0 && lines[curLineNum + 1][curIndex - 1] == '/') {
            curIndex--
            curLineNum++
        }

        if (lines[curLineNum + 1][curIndex - 1].isLetter()) {
            /*  /
               a
            */
            workWithVertex(curIndex - 1, curLineNum + 1)
        } else {
            /*
                /
                a
             */
            workWithVertex(curIndex, curLineNum)
        }
    }

    private fun workWithVertex(index: Int, lineNum: Int) {

        val line = lines[lineNum]
        var left = index
        var right = index
        while (left - 1 > 0 && line[left - 1].isLetter())
            left--
        while (right + 1 < line.length && line[right + 1].isLetter())
            right++

        ans.add(line.substring(left, right + 1))

        if (lineNum != lines.size - 1) {
            val nextLine = lines[lineNum + 1]
            for (i in left - 1..right) {
                if (i > 0 && i < nextLine.length && nextLine[i] == '/') {
                    goLeft(i, lineNum + 1)
                    break
                }
            }
            for (i in left..right + 1) {
                if (i > 0 && i < nextLine.length && nextLine[i] == '\\') {
                    goRight(i, lineNum + 1)
                    break
                }
            }

        }
    }

    fun solve(lines: List<String>): String {
        if (lines.isEmpty()) {
            return ""
        }
        this.lines = lines
        val left = lines[0].indexOfFirst { it.isLetter() }
        workWithVertex(left, 0)
        return ans.joinToString(separator = " ").also { ans.clear() }
    }

}

fun main() {
    val lines = listOf("foo")
    print(TreeWalker().solve(lines))
}