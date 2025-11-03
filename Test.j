.class public Test              // Test 클래스 선언?
.super java/lang/Object         // 상위 클래스 Object다 이건 기본적인 java 설정

.method public <init>()V        // 생성자 선언 반환값 V(void)
    aload_0                     // 스택에 this(local 0) 푸시
    invokespecial java/lang/Object/<init>()V    // Object 생성자 호출 이때 스택에서 this 꺼냄
    return                      // 리턴 void
.end method                     // 생성자 종료

.method public static func(I)Z  // int 받아서 bool값 반환하는 func라는 public static 메서드 시작
    .limit stack 32
    .limit locals 32            // 사용할 스택과 로컬 공간 생성??
    iload_0                     // local 0번을 push
    iconst_2                    // 스택에 2 push
    irem                        // stack 에서 pop 2번 해서 나머지 연산
    ifne Lfalse                 // 0 아니면 Lfalse로 
    iconst_1                    // stack에 1 push
    ireturn                     // return
Lfalse:
    iconst_0                    // stack에 0 push
    ireturn                     // return
.end method                     // method 종료

.method public static main([Ljava/lang/String;)V        // 레퍼런스 String[] 을인자로 받는 public static 메서드(main) 시작
    .limit stack 32
    .limit locals 32
    iconst_1                                            // 1 push
    istore_1                                            // 로컬 변수 1에 pop해서 저장(1)
    iconst_2                                            // 2 push
    istore_2                                            // 로컬 변수 2에 pop해서 저장(2)
    getstatic java/lang/System/out Ljava/io/PrintStream;    // system.out을 찾아서 push(printstream타입)
    iload_1                                             // 로컬 1번을 push
    invokestatic Test/func(I)Z                          // func함수에 pop한 값을 인자로 전달
    invokevirtual java/io/PrintStream/println(Z)V
    getstatic java/lang/System/out Ljava/io/PrintStream;
    iload_2
    invokestatic Test/func(I)Z
    invokevirtual java/io/PrintStream/println(Z)V
    return
.end method
