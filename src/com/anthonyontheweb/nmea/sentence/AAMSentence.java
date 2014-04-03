package com.anthonyontheweb.nmea.sentence;

/**
 * AAM sentence parses waypoint arrival alarms.
 * 
 * @author Anthony
 *
 */
public class AAMSentence extends NMEASentence
{
	/**
	 * The waypoint arrival circle has been entered.
	 */
	private boolean entered;
	
	/**
	 * Perpendicular passed at waypoint.
	 */
	private boolean passed;
	
	/**
	 * Arrival circle radius.
	 */
	private double radius;
	
	/**
	 * The ID of the waypoint.
	 */
	private String waypoint;
	
	/**
	 * Initiates the waypoint arrival alarm.
	 * 
	 * @param sentence
	 * @throws Exception 
	 */
	public AAMSentence( String sentence ) throws Exception
	{
		super(sentence);

		//waypoint entered?
		entered = getBoolean( getWord( 0 ) );

		//passed perpendicular?
		passed = getBoolean( getWord( 1 ) );
		
		//get the radius of circle and convert to nautical miles
		radius = Double.parseDouble( getWord( 2 ) );
		
		//convert kilos to nauts
		if( getWord( 3 ).equalsIgnoreCase( "K" ) )
			radius = convertDist( radius, DistType.KILO, DistType.NAUTS );
		//convert miles to nauts
		else if( getWord( 3 ).equalsIgnoreCase( "M" ) )
			convertDist( radius, DistType.MILES, DistType.NAUTS );
		
		waypoint = getWord( 4 );
		
	}
	
	/**
	 * Returns true if the circle has been entered, otherwise false.
	 * 
	 * @return true if the circle has been entered
	 */
	public boolean enteredCircle()
	{
		return entered;
	}
	
	/**
	 * Returns true if the perpendicular has been passed, otherwise false.
	 * 
	 * @return true if the perpendicular has been passed
	 */
	public boolean passedPerpendicular()
	{
		return passed;
	}
	
	/**
	 * Gets the radius of the circle.
	 * 
	 * @param the unit of measure to return
	 * 
	 * @return the radius of the circle
	 */
	public double getRadius( DistType t )
	{
		if( t == DistType.MILES )
			return convertDist( radius, DistType.NAUTS, DistType.MILES );
		else if( t == DistType.KILO )
			return convertDist( radius, DistType.NAUTS, DistType.KILO );
		
		return radius;
	}
	
	/**
	 * Returns the waypoint ID.
	 * 
	 * @return the waypoint ID
	 */
	public String getWaypointID()
	{
		return waypoint;
	}

}
