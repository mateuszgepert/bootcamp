package code_smell;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

public class ExchangeRatesProvider {

    HttpURLConnection con;
    InputStream is;
    BufferedInputStream bis = new BufferedInputStream(is);
    ByteArrayOutputStream buf = new ByteArrayOutputStream();

    public String printMoney(String cur, double PLN) throws IOException {
        String rate = null;
        URL url = new URL("https://api.nbp.pl/api/exchangerates/tables/a/");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        //read response
        InputStream is = con.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(is);
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        for (int result = bis.read(); result != -1; result = bis.read()) {
            buf.write((byte) result);
        }
        String response = buf.toString("UTF-8");
        if (response.contains(cur)) {
            List l = ((JSONArray) ((JSONObject) new JSONArray(response).get(0)).get("rates")).toList();
            for (int i = 0; i < l.size(); i++) {
                if (((HashMap) l.get(i)).get("code").equals(cur)) {
                    rate = String.valueOf((Double) ((HashMap) l.get(i)).get("mid"));
                }
            }
        } else {
            con = (HttpURLConnection) new URL("https://api.nbp.pl/api/exchangerates/tables/b/").openConnection();
            con.setRequestMethod("GET");
            //read response
            bis = new BufferedInputStream(con.getInputStream());
            buf = new ByteArrayOutputStream();
            for (int result = bis.read(); result != -1; result = bis.read()) {
                buf.write((byte) result);
            }
            response = buf.toString("UTF-8");
            if (response.contains(cur)) {
                List l = ((JSONArray) ((JSONObject) new JSONArray(response).get(0)).get("rates")).toList();
                for (int i = 0; i < l.size(); i++) {
                    if (((HashMap) l.get(i)).get("code").equals(cur)) {
                        rate = String.valueOf((Double) ((HashMap) l.get(i)).get("mid"));
                    }
                }
            } else {
                con = (HttpURLConnection) new URL("https://api.nbp.pl/api/exchangerates/tables/a/").openConnection();
                con.setRequestMethod("GET");
                //read response
                bis = new BufferedInputStream(con.getInputStream());
                buf = new ByteArrayOutputStream();
                for (int result = bis.read(); result != -1; result = bis.read()) {
                    buf.write((byte) result);
                }
                response = buf.toString("UTF-8");
                if (response.contains(cur)) {
                    List l = ((JSONArray) (new JSONObject(response)).get("rates")).toList();
                    if (((HashMap) l.get(0)).get("code").equals(cur)) {
                        rate = String.valueOf((Double) ((HashMap) l.get(0)).get("mid"));
                    } else {
                        rate = "NOT_FOUND";
                    }
                }
            }
        }
        return rate;
    }

    public static void main(String[] args) throws IOException {
        ExchangeRatesProvider exchangeRatesProvider = new ExchangeRatesProvider();
        System.out.println(exchangeRatesProvider.printMoney("EUR", 23453452));
    }
}
