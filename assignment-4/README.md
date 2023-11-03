# Android Seminar Assignment 4 : 영화 정보 앱

## 과제 기한
2023년 11월 16일(목) 자정까지 (목~금 넘어가는 밤)

## 과제의 목표
- 서버와 HTTP 통신으로 헤더에 정보를 넣어 요청과 응답을 주고받기
- 통신 에러 시 catch 후 에러 메시지 받아서 처리하기
- Fragment 이용해 UI 구성하기
- SharedPreference 사용하기

# 과제 요약
- TMDB api를 이용해 간단한 영화 정보 앱을 만듭니다.
- 이번 과제 앱에는 단 하나의 액티비티(MainActivity)만이 존재합니다.
- Jetpack Navigation으로 Login, Home, Detail의 경로를 구성합니다.
- 디자인은 데모와 달라도 상관없고, 최소 스펙의 내용만 채점합니다. (그래도 예쁘게 해보세요)

## TMDB api
https://developer.themoviedb.org/reference/intro/getting-started

API 관련 질문이 있으면 말씀해 주세요.

# 최소 스펙
- 통신 에러 시 크래시가 발생하지 않아야 합니다.

### 1. LoginFragment
로그인 화면에서는 간단한 API KEY 인증만 진행합니다.

- 여러분의 API KEY는 TMDB 사이트에서 확인할 수 있습니다.
- API KEY를 입력받고, 입력받은 KEY가 올바른지 [authenticate](https://developer.themoviedb.org/reference/intro/getting-started)를 진행합니다.
- 성공적으로 인증되면 이 토큰을 sharedPreference에 저장하고 HomeFragment로 이동합니다.
- 다음 번에 앱을 켤 경우, LoginFragment를 거치지 않고 바로 HomeFragment에서 앱이 시작합니다. (즉, sharedPreference에 토큰이 있을 경우 HomeFragment에서 앱이 시작합니다.)
- HomeFragment로 한번 이동하면, 뒤로가기를 했을 때 LoginFragment로 돌아오지 않습니다. (힌트 : popUpTo, popUpToInclusive)

### 2. HomeFragment
- [ViewPager2](https://developer.android.com/training/animation/screen-slide-2?hl=ko)를 이용해 영화 포스터와 간단한 정보를 표시합니다.
- [Popular movie API](https://developer.themoviedb.org/reference/movie-popular-list)를 이용해 인기 영화 목록을 가져오고, 상위 5개 아이템을 표시합니다.
- Glide 라이브러리를 이용해 포스터 이미지를 크게 표시하고, 간단한 정보들을 하단에 표시합니다.
- 상단 로그아웃 버튼을 누르면 sharedPreference에서 토큰을 제거하고 LoginFragment로 되돌아갑니다.
- 한번 로그아웃하면 뒤로가기를 했을 때 HomeFragment로 돌아와선 안 됩니다.

# 추가 스펙

### HomeFragment
- 하단의 `자세히 보기` 버튼을 누르면 DetailFragment로 이동합니다.

### 3. DetailFragment
- 추후 공지


# 챌린지 스펙
- TMDB api에서는 HTTP status가 40X일 경우 일관된 형태의 error를 보내줍니다.
  ```json
    {
      "status_code": 7,
      "status_message": "Invalid API key: You must be granted a valid key.",
      "success": false
    }
  ```
  CallAdapter과 Moshi를 이용해서, Retrofit 단에서 에러를 data class로 포장해서 throw하도록 해 봅시다.

# 데모

| 단어장 목록 화면 | 단어장 상세 화면 |
| ---- | ---- |
| <video src="" /> | <video src="" /> |



# 과제 제출 방법
[가이드 << 꼭 읽어보세요](https://github.com/wafflestudio/seminar-2023-android-assignment/blob/main/assignment-git-guide.md)

1. assignment 레포지토리를 fork한다.
2. 로컬에 clone한 후, `assignment4`브랜치에서 개발을 진행한다. **(매우매우 중요!!!!!!!!!)**
3. 개발을 진행하며 commit하고 fork한 레포지토리에 push한다.
4. 개발 완료 후 기존 assignment 레포지토리에 pull request를 날린다.