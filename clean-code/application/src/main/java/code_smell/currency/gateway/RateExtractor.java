package code_smell.currency.gateway;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

class RateExtractor {

    Optional<String> getRateForCurrency(String currency, String response) {
        List l = ((JSONArray) ((JSONObject) new JSONArray(response).get(0)).get("rates")).toList();
        for (int i = 0; i < l.size(); i++) {
            if (((HashMap) l.get(i)).get("code").equals(currency)) {
                return Optional.of(String.valueOf((Double) ((HashMap) l.get(i)).get("mid")));
            }
        }
        return Optional.empty();
    }
}
