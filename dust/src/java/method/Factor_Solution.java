/*
功能：求解某行政区某日/月/年pm10的影响因子
输入：
1.字符串类型的区域名，只能为字符型数组RegionNameCollection中的元素
2.字符型日期，有三种格式。求日均影响因子时为xxxx-xx-xx;求月均影响因子时为xxxx-xx;求年均影响因子时为xxxx
输出：
1.double型的影响因子(范围为 0-1 )

*/
package method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Factor_Solution {
	private String [] RegionNameCollection = {"Shuangliu","Longquanyi","Xindu","Pidu","Qingbaijiang","Wenjiang","Chenghua","Jinniu","Wuhou","Qingyang","Jinjiang"};
	private int [] ReionaIdCollection = {510122,510112,510114,510124,510113,510115,510108,510106,510107,510105,510104};
	private double [] RegionAreaCollection = {1032,558,497,438,379,227,110,108,77,68,61};
	private double ChengduTotalArea = 3555;
	private ArrayList<Double>  Region_pm10 = new ArrayList<Double>();
	private ArrayList<Double> City_pm10 = new ArrayList<Double>();
	//SelectdRegional_id数组用来存储选定行政区的regional_id
	int  SelectdRegional_id = 0,SelectedLabel=-1;
	
	double ImpactFactor_Value;
   public double Factor_Solution_Calculate(String Region_name,String Date) {
	   //由输入的区域名得到对应的reginal_id
	   for(int i=0;i<RegionNameCollection.length;i++) {
		   if (Region_name.equals(RegionNameCollection[i])) {
			   SelectdRegional_id = ReionaIdCollection[i];
			   SelectedLabel = i;
		   }
	   }
	   if (SelectedLabel ==-1) {
		   System.out.println("输入区域名错误:没有在库中找到对应区域名！结束");
		   return 0;
	   }
		   
	   
	 //加载MYSQL JDBC驱动程序   
	    try {
	      Class.forName("com.mysql.cj.jdbc.Driver");     
	      //Class.forName("org.gjt.mm.mysql.Driver");
	     System.out.println("Success loading Mysql Driver!");
	    }
	    catch (Exception e) {
	      System.out.print("Error loading Mysql Driver!");
	      e.printStackTrace();
	    }
	   
	   //判断该求日均影响因子，月均影响因子还是年均影响因子(0123-56-89)
	   if(Date.length() != 10) {
		   if (Date.length() != 7 && Date.length() == 4) {
			   
			   //求当年影响因子
			 
			   //连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码
		   	    try {
		   	      Connection connect = DriverManager.getConnection(
		   	          "jdbc:mysql://localhost:3307/dust?characterEncoding=utf8&useSSL=true&serverTimezone=GMT","root","123456");
		   	           

		   	      System.out.println("Success connect Mysql server!");
		   	      //Statement stmt = connect.createStatement();
		   	      
		   	      //在dayavg中找寻符合条件的记录，并将pm10数据存储在Region_pm10动态数组中
		   	      PreparedStatement pstmt1 = connect.prepareStatement("select * from dayavg where regional_id = ? && avg_time between ? and ? ");
		   	      pstmt1.setInt(1, SelectdRegional_id);
		   	      pstmt1.setString(2, Date+"-01-01" );
		   	      pstmt1.setString(3, Date+"-12-31" );
		   	      ResultSet Region = pstmt1.executeQuery();
		   	      //int i = 1;
		   	      while (Region.next()) {
		   	    	  
		   	    	Region_pm10.add(Region.getDouble("pm10")); //
		   	    	  
		   	      }
		   	      System.out.println("all done1");
		   	      
		   	 //在dayavg中找寻符合条件的记录，并将pm10数据存储在City_pm10动态数组中
		   	      PreparedStatement pstmt2 = connect.prepareStatement("select * from dayavg where avg_time between ? and ? ");
		   	      pstmt2.setString(1, Date+"-01-01" );
		   	      pstmt2.setString(2, Date+"-12-31" );
		   	      
		   	      ResultSet City = pstmt2.executeQuery();
		   	      //int i = 1;
		   	      while (City.next()) {
		   	    	  
		   	    	City_pm10.add(City.getDouble("pm10")); //
		   	    	  
		   	      }
		   	      System.out.println("all done2");
		   	      //计算影响因子
		   	   ImpactFactor_Value = (calcAverage(Region_pm10)*RegionAreaCollection[SelectedLabel]) / (calcAverage(City_pm10)*ChengduTotalArea);
		   	   return ImpactFactor_Value;
				   
			   }catch (Exception e) {
			        System.out.print(" 出错！");
			        e.printStackTrace();
			      }
			   
			   
		   }
		   //求当月影响因子
		   else {
			 //连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码
		   	    try {
		   	      Connection connect = DriverManager.getConnection(
		   	          "jdbc:mysql://localhost:3307/dust?characterEncoding=utf8&useSSL=true&serverTimezone=GMT","root","123456");
		   	           

		   	      System.out.println("Success connect Mysql server!");
		   	      //Statement stmt = connect.createStatement();
		   	      
		   	      //在dayavg中找寻符合条件的记录，并将pm10数据存储在Region_pm10动态数组中
		   	      PreparedStatement pstmt1 = connect.prepareStatement("select * from dayavg where regional_id = ? && avg_time between ? and ? ");
		   	      pstmt1.setInt(1, SelectdRegional_id);
		   	      pstmt1.setString(2, Date+"-01" );
		   	      pstmt1.setString(3, Date+"-31" );
		   	      ResultSet Region = pstmt1.executeQuery();
		   	      //int i = 1;
		   	      while (Region.next()) {
		   	    	  
		   	    	Region_pm10.add(Region.getDouble("pm10")); //
		   	    	  
		   	      }
		   	      System.out.println("all done1");
		   	      
		   	 //在dayavg中找寻符合条件的记录，并将pm10数据存储在City_pm10动态数组中
		   	      PreparedStatement pstmt2 = connect.prepareStatement("select * from dayavg where avg_time between ? and ? ");
		   	      pstmt2.setString(1, Date+"-01" );
		   	      pstmt2.setString(2, Date+"-31" );
		   	      
		   	      ResultSet City = pstmt2.executeQuery();
		   	      //int i = 1;
		   	      while (City.next()) {
		   	    	  
		   	    	City_pm10.add(City.getDouble("pm10")); //
		   	    	  
		   	      }
		   	      System.out.println("all done2");
		   	      //计算影响因子
		   	   ImpactFactor_Value = (calcAverage(Region_pm10)*RegionAreaCollection[SelectedLabel]) / (calcAverage(City_pm10)*ChengduTotalArea);
		   	   return ImpactFactor_Value;
				   
			   }catch (Exception e) {
			        System.out.print(" 出错！");
			        e.printStackTrace();
			      }
		   }
	   } 
	   //求当日影响因子
	   else {
		      
		 //连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码
   	    try {
   	      Connection connect = DriverManager.getConnection(
   	          "jdbc:mysql://localhost:3307/dust?characterEncoding=utf8&useSSL=true&serverTimezone=GMT","root","123456");
   	           

   	      System.out.println("Success connect Mysql server!");
   	      //Statement stmt = connect.createStatement();
   	      
   	      //在dayavg中找寻符合条件的记录，并将pm10数据存储在Region_pm10动态数组中
   	      PreparedStatement pstmt1 = connect.prepareStatement("select * from dayavg where regional_id = ? && avg_time = ? ");
   	      pstmt1.setInt(1, SelectdRegional_id);
   	      pstmt1.setString(2, Date );
   	      
   	      ResultSet Region = pstmt1.executeQuery();
   	      //int i = 1;
   	      while (Region.next()) {
   	    	  
   	    	Region_pm10.add(Region.getDouble("pm10")); //
   	    	  
   	      }
   	      System.out.println("all done1");
   	      
   	 //在dayavg中找寻符合条件的记录，并将pm10数据存储在City_pm10动态数组中
   	      PreparedStatement pstmt2 = connect.prepareStatement("select * from dayavg where avg_time = ? ");
   	      
   	      pstmt2.setString(1, Date );
   	      
   	      ResultSet City = pstmt2.executeQuery();
   	      //int i = 1;
   	      while (City.next()) {
   	    	  
   	    	City_pm10.add(City.getDouble("pm10")); //
   	    	  
   	      }
   	      System.out.println("all done2");
   	      //计算影响因子
   	   ImpactFactor_Value = (calcAverage(Region_pm10)*RegionAreaCollection[SelectedLabel]) / (calcAverage(City_pm10)*ChengduTotalArea);
   	   return ImpactFactor_Value;
		   
	   }catch (Exception e) {
	        System.out.print(" 出错！");
	        e.printStackTrace();
	      }
   	 
   }
	   return ImpactFactor_Value;
 }
   
 //求平均
   public double calcAverage(ArrayList<Double> sample) {
       // TODO Auto-generated method stub
       double sum = 0;
       int cnt = 0;
       for (int i = 0; i < sample.size(); i++) {
           sum += sample.get(i);
           cnt++;
       }

       return (double) sum / cnt;
   }
}