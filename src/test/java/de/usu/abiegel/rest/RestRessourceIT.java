/**
 * 
 */
package de.usu.abiegel.rest;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.GenericType;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.abiegel.hystrix.RestResource;
import de.abiegel.rest.TransferObject;
import de.usu.abiegel.hystrix.JaxRsIntegrationTest;


/**
 * @author usiabiegel
 *
 */
public class RestRessourceIT extends JaxRsIntegrationTest{


	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		initServer(new RestResource(){});
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		destroyServer();
	}

	/**
	 * Test method for
	 * {@link de.abiegel.rest.RestRessource#getTransferObject()}.
	 */
	@Test
	public final void testGetTransferObject() {
		RestResource clientInstance = new ResteasyClientBuilder().connectionPoolSize(20).build().target(url).register(LoggingFilter.class)
				.proxy(RestResource.class);
		assertEquals(TransferObject.asDefault(), clientInstance.getTransferObject().readEntity(new GenericType<TransferObject>(){})) ;
	}

}
