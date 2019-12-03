package three;

import lombok.Data;

@Data
class Point
{
	private int x;
	private int y;

	Point(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	void translate(int dx, int dy)
	{
		this.x += dx;
		this.y += dy;
	}

	/**
	 * @param other the other point
	 * @return the Manhattan distance between the two points.
	 */
	int distanceTo(Point other)
	{
		return Math.abs(other.x - x) + Math.abs(other.y - y);
	}

	Point getSnapshot()
	{
		return new Point(x, y);
	}
}
