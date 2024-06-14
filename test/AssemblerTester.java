import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AssemblerTester {
    private static Assembler assembler;

    @BeforeAll
    public static void setup() {
        assembler = new Assembler();
    }

    @Test
    public void assemblesCInstructions() {
        assertTrue(assembleAndCompare("OnlyCInstructions.asm","OnlyCInstructions.hack","OnlyCInstructions.hack"));
    }

    @Test
    public void assemblyToHackJustSimpleInstructions() {
        assertTrue(assembleAndCompare("AddJustSimpleInstructions.asm","AddJustSimpleInstructions.hack","Add.hack"));
    }

    @Test
    public void assemblyToHackWithoutSymbols() {
        assertTrue(assembleAndCompare("Add.asm","Add.hack","Add.hack"));
        assertTrue(assembleAndCompare("MaxL.asm","MaxL.hack", "Max.hack"));
        assertTrue(assembleAndCompare("PongL.asm","PongL.hack", "Pong.hack"));
        assertTrue(assembleAndCompare("RectL.asm","RectL.hack", "Rect.hack"));
    }

    @Test
    public void assemblyToHackWithSymbols() {
        assertTrue(assembleAndCompare("Max.asm","Max.hack", "Max.hack"));
        assertTrue(assembleAndCompare("Pong.asm","Pong.hack", "Pong.hack"));
        assertTrue(assembleAndCompare("Rect.asm","Rect.hack", "Rect.hack"));
    }


    private boolean assembleAndCompare(String asmFile, String myHackFile, String referenceHackFile) {
        try {
            assembler.assemble(Paths.get("referenceFiles/assemblyCode/" + asmFile));
            return filesAreEqual("assemblerOutput/" + myHackFile, "referenceFiles/hackCode/" + referenceHackFile);
        } catch (IOException e) {
            fail("One or more file(s) could probably not be opened. You most likely haven't assembled the file yet.");
            return false;
        }
    }

    private boolean filesAreEqual(String file1, String file2) throws IOException {
        return Files.mismatch(Paths.get(file1), Paths.get(file2)) == -1;
    }

}
