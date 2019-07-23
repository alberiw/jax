package pl.test.jax.provider

import com.fasterxml.jackson.databind.ObjectMapper
import pl.test.jax.dto.DTO
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.lang.reflect.Type
import javax.ws.rs.Consumes
import javax.ws.rs.Produces
import javax.ws.rs.WebApplicationException
import javax.ws.rs.core.Context
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.MultivaluedMap
import javax.ws.rs.ext.*

@Provider
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
class DTOProvider<T: DTO> : MessageBodyWriter<T>, MessageBodyReader<T> {

	@Context
	private lateinit var providers: Providers

	private val resolver = providers.getContextResolver(ObjectMapper::class.java, MediaType.APPLICATION_JSON_TYPE)

	private val mapper = resolver.getContext(ObjectMapper::class.java)

	@Throws(IOException::class, WebApplicationException::class)
	override fun writeTo(t: T, type: Class<*>?, genericType: Type?, annotations: Array<out Annotation>?, mediaType: MediaType?, httpHeaders: MultivaluedMap<String, Any>?, entityStream: OutputStream?) {
		mapper.writeValue(entityStream, t)
	}

	@Throws(IOException::class, WebApplicationException::class)
	override fun readFrom(type: Class<T>?, genericType: Type?, annotations: Array<out Annotation>?, mediaType: MediaType?, httpHeaders: MultivaluedMap<String, String>?, entityStream: InputStream?): T =
		mapper.readValue(entityStream, type)

	override fun getSize(t: T, type: Class<*>?, genericType: Type?, annotations: Array<out Annotation>?, mediaType: MediaType?): Long =
		-1

	override fun isReadable(type: Class<*>?, genericType: Type?, annotations: Array<out Annotation>?, mediaType: MediaType?): Boolean =
		type is DTO

	override fun isWriteable(type: Class<*>?, genericType: Type?, annotations: Array<out Annotation>?, mediaType: MediaType?): Boolean =
		type is DTO

}