import java.util.ArrayList;
import java.util.List;

// 상태 처음에는 (,), +, * 가능
// 각각 push, pop, add, mul
// 이후 상태와 같이 나머지 문자열 전달
public class lexer {
    public static List<ast.token> lexer(String str) {
        int length = str.length();
        char [] charArray = str.toCharArray();
        int index = 0;
        List<ast.token> tokens = new ArrayList<>();

        while (index < length) {
            ast.token token = new ast.token();
            token.start = index;
            switch (charArray[index]) {
                case '(':   // push
                    token.state = 0;
                    token.end = lex_range(charArray, index);
                    tokens.add(token);
                    index = token.end;
                    break;
                case ')':   // pop
                    token.state = 1;
                    token.end = lex_range(charArray, index);
                    tokens.add(token);
                case '+':   // add
                    token.state = 2;
                    token.end = index;
                    tokens.add(token);
                    index++;
                case '*':   // mul
                    token.state = 3;
                    token.end = index;
                    tokens.add(token);
                    index++;
            }
        }
        return tokens;
    }

    private static int lex_range(char[] str, int start) {
        int end, index;
        index = start + 1;
        end = start + 1;
        if (str[index] == '(') {
            end = lex_range(str, index) + 1;
        } else if (str[index] == ')') {
            end = start - 1;
        } else if (Character.isDigit(str[index]) ) {
            while (Character.isDigit(str[index])) {
                index++;
            }
            end = index - 1;
        } else if (str[index] == '<') {
            end = index + 1;
        }

        return end;
    }

//    private int lex_pop (char[] str, int start) {
//        int end, index;
//        index = start + 1;
//        end = start + 1;
//        if (str[index] == '(') {
//            end = lex_range(str, index) + 1;
//        } else if (str[index] == '<') {
//            end =
//        }
//    }
}
