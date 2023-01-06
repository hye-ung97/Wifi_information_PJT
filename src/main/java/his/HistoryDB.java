package his;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoryDB {
    public static List<History> historySelect(){
        List<History> historyList = new ArrayList();
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

            String sql = "select * from wifi_history order by num desc";
            preparedStatement = connection.prepareStatement(sql);


            rs = preparedStatement.executeQuery();

            while (rs.next()){
                String num = rs.getString("num");
                String lat = rs.getString("lat");
                String lnt = rs.getString("lnt");
                String searchDate = rs.getString("search_date");

                History history = new History();
                history.setNum(Integer.valueOf(num));
                history.setLat(lat);
                history.setLnt(lnt);
                history.setSearchDate(searchDate);

                historyList.add(history);
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
        return historyList;
    }
    public static void historyInsert(String lat, String lnt){
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

            String sql = "insert into wifi_history (lat, lnt, search_date) VALUES (?,?,now());";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, lat);
            preparedStatement.setString(2, lnt);


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
    public static int historyDel(String num) {

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
        int affected = 0;

        try {
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);

            String sql = "delete from wifi_history where num = ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, num);

            affected = preparedStatement.executeUpdate();

            if (affected > 0) {
                System.out.println("삭제 성공");
            } else {
                System.out.println("삭제 실패");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return affected;
    }
}
