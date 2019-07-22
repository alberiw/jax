package pl.test.jax.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

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
	fun post(json: String) {
		val mapper = jacksonObjectMapper()
		val user = mapper.readValue(json, UserDTO::class.java)
		println(user)
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
	fun post2(user: UserDTO) {
		println(user)
	}

}

data class UserDTO(
	val name: String,
	val surname: String
)