import ast.Action;
import ast.Expr;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Generator {
    public static void generator (List<Expr> exprs) throws IOException {

        String result = "./test.c";
        String basic = "#include <stdio.h>\n"
                + "\n"
                + "unsigned int stack[5];\n"
                + "int top = -1;\n"
                + "void push(unsigned int x) {\n"
                + "  if (top >= 5-1) {\n"
                + "    printf(\"Stack is full\n\");\n"
                + "    return;\n"
                + "  };\n"
                + "  stack[++top] = x;\n}\n\n"
                + "unsigned int pop() {\n"
                + "  if (top < 0) {\n"
                + "    printf(\"Stack is empty\n\");\n"
                + "    return 0;\n}\n"
                + "  return stack[top--];\n}\n"
                + "int main() {\n"
                + "  unsigned var1, var2, var3, input, output1, output2;\n";
                // + "  unsigned st0, st1, st2, st3, st4;\n";

//        try (FileWriter writer = new FileWriter(result)) {
//            writer.write(basic);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        FileWriter writer = new FileWriter(result);
        writer.write(basic);
        for (Expr expr : exprs) {
            for (Action action : expr.actions) {
                switch (expr) {
                    case Action.SCANF :
                        writer.write("scanf(\"%u\", &input);\n");
                        writer.write("push(input);\n");
                    case Action.PRINTF :
                        writer.write("output1 = pop()\n");
                        writer.write("printf(\"%u\", output1);\n");
                    case Action.VAR :
                        int len = expr.value.length();
                        if (len > 5) {
                            writer.write("push(input);\n");
                        } else if (len > 3) {

                        } else {

                        }
                    case Action.NUM :

                    case Action.RESTORE :

                    case Action.PUSH :

                    case Action.POP :

                    case Action.ADD :

                    case Action.MUL :

                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + expr);
                }
            }
        }
    }
}
