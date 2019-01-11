/*
 * 类功能：实现污染等级划分，求出并返回当前pm10日均值对应的pm10的IAQI(空气质量分指数)。
 * 输入：
 * int类型的pm10日均值
 * 返回：
 * int类型的IAQI值。
 */


package method;

public class Pollution_level {
	private double [] IAQI_air = {0,50,100,150,200,300,400,500};
	private double [] Pm10_LimitValue = {0,50,150,250,350,420,500,600};
           int IAQI_pm10 = -1;       
	public  int Pollution_level_Caculate(int pm10_value) {
		
		if (pm10_value < 0) {
			
			return IAQI_pm10;
		}
		if (pm10_value >= 600) {
			IAQI_pm10 = 500;
			return IAQI_pm10;
		}
  		  
                	  for(int i=0;i < Pm10_LimitValue.length - 1;i++) {
                		  if (pm10_value >= Pm10_LimitValue[i] && pm10_value <= Pm10_LimitValue[i+1]) {
                			  IAQI_pm10 = (int)((IAQI_air[i+1] - IAQI_air[i]) / (Pm10_LimitValue[i+1] - Pm10_LimitValue[i]) * (pm10_value - Pm10_LimitValue[i])+IAQI_air[i]);
                		    return IAQI_pm10;
                		  }
                			  
                	  }
                	 return IAQI_pm10; 
                  }
}