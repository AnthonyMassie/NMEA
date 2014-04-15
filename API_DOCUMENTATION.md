NMEA
===
NMEA()
Constructor initializes the maps.


data(String sentence)
Pass a data string (NMEA sentence) into the NMEA object.

NMEASentence
===
NMEASentence(String sentence)
The constructor processes the NMEA string.


getRawSentence()
Accessor returns the raw sentence.


getWord(int i)
Returns the nth word of the sentence. This does not include the identifier or checksum.


numwords()
Returns the number of words in the sentence.


getIdentifier()
Returns the 5 code identifier.
			
