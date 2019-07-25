package pl.test.jax.controller

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.sun.jersey.api.client.ClientResponse
import com.sun.jersey.test.framework.AppDescriptor
import com.sun.jersey.test.framework.JerseyTest
import com.sun.jersey.test.framework.WebAppDescriptor
import org.junit.Test
import pl.test.jax.dto.UserDTO

class UserControllerTest : JerseyTest() {

	override fun configure(): AppDescriptor =
		WebAppDescriptor.Builder("pl.test.jax").build()

	@Test
	fun `should return user`() {
		// given
		val webResource = client().resource(baseURI)

		// when
		val response = webResource.path("/users").get(ClientResponse::class.java)

		// then
		assertThat(response.status).isEqualTo(200)
		assertThat(response.getEntity(String::class.java)).isEqualTo("""{"name":"Jan","surname":"Kowalski"}""")
//		assertThat(response.getEntity(UserDTO::class.java)).isEqualTo(UserDTO("Jan", "Kowalski"))
	}

}