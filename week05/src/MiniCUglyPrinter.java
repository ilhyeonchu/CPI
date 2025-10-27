import generated.*;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class MiniCUglyPrinter extends MiniCBaseListener {
    public StringBuilder uglyResult = new StringBuilder();

    @Override
    public void enterWhile_stmt(MiniCParser.While_stmtContext ctx) {
        uglyResult.append("while (\n");
        ParserRuleContext expr = ctx.expr();

        uglyResult.append(expr.getText());
    }
}
