import java.util.ArrayList;
import java.util.List;
import ast.Token;

// 상태 처음에는 (,), +, * 가능
// 각각 push, pop, add, mul
// 이후 상태와 같이 나머지 문자열 전달
public class lexer {
    public static List<Token> lexer(String str) {
        int length = str.length();
        char [] charArray = str.toCharArray();
        int index = 0;
        List<Token> tokens = new ArrayList<>();

        while (index < length) {
            Token token = new Token();
            token.startIndex = index;
            switch (charArray[index]) {
                case '(':   // push
                    token.currentState = 0;
                    token.endIndex = lex_range(charArray, index);

                    tokens.add(token);
                    index = token.endIndex;
                    break;
                case ')':   // pop
                    token.currentState = 1;
                    token.endIndex = lex_range(charArray, index);
                    tokens.add(token);
                case '+':   // add
                    token.currentState = State.ADD;
                    token.endIndex = index;
                    tokens.add(token);
                    index++;
                case '*':   // mul
                    token.currentState = 3;
                    token.endIndex = index;
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

    private  static Parameter lex_para(char ch) {
        if (Character.isDigit(ch)) {
            return Parameter.NUM;   // num
        } else if (ch == '(') {
            return Parameter.VAR;   // var
        } else if (ch == '<') {
            return Parameter.IO;   // IO
        }
        return Parameter.IO;
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
