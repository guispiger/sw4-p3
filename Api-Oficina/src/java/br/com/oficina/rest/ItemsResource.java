/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package br.com.oficina.rest;

import br.com.oficina.interfaces.AgendamentoDAO;
import br.com.oficina.modelo.Agendamento;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.POST;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author guispiger
 */
@Stateless
@Path("/agendamento")
public class ItemsResource {

    @Context
    private UriInfo context;
    
    @EJB(lookup = "java:global/Oficina-ejb/AgendamentoService!br.com.oficina.interfaces.AgendamentoDAO")
    private AgendamentoDAO dao;

    /**
     * Creates a new instance of ItemsResource
     */
    public ItemsResource() {
    }

    /**
     * Retrieves representation of an instance of br.com.oficina.rest.ItemsResource
     * @return an instance of br.com.oficina.modelo.Agendamento
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Agendamento getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * POST method for creating an instance of ItemResource
     * @param content representation for the new resource
     * @return an HTTP response with content of the created resource
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postJson(String content) {
        //TODO
        return Response.created(context.getAbsolutePath()).build();
    }

    /**
     * Sub-resource locator method for {data}
     */
    @Path("{data}")
    public ItemResource getItemResource(@PathParam("data") String data) {
        return ItemResource.getInstance(data, dao);
    }
}
