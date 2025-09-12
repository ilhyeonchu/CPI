import java.util.ArrayList;
import java.util.List;
import ast.*;

// 입력된 문자열을 여러 문자열로 분해
// 분해된 각 문자열은 하나의 메인 작업(MainAction)을 수행함
// 각 문자열이 어떤 MainAction을 수행하고 해당 문자열의 범위가 어디까지인지 그리고 작업에 사용되는 인자가 무엇인지 체크
// 위의 내용들을 token으로 만들어서 tokens에 저장 후 반환
public class Lexer {
    public static List<Token> lexer(String str) {
        int length = str.length();
        char [] charArray = str.toCharArray();
        int index = 0;
        List<Token> tokens = new ArrayList<>();

        while (index < length) {
            Token token = new Token();
            token.startIndex = index;

            // MainAction 체크
            // 쪼개질 문자열의 범위 체크
            // 쪼개질 문자열에서 이용할 인자의 종류 체크
            // 쪼개질 문자열의 값 때어내서 저장
            // tokens에 만들어진 token 추가 후 index 업데이트
            switch (charArray[index]) {
                case '(':   // push
                    token.mainAction = MainAction.PUSH;
                    token.endIndex = lex_range(charArray, index);
                    token.parameter = lex_para(charArray[token.startIndex + 1]);
                    token.value = new String(charArray, token.startIndex + 1, token.endIndex - token.startIndex);
                    tokens.add(token);
                    index = token.endIndex;
                    break;
                case ')':   // pop
                    token.mainAction = MainAction.POP;
                    token.endIndex = lex_range(charArray, index);
                    token.parameter = lex_para(charArray[token.startIndex + 1]);
                    token.value = new String(charArray, token.startIndex + 1, token.endIndex - token.startIndex);
                    tokens.add(token);
                    index = token.endIndex;
                    break;
                case '+':   // add
                    token.mainAction = MainAction.ADD;
                    token.endIndex = index;
                    tokens.add(token);
                    break;
                case '*':   // mul
                    token.mainAction = MainAction.MUL;
                    token.endIndex = index;
                    tokens.add(token);
                    break;
            }
            index++;
        }
        return tokens;
    }

    // 쪼개진 각 문자열의 범위를 확인하는 메서드
    // MainAction 이후에는 숫자, 변수, IO가 올 수 있으므로 나눠서 처리
    // 문자열의 마지막 문자의 인덱스 반환
    private static int lex_range(char[] str, int start) {
        int end, index;
        index = start + 1;
        end = start + 1;
        if (str[index] == '(') {
            end = lex_range(str, index) + 1;
        } else if (str[index] == ')') {
            end = start;
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

    // MainAction 이후 오는 첫 문자를 확인하여 해당 문자열이 뭘 인자로 이용하는지 확인해 반환해주는 메서드
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
