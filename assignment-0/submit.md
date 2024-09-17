# 과제 제출
`seminar-2023-andoid-assignment` 레포를 clone한 후, 이 파일의 아래 부분을 수정해서 PR로 올려 주세요.

## 1. 설치 스크린샷

![1](https://github.com/kevin990222/seminar-2023-android-assignment/assets/113081855/a01eaaff-5082-4a74-a1c1-b23bf8f32559)


## 2. SNUTT 강의 찾기 코드

여기를 채워 주세요.
```kotlin
data class lecture(val lecNo:Int, val week:Int, val startT:Int, val endT:Int)

fun lecture.isNotDuplicate(otherLec:MutableList<lecture>):Boolean{
    for(other in otherLec){
        if ((this.week == other.week) && (this.startT <= other.endT) && (this.endT >= other.startT))
            return false
    }
    return true
}

fun main() {
    val info = readLine()?:""
    val (lecNum, userLecNum) = info.split(" ").map{ it.toIntOrNull()?:-1}

    val lectures: MutableList<lecture> = mutableListOf()
    val userLectures: MutableList<lecture> = mutableListOf()

    for(i in 0 until lecNum + userLecNum){
        val lecInfo = readLine()?:""
        val (lecNo, week, startT, endT) = lecInfo.split(" ").map{it.toIntOrNull()?:-1}
        val newLec = lecture(lecNo,week,startT,endT)

        if(i<lecNum) lectures.add(newLec)
        else userLectures.add(newLec)
    }

    val possible = userLectures.filter { it.isNotDuplicate(lectures) }

    val result = possible.sortedWith(compareBy<lecture>{it.week}.thenBy{it.startT}.thenBy { it.endT }.thenBy { it.lecNo })

    for(lec in result) println(lec.lecNo)
}
```

## 3. 쌍포는 와플도 녹인다 코드

여기를 채워 주세요.
```kotlin
data class Point(
    val x: Int,
    val y: Int,
)

sealed class Piece(open val pos: Point, open val team: Boolean) {
    data class Pho(override val pos: Point, override val team: Boolean) : Piece(pos, team)
}

fun canPhoMoveTo(board: Array<Array<Piece?>>, next: Point): Boolean {
    var phoPlace : Point? = null

    for(column in board){
        for(row in column){
            if(row==null) continue

            // next에 우리 말이나 적의 포가 있을 경우
            if((row.pos == next) && (row.team || row is Piece.Pho)) return false

            if(row is Piece.Pho && row.team){
                // Pho와 next가 일직선 상에 있지 않은 경우
                if(row.pos.x != next.x && row.pos.y != next.y) return false
                phoPlace = row.pos
            }
        }
    }

    var between : Int = 0

    for(column in board){
        for(row in column){
            if(row==null) continue

            val posX = row.pos.x
            val posY = row.pos.y

            if(phoPlace?.x == next.x && posX == next.x){
                if((posY<next.y && phoPlace.y < posY)||(posY>next.y && phoPlace.y > posY))
                    if(row is Piece.Pho) return false
                    else between++
            }

            else if(phoPlace?.y == next.y && posY == next.y){
                if((posX<next.x && phoPlace.x < posX)||(posX>next.x && phoPlace.x > posX))
                    if(row is Piece.Pho) return false
                    else between++
            }
        }
    }

    return between == 1
}
```

