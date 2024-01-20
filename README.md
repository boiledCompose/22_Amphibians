# 문제 발생

## 1. ViewModel에 uiState 저장 안됨

### 문제
네트워크를 통해 불러온 데이터를 상태 객체에 저장하고 상태를 바꾸려는 시도를 했지만,
바뀐 내용이 저장되지 않고 계속 기본 상태로 초기화가 됨

### 원인
viewModel 관련 종속 항목이 추가되지 않았고 처음 ViewModel 객체 선언이 잘못됨

### 해결 
- 다음 종속 항목을 추가함
  ```kotlin
    //ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
  ```
- 객체 선언 시 타입은 만들어 놓은 uiState 객체로 하되 `viewModel()`로 초기화함
  ```kotlin
  val uiState:AmphibianUiState = viewModel()
  ```
<br>

## 2.