import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //TIP 캐럿을 강조 표시된 텍스트에 놓고 <shortcut actionId="ShowIntentionActions"/>을(를) 누르면
        // IntelliJ IDEA이(가) 수정을 제안하는 것을 확인할 수 있습니다.
        String file = "./test.paren";
        String result = "./test.c";
        String basic = "#include <stdio.h>\n" +
                "int main() {\n" +
                "  unsigned a, b, c;\n" +
                " unsigned st0, st1, st2, st3, st4;\n";

        try (FileWriter writer = new FileWriter(result)) {
            writer.write(basic);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int state; // 0: 시작, 1: push, 2: pop, 3: add, 4: , 5: 숫자
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while (((line = reader.readLine()) != null)) {

            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    // 상태 처음에는 (,), +, * 가능
    // 각각 push, pop, add, mul
    // 이후 상태와 같이 나머지 문자열 전달
    public void lexer (String str) {
        int length = str.length();
        char [] charArray = str.toCharArray();
        int index = 0;
        List<token> tokens = new ArrayList<>();

        while (index < length) {
            token token = new token();
            switch (charArray[0]) {
                case '(':
                    // push
                    token.state = 0;

                    tokens.add(token);
                case ')':
                    // pop
                    token.state = 1;

                    tokens.add(token);
                case '+':
                    // add
                    token.state = 2;
                    tokens.add(token);
                case '*':
                    // mul
                    token.state = 3;
                    tokens.add(token);
            }
        }
    }

    private int lex_push (char[] str, int start) {
        int end, index;
        switch (str[index]) {
            case '(':
                index = start + 1;
                end = lex_push(str, index) + 1;
                break;
            case ')':
                end = start - 1;
                break;
            default:

        }

    }

    class token {
        // state push = 0, pop = 1, add = 2, mul = 3
        int state, start, end;
    }
}