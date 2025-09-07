import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        //TIP 캐럿을 강조 표시된 텍스트에 놓고 <shortcut actionId="ShowIntentionActions"/>을(를) 누르면
        // IntelliJ IDEA이(가) 수정을 제안하는 것을 확인할 수 있습니다.
        String file = "./test.paren";
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
    public void parser (String str) {
        char[] charArray = str.toCharArray();
        switch (charArray[0]) {
            case '(':

            case ')':

            case '+':
        }
    }
}