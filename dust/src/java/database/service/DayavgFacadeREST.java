/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.service;

import database.Dayavg;
import database.DayavgPK;
import database.MonthQuery;
import java.lang.reflect.Constructor;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.PathSegment;
import method.Pm10_Prediction;

/**
 *
 * @author jasonley
 */
@javax.ejb.Stateless
@Path("database.dayavg")
public class DayavgFacadeREST extends AbstractFacade<Dayavg> {

    //@PersistenceContext(unitName = "dustPU")
    String persistenceUnitName = "dustPU"; 
    EntityManagerFactory factory = Persistence.createEntityManagerFactory(persistenceUnitName);
    private EntityManager em = factory.createEntityManager();

    private DayavgPK getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;id=idValue;createTime=createTimeValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        database.DayavgPK key = new database.DayavgPK();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> id = map.get("id");
        if (id != null && !id.isEmpty()) {
            key.setId(new java.lang.Long(id.get(0)));
        }
        java.util.List<String> createTime = map.get("createTime");
        if (createTime != null && !createTime.isEmpty()) {
            key.setCreateTime(new java.util.Date(createTime.get(0)));
        }
        return key;
    }

    public DayavgFacadeREST() {
        super(Dayavg.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Dayavg entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") PathSegment id, Dayavg entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") PathSegment id) {
        database.DayavgPK key = getPrimaryKey(id);
        super.remove(super.find(key));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Dayavg find(@PathParam("id") PathSegment id) {
        database.DayavgPK key = getPrimaryKey(id);
        return super.find(key);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Dayavg> findAll() {
        return super.findAll();
    }
    
    @GET
    @Path("region/{regionalId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Dayavg> findByRegionalId(@PathParam("regionalId") Integer regionalId) {
        Query query =  em.createNamedQuery("Dayavg.findByRegionalId");
        query.setParameter("regionalId", regionalId);
        return query.getResultList();
    }
    
    @GET
    @Path("month/allcity/{from}/{to}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MonthQuery> findAllcityForMonth(@PathParam("from") String from,
                                                @PathParam("to") String to)
    {
        Query query =  em.createNamedQuery("Dayavg.findAllcityForMonth");
        query.setParameter("from", from);
        query.setParameter("to", to);
        //System.out.println("from is " + from);
        //System.out.println("to is " + to);
        List<Object[]> list = (List<Object[]>)query.getResultList();
        List<MonthQuery> deList = null;
        try 
        {
            //deList = castEntity(list, Dayavg.class);
            deList = super.castEntity(list, MonthQuery.class);
        }catch (Exception e) {
            System.out.println("error in castEntity,and e is " + e.getMessage());    
        }
        return deList;
        //return query.getResultList();
    }
    
    @GET
    @Path("month/{regionalId}/{from}/{to}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MonthQuery> findByRegionalIdForMonth(
            @PathParam("regionalId") Integer regionalId,
            @PathParam("from") String from,
            @PathParam("to") String to)
    {
        Query query =  em.createNamedQuery("Dayavg.findByRegionalIdForMonth");
        query.setParameter("regionalId", regionalId);
        query.setParameter("from", from);
        query.setParameter("to", to);
        //System.out.println("from is " + from);
        //System.out.println("to is " + to);
        List<Object[]> list = (List<Object[]>)query.getResultList();
        List<MonthQuery> deList = null;
        try 
        {
            //deList = castEntity(list, Dayavg.class);
            deList = super.castEntity(list, MonthQuery.class);
        }catch (Exception e) {
            System.out.println("error in castEntity,and e is " + e.getMessage());    
        }
        return deList;
        //return query.getResultList();
    }
    
    @GET
    @Path("day/allcity/{from}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MonthQuery> findAllcityForDay(@PathParam("from") String from)
    {
        Query query =  em.createNamedQuery("Dayavg.findAllcityForDay");
        query.setParameter("from", from);
        //System.out.println("from is " + from);
        //System.out.println("to is " + to);
        List<Object[]> list = (List<Object[]>)query.getResultList();
        List<MonthQuery> deList = null;
        try 
        {
            //deList = castEntity(list, Dayavg.class);
            deList = super.castEntity(list, MonthQuery.class);
        }catch (Exception e) {
            System.out.println("error in castEntity,and e is " + e.getMessage());    
        }
        return deList;
        //return query.getResultList();
    }
    
    @GET
    @Path("day/{regionalId}/{from}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MonthQuery> findByRegionalIdForDay(
            @PathParam("regionalId") Integer regionalId,
            @PathParam("from") String from)
    {
        Query query =  em.createNamedQuery("Dayavg.findByRegionalIdForDay");
        query.setParameter("regionalId", regionalId);
        query.setParameter("from", from);
        //System.out.println("from is " + from);
        //System.out.println("to is " + to);
        List<Object[]> list = (List<Object[]>)query.getResultList();
        List<MonthQuery> deList = null;
        try 
        {
            //deList = castEntity(list, Dayavg.class);
            deList = super.castEntity(list, MonthQuery.class);
        }catch (Exception e) {
            System.out.println("error in castEntity,and e is " + e.getMessage());    
        }
        return deList;
        //return query.getResultList();
    }
    
    @GET
    @Path("season/allcity/{season}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MonthQuery> findAllcityForSeason(@PathParam("season") String season)
    {
        Query query =  em.createNamedQuery("Dayavg.findAllcityForSeason");
        query.setParameter("season", season);
        List<Object[]> list = (List<Object[]>)query.getResultList();
//        Pm10_Prediction pmPrediction = new Pm10_Prediction();
//        List<Object[]> list = pmPrediction.findAllcityForSeason(season);
        List<MonthQuery> deList = null;
        try 
        {
            //deList = castEntity(list, Dayavg.class);
            deList = super.castEntity(list, MonthQuery.class);
        }catch (Exception e) {
            System.out.println("error in castEntity,and e is " + e.getMessage());    
        }
        return deList;
        //return query.getResultList();
    }
    
    @GET
    @Path("season/{regionalId}/{season}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MonthQuery> findByRegionalIdForSeason(
            @PathParam("regionalId") Integer regionalId,
            @PathParam("season") String season)
    {
        Query query =  em.createNamedQuery("Dayavg.findByRegionalIdForSeason");
        query.setParameter("regionalId", regionalId);
        query.setParameter("season", season);
        //System.out.println("from is " + from);
        //System.out.println("to is " + to);
        List<Object[]> list = (List<Object[]>)query.getResultList();
        List<MonthQuery> deList = null;
        try 
        {
            //deList = castEntity(list, Dayavg.class);
            deList = super.castEntity(list, MonthQuery.class);
        }catch (Exception e) {
            System.out.println("error in castEntity,and e is " + e.getMessage());    
        }
        return deList;
        //return query.getResultList();
    }
    
    @GET
    @Path("year/allcity")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MonthQuery> findAllcityForYear()
    {
        Query query =  em.createNamedQuery("Dayavg.findAllcityForYear");
        List<Object[]> list = (List<Object[]>)query.getResultList();
        List<MonthQuery> deList = null;
        try 
        {
            //deList = castEntity(list, Dayavg.class);
            deList = super.castEntity(list, MonthQuery.class);
        }catch (Exception e) {
            System.out.println("error in castEntity,and e is " + e.getMessage());    
        }
        return deList;
        //return query.getResultList();
    }
    
    @GET
    @Path("year/{regionalId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MonthQuery> findByRegionalIdForYear(@PathParam("regionalId") Integer regionalId)
    {
        Query query =  em.createNamedQuery("Dayavg.findByRegionalIdForYear");
        query.setParameter("regionalId", regionalId);
        List<Object[]> list = (List<Object[]>)query.getResultList();
        List<MonthQuery> deList = null;
        try 
        {
            //deList = castEntity(list, Dayavg.class);
            deList = super.castEntity(list, MonthQuery.class);
        }catch (Exception e) {
            System.out.println("error in castEntity,and e is " + e.getMessage());    
        }
        return deList;
        //return query.getResultList();
    }
    
    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Dayavg> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
