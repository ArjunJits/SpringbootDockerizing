package com.app.mapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.app.mapper.controller.ObjectController;
import com.app.mapper.record.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;

//@SpringBootTest
@WebMvcTest(ObjectController.class)
class DemoApplicationTests {
	@Autowired
	private MockMvc client;
	
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	@DisplayName("""
			This Test to test getjson api from controller class
			
			""")
	 void getJsonObject_Test() throws Exception {
		this.client.perform(get("/getjson")).andExpect(status().isOk()) // Checks for HTTP 200
				.andExpect(content().string(org.hamcrest.Matchers.containsString("\"name\": \"Gaurav\"")))
				.andExpect(content().string(org.hamcrest.Matchers.containsString("\"salary\": 30000")));
	}
	
	@Test
	@DisplayName("""
			This Test to test employee api from controller class with speific fields
			
			""")
	void testEmployeeJsonValues() throws Exception {
		this.client.perform(get("/employee")
                .accept(MediaType.APPLICATION_JSON)) // We expect JSON back
                .andExpect(status().isOk())
                // Verify specific fields using JsonPath ($ represents the root)
                .andExpect(jsonPath("$.name").value("Gaurav"))
                .andExpect(jsonPath("$.salary").value(30000))
                .andExpect(jsonPath("$.married").value(true));
	}
	
	@Test
	@DisplayName("""
			This Test to test employee api from controller class withcomplete JSON
			
			""")
	void testWholeEmployeeRecord() throws Exception {
	    // The expected JSON structure
	    String expectedJson = """
	            {
	                "name": "Gaurav",
	                "salary": 30000,
	                "married": true
	            }
	            """;

	    this.client.perform(get("/employee"))
	            .andExpect(status().isOk())
	            // This compares the entire body to your expected string
	            .andExpect(content().json(expectedJson)); 
	}

	@Test
	@DisplayName(" Test with Objectmapper")
	void testWholeEmployeeRecord_ObjectMapper() throws Exception {
		Employee expectedEmployee = new Employee("Gaurav", 30000, true);
		String expectedJson=objectMapper.writeValueAsString(expectedEmployee);
		System.out.println(" Expected Json "+expectedJson);
		this.client.perform(get("/employee").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk()).andExpect(content().json(expectedJson));
		
	}
}
