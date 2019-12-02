package two;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum Opcode
{
	IADD(1,  (mem, a, b, loc) -> mem[loc] = mem[a] + mem[b]),
	IMUL(2,  (mem, a, b, loc) -> mem[loc] = mem[a] * mem[b]),
	IRET(99, (mem, a, b, loc) -> mem[0]);

	@FunctionalInterface
	private interface Instruction
	{
		int execute(int[] mem, int a, int b, int loc);
	}

	private final int code;
	private final Instruction ins;

	Opcode(int code, Instruction ins)
	{
		this.code = code;
		this.ins = ins;
	}

	int execute(int[] mem, int a, int b, int loc)
	{
		return ins.execute(mem, a, b, loc);
	}

	static Opcode forIns(int code) throws IllegalArgumentException
	{
		for (Opcode op : values())
		{
			if (op.code == code)
			{
				return op;
			}
		}
		throw new IllegalArgumentException("No opcode exists for instruction: " + code);
	}
}
