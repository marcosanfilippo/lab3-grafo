package maps.route;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

/**
 * Classe che rappresenta i percorsi a costo minimo da salvare all'interno di MongoDB.
 * */
public class MinPath {

	/**
	 * Id della fermata (BusStop) di partenza dell'intero percorso
	 * */
	private String idSource;
	
	/**
	 * Id della fermata (BusStop) di arrivo dell'intero percorso
	 * */
	private String idDestination;
	
	/**
	 * Lista di tratte che compongono il percorso
	 * */
	private List<MinPathEdge> edges;
	
	/**
	 * Costo totale dell'intero percorso.
	 * La somma dei costi delle singole tratte deve essere uguale 
	 * a questo valore
	 * */
	private int totalCost;
	
	
	public String getIdSource() {
		return idSource;
	}
	public void setIdSource(String idSource) {
		this.idSource = idSource;
	}
	public String getIdDestination() {
		return idDestination;
	}
	public void setIdDestination(String idDestination) {
		this.idDestination = idDestination;
	}
	public List<MinPathEdge> getEdges() {
		return edges;
	}
	public void setEdges(List<MinPathEdge> edges) {
		this.edges = edges;
	}
	public int getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(int totalCost) {
		this.totalCost = totalCost;
	}
	
	public Document getMongoDbDocument()
	{
		Document d = new Document();
		
		d.append("sourceID", this.idSource);
		d.append("destinationID", this.idDestination);
		
		ArrayList<Document> edges_doc = new ArrayList<Document>();
		for(MinPathEdge mpe : edges)
		{
			Document mped = new Document();
			mped.append("busStopID", mpe.busStopId);
			mped.append("line", mpe.line);
			mped.append("cost", mpe.cost);
			edges_doc.add(mped);
		}
		d.append("path", edges_doc);
		d.append("cost", this.totalCost);
		
		return d;
	}
}
