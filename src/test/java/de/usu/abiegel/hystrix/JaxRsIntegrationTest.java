package de.usu.abiegel.hystrix;

import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.jboss.resteasy.plugins.server.tjws.TJWSEmbeddedJaxrsServer;
/**
 * Rest Integration setup with Tiny Web-Server
 * @author usiabiegel
 * <p>
 * <ul>
 * <li>call {@link JaxRsIntegrationTest#initServer(Object) }in Setup</li>
 * <li>call {@link JaxRsIntegrationTest#destroyServer()} in tear down</li>
 * </ul>
 * </p>
 * <p>for local debugging without maven set port and url in initServer</p>
 *
 */
public abstract class JaxRsIntegrationTest {

	protected String url = null;
	protected String port = null;
	protected TJWSEmbeddedJaxrsServer server;

	protected void initServer(Object... resourceInstance) {
		initServer(null, resourceInstance);
	}

	protected void initServer(List<Object> providers,Object... resourceInstance) {
		String urlProp = System.getProperty("integration.test.base.service.url");
		String portProp = System.getProperty("integration.test.base.service.port");
		port = (portProp!=null)?portProp:"53434";
		url =  (urlProp!=null)?urlProp:"http://localhost:53434";
		assertNotNull(url);
		assertNotNull(port);
		this.server = new TJWSEmbeddedJaxrsServer();
		server.setPort(Integer.parseInt(this.port));
		server.getDeployment().setResources(Arrays.asList(resourceInstance));
		if (providers != null ) {
			server.getDeployment().setProviders(providers);	
		}
		server.start();
	}

	protected void destroyServer() {
		if (server != null) {
			server.stop();
		}
		server = null;
		url = null;
		port = null;
	}

}
