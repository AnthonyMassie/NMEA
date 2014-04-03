NMEA Parser
====

NMEA stands for National Marine Electronics Association and the NMEA0183 and NMEA2000 protocols are widely used in marine electronics. The purpose of this project is to provide an easy to use interface to parse the NMEA protocol.

For a comprehensive list of NMEA sentences: http://fort21.ru/download/NMEAdescription.pdf

SIMPLE USAGE
===
```java
//create the nmea object
NMEA myNMEA = new NMEA();

//add an NMEA sentence to be parsed (water temperature at 84.9*C)
myNMEA.data( "$--TWM,84.9,C*HH" );

//get temperature sentence
TWMSentence temperature = myNMEA.get( TWMSentence.class );

//output temperature
System.out.println(
	temperature.getTemperature( TWMSentence.TempType.FAHRENHEIT )
);

```

ADDING YOUR OWN SENTENCES
===

In order to add your own NMEA sentence, you must extend the NMEASentence class as part of the com.anthonyontheweb.nmea.sentence package. The software expects your class to be named in the format IDENTIFIERSentence.

For example:
```java
// A simple NMEASentence to parse water temperature
public class TWMSentence extends NMEASentence
{
	//contains temperature
	private double temperature;
	
	//constructor accepts the raw string
	public TWMSentence( String sentence )
	{
		//DON'T FORGET TO CALL SUPER CLASS CONSTRUCTOR
		super(sentence);
		
		//each sentence is made of words, temperature is the first word!
		temperature = Double.parseDouble( getWord( 0 ) );
	}
	
	//temperature accessor
	public double getTemperature( )
	{
		return temperature;
	}
}
```

IMPLEMENTED SENTENCES
===
AAM - Waypoint Arrival Alarm

TWM - Water Temperature
