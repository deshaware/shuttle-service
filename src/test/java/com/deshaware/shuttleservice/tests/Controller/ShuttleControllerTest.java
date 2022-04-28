package com.deshaware.shuttleservice.tests.Controller;

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
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ShuttleControllerTest {
    @Autowired
    private MockMvc mockMvc;

	ShuttleRequest shuttleRequest;

    // @Before
	public void initInput()
	{
		// shuttleRequest = "{\"email\": \"ishan@gmail.com\", \"name\": \"Ishan Kishan\",\"role\": \"user\", \"password\": \"12345\"}";
	}

	@Test
	public void testAddShuttle(){	

	}

	@Test
	public void testViewShuttle(){	

	}

	@Test
	public void testDeleteShuttle(){	

	}


}
