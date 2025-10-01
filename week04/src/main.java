import java.util.ArrayList;
import java.util.Scanner;

public class main {
    static char nextSymbol;
    static int index;
    static String input;
    static ArrayList<Character> symbols = new ArrayList<>();
    public static void main(String[] args) {
        // check symbol
        // non terminal -> anothers or nothing
        // terminal -> get nextSymbol
        // fi S=A=C = {c}, B = {a, $}, D = {b, $}
        // fo S=B = {$}, A=D = {a, $}, C = {a, b, $}
        Scanner sc = new Scanner(System.in);
        input = sc.nextLine();
        for (int i = 0; i < input.length(); i++) {
            symbols.add(input.charAt(i));
        }
        symbols.add('$');
        // System.out.println(symbols);
        getNextSymbol();
    }

    static void getNextSymbol() {
        nextSymbol = symbols.get(index++);
    }
}
