package maps.interfaces;

import java.util.Map;

public interface BusLineService {

	public Map<String, BusLine> getAll();
	public BusLine getBusLine(String id);	
}
