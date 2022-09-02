package design_patterns.structural.facade;

class Application {

    public static void main(String[] args) {
        var pdfReportGenerator = new PdfReportGenerator();
        var csvReportGenerator = new CsvReportGenerator();
    }


}
