package org.prth.controller;

import org.prth.model.SPAA;
import org.prth.service.SpaaService;
import org.prth.service.SpaaServiceImpl;
import org.prth.dao.SPAADaoImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/spaa")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SPAAController {

    private final SpaaService spaaService = new SpaaServiceImpl(new SPAADaoImpl());

    public SPAAController() throws Exception {
    }

    @GET
    public Response getAllSpaa() {
        List<SPAA> SPAAS = spaaService.getAllSpaa();
        return Response.ok(SPAAS).build();
    }

    @GET
    @Path("/id")
    public Response getSpaaById(@QueryParam("id") Long id) {
        SPAA SPAA = spaaService.getSpaaById(id);
        if (SPAA != null) {
            return Response.ok(SPAA).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Employee not found").build();
        }
    }

    @POST
    @Path("/add")
    public Response addSpaa(SPAA SPAA) {
        SPAA savedSPAA = spaaService.addSpaa(SPAA);
        return Response.status(Response.Status.CREATED).entity(savedSPAA).build();
    }

    @PUT
    @Path("/update/id")
    public Response updateSpaa(@QueryParam("id") Long id, SPAA SPAA) {
        SPAA updatedSPAA = spaaService.updateSpaa(id, SPAA);
        return Response.ok(updatedSPAA).build();
    }

    @DELETE
    @Path("/delete/id")
    public Response deleteSpaa(@QueryParam("id") Long id) {
        SPAA SPAA = spaaService.getSpaaById(id);
        boolean deleted = spaaService.deleteSpaa(id);
        if (deleted) {
            return Response.status(Response.Status.OK).entity(SPAA).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Employee not found").build();
        }
    }

    @GET
    @Path("/getSpaaByTag")
    public Response getSpaaByTag(@QueryParam("tag") String tag) {
        List<SPAA> SPAAS = spaaService.getSpaaByTag(tag);
        return Response.ok(SPAAS).build();
    }
}