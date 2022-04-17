package com.deshaware.shuttleservice.tests;

// import static org.junit.Assert.assertNotNull;
// import static org.junit.Assert.assertEquals;
// import static org.junit.Assert.assertTrue;

import com.deshaware.shuttleservice.controller.ShuttleController;
import com.deshaware.shuttleservice.dto.ShuttleRequest;
import com.deshaware.shuttleservice.model.Vehicle;
import com.deshaware.shuttleservice.service.ShuttleService;

// import static org.junit.Assert.assertFalse;
// import org.junit.runner.RunWith;
// import mockit.Mock;
// import mockit.integration.junit4.JMockit;
// import org.junit.Before;

import org.junit.jupiter.api.Test;

// @RunWith(JMockit.class)
public class ShuttleTests {

    // @Before
	public void initInput()
	{
		ShuttleRequest shuttleRequest = new ShuttleRequest();
		shuttleRequest.setCapacity(1);
		shuttleRequest.setShuttle_desc("shuttle_desc");
		shuttleRequest.setShuttle_id("123");
		shuttleRequest.setShuttle_type(Vehicle.SHUTTLE);
	}

	@Test
	public void testAddShuttle(){
		// ShuttleRequest shuttleRequest = new ShuttleRequest();
		// ShuttleController shuttleController = new ShuttleController(shuttleService);
		// shuttleRequest.setCapacity(1);
		// shuttleRequest.setShuttle_desc("shuttle_desc");
		// shuttleRequest.setShuttle_id("123");
		// shuttleRequest.setShuttle_type(Vehicle.SHUTTLE);
		// shuttleController.addShuttle(shuttleRequest);
		
	}


}
