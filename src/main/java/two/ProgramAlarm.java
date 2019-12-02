package two;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProgramAlarm
{
	/**
	 * Had to settle for brute forcing part II.
	 */
	public static void main(String[] args)
	{
		int[] partOne = runProgram(12, 2);
		log.info("The value at memory addr 0 is: {}", partOne[0]);

		for(int n = 0; n < 100; n++)
		{
			for(int v = 0; v < 100; v++)
			{
				if(runProgram(n, v)[0] == 19690720)
				{
					log.info("100n + v = {} | n = {}, v = {}", 100 * n + v, n, v);
					return;
				}
			}
		}
	}

	private static int[] runProgram(int noun, int verb)
	{
		int[] ins = getProgramIns();
		ins[1] = noun;
		ins[2] = verb;

		for(int i = 0; i < ins.length; i++)
		{
			Opcode op = Opcode.forIns(ins[i]);
			if(op == Opcode.IRET)
			{
				return ins;
			}
			op.execute(ins, ins[++i], ins[++i], ins[++i]);
		}
		return ins;
	}

	private static int[] getProgramIns()
	{
		List<Integer> ins = new ArrayList<>();
		try(Scanner scanner = new Scanner(ProgramAlarm.class.getResourceAsStream("/two/program_data.txt")))
		{
			scanner.useDelimiter("[,\\n]");
			while(scanner.hasNextInt())
			{
				ins.add(scanner.nextInt());
			}
		}
		return ins.stream().mapToInt(Integer::intValue).toArray();
	}
}
