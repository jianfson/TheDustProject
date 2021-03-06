/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author root
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(database.service.CompanyFacadeREST.class);
        resources.add(database.service.ControlFacadeREST.class);
        resources.add(database.service.CorrectionFacadeREST.class);
        resources.add(database.service.DayavgFacadeREST.class);
        resources.add(database.service.DeviceFacadeREST.class);
        resources.add(database.service.FactorFacadeREST.class);
        resources.add(database.service.HouravgFacadeREST.class);
        resources.add(database.service.MinuteauditavgFacadeREST.class);
        resources.add(database.service.MinuteavgTempFacadeREST.class);
        resources.add(database.service.NationalFacadeREST.class);
        resources.add(database.service.NationalInfoFacadeREST.class);
        resources.add(database.service.PredictFacadeREST.class);
        resources.add(database.service.RelationFacadeREST.class);
    }
    
}
