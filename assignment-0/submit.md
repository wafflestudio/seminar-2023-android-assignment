# 과제 제출
`seminar-2023-andoid-assignment` 레포를 clone한 후, 이 파일의 아래 부분을 수정해서 PR로 올려 주세요.

## 1. 설치 스크린샷

![image](https://github.com/snupinel/seminar-2023-android-assignment/assets/142738524/a1e1b7ff-c00c-4ce9-9869-45289292e1a2)

## 2. SNUTT 강의 찾기 코드

여기를 채워 주세요.
```kotlin
data class Lec(val _id: Int, val _day: Int,val _start: Int,val _end: Int) {//강의 정보를 가지는 클래스
    val id=_id
    val day=_day
    val start=_start
    val end=_end
}   

var myLecs: MutableList<Lec> = mutableListOf()//현재 내 시간표 전역변수 배열

fun main(){
    //n과 m입력받음
    val n:Int
    val m:Int
    var input=readLine()!!
    n=input.split(' ')[0].toInt()
    m=input.split(' ')[1].toInt()
    
    //현재 내 시간표 입력받음
    for (number in 1..n) { 
        input=readLine()!!
        var adding:Lec=Lec(input.split(' ')[0].toInt(),input.split(' ')[1].toInt(),input.split(' ')[2].toInt(),input.split(' ')[3].toInt())
        myLecs.add(adding)
    }
    
    //다른 강의들 입력받음
    var newLecs: MutableList<Lec> = mutableListOf()
    for (number in 1..m) { 
        input=readLine()!!
        var adding:Lec=Lec(input.split(' ')[0].toInt(),input.split(' ')[1].toInt(),input.split(' ')[2].toInt(),input.split(' ')[3].toInt())
        newLecs.add(adding)
    }
    
    //겹치지 않는 강의들만 정렬해서 저장
    val result = newLecs.filter{
        it.possible()==1
    }.sortedBy{it.id}.sortedBy{it.end}.sortedBy{it.start}.sortedBy{it.day}
    
    //출력
    if(result.size==0){
        print(0)
    }else{
        for(i in result){
            println(i.id)
        }
    }
    
}
fun Lec.possible():Int {//해당 강의가 내 시간표와 겹치는지 검사하는 확장 함수
    var poss=1//1은 가능, 0은 불가능
    for (i in myLecs){
        if(i.day!=this.day){
            continue
        }else if(!(i.end<this.start||this.end<i.start)){
            poss=0
        }
        
    }
    return poss
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
    // data class Jol( ... )
    // data class King( ... )
}

fun canPhoMoveTo(board: Array<Array<Piece?>>, next: Point): Boolean {
    // TODO : board가 주어졌을 때, next 위치로 내 포가 이동할 수 있는지 없는지 반환
    return true
}
```

