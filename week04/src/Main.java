import java.util.ArrayList;
import java.util.Scanner;

public class Main {
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
        pS();
    }

    static void pS() {
        if (nextSymbol == 'c') {
            pA();pB();
        } else {
            System.out.println("FAIL");
            System.exit(0);
        }
    }

    static void pA() {
        if (nextSymbol == 'c') {
            pC();pD();
        } else {
            System.out.println("FAIL");
            System.exit(0);
        }
    }

    static void pB() {
        if (nextSymbol == 'a') {
            pa();pS();
        } else if (nextSymbol == '$') {
            // 프로그램 종료
            System.out.println("OK");
        } else {
            System.out.println("FAIL");
            System.exit(0);
        }
    }

    static void pC() {
        if (nextSymbol == 'c') {
            pc();
        } else {
            System.out.println("FAIL");
            System.exit(0);
        }
    }

    static void pD() {
        if (nextSymbol == 'b') {
            pb();pA();
        } else if (nextSymbol == '$' || nextSymbol == 'a') {
            //  하는거 없이 끝
        } else {
            System.out.println("FAIL");
            System.exit(0);
        }
    }

    static void pa() {
        if (nextSymbol == 'a') {
            getNextSymbol();
        } else {
            System.out.println("FAIL");
            System.exit(0);
        }
    }

    static void pb() {
        if (nextSymbol == 'b') {
            getNextSymbol();
        } else {
            System.out.println("FAIL");
            System.exit(0);
        }
    }

    static void pc() {
        if (nextSymbol == 'c') {
            getNextSymbol();
        } else {
            System.out.println("FAIL");
            System.exit(0);
        }
    }

    static void getNextSymbol() {
        nextSymbol = symbols.get(index++);
    }
}