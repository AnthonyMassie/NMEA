package com.anthonyontheweb.nmea.sentence;

/**
 * Parses the water temperature string.
 * 
 * @author Anthony
 *
 */
public class TWMSentence extends NMEASentence
{
	public enum TempType { KELVIN, CELSIUS, FAHRENHEIT };

	/**
	 * Contains the temperature in kelvins.
	 */
	private double temperature;
	
	/**
	 * Calls the NMEASentence constructor.
	 * 
	 * @param sentence the sentence of the water temperature
	 * @throws Exception 
	 */
	public TWMSentence( String sentence ) throws Exception
	{
		super(sentence);
		
		temperature = Double.parseDouble( getWord( 0 ) );

		//convert C to kelvin
		if( this.getWord( 1 ).indexOf('C') != -1 )
			temperature += 273.15;
		
		//convert F to kelvin
		if( this.getWord( 1 ).indexOf('F') != -1 )
			temperature = ( temperature - 32.0 ) / 1.8 + 273.15;
	}
	
	/**
	 * 
	 * @return the temperature
	 */
	public double getTemperature( TempType type )
	{
		double t = temperature;
		
		//convert to C
		if( type == TempType.CELSIUS )
			t -= 273.15;
		//convert to celsius
		else if( type == TempType.FAHRENHEIT )
			t = 1.8*(t - 273.0) + 32.0;
		
		return t;
	}
}