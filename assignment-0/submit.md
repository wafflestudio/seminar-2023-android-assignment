# 과제 제출
`seminar-2023-andoid-assignment` 레포를 clone한 후, 이 파일의 아래 부분을 수정해서 PR로 올려 주세요.

## 1. 설치 스크린샷
[화면 캡처 2023-08-30 185608](https://github.com/MinukJung/seminar-2023-android-assignment/assets/45121041/12f302e3-7079-44b5-acae-3e981a17c02f)


## 2. SNUTT 강의 찾기 코드

여기를 채워 주세요.
```kotlin
data class Lecture(val id: Int, val day: Int, val st: Int, val end: Int) {
    fun overlap(other: Lecture): Boolean {
        return (other.day == this.day && this.end >= other.st && other.end >= this.st)
    }
}

fun main() {
    var orgLec = mutableListOf<Lecture>()
    var newLec = mutableListOf<Lecture>()
    val input = readln()!!.split(" ")
    val N = input[0].toInt()
    val M = input[1].toInt()
    for (idx in 1..N) {
        val input = readln()!!.split(" ").map { it.toInt() }
        orgLec.add(Lecture(input[0], input[1], input[2], input[3]))
    }
    for (idx in 1..M) {
        val input = readln()!!.split(" ").map { it.toInt() }
        val inpLec = Lecture(input[0], input[1], input[2], input[3])
        val nOverlap = orgLec.filter { it.overlap(inpLec) }.count()
        if (nOverlap == 0) {
            newLec.add(inpLec)
        }
    }
    newLec.sortWith(compareBy<Lecture> { it.day }.thenBy { it.st }.thenBy { it.end }.thenBy { it.id })
    if (newLec.isEmpty()) {
        println(0)
    } else {
        for (lec in newLec) {
            println(lec.id)
        }
    }
}
```

## 3. 쌍포는 와플도 녹인다 코드

여기를 채워 주세요.
```kotlin
data class Point(
    val x: Int,
    val y: Int,
)

sealed class Piece(open val pos: Point, open val team: Boolean) { // team이 true이면 우리 편 기물
    data class Pho(override val pos: Point, override val team: Boolean) : Piece(pos, team)
    // TODO
    data class Cha(override val pos: Point, override val team: Boolean): Piece(pos, team)
    data class Jol(override val pos: Point, override val team: Boolean): Piece(pos, team)
    data class King(override val pos: Point, override val team: Boolean): Piece(pos, team)

    data class Next(override val pos: Point, override val team: Boolean): Piece(pos, team)
}

fun canPhoMoveTo(board: Array<Array<Piece?>>, next: Point): Boolean {
    // TODO : board가 주어졌을 때, next 위치로 내 포가 이동할 수 있는지 없는지 반환
    if (board[next.x][next.y]?.team == true || board[next.x][next.y] is Piece.Pho) {
        return false
    }
    board[next.x][next.y] = Piece.Next(Point(next.x, next.y), true)

    var idxPho = -10
    var idxNext = -10

    val xPieceList = board[next.x].filterNotNull()
    for (i in 0 until xPieceList.count()) {
        val piece = xPieceList[i]
        if (piece is Piece.Pho && piece.team) idxPho = i
        if (piece is Piece.Next) idxNext = i
    }
    if (idxPho - idxNext == 2 || idxNext - idxPho == 2) {
        val midIdx: Int = (idxPho + idxNext) / 2
        if (xPieceList[midIdx] !is Piece.Pho) {
            return true
        }
    }

    var yPieceList = mutableListOf<Piece?>()
    for (x in 0..8) {
        if (board[x][next.y] != null) yPieceList.add(board[x][next.y])
    }
    for (i in 0 until yPieceList.count()) {
        val piece = yPieceList[i]
        if (piece is Piece.Pho && piece.team) idxPho = i
        if (piece is Piece.Next) idxNext = i
    }
    if (idxPho - idxNext == 2 || idxNext - idxPho == 2) {
        val midIdx: Int = (idxPho + idxNext) / 2
        if (xPieceList[midIdx] !is Piece.Pho) {
            return true
        }
    }

    return false
}
```

