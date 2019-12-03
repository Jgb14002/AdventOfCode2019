package three;

enum Direction
{
	UP('U', 0, 1),
	DOWN('D', 0, -1),
	LEFT('L', -1, 0),
	RIGHT('R', 1, 0);

	private final char letter;
	private final int dx;
	private final int dy;

	Direction(char letter, int dx, int dy)
	{
		this.letter = letter;
		this.dx = dx;
		this.dy = dy;
	}

	int getDx() { return dx; }
	int getDy() { return dy; }

	static Direction forLetter(char letter)
	{
		for(Direction dir : values())
		{
			if(dir.letter == letter) return dir;
		}
		throw new IllegalArgumentException("No direction exists for letter: " + letter);
	}
}
