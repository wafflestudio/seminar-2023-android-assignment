# 과제 제출
`seminar-2023-andoid-assignment` 레포를 clone한 후, 이 파일의 아래 부분을 수정해서 PR로 올려 주세요.

## 1. 설치 스크린샷

![ass0](https://github.com/zboomz/seminar-2023-android-assignment/assets/128276524/9ecb0b24-0dc8-4cbf-9b15-c10922d60d1f)


## 2. SNUTT 강의 찾기 코드

여기를 채워 주세요.
```kotlin
data class Course(val i: Int, val d: Int, val s: Int, val e: Int)
fun Course.available(course: Course): Boolean {
    return if (d == course.d){
        ((s > course.e) || (e < course.s))
    }
    else {
        true
    }
}

val User = mutableListOf<Course>()
val Total = mutableListOf<Course>()
var Result = mutableListOf<Course>()

fun main() {
    val setting: String? = readlnOrNull()
    val parts = setting?.split("\\s+".toRegex())
    val n = parts?.get(0)?.toInt()
    val m = parts?.get(1)?.toInt()

    for (i in 1..n!!){
        val u: String? = readlnOrNull()
        val parts = u?.split("\\s+".toRegex())
        val user = Course(parts?.get(0)?.toInt()!!, parts?.get(1)?.toInt()!!, parts?.get(2)?.toInt()!!, parts?.get(3)?.toInt()!!)
        User.add(user)
    }

    for (i in 1..m!!){
        val t: String? = readlnOrNull()
        val parts = t?.split("\\s+".toRegex())
        val total = Course(parts?.get(0)?.toInt()!!, parts?.get(1)?.toInt()!!, parts?.get(2)?.toInt()!!, parts?.get(3)?.toInt()!!)
        Total.add(total)
        Result.add(total)
    }

    for (user in User){
        Result = Result.filter{Total.filter{it.available(user)}.contains(it)}.toMutableList()
    }

    Result.sortBy{it.d * (m+1) * 24 * 24 + it.s * (m+1) * 24 + it.e * (m+1) + it.i}

    if (Result.size > 0) {
        Result.forEach { println(it.i) }
    }
    else{
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
    data class Cha(override val pos: Point, override val team: Boolean) : Piece(pos, team)
    data class Jol(override val pos: Point, override val team: Boolean) : Piece(pos, team)
    data class King(override val pos: Point, override val team: Boolean) : Piece(pos, team)
}

fun canPhoMoveTo(board: Array<Array<Piece?>>, next: Point): Boolean {
    // TODO : board가 주어졌을 때, next 위치로 내 포가 이동할 수 있는지 없는지 반환
    val myPho = mutableListOf<Piece>()
    val sameX = mutableListOf<Piece>()
    val sameY = mutableListOf<Piece>()

    val nonNullPiece = board.flatten().filterNotNull()

    for (piece in nonNullPiece) {
        if (piece is Piece.Pho && piece.team) {
            myPho.add(piece)
        }
    }

    val xPho = myPho[0].pos.x
    val yPho = myPho[0].pos.y

    if (next.x == xPho){
        for (piece in nonNullPiece){
            if (piece.pos.x == next.x){
                sameX.add(piece)
            }
        }
        sameX.sortBy{it.pos.y}

        val iPho = sameX.indexOf(myPho[0])

        for (piece in sameX){
            if (piece.pos == next){
                return if (piece.team){
                    false
                } else {
                    val index = sameX.indexOf(piece)
                    if (kotlin.math.abs(iPho - index) != 2){
                        false
                    } else{
                        sameX[(iPho + index)/2] !is Piece.Pho
                    }
                }
            }
        }

        sameX.add(Piece.Jol(next, false))
        sameX.sortBy{it.pos.y}
        val index = sameX.indexOf(Piece.Jol(next, false))

        return if (kotlin.math.abs(iPho - index) != 2){
            false
        }
        else {
            sameX[(iPho + index)/2] !is Piece.Pho
        }
    }
    else if (next.y == yPho) {
        for (piece in nonNullPiece){
            if (piece.pos.y == next.y){
                sameY.add(piece)
            }
        }
        sameY.sortBy{it.pos.x}

        val iPho = sameY.indexOf(myPho[0])

        for (piece in sameY){
            if (piece.pos == next){
                return if (piece.team){
                    false
                } else {
                    val index = sameY.indexOf(piece)
                    if (kotlin.math.abs(iPho - index) != 2){
                        false
                    } else{
                        sameY[(iPho + index)/2] !is Piece.Pho
                    }
                }
            }
        }

        sameY.add(Piece.Jol(next, false))
        sameY.sortBy{it.pos.x}
        val index = sameY.indexOf(Piece.Jol(next, false))

        return if (kotlin.math.abs(iPho - index) != 2){
            false
        }
        else {
            sameY[(iPho + index)/2] !is Piece.Pho
        }
    }
    else{
        return false
    }
}
```

