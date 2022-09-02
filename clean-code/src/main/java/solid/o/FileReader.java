package solid.o;

import java.io.File;
import java.util.Optional;

public class FileReader {

    Optional<String> read(File file) {
        if (file.getName().endsWith("csv")) {
            return Optional.of(parseCsv(file));
        }
        if (file.getName().endsWith("json")) {
            return Optional.of(parseJson(file));
        }
        return Optional.empty();
    }

    private String parseJson(File file) {
        //json parsing
        return "";
    }

    private String parseCsv(File file) {
        //csv parsing
        return "";
    }
}
