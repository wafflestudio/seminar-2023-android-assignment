# 과제 제출
`seminar-2023-andoid-assignment` 레포를 clone한 후, 이 파일의 아래 부분을 수정해서 PR로 올려 주세요.

## 1. 설치 스크린샷

![image](https://github.com/snupinel/seminar-2023-android-assignment/assets/142738524/a1e1b7ff-c00c-4ce9-9869-45289292e1a2)

## 2. SNUTT 강의 찾기 코드

여기를 채워 주세요.
```kotlin
fun main() {

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

