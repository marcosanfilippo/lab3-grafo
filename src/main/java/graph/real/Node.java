package graph.real;

import java.util.ArrayList;
import java.util.List;

public class Node {
	
	private String id;
	private List<Edge> adjList;
	
	public Node(){
		adjList = new ArrayList<Edge>();
	}
	
	public Node(String id){
		this.id = id;
		adjList = new ArrayList<Edge>();
	}
	
	public Node(ArrayList<Edge> al){
		adjList = al;
	}
	
	public Node(String id, ArrayList<Edge> al){
		this.id = id;
		adjList = al;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Edge> getAdjList() {
		return adjList;
	}

	public void setAdjList(List<Edge> adjList) {
		this.adjList = adjList;
	}
		
}
