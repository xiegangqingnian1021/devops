package com.neu.framework.json;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class CustomMoneySerialize extends JsonSerializer<BigDecimal> {
	
	private DecimalFormat df = new DecimalFormat("##.00"); 

	@Override
	public void serialize(BigDecimal value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		if(value != null) {
            gen.writeString(df.format(value));  
        }
	}
}
