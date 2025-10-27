import generated.MiniCBaseListener;
import generated.MiniCParser;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

public class MiniCPrettyPrinter extends MiniCBaseListener {
    public StringBuilder prettyResult = new StringBuilder();
    // 각 노드에 대응하는 문자열을 저장하는 변수
    private final ParseTreeProperty<String> texts = new ParseTreeProperty<>();

    // 들여쓰기에 사용할 공백 4개
    private static final int INDENT_WIDTH = 4;
    // if while for switch 는 괄호 띄어쓰기위해 따로 지정
    private static final Set<String> KEYWORDS_BEFORE_PAREN =
            new HashSet<>(Arrays.asList("if", "while", "for", "switch"));

    @Override
    public void exitProgram(MiniCParser.ProgramContext ctx) {
        // 프로그램은 decl들의 집합
        List<String> declTexts = new ArrayList<>();
        // decl들 재귀적으로 문자열로 바꾸기 시작
        for (MiniCParser.DeclContext declCtx : ctx.decl()) {
            declTexts.add(getText(declCtx));
        }
        // 결과에 빈 값들 제외하면서 합치기
        String text = declTexts.stream()
                .filter(s -> s != null && !s.isEmpty())
                .collect(Collectors.joining());

        // this.text에 묶기, 이전 prettyResult 남아있는 경우 대비해서 초기화
        setText(ctx, text);
        prettyResult.setLength(0);
        prettyResult.append(text);
    }

    @Override
    public void exitDecl(MiniCParser.DeclContext ctx) {
        String body = "";
        // decl의 종류에 따라서 나눠서 처리
        if (ctx.var_decl() != null) {
            body = getText(ctx.var_decl());
        } else if (ctx.fun_decl() != null) {
            body = getText(ctx.fun_decl());
        }
        // 각 decl마다 뒤에 개행문자를 넣기
        if (!body.endsWith("\n")) {
            body += "\n";
        }
        setText(ctx, body);
    }

    @Override  // 선언만하고 초기화 등을 안하는 경우
    public void exitVar_decl(MiniCParser.Var_declContext ctx) {
        String literal = ctx.LITERAL() == null ? null : ctx.LITERAL().getText();
        setText(ctx, formatVarOrLocalDecl(ctx.type_spec(), ctx.IDENT().getText(), literal, ctx.getChildCount()));
    }

    @Override
    public void exitType_spec(MiniCParser.Type_specContext ctx) {
        setText(ctx, ctx.getText());
    }

    @Override
    public void exitFun_decl(MiniCParser.Fun_declContext ctx) {
        StringBuilder builder = new StringBuilder();
        builder.append(getText(ctx.type_spec()))    // 반환값
                .append(' ')                        // 공백
                .append(ctx.IDENT().getText())      // 함수명
                .append('(')
                .append(getText(ctx.params()))      // 파라미터
                .append(") ")
                .append(getText(ctx.compound_stmt()));  // 본문
        setText(ctx, builder.toString());
    }

    @Override
    public void exitParams(MiniCParser.ParamsContext ctx) {
        if (ctx.param().isEmpty()) {
            // 파라미터 void와 아에 비어있는 경우로 나뉘어있으니 나눠서 처리
            if (ctx.VOID() != null) {
                setText(ctx, "void");
            } else {
                setText(ctx, "");
            }
            return;
        }
        // 파라미터가 존재하니 각 파라미터마다 처리
        List<String> items = new ArrayList<>();
        for (MiniCParser.ParamContext paramCtx : ctx.param()) {
            items.add(getText(paramCtx).trim());
        }
        setText(ctx, String.join(", ", items));
    }

    @Override
    public void exitParam(MiniCParser.ParamContext ctx) {
        StringBuilder builder = new StringBuilder();
        builder.append(getText(ctx.type_spec()))
                .append(' ')
                .append(ctx.IDENT().getText());
        // getChildCount 로 파라미터가 배열인지 아닌지 체크
        if (ctx.getChildCount() > 2) {
            builder.append("[]");
        }
        setText(ctx, builder.toString());
    }

    @Override
    public void exitStmt(MiniCParser.StmtContext ctx) {
        String text = "";
        // stmt 종류별로
        if (ctx.expr_stmt() != null) {
            text = getText(ctx.expr_stmt());
        } else if (ctx.compound_stmt() != null) {
            text = getText(ctx.compound_stmt());
        } else if (ctx.if_stmt() != null) {
            text = getText(ctx.if_stmt());
        } else if (ctx.while_stmt() != null) {
            text = getText(ctx.while_stmt());
        } else if (ctx.return_stmt() != null) {
            text = getText(ctx.return_stmt());
        }
        if (!text.endsWith("\n")) {
            text += "\n";
        }
        setText(ctx, text);
    }

    @Override
    public void exitExpr_stmt(MiniCParser.Expr_stmtContext ctx) {
        setText(ctx, getText(ctx.expr()) + ";");
    }

    @Override
    public void exitWhile_stmt(MiniCParser.While_stmtContext ctx) {
        String expr = getText(ctx.expr());
        String stmtText = getText(ctx.stmt());
        boolean isBlock = stmtText.trim().startsWith("{");  // while문이 {} 형태의 여러줄인지 아니면 하나인지

        StringBuilder builder = new StringBuilder();
        builder.append("while (").append(expr).append(")");
        if (isBlock) {
            builder.append(' ').append(stmtText.trim());
            if (stmtText.endsWith("\n")) {
                builder.append('\n');
            }
        } else {
            builder.append('\n').append(stmtText);
        }
        setText(ctx, builder.toString());
    }

    @Override
    public void exitCompound_stmt(MiniCParser.Compound_stmtContext ctx) {
        StringBuilder builder = new StringBuilder();
        builder.append("{\n");
        // 지역 변수 선언과 문장을 들여쓰기 한 줄씩 추가
        for (MiniCParser.Local_declContext localDecl : ctx.local_decl()) {
            builder.append(indentLines(getText(localDecl), 1));
        }
        for (MiniCParser.StmtContext stmtCtx : ctx.stmt()) {
            builder.append(indentLines(getText(stmtCtx), 1));
        }
        builder.append("}");
        setText(ctx, builder.toString());
    }

    @Override
    public void exitLocal_decl(MiniCParser.Local_declContext ctx) {
        String literal = ctx.LITERAL() == null ? null : ctx.LITERAL().getText();
        String text = formatVarOrLocalDecl(ctx.type_spec(), ctx.IDENT().getText(), literal, ctx.getChildCount());
        if (!text.endsWith("\n")) {
            text += "\n";
        }
        setText(ctx, text);
    }

    @Override
    public void exitIf_stmt(MiniCParser.If_stmtContext ctx) {
        String condition = getText(ctx.expr());
        String thenPart = getText(ctx.stmt(0));
        boolean thenIsBlock = thenPart.trim().startsWith("{");

        StringBuilder builder = new StringBuilder();
        builder.append("if (").append(condition).append(")");
        if (thenIsBlock) {
            builder.append(' ').append(thenPart.trim());
            if (!thenPart.endsWith("\n")) {
                builder.append('\n');
            }
        } else {
            builder.append('\n').append(thenPart);
        }

        if (ctx.ELSE() != null) {
            String elsePart = getText(ctx.stmt(1));
            boolean elseIsBlock = elsePart.trim().startsWith("{");
            if (thenIsBlock) {
                builder.append('\n');
            }
            builder.append("else");
            if (elseIsBlock) {
                builder.append(' ').append(elsePart.trim());
                if (!elsePart.endsWith("\n")) {
                    builder.append('\n');
                }
            } else {
                builder.append('\n').append(elsePart);
            }
        }

        setText(ctx, builder.toString());
    }

    @Override
    public void exitReturn_stmt(MiniCParser.Return_stmtContext ctx) {
        StringBuilder builder = new StringBuilder();
        builder.append("return");
        if (ctx.expr() != null) {
            builder.append(' ').append(getText(ctx.expr()));
        }
        builder.append(";");
        setText(ctx, builder.toString());
    }

    @Override
    public void exitExpr(MiniCParser.ExprContext ctx) {
        int childCount = ctx.getChildCount();

        if (childCount == 1) {
            // 단일 토큰(expressions) 그대로 반환
            setText(ctx, text(ctx.getChild(0)));
            return;
        }

        if (childCount == 2) {
            // 단항 연산자는 연산자와 피연산자를 붙여서 반환
            String operator = text(ctx.getChild(0));
            String operand = text(ctx.getChild(1));
            setText(ctx, operator + operand);
            return;
        }

        if (childCount == 3) {
            String first = text(ctx.getChild(0));
            String second = text(ctx.getChild(1));
            String third = text(ctx.getChild(2));

            if ("(".equals(first) && ")".equals(third)) {
                // 괄호 내부는 공백 없이 붙여서 감싼다
                setText(ctx, "(" + text(ctx.getChild(1)) + ")");
                return;
            }

            String left = text(ctx.getChild(0));
            String op = second;
            String right = text(ctx.getChild(2));
            setText(ctx, joinParts(Arrays.asList(left, op, right)));
            return;
        }

        if (childCount == 4) {
            String first = text(ctx.getChild(0));
            String second = text(ctx.getChild(1));
            String third = text(ctx.getChild(2));
            String fourth = text(ctx.getChild(3));

            if ("(".equals(second) && ")".equals(fourth)) {
                setText(ctx, first + "(" + third + ")");
                return;
            }
            if ("[".equals(second) && "]".equals(fourth)) {
                setText(ctx, first + "[" + third + "]");
                return;
            }
        }

        if (childCount == 6) {
            String first = text(ctx.getChild(0));
            String second = text(ctx.getChild(1));
            String third = text(ctx.getChild(2));
            String fourth = text(ctx.getChild(3));
            String fifth = text(ctx.getChild(4));
            String sixth = text(ctx.getChild(5));

            if ("[".equals(second) && "]".equals(fourth) && "=".equals(fifth)) {
                String left = first + "[" + third + "]";
                setText(ctx, joinParts(Arrays.asList(left, "=", sixth)));
                return;
            }
        }

        setText(ctx, joinChildren(ctx));
    }

    @Override
    public void exitArgs(MiniCParser.ArgsContext ctx) {
        if (ctx.expr().isEmpty()) {
            setText(ctx, "");
            return;
        }
        List<String> items = new ArrayList<>();
        for (MiniCParser.ExprContext exprCtx : ctx.expr()) {
            items.add(getText(exprCtx));
        }
        setText(ctx, String.join(", ", items));
    }

    private void setText(ParserRuleContext ctx, String text) {
        texts.put(ctx, text == null ? "" : text);
    }

    private String getText(ParserRuleContext ctx) {
        if (ctx == null) {
            return "";
        }
        String stored = texts.get(ctx);
        return stored == null ? ctx.getText() : stored;
    }

    private String joinChildren(ParserRuleContext ctx) {
        List<String> parts = new ArrayList<>();
        for (int i = 0; i < ctx.getChildCount(); i++) {
            parts.add(text(ctx.getChild(i)));
        }
        return joinParts(parts);
    }

    private String joinParts(List<String> rawParts) {
        List<String> parts = rawParts.stream()
                .map(part -> part == null ? "" : part.trim())
                .filter(part -> !part.isEmpty())
                .collect(Collectors.toList());
        if (parts.isEmpty()) {
            return "";
        }

        StringBuilder builder = new StringBuilder();
        String previousPart = null;
        boolean suppressSpaceNext = false;
        boolean forceSpaceNext = false;

        for (String part : parts) {
            boolean applyForceSpace = forceSpaceNext;
            boolean suppressSpace = suppressSpaceNext;
            forceSpaceNext = false;
            suppressSpaceNext = false;

            boolean isSemicolon = ";".equals(part);
            boolean isComma = ",".equals(part);
            boolean isOpen = "(".equals(part) || "[".equals(part);
            boolean isClose = ")".equals(part) || "]".equals(part);

            boolean attachToPrevious = isSemicolon || isComma || isClose
                    || (isOpen && shouldAttachOpen(previousPart));

            if (builder.length() > 0) {
                if (applyForceSpace) {
                    builder.append(' ');
                } else if (!attachToPrevious && !suppressSpace) {
                    builder.append(' ');
                }
            }

            builder.append(part);
            previousPart = part;

            if (isOpen) {
                suppressSpaceNext = true;
            }
            if (isSemicolon || isComma) {
                forceSpaceNext = true;
            }
        }

        return builder.toString();
    }

    private String text(ParseTree node) {
        if (node instanceof ParserRuleContext) {
            return getText((ParserRuleContext) node);
        }
        return node.getText();
    }

    private String formatVarOrLocalDecl(MiniCParser.Type_specContext typeSpec,
                                        String identifier,
                                        String literalText,
                                        int childCount) {
        StringBuilder builder = new StringBuilder();
        // 기본 선언 형태: 타입 + 공백 + 식별자
        builder.append(getText(typeSpec)).append(' ').append(identifier);
        if (childCount == 5) {
            builder.append(" = ").append(literalText);
        } else if (childCount == 6) {
            builder.append('[').append(literalText).append(']');
        }
        builder.append(";");
        return builder.toString();
    }

    private String indent(int level) {
        if (level <= 0) {
            return "";
        }
        char[] chars = new char[level * INDENT_WIDTH];
        // 공백으로 들여쓰기 문자열 구성
        Arrays.fill(chars, ' ');
        return new String(chars);
    }

    private boolean shouldAttachOpen(String previousPart) {
        if (previousPart == null || previousPart.isEmpty()) {
            return false;
        }
        if (KEYWORDS_BEFORE_PAREN.contains(previousPart)) {
            return false;
        }
        char last = previousPart.charAt(previousPart.length() - 1);
        return Character.isLetterOrDigit(last) || last == '_' || last == ')' || last == ']' || last == '}';
    }

    private String indentLines(String text, int indentLevel) {
        if (text == null || text.isEmpty()) {
            return "";
        }
        String indentString = indent(indentLevel);
        // 줄마다 동일한 들여쓰기를 삽입
        StringBuilder result = new StringBuilder();
        result.append(indentString);
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            result.append(c);
            if (c == '\n' && i < text.length() - 1) {
                result.append(indentString);
            }
        }
        if (text.endsWith("\n")) {
            int indentLength = indentString.length();
            if (indentLength > 0 && result.length() >= indentLength) {
                int start = result.length() - indentLength;
                boolean endsWithIndent = true;
                for (int i = 0; i < indentLength; i++) {
                    if (result.charAt(start + i) != indentString.charAt(i)) {
                        endsWithIndent = false;
                        break;
                    }
                }
                if (endsWithIndent) {
                    result.setLength(start);
                }
            }
        }
        return result.toString();
    }
}
