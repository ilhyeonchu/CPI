import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import generated.MiniCLexer;
import generated.MiniCParser;

public class Main {
    public static void main(String[] args) throws Exception {
        MiniCLexer lexer = new MiniCLexer(CharStreams.fromFileName("./src/MiniC_테스트케이스/201802168.mc"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MiniCParser parser = new MiniCParser(tokens);
        ParseTree tree = parser.program();
    }
}