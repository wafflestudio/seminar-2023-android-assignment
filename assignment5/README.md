# Android Seminar Assignment 5 : Jetpack Compose로 이것저것

## 과제 기한
2023년 12월 10일(일) 자정까지 (일~월 넘어가는 밤)

## 과제의 목표
- Jetpack Compose로 재밌는 UI 만들어 보기
- Jetpack Compose로 서버와 통신하기

# 과제 요약
- 3개의 기능으로 구성된 앱을 만듭니다. (갑자기 늘어날 수도 있습니다)
- [Jetpack Compose Navigation](https://developer.android.com/jetpack/compose/navigation?hl=ko)을 이용해서 인트로 페이지와 세가지 기능 페이지를 구성합니다.
- 첫 번째는 Jetpack Compose Tutorial입니다. [링크](https://developer.android.com/codelabs/jetpack-compose-basics?hl=ko#0)를 보고 천천히 따라해 봅시다.
- 두 번째는 TMDB api를 이용한 간단한 영화 검색 기능입니다.
- 세 번째는 Compose를 이용한 디지털 시계 만들기입니다.
- 이번 과제도 단 하나의 액티비티(MainActivity)만이 존재합니다.
- 디자인은 데모와 달라도 상관없고, 최소 스펙의 내용만 채점합니다. (그래도 예쁘게 해보세요)

## TMDB api
https://developer.themoviedb.org/reference/intro/getting-started

API 관련 질문이 있으면 말씀해 주세요. 이번에는 SharedPreference를 쓰지 말고, 본인의 API KEY를 헤더에 하드코딩 하시면 됩니다.

# 최소 스펙

### 1. 튜토리얼
- 튜토리얼을 충실히 완료해 주세요. 정말 좋은 예제입니다! [링크](https://developer.android.com/codelabs/jetpack-compose-basics?hl=ko#0)

### 2. 영화 검색 앱
- 상단에는 TextField 혹은 BasixTextField를 이용해서 검색 문구 입력 창을 만듭니다.
- [검색 API](https://developer.themoviedb.org/reference/search-movie)를 이용해 검색합니다.
- 검색된 영화들의 포스터와 제목, 평점, 개봉일을 목록에 표시합니다.
- 포스터 url은 `https://image.tmdb.org/t/p/w500/${movie.posterPath}` 로 하시면 됩니다.
- 이미지 표시에는 Glide 혹은 Coil 라이브러리를 사용하세요.
- API 결과를 컴포저블 함수로 가져올 때는, 뷰모델에 MutableStateFlow를 두고 컴포저블 함수 내에서 `.collectAsState()`를 사용하세요. (참고 : [스누티티 코드](https://github.com/wafflestudio/snutt-android/blob/a2d5f25ecc4e507cf9d0e56199554402bdd8c7b3/app/src/main/java/com/wafflestudio/snutt2/views/logged_in/home/HomePage.kt#L56))

### 3. 디지털 시계 앱
- 논리설계에서 배우는 BCD-7 segment decoder를 구현하...는 건 아닙니다(하셔도 됩니다).
- 일곱 개의 선으로 구성된 디지털 숫자 UI를 구현합니다.
- 1. State Hoisting에 충실하게, 컴포넌트를 잘 분리하여 재사용이 용이하게 만들어 보세요!
- 2. LaunchedEffect와 Delay, 그리고 무한 루프를 이용해 시간이 흘러가게 해 보세요!

# 추가 스펙

### 영화 검색 앱
- 상단에 총 몇 개의 영화가 검색되었는지 표시합니다.
- 하단에는 현재 페이지 번호와, 다음 페이지/이전 페이지로 이동하는 버튼을 만듭니다.
  - 현재 페이지가 첫 페이지이면 이전 페이지 이동 문구가 없고, 현재 페이지가 마지막 페이지이면 다음 페이지 이동 문구가 없겠죠?
- 검색된 영화 아이템을 클릭하면 리뷰 페이지로 이동합니다.
- 이때 영화 id를 인수로 하는 [이동](https://developer.android.com/jetpack/compose/navigation?hl=ko#nav-with-args)을 통해 구현해 주세요.
- [리뷰 API](https://developer.themoviedb.org/reference/movie-reviews)를 이용해 영화의 리뷰를 가져옵니다.
- 리뷰를 단 유저 이름, 평점, 리뷰 내용을 표시합니다.
- 리뷰는 기본 5줄까지만 표시하고, 5줄 이상일 경우 ... 으로 말줄임을 넣습니다. (힌트 : Text에 `overflow` 파라미터)
- 5줄 이상인 리뷰는 클릭하면 펼쳐져서 전체 내용을 볼 수 있고, 다시 클릭하면 축소됩니다.
- 리뷰 화면의 경우도 동일하게, 총 리뷰 개수, 페이지 번호, 다음/이전 페이지 이동 버튼을 만듭니다.

# 데모

| 기본 스펙 | 추가 스펙 |
| ---- | ---- |
| <video src="https://github.com/wafflestudio/seminar-2023-android-assignment/assets/88367636/54b34387-b7a3-40f7-a9f4-ef776f5cea49" /> | <video src="https://github.com/wafflestudio/seminar-2023-android-assignment/assets/88367636/20b0b506-be77-49e4-81ba-76ee960ccd0d" /> |



# 과제 제출 방법
[가이드 << 꼭 읽어보세요](https://github.com/wafflestudio/seminar-2023-android-assignment/blob/main/assignment-git-guide.md)

1. assignment 레포지토리를 fork한다.
2. 로컬에 clone한 후, `assignment-5`브랜치에서 개발을 진행한다. **(매우매우 중요!!!!!!!!!)**
3. 개발을 진행하며 commit하고 fork한 레포지토리에 push한다.
4. 개발 완료 후 기존 assignment 레포지토리에 pull request를 날린다.
