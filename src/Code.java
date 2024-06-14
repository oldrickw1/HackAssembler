import java.util.HashMap;
import java.util.Map;

public class Code {

    private Map<String, String> compMap;
    private Map<String, String> destMap;
    private Map<String, String> jumpMap;

    public Code() {
        initMaps();
    }

    public String intToBin(int integerRepr) {
        return String.format("%16s", Integer.toBinaryString(integerRepr)).replace(" ", "0");
    }

    public String comp(String comp) {
        return compMap.get(comp);
    }

    public String dest(String dest) {
        return destMap.get(dest);
    }

    public String jump(String jump) {
        return jumpMap.get(jump);
    }

    private void initMaps() {
        compMap = new HashMap<>();
        destMap = new HashMap<>();
        jumpMap = new HashMap<>();

        compMap.put("0", "0101010");
        compMap.put("1", "0111111");
        compMap.put("-1", "0111010");
        compMap.put("D", "0001100");
        compMap.put("A", "0110000");
        compMap.put("M", "1110000");
        compMap.put("!D", "0001101");
        compMap.put("!A", "0110001");
        compMap.put("!M", "1110001");
        compMap.put("-D", "0001111");
        compMap.put("-A", "0110011");
        compMap.put("-M", "1110011");
        compMap.put("D+1", "0011111");
        compMap.put("A+1", "0110111");
        compMap.put("M+1", "1110111");
        compMap.put("D-1", "0001110");
        compMap.put("A-1", "0110010");
        compMap.put("M-1", "1110010");
        compMap.put("D+A", "0000010");
        compMap.put("D+M", "1000010");
        compMap.put("D-A", "0010011");
        compMap.put("D-M", "1010011");
        compMap.put("A-D", "0000111");
        compMap.put("M-D", "1000111");
        compMap.put("D&A", "0000000");
        compMap.put("D&M", "1000000");
        compMap.put("D|A", "0010101");
        compMap.put("D|M", "1010101");
        destMap.put("null", "000");
        destMap.put("M", "001");
        destMap.put("D", "010");
        destMap.put("DM", "011");
        destMap.put("MD", "011");
        destMap.put("A", "100");
        destMap.put("AM", "101");
        destMap.put("AD", "110");
        destMap.put("ADM", "111");
        jumpMap.put("null", "000");
        jumpMap.put("JGT", "001");
        jumpMap.put("JEQ", "010");
        jumpMap.put("JGE", "011");
        jumpMap.put("JLT", "100");
        jumpMap.put("JNE", "101");
        jumpMap.put("JLE", "110");
        jumpMap.put("JMP", "111");
    }
}
