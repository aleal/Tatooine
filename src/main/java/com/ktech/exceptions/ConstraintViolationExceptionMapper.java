package com.ktech.exceptions;

import java.util.Iterator;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path.Node;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

	@Override
	public Response toResponse(ConstraintViolationException exception) {
		return Response.status(Response.Status.BAD_REQUEST).entity(prepareMessage(exception)).build();
	}

	private Map<Object, Object> prepareMessage(ConstraintViolationException exception) {
		return exception.getConstraintViolations().stream()
				.collect(Collectors.toMap(this.getPropertyName(), ConstraintViolation::getMessage));

	}

	private Function<ConstraintViolation<?>, String> getPropertyName() {
		return (cv) -> {
			Iterator<Node> path = cv.getPropertyPath().iterator();
			Node node = null;
			while (path.hasNext()) {
				node = path.next();
			}
			return node != null ? node.getName() : "unknown";
		};
	}

}
