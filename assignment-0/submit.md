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

fun canPhoMoveTo(board: Array<Array<Piece?>>, next: Point, ourCha: Piece, thatCha: Piece): Boolean {
    // TODO : board가 주어졌을 때, next 위치로 내 포가 이동할 수 있는지 없는지 반환
    if (next.x !in 0..9 || next.y !in 0..8){
        return false
    //check if ourPho is in same row/same col
    var isThere =false
    var rowcol = ""
   	for (y in 0..8){
        if(board[next.x][y]!=null && board[next.x][y]==ourCha){
            isThere = true
            rowcol = "row"
        }
    }
    for (x in 0..9){
        if(board[x][next.y]!=null && board[x][next.y]==ourCha){
            isThere = true
            rowcol = "col"
        }
    }
    if (isThere == false){
        return false
    }
    
    //now true
    if(rowcol == "row"){
        //same x, diff y 
       	val small = when{
            next.y < ourCha.pos.y -> next.y
            next.y > ourCha.pos.y -> ourCha.pos.y
        }
        val big = when{
            next.y > ourCha.pos.y -> next.y
            next.y < ourCha.pos.y -> ourCha.pos.y
        }
        var numOfPieces = 0
        for (y in small+1..<big){
            if(board[next.x][y] !=null && board[next.x][y] != thatCha){
                numOfPieces ++
            }
        }
        if (numOfPieces !=1){
            return false
        }
    }
    else{ // col
        //same y, diff x
       	val small = when{
            next.x < ourCha.pos.x -> next.x
            next.x > ourCha.pos.x -> ourCha.pos.x
        }
        val big = when{
            next.x > ourCha.pos.x -> next.x
            next.x < ourCha.pos.x -> ourCha.pos.x
        }
        var numOfPieces = 0
        for (x in small+1..<big){
            if(board[x][next.y] !=null && board[x][next.y] != thatCha){
                numOfPieces ++
            }
        }
        if (numOfPieces !=1){
            return false
        }
    }
    

    return true
}

fun main(){
    //input - Array 
    //
    var board: Array<Array<Piece?>> = Array(9) {Array(10) {null}}
    //our side
    var Pho = readLine()
    val ourPhosplit = Pho?.split(" ")
    val ourPhoX:Int = ourPhosplit?.getOrNull(0)?.toInt() ?:-1
    val ourPhoY:Int = ourPhosplit?.getOrNull(1)?.toInt() ?:-1
    val ourPho = Piece.Pho(Point(ourPhoX, ourPhoY), true)
    
    var Cha = readLine()
    val ourChasplit = Cha?.split(" ")
    val ourChaX:Int = ourChasplit?.getOrNull(0)?.toInt() ?:-1
    val ourChaY:Int = ourChasplit?.getOrNull(1)?.toInt() ?:-1
    val ourCha = Piece.Cha(Point(ourChaX, ourChaY), true)
    
    var Jol = readLine()
    val ourJolsplit = Jol?.split(" ")
    val ourJolX:Int = ourJolsplit?.getOrNull(0)?.toInt()?:-1
    val ourJolY:Int = ourJolsplit?.getOrNull(1)?.toInt()?:-1
    val ourJol = Piece.Jol(Point(ourJolX, ourJolY), true)
    
    var King = readLine()
    val ourKingsplit = King?.split(" ")
    val ourKingX :Int = ourKingsplit?.getOrNull(0)?.toInt()?:-1
    val ourKingY :Int = ourKingsplit?.getOrNull(1)?.toInt()?:-1
    val ourKing = Piece.King(Point(ourKingX, ourKingY), true)
    
    //that side
    Pho = readLine()
    val thatPhosplit = Pho?.split(" ")
    val thatPhoX:Int = thatPhosplit?.getOrNull(0)?.toInt() ?:-1
    val thatPhoY:Int = thatPhosplit?.getOrNull(1)?.toInt() ?:-1
    val thatPho = Piece.Pho(Point(thatPhoX, thatPhoY), false)
    
    Cha = readLine()
    val thatChasplit = Cha?.split(" ")
    val thatChaX:Int = thatChasplit?.getOrNull(0)?.toInt() ?:-1
    val thatChaY:Int = thatChasplit?.getOrNull(1)?.toInt() ?:-1
    val thatCha = Piece.Cha(Point(thatChaX, thatChaY), false)
    
    Jol = readLine()
    val thatJolsplit = Jol?.split(" ")
    val thatJolX:Int = thatJolsplit?.getOrNull(0)?.toInt()?:-1
    val thatJolY:Int = thatJolsplit?.getOrNull(1)?.toInt()?:-1
    val thatJol = Piece.Jol(Point(thatJolX, thatJolY), false)
    
    King = readLine()
    val thatKingsplit = King?.split(" ")
    val thatKingX :Int = thatKingsplit?.getOrNull(0)?.toInt()?:-1
    val thatKingY :Int = thatKingsplit?.getOrNull(1)?.toInt()?:-1
    val thatKing = Piece.King(Point(thatKingX, thatKingY), false)
    
    //update board
    board[ourPho.pos.x][ourPho.pos.y] = ourPho
    board[ourCha.pos.x][ourCha.pos.y] = ourCha
    board[ourJol.pos.x][ourJol.pos.y] = ourJol
    board[ourKing.pos.x][ourKing.pos.y] = ourKing
    board[thatPho.pos.x][thatPho.pos.y] = thatPho
    board[thatCha.pos.x][thatCha.pos.y] = thatCha
    board[thatJol.pos.x][thatJol.pos.y] = thatJol
    board[thatKing.pos.x][thatKing.pos.y] = thatKing
    
    //
    //check all positions
    for(i in 0..9){
        for (j in 0..8){
            if (canPhoMoveTo(board, Point(i,j), ourCha, thatCha)==true){
                println("$i $j")
            }
        }
    }
}
```

