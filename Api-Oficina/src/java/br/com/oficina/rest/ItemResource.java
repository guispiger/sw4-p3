/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericItemResource.java to edit this template
 */
package br.com.oficina.rest;

import br.com.oficina.interfaces.AgendamentoDAO;
import br.com.oficina.modelo.Agendamento;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author guispiger
 */
public class ItemResource {

    private Date data;
    
    private AgendamentoDAO dao;

    /**
     * Creates a new instance of ItemResource
     */
    private ItemResource(String data, AgendamentoDAO dao) {
        try {
            this.data =  new SimpleDateFormat("yyyy-MM-dd").parse(data);
        } catch (ParseException ex) {
            Logger.getLogger(ItemResource.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        this.dao = dao;
    }

    /**
     * Get instance of the ItemResource
     */
    public static ItemResource getInstance(String data, AgendamentoDAO dao) {
        // The user may use some kind of persistence mechanism
        // to store and restore instances of ItemResource class.
        return new ItemResource(data, dao);
    }

    /**
     * Retrieves representation of an instance of br.com.oficina.rest.ItemResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Agendamento> getJson() {
        return dao.listarPorData(data);
    }

    /**
     * PUT method for updating or creating an instance of ItemResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }

    /**
     * DELETE method for resource ItemResource
     */
    @DELETE
    public void delete() {
    }
}
