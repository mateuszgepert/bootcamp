package code_smell.currency.gateway;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;

class RatesClient {

    Optional<String> getRatesResponse(String currency) {
        return getRatesFromExternalClient("https://api.nbp.pl/api/exchangerates/tables/a/")
                .filter(response -> response.contains(currency))
                .or(() -> getRatesFromExternalClient("https://api.nbp.pl/api/exchangerates/tables/b/"));
    }

    private Optional<String> getRatesFromExternalClient(String address) {
        try {
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
            return Optional.of(buf.toString("UTF-8"));
        } catch (IOException e) {
            System.out.println("ERROR " + e.getMessage());
            return Optional.empty();
        }
    }
}
