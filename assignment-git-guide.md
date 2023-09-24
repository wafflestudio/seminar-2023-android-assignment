## 과제 제출 하는 법 (feat. Git)

여러분들은 seminar-2023-android-assignment 브랜치를 fork하셨을 것입니다. 그리고 본인의 컴퓨터에 포크한 레포를 clone 하셨겠지요?

### 1. upstream 설정하기
각자의 레포 위치에서 터미널을 켜서, `git remote -v`를 눌러 봅시다.

만약 다음과 같이

<img width="610" alt="image" src="https://github.com/wafflestudio/seminar-2023-android-assignment/assets/88367636/0eb59af7-f89e-4d51-964f-0b54524344c2">

`origin	git@github.com:{자기닉네임}/seminar-2023-android-assignment.git (fetch)`로 나온다면, 여러분의 로컬 깃은 여러분이 fork한 원격 레포를 원격 레포로 바라보고 있는 것입니다.
혹시 만약에 이미 아래와 같이 upstream이 원본 레포로 되어 있으면 2번으로 스킵하시면 됩니다.

<img width="698" alt="image" src="https://github.com/wafflestudio/seminar-2023-android-assignment/assets/88367636/fd3f4c81-083e-4508-9fd3-7ae5af358313">


```
git remote add upstream https://github.com/wafflestudio/seminar-2023-android-assignment
```
를 하고 나서 
```
git remote -v
```
를 하면, 다음과 같이 origin (여러분의 레포)와 upstream (원본 레포)가 보일 겁니다.
<img width="627" alt="image" src="https://github.com/wafflestudio/seminar-2023-android-assignment/assets/88367636/1cee6eaa-7640-4730-939d-68840f531dd2">

## 2. upstream의 브랜치로 checkout하기
```
git fetch upstream
git checkout assignment2
git push --set-upstream origin 
```
를 해 주면 fork한 레포에 assignment2 브랜치가 올라갑니다.

## 3. 작업 사항 푸시하기
이제부터 이 assignment2 브랜치에서 작업을 하면 Github Desktop에서는 아래와 같이 보입니다.

<img width="744" alt="image" src="https://github.com/wafflestudio/seminar-2023-android-assignment/assets/88367636/e604fc0d-af5a-4f0f-b1ea-a86220ae8c88">


이제 저 push 버튼을 누르거나 `git push`를 해 주면 fork한 레포에서 커밋을 확인할 수 있습니다. (만약 권한 어쩌구 오류가 뜨면, `git push --set-upstream origin`을 해 주세요)

<img width="1443" alt="image" src="https://github.com/wafflestudio/seminar-2023-android-assignment/assets/88367636/e00b70a6-a539-46bb-a4fd-63c66e45e301">

## 4. PR 만들기
이제 원본 깃헙 레포에 와서, PR을 날리려고 하면 이렇게 뜹니다. (조금 다르면 `compare across fork`를 눌러 보세요)

<img width="1441" alt="image" src="https://github.com/wafflestudio/seminar-2023-android-assignment/assets/88367636/abeb74df-6c80-449b-83a8-7fc06d524300">

여기에서 base branch를 wafflestidio의 assignment2 브랜치로, head repository는 자기가 fork한 레포의 assignment2 브랜치로 지정해서 PR을 생성하면 완료!
