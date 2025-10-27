import generated.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

public class MiniCUglyPrinter extends MiniCBaseListener {
    public StringBuilder uglyResult = new StringBuilder();
    private final ParseTreeProperty<String> texts = new ParseTreeProperty<>();

    @Override
    public void enterWhile_stmt(MiniCParser.While_stmtContext ctx) {
        uglyResult.append("while ( \n");
        ParserRuleContext expr = ctx.expr();

        uglyResult.append(expr.getText());
    }

    @Override
    public void exitProgram(MiniCParser.ProgramContext ctx) {
        List<String> declTexts = new ArrayList<>();
        for (MiniCParser.DeclContext declCtx : ctx.decl()) {
            declTexts.add(getText(declCtx));
        }
        String joined = joinParts(declTexts);
        setText(ctx, joined);
        uglyResult.setLength(0);
        uglyResult.append(joined);
    }

    @Override
    public void exitDecl(MiniCParser.DeclContext ctx) {
        if (ctx.var_decl() != null) {
            setText(ctx, getText(ctx.var_decl()));
        } else if (ctx.fun_decl() != null) {
            setText(ctx, getText(ctx.fun_decl()));
        } else {
            setText(ctx, "");
        }
    }

    @Override
    public void exitVar_decl(MiniCParser.Var_declContext ctx) {
        setText(ctx, joinChildren(ctx));
    }

    @Override
    public void exitType_spec(MiniCParser.Type_specContext ctx) {
        setText(ctx, joinChildren(ctx));
    }

    @Override
    public void exitFun_decl(MiniCParser.Fun_declContext ctx) {
        List<String> parts = new ArrayList<>();
        parts.add(getText(ctx.type_spec()));
        parts.add(ctx.IDENT().getText());
        parts.add("(");
        parts.add(getText(ctx.params()));
        parts.add(")");
        parts.add(getText(ctx.compound_stmt()));
        setText(ctx, joinParts(parts));
    }

    @Override
    public void exitParams(MiniCParser.ParamsContext ctx) {
        if (ctx.param().isEmpty()) {
            if (ctx.VOID() != null) {
                setText(ctx, "void");
            } else {
                setText(ctx, "");
            }
            return;
        }
        List<String> parts = new ArrayList<>();
        int count = ctx.param().size();
        for (int i = 0; i < count; i++) {
            parts.add(getText(ctx.param(i)));
            if (i < count - 1) {
                parts.add(",");
            }
        }
        setText(ctx, joinParts(parts));
    }

    @Override
    public void exitParam(MiniCParser.ParamContext ctx) {
        List<String> parts = new ArrayList<>();
        parts.add(getText(ctx.type_spec()));
        parts.add(ctx.IDENT().getText());
        if (ctx.getChildCount() > 2) {
            parts.add("[");
            parts.add("]");
        }
        setText(ctx, joinParts(parts));
    }

    @Override
    public void exitStmt(MiniCParser.StmtContext ctx) {
        if (ctx.expr_stmt() != null) {
            setText(ctx, getText(ctx.expr_stmt()));
        } else if (ctx.compound_stmt() != null) {
            setText(ctx, getText(ctx.compound_stmt()));
        } else if (ctx.if_stmt() != null) {
            setText(ctx, getText(ctx.if_stmt()));
        } else if (ctx.while_stmt() != null) {
            setText(ctx, getText(ctx.while_stmt()));
        } else if (ctx.return_stmt() != null) {
            setText(ctx, getText(ctx.return_stmt()));
        } else {
            setText(ctx, "");
        }
    }

    @Override
    public void exitExpr_stmt(MiniCParser.Expr_stmtContext ctx) {
        List<String> parts = new ArrayList<>();
        parts.add(getText(ctx.expr()));
        parts.add(";");
        setText(ctx, joinParts(parts));
    }

    @Override
    public void exitWhile_stmt(MiniCParser.While_stmtContext ctx) {
        List<String> parts = new ArrayList<>();
        parts.add("while");
        parts.add("(");
        parts.add(getText(ctx.expr()));
        parts.add(")");
        parts.add(getText(ctx.stmt()));
        setText(ctx, joinParts(parts));
    }

    @Override
    public void exitCompound_stmt(MiniCParser.Compound_stmtContext ctx) {
        List<String> parts = new ArrayList<>();
        parts.add("{");
        ctx.local_decl().forEach(local -> parts.add(getText(local)));
        ctx.stmt().forEach(stmt -> parts.add(getText(stmt)));
        parts.add("}");
        setText(ctx, joinParts(parts));
    }

    @Override
    public void exitLocal_decl(MiniCParser.Local_declContext ctx) {
        setText(ctx, joinChildren(ctx));
    }

    @Override
    public void exitIf_stmt(MiniCParser.If_stmtContext ctx) {
        List<String> parts = new ArrayList<>();
        parts.add("if");
        parts.add("(");
        parts.add(getText(ctx.expr()));
        parts.add(")");
        parts.add(getText(ctx.stmt(0)));
        if (ctx.ELSE() != null) {
            parts.add("else");
            parts.add(getText(ctx.stmt(1)));
        }
        setText(ctx, joinParts(parts));
    }

    @Override
    public void exitReturn_stmt(MiniCParser.Return_stmtContext ctx) {
        List<String> parts = new ArrayList<>();
        parts.add("return");
        if (ctx.expr() != null) {
            parts.add(getText(ctx.expr()));
        }
        parts.add(";");
        setText(ctx, joinParts(parts));
    }

    @Override
    public void exitExpr(MiniCParser.ExprContext ctx) {
        setText(ctx, joinChildren(ctx));
    }

    @Override
    public void exitArgs(MiniCParser.ArgsContext ctx) {
        if (ctx.expr().isEmpty()) {
            setText(ctx, "");
            return;
        }
        List<String> parts = new ArrayList<>();
        int count = ctx.expr().size();
        for (int i = 0; i < count; i++) {
            parts.add(getText(ctx.expr(i)));
            if (i < count - 1) {
                parts.add(",");
            }
        }
        setText(ctx, joinParts(parts));
    }

    private void setText(ParserRuleContext ctx, String text) {
        if (ctx == null) {
            return;
        }
        texts.put(ctx, text == null ? "" : normalizeWhitespace(text));
    }

    private String getText(ParserRuleContext ctx) {
        if (ctx == null) {
            return "";
        }
        String text = texts.get(ctx);
        if (text != null) {
            return text;
        }
        return normalizeWhitespace(ctx.getText());
    }

    private String joinChildren(ParserRuleContext ctx) {
        List<String> parts = new ArrayList<>();
        for (int i = 0; i < ctx.getChildCount(); i++) {
            ParseTree child = ctx.getChild(i);
            if (child instanceof ParserRuleContext) {
                parts.add(getText((ParserRuleContext) child));
            } else {
                parts.add(child.getText());
            }
        }
        return joinParts(parts);
    }

    private String joinParts(List<String> rawParts) {
        List<String> parts = rawParts.stream()
                .map(part -> part == null ? "" : normalizeWhitespace(part))
                .filter(part -> !part.isEmpty())
                .collect(Collectors.toList());
        return normalizeWhitespace(String.join(" ", parts));
    }

    private String normalizeWhitespace(String text) {
        return text.trim().replaceAll("\\s+", " ");
    }
}
