# 과제 제출
`seminar-2023-andoid-assignment` 레포를 clone한 후, 이 파일의 아래 부분을 수정해서 PR로 올려 주세요.

## 1. 설치 스크린샷

깃허브 에디터에 스크린샷 이미지를 드래그 드롭 하면 이미지를 첨부할 수 있습니다. 여기에 넣어 주세요!
![KakaoTalk_20230906_135210476](https://github.com/kimhaneal11/seminar-2023-android-assignment/assets/84558752/6b69bd05-701a-4f6e-ae30-fc50b9d60698)


## 2. SNUTT 강의 찾기 코드

여기를 채워 주세요.
```kotlin
fun main() {
    data class Lecture(
        var num : Int = 0,
        var day : Int = 0,
        var start : Int = 0,
        var end : Int = 0
    ){
        constructor(str : String) : this() {
            val line = str.split(" ")
            this.num = line[0].toInt()
            this.day = line[1].toInt()
            this.start = line[2].toInt()
            this.end = line[3].toInt()
        }


        fun isPossible(userLecture:List<Lecture>):Boolean{
            for(user in userLecture){
                if(this.day==user.day){
                    if(this.start>=user.start && this.start<=user.end) return false
                    if(this.end>=user.start && this.end<=user.end) return false
                }
            }
            return true
        }
    }

    fun filterList(userLecture:List<Lecture>, totalLecture:MutableList<Lecture>): MutableList<Lecture> {
        var lst : MutableList<Lecture> = totalLecture.filter{it.isPossible(userLecture)}.toMutableList()
        return lst
    }

    val input = readLine()!!.split(" ")
    val N : Int = input[0].toInt()
    val M : Int = input[1].toInt()

    val userLecture : MutableList<Lecture> = mutableListOf()
    val totalLecture : MutableList<Lecture> = mutableListOf()

    for(i in 1..N){
        var lecture : Lecture = Lecture(readLine()!!)
        userLecture.add(lecture)
    }

    for(i in 1..M){
        var lecture : Lecture = Lecture(readLine()!!)
        totalLecture.add(lecture)
    }

    var filteredLecture : MutableList<Lecture> = filterList(userLecture, totalLecture)
    filteredLecture.sortWith(compareBy<Lecture>{it.day}.thenBy {it.start}.thenBy {it.end}.thenBy {it.num})
    if(filteredLecture.size==0) println(0)
    else{
        for(i in 1..filteredLecture.size){
            println(filteredLecture[i-1].num)
        }
    }
}
```

## 3. 쌍포는 와플도 녹인다 코드

여기를 채워 주세요.
```kotlin
import kotlin.math.max
import kotlin.math.min

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
    //1. 포의 현재 위치
    var x : Int = -1
    var y : Int = -1
    var finish = false
    for(line in board) {
        for (mal in line) {
            when (mal) {
                null -> continue
                is Piece.Pho -> {
                    x = mal.pos.x
                    y = mal.pos.y
                    finish = true
                }
                else -> continue
            }
            if(finish) break
        }
        if(finish) break
    }

    //2. 포의 현재 위치와 next 위치가 같은 줄인지 확인
    var check = 0//0-위아래, 1-왼오
    if(x==next.x) check = 1
    else if(y==next.y) check = 0
    else return false

    //3. 포의 현 위치와 다음 위치 사이에 말이 하나만 있는지 확인
    var meet = 0
    var start = 0
    var end = 0

    //3-1. 위아래 이동
    if(check==0){
        start = min(x, next.x)
        end = max(x, next.x)
        for(i in start+1..end-1) {
            var mal: Piece? = board[i][y]
            when (mal) {
                null -> continue
                is Piece.Pho -> return false//사이에 상대 포 있으면 이동 불가
                else -> meet++
            }
        }
        if(meet!=1) return false//사이에 말이 하나만 있어야 이동 가능
    }

    //3-2. 왼오 이동
    else{
        start = min(y, next.y)
        end = max(y, next.y)
        for(i in start+1..end-1) {
            var mal: Piece? = board[x][i]
            when (mal) {
                null -> continue
                is Piece.Pho -> return false//사이에 상대 포 있으면 이동 불가
                else -> meet++
            }
        }
        if(meet!=1) return false//사이에 말이 하나만 있어야 이동 가능
    }

    //다 통과하면 이동 가능!
    return true
}
```

