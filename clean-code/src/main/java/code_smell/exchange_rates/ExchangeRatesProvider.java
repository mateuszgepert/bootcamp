package code_smell.exchange_rates;

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

        // currency conversion is found here
        //https://fastspring.com/blog/how-to-format-30-currencies-from-countries-all-over-the-world/
        //currently we support only currencies EUR, USD, CAD, JPY
/*
*
* Abbreviation/Code: CAD
* Symbol: $
* Example: $ 1,234.56
*
* Abbreviation/Code: EUR
* Symbol: €
* Example: €1.234,56
*
* Abbreviation/Code: JPY
* Symbol: ¥
* Example: ¥ 1,234.56
*
* Abbreviation/Code: USD
* Symbol: $
* Example: $1,234.56
*
* Abbreviation/Code: MXN
* Symbol: $
* Example: $ 1,234.56
*
* Abbreviation/Code: SEK
* Symbol: kr
* Example: 1.234,56 kr
*
* */
        Double amount = null;
        if(!rate.equals("NOT_COUND")) {
            amount = Double.valueOf(rate) * PLN;
        }
        StringBuffer sb = new StringBuffer();
        if(cur.equals("CAD") || cur.equals("USD") || cur.equals("MXN") || cur.equals("JPY")) {
            if (cur.equals("JPY")) {
                sb.append("¥ ");
            } else {
                sb.append("$ ");
            }
            if (amount > 999) {
                char[] s = amount.toString().toCharArray();
                sb.append(s[0]);
                sb.append(",");
                for (int i = 1; i < s.length; i++) {
                    sb.append(s[i]);
                }
            }
            sb.append(amount);
        }
        else if(cur.equals("EUR") || cur.equals("SEK")) {
            if(cur.equals("EUR")) {
                sb.append("€");
            } else {
                //do nothing we want currency symbol in the end
            }
            if(amount < 1000) {
                int dot = 0;
                char[] s = amount.toString().toCharArray();
                for (int i = 0; i < s.length; i++) {
                    if(dot == 3) {
                        break;
                    } else if(dot >= 1){
                        dot++;
                    }
                    if(s[i] == '.') {
                        sb.append(',');
                        dot++;
                    } else {
                        sb.append(s[i]);
                    }
                }
            }
            if(amount > 999) {
                int dot = 0;
                char[] s = amount.toString().toCharArray();
                sb.append(s[0]);
                sb.append(".");
                for (int i = 1; i < s.length; i++) {
                    if(dot == 3) {
                        break;
                    } else if(dot >= 1){
                        dot++;
                    }
                    if(s[i] == '.') {
                        sb.append(',');
                        dot++;
                    } else {
                        sb.append(s[i]);
                    }
                }
            }
            if(cur.equals("SEK")) {
                char[] temp = sb.toString().replace("€", "").toCharArray();
                sb = new StringBuffer();
                for (int i = 0; i < temp.length; i++) {
                    sb.append(temp[i]);
                }
                sb.append(" kr");

            }
        } else {
            throw new IOException("currency is not supported");
        }
        String resultString = sb.toString();

        if(resultString.contains("USD")) {
            resultString = resultString.replace(" ", "");
        }

        if(!(cur.equals("EUR") || cur.equals("SEK"))) {
            int end = resultString.indexOf(".")+3;
            resultString = resultString.substring(0, end);
        }

        return resultString;
    }

    public static void main(String[] args) throws IOException {
        ExchangeRatesProvider exchangeRatesProvider = new ExchangeRatesProvider();
        System.out.println(exchangeRatesProvider.printMoney("EUR", 23453452));
    }
}
