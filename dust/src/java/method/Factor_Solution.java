/*
类功能：求解某行政区某日/月/季度/年pm10的影响因子
方法1：Season_Factor_Solution_Calculate
功能：求解某行政区某季度pm10的影响因子
输入：
1.字符串类型的区域名，只能为字符型数组RegionNameCollection中的元素
2.字符串类型的年份，格式为xxxx，如2017
3.字符串类型的季节，如Spring,Summer,Autumn,Winter
输出：
1.double型的影响因子(范围为 0-1 )

方法2：Factor_Solution_Calculate
功能：求解某行政区某日/月/年pm10的影响因子
输入：
1.字符串类型的区域名，只能为字符型数组RegionNameCollection中的元素
2.字符型日期，有三种格式。求日均影响因子时为xxxx-xx-xx;求月均影响因子时为xxxx-xx;求年均影响因子时为xxxx
输出：
1.double型的影响因子(范围为 0-1 )
*/
package method;
import database.Dayavg;
import database.FactorData;
import database.service.AbstractFacade;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

@javax.ejb.Stateless
public class Factor_Solution extends AbstractFacade<Dayavg> {
    String persistenceUnitName = "dustPU"; 
    EntityManagerFactory factory = Persistence.createEntityManagerFactory(persistenceUnitName);
    private EntityManager em = factory.createEntityManager();
    
    public Factor_Solution() {
        super(Dayavg.class);

    }
    
    private List<Object[]> findDayavgForRegion(Integer regionalId, String from)
    {
        Query query =  em.createNamedQuery("Dayavg.findDayByRegionalIdForFactor");
        query.setParameter("regionalId", regionalId);
        query.setParameter("from", from);
        List<Object[]> list = (List<Object[]>)query.getResultList();
        return list;
    }
    
    private List<Object[]> findDayavgForAllcity(String from)
    {
        Query query =  em.createNamedQuery("Dayavg.findDayByAllcityForFactor");
        query.setParameter("from", from);
        List<Object[]> list = (List<Object[]>)query.getResultList();
        return list;
    }
    
    private List<Object[]> findMonthavgForRegion(Integer regionalId, String from)
    {
        Query query =  em.createNamedQuery("Dayavg.findMonthByRegionalIdForFactor");
        query.setParameter("regionalId", regionalId);
        query.setParameter("from", from);
        List<Object[]> list = (List<Object[]>)query.getResultList();
        return list;
    }
    
    private List<Object[]> findMonthavgForAllcity(String from)
    {
        Query query =  em.createNamedQuery("Dayavg.findMonthByAllcityForFactor");
        query.setParameter("from", from);
        List<Object[]> list = (List<Object[]>)query.getResultList();
        return list;
    }
    
    private List<Object[]> findYearavgForRegion(Integer regionalId, String from)
    {
        Query query =  em.createNamedQuery("Dayavg.findYearByRegionalIdForFactor");
        query.setParameter("regionalId", regionalId);
        query.setParameter("from", from);
        List<Object[]> list = (List<Object[]>)query.getResultList();
        return list;
    }
    
    private List<Object[]> findYearavgForAllcity(String from)
    {
        Query query =  em.createNamedQuery("Dayavg.findYearByAllcityForFactor");
        query.setParameter("from", from);
        List<Object[]> list = (List<Object[]>)query.getResultList();
        return list;
    }
    
    private List<Object[]> findWorksiteForRegion(Integer regionalId, String from)
    {
        Query query =  em.createNamedQuery("Dayavg.findWorksiteByRegionalIdForFactor");
        query.setParameter("regionalId", regionalId);
        query.setParameter("from", from);
        List<Object[]> list = (List<Object[]>)query.getResultList();
        return list;
    }
    
    private List<Object[]> findWorksiteForAllcity(String from)
    {
        Query query =  em.createNamedQuery("Dayavg.findWorksiteByAllcityForFactor");
        query.setParameter("from", from);
        List<Object[]> list = (List<Object[]>)query.getResultList();
        return list;
    }
    
    //private String [] RegionNameCollection = {"Shuangliu","Longquanyi","Xindu","Pidu","Qingbaijiang","Wenjiang","Chenghua","Jinniu","Wuhou","Qingyang","Jinjiang"};
    private int [] ReionaIdCollection = {510109,510108,510106,510107,510105,510104};
    private double [] RegionAreaCollection = {613,110,108,77,68,61};
    private double ChengduTotalArea = 1037;
    private ArrayList<Double>  Region_pm10 = new ArrayList<Double>();
    private ArrayList<Double> City_pm10 = new ArrayList<Double>();

    //SelectdRegional_id数组用来存储选定行政区的regional_id
    int  SelectdRegional_id = 0,SelectedLabel=-1;
    double ImpactFactor_Value;
	
    //求某行政区某季度pm10影响因子，会调用Factor_Solution_Calculate
    public double Season_Factor_Solution_Calculate(String Region_name,String Year,String Season) {
        String Month1 = new String();
        String Month2 = new String();
        String Month3 = new String();
        //ArrayList<Double> temp = new ArrayList<Double>();
        if (Season.equals("Spring")) {
            Month1 = Year + "-03";
            Month2 = Year + "-04";
            Month3 = Year + "-05";

        } else if(Season.equals("Summer")) {
            Month1 = Year + "-06";
            Month2 = Year + "-07";
            Month3 = Year + "-08";
        } else if(Season.equals("Autumn")) {
            Month1 = Year + "-09";
            Month2 = Year + "-10";
            Month3 = Year + "-11";
        } else if(Season.equals("Winter")) {
            Month1 = Year + "-12";
            Month2 = Integer.toString(Integer.parseInt(Year)+1) + "-01";
            Month3 = Integer.toString(Integer.parseInt(Year)+1) + "-02";
        }
        //temp.add(Factor_Solution_Calculate(Region_name,Month1));
        //temp.add(Factor_Solution_Calculate(Region_name,Month2));
        //temp.add(Factor_Solution_Calculate(Region_name,Month3));
        //System.out.println(temp.size());
        //ImpactFactor_Value = calcAverage(temp);
        return ImpactFactor_Value;
    }
	
    // 求解某行政区某日/月/年pm10的影响因子
    public List<FactorData> Factor_Solution_Calculate(String Date) {
        List<FactorData> returnList = new ArrayList<>();
	//判断该求日均影响因子，月均影响因子还是年均影响因子(0123-56-89)
	if(Date.length() != 10) {
            if (Date.length() != 7 && Date.length() == 4) {
		//求当年影响因子
                List<Object[]> yearAvg = null;
                yearAvg = findYearavgForAllcity(Date);
                if (yearAvg.isEmpty()) {
                    return null;
                }
		for(int i=0;i<ReionaIdCollection.length;i++) {
                    List<Object[]> yearRegion = null;
                    yearRegion = findYearavgForRegion(ReionaIdCollection[i], Date);
                    if (yearRegion.isEmpty()) {
                        continue;
                    }
                    
                    List<Object[]> site = null;
                    site = findWorksiteForRegion(ReionaIdCollection[i], Date);
                    Double effect = 0.0;
                    Object[] coRegion = yearRegion.get(0);
                    Object[] coAll = yearAvg.get(0);
                    effect = Double.parseDouble(coRegion[0].toString())*RegionAreaCollection[i] / (Double.parseDouble(coAll[0].toString())*ChengduTotalArea);

                    Object[] coSite = site.get(0);
                    Integer workSite = Integer.parseInt(coSite[0].toString());
                    
                    FactorData data = new FactorData(ReionaIdCollection[i], effect, workSite);
                    returnList.add(data);
                }
                return returnList;
	    }
            //求当月影响因子
	    else {
                List<Object[]> monthAvg = null;
                monthAvg = findMonthavgForAllcity(Date);
                if (monthAvg.isEmpty()) {
                    return null;
                }
		for(int i=0;i<ReionaIdCollection.length;i++) {
                    List<Object[]> monthRegion = null;
                    monthRegion = findMonthavgForRegion(ReionaIdCollection[i], Date);
                    if (monthRegion.isEmpty()) {
                        continue;
                    }
                    
                    List<Object[]> site = null;
                    site = findWorksiteForRegion(ReionaIdCollection[i], Date);
                    //ImpactFactor_Value = (calcAverage(Region_pm10)*RegionAreaCollection[SelectedLabel]) / (calcAverage(City_pm10)*ChengduTotalArea);
                    Double effect = 0.0;
                    Object[] coRegion = monthRegion.get(0);
                    Object[] coAll = monthAvg.get(0);
                    effect = Double.parseDouble(coRegion[0].toString())*RegionAreaCollection[i] / (Double.parseDouble(coAll[0].toString())*ChengduTotalArea);

                    Object[] coSite = site.get(0);
                    Integer workSite = Integer.parseInt(coSite[0].toString());
                    
                    FactorData data = new FactorData(ReionaIdCollection[i], effect, workSite);
                    returnList.add(data);
                }
                return returnList;
            }
        }
        //求当日影响因子
        else {
            List<Object[]> dayAvg = null;
            dayAvg = findDayavgForAllcity(Date);
            if (dayAvg.isEmpty()) {
                return null;
            }
            for(int i=0;i<ReionaIdCollection.length;i++) {
                List<Object[]> dayRegion = null;
                dayRegion = findDayavgForRegion(ReionaIdCollection[i], Date);
                if (dayRegion.isEmpty()) {
                    continue;
                }

                List<Object[]> site = null;
                site = findWorksiteForRegion(ReionaIdCollection[i], Date);
                //ImpactFactor_Value = (calcAverage(Region_pm10)*RegionAreaCollection[SelectedLabel]) / (calcAverage(City_pm10)*ChengduTotalArea);
                Double effect = 0.0;
                Object[] coRegion = dayRegion.get(0);
                Object[] coAll = dayAvg.get(0);
                effect = Double.parseDouble(coRegion[0].toString())*RegionAreaCollection[i] / (Double.parseDouble(coAll[0].toString())*ChengduTotalArea);

                Object[] coSite = site.get(0);
                Integer workSite = Integer.parseInt(coSite[0].toString());

                FactorData data = new FactorData(ReionaIdCollection[i], effect, workSite);
                returnList.add(data);
            }
            return returnList;
        }
        //return null;
    }
   
   @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}