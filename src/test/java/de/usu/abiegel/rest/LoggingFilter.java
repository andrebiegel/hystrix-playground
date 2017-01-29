package de.usu.abiegel.rest;

import java.io.ByteArrayOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;



public class LoggingFilter  implements ClientRequestFilter, ClientResponseFilter, WriterInterceptor{

	final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
	final String ENTITY_STREAM_PROPERTY = "EntityLoggingFilter.entityStream";
	private class LoggingStream extends FilterOutputStream {

		private final StringBuilder sb = new StringBuilder();
		private final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		private final int maxEntitySize = 1024 * 8;

		LoggingStream(OutputStream out) {
			super(out);
		}

		StringBuilder getStringBuilder(Charset charset) {
			// write entity to the builder
			final byte[] entity = baos.toByteArray();

			sb.append(new String(entity, 0, entity.length, charset));
			if (entity.length > maxEntitySize) {
				sb.append("...more...");
			}
			sb.append('\n');

			return sb;
		}

		@Override
		public void write(final int i) throws IOException {
			if (baos.size() <= maxEntitySize) {
				baos.write(i);
			}
			out.write(i);
		}
	}
	
	@Override
	public void filter(ClientRequestContext requestContext) throws IOException {

		if (requestContext.hasEntity()) {
			final OutputStream stream = new LoggingStream(requestContext.getEntityStream());
			requestContext.setEntityStream(stream);
			requestContext.setProperty(ENTITY_STREAM_PROPERTY, stream);
		}
		System.out.println(requestContext.getStringHeaders().entrySet());
		System.out.println(requestContext.getUri());
		System.out.println("------------------- REQ");

	}
	
	@Override
	public void aroundWriteTo(WriterInterceptorContext context) throws IOException, WebApplicationException {
		final LoggingStream stream = (LoggingStream) context.getProperty(ENTITY_STREAM_PROPERTY);
		context.proceed();
		if (stream != null) {
			System.out.println(stream.getStringBuilder(DEFAULT_CHARSET));
		}

	}
	@Override
	public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext)
			throws IOException {
		if (requestContext.hasEntity()) {
			final OutputStream stream = new LoggingStream(requestContext.getEntityStream());
			requestContext.setEntityStream(stream);
			requestContext.setProperty(ENTITY_STREAM_PROPERTY, stream);
		}
		System.out.println(responseContext.getHeaders().entrySet());
	}
}
