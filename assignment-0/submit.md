# 과제 제출
`seminar-2023-andoid-assignment` 레포를 clone한 후, 이 파일의 아래 부분을 수정해서 PR로 올려 주세요.

## 1. 설치 스크린샷

깃허브 에디터에 스크린샷 이미지를 드래그 드롭 하면 이미지를 첨부할 수 있습니다. 여기에 넣어 주세요!
![KakaoTalk_20230906_022754405](https://github.com/wafflestudio/seminar-2023-android-assignment/assets/142739361/c5fe8200-10c8-4636-9ed4-35e07a7d6c03)


## 2. SNUTT 강의 찾기 코드

여기를 채워 주세요.
```kotlin
fun main() {
    val myschedule = Array(7) { IntArray(24) }
    val availableLists = mutableListOf<List<Int>>()
    val NM = readLine()?.split(" ")?.map { it.toInt() }

    if (NM != null) {
        for (i in 1..NM[0]) {
            val IDSE = readLine()?.split(" ")?.map { it.toIntOrNull() }

            if (IDSE != null && IDSE.size >= 4) {
                val start = IDSE[2] ?: 0
                val end = IDSE[3] ?: 0

                for (j in start..end) {
                    myschedule[IDSE[1]?.minus(1)!!][j] = 1
                }
            }
        }

        for (i in 1..NM[1]) {
            val IDSE = readLine()?.split(" ")?.map { it.toIntOrNull() }
            var chec: Int = 0

            if (IDSE != null && IDSE.size >= 4) {
                val start = IDSE[2] ?: 0
                val end = IDSE[3] ?: 0

                for (j in start..end) {
                    chec += myschedule[IDSE[1]?.minus(1)!!][j]
                }

                if (chec == 0) {
                    availableLists.add(IDSE as List<Int>)
                }
            }
        }
        availableLists.sortBy { it[0] }
        availableLists.sortBy { it[3] }
        availableLists.sortBy { it[2] }
        availableLists.sortBy { it[1] }

        for (list in availableLists) {
            println("${list[0]}")
        }
    }
}
```

## 3. 쌍포는 와플도 녹인다 코드

여기를 채워 주세요.
```kotlin
fun main() {
    val board = Array(9) { IntArray(9) }
    val availableLists = mutableListOf<List<Int>>()

    val teamPo = readLine()?.split(" ")?.map { (it.toIntOrNull() ?: 0) - 1 }

    for (i in 1..3) {
        val xyi = readLine()?.split(" ")?.map { (it.toIntOrNull() ?: 0) - 1 }
        board[xyi?.get(0)!!][xyi[1]!!] = 1
    }
    val xy = readLine()?.split(" ")?.map { (it.toIntOrNull() ?: 0) - 1 }
    board[xy?.get(0)!!][xy[1]!!] = 2

    for (i in 1..3) {
        val xy = readLine()?.split(" ")?.map { (it.toIntOrNull() ?: 0) - 1 }
        board[xy?.get(0)!!][xy[1]!!] = 3
    }

    val dir = listOf(listOf(1, 0), listOf(-1, 0), listOf(0, 1), listOf(0, -1))
    for (move in dir) {
        var PoX = teamPo?.get(0) ?: 0
        var PoY = teamPo?.get(1) ?: 0
        var jump = false
        while (1 <= PoX && PoX < 8 && 1 <= PoY && PoY < 8) {
            PoX += move[0]
            PoY += move[1]
            when (jump) {
                true -> when (board[PoX][PoY]) {
                    0 -> availableLists.add(listOf(PoX, PoY))
                    1 -> break
                    2 -> break
                    3 -> {
                        availableLists.add(listOf(PoX, PoY))
                        break
                    }
                }

                false -> when (board[PoX][PoY]) {
                    0 -> continue
                    1 -> jump = true
                    2 -> break
                    3 -> jump = true
                    }

                }
            }
        }
    for (list in availableLists) {
        println(list)
        }
}
data class Point(
    val x: Int,
    val y: Int,
)

sealed class Piece(open val pos: Point, open val team: Boolean) { // team이 true이면 우리 편 기물
    data class Pho(override val pos: Point, override val team: Boolean) : Piece(pos, team)
    // TODO
    // data class Cha( ... )
    // data class Jol( ... )
    // data class King( ... )
}

fun canPhoMoveTo(board: Array<Array<Piece?>>, next: Point): Boolean {
    // TODO : board가 주어졌을 때, next 위치로 내 포가 이동할 수 있는지 없는지 반환
    return true
}
```

