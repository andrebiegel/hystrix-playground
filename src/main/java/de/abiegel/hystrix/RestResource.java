/**
 * 
 */
package de.abiegel.hystrix;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.abiegel.rest.TransferObject;

/**
 * @author usiabiegel
 *
 */
@Path("root")
public interface RestResource {
	// START SNIPPET: example
	@Path("magic")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	default Response getTransferObject(){
		return Response.ok(TransferObject.asDefault()).build();
	}
	// END SNIPPET: example
}
