/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.service;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author root
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
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
    public static <T> List<T> castEntity(List<Object[]> list, Class<T> clazz) throws Exception {  
        List<T> returnList = new ArrayList<T>();
        Object[] co = list.get(0);
        Class[] c2 = new Class[co.length];
        
        //System.out.println("List.length is   " + list.size());
        //System.out.println("new Class[co.length] is   " + co.length);
          
        //确定构造方法  
        for(int i = 0; i < co.length; i++){
            //System.out.println("co[i].getClass() is   " + co[i].getClass().toString());
            c2[i] = co[i].getClass();
            
        }  

        int j = 1;
        for(Object[] o : list){
            //System.out.println("o[0].getClass() is   " + o[0].getClass().toString());
            //System.out.println("o[1].getClass() is   " + o[1].getClass().toString());
            Constructor<T> constructor = clazz.getConstructor(c2);
            returnList.add(constructor.newInstance(o));
        }  
          
        return returnList;  
    }
    
}
