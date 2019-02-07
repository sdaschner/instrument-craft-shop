package com.sebastian_daschner.instrument_craft_shop;

import org.eclipse.microprofile.faulttolerance.exceptions.BulkheadException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class BulkheadExceptionMapper implements ExceptionMapper<BulkheadException> {

    @Override
    public Response toResponse(BulkheadException exception) {
        return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
    }
}
