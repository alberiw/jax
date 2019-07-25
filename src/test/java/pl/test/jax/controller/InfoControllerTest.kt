package pl.test.jax.controller

import com.sun.jersey.test.framework.JerseyTest
import com.sun.jersey.test.framework.AppDescriptor
import com.sun.jersey.test.framework.WebAppDescriptor
import com.sun.jersey.api.client.ClientResponse

import org.junit.Test
import assertk.assertThat
import assertk.assertions.*

class InfoControllerTest : JerseyTest() {

	override fun configure(): AppDescriptor =
		WebAppDescriptor.Builder("pl.test.jax").build()

	@Test
	fun `should return 200`() {
		// given
		val webResource = client().resource(baseURI)

		// when
		val response = webResource.path("/info").get(ClientResponse::class.java)

		// then
		assertThat(response.status).isEqualTo(200)
	}

}