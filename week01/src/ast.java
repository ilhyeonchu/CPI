public class ast {
    // state push = 0, pop = 1, add = 2, mul = 3
    static class token {
        int state;
        int start;
        int end;
        char[] parameter;
    }

    static class expr {
        int state;
        char[] parameter;
    }
}
