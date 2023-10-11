package com.stewartlavenia.tally.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.stewartlavenia.tally.entity.Customer;
import com.stewartlavenia.tally.entity.CustomerRequest;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

@Validated
@RequestMapping("/customers")
//@RequestMapping("/tallyCalCarbsDaily")
@OpenAPIDefinition(info = @Info(title = "Tally Customer Service"), servers = {@Server(url="http://localhost:8080", description = "Local Server")})
public interface TallyCustomerController {
	public static final int NAME_MAX_LENGTH = 30;
	

	//@formatter: off
	@Operation(
		summary = "Returns a new customer",
		description = "Given a user fills out all seven fields a new customer is created",
		responses = {
				@ApiResponse(
						responseCode = "201", 
						description = "A new customer is returned", 
						content = @Content(mediaType = "application/json", 
						schema =@Schema(implementation=Customer.class))),
				@ApiResponse(
						responseCode = "400", 
						description = "Params are invalid.", 
						content = @Content(mediaType = "application/json")),
				@ApiResponse(
						responseCode = "404", 
						description = "A customer field was not found.", 
						content = @Content(mediaType = "application/json")),
				@ApiResponse(
						responseCode = "500", 
						description = "An unplanned error occured.", 
						content = @Content(mediaType = "application/json"))
		},
		parameters = {
			@Parameter(name = "customerRequest", 
				allowEmptyValue = true, 
				required = false, 
				description = "The customer as JSON")
		}
		//@formatter: on
	) // end of @Operation

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	// bind it to a 
	Customer createCustomer(@Valid @RequestBody CustomerRequest customerRequest);
			
			
			
			
			
	//  ______________________end of return user's details mapping request ----------------------------------------------------- 
	/* @Operation(
			summary = "Returns first and last name",
			description = "Given a valid email it returns the user's first and last name",
			responses = {
					@ApiResponse(
							responseCode = "200", 
							description = "A first and last name is returned", 
							content = @Content(mediaType = "application/json", 
							schema =@Schema(implementation=Users.class))),
					@ApiResponse(
							responseCode = "400", 
							description = "Params are invalid.", 
							content = @Content(mediaType = "application/json")),
					@ApiResponse(
							responseCode = "404", 
							description = "No names were found for that email address.", 
							content = @Content(mediaType = "application/json")),
					@ApiResponse(
							responseCode = "500", 
							description = "An unplanned error occurred.", 
							content = @Content(mediaType = "application/json"))
			},
					
					parameters = {
					@Parameter(name = "email", 
					allowEmptyValue = false, 
					required = false, 
					description = "The user's email")
							
			}
			) // end of @Operation
	
	// Lisa suggestion
	@GetMapping("/email")
	@ResponseStatus(code = HttpStatus.OK)
	List<Users> fetchUserNameByEmail(
			@RequestParam(required = false)
			String email);
			*/
	// @formatter:on
			
} // end TallyController
	