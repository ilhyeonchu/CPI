import java.util.*;

public class parser {
    public static List<ast.expr> parser(List<ast.token> tokens) {
        List<ast.expr> exprs = new ArrayList<>();
        int state, start, end;
        for (ast.token token : tokens) { // 각 토큰 파싱?하기
            ast.expr exp = new ast.expr();
            exp = parsing(token);
            exprs.add(exp);
        }
    }

    public static ast.expr parsing(ast.token token) {
        ast.expr expr = new ast.expr();
        expr.state = token.state;

    }

    public static 
}
