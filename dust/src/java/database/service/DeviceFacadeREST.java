/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.service;

import database.Device;
import java.lang.reflect.Constructor;
import java.net.URL;
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

/**
 *
 * @author root
 */
@javax.ejb.Stateless
@Path("database.device")
public class DeviceFacadeREST extends AbstractFacade<Device> {

    //@PersistenceContext(unitName = "dustPU")
    String persistenceUnitName = "dustPU"; 
    EntityManagerFactory factory = Persistence.createEntityManagerFactory(persistenceUnitName);
    private EntityManager em = factory.createEntityManager();

    public DeviceFacadeREST() {
        super(Device.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Device entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Device entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Device find(@PathParam("id") Integer id) {
        //return super.find(id);
        return em.find(Device.class, id);
    }

    @GET
    @Override
    @Produces(MediaType.APPLICATION_JSON)
    public List<Device> findAll() {
        //String jpql = "FROM device";
        //javax.persistence.Query q = em.createQuery(jpql);
        //return q.getResultList();
        return super.findAll();
    }
    
    @GET
    @Path("region/{regionalId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Device> findByRegionalId(@PathParam("regionalId") Integer regionalId) {
        //javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        //cq.select(cq.from(Device.class));
        Query query =  em.createNamedQuery("Device.findByRegionalId");
        //Query query = em.createNativeQuery(Device.findByDeviceId,Device.class);
        query.setParameter("regionalId", regionalId); 
        return query.getResultList();
    }
    
    @GET
    @Path("table/{regionalId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Device> findByRegionalIdForTable(@PathParam("regionalId") Integer regionalId) {
        Query query =  em.createNamedQuery("Device.findByRegionalIdForTable");
        query.setParameter("regionalId", regionalId);
        //return query.getResultList();

        List<Object[]> list = (List<Object[]>)query.getResultList();
        List<Device> deList = null;
        try 
        {
            deList = castEntity(list, Device.class);    
        }catch (Exception e) {    
            System.out.println("error in castEntity,and e is " + e.getMessage());    
        }
        return deList;
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Device> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
          
        //确定构造方法  
        for(int i = 0; i < co.length; i++){  
            c2[i] = co[i].getClass();  
        }  
          
        for(Object[] o : list){  
            Constructor<T> constructor = clazz.getConstructor(c2);  
            returnList.add(constructor.newInstance(o)); 
        }  
          
        return returnList;  
    }  
    
}
