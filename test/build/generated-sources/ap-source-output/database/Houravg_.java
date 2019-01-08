package database;

import database.HouravgPK;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-01-08T21:01:40")
@StaticMetamodel(Houravg.class)
public class Houravg_ { 

    public static volatile SingularAttribute<Houravg, Double> airpressure;
    public static volatile SingularAttribute<Houravg, Double> rainfall;
    public static volatile SingularAttribute<Houravg, Integer> unitsId;
    public static volatile SingularAttribute<Houravg, Short> dataType;
    public static volatile SingularAttribute<Houravg, Double> pm10;
    public static volatile SingularAttribute<Houravg, String> deviceId;
    public static volatile SingularAttribute<Houravg, Double> winddirection;
    public static volatile SingularAttribute<Houravg, Double> voltage;
    public static volatile SingularAttribute<Houravg, Double> tsp;
    public static volatile SingularAttribute<Houravg, Double> valid;
    public static volatile SingularAttribute<Houravg, Integer> companyId;
    public static volatile SingularAttribute<Houravg, Double> pm25;
    public static volatile SingularAttribute<Houravg, Date> avgTime;
    public static volatile SingularAttribute<Houravg, Double> rate;
    public static volatile SingularAttribute<Houravg, HouravgPK> houravgPK;
    public static volatile SingularAttribute<Houravg, Double> noise;
    public static volatile SingularAttribute<Houravg, Double> temperature;
    public static volatile SingularAttribute<Houravg, Double> windspeed;
    public static volatile SingularAttribute<Houravg, Double> humidity;
    public static volatile SingularAttribute<Houravg, Double> boardTemperature;
    public static volatile SingularAttribute<Houravg, Double> boardHunmidity;
    public static volatile SingularAttribute<Houravg, Integer> regionalId;

}