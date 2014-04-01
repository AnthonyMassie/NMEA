package com.anthonyontheweb.nmea;
import com.anthonyontheweb.nmea.sentence.*;

/**
 * Defines a callable event.
 * 
 * @author Anthony
 *
 */
public interface NMEAEvent<T extends NMEASentence>
{
	/**
	 * @return 
	 * 
	 */
	public void event( T sentence );
}