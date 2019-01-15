/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.service;

import database.Dayavg;
import database.DayavgPK;
import java.lang.reflect.Constructor;
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

/**
 *
 * @author root
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
    @Path("month/{from}/{to}/{regionalId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Dayavg> findByRegionalIdForMonth(
            @PathParam("from") Integer from,
            @PathParam("to") Integer to,
            @PathParam("regionalId") Integer regionalId) 
    {
        Query query =  em.createNamedQuery("Dayavg.findByRegionalIdForMonth");
        query.setParameter("regionalId", regionalId);
        List<Object[]> list = (List<Object[]>)query.getResultList();
        List<Dayavg> deList = null;
        try 
        {
            deList = castEntity(list, Dayavg.class);
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
    
    /** 
     * 通用实体转换方法,将JPA返回的数组转化成对应的实体集合,这里通过泛型和反射实现 
     * @param <T> 
     * @param list 
     * @param clazz 需要转化后的类型 
     * @return  
     * @throws Exception 
     */  
    @SuppressWarnings("unchecked")  
    private static <T> List<T> castEntity(List<Object[]> list, Class<T> clazz) throws Exception {  
        List<T> returnList = new ArrayList<T>();
        Object[] co = list.get(0);
        Class[] c2 = new Class[co.length];
        
        //System.out.println("List.length is   " + list.size());
        //System.out.println("new Class[co.length] is   " + co.length);
          
        //确定构造方法  
        for(int i = 0; i < co.length; i++){
            c2[i] = co[i].getClass();
            
        }  

        int j = 1;
        for(Object[] o : list){
            //System.out.println("o[0].getClass() is   " + o[0].getClass().toString());
            //System.out.println("o[1].getClass() is   " + o[1].getClass().toString());
            Constructor<T> constructor = clazz.getConstructor(c2);
            //o[0] = Double(o[0]);
            returnList.add(constructor.newInstance(o));
        }  
          
        return returnList;  
    }
    
}
