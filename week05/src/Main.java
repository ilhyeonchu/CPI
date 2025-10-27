import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import generated.MiniCLexer;
import generated.MiniCParser;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class Main {
    public static void main(String[] args) throws Exception {
        MiniCLexer lexer = new MiniCLexer(CharStreams.fromFileName("./src/MiniC_테스트케이스/201802168.mc"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MiniCParser parser = new MiniCParser(tokens);
        ParseTree tree = parser.program();

        ParseTreeWalker walker = new ParseTreeWalker();
        // MiniCUglyPrinter uglyPrinter = new MiniCUglyPrinter();
        // walker.walk(uglyPrinter, tree);
        MiniCPrettyPrinter prettyPrinter = new MiniCPrettyPrinter();
        walker.walk(prettyPrinter, tree);

        Path outputPath = Path.of("./output.mc");
        Files.writeString(
                outputPath,
                prettyPrinter.prettyResult.toString(),
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING,
                StandardOpenOption.WRITE
        );
    }
}
