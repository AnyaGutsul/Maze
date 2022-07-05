package graph;

import java.util.Collection;
import java.util.HashSet;

public class Maze implements GraphInterface<Place> {

	private int size;
	private Place[][] maze; // matrix for the maze
	private Place start;
	private Place end;

	public Maze(int size, int startx, int starty, int endx, int endy) { // Initialize the maze

		int i = 0, j = 0;
		this.size = size;
		this.maze = new Place[size][size];

		for (i = 0; i < size; i++) {// Initialize the maze with new places
			for (j = 0; j < size; j++)
				maze[i][j] = new Place(i, j, size);
		}
		start = new Place(startx, starty, size); // Initialize start place with "S" value and put it into the maze
		start.setValue("S");
		maze[startx][starty] = start;
		end = new Place(endx, endy, size); // Initialize end place with "E" value and put it into the maze
		end.setValue("E");
		maze[endx][endy] = end;
	}

	public boolean addWall(int x, int y) { // adding a wall in (x,y) place

		Place wall = new Place(x, y, this.size); // Initialize wall and set value "@"
		wall.setValue("@");

		// check if the new wall is not at the start or at the end and that there is not
		// a wall yet
		if (wall.equals(start) || wall.equals(end) || maze[x][y].getValue().equals("@"))
			return false;
		else {
			maze[x][y] = wall; // set a wall at this place
			return true;
		}
	}

	// string version of the graph
	public String toString() {
		int i, j;
		StringBuilder b = new StringBuilder();
		for (i = 0; i < this.size; i++) {
			for (j = 0; j < this.size; j++)
				b.append(maze[i][j].getValue());
			b.append("\n");
		}
		return b.toString();
	}

	// check if the graph is solvable
	public boolean isSolvable() {

		int i, j;
		Graph<Place> graph = new Graph<>();

		for (i = 0; i < this.size; i++) // add all vertices to the graph except the walls
			for (j = 0; j < this.size; j++)
				if (!this.maze[i][j].getValue().equals("@")) // if it is not wall add to the graph
					try {
						graph.addVertex(maze[i][j]);
					} catch (GraphException e) {
						e.printStackTrace();
					}

		for (i = 0; i < this.size; i++)
			for (j = 0; j < this.size; j++) { // add edges for all vertices that are not walls
				try {
					if (!this.maze[i][j].getValue().equals("@")) {
						if ((i - 1) >= 0) // for up
							if (!this.maze[i - 1][j].getValue().equals("@")
									&& !graph.hasEdge(maze[i][j], maze[i - 1][j]))
								graph.addEdge(maze[i][j], maze[i - 1][j]);

						if ((i + 1) < size) // for down
							if (!this.maze[i + 1][j].getValue().equals("@")
									&& !graph.hasEdge(maze[i][j], maze[i + 1][j]))
								graph.addEdge(maze[i][j], maze[i + 1][j]);

						if ((j - 1) >= 0) // for left
							if (!this.maze[i][j - 1].getValue().equals("@")
									&& !graph.hasEdge(maze[i][j], maze[i][j - 1]))
								graph.addEdge(maze[i][j], maze[i][j - 1]);

						if ((j + 1) < size) // for right
							if (!this.maze[i][j + 1].getValue().equals("@")
									&& !graph.hasEdge(maze[i][j], maze[i][j + 1]))
								graph.addEdge(maze[i][j], maze[i][j + 1]);
					}
				} catch (GraphException e) {
					e.printStackTrace();
				}
			}
		try {
			return graph.connected(start, end);
		} catch (GraphException e) {
			e.printStackTrace();
		}
		return false;
	}

	// return all neighbors of the place that was given
	@Override
	public Collection<Place> neighbours(Place place) {

		// if the place we have got is a wall, it has not neighbours
		if (place.getValue().equals("@"))
			return null;

		Collection<Place> neighbours = new HashSet<Place>();

		int x = place.getX();
		int y = place.getY();

		if ((x - 1) >= 0) // for up
			if (!this.maze[x - 1][y].getValue().equals("@"))
				neighbours.add(maze[x - 1][y]);

		if ((x + 1) < size) // for down
			if (!this.maze[x + 1][y].getValue().equals("@"))
				neighbours.add(maze[x + 1][y]);

		if ((y - 1) >= 0) // for left
			if (!this.maze[x][y - 1].getValue().equals("@"))
				neighbours.add(maze[x][y - 1]);

		if ((y + 1) < size) // for right
			if (!this.maze[x][y + 1].getValue().equals("@"))
				neighbours.add(maze[x][y + 1]);

		return neighbours;
	}
}
