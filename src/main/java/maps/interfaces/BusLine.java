package maps.interfaces;

import java.util.List;

public interface BusLine {

	/*
	 * create table if not exists BusLine (
			  line varchar(20) not null,
			  description varchar(255),
			  primary key (line)
		);
	 */
	
	public String getLine();
	public void setLine(String line);
	
	public String getDescription();
	public void setDescription(String description);
	
	/***
	 * Return SORTED (by sequence)!
	 * @return
	 */
	public List<BusLineStop> getBusStops();
	public void removeBusLineStop(BusLineStop busLineStop) throws Exception;
	public void addBusLineStop(BusLineStop busLineStop) throws Exception;

}
