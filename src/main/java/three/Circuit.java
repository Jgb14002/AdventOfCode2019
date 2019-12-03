package three;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class Circuit
{
	static final Point ORIGIN = new Point(0, 0);
	static final int VISITED = 0x01; //left one here in case we need to do some pathfinding algos in part II
	static final int VERTICAL = 0x02;
	static final int HORIZONTAL = 0x04;

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
	private Map<Point, Integer> gridState;

	private Circuit()
	{
		this.head = new Point(0, 0);
		this.gridState = new HashMap<>();
	}

	private void resetHead()
	{
		this.head = new Point(0, 0);
	}

	private void advanceDirection(int index, Direction dir, int distance)
	{
		for(int i = 0; i < distance; i++)
		{
			head.translate(dir.getDx(), dir.getDy());
			int bit = dir.getDx() == 0 ? VERTICAL : HORIZONTAL;
			gridState.compute(head.getSnapshot(), (p, v) -> v == null ? bit << index * 2 : (v << index * 2) | bit);
		}
	}

	Map<Point, Integer> getGridStates() { return this.gridState; }
}
