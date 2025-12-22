# WEEK 13 HW MiniC 코드를 java bytecode(jasmin) 으로 변환
## TODO List
- [x] MiniC 코드를 3-addr 코드로 변환 (./src/MiniC2IR.java)
- [x] MiniC2IR 을 확장
- [x] 변환된 3-addr code(./output.ir) 파일을 java bytecode  로 변환해줄 "./src/IR2Jasmin.java" 생성
- [x] "./src/Main.java" 를 수정해 결과물을 "./output.j" 로 저장
- [ ] 보너스 과제 수행

## 작업별 규칙
### 1. MiniC 코드를 3-addr 코드로 변환
    1. 주어진 문법 파일(./src/IR.g4)의 문법을 따를것
    2. Parsetree에서 바로 IR 변화하므로, 현재 노드에서 변환할 필요가 없는 경우가 있으면 그대로 부모 노드로 전달
    3. 배열, 전역변수 관련 MiniC 문법은 다루지 않음
    4. ANTLR Listener로 구현

### 2. MiniC2IR 을 확장
    1. 함수 정의에서 함수 인자 정보를 더 자세히 (변수 이름 추가) " type_spec* -> (type_spec IDENT)* " 로 문법 수정
    2. 수정된 문법에 맞는 3addr-code 를 생성하도록 MiniC2IR 수정

### 3. 3-addr code 파일을 java bytecode로 변환
    1. 변환을 위한 소스 파일의 이름은 "IR2Jasmin.java"
    2. instruction 한 줄을 유사한 기능을 가진 java bytecode instruction으로 변환
    3. 3addr-code의 임시 변수를 적절히 스택 머신에 맞게 조정 ex) 임시 변수 제거 또는 local variable array에 저장
    4. 비교 연산자는 항상 조건문의 expr 에서만 사용된다고 가정하고 구현
    5. Class 정의는 기본적으로 존재한다고 가정 (public Main) : 적절한 Main 클래스 및 생성자 자동으로 생성
    6. MiniC에는 반드시 'void main()' 함수가 존재하며 이를 'public static void main(String[] args)' 함수로 변환
    7. 모든 MiniC 함수는 java bytecode로 변환될 떄 public static 함수로 변환
    8. MiniC는 scan()함수와 print(int x) 함수를 지원하며 이를 적절한 자바 함수로 변환
    9. local, stack의 크기는 32
    10. 입력 파일은 온전히 동작하는 코드만 입력된다고 가정
    11. 3addr code의 키워드에 해당하는 함수/변수명은 MiniC 코드에서 사용되지 않는다고 가정

### 4. "./src/Main.java" 수정해 결과물 저장
    1. 처음 입력받는 MiniC 코드는 "./input.mc"
    2. 변환된 3addr-code는 "./output.ir"
    3. java bytecode는 "./output.j"

### 5. 보너스 과제
    1. 해야할 필요는 없다.
    2. 아래의 목록 중 하나 이상을 선택하거나 적절한 자유 주제 가능
        - CFG 생성
        - 초기화되지 않고 사용된 변수 찾기
        - 타입 분석
        - 배열 넣기
    3. 이전까지 있었던 규칙들이 필요에 따라서 예외처리 될 수 있음
