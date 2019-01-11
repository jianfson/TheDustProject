package method;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
/*
 * 类功能：对一个月的数据进行数据修正。找到最近的国控点数据，通过多元回归修正选定设备的数据。
 * 输入：
 * 1.设备id，格式为字符串 ，如：Y0028000410265
 * 2. 待修正的起始日期，格式为字符串，如:2017-12-01
 * 3.待修正的结束日期，格式为字符串，如：2017-12-31
 * 3.带时分秒的起始日期，格式为字符串，如:2017-12-01 00:00:00
 * 4.带时分秒的结束日期，格式为字符串，如：2018-01-01 00:00:00 
 * 
 * 输出：
 * 1.修正后的pm10数据，以double数组 corrected_pm10 存储
 * 注意：带时分秒的结束日期 必须为下个月的第一天00:00:00
 * */
 
public class Correct_to_GKD {
           
             double [] Fail = {0};
            	  public   double [] Correct_to_GKD(String device,String year_month_begin,String year_month_end,String year_month_begin_withHours,String year_month_end_withHours) {
            		
            		
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
            	   String Target_device = "Y0028000410265";
          		  String Target_year_month_begin = "2017-12-01",Target_year_month_end = "2017-12-31";
          		  String Target_year_month_begin_withHours = "2017-12-01 00:00:00",Target_year_month_end_withHours = "2018-01-01 00:00:00";
          	    
            	  //假设这是欲修正的device_id,年，月
            	    if (device != null)
            	    {
            	    	 Target_device = device;
              		   Target_year_month_begin = year_month_begin;
              		   Target_year_month_end = year_month_end;
              		   Target_year_month_begin_withHours = year_month_begin_withHours;
              		   Target_year_month_end_withHours = year_month_end_withHours;
              	    
            	    }
            	    
            		  
            		  //根据年份和月份获取这个月对应的天数
            		  String Str_year = Target_year_month_begin.substring(0,4);//substring( 开始索引(包括) ， 结束索引(不包括) )
            		  String Str_month = Target_year_month_begin.substring(5,7);
            		  int Target_year = Integer.parseInt(Str_year);
            		  int Target_month = Integer.parseInt(Str_month);
            		  Calendar c = Calendar.getInstance();
            		  c.set(Target_year, Target_month, 0); //输入类型为int类型
            		  int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
            		  System.out.println(Target_year + "年" + Target_month + "月有" + dayOfMonth + "天");
            		  
            		//这个数组用来存储按时间先后排序的一个月私有监控点PM10数据
            		  double [] CDJW_pm10 = new double[dayOfMonth];
            		//这个数组用来存放对应的国控点的一个月pmn10数据
            		  double[] GKD_pm10 = new double[dayOfMonth];
            		  //这几个数组用来存储按时间先后排序的一个月私有监控点数据
            		  double[] CDJW_windspeed,CDJW_winddirection,CDJW_temperature,CDJW_humidity;
            		  CDJW_windspeed = new double[dayOfMonth];
            		  CDJW_winddirection = new double[dayOfMonth];
            		  CDJW_temperature = new double[dayOfMonth];
            		  CDJW_humidity = new double[dayOfMonth];
            		  //该字符串用来存储与选定的device_id最近的国控点name
            		  String BestMatch_GKDName="";
            		// 训练数据集:存储训练数据。每一行代表一个样本;每一列的格式为:x0(默认1) 监控点pm10 监控点风速 风向 温度 湿度  国控点pm10
            			  double [][] traindata = new double [dayOfMonth][7];
            			  // 测试数据集 存储修正之后的结果
            			  double [][] predictdata = new double [dayOfMonth][7]; 
            		  
            		  
            		//连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码
            	    try {
            	      Connection connect = DriverManager.getConnection(
            	          "jdbc:mysql://localhost:3307/dust?characterEncoding=utf8&useSSL=true&serverTimezone=GMT","root","123456");
            	           

            	      System.out.println("Success connect Mysql server!");
            	      //Statement stmt = connect.createStatement();
            	      
            	      //在dayavg中找寻符合条件的记录，并将pm10数据存储在CDJW_pm10数组中
            	      PreparedStatement pstmt1 = connect.prepareStatement("select * from dayavg where device_id = ? && avg_time between ? and ? ORDER BY avg_time ASC ");
            	      pstmt1.setString(1, Target_device);
            	      pstmt1.setString(2, Target_year_month_begin );
            	      pstmt1.setString(3, Target_year_month_end );
            	      ResultSet Dayavg = pstmt1.executeQuery();
            	      int i = 1;
            	      while (Dayavg.next()) {
            	    	  String string = Dayavg.getString("avg_time").substring(8,10); //获取Pm10数据对应的日期
            	    	  CDJW_pm10[Integer.parseInt(string) - 1] = Dayavg.getDouble("pm10"); //例如：21号的pm10数据存储在CDJW_pm10[20]
            	    	  //System.out.println(CDJW_pm10[Integer.parseInt(string) - 1]);
            	      }
            	      System.out.println("all done1");
            	      
            	      // 按照给定的device_id，在device.sql中找到对应的经纬度
            	      PreparedStatement pstmt2 = connect.prepareStatement("select * from device where device_id = ?");
            	      pstmt2.setString(1, Target_device);
            	      ResultSet Device = pstmt2.executeQuery();
            	      Device.next();//在调用getXXX之前这一行不能少，因为光标初始时指向第一个记录之前
            	      double lng = Device.getDouble("lng");
            	      double lat = Device.getDouble("lat");
            	      System.out.println("经度是:"+lng+" 纬度是" + lat);
            	      
            	      // 在national_info中查找最近的经纬度，获取最近经纬度对应的国控点name
            	      PreparedStatement pstmt3 = connect.prepareStatement("select * from national_info where lng between ? and ? && lat between ? and ?");
            	      pstmt3.setDouble(1, lng-1); //查找[经度-1,经度+1],[纬度-1,纬度+1]范围内的记录
            	      pstmt3.setDouble(2, lng+1);
            	      pstmt3.setDouble(3, lat-1);
            	      pstmt3.setDouble(4, lat+1);
            	      ResultSet National_info = pstmt3.executeQuery();
            	      double Minimum_distance = 0, temp_lng, temp_lat;
            	      
            	      
            	      Lati_and_longi_to_Distance DisMeasure = new Lati_and_longi_to_Distance();
            	      i = 1;
            	      while(National_info.next()) {
            	    	  temp_lng = National_info.getDouble("lng");
            			  temp_lat = National_info.getDouble("lat");
            			  
            	    	  if (i == 1) {
            	    		  
            	    		  Minimum_distance = DisMeasure.algorithm( lng, lat,temp_lng , temp_lat);
            	    		  BestMatch_GKDName = National_info.getString("name");
            	    		  i++;
            	    		  
            	    	  }
            	    	  else if(Minimum_distance > DisMeasure.algorithm( lng, lat,temp_lng , temp_lat)) {
            	    		  Minimum_distance = DisMeasure.algorithm( lng, lat,temp_lng , temp_lat);
            	    		  BestMatch_GKDName = National_info.getString("name");
            	    		  
            	    	  }
            	      }
            	      System.out.println("1国控点距离是："+Minimum_distance);
            	      if(Minimum_distance>3) {
            	    	  System.out.println("没有找到距离足够近的国控点！结束。");
            	    	  return Fail;
            	      }
            	      System.out.println("2国控点名称是："+BestMatch_GKDName);
            	      
            	      // 根据上一步中找到的国控点，寻找选定时间段内的记录，计算每一天的pm10均值，存储到GKD_pm10数组中。例如：21号的pm10数据存储在GKD_pm10[20]
            	      PreparedStatement pstmt4 = connect.prepareStatement("select * from national where name = ? && create_time between ? and ? ORDER BY create_time ASC ");
            	      pstmt4.setString(1,BestMatch_GKDName);
            	      pstmt4.setString(2,Target_year_month_begin_withHours);
            	      pstmt4.setString(3,Target_year_month_end_withHours);
            	      ResultSet National = pstmt4.executeQuery();
            	      double temp_pm10;
            	      int effective_point = 1; //记录国控点一天中非零的点数
            	      String LastCreat_time = "";
            	      National.next();
            	      String string1 = National.getString("create_time").substring(8,10); //获取Pm10数据对应的日期
            	      //System.out.println("3名称是："+National.getString("name"));
            		  GKD_pm10[Integer.parseInt(string1) - 1] = National.getDouble("pm10"); //例如：21号的pm10数据存储在GKD_pm10[20]
            		  LastCreat_time = string1;
            	      while(National.next()) {
            	    	  String string2 = National.getString("create_time").substring(8,10); //获取Pm10数据对应的日期
            	    	  temp_pm10 = National.getDouble("pm10");
            	    	  
            	    	  if (string2.equals(LastCreat_time)) {
            	    		  //System.out.println();
            	    		  if (temp_pm10 != 0.0) {
            	    			  GKD_pm10[Integer.parseInt(string2) - 1] += temp_pm10; //例如：21号的pm10数据存储在GKD_pm10[20]
            	    			  //if (string2.equals("31"))
            	    	    		  
            	    			  effective_point ++;
            	    		  }
            	    		  } else {
            	    			  GKD_pm10[Integer.parseInt(LastCreat_time) - 1] = GKD_pm10[Integer.parseInt(LastCreat_time) - 1] / effective_point;
            	    			  effective_point = 1;
            	    			  //System.out.println(GKD_pm10[Integer.parseInt(LastCreat_time) - 1]);
            	    			  LastCreat_time = string2;
            	    			  if (temp_pm10 != 0.0 && (Integer.parseInt(string2)!=1)) {
            	    				  GKD_pm10[Integer.parseInt(string2) - 1] = temp_pm10;  
            	    			      //System.out.println(string2);
            	    			  }
            	    		  }
            	    	  
            	      }          
            	      
            	    	  System.out.println("all done2");
            	    	  
            	      // 从houravg中取出device_id对应时间段的记录并求每日温湿度，风速风向均值。
            	    	  PreparedStatement pstmt5 = connect.prepareStatement("select * from sub_houravg where device_id = ? && create_time between ? and ? ORDER BY create_time ASC ");
            	          pstmt5.setString(1,Target_device);
            	          pstmt5.setString(2,Target_year_month_begin_withHours);
            	          pstmt5.setString(3,Target_year_month_end_withHours);
            	          ResultSet Houravg = pstmt5.executeQuery();
            	          double temp_windspeed,temp_winddirection,temp_temperature,temp_humidity;
            	          int total_point = 1; //记录一天中非零的记录点数
            	          String Sub_LastCreat_time = "";
            	          Houravg.next();
            	          String string3 = Houravg.getString("create_time").substring(8,10); //获取Pm10数据对应的日期
            	          //System.out.println("3名称是："+National.getString("name"));
            	    	  CDJW_windspeed[Integer.parseInt(string3) - 1] = Houravg.getDouble("windspeed"); 
            	    	  CDJW_winddirection[Integer.parseInt(string3) - 1] = Houravg.getDouble("winddirection");
            	    	  CDJW_temperature[Integer.parseInt(string3) - 1] = Houravg.getDouble("temperature");
            	    	  CDJW_humidity[Integer.parseInt(string3) - 1] = Houravg.getDouble("humidity");
            	    	  LastCreat_time = string3;
            	          while(Houravg.next()) {
            	        	  String string4 = Houravg.getString("create_time").substring(8,10); //获取Pm10数据对应的日期
            	        	  //temp_pm10 = National.getDouble("pm10");
            	        	  temp_windspeed = Houravg.getDouble("windspeed");
            	        	  temp_winddirection = Houravg.getDouble("winddirection");
            	        	  temp_temperature = Houravg.getDouble("temperature");
            	        	  //System.out.println("温度是："+temp_temperature);
            	        	  temp_humidity = Houravg.getDouble("humidity");
            	        	  if (string4.equals(LastCreat_time)) {
            	        		  
            	        			  CDJW_windspeed[Integer.parseInt(string4) - 1] += temp_windspeed; 
            	        	    	  CDJW_winddirection[Integer.parseInt(string4) - 1] += temp_winddirection;
            	        	    	  CDJW_temperature[Integer.parseInt(string4) - 1] += temp_temperature;
            	        	    	  CDJW_humidity[Integer.parseInt(string4) - 1] += temp_humidity;
            	        	    	  total_point ++;
            	        		  
            	        		  } else {
            	        			  CDJW_windspeed[Integer.parseInt(LastCreat_time) - 1] = CDJW_windspeed[Integer.parseInt(LastCreat_time) - 1] / total_point; 
            	        	    	  CDJW_winddirection[Integer.parseInt(LastCreat_time) - 1] = CDJW_winddirection[Integer.parseInt(LastCreat_time) - 1] / total_point;
            	        	    	  CDJW_temperature[Integer.parseInt(LastCreat_time) - 1] = CDJW_temperature[Integer.parseInt(LastCreat_time) - 1] / total_point;
            	        	    	  CDJW_humidity[Integer.parseInt(LastCreat_time) - 1] = CDJW_humidity[Integer.parseInt(LastCreat_time) - 1] / total_point;

            	        	    	  total_point = 1;
            	        			  //System.out.println("第"+LastCreat_time+"天的temperature数据是："+CDJW_temperature[Integer.parseInt(LastCreat_time) - 1]);
            	        			  LastCreat_time = string4;
            	        			  if ( Integer.parseInt(string4)!=1) {
            	        				  CDJW_windspeed[Integer.parseInt(string4) - 1] = temp_windspeed; 
            	            	    	  CDJW_winddirection[Integer.parseInt(string4) - 1] = temp_winddirection;
            	            	    	  CDJW_temperature[Integer.parseInt(string4) - 1] = temp_temperature;
            	            	    	  CDJW_humidity[Integer.parseInt(string4) - 1] = temp_humidity;
            	        			      //System.out.println(string4);
            	        			  }
            	        		  }
            	          }
            	    }catch (Exception e) {
            	        System.out.print("get data error!");
            	        e.printStackTrace();
            	      }
            	      //  判断建委pm10总量与国控点pm10总量哪个大；若后者大就退出该方法，不予修正
            	    double WhoBig = 0;
            	         for(int i=0;i<CDJW_pm10.length;i++) {
            	        	 WhoBig += CDJW_pm10[i]- GKD_pm10[i];
            	         }
            	         if (WhoBig<0) {
            	        	 System.out.print("国控点的pm10更大，无需纠正！");
            	        	 return Fail;
            	         }
            	    
            	      // 构建训练数据集
            	      for (int i=0;i < dayOfMonth;i++) {
            	      	traindata [i][0] = 1;
            	      	traindata [i][1] = CDJW_pm10 [i];
            	      	traindata [i][2] = CDJW_windspeed [i];
            	      	traindata [i][3] = CDJW_winddirection [i];
            	      	traindata [i][4] = CDJW_temperature [i];
            	      	traindata [i][5] = CDJW_humidity [i];
            	      	traindata [i][6] = GKD_pm10 [i];
            	      					
            	      	
            	      }
            	      LinearRegression m =new LinearRegression(traindata,dayOfMonth,7,0.001,1000000);
            	     // m.printTrainData();
            	      m.trainTheta();
            	      //m.printTheta();
            	      
            	      // 进行预测 获得修正之后的值
            	      //int t = 0;
            	      //double error = 0;
            	      double [] corrected_pm10 = new double [dayOfMonth];
            	      		 for (int i=0;i < dayOfMonth;i++) {
            	      		    	predictdata [i][0] = 1;
            	      		    	predictdata [i][1] = CDJW_pm10 [i];
            	      		    	predictdata [i][2] = CDJW_windspeed [i];
            	      		    	predictdata [i][3] = CDJW_winddirection [i];
            	      		    	predictdata [i][4] = CDJW_temperature [i];
            	      		    	predictdata [i][5] = CDJW_humidity [i];
            	      		    	predictdata [i][6] = predictdata [i][0] * m.theta [0] + predictdata [i][1] * m.theta [1] + predictdata [i][2] * m.theta [2] + predictdata [i][3] * m.theta [3] + predictdata [i][4] * m.theta [4] + predictdata [i][5] * m.theta [5];
            	      		    	corrected_pm10 [i] = predictdata [i][6];
            	      		    	//error = predictdata [i][6] - GKD_pm10 [i];
            	      		    	//t = i+1;
            	      		    	//System.out.println("第"+t+"天的偏差值："+error);		
            	      		    	
            	      		    }
            	      		 return corrected_pm10;
            	                 
            	    }
            	  
            	  
}