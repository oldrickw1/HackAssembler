import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Assembler {
    public void assemble(Path path) {
        SymbolTable st = new SymbolTable();
        Code code = new Code();
        Path outputPath = getOutputPath(path);
        try(
            BufferedWriter bw = Files.newBufferedWriter(outputPath);
            BufferedReader br = Files.newBufferedReader(path)
        ) {
            Parser p =	new Parser(br.lines());

            // first pass
            int line = 0;
            while (p.hasMoreLines()) {
                p.advance();
                if (p.instructionType() == InstructionType.L_INSTRUCTION) {
                    st.addEntry(p.symbol(), line );
                } else {
                    line++;
                }
            }

            // second pass
            p.reset();
            int varCount = 16;
            while (p.hasMoreLines()) {
                p.advance();
                InstructionType t =	p.instructionType();
                if (t == InstructionType.L_INSTRUCTION) {
                    continue;
                }
                else if (t == InstructionType.A_INSTRUCTION) {
                    String s = p.symbol();
                    if (!Character.isDigit(s.charAt(0))) { // if it is symbolic
                        if (!st.contains(s)) {
                            st.addEntry(s, varCount++); // Adds the symbol with its address if not present.
                        }
                        bw.write(code.intToBin(st.getAddress(s)));
                    } else {
                        bw.write(code.intToBin(Integer.parseInt(s)));
                    }
                }
                else if (t == InstructionType.C_INSTRUCTION) {
                    String cInstruction = makeCInstruction(p, code);
                    bw.write(cInstruction);
                }
                if (p.hasMoreLines()) {
                    bw.write("\n");
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String makeCInstruction(Parser p, Code code) {
        String opCode = "111";

        String compCode = p.comp();
        String destCode = p.dest();
        String jumpCode = p.jump();

        String compBits = code.comp(compCode);
        String destBits = code.dest(destCode);
        String jumpBits = code.jump(jumpCode);

        return opCode + compBits + destBits + jumpBits;
    }

    private Path getOutputPath(Path path) {
        return Path.of("/home/oldrick/IdeaProjects/assembler3/assemblerOutput/" + path.getFileName().toString().replace(".asm", ".hack"));
    }

}
