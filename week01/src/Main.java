import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import ast.*;

public class Main {
    public static void main(String[] args) {
        // TIP 캐럿을 강조 표시된 텍스트에 놓고 <shortcut actionId="ShowIntentionActions"/>을(를) 누르면
        // IntelliJ IDEA이(가) 수정을 제안하는 것을 확인할 수 있습니다.
        String file = "test.paren";

        int state; // 0: 시작, 1: push, 2: pop, 3: add, 4: , 5: 숫자
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while (((line = reader.readLine()) != null)) {
                List<Token> tokens = Lexer.lexer(line);
                List<Expr> exprs = Parser.parser(tokens);
                Generator.generator(exprs);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
