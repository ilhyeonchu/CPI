import ast.Expr;
import ast.Block;
import ast.MainAction;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

// blocks를 받아 각 block들의 내용물을 c언어 코드로 변환
// ./test.c 가 이미 있다면 지우고 시작
// basic이라는 기본으로 추가할 c언어 코드들을 작성
// 이후 MainAction에 따라 2가지 종류의 메서드 중 하나를 이용해 block의 exprs를 c언어 코드로 변환하여 파일에 작성
public class Generator {
    public static void generator(List<Block> blocks) throws IOException {

        String result = "./test.c";
        String basic = "#include <stdio.h>\n"   // stack 구조를 위한 기본적인 코드와 변수, IO작업에서 사용할 임시 변수들 생성
                + "\n"
                + "unsigned int stack[5];\n"
                + "int top = -1;\n"
                + "void push(unsigned int x) {\n"
                + "  if (top >= 5-1) {\n"
                + "    printf(\"Stack is full\");\n"
                + "    return;\n"
                + "  };\n"
                + "  stack[++top] = x;\n}\n\n"
                + "unsigned int pop() {\n"
                + "  if (top < 0) {\n"
                + "    printf(\"Stack is empty\");\n"
                + "    return 0;\n}\n"
                + "  return stack[top--];\n}\n"
                + "int main() {\n"
                + "  unsigned int var1, var2, var3, input, output1, output2;\n\n";
        // + " unsigned st0, st1, st2, st3, st4;\n";

        // try (FileWriter writer = new FileWriter(result)) {
        // writer.write(" (basic);
        // } catch (IOException e) {
        // throw new RuntimeException(e);
        // }

        File file = new File(result);
        if (file.exists()) {                // 파일이 기존에 이미 존재한다면 지우기
            boolean delete = file.delete();
            if (delete) {

            } else {
                System.out.println("failed to delete file");
                return;
            }
        }

        // BufferedWriter을 이용해 test.c 에 적절한 코드들 삽입 시작
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(result))) {
            writer.write(basic);
            for (Block block : blocks) {
                if (block.mainaction == MainAction.POP || block.mainaction == MainAction.PUSH) {
                    exprPP(block, writer);
                } else {
                    exprAM(block, writer);
                }
            }
            writer.write("\n  return 0;\n");
            writer.write("}");
        }
    }

    // block의 MainAction이 POP 이나 PUSH인 경우
    // exprs에 들어있는 expr을 하나씩 처리
    // expr.PUSH의 경우 POP 과 PUSH에 따라서 다르게 동작
    public static void exprPP(Block block, BufferedWriter writer) throws IOException {
        int var_order_num = 0;
        for (Expr expr : block.exprs) {
            switch (expr) {
                case Expr.SCANF:
                    writer.write("  scanf(\"%u\", &input);\n");
                    break;
                case Expr.PRINTF:
                    writer.write("  printf(\"%u\\n\", output1);\n");
                    break;
                case Expr.VAR:
                    var_order_num = exprVar(block);
                    break;
                case Expr.NUM:
                    writer.write("  input = " + block.value + ";\n");
                    break;
                case Expr.RESTORE:
                    writer.write("  var" + var_order_num + " = output1;\n");
                    var_order_num = 0;
                    break;
                case Expr.PUSH:
                    if (block.mainaction == MainAction.POP) {
                        writer.write("  push(output1);\n");
                    } else if (var_order_num == 0) {
                        writer.write("  push(input);\n");
                    } else {
                        writer.write("  push(var" + var_order_num + ");\n");
                    }
                    break;
                case Expr.POP:
                    writer.write("  output1 = pop();\n");
                    break;
            }
        }
    }

    // block의 MainAction이 ADD 이나 MUL인 경우
    // exprs에 저장되어있긴 하지만 동작이 고정되어 있음
    // 중간에 넣는 op값만 케이스 따라서 다르게 작성
    public static void exprAM(Block block, BufferedWriter writer) throws IOException {
        String op;
        writer.write("  output1 = pop();\n");
        writer.write("  output2 = pop();\n");
        if (block.mainaction == MainAction.ADD) {
            op = "+";
        } else {
            op = "*";
        }
        writer.write("  input = output1" + op + "output2;\n");
        writer.write("  push(input);\n");
    }

    // MainAction이 VAR인 경우
    // var1, var2, var3 중 무엇인지 확인하기 위한 메서드
    public static int exprVar(Block block) throws IOException {
        String val = block.value;
        if (Objects.equals(val, "()")) {
            return 1;
        } else if (Objects.equals(val, "(())")) {
            return 2;
        } else {
            return 3;
        }
    }
}
