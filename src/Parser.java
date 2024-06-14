import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Parser {
    private List<String> instructions;
    private int lineNumber = 0;
    private String currentInstruction;

    private final String REGEX = "^(?:(?<dest>\\w{1,4})=)?(?<comp>[A-Z!\\d\\-|&\\+]{1,3})(;(?<jump>\\w{3,4}))?";

    public Parser(Stream<String> input) {
        instructions = input.map(String::trim).filter((s -> !s.startsWith("/") && !s.isEmpty())).collect(Collectors.toList());
        lineNumber = 0;
        currentInstruction = instructions.getFirst();
    }

    public void advance() {
        currentInstruction = instructions.get(lineNumber++);
    }

    public boolean hasMoreLines() {
        return lineNumber < instructions.size();
    }

    public InstructionType instructionType() {
        return currentInstruction.startsWith("@") ? InstructionType.A_INSTRUCTION : (currentInstruction.startsWith("(") ? InstructionType.L_INSTRUCTION : InstructionType.C_INSTRUCTION);
    }

    public String symbol() {
        return currentInstruction.charAt(0) == '@' ? currentInstruction.substring(1) : currentInstruction.substring(1, currentInstruction.length()-1);
    }

    public void reset() {
        lineNumber = 0;
        currentInstruction = instructions.get(0);
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public String dest() {
        return extractGroup("dest");
    }

    public String comp() {
        return extractGroup("comp");
    }

    public String jump() {
        return extractGroup("jump");
    }

    private String extractGroup(String group) {
        Pattern compiledPattern = Pattern.compile(REGEX);
        Matcher matcher = compiledPattern.matcher(currentInstruction);

        if (matcher.find()) {
            try {
                String result = matcher.group(group);
                return result != null ? result : "null";
            } catch (IllegalArgumentException e) {
                return "null";
            }
        }
        return "null";
    }
}
