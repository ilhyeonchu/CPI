import generated.MiniCBaseListener;
import generated.MiniCParser;
import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.TerminalNode;

public class MiniC2IR extends MiniCBaseListener {
    // ir? 결과 저장
    public StringBuilder irResult = new StringBuilder();
    // 각 노드별 생성된 값을 저장
    private final ParseTreeProperty<String> codes = new ParseTreeProperty<>();
    private final ParseTreeProperty<String> values = new ParseTreeProperty<>();

    // 임시 변수/레이블 번호 생성용 카운터
    private int tempCounter = 0;
    private int labelCounter = 0;

    @Override
    public void exitProgram(MiniCParser.ProgramContext ctx) {
        StringBuilder builder = new StringBuilder();

        // decl들을 순서대로 이어붙이기
        for (MiniCParser.DeclContext declCtx : ctx.decl()) {
            String code = getCode(declCtx);
            if (code == null || code.isBlank()) {
                continue;
            }
            builder.append(code);
            if (!code.endsWith("\n")) {
                builder.append('\n');
            }
        }

        String result = builder.toString();
        setCode(ctx, result);
        irResult.setLength(0);
        irResult.append(result);
    }

    @Override
    public void exitDecl(MiniCParser.DeclContext ctx) {
        // 각 decl이 var, fun이냐에 따라서 처리
        if (ctx.var_decl() != null) {
            setCode(ctx, getCode(ctx.var_decl()));
        } else if (ctx.fun_decl() != null) {
            setCode(ctx, getCode(ctx.fun_decl()));
        } else {
            setCode(ctx, "");
        }
    }

    @Override
    public void exitFun_decl(MiniCParser.Fun_declContext ctx) {
        String header = buildFunctionHeader(ctx);
        String body = getCode(ctx.compound_stmt());

        StringBuilder builder = new StringBuilder();
        builder.append(header).append("\n");
        if (body != null) {
            builder.append(body);
            if (!body.endsWith("\n")) {
                builder.append('\n');
            }
        }
        builder.append("end function\n");
        setCode(ctx, builder.toString());
    }

    @Override
    public void exitCompound_stmt(MiniCParser.Compound_stmtContext ctx) {
        StringBuilder builder = new StringBuilder();
        // 블록 안에서 선언들 처리
        for (MiniCParser.Local_declContext localDecl : ctx.local_decl()) {
            String code = getCode(localDecl);
            if (code != null && !code.isBlank()) {
                builder.append(code);
                // 개행문자
                if (!code.endsWith("\n")) {
                    builder.append('\n');
                }
            }
        }
        // 블록 안 stmt처리
        for (MiniCParser.StmtContext stmtCtx : ctx.stmt()) {
            String code = getCode(stmtCtx);
            if (code != null && !code.isBlank()) {
                builder.append(code);
                if (!code.endsWith("\n")) {
                    builder.append('\n');
                }
            }
        }
        setCode(ctx, builder.toString());
    }

    // 지역 변수 선언?시 값 초기화
    @Override
    public void exitLocal_decl(MiniCParser.Local_declContext ctx) {
        String code = buildInitInstruction(ctx.type_spec(), ctx.IDENT().getText(), ctx.LITERAL(), ctx.getChildCount());
        setCode(ctx, code);
    }

    @Override
    public void exitStmt(MiniCParser.StmtContext ctx) {
        if (ctx.expr_stmt() != null) {
            setCode(ctx, getCode(ctx.expr_stmt()));
            return;
        }
        if (ctx.compound_stmt() != null) {
            setCode(ctx, getCode(ctx.compound_stmt()));
            return;
        }
        if (ctx.if_stmt() != null) {
            setCode(ctx, getCode(ctx.if_stmt()));
            return;
        }
        if (ctx.while_stmt() != null) {
            setCode(ctx, getCode(ctx.while_stmt()));
            return;
        }
        if (ctx.return_stmt() != null) {
            setCode(ctx, getCode(ctx.return_stmt()));
        }
    }

    @Override
    public void exitExpr_stmt(MiniCParser.Expr_stmtContext ctx) {
        String code = getCode(ctx.expr());
        setCode(ctx, code);
    }

    @Override
    public void exitWhile_stmt(MiniCParser.While_stmtContext ctx) {
        // 라벨 생성(L + num)
        String startLabel = newLabel("L");
        String endLabel = newLabel("L");

        StringBuilder builder = new StringBuilder();
        appendLine(builder, startLabel + ":"); // 시작 설정
        builder.append(getCode(ctx.expr())); // 조건
        // 조건이 거짓일 때 종료로 점프하도록 not 적용 후 cjump
        String condValue = getValue(ctx.expr());
        String condIsFalse = newTemp();
        appendLine(builder, condIsFalse + " = not " + condValue);
        appendLine(builder, "cjump " + condIsFalse + " " + endLabel);

        // 참인 경우 본문 코드, 시작 지점으로 복귀
        builder.append(getCode(ctx.stmt()));
        appendLine(builder, "jump " + startLabel);

        // 반복 종료
        appendLine(builder, endLabel + ":");
        setCode(ctx, builder.toString());
    }

    @Override
    public void exitIf_stmt(MiniCParser.If_stmtContext ctx) {
        // else가 없는 경우는 elseLabel이 종료 라벨이기도함
        // if else는 elseLabel과 endLabel 구별
        String elseLabel = newLabel("L");
        String endLabel = ctx.ELSE() != null ? newLabel("L") : elseLabel;

        StringBuilder builder = new StringBuilder();
        // 조건 확인 코드
        builder.append(getCode(ctx.expr()));
        // 조건이 거짓인 경우 elseLabel로 점프 (not 사용)
        String condValue = getValue(ctx.expr());
        String condIsFalse = newTemp();
        appendLine(builder, condIsFalse + " = not " + condValue);
        appendLine(builder, "cjump " + condIsFalse + " " + elseLabel);

        // 참인 경우 보눈
        builder.append(getCode(ctx.stmt(0)));

        // 라벨 생성때와 마찬가지로 else 유무로 바뀜
        if (ctx.ELSE() != null) { // else있으므로 참인경우 실행 후 점프
            appendLine(builder, "jump " + endLabel);
        }

        // else(없을 경우 end라벨임) 라벨 추가
        appendLine(builder, elseLabel + ":");

        // else가 있는 경우
        if (ctx.ELSE() != null) {
            builder.append(getCode(ctx.stmt(1)));
            appendLine(builder, endLabel + ":");
        }
        setCode(ctx, builder.toString());
    }

    @Override
    public void exitReturn_stmt(MiniCParser.Return_stmtContext ctx) {
        StringBuilder builder = new StringBuilder();
        if (ctx.expr() != null) {
            // 반환값이 있을 때는 값 계산 후 return <val>
            builder.append(getCode(ctx.expr()));
            appendLine(builder, "return " + getValue(ctx.expr()));
        } else {
            appendLine(builder, "return");
        }
        setCode(ctx, builder.toString());
    }

    @Override
    public void exitExpr(MiniCParser.ExprContext ctx) {
        int childCount = ctx.getChildCount();

        if (childCount == 1) {
            // 리터럴,식별자 그대로 사용 우변 값 전달
            String value = ctx.getChild(0).getText();
            setCode(ctx, "");
            setValue(ctx, value);
            return;
        }

        if (childCount == 2) {
            // 단항 연산자 처리
            String opToken = ctx.getChild(0).getText();
            MiniCParser.ExprContext operandCtx = ctx.expr(0);
            String operandCode = getCode(operandCtx);
            String operandValue = getValue(operandCtx);

            String op = mapUnary(opToken);
            String dest = shouldUpdateOperand(op) && isIdentifier(operandValue)
                    ? operandValue
                    : newTemp();

            StringBuilder builder = new StringBuilder();
            builder.append(operandCode);
            appendLine(builder, dest + " = " + op + " " + operandValue);

            setCode(ctx, builder.toString());
            setValue(ctx, dest);
            return;
        }

        // 이항 연산자 또는 괄호
        if (childCount == 3) {
            String first = ctx.getChild(0).getText();
            String second = ctx.getChild(1).getText();
            String third = ctx.getChild(2).getText();

            if ("(".equals(first) && ")".equals(third)) {
                // 괄호는 내부 값을 그대로 전달
                setCode(ctx, getCode(ctx.expr(0)));
                setValue(ctx, getValue(ctx.expr(0)));
                return;
            }

            if ("=".equals(second)) {
                // 단순 대입
                String dest = first;
                MiniCParser.ExprContext rightCtx = ctx.expr(0);
                StringBuilder builder = new StringBuilder();
                builder.append(getCode(rightCtx));
                appendLine(builder, dest + " = " + getValue(rightCtx));

                setCode(ctx, builder.toString());
                setValue(ctx, dest);
                return;
            }

            // 이항 연산자인 경우
            MiniCParser.ExprContext leftCtx = ctx.expr(0);
            MiniCParser.ExprContext rightCtx = ctx.expr(1);
            String leftCode = getCode(leftCtx);
            String rightCode = getCode(rightCtx);
            String op = mapBinary(second);
            String dest = newTemp();

            StringBuilder builder = new StringBuilder();
            builder.append(leftCode);
            builder.append(rightCode);
            appendLine(builder, dest + " = " + getValue(leftCtx) + " " + op + " " + getValue(rightCtx));

            setCode(ctx, builder.toString());
            setValue(ctx, dest);
            return;
        }

        if (childCount == 4) {
            String first = ctx.getChild(0).getText();
            String second = ctx.getChild(1).getText();
            String fourth = ctx.getChild(3).getText();

            if ("(".equals(second) && ")".equals(fourth)) {
                // 함수 호출
                MiniCParser.ArgsContext argsCtx = ctx.args();
                List<String> argValues = new ArrayList<>();
                StringBuilder builder = new StringBuilder();
                if (argsCtx != null) {
                    for (MiniCParser.ExprContext arg : argsCtx.expr()) {
                        builder.append(getCode(arg));
                        argValues.add(getValue(arg));
                    }
                }
                String dest = newTemp();
                String call = dest + " = call " + first + "(" + String.join(", ", argValues) + ")";
                appendLine(builder, call);

                setCode(ctx, builder.toString());
                setValue(ctx, dest);
                return;
            }
            // 배열은 과제x
            // if ("[".equals(second)) {
            // MiniCParser.ExprContext indexCtx = ctx.expr(0);
            // StringBuilder builder = new StringBuilder();
            // builder.append(getCode(indexCtx));
            // String dest = first + "_" + getValue(indexCtx);
            // setCode(ctx, builder.toString());
            // setValue(ctx, dest);
            // return;
            // }
        }

        // 배열은 과제x
        // if (childCount == 6) {
        // String first = ctx.getChild(0).getText();
        // String second = ctx.getChild(1).getText();
        // String fifth = ctx.getChild(4).getText();
        //
        // if ("[".equals(second) && "=".equals(fifth)) {
        // MiniCParser.ExprContext indexCtx = ctx.expr(0);
        // MiniCParser.ExprContext valueCtx = ctx.expr(1);
        //
        // StringBuilder builder = new StringBuilder();
        // builder.append(getCode(indexCtx));
        // builder.append(getCode(valueCtx));
        //
        // String target = first + "_" + getValue(indexCtx);
        // appendLine(builder, target + " = " + getValue(valueCtx));
        //
        // setCode(ctx, builder.toString());
        // setValue(ctx, target);
        // return;
        // }
        // }

        setCode(ctx, "");
        setValue(ctx, ctx.getText());
    }

    private void setCode(ParserRuleContext ctx, String code) {
        codes.put(ctx, code == null ? "" : code);
    }

    private String getCode(ParserRuleContext ctx) {
        if (ctx == null) {
            return "";
        }
        String stored = codes.get(ctx);
        return stored == null ? "" : stored;
    }

    private void setValue(ParserRuleContext ctx, String value) {
        values.put(ctx, value == null ? "" : value);
    }

    private String getValue(ParserRuleContext ctx) {
        if (ctx == null) {
            return "";
        }
        String stored = values.get(ctx);
        return stored == null ? ctx.getText() : stored;
    }

    private String buildFunctionHeader(MiniCParser.Fun_declContext ctx) {
        List<String> paramsWithNames = new ArrayList<>();
        MiniCParser.ParamsContext params = ctx.params();
        if (params != null) {
            for (MiniCParser.ParamContext param : params.param()) {
                paramsWithNames.add(typeCode(param.type_spec()) + " " + param.IDENT().getText());
            }
        }
        String returnType = typeCode(ctx.type_spec());
        return "function " + ctx.IDENT().getText() + "(" + String.join(" ", paramsWithNames) + ") " + returnType;
    }

    private String buildInitInstruction(MiniCParser.Type_specContext typeSpec,
            String identifier,
            TerminalNode literal,
            int childCount) {
        if (childCount == 5 && literal != null) {
            return identifier + " = " + literal.getText() + "\n";
        }
        return "";
    }

    private String typeCode(MiniCParser.Type_specContext ctx) {
        return ctx.VOID() != null ? "V" : "I";
    }

    private String newTemp() {
        return "t" + tempCounter++;
    }

    // labelCounter를 이용해 중복되지 않는 Label 생성
    private String newLabel(String prefix) {
        return prefix + labelCounter++;
    }

    private void appendLine(StringBuilder builder, String line) {
        builder.append(line);
        builder.append('\n');
    }

    private String mapUnary(String token) {
        switch (token) {
            case "-":
                return "neg";
            case "+":
                return "pos";
            case "--":
                return "dec";
            case "++":
                return "inc";
            case "!":
                return "not";
            default:
                return token;
        }
    }

    private boolean shouldUpdateOperand(String op) {
        return "inc".equals(op) || "dec".equals(op);
    }

    private String mapBinary(String token) {
        switch (token) {
            case "*":
                return "mul";
            case "/":
                return "div";
            case "%":
                return "mod";
            case "+":
                return "add";
            case "-":
                return "sub";
            case "==":
                return "eq";
            case "!=":
                return "neq";
            case "<=":
                return "leq";
            case "<":
                return "lt";
            case ">=":
                return "geq";
            case ">":
                return "gt";
            case "and":
                return "and";
            case "or":
                return "or";
            default:
                return token;
        }
    }

    private boolean isIdentifier(String text) {
        if (text == null || text.isEmpty()) {
            return false;
        }
        if (!Character.isLetter(text.charAt(0)) && text.charAt(0) != '_') {
            return false;
        }
        for (int i = 1; i < text.length(); i++) {
            char c = text.charAt(i);
            if (!Character.isLetterOrDigit(c) && c != '_') {
                return false;
            }
        }
        return true;
    }
}
