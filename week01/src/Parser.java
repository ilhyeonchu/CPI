import java.util.*;
import ast.*;


// tokens를 받아 각 토큰이 c언어로 변환될때 어떤 명령어들을 실행하는지 분석
// 분석된 내용을 block으로 변환 후 blocks에 모아서 반환
// block 은 exprs과 관련 정보를 가지고 있음
public class Parser {
    public static List<Block> parser(List<Token> tokens) {
        List<Block> blocks = new ArrayList<>();
        int state, start, end;
        for (Token token : tokens) { // 각 토큰 파싱?하기
            Block block = new Block();
            block = parsing(token);
            blocks.add(block);
        }
        return blocks;
    }


    // 각 토큰의 MainAction을 확인하여 각각 적절한 메서드를 호출
    // 메서드를 통해 expr들의 리스트 받아 exprs에 모은 후 반환
    public static Block parsing(Token token) {
        Block block = new Block();
        if (token.mainAction == MainAction.PUSH) {
            block.exprs = parse_push(token.parameter);
        } else if (token.mainAction == MainAction.POP) {
            block.exprs = parse_pop(token.parameter);
        } else {
            block.exprs = parse_op(token.parameter, token.mainAction);
        }
        block.mainaction = token.mainAction;
        block.value = token.value;
        return block;
    }

    // MainAction이 PUSH인 경우
    // 뒤에 오는 인자는 IO, 변수, 숫자 중 하나
    // 케이스를 나눠서 적절한 expr로 바꿔 exprs에 추가 후 반환
    public static List<Expr> parse_push(Parameter parameter) {
        List<Expr> exprs = new ArrayList<>();
        if (parameter == Parameter.IO) {
            exprs.add(Expr.SCANF);
            exprs.add(Expr.PUSH);
        } else if (parameter == Parameter.VAR) {
            exprs.add(Expr.VAR);
            exprs.add(Expr.PUSH);
        } else {
            exprs.add(Expr.NUM);
            exprs.add(Expr.PUSH);
        }
        return exprs;
    }

    // MainAction이 POP인 경우
    // 뒤에 오는 인자는 IO, 변수 중 하나
    // 케이스를 나눠서 적절한 expr로 바꿔 exprs에 추가 후 반환
    public static List<Expr> parse_pop(Parameter parameter) {
        List<Expr> exprs = new ArrayList<>();
        if (parameter == Parameter.IO) {
            exprs.add(Expr.POP);
            exprs.add(Expr.PRINTF);
        } else {
            exprs.add(Expr.VAR);
            exprs.add(Expr.POP);
            exprs.add(Expr.RESTORE);
        }
        return exprs;
    }

    // MainAction이 ADD 또는 MUL인 경우
    // 뒤에 오는 인자는 없음
    // 케이스를 나눠서 적절한 expr로 바꿔 exprs에 추가 후 반환
    public static List<Expr> parse_op(Parameter parameter, MainAction mainAction) {
        List<Expr> exprs = new ArrayList<>();
        exprs.add(Expr.POP);
        exprs.add(Expr.POP);
        if (mainAction == MainAction.ADD) {
            exprs.add(Expr.ADD);
        } else {
            exprs.add(Expr.MUL);
        }

        exprs.add(Expr.PUSH);
        return exprs;
    }
}
