package com.anthonyontheweb.nmea.sentence;


/**
 * The NMEA class delegates NMEA sentences
 * and handles events.
 * 
 * @author Anthony Massie
 *
 */
public class NMEASentence
{
	/**
	 * Type of miles
	 */
	public static enum DistType { KILO, NAUTS, MILES };
	
	/**
	 * The raw unprocessed sentence. 
	 */
	private String rawSentence;
	
	/**
	 * The pieces of the string.
	 */
	private String [] pieces;

	/**
	 * The 5 letter code.
	 */
	private String identifier;
	
	/**
	 * The checksum of the sentence.
	 */
	private String checksum;
	
	/**
	 *	The constructor processes the
	 *  NMEA string.
	 * @throws Exception 
	 */
	public NMEASentence( String sentence ) throws Exception
	{
		rawSentence = sentence;
		identifier = sentence.substring( 1, 6 ).replaceAll( "-", "" );
		
		//remove identifier
		sentence = sentence.substring( 7, sentence.length() );

		//get checksum, remove from sentence
		if( sentence.charAt( sentence.length() - 3 ) == '*' )
		{
			checksum = sentence.substring( sentence.length() - 2, sentence.length() );
			sentence = sentence.substring( 0, sentence.length() - 3 );
			
			//make sure checksum is correct
			if( !checksum( rawSentence.substring( 1, rawSentence.length() - 3), checksum ) )
				throw new Exception( "The checksum provided does not match the checksum calculated." );

		}

		//split into words
		pieces = sentence.split( "," );
	}
	
	/**
	 * Checks if the checksum is valid.
	 */
	boolean checksum( String sentence, String checksum )
	{
		int cs = 0;
		
		for( int i = 0; i < sentence.length(); i++ )
		    cs ^= sentence.charAt(i);
		
		System.out.println( sentence + "; calculated: " + cs + "; given: " + Integer.parseInt( checksum, 16 ));
		
		//checksums match?
		return Integer.parseInt( checksum, 16 ) == cs;
	}
	/**
	 * Accessor returns the raw sentence.
	 */
	public String getRawSentence()
	{
		return rawSentence;
	}
	
	/**
	 * Returns the nth word of the sentence. This does not
	 * include the identifier or checksum.
	 *
	 * @param i the nth word
	 * 
	 * 
	 */
	public String getWord( int i )
	{
		return pieces[i];
	}
	
	/**
	 * Returns the number of words in the sentence.
	 * 
	 * @return the number of words
	 */
	public int numwords()
	{
		return pieces.length;
	}
	
	/**
	 * Returns the 5 code identifier.
	 * 
	 * @return the 5 code identifier
	 */
	public String getIdentifier()
	{
		return identifier;
	}
	
	/**
	 * A convenience method that hecks if a string is in boolean form.
	 */
	protected static boolean getBoolean( String s )
	{
		return  s.equalsIgnoreCase( "1" ) || s.equalsIgnoreCase("T")|| s.equalsIgnoreCase( "TRUE" );	
	}
	
	/**
	 * A convenience method to convert kilometers, nautical miles and miles.
	 * 
	 * @param value the value to convert
	 * @param from the type to convert from
	 * @param to the type to convert to
	 * 
	 * @return the converted value
	 */
	protected static double convertDist( double value, DistType from, DistType to )
	{
		//first convert to nautical miles
		if( from == DistType.KILO )
			value *= 0.539957; 
		else if( from == DistType.MILES )
			value *= 0.868976;
		
		//now convert to the to type
		if( to == DistType.KILO )
			value *= 1.852;
		else if( to == DistType.MILES )
			value *= 1.15078;
		
		return value;
	}
}