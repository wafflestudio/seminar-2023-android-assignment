# 과제 제출
`seminar-2023-andoid-assignment` 레포를 clone한 후, 이 파일의 아래 부분을 수정해서 PR로 올려 주세요.

## 1. 설치 스크린샷

깃허브 에디터에 스크린샷 이미지를 드래그 드롭 하면 이미지를 첨부할 수 있습니다. 여기에 넣어 주세요!!

[KakaoTalk_20230902_161543279](https://github.com/wafflestudio/seminar-2023-android-assignment/assets/110971028/4a7cfd34-5bc2-445b-be75-524866959fa1)


## 2. SNUTT 강의 찾기 코드

여기를 채워 주세요.
```kotlin
data class Lecture(
    val lectureNumber: Int,
    val day: Int,
    val startTime: Int,
    val endTime: Int
) {
    fun toSortableValue(): Long {
        return day.toLong() shl 48 or (startTime.toLong() shl 32) or (endTime.toLong() shl 16) or lectureNumber.toLong()
    }
}

fun Lecture.isConflictingWith(other: Lecture): Boolean {
    if (this.day != other.day) return false
    return !(this.endTime <= other.startTime || this.startTime >= other.endTime)
}

fun main() {
    val (N, M) = readLine()!!.split(" ").map { it.toInt() }

    var allLectures = mutableListOf<Lecture>()
    var userLectures = mutableListOf<Lecture>()

    for (i in 1..N) {
        val (lectureNumber, day, startTime, endTime) = readLine()!!.split(" ").map { it.toInt() }
        userLectures.add(Lecture(lectureNumber, day, startTime, endTime))
    }

    repeat(M) {
        val (lectureNumberM, dayM, startTimeM, endTimeM) = readLine()!!.split(" ").map { it.toInt() }
        allLectures.add(Lecture(lectureNumberM, dayM, startTimeM, endTimeM))
    }

    allLectures.sortBy { it.toSortableValue() }

    val nonConflictingLectures = allLectures.filterNot { lecture ->
        userLectures.any { it.isConflictingWith(lecture) }
    }

    if (nonConflictingLectures.isNotEmpty()) {
        nonConflictingLectures.forEach { println(it.lectureNumber) }
    } else {
        println(0)
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
    data class Cha(override val pos: Point, override val team: Boolean) : Piece(pos,team)
    data class Jol(override val pos: Point, override val team: Boolean) : Piece(pos, team)
    data class King(override val pos: Point, override val team: Boolean) : Piece(pos, team)
}

fun canPhoMoveTo(board: Array<Array<Piece?>>, next: Point): Boolean {
    // TODO : board가 주어졌을 때, next 위치로 내 포가 이동할 수 있는지 없는지 반환

    var from:Point?=board.indices.flatMap{rowIndex->board[rowIndex].indices.map{colIndex->
        if(board[rowIndex][colIndex] is Piece.Pho&&board[rowIndex][colIndex]!!.team) Point(rowIndex,colIndex) else null
        }
    }.firstOrNull()

    if(from == null) return false

    if(from?.x!=next.x&&from?.y!=next.y) return false


    val minRow=minOf(from.x, next.x)
    val maxRow=maxOf(from.x,next.x)
    val minCol=minOf(from.y,next.y)
    val maxCol=maxOf(from.y,next.y)

    var count :Int = 0

    if(from.x==next.x){
        for(col in minCol + 1 until maxCol){
            if(board[from.x][col]!=null)
                count++
        }
    }
    else{
        for(row in minRow+1 until maxRow){
            if(board[row][from.y]!=null){
                count++
            }
        }
    }

    if(board[next.x][next.y]==null){
        return count==0
    }

    return count==1&&board[next.x][next.y]!!.team!=board[from.x][from.y]!!.team

}
```

