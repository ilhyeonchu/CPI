import ast.Action;
import ast.Expr;
import ast.MainAction;

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
            if (expr.mainaction == MainAction.POP || expr.mainaction == MainAction.PUSH) {
                action_pp(expr);
            } else  {
                action_am(expr);
            }
        }
        writer.write("\nretrun 0;");
        writer.close();
    }

    public static void action_pp(Expr expr) throws IOException {
        FileWriter writer = new FileWriter("./test.c");
        for (Action action : expr.actions) {
            int var_order_num = 1;
            switch (action) {
                case Action.SCANF :
                    writer.write("scanf(\"%u\", &input);\n");
                case Action.PRINTF :
                    writer.write("printf(\"%u\", output1);\n");
                case Action.VAR :
                    var_order_num = action_var(expr);
                case Action.NUM :
                    writer.write("input = " + expr.value + ";\n");
                case Action.RESTORE :
                    writer.write("var" + var_order_num + "output1;\n");
                case Action.PUSH :
                    writer.write("push(input);\n");
                case Action.POP :
                    writer.write("output1 = pop();\n");
                default:
                    throw new IllegalStateException("Unexpected value: " + expr);
            }
        }
    }

    public static void action_am(Expr expr) throws IOException {
        FileWriter writer = new FileWriter("./test.c");
        String op;
        writer.write("output1 = pop();\n");
        writer.write("output2 = pop();\n");
        if (expr.mainaction == MainAction.ADD) {
            op = "+";
        } else {
            op = "*";
        }
        writer.write("input = output1" + op + "output2;\n");
        writer.write("push(input);\n");
    }

    public static int action_var(Expr expr) throws IOException {
        String val = expr.value;
        if (Objects.equals(val, "()")) {
            return 1;
        } else if (Objects.equals(val, "(())")) {
            return 2;
        } else {
            return 3;
        }
    }
}
