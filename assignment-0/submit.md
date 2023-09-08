# 과제 제출
`seminar-2023-andoid-assignment` 레포를 clone한 후, 이 파일의 아래 부분을 수정해서 PR로 올려 주세요.

## 1. 설치 스크린샷
![과제스샷](https://github.com/plgafhd/seminar-2023-android-assignment/assets/141345525/f8c44220-37c7-436e-9320-2b8b6585a048)


## 2. SNUTT 강의 찾기 코드

```kotlin
package com.example.myfirstapp

data class Class(val classid:Int, val dotw:Int, val start:Int, val finish:Int)

fun Class.checkOverlap(others:MutableList<Class>):Boolean{
    var result:Boolean=false
    for(other in others){
        if(this.dotw==other.dotw){
            if(this.start>other.finish || other.start>this.finish) result=false
            else{
                result=true
                break
            }
        }
    }
    return result
}

fun main() {
    val table: MutableList<Class> = mutableListOf()
    val toadd: MutableList<Class> = mutableListOf()
    var k = readLine()!!.split(" ").map { it.toInt() }
    val n1 = k[0]
    val n2 = k[1]

    for (i in 1..n1) {
        k = readLine()!!.split(" ").map { it.toInt() }
        table.add(Class(k[0], k[1], k[2], k[3]))
    }
    for (i in 1..n2) {
        k = readLine()!!.split(" ").map { it.toInt() }
        toadd.add(Class(k[0], k[1], k[2], k[3]))
    }

    val result: MutableList<Class> = toadd.filter { !it.checkOverlap(table) }.toMutableList()
    result.sortBy { it.classid }
    result.sortBy { it.finish }
    result.sortBy { it.start }
    result.sortBy { it.dotw }

    if (result.count() > 0) {
        for (i in result) {
            println("${i.classid}")
        }
    }
    else println(0)
}
```

## 3. 쌍포는 와플도 녹인다 코드

여기를 채워 주세요.
```kotlin
package com.example.myfirstapp

import kotlin.math.min
import kotlin.math.max

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
    if (board[next.y][next.x] is Piece.Pho){
        return false
    }

    var stdloc:Point=Point(-1,-1)
    for(row in board){
        for(slot in row){
            if (slot is Piece.Pho && slot.team){
                stdloc=slot.pos
            }
        }
    }

    var cnt:Int=0
    if (stdloc.x==next.x){
        for(i in (min(next.y,stdloc.y)+1)..(max(next.y,stdloc.y)-1)){
            if (board[stdloc.x][i] is Piece){
                if (board[stdloc.x][i] is Piece.Pho){
                    return false
                }
                cnt++
                if (cnt>=2) return false
            }
        }
    }
    else if (stdloc.y==next.y){
        for(i in (min(next.x,stdloc.x)+1)..(max(next.x,stdloc.x)-1)){
            if (board[i][stdloc.y] is Piece){
                if (board[i][stdloc.y] is Piece.Pho){
                    return false
                }
                cnt++
                if (cnt>=2) return false
            }
        }
    }
    else{
        return false
    }
    if (cnt==0) return false
    return true
}
```

