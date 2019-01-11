/* 类功能：实现pm10小时均值数据中的缺失值填充
 *输入：
 *1.字符串类型的device_id
 *2.字符串类型的待填充数据段的开始日期，格式为20xx-xx-01 00:00:00,如2017-12-01 00:00:00
 *3.字符串类型的待填充数据段的结束日期，格式为20xx-xx-01 00:00:00,如2018-01-01 00:00:00
 *输出：
 *1.double数组类型的完整pm10日均值数组(包含当月天数×24个数据)
 * */
 

package method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
public class MissingValue_Fill {
	 double [] Daitianchong_pm10 = new double [3124];
	
       public double []  MissingValue_Fill_Caculate(String Target_device,String year_month_begin_withHours,String year_month_end_withHours) {
    	 
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
   	    
   	//根据年份和月份获取这个月对应的天数
		  String Str_year = year_month_begin_withHours.substring(0,4);//substring( 开始索引(包括) ， 结束索引(不包括) )
		  String Str_month = year_month_begin_withHours.substring(5,7);
		  int Target_year = Integer.parseInt(Str_year);
		  int Target_month = Integer.parseInt(Str_month);
		  Calendar c = Calendar.getInstance();
		  c.set(Target_year, Target_month, 0); //输入类型为int类型
		  int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
		  System.out.println(Target_year + "年" + Target_month + "月有" + dayOfMonth + "天");
		  double [] New_pm10 = new double [dayOfMonth*24];
   	    
   	//连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码
   	 try {
	      Connection connect = DriverManager.getConnection(
	          "jdbc:mysql://localhost:3307/dust?characterEncoding=utf8&useSSL=true&serverTimezone=GMT","root","123456");
	     
	      //从dayavg.sql中取出所需要的待填充的历史数据
	      System.out.println("Success connect Mysql server!");
	      PreparedStatement pstmt1 = connect.prepareStatement("select * from sub_houravg where device_id = ? && create_time between ? and ? ORDER BY create_time ASC ");
	      pstmt1.setString(1, Target_device);
	      pstmt1.setString(2, year_month_begin_withHours);
	      pstmt1.setString(3, year_month_end_withHours);
	      ResultSet houravg = pstmt1.executeQuery();
	      int cumNumber,NotCompleteFlag=1;
	      while (houravg.next()) {
	    	  String string1 = houravg.getString("create_time").substring(8,10); //获取Pm10数据对应的天数
	    	  String string2 = houravg.getString("create_time").substring(11,13); //获取Pm10数据对应的小时数
	    	  Daitianchong_pm10[Integer.parseInt(string1)*100+Integer.parseInt(string2) ] = houravg.getDouble("pm10"); //例如：21号11时的pm10数据存储在Daitianchong_pm10[2111]
	    	  //System.out.println(CDJW_pm10[Integer.parseInt(string) - 1]);
	      }
	      while(NotCompleteFlag==1) {
	    	  int TotalFlag = 0;
	      for(int i=1;i<=dayOfMonth;i++) {
	    	  for(int j=0;j<24;j++) {
	    		  if  (Daitianchong_pm10[i*100+j] != 0) {
	    			  TotalFlag++;
	    		  }
	    		  
	    		  else {
	    			  cumNumber = 0;
	    			  if (Daitianchong_pm10[i*100+j-2] == 0 && Daitianchong_pm10[i*100+j-1] == 0 && Daitianchong_pm10[i*100+j+1] == 0 && Daitianchong_pm10[i*100+j+2] == 0)
	    			      break;
	    			  if(Daitianchong_pm10[i*100+j-2] != 0) 
		    			  cumNumber += 1;
	    			  if(Daitianchong_pm10[i*100+j-1] != 0) 
		    			  cumNumber += 1;
	    			  if(Daitianchong_pm10[i*100+j+1] != 0) 
		    			  cumNumber += 1;
	    			  if(Daitianchong_pm10[i*100+j+2] != 0) 
		    			  cumNumber += 1;
	    			  Daitianchong_pm10[i*100+j] = (Daitianchong_pm10[i*100+j-2]+Daitianchong_pm10[i*100+j-1]+Daitianchong_pm10[i*100+j+1]+Daitianchong_pm10[i*100+j+2])/cumNumber;
	    		  }
	    		  //如果所有的缺失点都得到填充就退出while循环
	    		  if (TotalFlag==dayOfMonth*24){
	    			  NotCompleteFlag = 0;
	    		  }
	    	  }
	      }
	      }
	      //
	      int k=0;
	      for(int i=1;i<=dayOfMonth;i++) {
	    	  System.out.println("i: "+i);
	    	  for(int j=0;j<24;j++) {
	    		 New_pm10[k] = Daitianchong_pm10[i*100+j];
	    		 //System.out.println("New_pm10: "+New_pm10[k]);
	    		 k++;
	    		 //
	    		 
	    		 if(i==dayOfMonth && j ==23)
	    			 return New_pm10;
	    	  }
	    	  
	      }
	      
	      System.out.println("all done1");
	      //for (int kk=0;kk<New_pm10.length;kk++) {
	    	  
	      //}
	      //return New_pm10;
    	   
   	 }
       catch (Exception e) {
	        System.out.print("get data error!");
	        e.printStackTrace();
	      }
    	   
   	return New_pm10; 
       }
}