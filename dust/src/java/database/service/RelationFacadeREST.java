/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.service;

import database.Device;
import database.DeviceWithPM10;
import database.RelationData;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
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
@Path("relation")
public class RelationFacadeREST extends AbstractFacade<Device> {
    
    String persistenceUnitName = "dustPU"; 
    EntityManagerFactory factory = Persistence.createEntityManagerFactory(persistenceUnitName);
    private EntityManager em = factory.createEntityManager();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of RelationFacadeREST
     */
    public RelationFacadeREST() {
        super(Device.class);
    }

    /**
     * Retrieves representation of an instance of database.service.RelationFacadeREST
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of RelationFacadeREST
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
    
    @GET
    @Path("month/{regionID}/{month}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<RelationData> getMonth(@PathParam("regionalId") Integer regionalId,
                            @PathParam("month") String month) {
        //TODO return proper representation object
        //throw new UnsupportedOperationException();
        Query query =  em.createNamedQuery("Device.findDeviceForRelation");
        query.setParameter("regionalId", regionalId);
        query.setParameter("month", month);
        List<Object[]> list = (List<Object[]>)query.getResultList();
        List<RelationData> deList = null;
        try 
        {
            deList = super.castEntity(list, RelationData.class);    
        }catch (Exception e) {    
            System.out.println("error in castEntity,and e is " + e.getMessage());    
        }
        return deList;
    }
    
    @GET
    @Path("season/{regionID}/{year}/{season}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<RelationData> getSeason(@PathParam("regionalId") Integer regionalId,
                            @PathParam("month") String month,
                            @PathParam("season") String season) {
        //TODO return proper representation object
        //throw new UnsupportedOperationException();
        Query query =  em.createNamedQuery("Device.findDeviceForRelation");
        query.setParameter("regionalId", regionalId);
        query.setParameter("month", month);
        query.setParameter("season", season);
        List<Object[]> list = (List<Object[]>)query.getResultList();
        List<RelationData> deList = null;
        try 
        {
            deList = super.castEntity(list, RelationData.class);    
        }catch (Exception e) {    
            System.out.println("error in castEntity,and e is " + e.getMessage());    
        }
        return deList;
    }
    
    @GET
    @Path("year/{regionID}/{year}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<RelationData> getYear(@PathParam("regionalId") Integer regionalId,
                            @PathParam("year") String year) {
        //TODO return proper representation object
        //throw new UnsupportedOperationException();
        Query query =  em.createNamedQuery("Device.findDeviceForRelation");
        query.setParameter("regionalId", regionalId);
        query.setParameter("year", year);
        List<Object[]> list = (List<Object[]>)query.getResultList();
        List<RelationData> deList = null;
        try 
        {
            deList = super.castEntity(list, RelationData.class);    
        }catch (Exception e) {    
            System.out.println("error in castEntity,and e is " + e.getMessage());    
        }
        return deList;
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
