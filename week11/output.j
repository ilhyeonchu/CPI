.class public Main
.super java/lang/Object

.method public <init>()V
  aload_0
  invokespecial java/lang/Object/<init>()V
  return
.end method

.method public static scan()I
  .limit stack 4
  .limit locals 1
  new java/util/Scanner
  dup
  getstatic java/lang/System/in Ljava/io/InputStream;
  invokespecial java/util/Scanner/<init>(Ljava/io/InputStream;)V
  invokevirtual java/util/Scanner/nextInt()I
  ireturn
.end method

.method public static print(I)V
  .limit stack 2
  .limit locals 1
  getstatic java/lang/System/out Ljava/io/PrintStream;
  iload_0
  invokevirtual java/io/PrintStream/println(I)V
  return
.end method

.method public static fac(I)I
  .limit stack 32
  .limit locals 32
  iload_0
  iconst_1
  if_icmple Lcmp0
  iconst_0
  goto Lcmp1
Lcmp0:
  iconst_1
Lcmp1:
  istore_1
  iload_1
  ifeq Lnot2
  iconst_0
  goto Lnot3
Lnot2:
  iconst_1
Lnot3:
  istore_2
  iload_2
  ifne L0
  iconst_1
  ireturn
L0:
  iload_0
  iconst_1
  isub
  istore_3
  iload_3
  invokestatic Main/fac(I)I
  istore 4
  iload 4
  iload_0
  imul
  istore 5
  iload 5
  ireturn
.end method

.method public static main([Ljava/lang/String;)V
  .limit stack 32
  .limit locals 32
  iconst_3
  istore_1
  iload_1
  invokestatic Main/fac(I)I
  istore_2
  iload_2
  invokestatic Main/print(I)V
  iconst_0
  istore_3
  return
.end method

