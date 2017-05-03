package maps.interfaces;


public interface BusLineStop {
	
	/*
	create table if not exists BusLineStop (
			  stopId varchar(20) not null,
			  lineId varchar(20) not null,
			  seqenceNumber smallint not null,
			  primary key(stopId, lineId),
			  foreign key (stopId) references BusStop(id),
			  foreign key (lineId) references BusLine(line)
	);
	 */
	
	public BusStop getBusStop();
	public void setBusStop(BusStop bs ) throws Exception;
	
	public BusLine getBusLine();
	public void setBusLine(BusLine bl) throws Exception;
	
	public Integer getSequenceNumber();
	public void setSequenceNumber(Integer seq);
}
