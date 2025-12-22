import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IR2Jasmin {
    private static class FunctionIR {
        final String name;
        final List<String> paramTypes;
        final List<String> paramNames;
        final String returnType;
        final List<String> instructions;

        FunctionIR(String name, List<String> paramTypes, List<String> paramNames, String returnType, List<String> instructions) {
            this.name = name;
            this.paramTypes = paramTypes;
            this.paramNames = paramNames;
            this.returnType = returnType;
            this.instructions = instructions;
        }
    }

    public String convert(String irText) {
        // 호출 시그니처를 만들기 위해 IR 함수들을 먼저 파싱한다.
        List<FunctionIR> functions = parseFunctions(irText);
        Map<String, FunctionIR> functionMap = new HashMap<>();
        for (FunctionIR function : functions) {
            functionMap.put(function.name, function);
        }

        StringBuilder out = new StringBuilder();
        // 기본 Main 클래스와 scan/print 헬퍼 메서드를 생성한다.
        out.append(".class public Main\n");
        out.append(".super java/lang/Object\n\n");
        out.append(buildConstructor());
        out.append('\n');
        out.append(buildScanMethod());
        out.append('\n');
        out.append(buildPrintMethod());
        out.append('\n');

        for (FunctionIR function : functions) {
            out.append(buildMethod(function, functionMap));
            out.append('\n');
        }
        return out.toString();
    }

    private String buildConstructor() {
        StringBuilder out = new StringBuilder();
        out.append(".method public <init>()V\n");
        out.append("  aload_0\n");
        out.append("  invokespecial java/lang/Object/<init>()V\n");
        out.append("  return\n");
        out.append(".end method\n");
        return out.toString();
    }

    private String buildScanMethod() {
        StringBuilder out = new StringBuilder();
        out.append(".method public static scan()I\n");
        out.append("  .limit stack 4\n");
        out.append("  .limit locals 1\n");
        out.append("  new java/util/Scanner\n");
        out.append("  dup\n");
        out.append("  getstatic java/lang/System/in Ljava/io/InputStream;\n");
        out.append("  invokespecial java/util/Scanner/<init>(Ljava/io/InputStream;)V\n");
        out.append("  invokevirtual java/util/Scanner/nextInt()I\n");
        out.append("  ireturn\n");
        out.append(".end method\n");
        return out.toString();
    }

    private String buildPrintMethod() {
        StringBuilder out = new StringBuilder();
        out.append(".method public static print(I)V\n");
        out.append("  .limit stack 2\n");
        out.append("  .limit locals 1\n");
        out.append("  getstatic java/lang/System/out Ljava/io/PrintStream;\n");
        out.append("  iload_0\n");
        out.append("  invokevirtual java/io/PrintStream/println(I)V\n");
        out.append("  return\n");
        out.append(".end method\n");
        return out.toString();
    }

    private String buildMethod(FunctionIR function, Map<String, FunctionIR> functionMap) {
        StringBuilder out = new StringBuilder();
        boolean isMain = "main".equals(function.name);
        String descriptor;
        if (isMain) {
            // MiniC의 main은 Java의 main(String[] args)로 변환한다.
            descriptor = "([Ljava/lang/String;)V";
        } else {
            StringBuilder params = new StringBuilder();
            for (String type : function.paramTypes) {
                params.append(type);
            }
            descriptor = "(" + params + ")" + function.returnType;
        }

        out.append(".method public static ").append(function.name).append(descriptor).append("\n");
        out.append("  .limit stack 32\n");
        out.append("  .limit locals 32\n");

        Map<String, Integer> locals = new HashMap<>();
        int[] nextLocal = new int[] {isMain ? 1 : 0};
        // 파라미터 로컬 슬롯을 먼저 배정한다.
        for (int i = 0; i < function.paramNames.size(); i++) {
            locals.put(function.paramNames.get(i), nextLocal[0] + i);
        }
        nextLocal[0] += function.paramNames.size();

        int[] labelCounter = new int[] {0};
        boolean hasReturn = false;
        for (String line : function.instructions) {
            String trimmed = line.trim();
            if (trimmed.isEmpty()) {
                continue;
            }
            // 라벨은 그대로 유지해서 분기 타깃을 맞춘다.
            if (trimmed.endsWith(":")) {
                out.append(trimmed).append("\n");
                continue;
            }
            if (trimmed.startsWith("jump ")) {
                String label = trimmed.substring(5).trim();
                out.append("  goto ").append(label).append("\n");
                continue;
            }
            if (trimmed.startsWith("cjump ")) {
                String[] parts = trimmed.split("\\s+");
                if (parts.length >= 3) {
                    // IR cjump은 피연산자가 0이 아닐 때 점프한다.
                    emitLoad(parts[1], out, locals, nextLocal);
                    out.append("  ifne ").append(parts[2]).append("\n");
                }
                continue;
            }
            if (trimmed.startsWith("return")) {
                String rest = trimmed.substring(6).trim();
                if (rest.isEmpty()) {
                    out.append("  return\n");
                } else {
                    emitLoad(rest, out, locals, nextLocal);
                    out.append("  ireturn\n");
                }
                hasReturn = true;
                continue;
            }
            if (trimmed.contains("= call ")) {
                emitCall(trimmed, out, locals, functionMap, nextLocal);
                continue;
            }
            if (trimmed.contains("=")) {
                emitAssign(trimmed, out, locals, labelCounter, nextLocal);
            }
        }

        if (!hasReturn && "V".equals(function.returnType)) {
            out.append("  return\n");
        }
        out.append(".end method\n");
        return out.toString();
    }

    private void emitAssign(String line, StringBuilder out, Map<String, Integer> locals, int[] labelCounter, int[] nextLocal) {
        String[] parts = line.split("=", 2);
        if (parts.length != 2) {
            return;
        }
        String dest = parts[0].trim();
        String rhs = parts[1].trim();
        String[] tokens = rhs.split("\\s+");
        if (tokens.length == 1) {
            emitLoad(tokens[0], out, locals, nextLocal);
            emitStore(dest, out, locals, nextLocal);
            return;
        }
        if (tokens.length == 2) {
            String op = tokens[0];
            String operand = tokens[1];
            emitUnary(op, operand, dest, out, locals, labelCounter, nextLocal);
            return;
        }
        if (tokens.length == 3) {
            String left = tokens[0];
            String op = tokens[1];
            String right = tokens[2];
            emitBinary(left, op, right, dest, out, locals, labelCounter, nextLocal);
        }
    }

    private void emitCall(String line, StringBuilder out, Map<String, Integer> locals,
            Map<String, FunctionIR> functionMap, int[] nextLocal) {
        String[] parts = line.split("=", 2);
        String dest = parts[0].trim();
        String rest = parts[1].trim();
        String callBody = rest.substring("call ".length()).trim();
        int parenStart = callBody.indexOf('(');
        int parenEnd = callBody.lastIndexOf(')');
        if (parenStart < 0 || parenEnd < parenStart) {
            return;
        }
        String funcName = callBody.substring(0, parenStart).trim();
        String argsStr = callBody.substring(parenStart + 1, parenEnd).trim();
        List<String> args = new ArrayList<>();
        if (!argsStr.isEmpty()) {
            for (String part : argsStr.split(",")) {
                args.add(part.trim());
            }
        }

        if ("print".equals(funcName)) {
            // print는 void이므로 대입되는 경우 0을 저장한다.
            if (args.size() == 1) {
                emitLoad(args.get(0), out, locals, nextLocal);
            } else {
                out.append("  iconst_0\n");
            }
            out.append("  invokestatic Main/print(I)V\n");
            if (!dest.isEmpty()) {
                out.append("  iconst_0\n");
                emitStore(dest, out, locals, nextLocal);
            }
            return;
        }

        if ("scan".equals(funcName)) {
            // scan은 int를 반환한다.
            out.append("  invokestatic Main/scan()I\n");
            emitStore(dest, out, locals, nextLocal);
            return;
        }

        FunctionIR callee = functionMap.get(funcName);
        StringBuilder descriptor = new StringBuilder();
        if (callee != null) {
            descriptor.append('(');
            for (String type : callee.paramTypes) {
                descriptor.append(type);
            }
            descriptor.append(')').append(callee.returnType);
        } else {
            descriptor.append("()V");
        }

        for (String arg : args) {
            emitLoad(arg, out, locals, nextLocal);
        }
        out.append("  invokestatic Main/").append(funcName).append(descriptor).append("\n");

        if (callee != null && "I".equals(callee.returnType)) {
            emitStore(dest, out, locals, nextLocal);
        }
    }

    private void emitUnary(String op, String operand, String dest, StringBuilder out,
            Map<String, Integer> locals, int[] labelCounter, int[] nextLocal) {
        switch (op) {
            case "neg":
                emitLoad(operand, out, locals, nextLocal);
                out.append("  ineg\n");
                emitStore(dest, out, locals, nextLocal);
                return;
            case "pos":
                emitLoad(operand, out, locals, nextLocal);
                emitStore(dest, out, locals, nextLocal);
                return;
            case "inc":
            case "dec":
                if (dest.equals(operand) && isIdentifier(operand)) {
                    int index = getLocalIndex(operand, locals, nextLocal);
                    int amount = "inc".equals(op) ? 1 : -1;
                    out.append("  iinc ").append(index).append(" ").append(amount).append("\n");
                } else {
                    emitLoad(operand, out, locals, nextLocal);
                    out.append("  iconst_1\n");
                    out.append("  ").append("inc".equals(op) ? "iadd" : "isub").append("\n");
                    emitStore(dest, out, locals, nextLocal);
                }
                return;
            case "not":
                emitLoad(operand, out, locals, nextLocal);
                emitNot(dest, out, locals, labelCounter, nextLocal);
                return;
            default:
                emitLoad(operand, out, locals, nextLocal);
                emitStore(dest, out, locals, nextLocal);
        }
    }

    private void emitBinary(String left, String op, String right, String dest, StringBuilder out,
            Map<String, Integer> locals, int[] labelCounter, int[] nextLocal) {
        switch (op) {
            case "add":
            case "sub":
            case "mul":
            case "div":
            case "mod":
            case "and":
            case "or":
                emitLoad(left, out, locals, nextLocal);
                emitLoad(right, out, locals, nextLocal);
                out.append("  ").append(binaryOpcode(op)).append("\n");
                emitStore(dest, out, locals, nextLocal);
                return;
            case "eq":
            case "neq":
            case "leq":
            case "lt":
            case "geq":
            case "gt":
                emitLoad(left, out, locals, nextLocal);
                emitLoad(right, out, locals, nextLocal);
                emitCompare(op, dest, out, locals, labelCounter, nextLocal);
                return;
            default:
                emitLoad(left, out, locals, nextLocal);
                emitLoad(right, out, locals, nextLocal);
                emitStore(dest, out, locals, nextLocal);
        }
    }

    private void emitCompare(String op, String dest, StringBuilder out, Map<String, Integer> locals,
            int[] labelCounter, int[] nextLocal) {
        // 비교 결과를 0/1 값으로 변환한다.
        String trueLabel = "Lcmp" + labelCounter[0]++;
        String endLabel = "Lcmp" + labelCounter[0]++;
        out.append("  ").append(compareOpcode(op)).append(" ").append(trueLabel).append("\n");
        out.append("  iconst_0\n");
        out.append("  goto ").append(endLabel).append("\n");
        out.append(trueLabel).append(":\n");
        out.append("  iconst_1\n");
        out.append(endLabel).append(":\n");
        emitStore(dest, out, locals, nextLocal);
    }

    private void emitNot(String dest, StringBuilder out, Map<String, Integer> locals,
            int[] labelCounter, int[] nextLocal) {
        // 논리 not: 0 -> 1, 0이 아닌 값 -> 0.
        String trueLabel = "Lnot" + labelCounter[0]++;
        String endLabel = "Lnot" + labelCounter[0]++;
        out.append("  ifeq ").append(trueLabel).append("\n");
        out.append("  iconst_0\n");
        out.append("  goto ").append(endLabel).append("\n");
        out.append(trueLabel).append(":\n");
        out.append("  iconst_1\n");
        out.append(endLabel).append(":\n");
        emitStore(dest, out, locals, nextLocal);
    }

    private String binaryOpcode(String op) {
        switch (op) {
            case "add":
                return "iadd";
            case "sub":
                return "isub";
            case "mul":
                return "imul";
            case "div":
                return "idiv";
            case "mod":
                return "irem";
            case "and":
                return "iand";
            case "or":
                return "ior";
            default:
                return "iadd";
        }
    }

    private String compareOpcode(String op) {
        switch (op) {
            case "eq":
                return "if_icmpeq";
            case "neq":
                return "if_icmpne";
            case "leq":
                return "if_icmple";
            case "lt":
                return "if_icmplt";
            case "geq":
                return "if_icmpge";
            case "gt":
                return "if_icmpgt";
            default:
                return "if_icmpeq";
        }
    }

    private void emitLoad(String operand, StringBuilder out, Map<String, Integer> locals, int[] nextLocal) {
        Integer literalValue = parseLiteral(operand);
        if (literalValue != null) {
            emitConst(literalValue, out);
            return;
        }
        // 나머지는 로컬 int 변수로 취급한다.
        int index = getLocalIndex(operand, locals, nextLocal);
        if (index >= 0 && index <= 3) {
            out.append("  iload_").append(index).append("\n");
        } else {
            out.append("  iload ").append(index).append("\n");
        }
    }

    private void emitStore(String name, StringBuilder out, Map<String, Integer> locals, int[] nextLocal) {
        int index = getLocalIndex(name, locals, nextLocal);
        if (index >= 0 && index <= 3) {
            out.append("  istore_").append(index).append("\n");
        } else {
            out.append("  istore ").append(index).append("\n");
        }
    }

    private void emitConst(int value, StringBuilder out) {
        if (value >= -1 && value <= 5) {
            switch (value) {
                case -1:
                    out.append("  iconst_m1\n");
                    return;
                case 0:
                    out.append("  iconst_0\n");
                    return;
                case 1:
                    out.append("  iconst_1\n");
                    return;
                case 2:
                    out.append("  iconst_2\n");
                    return;
                case 3:
                    out.append("  iconst_3\n");
                    return;
                case 4:
                    out.append("  iconst_4\n");
                    return;
                case 5:
                    out.append("  iconst_5\n");
                    return;
                default:
                    break;
            }
        }
        if (value >= -128 && value <= 127) {
            out.append("  bipush ").append(value).append("\n");
        } else if (value >= -32768 && value <= 32767) {
            out.append("  sipush ").append(value).append("\n");
        } else {
            out.append("  ldc ").append(value).append("\n");
        }
    }

    private Integer parseLiteral(String text) {
        if (text == null || text.isEmpty()) {
            return null;
        }
        try {
            if (text.startsWith("0x") || text.startsWith("0X")) {
                return Integer.parseInt(text.substring(2), 16);
            }
            if (text.startsWith("0") && text.length() > 1 && isDigits(text.substring(1))) {
                return Integer.parseInt(text.substring(1), 8);
            }
            if (text.startsWith("-") && isDigits(text.substring(1))) {
                return Integer.parseInt(text);
            }
            if (isDigits(text)) {
                return Integer.parseInt(text);
            }
        } catch (NumberFormatException ignored) {
        }
        return null;
    }

    private boolean isDigits(String text) {
        if (text == null || text.isEmpty()) {
            return false;
        }
        for (int i = 0; i < text.length(); i++) {
            if (!Character.isDigit(text.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private int getLocalIndex(String name, Map<String, Integer> locals, int[] nextLocal) {
        Integer existing = locals.get(name);
        if (existing != null) {
            return existing;
        }
        // 처음 보는 식별자에 새 로컬 슬롯을 배정한다.
        int assigned = nextLocal[0]++;
        locals.put(name, assigned);
        return assigned;
    }

    private boolean isIdentifier(String text) {
        if (text == null || text.isEmpty()) {
            return false;
        }
        char first = text.charAt(0);
        if (!Character.isLetter(first) && first != '_') {
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

    private List<FunctionIR> parseFunctions(String irText) {
        List<FunctionIR> functions = new ArrayList<>();
        String[] lines = irText.split("\\r?\\n");
        int idx = 0;
        while (idx < lines.length) {
            String line = lines[idx].trim();
            if (!line.startsWith("function ")) {
                idx++;
                continue;
            }
            String header = line;
            int parenStart = header.indexOf('(');
            int parenEnd = header.lastIndexOf(')');
            if (parenStart < 0 || parenEnd < parenStart) {
                idx++;
                continue;
            }
            String name = header.substring("function ".length(), parenStart).trim();
            String paramsStr = header.substring(parenStart + 1, parenEnd).trim();
            String returnType = header.substring(parenEnd + 1).trim();
            List<String> paramTypes = new ArrayList<>();
            List<String> paramNames = new ArrayList<>();
            if (!paramsStr.isEmpty()) {
                String[] tokens = paramsStr.split("\\s+");
                for (int i = 0; i + 1 < tokens.length; i += 2) {
                    paramTypes.add(tokens[i]);
                    paramNames.add(tokens[i + 1]);
                }
            }
            idx++;
            List<String> instructions = new ArrayList<>();
            while (idx < lines.length) {
                String bodyLine = lines[idx].trim();
                if (bodyLine.startsWith("end function")) {
                    idx++;
                    break;
                }
                if (!bodyLine.isEmpty()) {
                    instructions.add(bodyLine);
                }
                idx++;
            }
            functions.add(new FunctionIR(name, paramTypes, paramNames, returnType, instructions));
        }
        return functions;
    }
}
