package graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class ConnectionChecker<V> {
	private GraphInterface<V> g;

	public ConnectionChecker(GraphInterface<V> g) {
		this.g = g;
	}

	// check if there is a connection between v1 and v2
	public boolean check(V v1, V v2) {

		if (v1.equals(v2))
			return true;

		Set<V> signedV = new HashSet<V>(); // signedV contains vertices that have been already visited
		LinkedList<V> queue = new LinkedList<V>(); // queue contains vertices to be visited

		queue.add(v1);

		while (!queue.isEmpty()) {
			V current = queue.poll();
			for (V vertex : g.neighbours(current)) { // checking each of the neighbors if it equals v2
				if (signedV.contains(vertex))
					continue;
				if (vertex.equals(v2)) {
					return true;
				}
				queue.add(vertex);
			}
			signedV.add(current);
		}
		return false;
	}
}
