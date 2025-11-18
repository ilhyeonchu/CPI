import  java.util.Scanner;

public class Main {
    public static int fac(int n) {
        if (n == 1) {
            return 1;
        } else {
            return n * fac(n - 1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // System.out.print("Enter a number(int): ");
        int num = sc.nextInt();
        int resultr = fac(num);
        // int resultf = facf(num);
        System.out.println(resultr);
        // System.out.println(resultf);
    }
}