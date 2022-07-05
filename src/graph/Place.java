package graph;

public class Place {

	private int x;
	private int y;
	private String value;

	public Place(int x, int y, int bound) {
		this.x = x;
		this.y = y;
		this.value = "."; // set first value "."
		if (x < 0 || x > bound - 1 || y < 0 || y > bound - 1)
			throw new IllegalArgumentException();
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	// check if objects data is equal
	@Override
	public boolean equals(Object o) {
		if (this.x == ((Place) o).getX() && this.y == ((Place) o).getY())
			return true;
		else
			return false;
	}

	@Override
	public int hashCode() {
		return this.getX() * 2 + this.getY() * 3;
	}

	public void setValue(String s) {
		this.value = s;
	}

	public String getValue() {
		return this.value;
	}
}
