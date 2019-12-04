package three;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class Circuit
{
	/**
	 * To preserve the orientation of wires at given coordinates.
	 * 2 wires could share coordinates but they would not "intersect" if they were parallel
	 */
	static final int VERTICAL = 0x01;
	static final int HORIZONTAL = 0x02;

	static final Point ORIGIN = new Point(0, 0);

	static Circuit create(String... wires)
	{
		Circuit circuit = new Circuit();
		for(int i = 0; i < wires.length; i++)
		{
			String wire = wires[i];
			try (Scanner scanner = new Scanner(wire))
			{
				scanner.useDelimiter(",");
				while (scanner.hasNext())
				{
					String vector = scanner.next();
					circuit.advanceDirection(i,
						Direction.forLetter(vector.charAt(0)),
						Integer.parseInt(vector.substring(1))
					);
				}
			}
			circuit.resetHead();
		}
		return circuit;
	}

	private Point head;
	private int steps;
	private Map<Point, Integer> gridState;

	private Circuit()
	{
		this.head = new Point(0, 0);
		this.gridState = new HashMap<>();
	}

	private void resetHead()
	{
		this.head = new Point(0, 0);
		this.steps = 0;
	}

	private void advanceDirection(int index, Direction dir, int distance)
	{
		for(int i = 0; i < distance; i++)
		{
			head.translate(dir.getDx(), dir.getDy());
			int bit = dir.getDx() == 0 ? VERTICAL : HORIZONTAL;
			gridState.compute(head.getSnapshot(),
				(p, v) -> v == null
					? (++steps << 4) | (bit << index * 2)
					: (++steps << 4) + v | (bit << index * 2));
		}
	}

	Map<Point, Integer> getGridStates() { return this.gridState; }
}
