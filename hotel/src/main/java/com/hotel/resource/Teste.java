package com.hotel.resource;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotel.model.json.JsonCalendar;

public class Teste {

	public static void main(final String[] args) throws JsonProcessingException {
		// TODO Auto-generated method stub
		final JsonCalendar cal = new JsonCalendar();
		final Map<String, Object> map = new HashMap();
		map.put("12-12-2012", cal);
		map.put("10-10-2010", cal);
		final ObjectMapper mapper = new ObjectMapper();

		final String jsonInString = mapper.writeValueAsString(map);

		System.out.println(jsonInString);

	}

}
