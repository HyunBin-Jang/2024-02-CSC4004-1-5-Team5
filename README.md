# Travel Kit

## 맞춤형 여행 준비물 체크리스트 생성 서비스

### 개요
여행 준비물을 찾기 위해 유튜브 영상이나 검색 사이트를 통해 정보를 일일이 찾아보는 과정은 매우 번거롭고 시간 소모가 크다. 특히, 여러 사이트를 방문해야 하고 각 여행지의 날씨나 계절감을 고려해야 하는 등 정보의 조합이 필요하다. 

본 프로젝트는 **사용자가 여행지와 날짜를 입력하기만 하면 자동으로 맞춤형 준비물 체크리스트를 생성**해주는 간편한 서비스를 구축하여 이러한 문제를 해결하고자 한다. 

---

### UI
<p align="center">
     <img width="60%" src="https://github.com/user-attachments/assets/7e9d2622-34c2-4495-9551-1d1ada1a699d">
     <img width="60%" src="https://github.com/user-attachments/assets/8527e568-153e-45b7-ab82-740384569f1e">
     <img width="60%" src="https://github.com/user-attachments/assets/9a156b40-8521-4b6a-8d43-c6496f98a496">
     <img width="60%" src="https://github.com/user-attachments/assets/97d5503d-cb24-4da0-84d6-195adabc6cee">
</p>

---

### 추천 알고리즘

**메모리 기반 접근법(Memory-Based Approach)**
메모리 기반 접근법의 대표 방법론은 User-based Filterling과 Item-based Filterling가 존재한다. 프로젝트 내에서는 User-based Filterling 방법을 채택하였다. 이는 유사한 사
용자(User)를 기반으로 한 추천과 유사한 물품(Item)을 기반으로 파악한다.

**유저 기반 필터링(User-based Filterling) 및 자카드 유사도(Jaccard Similarity) 응용**
프로젝트 내에서는 User-based Filterling의 User를 하나의 체크리스트로 다루었다. 선택된 지역의 체크리스트 간 유사성을 측정한다. 유사하다고 판단된 체크리스트 A를 사용자 체크리스트와 비교하여 체크리스트 A에는 존재하지만 사용자 체크리스트에는 존재하지 않는 품목을 사용자에게 제공하는 방식으로 적용한다. 유저 기반 필터링의 유사도 측정 방법으로 자카드 유사도(Jaccard Similarity)를 이용하여 측정되었다.

프로젝트 내 적용된 자카드 유사도(Jaccard Similarity)
<img width="20%" src="https://github.com/user-attachments/assets/9ba2c147-92dd-4b4a-ab1d-bb6701b3b2e9">
<img width="20%" src="https://github.com/user-attachments/assets/6fe2ffa9-2019-447d-b471-4592e1c14aad">

---

## Team Member

|이름|소속|역할|github ID|
|----|----|------|---|
|장현빈|컴퓨터공학전공|팀장/Backend|[hyunbinn](https://github.com/HyunBin-Jang)|
|강민우|컴퓨터공학전공|팀원/Backend|[KangKang109](https://github.com/KangKang109)|
|전채연|컴퓨터공학전공|팀원/Frontend|[chaeee01](https://github.com/chaeee01)|
|백지연|경영정보학과|팀원/Frontend|[Jiyeon Baek](https://github.com/jyeon03)|
|김현정|멀티미디어소프트웨어공학전공|팀원/Frontend|[HyeonJeong0322](https://github.com/HyeonJeong0322)|
<hr>
---
