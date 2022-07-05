package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Graph<V> {
	private Set<V> vertices;
	private Map<V, Set<V>> edges;
	private Set<V> signedVertices; // set of all of the vertices that were visited already

	public Graph() {
		this.vertices = new HashSet<V>();
		this.edges = new HashMap<V, Set<V>>();
		this.signedVertices = new HashSet<V>();
	}

	// add vertex and its edges empty set
	public void addVertex(V v) throws GraphException {
		if (vertices.contains(v))
			throw new GraphException("Exists");

		vertices.add(v);
		edges.put(v, new HashSet<V>());
	}

	public void addEdge(V v1, V v2) throws GraphException {
		if ((!vertices.contains(v1)) || (!vertices.contains(v2)))
			throw new GraphException("Vertex does not exist");

		if (edges.get(v1).contains(v2))
			throw new GraphException("Edge already exists");

		edges.get(v1).add(v2);
		edges.get(v2).add(v1);
	}

	public boolean hasEdge(V v1, V v2) {
		return edges.get(v1).contains(v2);
	}

	// check if v1 and v2 are connected
	public boolean connected(V v1, V v2) throws GraphException {
		if ((!vertices.contains(v1)) || (!vertices.contains(v2)))
			throw new GraphException("does not exist");

		// check if vertices has edges
		else if (!edges.containsKey(v1) || !edges.containsKey(v2))
			return false;
		else {
			signedVertices.clear();
			return dfs(v1, v2);
		}
	}

	// recursive function for checking if v1 and v2 have connection
	private boolean dfs(V v1, V v2) {

		Iterator<V> itr;
		V temp;

		if (signedVertices.contains(v1)) // if we try to visit a specific vertex twice there are no connection between
											// v1 and v2
			return false;
		else
			signedVertices.add(v1); // sign the vertex as visited

		if (v1.equals(v2)) // if v1 equals v2 there is a connection
			return true;
		else if (edges.get(v1).contains(v2)) // if v1 is a neighbour of v2 there is a connection
			return true;
		else {
			itr = edges.get(v1).iterator(); // for all neighbours of v1
			while (itr.hasNext()) {
				temp = itr.next();
				if (!signedVertices.contains(temp))
					if (dfs(temp, v2)) // recursive function for all vertices to improve the connection
						return true;
			}
		}
		return false;
	}
}
