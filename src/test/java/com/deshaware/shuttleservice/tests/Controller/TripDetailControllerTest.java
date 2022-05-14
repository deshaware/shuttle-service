
package com.deshaware.shuttleservice.tests.Controller;

import com.deshaware.shuttleservice.dto.ShuttleRequest;
import com.deshaware.shuttleservice.dto.TripRequest;
import com.deshaware.shuttleservice.model.TripDetail;
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
public class TripDetailControllerTest {
    @Autowired
    private MockMvc mockMvc;
	static String trip_id, entrollTrip;

	TripRequest tripRequest, tripRequest2, finalTripRequest;
    TripDetail tripDetail, tripDetail2, tripDetail3;
	MvcResult addTripRes;

    @BeforeAll
	public void initInput()
	{
        // create a tripDetailRequest
        tripDetail = new TripDetail();
        tripDetail.setStart_lat(55.5);
        tripDetail.setStart_long(56.6);
        tripDetail.setEnd_lat(85);
        tripDetail.setEnd_long(60);

    }

    @Test
    public void entrollTripTest() throws Exception{
        // url = '/api/tripdetail/enrollTrip?trip_id=2&email=sachin@gmail.com';
        addTripRes = this.mockMvc.perform(
            put("/api/tripdetail/enrollTrip?trip_id=1&email=swapnil@gmail.com'")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(tripRequest2.toJSONString()))
            .andExpect( status().isCreated() )
            .andExpect(jsonPath("$.message").value("Trip added successfully!"))
			.andReturn();
        //     ;
		// JSONObject jsonObject = new JSONObject(addTripRes.getResponse().getContentAsString());
		// String trip_id = jsonObject.getJSONObject("data").get("trip_id").toString();;
        // this.mockMvc.perform(
        //     delete("/api/trip/delete/" + trip_id))
        //     .andExpect(status().isAccepted());

    }

}