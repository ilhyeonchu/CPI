import java.util.*;
import ast.*;

public class Parser {
    public static List<Expr> parser(List<Token> tokens) {
        List<Expr> exprs = new ArrayList<>();
        int state, start, end;
        for (Token token : tokens) { // 각 토큰 파싱?하기
            Expr expr = new Expr();
            expr = parsing(token);
            exprs.add(expr);
        }
        return exprs;
    }

    public static Expr parsing(Token token) {
        Expr expr = new Expr();
        if (token.mainAction == MainAction.PUSH) {
            expr.actions = parse_push(token.parameter);
        } else if (token.mainAction == MainAction.POP) {
            expr.actions = parse_pop(token.parameter);
        } else {
            expr.actions = parse_op(token.parameter, token.mainAction);
        }
        expr.value = token.value;
        return expr;
    }

    public static List<Action> parse_push(Parameter parameter) {
        List<Action> actions = new ArrayList<>();
        if (parameter == Parameter.IO) {
            actions.add(Action.SCANF);
            actions.add(Action.PUSH);
        } else if (parameter == Parameter.VAR) {
            actions.add(Action.VAR);
            actions.add(Action.PUSH);
        } else {
            actions.add(Action.NUM);
            actions.add(Action.PUSH);
        }
        return actions;
    }

    public static List<Action> parse_pop(Parameter parameter) {
        List<Action> actions = new ArrayList<>();
        if (parameter == Parameter.IO) {
            actions.add(Action.POP);
            actions.add(Action.PRINTF);
        } else {
            actions.add(Action.POP);
            actions.add(Action.RESTORE);
        }
        return actions;
    }

    public static List<Action> parse_op(Parameter parameter, MainAction mainAction) {
        List<Action> actions = new ArrayList<>();
        actions.add(Action.POP);
        actions.add(Action.POP);
        if (mainAction == MainAction.ADD) {
            actions.add(Action.ADD);
        } else {
            actions.add(Action.MUL);
        }

        actions.add(Action.PUSH);
        return actions;
    }
}
