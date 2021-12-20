package com.cairone.s3.ui.ctrl;

import com.cairone.s3.ui.response.EchoResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("api/echo")
@Produces(MediaType.APPLICATION_JSON)
public class EchoCtrl {

    @GET
    public EchoResponse echo(@QueryParam("message") String message) {
        return new EchoResponse(message.toUpperCase());
    }
}
