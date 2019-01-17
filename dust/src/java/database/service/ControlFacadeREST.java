/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.service;

import database.CompanyData;
import database.ControlData;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author jasonley
 */
@Path("control")
public class ControlFacadeREST {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ControlFacadeREST
     */
    public ControlFacadeREST() {
    }

    /**
     * Retrieves representation of an instance of database.service.ControlFacadeREST
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of ControlFacadeREST
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
    @GET
    @Path("allcity")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ControlData> getAllcity() {
        //TODO return proper representation object
        List<ControlData> deList = null;
        return deList;
    }
    
    @GET
    @Path("{regionId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ControlData> getRegion(@PathParam("regionId") Integer regionId) {
        //TODO return proper representation object
        List<ControlData> deList = null;
        return deList;
    }
    
    @GET
    @Path("company/{regionId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CompanyData> getCompany(@PathParam("regionId") Integer regionId) {
        //TODO return proper representation object
        List<CompanyData> deList = null;
        return deList;
    }
}
