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

    public String printMoney(String currency) throws IOException {
        var response = getRatesResponse(currency);
        var rate = getRateForCurrency(currency, response);

        if (rate.isEmpty()) {
            return "NOT_FOUND";
        }
        return rate;
    }

    private String getRatesResponse(String currency) throws IOException {
        var response = getRatesFromExternalClient("https://api.nbp.pl/api/exchangerates/tables/a/");
        if (response.contains(currency)) {
            return response;
        }
        return getRatesFromExternalClient("https://api.nbp.pl/api/exchangerates/tables/b/");
    }

    private String getRateForCurrency(String currency, String response) {
        List l = ((JSONArray) ((JSONObject) new JSONArray(response).get(0)).get("rates")).toList();
        for (int i = 0; i < l.size(); i++) {
            if (((HashMap) l.get(i)).get("code").equals(currency)) {
                return String.valueOf((Double) ((HashMap) l.get(i)).get("mid"));
            }
        }
        return "";
    }

    private String getRatesFromExternalClient(String address) throws IOException {
        URL url = new URL(address);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        //read response
        InputStream is = con.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(is);
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        for (int result = bis.read(); result != -1; result = bis.read()) {
            buf.write((byte) result);
        }
        return buf.toString("UTF-8");
    }

}