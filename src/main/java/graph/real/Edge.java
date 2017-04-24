package graph.real;

public class Edge {
	
	private String dts;
	private String line;
	private int cost;

	public Edge(String dts) {
		this.dts = dts;
		this.cost = 1;
	}
	
	public Edge(String dts, String line) {
		this.dts = dts;
		this.line = line;
		this.cost = 1;
	}
	
	public Edge(String dts, String line, int cost) {
		this.dts = dts;
		this.cost = cost;
	}

	public String getDts() {
		return dts;
	}

	public void setDts(String dts) {
		this.dts = dts;
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
