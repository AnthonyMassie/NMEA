import com.anthonyontheweb.nmea.*;
import com.anthonyontheweb.nmea.sentence.*;

/**
 * This is the example file. It shows you how to initiate the NMEA
 * object, provide data, and create an event.
 * 
 * @author Anthony Massie
 * 
 */
public class Example {
    public static <T> void main(String[] args) {
    	//create out nmea object
    	NMEA m = new NMEA();
    	
    	//add temperature data (84.9*F)
    	m.data( "$--TWM,84.9,F*13" );
    	m.data( "$--AAM,1,0,1.7,N,G554*75" );
    	
    	//get the previously logged temperature
    	TWMSentence t = m.get( TWMSentence.class );
    	
    	System.out.println( "Temperature: " + t.getTemperature( TWMSentence.TempType.FAHRENHEIT ) + "*F" );
    	
    	//get the waypoint arrival alarm
    	AAMSentence aam = m.get( AAMSentence.class );
    	
    	System.out.println(
    			"Arrived at " + aam.getWaypointID()
    			+ " (radius=" + aam.getRadius( AAMSentence.DistType.KILO ) + "K)? "
    			 + aam.enteredCircle()
    	);
    	
        //add event
        m.bindEvent(
        	TWMSentence.class,
        	new NMEAEvent<TWMSentence> ()
        	{
        		@Override
        		public void event( TWMSentence sentence ) {
        			System.out.println(
        				"Temperature: " + sentence.getTemperature( TWMSentence.TempType.CELSIUS ) + "*C"
        			);
        		}
        	}
        );
        
        //the temperature has changed
        // the event should output this temperature
    	m.data( "$--TWM,90.9,F*16" );
    }
}
