			<div class="class">
				<span class="class-name">com::anthonyontheweb::nmea::NMEA</span>
				<p class="method-description">NMEA object delegates NMEA sentences and manages events to call when the data changes.</p>
				<div class="method">
					<span class="method-name">NMEA()</span>
					<p class="method-description">Constructor initializes the maps.</p>
				</div>
				<div class="method">
					<span class="method-name">data(String sentence)</span>
					<p class="method-description">Pass a data string (NMEA sentence) into the NMEA object.</p>
				</div>
			</div>

			<div class="class">
				<span class="class-name">com::anthonyontheweb::nmea::sentence::NMEASentence</span>
				<p class="method-description">The NMEA class delegates NMEA sentences and handles events.</p>
				<div class="method">
					<span class="method-name">NMEASentence(String sentence)</span>
					<p class="method-description">The constructor processes the NMEA string.
					</p>
				</div>
				<div class="method">
					<span class="method-name">getRawSentence()</span>
					<p class="method-description">Accessor returns the raw sentence.</p>
				</div>
				<div class="method">
					<span class="method-name">getWord(int i)</span>
					<p class="method-description">Returns the nth word of the sentence. This does not include the identifier or checksum.</p>
				</div>
				<div class="method">
					<span class="method-name">numwords()</span>
					<p class="method-description">Returns the number of words in the sentence.</p>
				</div>
				<div class="method">
					<span class="method-name">getIdentifier()</span>
					<p class="method-description">Returns the 5 code identifier.</p>
				</div>
			</div>
