package pl.test.jax

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.ServletContextHandler
import org.eclipse.jetty.servlet.ServletHolder
import org.glassfish.jersey.server.ResourceConfig
import org.glassfish.jersey.servlet.ServletContainer
import javax.ws.rs.ext.ContextResolver


fun main() {
	val config = ResourceConfig()
		.packages("pl.test.jax")
		.register(ContextResolver<ObjectMapper> { ObjectMapper().registerModule(KotlinModule()) })
	val jerseyServlet = ServletHolder(ServletContainer(config))

	val server = Server(1010)
	val context = ServletContextHandler(server, "/")
	context.addServlet(jerseyServlet, "/*")

	try {
		server.start()
		server.join()
	} finally {
		server.destroy()
	}
}