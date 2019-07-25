package pl.test.jax.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import pl.test.jax.dto.UserDTO
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import javax.ws.rs.core.Response.*

@Path("/users")
class UserController {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	fun get(): Response {
		val user = UserDTO("Jan", "Kowalski")
		val mapper = jacksonObjectMapper()
		val json = mapper.writeValueAsString(user)
		return Response.ok(json).build()
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	fun post(json: String): Response {
		val mapper = jacksonObjectMapper()
		val user = mapper.readValue(json, UserDTO::class.java)
		println(user)
		return Response.status(Status.CREATED).build()
	}

	@GET
	@Path("/2")
	@Produces(MediaType.APPLICATION_JSON)
	fun get2(): UserDTO {
		return UserDTO("Jan", "Kowalski")
	}

	@POST
	@Path("/2")
	@Consumes(MediaType.APPLICATION_JSON)
	fun post2(user: UserDTO): Response {
		println(user)
		return Response.status(Status.CREATED).build()
	}

}