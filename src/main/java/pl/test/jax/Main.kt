package pl.test.jax

import com.sun.jersey.api.core.PackagesResourceConfig
import com.sun.jersey.spi.container.servlet.ServletContainer
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.ServletContextHandler
import org.eclipse.jetty.servlet.ServletHolder

fun main() {
	val config = PackagesResourceConfig("pl.test.jax")
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