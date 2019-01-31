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
    
    private List<Object[]> findDayavgForRegion(Integer regionalId, String from, String to)
    {
        Query query =  em.createNamedQuery("Dayavg.findDayavgForRegion");
        query.setParameter("regionalId", regionalId);
        query.setParameter("from", from);
        query.setParameter("to", to);
        List<Object[]> list = (List<Object[]>)query.getResultList();
        return list;
    }
    
    private List<Object[]> findDayavgForAllcity(String from, String to)
    {
        Query query =  em.createNamedQuery("Dayavg.findDayavgForAllcity");
        query.setParameter("from", from);
        query.setParameter("to", to);
        List<Object[]> list = (List<Object[]>)query.getResultList();
        return list;
    }
    
    private List<Object[]> findMonthavgForRegion(Integer regionalId, String from, String to)
    {
        Query query =  em.createNamedQuery("Dayavg.findByRegionalIdForMonth");
        query.setParameter("regionalId", regionalId);
        query.setParameter("from", from);
        query.setParameter("to", to);
        List<Object[]> list = (List<Object[]>)query.getResultList();
        return list;
    }
    
    private List<Object[]> findMonthavgForAllcity(String from, String to)
    {
        Query query =  em.createNamedQuery("Dayavg.findAllcityForMonth");
        query.setParameter("from", from);
        query.setParameter("to", to);
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
	       ArrayList<Double> temp = new ArrayList<Double>();
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
                    //ImpactFactor_Value = (calcAverage(Region_pm10)*RegionAreaCollection[SelectedLabel]) / (calcAverage(City_pm10)*ChengduTotalArea);
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
        }
        return null;
    }
		   //求当月影响因子
//	    else {
//			 //连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码
//		   	    try {
//		   	      Connection connect = DriverManager.getConnection(
//		   	          "jdbc:mysql://localhost:3307/dust?characterEncoding=utf8&useSSL=true&serverTimezone=GMT","root","123456");
//		   	           
//
//		   	      //System.out.println("Success connect Mysql server!");
//		   	      //Statement stmt = connect.createStatement();
//		   	      
//		   	      //在dayavg中找寻符合条件的记录，并将pm10数据存储在Region_pm10动态数组中
//		   	      PreparedStatement pstmt1 = connect.prepareStatement("select * from dayavg where regional_id = ? && avg_time between ? and ? ");
//		   	      pstmt1.setInt(1, SelectdRegional_id);
//		   	      pstmt1.setString(2, Date+"-01" );
//		   	      pstmt1.setString(3, Date+"-31" );
//		   	      ResultSet Region = pstmt1.executeQuery();
//		   	      //int i = 1;
//		   	      while (Region.next()) {
//		   	    	  
//		   	    	Region_pm10.add(Region.getDouble("pm10")); //
//		   	    	  
//		   	      }
//		   	      //System.out.println("all done1");
//		   	      
//		   	 //在dayavg中找寻符合条件的记录，并将pm10数据存储在City_pm10动态数组中
//		   	      PreparedStatement pstmt2 = connect.prepareStatement("select * from dayavg where avg_time between ? and ? ");
//		   	      pstmt2.setString(1, Date+"-01" );
//		   	      pstmt2.setString(2, Date+"-31" );
//		   	      
//		   	      ResultSet City = pstmt2.executeQuery();
//		   	      //int i = 1;
//		   	      while (City.next()) {
//		   	    	  
//		   	    	City_pm10.add(City.getDouble("pm10")); //
//		   	    	  
//		   	      }
//		   	      //System.out.println("all done2");
//		   	      //计算影响因子
//		   	   ImpactFactor_Value = (calcAverage(Region_pm10)*RegionAreaCollection[SelectedLabel]) / (calcAverage(City_pm10)*ChengduTotalArea);
//		   	   return ImpactFactor_Value;
//				   
//			   }catch (Exception e) {
//			        System.out.print(" 出错！");
//			        e.printStackTrace();
//			      }
//		   }
//	   } 
//	   //求当日影响因子
//	   else {
//		      
//		 //连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码
//   	    try {
//   	      Connection connect = DriverManager.getConnection(
//   	          "jdbc:mysql://localhost:3307/dust?characterEncoding=utf8&useSSL=true&serverTimezone=GMT","root","123456");
//   	           
//
//   	      System.out.println("Success connect Mysql server!");
//   	      //Statement stmt = connect.createStatement();
//   	      
//   	      //在dayavg中找寻符合条件的记录，并将pm10数据存储在Region_pm10动态数组中
//   	      PreparedStatement pstmt1 = connect.prepareStatement("select * from dayavg where regional_id = ? && avg_time = ? ");
//   	      pstmt1.setInt(1, SelectdRegional_id);
//   	      pstmt1.setString(2, Date );
//   	      
//   	      ResultSet Region = pstmt1.executeQuery();
//   	      //int i = 1;
//   	      while (Region.next()) {
//   	    	  
//   	    	Region_pm10.add(Region.getDouble("pm10")); //
//   	    	  
//   	      }
//   	      //System.out.println("all done1");
//   	      
//   	 //在dayavg中找寻符合条件的记录，并将pm10数据存储在City_pm10动态数组中
//   	      PreparedStatement pstmt2 = connect.prepareStatement("select * from dayavg where avg_time = ? ");
//   	      
//   	      pstmt2.setString(1, Date );
//   	      
//   	      ResultSet City = pstmt2.executeQuery();
//   	      //int i = 1;
//   	      while (City.next()) {
//   	    	  
//   	    	City_pm10.add(City.getDouble("pm10")); //
//   	    	  
//   	      }
//   	      //System.out.println("all done2");
//   	      //计算影响因子
//   	   ImpactFactor_Value = (calcAverage(Region_pm10)*RegionAreaCollection[SelectedLabel]) / (calcAverage(City_pm10)*ChengduTotalArea);
//   	   return ImpactFactor_Value;
//		   
//	   }catch (Exception e) {
//	        System.out.print(" 出错！");
//	        e.printStackTrace();
//	      }
//   	 
//   }
//	   return ImpactFactor_Value;
 //}
   
   @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}