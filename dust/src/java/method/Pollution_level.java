/*
 * 类功能：实现污染等级划分，求出并返回当前pm10日均值对应的pm10的IAQI(空气质量分指数)。
 * 输入：
 * 1.int类型的pm10日均值
 * 返回：
 * 1.int类型的IAQI值
 * 2.String类型的污染等级(分为四个等级：Excellent，Good,Middle,Bad)
 * 3.String类型的污染颜色（对应于上面的四个等级：Red,Yellow,Orange,Red）
 */


package method;

public class Pollution_level {
    private double [] IAQI_air = {0,50,100,150,200,300,400,500};
    private double [] Pm10_LimitValue = {0,50,150,250,350,420,500,600};

    ReturnPack_of_PollutionLevel RP = new ReturnPack_of_PollutionLevel();

    public Pollution_level() {
    }

    public ReturnPack_of_PollutionLevel Pollution_level_Caculate(int pm10_value) {

            if (pm10_value < 0) {

                    return RP;
            }
            if (pm10_value >= 600) {
                    RP.IAQI_pm10 = 500;
                    RP.PollutionRank = "Bad";
                    RP.Color = "Red";
                    return RP;
            }

                      for(int i=0;i < Pm10_LimitValue.length - 1;i++) {
                              if (pm10_value >= Pm10_LimitValue[i] && pm10_value <= Pm10_LimitValue[i+1]) {
                                      RP.IAQI_pm10 = (int)((IAQI_air[i+1] - IAQI_air[i]) / (Pm10_LimitValue[i+1] - Pm10_LimitValue[i]) * (pm10_value - Pm10_LimitValue[i])+IAQI_air[i]);
                                      if(400 < RP.IAQI_pm10 && RP.IAQI_pm10 <=500) {
                                              RP.PollutionRank = "Bad";
                                                    RP.Color = "Red";
                                      }
                                      if(200 < RP.IAQI_pm10 && RP.IAQI_pm10 <=400) {
                                              RP.PollutionRank = "Middle";
                                                    RP.Color = "Orange";
                                      }
                                      if(100 < RP.IAQI_pm10 && RP.IAQI_pm10 <=200) {
                                              RP.PollutionRank = "Good";
                                                    RP.Color = "Yellow";
                                      }
                                      if(0 <= RP.IAQI_pm10 && RP.IAQI_pm10 <=100) {
                                              RP.PollutionRank = "Excellent";
                                                    RP.Color = "Blue";
                                      }
                                      return RP;
                              }

                      }
                     return RP; 
              }
}