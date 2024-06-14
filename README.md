# Assembler Learning Project as part of Nand2Tetris(p6)
### Assembler that assembles symbolic machine instructions into binaries (.asm -> .hack). 

### Project: Nand2Tetris Chapter 6 - Hack Assembler.
Due: 15/06/24


Contract:

- Develop a program called HackAssembler that translates Hack assembly programs into executable Hack binary code.
- Source program is supplied in text file named xxx.asm.
- Generated code is written into file named xxx.hack.
- Assumption: xxx.asm is error free.

Usage: 
prompt> java HackAssembler xxx.asm
This should create (or override) an xxx.hack file that can be executed as-is on the Hack computer.

Proposed software architecture:
- Parser: unpacks each instruction into its underlying fields
- Code: translates each field into its corresponding binary value
- SymbolTable: manages the symbol table
- Main: initializes the I/O files and drives the process


Staged development (proposed approach):
- Develop a basic assembler that translates assembly programs without symbols.
- Develop an ability to handle symbols.
- Morph the basic assembler into an assembler that can translate any assembly program.


Plan:

#### testing
- Compile the supplied xxx.asm files with the provided assembler to xxx.hack files. 
- Use these xxx.hack files as reference to run your tests with. 
	- Copy reference files to a dir "reference_hack_files".
	- Create a test module.
	- Create test methods for each file. ACTUALLY, the output of the symbol version and non-symbol versions are exactly the same. 
	- Create stub program and make sure all tests fail.
	

#### development (pseudocode)
``` 
HackAssembler.java
// Just a wrapper so that it Assembler's functionality can be tested, and easily used as a CLI program at the same time. 
+ main() {
	// input validation
	Assembler a = new Assembler();
	a.assemble(path)
	

Assembler.java (path to xxx.asm file)
+ assemble -> {
	
	// create outputfile	
	// get st
	// get code
	
	Parser p =	new Parser(path);
	// first pass
	while p.hasMoreLines() {
		if p.currentInstructionIsLabel:
			add label + address to symboltable.
		p.advance();
	
	p.reset();
	
	// second pass
	while p.hasMoreLines()
		Instructiontype t =	p.instructionType();
		if (t == InstructionType.A_INSTRUCTION) {
		 	if (p.islabel())
				skip
			String s = p.symbol() 
			if !s.isNumber() {
				int address = parser.getLine()
				if (!symbolTable.contains(symbol)) {
					symbolTable.addEntry(symbol, address); // Adds the symbol with its address if not present.
				outputfile.write("0" + st.getAddress(p.symbol))
			}
		} else {
		 output.write("0" + s)
		if (t == InstructionType.C_INSTRUCTION) {
			outputfile.write("111" + code.comp(p.comp()) + code.dest(p.dest()) + code.jump(p.jump());		
		p.advance();
	

	
Parser.java
	+ constructor(Stream) // parser parses the instructions into a list (filtering any whitespace and comments). Does this in the constructor.
	+ hasMoreLines():boolean // just checks if lineNumber < lines.size()-1
	+ advance() // currentInstruction = lines.get(++line);
	+ instructionType():InstructionType
	+ symbol:String // either a value 
	+ dest:String
	+ comp:String
	+ jump:String


Code.java
	+comp(String comp):String // 7 bits as string
	+dest(String dest):String // 3 bits as string
	+jump(String jump):String // 4 bits as string

SymbolTable.java
	+addEntry(String symbol, int address)
	+contains(String symbol):boolean
	+getAddress(String symbol):int
	
InstructionType (enum)	
```







#### Log:
12-06 18:07
- Left on implementing dest, symbol and jump methods in parser. Need to brush up regex skills.
- Implement Code and SymbolTable
