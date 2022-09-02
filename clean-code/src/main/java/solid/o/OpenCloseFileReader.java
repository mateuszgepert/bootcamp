package solid.o;

import java.io.File;
import java.util.List;
import java.util.Optional;

public class OpenCloseFileReader {

    List<FileToStringParser> fileToStringParsers;

    Optional<String> read(File file) {
        return fileToStringParsers
                .stream()
                .filter(parser -> parser.supports(file))
                .findAny()
                .map(parser -> parser.parse(file));
    }

}

interface FileToStringParser {

    boolean supports(File file);

    String parse(File file);
}

class CsvParser implements FileToStringParser {

    @Override
    public boolean supports(File file) {
        return file.getName().endsWith("csv");
    }

    @Override
    public String parse(File file) {
        //csv type file parsing
        return "";
    }
}

class XmlParser implements FileToStringParser {

    @Override
    public boolean supports(File file) {
        return file.getName().endsWith("xml");
    }

    @Override
    public String parse(File file) {
        //xml type file parsing
        return "";
    }
}

class JsonParser implements FileToStringParser {

    @Override
    public boolean supports(File file) {
        return file.getName().endsWith("json");
    }

    @Override
    public String parse(File file) {
        //json type file parsing
        return "";
    }
}

class YmlParser implements FileToStringParser {
    @Override
    public boolean supports(File file) {
        return file.getName().endsWith("yml");
    }

    @Override
    public String parse(File file) {
        //parse byte code
        return "";
    }
}
