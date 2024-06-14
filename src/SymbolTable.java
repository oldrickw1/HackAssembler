import java.util.HashMap;
import java.util.Map;

public class SymbolTable {
    private Map<String, Integer> symbolTable;

    public SymbolTable() {
        symbolTable = new HashMap<>();
        populateMap();
    }


    public void addEntry(String symbol, int i) {
        symbolTable.put(symbol, i);
    }

    public boolean contains(String symbol) {
        return symbolTable.containsKey(symbol);
    }

    public int getAddress(String symbol) {
        return symbolTable.get(symbol);
    }

    private void populateMap() {
        symbolTable.putAll(Map.of(
                "SP", 0,
                "LCL", 1,
                "ARG", 2,
                "THIS", 3,
                "THAT", 4,
                "SCREEN", 16384,
                "KBD", 24576
        ));
        for (int i = 0; i <= 15; i++) {
            symbolTable.put("R" + i, i);
        }
    };
}
