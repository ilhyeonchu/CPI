public class ast {
  // state : push = 0, pop = 1, add = 2, mul = 3
  // parameter : <> = 0, var = 1, num = 2
  static class token {
    int state;
    int start;
    int end;
    int parameter;
  }

  static class expr {
    int state;
    char[] parameter;
  }
}
