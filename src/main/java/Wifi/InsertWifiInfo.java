package Wifi;
import org.json.simple.JSONObject;
import java.sql.*;

public class InsertWifiInfo {
    public static void Insert(JSONObject jsonObject){
        String url = "jdbc:mariadb://127.0.0.1:3306/wifi_db";
        String dbUserId = "test";
        String dbPassword = "1234";

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection = DriverManager.getConnection(url,dbUserId, dbPassword);

            String sql = "insert into wifi (X_SWIFI_MGR_NO, X_SWIFI_WRDOFC, X_SWIFI_MAIN_NM, " +
                    "X_SWIFI_ADRES1, X_SWIFI_ADRES2, X_SWIFI_INSTL_FLOOR, X_SWIFI_INSTL_TY, X_SWIFI_INSTL_MBY, " +
                    "X_SWIFI_SVC_SE, X_SWIFI_CMCWR, X_SWIFI_CNSTC_YEAR, X_SWIFI_INOUT_DOOR, X_SWIFI_REMARS3, " +
                    "LAT, LNT, WORK_DTTM) \n" +
                    "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, (String) jsonObject.get("X_SWIFI_MGR_NO"));
            preparedStatement.setString(2, (String) jsonObject.get("X_SWIFI_WRDOFC"));
            preparedStatement.setString(3, (String) jsonObject.get("X_SWIFI_MAIN_NM"));
            preparedStatement.setString(4, (String) jsonObject.get("X_SWIFI_ADRES1"));
            preparedStatement.setString(5, (String) jsonObject.get("X_SWIFI_ADRES2"));
            preparedStatement.setString(6, (String) jsonObject.get("X_SWIFI_INSTL_FLOOR"));
            preparedStatement.setString(7, (String) jsonObject.get("X_SWIFI_INSTL_TY"));
            preparedStatement.setString(8, (String) jsonObject.get("X_SWIFI_INSTL_MBY"));
            preparedStatement.setString(9, (String) jsonObject.get("X_SWIFI_SVC_SE"));
            preparedStatement.setString(10, (String) jsonObject.get("X_SWIFI_CMCWR"));
            preparedStatement.setString(11, (String) jsonObject.get("X_SWIFI_CNSTC_YEAR"));
            preparedStatement.setString(12, (String) jsonObject.get("X_SWIFI_INOUT_DOOR"));
            preparedStatement.setString(13, (String) jsonObject.get("X_SWIFI_REMARS3"));
            preparedStatement.setString(14, (String) jsonObject.get("LAT"));
            preparedStatement.setString(15, (String) jsonObject.get("LNT"));
            preparedStatement.setString(16, (String) jsonObject.get("WORK_DTTM"));

            int affected = preparedStatement.executeUpdate();

            if(affected > 0){
                System.out.println("저장 성공");
            }else {
                System.out.println("저장 실패");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if(rs != null && !rs.isClosed()){
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if(preparedStatement != null && !preparedStatement.isClosed()){
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if(connection != null && !connection.isClosed()){
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void Delete(){
        String url = "jdbc:mariadb://127.0.0.1:3306/wifi_db";
        String dbUserId = "test";
        String dbPassword = "1234";

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection = DriverManager.getConnection(url,dbUserId, dbPassword);

            String sql = "delete from wifi;";
            preparedStatement = connection.prepareStatement(sql);

            int affected = preparedStatement.executeUpdate();

            if(affected > 0){
                System.out.println("성공");
            }else {
                System.out.println("실패");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if(rs != null && !rs.isClosed()){
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if(preparedStatement != null && !preparedStatement.isClosed()){
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if(connection != null && !connection.isClosed()){
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
