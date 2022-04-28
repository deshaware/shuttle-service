package com.deshaware.shuttleservice.tests.Controller;

// import static org.junit.Assert.assertNotNull;
// import static org.junit.Assert.assertEquals;
// import static org.junit.Assert.assertTrue;

import com.deshaware.shuttleservice.controller.ShuttleController;
import com.deshaware.shuttleservice.dto.ShuttleRequest;
import com.deshaware.shuttleservice.model.Vehicle;
import com.deshaware.shuttleservice.service.ShuttleService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.junit.jupiter.api.BeforeAll;

// import static org.junit.Assert.assertFalse;
// import org.junit.runner.RunWith;
// import mockit.Mock;
// import mockit.integration.junit4.JMockit;
// import org.junit.Before;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ShuttleControllerTest {
    @Autowired
    private MockMvc mockMvc;

	ShuttleRequest shuttleRequest;
	String shuttle_id;

    @BeforeAll
	public void initInput()
	{
		shuttleRequest = new ShuttleRequest();
		shuttleRequest.setShuttle_id("yello-loop");
		shuttleRequest.setCapacity(10);
		shuttleRequest.setShuttle_desc("A test shuttle");
		shuttleRequest.setShuttle_type(Vehicle.CAR);
	}

	@Test
	public void testAddShuttle() throws Exception{	
		System.out.println(shuttleRequest.toJSONString());
		MvcResult res =  this.mockMvc.perform(
            post("/api/shuttle/addShuttle")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(shuttleRequest.toJSONString()))
            .andExpect( status().isCreated() )
            .andExpect(jsonPath("$.message").value("Shuttle Added Succesfully"))
			.andReturn()
            ;
		System.out.println("res is ");
		System.out.println(res);
	}

	@Test
	public void testViewShuttle() throws Exception{	
		this.mockMvc.perform(
			get("/api/shuttle/viewAllShuttle"))
			.andExpect(status().isOk())
			.andExpect( jsonPath("$.message").value("Shuttle Fetched Succesfully")
		);
	}

	@Test
	public void testDeleteShuttle()throws Exception {	
        String shuttle_id = "yello-loop";
        this.mockMvc.perform(
            delete("/api/shuttle/delete/" + shuttle_id))
            .andExpect(status().isAccepted());
	}


}
