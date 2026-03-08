package com.app.mapper.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.mapper.record.Employee;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class ObjectController {

	@GetMapping("/getjson")
	public String getJsonObject() {
		return """
						"employee": {
				             "name": "Gaurav",
				             "salary": 30000,
				            "married": true
				}
						""";
	}

	@GetMapping("/employee")
	public Employee getEmployee() {
		// Spring uses Jackson (from the BOM!) to convert this to JSON automatically
		return new Employee("Gaurav", 30000, true);
	}

	@GetMapping("/errorpage")
	public void testErrorPage(Model model, @RequestParam(required = false) String code, HttpServletResponse response)
			throws IOException {
		if ("400".equals(code)) {
			throw new BadRequestException("Bad request exception");
		}
		if ("403".equals(code)) {
			throw new ForbiddenException("You are not autherize to access the request");
		}
		model.addAttribute("greeting", "hello mallikarjun");
		response.sendRedirect("/error.html");
	}
	
	@SuppressWarnings("serial")
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	class BadRequestException extends RuntimeException{

		public BadRequestException(String message) {
			super(message);
		}
		
	}
	@SuppressWarnings("serial")
	@ResponseStatus(HttpStatus.FORBIDDEN)
	class ForbiddenException extends RuntimeException {
		public ForbiddenException(String message) {
			super(message);
		}

	}
}
