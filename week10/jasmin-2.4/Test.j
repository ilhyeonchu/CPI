.class public Main
.super java/lang/Object

.method public <init>()V
	aload_0
	invokespecial java/lang/Object/<init>()V
	return
.end method

.method public static fac(I)I
	.limit stack 32
	.limit locals 32
	iload_0
	iconst_1
	if_icmpne Lfalse
	iconst_1
	ireturn
Lfalse:
	iload_0
	iload_0
	iconst_1
	isub
	invokestatic	Main/fac(I)I
	imul
	ireturn
.end method

.method public static main([Ljava/lang/String;)V
	.limit stack 32
	.limit locals 32
	new java/util/Scanner
	dup
	getstatic			java/lang/System/in Ljava/io/InputStream;
	invokespecial 	java/util/Scanner/<init>(Ljava/io/InputStream;)V
	astore_1
	aload_1
	invokevirtual 		java/util/Scanner/nextInt()I
	istore_2
	iload_2
	invokestatic		Main/fac(I)I
	istore_3
	getstatic			java/lang/System/out Ljava/io/PrintStream;
	iload_3
	invokevirtual		java/io/PrintStream/println(I)V
	return
.end method
