# 과제 제출
`seminar-2023-andoid-assignment` 레포를 clone한 후, 이 파일의 아래 부분을 수정해서 PR로 올려 주세요.

## 1. 설치 스크린샷
<img width="1280" alt="스크린샷 2023-09-05 174216" src="https://github.com/mscwrd02/seminar-2023-android-assignment/assets/96734714/cb2ccb7d-0b14-4afb-ab25-0f6dd36258fa">

깃허브 에디터에 스크린샷 이미지를 드래그 드롭 하면 이미지를 첨부할 수 있습니다. 여기에 넣어 주세요!

## 2. SNUTT 강의 찾기 코드

여기를 채워 주세요.
```kotlin
data class Course(val id: Int, val day : Int, val start : Int, val end : Int, val sortScore : Int)
fun Course.isOverlap(anotherCourse : Course) : Boolean {
    if(anotherCourse.day != this.day) return true
    if(anotherCourse.end < this.start || anotherCourse.start >this.end) return true
    return false
}

fun main(){
    var registeredCourse = mutableListOf<Course>()
    var answerCourse = mutableListOf<Course>()
    val twoInput = readLine()!!.split(' ').map { it.toInt() }
    val N = twoInput[0]
    val M = twoInput[1]

    for( i in 1..N){
        val courseInput = readLine()!!.split(' ').map { it.toInt() }
        val newCourse  = Course(courseInput[0], courseInput[1], courseInput[2], courseInput[3],0)
        registeredCourse.add(newCourse)
    }
    registeredCourse.filterNot{it.id > 1000 || it.id <0 || it.day>7 || it.day<1 || it.start>23 || it.start<0 || it.end>23 || it.end<0}
    for(i in 1..M){
        var courseInput = readLine()!!.split(' ').map { it.toInt() }
        var newCourse  = Course(courseInput[0], courseInput[1], courseInput[2], courseInput[3],24*24*1000*courseInput[1]+24*1000*courseInput[2]+1000*courseInput[3]+courseInput[0])
        var canBeAnswer = true
        for(course in registeredCourse){
            canBeAnswer = canBeAnswer && course.isOverlap(newCourse)
        }
        if(canBeAnswer) answerCourse.add(newCourse)
    }

    if(answerCourse.size==0){
        println("0")
        return
    }
    answerCourse.sortBy { it.sortScore }

    for(course in answerCourse) println(course.id)
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
    data class Cha(override val pos: Point, override val team: Boolean) : Piece(pos, team)
    data class Jol(override val pos: Point, override val team: Boolean) : Piece(pos, team)
    data class King(override val pos: Point, override val team: Boolean) : Piece(pos, team)
}

fun canPhoMoveTo(board: Array<Array<Piece?>>, next: Point): Boolean {
    // TODO : board가 주어졌을 때, next 위치로 내 포가 이동할 수 있는지 없는지 반환
    var myPhoPlace : Point? = null
    for(row in board) {
        for (column in row) if (column is Piece.Pho && column.team) myPhoPlace = column.pos
    }
    if(myPhoPlace?.x == next.x){
        if(myPhoPlace.y < next.y){
            var bridge : Piece? = null
            var piecesInPath = 0
            for(i in (myPhoPlace.y+1) until next.y){
                if(board[myPhoPlace.x][i] != null) {
                    piecesInPath++
                    bridge = board[myPhoPlace.x][i]!!
                }
            }
            return piecesInPath==1 && bridge !is Piece.Pho
        }
        else{
            var bridge : Piece? = null
            var piecesInPath = 0
            for(i in (next.y+1) until myPhoPlace.y){
                if(board[myPhoPlace.x][i] != null) {
                    piecesInPath++
                    bridge = board[myPhoPlace.x][i]!!
                }
            }
            return piecesInPath==1 && bridge !is Piece.Pho
        }
    }
    else if(myPhoPlace?.y == next.y){
        if(myPhoPlace.x < next.x){
            var bridge : Piece? = null
            var piecesInPath = 0
            for(i in (myPhoPlace.x+1) until next.x){
                if(board[i][myPhoPlace.y] != null) {
                    piecesInPath++
                    bridge = board[i][myPhoPlace.y]!!
                }
            }
            return piecesInPath==1 && bridge !is Piece.Pho
        }
        else{
            var bridge : Piece? = null
            var piecesInPath = 0
            for(i in (next.x+1) until myPhoPlace.x){
                if(board[i][myPhoPlace.y] != null) {
                    piecesInPath++
                    bridge = board[i][myPhoPlace.y]!!
                }
            }
            return piecesInPath==1 && bridge !is Piece.Pho
        }
    }
    return false
}
```

