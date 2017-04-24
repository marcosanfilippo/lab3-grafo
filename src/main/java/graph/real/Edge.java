package graph.real;

public class Edge {
	
	private String dst;
	private String line;
	private int cost;

	public Edge(String dst) {
		this.dst = dst;
		this.cost = 1;
	}
	
	public Edge(String dst, String line) {
		this.dst = dst;
		this.line = line;
		this.cost = 1;
	}
	
	public Edge(String dst, String line, int cost) {
		this.dst = dst;
		this.cost = cost;
	}

	public String getDst() {
		return dst;
	}

	public void setDst(String dst) {
		this.dst = dst;
	}

	public int getCost() {
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
