package com.anthonyontheweb.nmea;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import com.anthonyontheweb.nmea.sentence.NMEASentence;

/**
 * NMEA object delegates NMEA sentences and manages
 * events to call when the data changes.
 * 
 * @author Anthony Massie
 *
 */
public class NMEA
{
	/**
	 * Map of identifier to its NMEASentence.
	 */
	private Map<Class<? extends NMEASentence>, NMEASentence> data;
	
	/**
	 * Map of identifier to its NMEAEvent.
	 */
	private Map<Class<? extends NMEASentence>, NMEAEvent> events;
	
	/**
	 * Constructor initializes the maps.
	 */
	public NMEA()
	{
		//data
		data = new HashMap< Class<? extends NMEASentence>, NMEASentence >();
		events = new HashMap< Class<? extends NMEASentence>, NMEAEvent >();
	}
	
	/**
	 * Pass a data string (NMEA sentence) into the NMEA object.
	 */
	public void data( String sentence  )
	{
		String identifier = sentence.substring( 1, 6 ).replaceAll( "-", "" );
		NMEASentence s;
		Class c;
			
		//create class object and sentence
		try {
			//get class object
			c = Class.forName( "com.anthonyontheweb.nmea.sentence." + identifier + "Sentence" );
			
			//create the sentence
			s = (NMEASentence) c
					.getDeclaredConstructor( String.class )
					.newInstance( sentence );
		} catch (SecurityException | ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException e) {
			return;
		}
		

		//not null? add to our map
		if( s != null )
		{
			//add the object to our map
			data.put( c, s );

			//call event if it exists
			if( events.containsKey( c ) )
			{
				events.get( c ).event( s );
			}
		}
	}
	
	/**
	 * Binds an NMEAEvent on data change.
	 * 
	 * @param identifier the NMEA identifier
	 * @param event the NMEAEvent
	 */
	public <T extends NMEAEvent>void bindEvent( Class<? extends NMEASentence> c, T event )
	{
		events.put( c, event );
	}
	
	/**
	 * Returns the NMEASentence object given the
	 * identifier.
	 * 
	 * @param identifier the identifier of the sentence
	 *
	 * @return the NMEASentence object
	 */
	public <T extends NMEASentence> T get( Class<? extends NMEASentence> classObj )
	{
		return (T)data.get( classObj );
	}
}
