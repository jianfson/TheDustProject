/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.service;

import database.MonthQuery;
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
import method.Pm10_Prediction;

/**
 * REST Web Service
 *
 * @author jasonley
 */
@Path("predict")
public class PredictFacadeREST {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PredictFacadeREST
     */
    public PredictFacadeREST() {
    }

    /**
     * Retrieves representation of an instance of database.service.PredictFacadeREST
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of PredictFacadeREST
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
    @GET
    @Path("month/{regionID}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MonthQuery> getMonth(@PathParam("regionID") Integer regionID) {
        //TODO return proper representation object
        Pm10_Prediction prediction = new Pm10_Prediction();
        String date = new String("2019-02");
        //System.out.print("regionalId:   "+regionID);
        return prediction.NextMonth_Pm10DayAvg_Prediction_Caculate(regionID, date);
    }
    
    @GET
    @Path("month/allcity")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MonthQuery> getMonthAllcity() {
        //TODO return proper representation object
        Pm10_Prediction prediction = new Pm10_Prediction();
        String date = new String("2019-02");
        //System.out.print("regionalId:   "+regionID);
        return prediction.NextMonth_Pm10DayAvg_Prediction_Caculate(510101, date);
    }
    
    @GET
    @Path("season/{regionID}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MonthQuery> getSeason(@PathParam("regionalId") Integer regionalId) {
        //TODO return proper representation object
        List<MonthQuery> deList = null;
        return deList;
    }
    
    @GET
    @Path("year/{regionID}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MonthQuery> getYear(@PathParam("regionalId") Integer regionalId) {
        //TODO return proper representation object
        List<MonthQuery> deList = null;
        return deList;
    }
    
    @GET
    @Path("threeyear/{regionID}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MonthQuery> getThreeYear(@PathParam("regionalId") Integer regionalId) {
        //TODO return proper representation object
        List<MonthQuery> deList = null;
        return deList;
    }
}
