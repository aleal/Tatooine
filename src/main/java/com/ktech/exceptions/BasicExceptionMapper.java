package com.ktech.exceptions;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class BasicExceptionMapper implements ExceptionMapper<Exception> {

	@Override
	public Response toResponse(Exception exception) {
		exception.printStackTrace();
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(prepareMessage(exception)).build();
	}

	private Map<Object, Object> prepareMessage(Exception exception) {
		Map<Object, Object> message = new HashMap<Object, Object>();
		message.put("error", exception.getMessage());
		return message;
	}

}
