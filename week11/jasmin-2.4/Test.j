.class public Test
.super java/lang/Object

.method public <init>()V
    aload_0
    invokespecial java/lang/Object/<init>()V
    return
.end method

.method public static func(I)Z
    .limit stack 32
    .limit locals 32
    iload_0
    iconst_2
    irem
    ifne Lfalse
    iconst_1
    ireturn
Lfalse:
    iconst_0
    ireturn
.end method

.method public static main([Ljava/lang/String;)V
    .limit stack 32
    .limit locals 32
    iconst_1
    istore_1
    iconst_2
    istore_2
    getstatic java/lang/System/out Ljava/io/PrintStream;
    iload_1
    invokestatic Test/func(I)Z
    invokevirtual java/io/PrintStream/println(Z)V
    getstatic java/lang/System/out Ljava/io/PrintStream;
    iload_2
    invokestatic Test/func(I)Z
    invokevirtual java/io/PrintStream/println(Z)V
    return
.end method
