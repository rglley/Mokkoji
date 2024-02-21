# 공통 프로젝트(웹 기술) - 모꼬지(Mokkoji)

### 모꼬지 링크 : https://mokkoji.online/
<img src="/uploads/7b5575137162495153b5cc3cda0a6860/mokkoji_logo.png" width="300"/><br>
💻SSAFY 10기 2학기 공통 프로젝트💻<br>
2024.01.03 - 2024.02.16(45일)

## 목차

- [공통 프로젝트(웹 기술) - 모꼬지(Mokkoji)](#공통-프로젝트웹-기술---모꼬지mokkoji)
  - [모꼬지 링크 : https://mokkoji.online/](#모꼬지-링크--httpsmokkojionline)
  - [목차](#목차)
  - [🐳포팅 메뉴얼](#포팅-메뉴얼)
  - [💡 프로젝트 콘셉트](#-프로젝트-콘셉트)
    - [온라인으로 만나서 오래도록 간직할 수 있는 추억 쌓기](#온라인으로-만나서-오래도록-간직할-수-있는-추억-쌓기)
    - [핵심 기능](#핵심-기능)
    - [추가 기능](#추가-기능)
  - [⚙️ 서비스 기능](#️-서비스-기능)
    - [회원](#회원)
    - [행사](#행사)
    - [결과물](#결과물)
  - [🏠 서비스 레이아웃](#-서비스-레이아웃)
    - [메인 페이지](#메인-페이지)
    - [소셜 로그인](#소셜-로그인)
    - [소그룹 형성](#소그룹-형성)
    - [축하 편지 쓰기](#축하-편지-쓰기)
    - [사진 찍기](#사진-찍기)
    - [화면 배치 변경](#화면-배치-변경)
    - [참여자 목록](#참여자-목록)
    - [롤링페이퍼 생성](#롤링페이퍼-생성)
    - [포토 모자이크 생성](#포토-모자이크-생성)
  - [🛠️ 기술 스택](#️-기술-스택)
    - [Management Tool](#management-tool)
    - [IDE](#ide)
    - [Infra](#infra)
    - [Frontend](#frontend)
    - [Backend](#backend)
  - [🛠️서비스 아키텍처](#️서비스-아키텍처)
  - [🗂설계 문서](#설계-문서)
    - [Flow Chart](#flow-chart)
    - [Wire Frame](#wire-frame)
  - [💾 ERD](#-erd)
  - [📁프로젝트 구조](#프로젝트-구조)
    - [Frontend](#frontend-1)
    - [Backend](#backend-1)
  - [📃발표자료](#발표자료)
  - [👥팀 구성원](#팀-구성원)

---

## 🐳[포팅 메뉴얼](https://lab.ssafy.com/s10-webmobile1-sub2/S10P12A401/-/blob/master/exec/mokkoji_%ED%8F%AC%ED%8C%85%EB%A7%A4%EB%89%B4%EC%96%BC.pdf?ref_type=heads) 

## 💡 프로젝트 콘셉트

👨🏻‍👩🏻‍👦🏻‍👦🏻 **온라인 화상 모임 플랫폼** 👨🏻‍👩🏻‍👦🏻‍👦🏻

### 온라인으로 만나서 오래도록 간직할 수 있는 추억 쌓기

- 단순한 화상 회의 서비스에 그치지 않고, 결과물을 만들어줌으로서 하나의 추억으로 간직할 수 있는 화상 모임 플랫폼

### 핵심 기능

- 회의 주최자에게 축하 메세지(영상, 음성) 전송
- 참여자들의 축하 메세지를 기반으로 한 “롤링페이퍼” 생성
- 회의의 결과물로 “포토 모자이크” 생성

### 추가 기능

- 원하는 사람끼리 채팅 가능한 소그룹 형성 기능 제공
- 사진 찍기 기능 제공

## ⚙️ 서비스 기능

### 회원

💡 회원 관리는 기본적으로 `Spring Security`, `JWT`와 `OAUTH2`를 활용

- 회원 가입
- 로그인 / 로그아웃
- 마이페이지
- 회원 정보 수정

### 행사

💡 화상 회의는 `OpenVidu`를 활용<br>
💡 행사 별 소그룹 리스트는 `Redis`를 이용해 관리

- 호스트 메인 행사 생성 및 참여자들 입장
- 참여자들 소그룹 생성
- 회의 정보 조회
- 개인 & 소그룹 사진 촬영
- 롤링페이퍼 메시지 입력 후 저장

### 결과물

💡 사진, 음성, 영상 파일은 `S3` 에 업로드<br>
💡 사진 리스트는 `Redis Cache`를 이용해 조회 속도 개선<br>
💡 포토모자이크는 `OpenCv` 라이브러리를 활용

- **기억**
  - 롤링페이퍼, 포토모자이크 편집 완료 여부 조회
  - 롤링페이퍼 배경지, 포스트잇 템플릿 설정
  - 포토모자이크/추억 썸네일용 대표이미지 설정
  - 참여자들에게 받은 사진과 로컬 사진을 추가해 포토모자이크 생성
- **추억**
  - 각 추억의 종합 정보 조회
  - 롤링페이퍼 조회
  - 포토모자이크 조회

## 🏠 서비스 레이아웃

### 메인 페이지

![mokkoji-main](/uploads/763aa9662f6f4f3aac783cd221c23ca0/screencapture-mokkoji-online-2024-02-14-01_23_10.png)

### 소셜 로그인

![mokkoji-login](/uploads/5ec7995752082cac61fc855e64a54201/_ED_99_94_EB_A9_B4__EC_BA_A1_EC_B2_98_2024-02-14_012544.png)

![_ED_99_94_EB_A9_B4__EC_BA_A1_EC_B2_98_2024-02-14_012652](/uploads/b3a46f640306af9c9f785682cb1f21d4/_ED_99_94_EB_A9_B4__EC_BA_A1_EC_B2_98_2024-02-14_012652.png)

![_ED_99_94_EB_A9_B4__EC_BA_A1_EC_B2_98_2024-02-14_012738](/uploads/da2709d6d471d03f0c184afffa1c9b00/_ED_99_94_EB_A9_B4__EC_BA_A1_EC_B2_98_2024-02-14_012738.png)

### 마이페이지
![마이페이지](/uploads/da5b9081e600fc734f73c02f8469de76/마이페이지.png)

### 행사 생성
![화상회의](/uploads/ac38475cae8d7ad407c1f68327dc61cd/image__6_.png)

### 소그룹 형성
![소그룹](/uploads/cee31fd67fb70fc50500c6c1ffcaecba/소그룹.png)

### 축하 편지 쓰기
![메시지보내기](/uploads/d7e50ffaaf029ed8f3df7ede8400ecf6/메시지보내기.png)

### 사진 찍기
![사진찍기](/uploads/2bf4977acbd58f51f039ba70f2b64843/사진찍기.png)

### 기억 페이지
![결과물-기억](/uploads/1d7a6adc4c728254074756b331416470/결과물-기억.png)

### 롤링페이퍼 생성
![롤링페이지편집](/uploads/bb1e8e50a61adeb6970d841701d14677/롤링페이지편집.png)

### 포토 모자이크 생성
![포토모자이크생성](/uploads/7366037d0e9fa9de4169fa53785a4382/image__4_.png)

### 추억 페이지
![추억결과](/uploads/8ea0235178dca5b82c70c5cab433e3a2/추억결과.PNG)

### 롤링페이퍼 확인
![롤링페이퍼결과](/uploads/c1b9a9b564e664c72131f912d6154ad6/롤링페이퍼결과.PNG)

### 포토모자이크 확인
![포토모자이크결과](/uploads/b33282b7a5927f88906dd841b0755e51/포토모자이크결과.PNG)

## 🛠️ 기술 스택

### Management Tool

![gitlab](https://img.shields.io/badge/gitlab-FC6D26?style=for-the-badge&logo=gitlab&logoColor=white)
![Git](https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=git&logoColor=white)
![jira](https://img.shields.io/badge/jira-0052CC?style=for-the-badge&logo=jira&logoColor=white)
![slack](https://img.shields.io/badge/slack-4A154B?style=for-the-badge&logo=slack&logoColor=white)
![notion](https://img.shields.io/badge/notion-000000?style=for-the-badge&logo=notion&logoColor=white)
![figma](https://img.shields.io/badge/figma-F24E1E?style=for-the-badge&logo=figma&logoColor=white)

### IDE

![intellij](https://img.shields.io/badge/intellij_idea-000000?style=for-the-badge&logo=intellijidea&logoColor=white)
![vscode](https://img.shields.io/badge/vscode-0078d7?style=for-the-badge&logo=visual%20studio&logoColor=white)

### Infra

![amazonec2](https://img.shields.io/badge/amazon%20ec2-FF9900?style=for-the-badge&logo=amazonec2&logoColor=white)
![amazons3](https://img.shields.io/badge/amazon%20s3-569A31?style=for-the-badge&logo=amazons3&logoColor=white)
![nginx](https://img.shields.io/badge/nginx-009639?style=for-the-badge&logo=nginx&logoColor=white)
![docker](https://img.shields.io/badge/docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![ubuntu](https://img.shields.io/badge/ubuntu-E95420?style=for-the-badge&logo=ubuntu&logoColor=white)

### Frontend

![vue.js](https://img.shields.io/badge/vue.js-4FC08D?style=for-the-badge&logo=Vue.js&logoColor=white)
![javascript](https://img.shields.io/badge/javascript-F0DB4F?style=for-the-badge&logo=javascript&logoColor=white)
![nodejs](https://img.shields.io/badge/nodejs-3C873A?style=for-the-badge&logo=node.js&logoColor=white)
![tailwind](https://img.shields.io/badge/tailwind-06B6D4?style=for-the-badge&logo=tailwindcss&logoColor=white)
![html](https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white)
![css3](https://img.shields.io/badge/css3-1572B6?style=for-the-badge&logo=css3&logoColor=white)

### Backend

![java](https://img.shields.io/badge/Java-007396?style=for-the-badge)
![springboot](https://img.shields.io/badge/spring%20boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![springjpa](https://img.shields.io/badge/spring%20jpa-6DB33F?style=for-the-badge&logo=Spring&logoColor=white)
![springsecurity](https://img.shields.io/badge/spring%20security-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white)
![jwt](https://img.shields.io/badge/jwt-000000?style=for-the-badge&logo=jwt&logoColor=white)
![oauth](https://img.shields.io/badge/oauth-000000?style=for-the-badge&logo=oauth&logoColor=white)
![querydsl](https://img.shields.io/badge/querydsl-4285F4?style=for-the-badge&logo=querydsl&logoColor=white)
![redis](https://img.shields.io/badge/redis-DC382D?style=for-the-badge&logo=redis&logoColor=white)
![mysql](https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![junit5](https://img.shields.io/badge/junit5-25A162?style=for-the-badge&logo=junit5&logoColor=white)
![postman](https://img.shields.io/badge/postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white)

## 🛠️서비스 아키텍처
![서비스아키텍처](/uploads/465a44994c56f47db9abfd9312d00410/mokkoji__1_.png)

## 🗂설계 문서

### Flow Chart
![모꼬지_플로우차트](/uploads/a9bbb87e95208f617c49f16cbafc8889/모꼬지_플로우차트.jpg)

### Wire Frame
![와이어프레임](/uploads/67316c7842fcab8bf1c94079f37b9b21/image.png)

## 💾 ERD

![mokkoji-erd](/uploads/9618596855c1de49af0d268ef9227652/mokkoji-erd.png)

## 📁프로젝트 구조

### Frontend

```
frontend
  ├── node_modules
  ├── public
  └── src
      ├── api
      ├── assets
      ├── components
      │   ├── common
      │   ├── meeting
      │   ├── modal
      │   │   ├── home
      │   │   └── meeting
      │   ├── myedit
      │   ├── myevent
      │   └── mypage
      ├── icons
      ├── router
      ├── services
      ├── store
      ├── util
      └── views
```

### Backend

```
mokkoji
├── common
│   ├── auth
│   │   ├── jwt
│   │   └── oauth2
│   ├── domain
│   └── exception
│       ├── dto
│       ├── errorcode
│       └── handler
├── config
├── event
│   ├── controller
│   ├── domain
│   ├── dto
│   │   ├── request
│   │   └── response
│   ├── repository
│   └── service
├── openvidu
│   ├── controller
│   └── dto
│       ├── request
│       └── response
├── result
│   ├── controller
│   ├── domain
│   ├── dto
│   │   ├── request
│   │   └── response
│   ├── repository
│   └── service
├── s3
└── user
    ├── controller
    ├── domain
    ├── dto
    │   ├── request
    │   └── response
    ├── repository
    └── service
```

## 📃발표자료

## 👥팀 구성원

<table align="center">
  <tr>
    <tr align="center">
        <td style="min-width: 250px;">
            <a href="https://github.com/KuMMii">
              <b>김지은</b>
            </a> 
        </td>
        <td style="min-width: 250px;">
            <a href="https://github.com/cheoljunpark">
              <b>박철준</b>
            </a>
        </td>
        <td style="min-width: 250px;">
            <a href="">
              <b>이경배</b>
            </a>
        </td>
    </tr>
    <tr align="center">
        <td style="min-width: 250px;">
              <img src="https://avatars.githubusercontent.com/u/128502524?v=4" width="100">
        </td>
        <td style="min-width: 250px;">
              <img src="https://avatars.githubusercontent.com/u/82487512?v=4" width="100">
        </td>
        <td style="min-width: 250px;">
              <img src="https://secure.gravatar.com/avatar/2c1b4340df6c333d6e31f0331798b5a8?s=800&d=identicon" width="100">
        </td>
    </tr>
    <tr align="center">
        <td>
        <b>Backend</b><br> OpenVidu, 롤링페이퍼 api 담당
        <br/>
        </td>
        <td>
        <b>Infra</b><br>UCC 담당
        <br/>
        </td>
        <td>
        <b>Backend</b><br> 소셜 로그인, 포토모자이크 api 담당
        <br/>
        </td>
    </tr>
  </tr>
  <tr>
    <tr align="center">
        <td style="min-width: 250px;">
            <a href="">
              <b>이정민</b>
            </a>
        </td>
        <td style="min-width: 250px;">
            <a href="https://github.com/chomchom96">
              <b>조용운</b>
            </a>
        </td>
        <td style="min-width: 250px;">
            <a href="https://github.com/hunteac">
              <b>한재훈</b>
            </a>
        </td>
    </tr>
    <tr align="center">
        <td style="min-width: 250px;">
              <img src="https://secure.gravatar.com/avatar/11aa9e89b7b70c7db93e39b018993c08?s=800&d=identicon" width="100">
        </td>
        <td style="min-width: 250px;">
              <img src="https://avatars.githubusercontent.com/u/112466460?v=4" width="100">
        </td>
        <td style="min-width: 250px;">
              <img src="https://avatars.githubusercontent.com/u/135293451?v=4" width="100">
        </td>
    </tr>
    <tr align="center">
        <td>
        <b>Frontend</b><br> 롤링페이퍼, 포토모자이크 담당
        <br/>
        </td>
        <td>
        <b>Frontend</b><br> 소셜 로그인, 사용자 기능 담당
        <br/>
        </td>
        <td>
        <b>Frontend</b><br> OpenVidu 담당<br>발표 담당
        <br/>
        </td>
    </tr>
  </tr>

</table>
