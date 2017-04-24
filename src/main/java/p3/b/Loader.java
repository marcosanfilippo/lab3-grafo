package p3.b;

import java.sql.*;
import java.util.*;

import graph.real.*;

public class Loader {
    
    public static Graph getGraphDB (Graph g) {
    	return parseFromDB(g);
    }
    
    private static Graph parseFromDB(Graph g){
    	List<Node> nodeList = new ArrayList<Node>();
    	String connectionUrl = "jdbc:postgresql://192.168.99.100:5432/trasporti";
    	try {
    		Class.forName("org.postgresql.Driver");
    		Connection conn = DriverManager.getConnection(connectionUrl,"postgres","ai-user-password"); 
    		Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    		 
    		ResultSet nodeRS = stmt.executeQuery("SELECT id FROM BusStop ORDER BY id");
    		while(nodeRS.next())
        		nodeList.add(new Node(nodeRS.getString(1))); // starts from 1, not 0.
    		
    		//ResultSet stopRS = stmt.executeQuery("SELECT stopid, lineid, sequencenumber "
    			//+"FROM BusLineStop ORDER BY stopid, lineid, sequencenumber");
    		ResultSet sequenceRS = stmt.executeQuery("SELECT lineid, sequencenumber, stopid "
    			+"FROM BusLineStop ORDER BY lineid, sequencenumber, stopid");
    
    		while(sequenceRS.next()){
    			for(Node n : nodeList){
					if(n.getId().equals(sequenceRS.getString(3))){
						String line = sequenceRS.getString(1);
						// la sua adiacenza è il nodo che segue sulla stessa linea, con sequence number più grande (sono già ordinati per sequenceNumber)
						if(sequenceRS.next()){
							String nextLine = sequenceRS.getString(1);
							if(line.compareToIgnoreCase(nextLine)==0){
								//can add its next, because the bus line is the same
								System.out.println("Linea: " + line + ", " + n.getId() + " -> " + sequenceRS.getString(3) + ", ");
								n.getAdjList().add(new Edge(sequenceRS.getString(3), sequenceRS.getString(1)));
							}
							if(!sequenceRS.isLast())
								sequenceRS.previous(); //return to the previous line to process its node!
							break;
						}
					}
				}
    		}
    		g = new Graph(nodeList);
    		
    	} catch (Exception e) {    		
    		e.printStackTrace();
    		System.exit(1);
    	}
    	
    	return g;
    }
}
