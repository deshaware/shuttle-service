
package com.deshaware.shuttleservice.tests.Controller;

import com.deshaware.shuttleservice.dto.ShuttleRequest;
import com.deshaware.shuttleservice.dto.TripRequest;
import com.deshaware.shuttleservice.model.Vehicle;
import com.deshaware.shuttleservice.tests.Persist.PersistTrip;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ParseContext;
import com.mysql.cj.xdevapi.JsonParser;

import org.aspectj.lang.annotation.Before;
import org.json.*;

import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Instant;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.platform.commons.annotation.Testable;
import org.skyscreamer.jsonassert.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestExecution;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TripControllerTest {
    @Autowired
    private MockMvc mockMvc;
	static String trip_id;

	TripRequest tripRequest, tripRequest2, finalTripRequest;
	MvcResult addTripRes;

	public void initInput()
	{
		tripRequest = new TripRequest();
		tripRequest.setDriver_id("navya@gmail.com");
		tripRequest.setShuttle_id("manley-loop");
		tripRequest.setScheduled_on( Instant.parse("2022-04-23T12:00:00Z"));
		tripRequest.setStart_lang(43.0377074);
		tripRequest.setStart_lat(-76.13336859);

		tripRequest2 = new TripRequest();
		tripRequest2.setDriver_id("apurv@gmail.com");
		tripRequest2.setShuttle_id("orange-loop");
		tripRequest2.setScheduled_on( Instant.parse("2022-06-23T12:00:00Z"));
		tripRequest2.setStart_lang(122.0377074);
		tripRequest2.setStart_lat(-736.13336859);

		finalTripRequest =  new TripRequest();
		finalTripRequest.setDriver_id("navya@gmail.com");
		finalTripRequest.setShuttle_id("manley-loop");
		finalTripRequest.setScheduled_on( Instant.parse("2022-05-23T21:21:21Z"));
		finalTripRequest.setStart_lang(55.0377074);
		finalTripRequest.setStart_lat(-66.13336859);
	}

	@Test
	public void testAddShuttlePositive() throws Exception {
		System.out.println(tripRequest.toJSONString());
		this.mockMvc.perform(
            post("/api/trip/addTrip")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(tripRequest.toJSONString()))
            .andExpect( status().isCreated() )
            .andExpect(jsonPath("$.message").value("Trip added successfully!"))
			.andReturn()
            ;
		// System.out.println("ressss is ");
		// System.out.println(JsonParser.parseDoc(addTripRes.getResponse().getContentAsString()));
		// System.out.println(addTripRes);
		// System.out.println("content is");
		// System.out.println(this.addTripRes.getResponse().getContentAsString());
		// // JsonObject json = JsonParser.parseJSON(addTripRes.getResponse().getContentAsString());
		// JSONObject jsonObject = new JSONObject(addTripRes.getResponse().getContentAsString());
		// System.out.println(jsonObject);
		// System.out.println("status is");
		// System.out.println(jsonObject.get("status"));
		// System.out.println("trip is");
		// System.out.println(jsonObject.getJSONObject("data").get("trip_id"));
		// trip = new PersistTrip();
		// trip.setTrip_id(jsonObject.getJSONObject("data").get("trip_id").toString());
		// trip_id = jsonObject.getJSONObject("data").get("trip_id").toString();

	}

	@Test
	public void testAddShuttlePositiveFinal() throws Exception {
		System.out.println(finalTripRequest.toJSONString());
		this.mockMvc.perform(
            post("/api/trip/addTrip")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(finalTripRequest.toJSONString()))
            .andExpect( status().isCreated() )
            .andExpect(jsonPath("$.message").value("Trip added successfully!"))
;
	}

	@Test
	public void testViewAllTrip() throws Exception{	
		this.mockMvc.perform(
			get("/api/trip/viewAllTrip"))
			.andExpect(status().isAccepted())
			.andExpect( jsonPath("$.message").value("Trips fetched successfully")
		);
	}

	@Test
	public void testViewTrip() throws Exception{	
		this.mockMvc.perform(
			get("/api/trip/viewAllTrip"))
			.andExpect(status().isAccepted())
			.andExpect( jsonPath("$.message").value("Trips fetched successfully")
		);
	}

	@Test
	public void testDeleteTrip()throws Exception {
		addTripRes =  this.mockMvc.perform(
            post("/api/trip/addTrip")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(tripRequest2.toJSONString()))
            .andExpect( status().isCreated() )
            .andExpect(jsonPath("$.message").value("Trip added successfully!"))
			.andReturn()
            ;
		JSONObject jsonObject = new JSONObject(addTripRes.getResponse().getContentAsString());
		String trip_id = jsonObject.getJSONObject("data").get("trip_id").toString();;
        this.mockMvc.perform(
            delete("/api/trip/delete/" + trip_id))
            .andExpect(status().isAccepted());
			// .andExc	"Trip "+ trip_id + " is deleted successully"
	// }
	
	}

}
