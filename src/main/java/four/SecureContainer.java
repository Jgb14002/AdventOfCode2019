package four;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SecureContainer
{
	private static final int MAX = 765869;
	private static final int MIN = 234208;

	public static void main(String[] args)
	{
		int count = 0;

		for(int i = MIN; i <= MAX; i++)
		{
			if(isValid(i))  count++;
		}

		log.info("{} valid numbers in the given range.", count);

	}

	private static boolean isValid(int num)
	{
		int[] digits = digitize(num);

		int tmp = digits[0];
		for(int i = 1; i < digits.length; i++)
		{
			if(digits[i] < (tmp & 0xF)) return false;
			int cur = digits[i];
			if(cur == (tmp & 0xF)) tmp |= 0x1F;
			tmp = (tmp & ~0xF) | cur;
		}
		return (tmp >> 4 & 1) == 1;
	}

	private static int[] digitize(int num)
	{
		int[] digits = new int[6];
		for(int i = 0; i < digits.length; i++)
		{
			digits[digits.length - i - 1] = (int) ((num / Math.pow(10, i)) % 10);
		}
		return digits;
	}
}
