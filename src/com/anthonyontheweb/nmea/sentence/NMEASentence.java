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
	 */
	public NMEASentence( String sentence )
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
		}

		//split into words
		pieces = sentence.split( "," );
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
}