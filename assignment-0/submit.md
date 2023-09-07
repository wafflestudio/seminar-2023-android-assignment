# 과제 제출
`seminar-2023-andoid-assignment` 레포를 clone한 후, 이 파일의 아래 부분을 수정해서 PR로 올려 주세요.

## 1. 설치 스크린샷

깃허브 에디터에 스크린샷 이미지를 드래그 드롭 하면 이미지를 첨부할 수 있습니다. 여기에 넣어 주세요!
<img width="1280" alt="screenshot" src="https://github.com/cherry-0/seminar-2023-android-assignment-0/assets/80082633/85f9755f-9fb5-43be-92c4-0e7e0b031e1b">

## 2. SNUTT 강의 찾기 코드

여기를 채워 주세요.
```kotlin
data class Lecture(val start_time:Int, val end_time:Int, val day:Int, val id:Int)

fun can_coexist(x:Lecture, y:Lecture):Boolean{
    // already in: x, candidate: y
    if (x.start_time == y.start_time && x.end_time ==y.end_time && x.day == y.day){ //same w.o. id
    	return false 
    }
    if (x.day != y.day){
    	return true
    }
    if (x.end_time < y.start_time || y.end_time < x.start_time){
        return true
    }
    return false
}

fun can_insert(my_table:List<Lecture>, lecture:Lecture):Boolean{
    var cando=true
    for (lec in my_table){
        if (can_coexist(lec, lecture)==false){
            cando=false
            break
        }
    }
    return cando
}

fun main() {
    var nm = readLine()
    val nmSplit = nm?.split(" ")
    //println(nmSplit)
    val N = nmSplit?.getOrNull(0)?.toInt()
    val M = nmSplit?.getOrNull(1)?.toInt()
    
    val userLectures : MutableList<Lecture> = mutableListOf()
    for (i in 0 until (N ?: 0)) {
        val lecture = readLine()!!.split(" ").map { it.toInt() }
        val I = lecture[0]
        val D = lecture[1]
        val S = lecture[2]
        val E = lecture[3]

        require(I in 1..1000) { "Wrong lecture number." }
        require(D in 1..7) { "Wrong day number." }
        require(S in 0..E) { "Wrong lecture time information." }
        require(E <= 23) { "Wrong lecture end time." }

        userLectures.add(Lecture(id=I, day=D, start_time = S, end_time =E))
    }

    val allLectures : MutableList<Lecture> = mutableListOf()
    for (i in 0 until (M ?: 0)) {
        val lecture = readLine()!!.split(" ").map { it.toInt() }
        val I = lecture[0]
        val D = lecture[1]
        val S = lecture[2]
        val E = lecture[3]

        require(I in 1..1000) { "Wrong lecture number." }
        require(D in 1..7) { "Wrong day number." }
        require(S in 0..E) { "Wrong lecture time information." }
        require(E <= 23) { "Wrong lecture end time." }

        allLectures.add(Lecture(id=I, day=D, start_time = S, end_time =E))
    }

    
    val validLectures = allLectures.filter{ x -> can_insert(userLectures,x)==true }

    if (validLectures.isNotEmpty()) {
        val sortedValidLectures = validLectures.sortedWith(compareBy<Lecture> {it.day}.thenBy{it.start_time}.thenBy{it.end_time})
        for (lecture in sortedValidLectures) {
            println(lecture.id)
        }
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
    // TODO
    // data class Cha( ... )
    data class Cha(override val pos: Point, override val team: Boolean) : Piece(pos, team)
    // data class Jol( ... )
    data class Jol(override val pos: Point, override val team: Boolean) : Piece(pos, team)
    // data class King( ... )
    data class King(override val pos: Point, override val team: Boolean) : Piece(pos, team)
}

fun canPhoMoveTo(board: Array<Array<Piece?>>, next: Point): Boolean {
    // TODO : board가 주어졌을 때, next 위치로 내 포가 이동할 수 있는지 없는지 반환
    // 
    return true
}

fun main(){
    //input - Array 
    //
    //
    //
}
```

