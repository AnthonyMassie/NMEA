NMEA
====

NMEA stands for National Marine Electronics Association and the NMEA0183 and NMEA2000 protocols are widely used in marine electronics. The purpose of this project is to provide an easy to use interface to parse the NMEA protocol.

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