package Wifi;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
import java.util.*;


public class WifiInfo {
    static final String KEY = "4a42706c4d687973313333695a694f67";
    static int TOTALCNT;

    public static int TotalCnt() throws ParseException {
        URL url = null;
        HttpURLConnection con= null;
        JSONObject result = null;
        StringBuilder sb = new StringBuilder();
        int start = 1;
        int end = 1;
        String baseUrl = "http://openapi.seoul.go.kr:8088/" + KEY + "/" +
                "json/TbPublicWifiInfo/"+ start + "/"+end+"/";

        try {
            url = new URL(baseUrl);
            con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");

            con.setRequestProperty("Content-type", "application/json");

            con.setDoOutput(true);

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            while(br.ready()) {
                sb.append(br.readLine());
            }
            con.disconnect();
        }catch(Exception e) {
            e.printStackTrace();
        }

        result = (JSONObject) new JSONParser().parse(sb.toString());

        StringBuilder out = new StringBuilder();

        JSONObject data = (JSONObject) result.get("TbPublicWifiInfo");
        int totalGet = Integer.parseInt( data.get("list_total_count").toString());

        return totalGet;
    }

    public static int AddWifi() throws ParseException{
        int start = 0;
        int end = 0;
        int total = 0;

        TOTALCNT = TotalCnt();
        int pageEnd = TOTALCNT / 1000;
        int pageEndRemain = TOTALCNT % 1000;

        for (int k = 0; k <= pageEnd; k++) {
            start = (int) Math.pow(10, 3) * k + 1;

            if(k == pageEnd){
                end = start + pageEndRemain - 1;
            }
            else{
                end = (int) Math.pow(10, 3) * (k+1) ;
            }

            String baseUrl = "http://openapi.seoul.go.kr:8088/" + KEY + "/" +
                    "json/TbPublicWifiInfo/";

            baseUrl = baseUrl + start + "/" + end + "/";

            URL url = null;
            HttpURLConnection con= null;
            JSONObject result = null;
            StringBuilder sb = new StringBuilder();


            try {
                url = new URL(baseUrl);
                con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("Content-type", "application/json");
                con.setDoOutput(true);


                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
                while(br.ready()) {
                    sb.append(br.readLine());
                }
                con.disconnect();
            }catch(Exception e) {
                e.printStackTrace();
            }

            result = (JSONObject) new JSONParser().parse(sb.toString());

            JSONObject data = (JSONObject) result.get("TbPublicWifiInfo");
            JSONArray array = (JSONArray) data.get("row");

            JSONObject tmp;

            for(int i = 0; i<array.size(); i++) {
                tmp = (JSONObject) array.get(i);
                InsertWifiInfo insertWifiInfo = new InsertWifiInfo();
                if(total == 0){
                    insertWifiInfo.Delete();
                }
                insertWifiInfo.Insert(tmp);
                total++;
            }
        }
        return total;
    }

    public static List<WifiDetail> SearchWifi(String lat, String lnt){
        List<WifiDetail> wifiList = new ArrayList();
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

        String memberTypeValue = "email";

        try {
            connection = DriverManager.getConnection(url,dbUserId, dbPassword);
            String sql = "select * from wifi";

            preparedStatement = connection.prepareStatement(sql);
            rs = preparedStatement.executeQuery();

            while (rs.next()){

                WifiDetail detail = new WifiDetail();
                detail.setX_SWIFI_MGR_NO(rs.getString("X_SWIFI_MGR_NO"));
                detail.setX_SWIFI_WRDOFC(rs.getString("X_SWIFI_WRDOFC"));
                detail.setX_SWIFI_MAIN_NM(rs.getString("X_SWIFI_MAIN_NM"));
                detail.setX_SWIFI_ADRES1(rs.getString("X_SWIFI_ADRES1"));
                detail.setX_SWIFI_ADRES2(rs.getString("X_SWIFI_ADRES2"));
                detail.setX_SWIFI_INSTL_FLOOR(rs.getString("X_SWIFI_INSTL_FLOOR"));
                detail.setX_SWIFI_INSTL_TY(rs.getString("X_SWIFI_INSTL_TY"));
                detail.setX_SWIFI_INSTL_MBY(rs.getString("X_SWIFI_INSTL_MBY"));
                detail.setX_SWIFI_SVC_SE(rs.getString("X_SWIFI_SVC_SE"));
                detail.setX_SWIFI_CMCWR(rs.getString("X_SWIFI_CMCWR"));
                detail.setX_SWIFI_CNSTC_YEAR(rs.getString("X_SWIFI_CNSTC_YEAR"));
                detail.setX_SWIFI_INOUT_DOOR(rs.getString("X_SWIFI_INOUT_DOOR"));
                detail.setX_SWIFI_REMARS3(rs.getString("X_SWIFI_REMARS3"));
                detail.setLAT(rs.getString("LAT"));
                detail.setLNT(rs.getString("LNT"));
                detail.setWORK_DTTM(rs.getString("WORK_DTTM"));

                double lat2 = Double.parseDouble(rs.getString("LAT"));
                double lnt2 = Double.parseDouble(rs.getString("LNT"));

                CalDistance newdis = new CalDistance();
                double dis = newdis.distance(Double.parseDouble(lat), Double.parseDouble(lnt), lat2, lnt2);
                detail.setDis(dis);

                wifiList.add(detail);
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

        //wifiList.sort((x,y) -> (int) (x.getDis() - y.getDis()));
        Collections.sort(wifiList, new Comparator<WifiDetail>() {
            @Override
            public int compare(WifiDetail o1, WifiDetail o2) {
                if(o1.getDis() > o2.getDis()){
                    return 1;
                }
                else if(o1.getDis() == o2.getDis()){
                    return 0;
                }
                else{
                    return -1;
                }
            }
        });

        return wifiList;
    }

}
