package pl.test.jax.provider

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import javax.ws.rs.ext.ContextResolver
import javax.ws.rs.ext.Provider

@Provider
class ObjectMapperProvider : ContextResolver<ObjectMapper> {

	override fun getContext(p0: Class<*>?): ObjectMapper =
		jacksonObjectMapper()

}