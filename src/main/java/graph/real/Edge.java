package graph.real;

public class Edge {
	
	private String dst; 
	private String line;	//line == null indicates a near stop (250 meters) belonging to another line
	private float cost;

	public Edge(String dst) {
		this.dst = dst;
	}
	
	public Edge(String dst, String line) {
		this.dst = dst;
		this.line = line;
		this.cost = 1;
	}
	
	public Edge(String dst, String line, float cost) {
		this.dst = dst;
		this.line = line;
		this.cost = cost;
	}
	
	public Edge(String dst, float cost) {
		this.dst = dst;
		this.cost = cost;
	}
	

	public String getDst() {
		return dst;
	}

	public void setDst(String dst) {
		this.dst = dst;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(int c) {
		this.cost = c;
	}
	
	public String getLine() {
		return line;
	}
	
	public void setLine(String line) {
		this.line = line;
	}

}
