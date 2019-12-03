package one;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class FuelProblem
{
	/**
	 * Using Math.floorDiv to clarify intention. We could rely on integer division.
	 */
	public static void main(String[] args)
	{
		List<Integer> data = getModuleMassData();
		int moduleFuel = data.stream()
			.reduce(0, (t, i) -> t + Math.floorDiv(i, 3) - 2);

		log.info("Fuel required not including mass of fuel: {}", moduleFuel);

		int totalFuel = data.stream()
			.map(i -> Math.floorDiv(i, 3) - 2)
			.reduce(0, (t, i) ->
			{
				do
				{
					t += i;
				}
				while ((i = Math.floorDiv(i, 3) - 2) > 0);
				return t;
			});

		log.info("Fuel required including mass of fuel: {}", totalFuel);
	}

	private static List<Integer> getModuleMassData()
	{
		List<Integer> data = new ArrayList<>();
		try (Scanner scanner = new Scanner(FuelProblem.class.getResourceAsStream("/one/module_mass_data.txt")))
		{
			while (scanner.hasNextInt())
			{
				data.add(scanner.nextInt());
			}
		}
		return data;
	}
}
