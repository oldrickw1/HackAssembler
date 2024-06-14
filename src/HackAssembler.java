import java.io.IOException;
import java.nio.file.Path;

public class HackAssembler {
    public static void main(String[] args) {
//        if (args.length != 2 || !args[1].endsWith(".asm")) {
//            System.out.println("java HackAssembler xxx.asm");
//            System.exit(1);
//        }
//        System.out.println("Running the hack assembler!");
        Assembler a = new Assembler();
        a.assemble(Path.of("referenceFiles/assemblyCode/AddJustSimpleInstructions.asm"));
//        a.assemble(args[1]);

    }
}