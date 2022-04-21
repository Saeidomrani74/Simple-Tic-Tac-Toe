package tictactoe

fun main() {
    val inputs = MutableList(9) { " " }
    drawGrid(inputs)
}

fun drawGrid(xyz: MutableList<String>) {

    val board = MutableList(5) { MutableList(9) { " " } }
    for (i in 0..4) {
        for (j in 0..8) {
            if (i == 0 || i == 4) board[i][j] = "-"
            else if (j == 0 || j == 8) board[i][j] = "|"
            else if (j % 2 == 0) board[i][j] =
                xyz[((i - 1) * 3) + ((j / 2) - 1)]
        }
    }

    for (i in 0..4) {
        for (j in 0..8) {
            if (j == 8) println(board[i][j]) else print(board[i][j])
        }
    }

    var turnX = true
    while (true) {
        print("Enter the coordinates: ")
        val xy = readln().split(" ").toMutableList()
        if (xy[0].all { Character.isDigit(it) } &&
            xy[1].all { Character.isDigit(it) }) {
            if (xy[0].toInt() in 1..3 && xy[1].toInt() in 1..3) {
                val x = xy[0].toInt()
                val y = xy[1].toInt()
                if (board[x][y * 2] == " ") {
                    if (turnX) {
                        board[x][y * 2] = "X"
                        xyz[((x - 1) * 3) + (y - 1)] = "X"
                    } else {
                        board[x][y * 2] = "O"
                        xyz[((x - 1) * 3) + (y - 1)] = "O"
                    }
                    for (i in 0..4) {
                        for (j in 0..8) {
                            if (j == 8) println(board[i][j]) else print(board[i][j])
                        }
                    }
                    turnX = !turnX
                    if (cheking(xyz)) break else continue
                } else {
                    println("This cell is occupied! Choose another one!")
                    continue
                }
            } else {
                println("Coordinates should be from 1 to 3!")
                continue
            }
        } else {
            println("You should enter numbers!")
            continue
        }
    }

}

fun cheking(inputs: MutableList<String>): Boolean {
    var state = ""

        for (i in 0..8) {
            if (i % 3 == 0) if (inputs[i] == inputs[i + 1] &&
                inputs[i] == inputs[i + 2] && inputs[i] == "X"
            ) {
                state = "X"
                break
            }
            if (i < 3) if (inputs[i] == inputs[i + 3] &&
                inputs[i] == inputs[i + 6] && inputs[i] == "X"
            ) {
                state = "X"
                break
            }
            if (((inputs[0] == inputs[4] && inputs[0] == inputs[8]) ||
                        (inputs[2] == inputs[4] && inputs[2] == inputs[6])) &&
                inputs[4] == "X"
            ) {
                state = "X"
                break
            }
        }

    for (i in 0..8) {
        if (i % 3 == 0) if (inputs[i] == inputs[i + 1] &&
            inputs[i] == inputs[i + 2] && inputs[i] == "O"
        ) {
            state = "O"
            break
        }
        if (i < 3) if (inputs[i] == inputs[i + 3] &&
            inputs[i] == inputs[i + 6] && inputs[i] == "O"
        ) {
            state = "O"
            break
        }
        if (((inputs[0] == inputs[4] && inputs[0] == inputs[8]) ||
                    (inputs[2] == inputs[4] && inputs[2] == inputs[6])) &&
            inputs[4] == "O"
        ) {
            state = "O"
            break
        }
    }

    var es = 0
    for (i in 0..8) {
        if (inputs[i] == " ") es++
    }
    if (es == 0 && state == "") state = "D"

    if (state == "") return false

    when (state) {
        "X", "O" -> println("$state wins")
        "D" -> println("Draw")
    }
    return true
}