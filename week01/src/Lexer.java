import java.util.ArrayList;
import java.util.List;
import ast.*;

// 상태 처음에는 (,), +, * 가능
// 각각 push, pop, add, mul
// 이후 상태와 같이 나머지 문자열 전달
public class Lexer {
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
                    token.mainAction = MainAction.PUSH;
                    token.endIndex = lex_range(charArray, index);
                    token.typeP = lex_para(charArray[token.startIndex + 1]);
                    token.value = new String(charArray, token.startIndex + 1, token.endIndex - token.startIndex + 1);
                    tokens.add(token);
                    index = token.endIndex;
                    break;
                case ')':   // pop
                    token.mainAction = MainAction.POP;
                    token.endIndex = lex_range(charArray, index);
                    token.typeP = lex_para(charArray[token.startIndex + 1]);
                    token.value = new String(charArray, token.startIndex + 1, token.endIndex - token.startIndex + 1);
                    tokens.add(token);
                    index = token.endIndex;
                case '+':   // add
                    token.mainAction = MainAction.ADD;
                    token.endIndex = index;
                    tokens.add(token);
                case '*':   // mul
                    token.mainAction = MainAction.MUL;
                    token.endIndex = index;
                    tokens.add(token);
            }
            index++;
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
