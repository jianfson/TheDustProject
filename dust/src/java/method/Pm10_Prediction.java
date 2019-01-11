/* 类功能:实现下一个月/季度/年的pm10均值预测
 * 
 *方法1：NextMonth_Pm10_Prediction_Caculate;
 *功能：实现下一个月的pm10月均值预测
 *输入：
 *1.字符型device
 *2.字符型待预测月份；格式为xxxx-xx；如：2018-12
 *输出：
 *1.double型预测值
 *
 *方法2：NextSeason_Pm10_Prediction_Caculate
 *功能：实现下一个季节的pm10预测
 *输入：
 *1.字符型device
 *2.int型年份；如：2016
 *3.字符型季节；如：Spring,Summer,Autumn,Winter
 *输出：
 *1.double型预测值
 *
 *方法3：NextYear_Pm10_Prediction_Caculate
 *功能：实现下一年的Pm10预测
 *输入：
 *1.字符型device
 *2.Int型年份；如：2019
 *输出：
 *1.double型预测值
 */
 

package method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Pm10_Prediction {
	
	
	//该方法用于预测某月pm10均值
        public double NextMonth_Pm10_Prediction_Caculate(String device,String year_month) {
        	
        	double sum = 0 ,MonthA_Avg=0,MonthB_Avg=0,MonthC_Avg=0,MonthD_Avg=0,Predicted_pm10=0;
        	//A代表上年度的同一月，BCD分别为待预测月份的前三月
        	String MonthA1,MonthA2;
        	String MonthB1 = new String();
        	String MonthB2 = new String();
        	String MonthC1 = new String();
        	String MonthC2 = new String();
        	String MonthD1 = new String();
        	String MonthD2 = new String();
        	//这四个数组用来存储四个月的pm10日均值
        	double [] MonthA_pm10 = new double [31];
        	double [] MonthB_pm10 = new double [31];
        	double [] MonthC_pm10 = new double [31];
        	double [] MonthD_pm10 = new double [31];
        	//构成查找条件
        	MonthA1 = Integer.toString(Integer.parseInt(year_month.substring(0,4))-1) + "-" + year_month.substring(5,7)+"-01";
        	MonthA2 = Integer.toString(Integer.parseInt(year_month.substring(0,4))-1) + "-" + year_month.substring(5,7)+"-31";
        	
        	if (Integer.parseInt(year_month.substring(5,7))>3){
        	MonthB1 = year_month.substring(0,4) + "-" +Integer.toString(Integer.parseInt(year_month.substring(5,7))-3)+"-01";
        	MonthB2 = year_month.substring(0,4) + "-" +Integer.toString(Integer.parseInt(year_month.substring(5,7))-3)+"-31";
        	MonthC1 = year_month.substring(0,4) + "-" +Integer.toString(Integer.parseInt(year_month.substring(5,7))-2)+"-01";
        	MonthC2 = year_month.substring(0,4) + "-" +Integer.toString(Integer.parseInt(year_month.substring(5,7))-2)+"-31";
        	MonthD1 = year_month.substring(0,4) + "-" +Integer.toString(Integer.parseInt(year_month.substring(5,7))-1)+"-01";
        	MonthD2 = year_month.substring(0,4) + "-" +Integer.toString(Integer.parseInt(year_month.substring(5,7))-1)+"-31";
        	}
        	else if(Integer.parseInt(year_month.substring(5,7))==1){
        		MonthB1 = year_month.substring(0,4) + "-" +"10"+"-01";
            	MonthB2 = year_month.substring(0,4) + "-" +"10"+"-31";
            	MonthC1 = year_month.substring(0,4) + "-" +"11"+"-01";
            	MonthC2 = year_month.substring(0,4) + "-" +"11"+"-31";
            	MonthD1 = year_month.substring(0,4) + "-" +"12"+"-01";
            	MonthD2 = year_month.substring(0,4) + "-" +"12"+"-31";
        	}
        		else if(Integer.parseInt(year_month.substring(5,7))==2){
        			MonthB1 = year_month.substring(0,4) + "-" +"11"+"-01";
                	MonthB2 = year_month.substring(0,4) + "-" +"11"+"-31";
                	MonthC1 = year_month.substring(0,4) + "-" +"12"+"-01";
                	MonthC2 = year_month.substring(0,4) + "-" +"12"+"-31";
                	MonthD1 = year_month.substring(0,4) + "-" +"01"+"-01";
                	MonthD2 = year_month.substring(0,4) + "-" +"01"+"-31";
        	} else {
		        		MonthB1 = year_month.substring(0,4) + "-" +"12"+"-01";
		            	MonthB2 = year_month.substring(0,4) + "-" +"12"+"-31";
		            	MonthC1 = year_month.substring(0,4) + "-" +"01"+"-01";
		            	MonthC2 = year_month.substring(0,4) + "-" +"01"+"-31";
		            	MonthD1 = year_month.substring(0,4) + "-" +"02"+"-01";
		            	MonthD2 = year_month.substring(0,4) + "-" +"02"+"-31";
        	}
        	
        	//System.out.println("\n MonthB1："+MonthB1);
        	//System.out.println("\n MonthB2："+MonthB2);
        	
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
        	
        	//连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码
    	    try {
    	      Connection connect = DriverManager.getConnection(
    	          "jdbc:mysql://localhost:3307/dust?characterEncoding=utf8&useSSL=true&serverTimezone=GMT","root","123456");
    	           

    	      System.out.println("Success connect Mysql server!");
    	      //Statement stmt = connect.createStatement();
    	      
    	      //在dayavg中找寻上一年度同一月份的记录，并将pm10数据存储在MonthA_pm10数组中，然后求月均值，找不到就退出该方法
    	      PreparedStatement pstmt1 = connect.prepareStatement("select * from dayavg where device_id = ? && avg_time between ? and ? ORDER BY avg_time ASC ");
    	      pstmt1.setString(1, device);
    	      pstmt1.setString(2, MonthA1 );
    	      pstmt1.setString(3, MonthA2 );
    	      ResultSet Dayavg1 = pstmt1.executeQuery();
    	      int i = 0;
    	      while (Dayavg1.next()) {
    	    	  
    	    	  MonthA_pm10[i] = Dayavg1.getDouble("pm10"); 
    	    	  i++;
    	    	  //System.out.println(CDJW_pm10[Integer.parseInt(string) - 1]);
    	      }
    	      //求该月的总pm10
    	      for (int j=0;j<MonthA_pm10.length;j++) {
    	    	  sum += MonthA_pm10[j];
    	      }
    	      //如果sum=0说明没有对应月份的数据
    	      if (sum == 0) {
    	    	  System.out.print(" error：01.上年度同月数据不存在，无法进行预测!");
    	    	  return -1;
    	      } else MonthA_Avg=sum/i;   //求出A月份的月均值
    	      sum=0; //将sum清零，以便用来装另一个月的总pm10数据；
    	      
    	      //在dayavg中找寻同一年度前三月份的记录，并将pm10数据存储在MonthB_pm10数组中，然后求月均值，找不到就退出该方法
    	      PreparedStatement pstmt2 = connect.prepareStatement("select * from dayavg where device_id = ? && avg_time between ? and ? ORDER BY avg_time ASC ");
    	      pstmt2.setString(1, device);
    	      pstmt2.setString(2, MonthB1 );
    	      pstmt2.setString(3, MonthB2 );
    	      ResultSet Dayavg2 = pstmt2.executeQuery();
    	       i = 0;
    	      while (Dayavg2.next()) {
    	    	  
    	    	  MonthB_pm10[i] = Dayavg2.getDouble("pm10"); 
    	    	  i++;
    	    	  //System.out.println(CDJW_pm10[Integer.parseInt(string) - 1]);
    	      }
    	      //求该月的总pm10
    	      for (int j=0;j<MonthB_pm10.length;j++) {
    	    	  sum += MonthB_pm10[j];
    	      }
    	      //如果sum=0说明没有对应月份的数据
    	      if (sum == 0) {
    	    	  System.out.print(" error：02.同年度前三月数据不存在，无法进行预测!");
    	    	  return -1;
    	      } else MonthB_Avg=sum/i;   //求出B月份的月均值
    	      sum=0; //将sum清零，以便用来装另一个月的总pm10数据；
    	      
    	    //在dayavg中找寻同一年度前二月份的记录，并将pm10数据存储在MonthC_pm10数组中，然后求月均值，找不到就退出该方法
    	      PreparedStatement pstmt3 = connect.prepareStatement("select * from dayavg where device_id = ? && avg_time between ? and ? ORDER BY avg_time ASC ");
    	      pstmt2.setString(1, device);
    	      pstmt2.setString(2, MonthC1 );
    	      pstmt2.setString(3, MonthC2 );
    	      ResultSet Dayavg3 = pstmt2.executeQuery();
    	       i = 0;
    	      while (Dayavg3.next()) {
    	    	  
    	    	  MonthC_pm10[i] = Dayavg3.getDouble("pm10"); 
    	    	  i++;
    	    	  //System.out.println(CDJW_pm10[Integer.parseInt(string) - 1]);
    	      }
    	      //求该月的总pm10
    	      for (int j=0;j<MonthC_pm10.length;j++) {
    	    	  sum += MonthC_pm10[j];
    	      }
    	      //如果sum=0说明没有对应月份的数据
    	      if (sum == 0) {
    	    	  System.out.print(" error：03.同年度前二月数据不存在，无法进行预测!");
    	    	  return -1;
    	      } else MonthC_Avg=sum/i;   //求出C月份的月均值
    	      sum=0; //将sum清零，以便用来装另一个月的总pm10数据；
    	      
    	    //在dayavg中找寻同一年度前一月份的记录，并将pm10数据存储在MonthD_pm10数组中，然后求月均值，找不到就退出该方法
    	      PreparedStatement pstmt4 = connect.prepareStatement("select * from dayavg where device_id = ? && avg_time between ? and ? ORDER BY avg_time ASC ");
    	      pstmt4.setString(1, device);
    	      pstmt4.setString(2, MonthD1 );
    	      pstmt4.setString(3, MonthD2 );
    	      ResultSet Dayavg4 = pstmt4.executeQuery();
    	       i = 0;
    	      while (Dayavg4.next()) {
    	    	  
    	    	  MonthD_pm10[i] = Dayavg4.getDouble("pm10"); 
    	    	  i++;
    	    	  //System.out.println(CDJW_pm10[Integer.parseInt(string) - 1]);
    	      }
    	      //求该月的总pm10
    	      for (int j=0;j<MonthD_pm10.length;j++) {
    	    	  sum += MonthD_pm10[j];
    	      }
    	      //如果sum=0说明没有对应月份的数据
    	      if (sum == 0) {
    	    	  System.out.print(" error：04.同年度前一月数据不存在，无法进行预测!");
    	    	  return -1;
    	      } else MonthD_Avg=sum/i;   //求出D月份的月均值
    	      sum=0; //将sum清零，以便用来装另一个月的总pm10数据；
    	      
    	      Predicted_pm10 = MonthA_Avg*0.1 + MonthB_Avg*0.2 + MonthC_Avg*0.3 + MonthD_Avg*0.4;
    	      System.out.println("all done1");
    	      return Predicted_pm10;
    	    }catch (Exception e) {
    	        System.out.print("get data error!");
    	        e.printStackTrace();
    	      }
    	    return Predicted_pm10;
        }
        
        
        //该方法用来计算某个季度的pm10预测值
        public double NextSeason_Pm10_Prediction_Caculate(String device,int year,String Season) {
        	
        	double sum=0,predicted_pm10=0;
        	//这四个数组用来接收四个季节的pm10日均值
        	double [] Spring_pm10 = new double [92];
        	double [] Summer_pm10 = new double [92];
        	double [] Autumn_pm10 = new double [92];
        	double [] Winter_pm10 = new double [92];
        	//这四个double变量存储四个季节的pm10季节均值
        	double SeasonSpringAvg_pm10 = 0,SeasonSummerAvg_pm10 = 0,SeasonAutumnAvg_pm10 = 0,SeasonWinterAvg_pm10 = 0;
        	//生成查询条件
        	String Spring1 = new String();
        	String Spring2 = new String();
        	String Summer1 = new String();
        	String Summer2 = new String();
        	String Autumn1 = new String();
        	String Autumn2 = new String();
        	String Winter1 = new String();
        	String Winter2 = new String();
        	//Integer.parseInt(year_month.substring(5,7))-3
        	if(Season.equals("Spring")) {
        		Spring1 = Integer.toString(year-1)+"-03-01";
        		Spring2 = Integer.toString(year-1)+"-05-31";
        		Summer1 = Integer.toString(year-1)+"-06-01";
        		Summer2 = Integer.toString(year-1)+"-08-31";
        		Autumn1 = Integer.toString(year-1)+"-09-01";
        		Autumn2 = Integer.toString(year-1)+"-11-31";
        		Winter1 = Integer.toString(year-1)+"-12-01";
        		Winter2 = Integer.toString(year)+"-02-31";
        	}else if(Season.equals("Summer")) {
        		
        		Summer1 = Integer.toString(year-1)+"-06-01";
        		Summer2 = Integer.toString(year-1)+"-08-31";
        		Autumn1 = Integer.toString(year-1)+"-09-01";
        		Autumn2 = Integer.toString(year-1)+"-11-31";
        		Winter1 = Integer.toString(year-1)+"-12-01";
        		Winter2 = Integer.toString(year)+"-02-31";
        		Spring1 = Integer.toString(year)+"-03-01";
        		Spring2 = Integer.toString(year)+"-05-31";
        	
        	}else if(Season.equals("Autumn")) {
        		
        		Autumn1 = Integer.toString(year-1)+"-09-01";
        		Autumn2 = Integer.toString(year-1)+"-11-31";
        		Winter1 = Integer.toString(year-1)+"-12-01";
        		Winter2 = Integer.toString(year)+"-02-31";
        		Spring1 = Integer.toString(year)+"-03-01";
        		Spring2 = Integer.toString(year)+"-05-31";
        		Summer1 = Integer.toString(year)+"-06-01";
        		Summer2 = Integer.toString(year)+"-08-31";
        	
        	} else if(Season.equals("Winter")) {

        		Winter1 = Integer.toString(year-1)+"-12-01";
        		Winter2 = Integer.toString(year)+"-02-31";
        		Spring1 = Integer.toString(year)+"-03-01";
        		Spring2 = Integer.toString(year)+"-05-31";
        		Summer1 = Integer.toString(year)+"-06-01";
        		Summer2 = Integer.toString(year)+"-08-31";
        		Autumn1 = Integer.toString(year)+"-09-01";
        		Autumn2 = Integer.toString(year)+"-11-31";
        	} else {
        		System.out.println("季节输入错误");
        				return -1;
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
        	
        	//连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码
    	    try {
    	      Connection connect = DriverManager.getConnection(
    	          "jdbc:mysql://localhost:3307/dust?characterEncoding=utf8&useSSL=true&serverTimezone=GMT","root","123456");
    	           
    	      System.out.println("Success connect Mysql server!");
    	      //Statement stmt = connect.createStatement();
    	      
    	      //在dayavg中找寻上一个春季的记录，并将pm10数据存储在Spring_pm10数组中，然后求季度均值，找不到就退出该方法
    	      PreparedStatement pstmt1 = connect.prepareStatement("select * from dayavg where device_id = ? && avg_time between ? and ? ORDER BY avg_time ASC ");
    	      pstmt1.setString(1, device);
    	      pstmt1.setString(2, Spring1 );
    	      pstmt1.setString(3, Spring2 );
    	      ResultSet Dayavg1 = pstmt1.executeQuery();
    	      int i = 0;
    	      while (Dayavg1.next()) {
    	    	  
    	    	  Spring_pm10[i] = Dayavg1.getDouble("pm10"); 
    	    	  i++;
    	    	  //System.out.println(CDJW_pm10[Integer.parseInt(string) - 1]);
    	      }
    	      //求该季度的总pm10
    	      for (int j=0;j<Spring_pm10.length;j++) {
    	    	  sum += Spring_pm10[j];
    	      }
    	      //如果sum=0说明没有对应季度的数据
    	      if (sum == 0) {
    	    	  System.out.print(" error：01.上一个春季数据不存在，无法进行预测!");
    	    	  return -1;
    	      } else SeasonSpringAvg_pm10 = sum/i;   //求出春季的季度均值
    	      sum=0; //将sum清零，以便用来装另一个季度的总pm10数据；
    	      
    	    //在dayavg中找寻上一个夏季的记录，并将pm10数据存储在Summer_pm10数组中，然后求季度均值，找不到就退出该方法
    	      PreparedStatement pstmt2 = connect.prepareStatement("select * from dayavg where device_id = ? && avg_time between ? and ? ORDER BY avg_time ASC ");
    	      pstmt2.setString(1, device);
    	      pstmt2.setString(2, Summer1 );
    	      pstmt2.setString(3, Summer2 );
    	      ResultSet Dayavg2 = pstmt2.executeQuery();
    	       i = 0;
    	      while (Dayavg2.next()) {
    	    	  
    	    	  Summer_pm10[i] = Dayavg2.getDouble("pm10"); 
    	    	  i++;
    	    	  //System.out.println(CDJW_pm10[Integer.parseInt(string) - 1]);
    	      }
    	      //求该季度的总pm10
    	      for (int j=0;j<Summer_pm10.length;j++) {
    	    	  sum += Summer_pm10[j];
    	      }
    	      //如果sum=0说明没有对应季度的数据
    	      if (sum == 0) {
    	    	  System.out.print(" error：02.上一个夏季数据不存在，无法进行预测!");
    	    	  return -1;
    	      } else SeasonSummerAvg_pm10 = sum/i;   //求出夏季的季度均值
    	      sum=0; //将sum清零，以便用来装另一个季度的总pm10数据；
    	      
    	    //在dayavg中找寻上一个秋季的记录，并将pm10数据存储在Autumn_pm10数组中，然后求季度均值，找不到就退出该方法
    	      PreparedStatement pstmt3 = connect.prepareStatement("select * from dayavg where device_id = ? && avg_time between ? and ? ORDER BY avg_time ASC ");
    	      pstmt3.setString(1, device);
    	      pstmt3.setString(2, Autumn1 );
    	      pstmt3.setString(3, Autumn2 );
    	      ResultSet Dayavg3 = pstmt3.executeQuery();
    	       i = 0;
    	      while (Dayavg3.next()) {
    	    	  
    	    	  Autumn_pm10[i] = Dayavg3.getDouble("pm10"); 
    	    	  i++;
    	    	  //System.out.println(CDJW_pm10[Integer.parseInt(string) - 1]);
    	      }
    	      //求该季度的总pm10
    	      for (int j=0;j<Autumn_pm10.length;j++) {
    	    	  sum += Autumn_pm10[j];
    	      }
    	      //如果sum=0说明没有对应季度的数据
    	      if (sum == 0) {
    	    	  System.out.print(" error：03.上一个秋季数据不存在，无法进行预测!");
    	    	  return -1;
    	      } else SeasonAutumnAvg_pm10 = sum/i;   //求出秋季的季度均值
    	      sum=0; //将sum清零，以便用来装另一个季度的总pm10数据；
    	      
    	    //在dayavg中找寻上一个冬季的记录，并将pm10数据存储在Winter_pm10数组中，然后求季度均值，找不到就退出该方法
    	      PreparedStatement pstmt4 = connect.prepareStatement("select * from dayavg where device_id = ? && avg_time between ? and ? ORDER BY avg_time ASC ");
    	      pstmt4.setString(1, device);
    	      pstmt4.setString(2, Winter1 );
    	      pstmt4.setString(3, Winter2 );
    	      ResultSet Dayavg4 = pstmt4.executeQuery();
    	       i = 0;
    	      while (Dayavg4.next()) {
    	    	  
    	    	  Winter_pm10[i] = Dayavg4.getDouble("pm10"); 
    	    	  i++;
    	    	  //System.out.println(CDJW_pm10[Integer.parseInt(string) - 1]);
    	      }
    	      //求该季度的总pm10
    	      for (int j=0;j<Winter_pm10.length;j++) {
    	    	  sum += Winter_pm10[j];
    	      }
    	      //如果sum=0说明没有对应季度的数据
    	      if (sum == 0) {
    	    	  System.out.print(" error：04.上一个冬季数据不存在，无法进行预测!");
    	    	  return -1;
    	      } else SeasonWinterAvg_pm10 = sum/i;   //求出冬季的季度均值
    	      sum=0; //将sum清零，以便用来装另一个季度的总pm10数据；
        
    	      predicted_pm10 = (SeasonSpringAvg_pm10*0.2 + SeasonSummerAvg_pm10*0.1 + SeasonAutumnAvg_pm10*0.2 + SeasonWinterAvg_pm10*0.5)*0.85;
        
        }catch (Exception e) {
	        System.out.print("get data error!");
	        e.printStackTrace();
	      }     
    	       
	    return predicted_pm10;
        }
        
      //该方法用来计算下一年的pm10预测值
        public double NextYear_Pm10_Prediction_Caculate(String device,int year) {
        	double sum=0,predicted_pm10=0;
        	//这两个变量分别用来存储前年pm10年均值和去年pm10年均值
        	double YearBefore_lastAvg_pm10 = 0,LastYearAvg_pm10 = 0;
        	//这两个数组分别用来存储前年pm10日均值和去年pm10日均值
        	double [] YearBefore_last_pm10 = new double[365];
        	double [] LastYear_pm10 = new double[365];
        	//构成查询条件
        	String YearBefore_last1 = Integer.toString(year-2)+"-01-01";  //前年起始日期
        	String YearBefore_last2 = Integer.toString(year-2)+"-12-31";  //前年结束日期
        	String LastYear1 = Integer.toString(year-1)+"-01-01";  //去年起始日期
        	String LastYear2 = Integer.toString(year-1)+"-12-31";  //去年结束日期
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
        	
        	//连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码
    	    try {
    	      Connection connect = DriverManager.getConnection(
    	          "jdbc:mysql://localhost:3307/dust?characterEncoding=utf8&useSSL=true&serverTimezone=GMT","root","123456");
    	           

    	      System.out.println("Success connect Mysql server!");
    	      //Statement stmt = connect.createStatement();
    	      
    	      //在dayavg中找寻前年的记录，并将pm10数据存储在YearBefore_last_pm10数组中，然后求年均值，找不到就退出该方法
    	      PreparedStatement pstmt1 = connect.prepareStatement("select * from dayavg where device_id = ? && avg_time between ? and ? ORDER BY avg_time ASC ");
    	      pstmt1.setString(1, device);
    	      pstmt1.setString(2, YearBefore_last1 );
    	      pstmt1.setString(3, YearBefore_last2 );
    	      ResultSet Dayavg1 = pstmt1.executeQuery();
    	      int i = 0;
    	      while (Dayavg1.next()) {
    	    	  
    	    	  YearBefore_last_pm10[i] = Dayavg1.getDouble("pm10"); 
    	    	  i++;
    	    	  //System.out.println(CDJW_pm10[Integer.parseInt(string) - 1]);
    	      }
    	      //求该月的总pm10
    	      for (int j=0;j<YearBefore_last_pm10.length;j++) {
    	    	  sum += YearBefore_last_pm10[j];
    	      }
    	      //如果sum=0说明没有对应年份的数据
    	      if (sum == 0) {
    	    	  System.out.print(" error：01.前年数据不存在，无法进行预测!");
    	    	  return -1;
    	      } else YearBefore_lastAvg_pm10=sum/i;   //求出前年的年均值
    	      sum=0; //将sum清零，以便用来装另一年的总pm10数据；
    	      
    	      
    	    //在dayavg中找寻去年的记录，并将pm10数据存储在LastYear_pm10数组中，然后求年均值，找不到就退出该方法
    	      PreparedStatement pstmt2 = connect.prepareStatement("select * from dayavg where device_id = ? && avg_time between ? and ? ORDER BY avg_time ASC ");
    	      pstmt2.setString(1, device);
    	      pstmt2.setString(2, LastYear1 );
    	      pstmt2.setString(3, LastYear2 );
    	      ResultSet Dayavg2 = pstmt2.executeQuery();
    	       i = 0;
    	      while (Dayavg2.next()) {
    	    	  
    	    	  LastYear_pm10[i] = Dayavg2.getDouble("pm10"); 
    	    	  i++;
    	    	  //System.out.println(CDJW_pm10[Integer.parseInt(string) - 1]);
    	      }
    	      //求该月的总pm10
    	      for (int j=0;j<LastYear_pm10.length;j++) {
    	    	  sum += LastYear_pm10[j];
    	      }
    	      //如果sum=0说明没有对应年份的数据
    	      if (sum == 0) {
    	    	  System.out.print(" error：02.去年数据不存在，无法进行预测!");
    	    	  return -1;
    	      } else LastYearAvg_pm10=sum/i;   //求出去年的月均值
    	      sum=0; //将sum清零，以便用来装另一年的总pm10数据；
    	      
    	      predicted_pm10 = (YearBefore_lastAvg_pm10*0.3 + LastYearAvg_pm10*0.7)*0.85;
        }catch (Exception e) {
	        System.out.print("get data error!");
	        e.printStackTrace();
	      }  
        	return predicted_pm10;
        }
}