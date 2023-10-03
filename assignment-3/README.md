# Android Seminar Assignment 3 : 단어장 앱

## 과제 기한
2023년 10월 26일(목) 자정까지 (목~금 넘어가는 밤)

## 과제의 목표
- 서버와 Http 통신으로 요청과 응답, 데이터를 주고받기
- DI(Dependency Injection)을 이용해서 구성 요소 관리하기
- Asyncronous programming에 익숙해지기

# 과제 요약
- MainActivity에서는 서버에서 받아온 단어장들의 목록을 보여줍니다.
- DetailActivity에서는 단어장 내 단어 목록을 보여줍니다.
- 스펙에 명시되지 않은 자잘한 부분은 자유롭게 구현하시면 됩니다.
- 디자인은 데모와 달라도 상관없고, 최소 스펙의 내용만 채점합니다. (그래도 예쁘게 해보세요)

# 서버 API 명세
## swagger
http://ec2-13-209-69-159.ap-northeast-2.compute.amazonaws.com:8000/swagger/

## admin
http://ec2-13-209-69-159.ap-northeast-2.compute.amazonaws.com:8000/admin 에서 아이디 : `guest`, 비밀번호 : `wafflestudio`로 로그인하면 단어장 DB 상태를 조회할 수 있습니다.

# 최소 스펙

### 1. 단어장 목록 화면
   - GET 요청으로 서버에서 단어장 목록을 가져와 RecyclerView로 표시한다.
   - 각 아이템에는 단어장 소유자의 이름과 단어장 이름을 표시한다.
   - 우상단 버튼을 누르면 새 단어장 만들기 dialog가 나타난다.
      - 소유자, 이름, 비밀번호를 받아 서버에게 POST 요청을 보낸다.
      - 성공적으로 응답이 오면 새 단어장이 보이도록 목록을 갱신한다.

### 2. 단어장 상세 화면
   - 단어장 목록에서 아이템을 클릭하면 단어장 상세 화면으로 이동한다.
   - GET 요청으로 각 단어장의 세부 정보를 가져오고, 각 단어의 철자와 뜻을 리스트로 표시한다.
   - 단어를 클릭하면 나머지 정보(철자, 뜻, 유의어, 반의어, 예문)을 dialog에 표시한다.
   - 좌상단 뒤로가기 버튼을 누르면 단어장 목록 화면으로 돌아간다.

### 3. 기타
   - MVVM 구조를 사용합니다.
   - DI를 사용해서 retrofit 등 구성 요소들을 Singleton하게 관리합니다.
   - Dialog의 경우 AlertDialog 혹은 Dialog의 사용법을 검색해서 구현합니다.

# 추가 스펙
   - 우상단 `편집하기` 버튼을 누르면 비밀번호를 받는 dialog를 표시한다.
      - POST 요청을 보낸 후 비밀번호가 valid하다고 응답이 오면, 우상단의 편집하기 버튼은 사라지고 삭제/추가 두 개의 버튼이 나타난다.
      - 단어장 상세 화면에 머무르는 동안, valid하다고 인증받은 비밀번호를 아래 두 api에서 계속 사용한다.
   - 삭제 버튼을 누르면 단어장을 삭제할지 물어보는 dialog를 표시한다.
      - 확인을 누르고 DELETE 요청이 성공하면 단어장 목록 화면으로 돌아간다.
      - 이때 단어장 목록은 갱신되어야 한다.
   - 추가 버튼을 누르면 새 단어를 추가하는 dialog를 표시한다.
      - 철자, 의미, 유의어, 반의어, 예문을 입력받고 POST 요청을 보낸다.
      - 철자와 의미만 필수 항목이고 나머지는 empty여도 상관없다.
      - 요청이 성공하면 단어 목록을 갱신한다. 

# 챌린지 스펙
   - 상세 화면에서 목록 화면으로 돌아오면, 목록 화면이 그것을 감지하고 바로 목록 GET api를 쏠 수 있게 하기
      - 힌트 : registerForActivityResult
   - API 에러(400, 403 등)가 나도 크래시가 나지 않게 하기
      - 힌트 : try - catch
      - 에러의 내용을 Toast로 띄워 봅시다 (`HTTP 400 Bad Request` 이정도 내용만 띄워도 됩니다)
      - 더 고급진 에러 핸들링 및 에러 파싱은 다음 세미나에서 다룰 예정입니다.
    
# 데모

| 단어장 목록 화면 | 단어장 상세 화면 |
| ---- | ---- |
| <video src="" /> | <video src="" /> |

# 과제 제출 방법
[가이드 << 꼭 읽어보세요](https://github.com/wafflestudio/seminar-2023-android-assignment/blob/main/assignment-git-guide.md)

1. assignment 레포지토리를 fork한다.
2. 로컬에 clone한 후, `assignment3`브랜치에서 개발을 진행한다. **(매우매우 중요!!!!!!!!!)**
3. 개발을 진행하며 commit하고 fork한 레포지토리에 push한다.
4. 개발 완료 후 기존 assignment 레포지토리에 pull request를 날린다.
