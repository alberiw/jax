package pl.test.jax.provider

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import javax.ws.rs.Consumes
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.ext.Provider

@Provider
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
class KotlinJacksonJaxbJsonProvider: JacksonJaxbJsonProvider() {

	init {
		setMapper(jacksonObjectMapper())
	}

}
