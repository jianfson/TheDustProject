/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dust;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import method.GetLocation;
import static method.GetLocation.getAdd;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * @author jiangxin
 */
public class test {

    public test() {

    }

    public static void AddLocation() {
        Connection conn = null;
        PreparedStatement stmt = null;
        PreparedStatement stmt_insert = null;
        ResultSet rs = null;

        String drive = "com.mysql.jdbc.Driver";
        System.out.println("ajax后台交互成功");

        try {
            Class.forName(drive);
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dust?characterEncoding=utf-8&user=root&password=1");
            System.out.println("数据库连接成功");
            String sql = "select lng,lat,id from device ";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            System.out.println("success");
            while (rs.next()) {
                Double log = rs.getDouble(1);
                Double lat = rs.getDouble(2);
                int id = rs.getInt(3);
                //System.out.println(log + ", " + lat);
                String add = method.GetLocation.getAdd(String.valueOf(log), String.valueOf(lat));
                //System.out.println(add);
                JSONObject jsonObject = JSONObject.fromObject(add);
                JSONArray jsonArray = JSONArray.fromObject(jsonObject.getString("addrList"));
                JSONObject j_2 = JSONObject.fromObject(jsonArray.get(0));
                String allAdd = j_2.getString("admName");
                if(!allAdd.equals(""))
                {
                    //System.out.println(allAdd);
                    String arr[] = allAdd.split(",");
                    System.out.println("省:" + arr[0] + "\n市:" + arr[1] + "\n区:" + arr[2]);
                    String location = arr[2];
                    String sql_insert = "update device set location='"+location+"' where id="+id;
                    //String sql_insert = "update device set location='\"+arr[2]+\"' ";
                    stmt_insert = conn.prepareStatement(sql_insert);
                    stmt_insert.executeUpdate();
                    stmt_insert.close();
                }
            }
            //out.flush();
            //out.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                //注意关闭原则：从里到外
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
