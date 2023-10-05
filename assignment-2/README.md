# Android Seminar Assignment 2 : Tic-Tac-Toe 게임

## 과제 기한
2023년 10월 5일(목) 자정까지  (목~금 넘어가는 밤)

## 과제의 목표
- ViewModel을 이용해서 시스템 변화에 의해 상태가 손실되지 않게 하기
- MVVM 패턴을 이용해서 뷰와 로직을 완전히 분리하기
- RecyclerView를 이용해서 복잡한 UI 구현하기

# 과제 상세
- 틱택토 게임을 구현합니다.
- 앱은 게임 화면과 게임 히스토리 화면으로 구성됩니다.
- 스펙에 명시되지 않은 자잘한 부분은 자유롭게 구현하시면 됩니다.
- 디자인은 데모와 달라도 상관없고, 최소 스펙의 내용만 채점합니다. (그래도 예쁘게 해보세요)
- 이번에는 챌린지 스펙이 있습니다. 도전해보세요!

# 최소 스펙

### 1. DrawerLayout을 사용해서 UI를 구성한다.

   > 초간단 사용법
   ```xml
   <!-- activity_main.xml -->
   <?xml version="1.0" encoding="utf-8"?>
   <androidx.drawerlayout.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

   <!-- 이 부분에는 메인 화면의 UI를 넣습니다. -->
   
    <com.google.android.material.navigation.NavigationView
        android:id="@+id/drawer"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_gravity="start">

       <!-- 이 부분에는 서랍 안 화면의 UI를 넣습니다. -->

    </com.google.android.material.navigation.NavigationView>

   </androidx.drawerlayout.widget.DrawerLayout>
   ```
   ```kotlin
   // drawer 열기
   binding.root.openDrawer(binding.drawer)
   ```
### 2. 메인 화면
   - 중앙에는 9개의 칸으로 구성된 틱택토 보드가 존재한다. [틱택토 게임 규칙](https://namu.wiki/w/%ED%8B%B1%ED%83%9D%ED%86%A0#toc)
   - 상단에는 서랍을 여는 버튼, 우상단 버튼이 존재한다.
   - 승자가 생기면 빈 칸을 눌러도 더 이상 게임이 진행되지 않는다.

### 3. 서랍
   해당 판의 턴 별 기록을 서랍 안에 RecyclerView를 이용해서 그린다.
   
### 4. 우상단 버튼
   - 게임이 진행 중이면, 우상단 버튼은 `초기화` 라고 표시하고, 게임이 끝났으면 `한판 더`라고 표시한다.
   - 버튼을 누르면 게임 상태가 초기화되고 처음부터 게임을 할 수 있다.
   - 초기화 버튼을 누르면, 서랍에 표시되는 기록도 초기화되어야 한다.

### 6. 상태 보존
  다크모드를 전환하거나 화면을 회전했을 때 데이터가 보존되어야 한다.

# 추가 스펙

### 1. 상태 텍스트
   - 게임의 상태에 따라 상단 중앙에 `O의 차례입니다`, `X의 차례입니다`, `게임 오버`, `무승부` 를 표시한다.
### 2. 서랍의 리스트의 각 아이템이 몇 번째 턴인지 숫자를 추가한다.
### 3. 서랍 페이지의 RecyclerView에서 여러 종류의 ViewHolder를 사용해서
 - 가장 첫 턴은 빈 보드가 아니라 `게임 시작!` 이라는 텍스트를 표시한다.
 - 마지막 턴은 각 턴의 상황 + 승자 or 무승부 텍스트를 보여 준다.

# 챌린지 스펙
- 서랍 페이지의 각 턴(마지막 턴 제외)에 `되돌아가기` 버튼을 추가한다.
- 버튼을 누르면 보드가 해당 상태로 되돌아가고, 그 턴 이후의 기록은 사라진다.
- 되돌아간 후 게임을 이어서 하면 기록은 그 위로 다시 쌓인다.

# 데모

| 메인 화면 | 서랍 화면 |
| ----- | ----- |
| <video src="https://github.com/wafflestudio/seminar-2023/assets/88367636/b81cbf11-eefc-470c-83be-58fba51c7144"/> | <video src="https://github.com/wafflestudio/seminar-2023/assets/88367636/bc8e1640-3f80-4d89-804c-40b1017954a0"/> |


# 과제 제출 방법

[가이드 << 꼭 읽어보세요](https://github.com/wafflestudio/seminar-2023-android-assignment/blob/main/assignment-git-guide.md)

1. assignment 레포지토리를 fork한다.
2. 로컬에 clone한 후, `assignment2`브랜치에서 개발을 진행한다. **(매우매우 중요!!!!!!!!!)**
3. 개발을 진행하며 commit하고 fork한 레포지토리에 push한다.
4. 개발 완료 후 기존 assignment 레포지토리에 pull request를 날린다.
