package three;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class CrossedWires
{
	public static void main(String[] args)
	{
		try
		{
			/**
			 * Part I
			 */
			Circuit circuit = loadCircuit();
			Map<Point, Integer> grid = circuit.getGridStates();


			int distance = grid.entrySet().stream()
				.filter(entry -> (entry.getValue() & 0xF) == 6 || (entry.getValue() & 0xF) == 9)
				.mapToInt(entry -> entry.getKey().distanceTo(Circuit.ORIGIN))
				.min().orElse(-1);

			log.info("Closest point of intersection is {} units from the origin", distance);

			/**
			 * Part II
			 */
			int steps = grid.values().stream()
				.filter(v -> (v & 0xF) == 6 || (v & 0xF) == 9)
				.mapToInt(v -> v >> 4)
				.min().orElse(-1);

			log.info("Minimum steps: {}", steps);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private static Circuit loadCircuit() throws IOException
	{
		try(BufferedReader br = new BufferedReader(new InputStreamReader(CrossedWires.class.getResourceAsStream("/three/circuit_data.txt"))))
		{
			List<String> wires = new ArrayList<>();
			String line;
			while((line = br.readLine()) != null)
			{
				wires.add(line);
			}
			return Circuit.create(wires.toArray(new String[0]));
		}
	}
}
