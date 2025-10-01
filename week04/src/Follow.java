import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Follow {
    Map<NonTerminal, List<Character>> follow = new HashMap<NonTerminal, List<Character>>();
    ArrayList<Character> S_Follow = new ArrayList<>();
    ArrayList<Character> A_Follow = new ArrayList<>();
    ArrayList<Character> B_Follow = new ArrayList<>();
    ArrayList<Character> C_Follow = new ArrayList<>();
    ArrayList<Character> D_Follow = new ArrayList<>();
    Follow() {
        S_Follow.add('$');
        A_Follow.add('a');
        A_Follow.add('$');
        B_Follow.add('$');
        C_Follow.add('a');
        C_Follow.add('b');
        C_Follow.add('$');
        D_Follow.add('a');
        D_Follow.add('$');
        follow.put(NonTerminal.S, S_Follow);
        follow.put(NonTerminal.A, A_Follow);
        follow.put(NonTerminal.B, B_Follow);
        follow.put(NonTerminal.C, C_Follow);
        follow.put(NonTerminal.D, D_Follow);
    }
}
