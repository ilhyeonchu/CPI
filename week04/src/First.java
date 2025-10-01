import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class First {
    Map<NonTerminal, List<Character>> first = new HashMap<NonTerminal, List<Character>>();
    ArrayList<Character> S_First = new ArrayList<>();
    ArrayList<Character> A_first = new ArrayList<>();
    ArrayList<Character> B_First = new ArrayList<>();
    ArrayList<Character> C_first = new ArrayList<>();
    ArrayList<Character> D_first = new ArrayList<>();
    First() {
        S_First.add('c');
        A_first.add('c');
        B_First.add('a');
        B_First.add('$');
        C_first.add('c');
        D_first.add('b');
        D_first.add('$');
        first.put(NonTerminal.S, S_First);
        first.put(NonTerminal.A, A_first);
        first.put(NonTerminal.B, B_First);
        first.put(NonTerminal.C, C_first);
        first.put(NonTerminal.D, D_first);
    }
}
