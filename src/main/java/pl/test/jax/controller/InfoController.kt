package pl.test.jax.controller

import javax.ws.rs.GET
import javax.ws.rs.Path

@Path("/info")
class InfoController {

	@GET
	fun get(): String = "ok"

}