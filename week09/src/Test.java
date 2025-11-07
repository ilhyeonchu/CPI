public class Test {
    public Test() {
        super();
    }

    public static boolean func(int v0) {
        if (v0 % 2 == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        int v1 = 1;
        int v2 = 2;
        System.out.println(func(v1));
        System.out.println(func(v2));
    }
}
