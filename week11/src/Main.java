import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import generated.MiniCLexer;
import generated.MiniCParser;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class Main {
    public static void main(String[] args) throws Exception {
        MiniCLexer lexer = new MiniCLexer(CharStreams.fromFileName("./input.mc"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MiniCParser parser = new MiniCParser(tokens);
        ParseTree tree = parser.program();

        ParseTreeWalker walker = new ParseTreeWalker();
        MiniC2IR threeAddress = new MiniC2IR();
        walker.walk(threeAddress, tree);

        Path outputPath = Path.of("./output.ir");
        Files.writeString(
                outputPath,
                threeAddress.irResult.toString(),
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING,
                StandardOpenOption.WRITE
        );
    }
}
