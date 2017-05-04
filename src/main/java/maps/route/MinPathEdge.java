package maps.route;


public class MinPathEdge {

	//TODO
	public String busStopId;
	public String line;
	public int cost;
	
	public MinPathEdge(String busStopId, String line,int cost) {
		this.busStopId = busStopId;
		this.line = line;
		this.cost = cost;
	}
}
