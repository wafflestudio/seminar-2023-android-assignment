# Wafflestudio Android Seminar Assignment0

## 과제의 목표
- 안드로이드 스튜디오 설치
- 첫 세미나의 원활한 진행을 위해 환경설정 및 각종 dependency 다운로드 미리 완료하기
- 코틀린 기본 문법 익히기


# 과제 0-a. 안드로이드 스튜디오 설치하기

현재 공식 버전은 [Giraffe](https://developer.android.com/studio)입니다. 이걸 다운받으셔도 되고, 조금 더 최신 버전을 받고 싶으시다면 [Preview 버전(Hedgehog)](https://developer.android.com/studio/preview)를 다운받아 주세요.
위 링크에서 Beta build (Hedgehog)를 설치하시면 됩니다.
<details>
<summary>스크린샷</summary>
<div markdown="1">

<img src="https://github.com/JuTaK97/JuTaK97.github.io/assets/88367636/208c11f8-a5de-4a98-9fbb-43b1e7d1956b" width=800 />

</div>
</details>

- Preview 안내 글 : [https://developer.android.com/studio/preview/install-preview?hl=ko](https://developer.android.com/studio/preview/install-preview?hl=ko)

## 첫 프로젝트 만들기
1. New Project를 눌러 줍니다.
    <details>
    <summary>스크린샷</summary>
    <div markdown="1">
    
    <img src="https://github.com/JuTaK97/JuTaK97.github.io/assets/88367636/760691aa-ccbe-44a1-8a0e-0d6863bd15e6" width=800 />
    
    </div>
    </details>
2. 선택지가 참 많은데, Empty Views Activity를 골라 줍니다(지금은 아직 보라색 고르시면 안 됩니다!). Activity가 뭔지는 모르겠지만 우선 넘어갑시다.
    <details>
    <summary>스크린샷</summary>
    <div markdown="1">
    
    <img src="https://github.com/wafflestudio/seminar-2023-android-assignment/assets/68140623/0f33971c-5a24-4a70-bacb-8f590b71b556)" width=800 />
    
    </div>
    </details>
3. 적당히 프로젝트의 이름을 적어 주고 finish를 눌러 줍니다.
    <details>
    <summary>스크린샷</summary>
    <div markdown="1">
    
    <img src="https://github.com/JuTaK97/JuTaK97.github.io/assets/88367636/b515ef4d-5114-436d-a6f2-03cfbb24769d" width=800 />
    <img src="https://github.com/JuTaK97/JuTaK97.github.io/assets/88367636/2c1bb76f-4736-4ddd-bc71-fd6344b36942" width=800 />
    
    </div>
    </details>
4. Gradle project sync in progress... 알림이 사라질 때까지 대기합니다(우하단 프로그레스 바가 없어지면 됩니다). 꽤 오래 걸립니다. (제 똥컴 기준으로 약 15분 소요)
    <details>
    <summary>스크린샷</summary>
    <div markdown="1">
    
    <img src="https://github.com/JuTaK97/JuTaK97.github.io/assets/88367636/a64e0182-7a6a-4c8d-8e7d-dc098f5b2fe7" width=800 />
    
    </div>
    </details>
5. 최근에 갑자기 UI를 vscode느낌으로 바꿔서 제공하는데, 여러분과 저의 화면이 동일하면 좋으니 Switch to Classic UI를 눌러 줍시다.
    <details>
    <summary>스크린샷</summary>
    <div markdown="1">
    
    <img src="https://github.com/JuTaK97/JuTaK97.github.io/assets/88367636/a8317647-f968-4713-8f6c-0cd726898e5d" width=800 />
    
    </div>
    </details>
6. MainActivity.kt라는 것이 생겨 있지만, 일단 신경쓰지 않고 좌상단의 초록색 재생 버튼을 누릅니다.
7. 윈도우 컴퓨터라면 높은 확률로 팬이 돌아가기 시작하고... 조금 기다리면 오른쪽에 휴대폰 화면이 등장합니다. Hello World! 라는 글씨가 보이면 완료입니다. 이 상태의 스크린샷을 찍어 주세요(과제 제출용). 그리고 이 상태로 첫 번째 세미나에 오시면 됩니다! 시간이 남으신다면 [여기](https://developer.android.com/courses/android-basics-kotlin/unit-1)에서 앞으로 배울 것들을 미리 공부해 보셔도 됩니다.
    <details>
    <summary>스크린샷</summary>
    <div markdown="1">
    
    <img src="https://github.com/JuTaK97/JuTaK97.github.io/assets/88367636/a9e47253-f143-49e2-a88f-5949f90c1e52" width=800 />
    
    </div>
    </details>


# 과제 0-b. 코틀린 기초 문법 익히기

### Kotlin 파일 실행하는 법
안드로이드 스튜디오(이하 안스)를 설치하셨으면 kt 파일을 안스에서 실행할 수 있습니다.
적당히 MainActivity.kt 옆에 Main.kt 파일을 만들어 주고 
```kotlin
fun main() {
  println("Hello World!")
}
```
를 입력해 주시면 라인 번호 옆에 초록색 재생 버튼이 생깁니다. 누르시면 main 함수를 실행할 수 있습니다.

더욱 가볍게 코틀린을 실행해 보고 싶으시면, 웹 사이트 [https://play.kotlinlang.org/](https://play.kotlinlang.org/)에서도 코틀린 파일을 실행할 수 있습니다.(그런데 자동완성이 안 돼서 불편합니다)

### 코틀린 기초 문법 공부하기
1. [https://kotlinlang.org/docs/kotlin-tour-hello-world.html](https://kotlinlang.org/docs/kotlin-tour-hello-world.html)
위 사이트에서 1번(Hello World) 부터 7번(Null Safety)까지 진행해 주세요.

2. [https://play.kotlinlang.org/byExample/01_introduction/01_Hello%20world](https://play.kotlinlang.org/byExample/01_introduction/01_Hello%20world) 에서 아래 부분을 제외하고 읽어 보시는 것을 추천드립니다.

    지금 당장은 안 읽어도 되는 부분 :
    - Introduction 챕터: Generics
    - Special Classes 챕터 : Object Keyword
    - Functional 챕터: 이해가 가지 않기 시작하기 전까지
    - Scope Functions 챕터 : let 외 나머지 (다 공부해도 좋습니다)
    - Delegation 챕터 아래부터 쭉 (Kotlin/JS 챕터까지)

## 과제: 코테 문제 Kotlin으로 다시 풀기

### 1. SNUTT 강의 찾기

#### 풀이 조건
1. data class 사용하기
2. filter 혹은 filterNot 사용하기
4. sortWith 사용하기
5. 1번에서 만든 data class의 extension function 만들어서 사용하기. 두 강의가 서로 겹치는지 안겹치는지 반환하는 함수를 만들면 됩니다.

테스트케이스는 아래 제공드리는 것만 돌려 보시면 됩니다. (테케가 맞았냐 틀렸냐는 저는 안 볼거고, 문법을 예쁘고 멋지게 잘 썼는지만 볼 예정입니다)

<details>
<summary>테케 1</summary>
<div markdown="1">

```
input

3 3
1 1 2 3
3 1 4 6
10 2 3 7
7 3 3 5
6 3 3 6
5 3 3 7

output

7
6
5
```

</div>
</details>


<details>
<summary>테케 2</summary>
<div markdown="1">

```
input

0 3
1 1 3 5
3 1 6 8
5 4 3 9

output

1
3
5
```

</div>
</details>

<details>
<summary>테케 3</summary>
<div markdown="1">

```
input

3 0
1 1 2 3
3 1 4 6
5 2 3 7

output

0
```

</div>
</details>

<details>
<summary>테케 4</summary>
<div markdown="1">

```
input

4 5
10 1 2 3
9 1 4 6
11 2 3 7
13 4 3 10
17 1 2 3
1 1 4 6
7 2 3 7
6 4 3 10
5 5 1 9

output

5
```

</div>
</details>

<details>
<summary>테케 5</summary>
<div markdown="1">

```
input

1 3
5 1 3 5
4 1 6 8
2 1 6 8
1 4 3 9

output

2
4
1
```

</div>
</details>

### 2. 쌍포는 와플도 녹인다 문제
걱정 마세요! 아래 스켈레톤 코드를 바탕으로 TODO 부분만 완성해 주시면 됩니다.
참고로 **장군과 궁성(대각선 경로들)은 전부 고려하지 말고**, 오직 포의 이동 가능 여부만 고려하시면 됩니다. 1번과 동일하게 로직이 맞냐 틀리냐 보다는 공부한 코틀린 문법들을 잘 사용했는지를 볼 것입니다.

#### 풀이 조건
1. sealed class를 사용한다.
2. nullable type을 사용한다. (+ 다양한 [null 관련 문법들](https://play.kotlinlang.org/byExample/01_introduction/04_Null%20Safety)) 
3. when 문법을 활용한다.

풀이 조건 이외에도 앞서 튜토리얼에서 공부한 코틀린 문법을 최대한 많이 다양하게 사용해 보세요!

<details>
<summary>스켈레톤 코드</summary>
<div markdown="1">

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

</div>
</details>

<br /> <br />


# 수정 사항
(8/30 22시) SNUTT 강의 찾기 풀이 조건에서 `sortBy`를 `sortWith`로 변경했습니다. `sorted()` 라는 것도 쓸 수 있는데 어떻게 하면 될까요?
