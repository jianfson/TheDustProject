package database;

import database.DayavgPK;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-01-08T21:01:40")
@StaticMetamodel(Dayavg.class)
public class Dayavg_ { 

    public static volatile SingularAttribute<Dayavg, Double> airpressure;
    public static volatile SingularAttribute<Dayavg, Double> rainfall;
    public static volatile SingularAttribute<Dayavg, Integer> unitsId;
    public static volatile SingularAttribute<Dayavg, Boolean> dataType;
    public static volatile SingularAttribute<Dayavg, Double> pm10;
    public static volatile SingularAttribute<Dayavg, DayavgPK> dayavgPK;
    public static volatile SingularAttribute<Dayavg, String> deviceId;
    public static volatile SingularAttribute<Dayavg, Double> winddirection;
    public static volatile SingularAttribute<Dayavg, Double> voltage;
    public static volatile SingularAttribute<Dayavg, Double> tsp;
    public static volatile SingularAttribute<Dayavg, Double> valid;
    public static volatile SingularAttribute<Dayavg, Integer> companyId;
    public static volatile SingularAttribute<Dayavg, Double> pm25;
    public static volatile SingularAttribute<Dayavg, Date> avgTime;
    public static volatile SingularAttribute<Dayavg, Double> rate;
    public static volatile SingularAttribute<Dayavg, Double> noise;
    public static volatile SingularAttribute<Dayavg, Double> temperature;
    public static volatile SingularAttribute<Dayavg, Double> windspeed;
    public static volatile SingularAttribute<Dayavg, Double> humidity;
    public static volatile SingularAttribute<Dayavg, Double> boardTemperature;
    public static volatile SingularAttribute<Dayavg, Double> boardHunmidity;
    public static volatile SingularAttribute<Dayavg, Integer> regionalId;

}