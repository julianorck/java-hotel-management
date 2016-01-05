package com.hotel.resource;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hotel.model.json.JsonCalendar;

@Path("/calendar")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.APPLICATION_JSON)

public class CalendarResource {

	@POST
	public Response getCalendar() throws JsonProcessingException {
		final JsonCalendar cal = new JsonCalendar();
		final Map<String, Object> map = new HashMap();
		map.put("2015-12-27", cal);
		map.put("2015-12-28", cal);

		// final ObjectMapper mapper = new ObjectMapper();

		// final String jsonInString = mapper.writeValueAsString(map);

		return Response.ok() // 200
				.entity(map).build();
	}

	@POST
	@Path("/send")
	@Consumes(MediaType.APPLICATION_JSON)

	public Response setReservation(final JsonArray data)
			throws JsonProcessingException {

		System.out.println("oi");

		final JsonObject jsonObject = data.getJsonObject(0);

		final String cid = jsonObject.getString("check_in");
		final LocalDate checkinDate = LocalDate.parse(cid);
		final LocalDate checkoutDate = LocalDate.parse(cid);
		System.out.println(checkinDate);
		System.out.println(checkoutDate);

		// final ObjectMapper mapper = new ObjectMapper();

		// final String jsonInString = mapper.writeValueAsString(map);

		return Response.ok() // 200
				.build();
	}

}
