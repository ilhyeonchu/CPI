import java.lang.Thread.State;
import java.lang.reflect.Parameter;

public class ast {
    // state : push = 0, pop = 1, add = 2, mul = 3
    public State {
        PUSH,
        POP,
        ADD,
        MUL
    }

    // parameter : <> = 0, var = 1, num = 2
    public Parameter {
        IO,
        VAR,
        NUM
    }

    public class token {
        State currentState;
        int startIndex;
        int endIndex;
        Parameter typeP;
    }

    static class expr {
        int state;
        char[] parameter;
    }
}
