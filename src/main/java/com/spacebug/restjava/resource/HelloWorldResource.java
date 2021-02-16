package com.spacebug.restjava.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.spacebug.restjava.dto.HelloWorldDto;
import com.spacebug.restjava.dto.RequestErrorDto;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Optional;

import static com.spacebug.restjava.resource.Constants.NOT_FOUND;
import static com.spacebug.restjava.resource.Constants.OK;

@Path("hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {

    @GET
    @Operation(
        operationId = "Hello",
        description = "Says Hello World!",
        responses = {
            @ApiResponse(
                responseCode = OK,
                content = {
                    @Content(schema = @Schema(implementation = HelloWorldDto.class))
                }
            ),
            @ApiResponse(
                responseCode = NOT_FOUND,
                description = "Api Access not found for that ID",
                content = {
                    @Content(schema = @Schema(implementation = RequestErrorDto.class))
                }
            )
        }
    )
    public HelloWorldDto sayHello(@QueryParam("name") Optional<String> name) {
        return new HelloWorldDto("Hello, World!");
    }

}
