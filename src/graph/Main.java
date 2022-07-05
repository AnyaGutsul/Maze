package graph;

public class Main {

	public static void main(String[] args) {
		boolean solvable;
		
		Maze m = new Maze(4, 0, 0, 3, 3);
		m.addWall(1, 1);
		m.addWall(3, 1);
		m.addWall(0, 1);
		m.addWall(2, 3);
		m.addWall(2, 3);
		m.addWall(1, 3);
		//m.addWall(2, 2); //by adding 2,2 wall will turn the maze to not solvable

		System.out.println(m);

		ConnectionChecker<Place> cc = new ConnectionChecker<>(m);
		solvable = cc.check(new Place(0, 0, 4), new Place(3, 3, 4));
		if(solvable) {
			System.out.println("The maze is solvable.");
		}else 
			System.out.println("The maze is not solvable.");
	}
}
