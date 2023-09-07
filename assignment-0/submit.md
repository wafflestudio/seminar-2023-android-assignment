# 과제 제출
`seminar-2023-andoid-assignment` 레포를 clone한 후, 이 파일의 아래 부분을 수정해서 PR로 올려 주세요.

## 1. 설치 스크린샷
![안드로이드 스튜디오 설치 완료](https://github.com/Glenn-syj/seminar-2023-android-assignment/assets/65771798/fce449e5-2ea5-4fc2-ab5f-d50a6421f880)

깃허브 에디터에 스크린샷 이미지를 드래그 드롭 하면 이미지를 첨부할 수 있습니다. 여기에 넣어 주세요!

## 2. SNUTT 강의 찾기 코드

여기를 채워 주세요.
```kotlin
import java.io.*
import java.util.*

data class Lecture (
    val lectNumb: Int,
    val day: Int,
    val startHour: Int,
    val endHour: Int,
) {
    fun getPriority() = day * 100_000_000 + startHour * 1_000_000 + endHour * 10_000 + lectNumb
}

fun Lecture.compare(lecture2: Lecture): Boolean {
    if (this.day == lecture2.day) {
        var occupied = Array(24) {i -> true}
        for (i in this.startHour until this.endHour+1) {
            occupied[i] = false
        }
        if (false in occupied.copyOfRange(lecture2.startHour, lecture2.endHour+1)) {
            return false
        }
    }
    return true
}

fun main() {
    var st = StringTokenizer(readLine())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()

    var appliedLectures = ArrayList<Lecture>()
    var selectableLectures = ArrayList<Lecture>()

    repeat(N) {
        val st = StringTokenizer(readLine())
        val lectureNumber = st.nextToken().toInt()
        val day = st.nextToken().toInt()
        val startHour = st.nextToken().toInt()
        val endHour = st.nextToken().toInt()
        
        appliedLectures.add(Lecture(lectureNumber, day, startHour, endHour))
    }

    repeat(M) {
        val st = StringTokenizer(readLine())
        val lectureNumber = st.nextToken().toInt();
        val day = st.nextToken().toInt()
        val startHour = st.nextToken().toInt()
        val endHour = st.nextToken().toInt()

        selectableLectures.add(Lecture(lectureNumber, 
 day, startHour, endHour))
    }

    if (selectableLectures.isEmpty()) {
        println("0")
        return
    }

    var viewedLectures = selectableLectures.filter{ selectableLecture -> 
        appliedLectures.all { appliedLecture ->
            appliedLecture.compare(selectableLecture)
        }
    }
    
    viewedLectures.sortedBy { it.getPriority() }.forEach { println(it.lectNumb) }
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
    data class Cha(override val pos: Point, override val team: Boolean): Piece(pos,team)
    data class Jol(override val pos: Point, override val team: Boolean): Piece(pos,team)
    data class King(override val pos: Point, override val team: Boolean): Piece(pos,team)
}
}

fun canPhoMoveTo(board: Array<Array<Piece?>>, next: Point): Boolean {
    // TODO : board가 주어졌을 때, next 위치로 내 포가 이동할 수 있는지 없는지 반환

    var currentX = -1
    var currentY = -1

    for (i in 0 until board.size) {
        for (j in 0 until board[i].size) {
            if (board[i][j] is Pho &&board[i][j].team) {
                currentX = i
                currentY = j
            } 
        }
    }

    var nextPiece: Piece? = board[next.x][next.y]
    var pieceCount = 0

    if (!(next.x==currentX||next.y==currentY)) {
        return false;
    }
    else if (nextPiece != null && (nextPiece.team|| nextPiece is Pho)) {
        return false
    }

    if (next.x == currentX) {
        if (currentY > next.y) {
            for (i in currentY downTo next.y+1) {
                when (board[next.x][i]) {
                    is null -> continue
                    else -> pieceCount++
                }
            }
        }
        else {
            for (i in currentY until next.y) {
                when (board[next.x][i]) {
                    is null -> continue
                    else -> pieceCount++
                }
            }
        }
        if (pieceCount != 1) {
            return false
        }
    }

    if (next.y == currentY) {
        if (currentX > next.x) {
            for (i in currentX downTo next.x+1) {
                when (board[i][next.y]) {
                    is null -> continue
                    else -> pieceCount++
                }
            }
        }
        else {
            for (i in currentX until next.x) {
                when (board[i][next.y]) {
                    is null -> continue
                    else -> pieceCount++
                }
            }
        }
        if (pieceCount != 1) {
            return false
        }
    }

    return true
}
```

