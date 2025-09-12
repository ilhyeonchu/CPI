import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import ast.*;

public class Main {
    public static void main(String[] args) {
        // TIP 캐럿을 강조 표시된 텍스트에 놓고 <shortcut actionId="ShowIntentionActions"/>을(를) 누르면
        // IntelliJ IDEA이(가) 수정을 제안하는 것을 확인할 수 있습니다.
        String file = "./test.paren";

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while (((line = reader.readLine()) != null)) {  // 여러줄이 아니라 필요없는듯?
                List<Token> tokens = Lexer.lexer(line);
                List<Block> blocks = Parser.parser(tokens);
                Generator.generator(blocks);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
