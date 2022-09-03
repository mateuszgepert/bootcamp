package design_patterns.structural.adapter;

import lombok.AllArgsConstructor;

@AllArgsConstructor
class PdfCreator {

    private final UglyAndLowLevelOfImageTransformingAPI api;

    Pdf createPdf(Image image) {
        var asPng = convertToPng(image);
        byte[] pdfContent = printPdfFromPng(asPng);
        String headers = headersFromRawPdf(pdfContent);
        return new Pdf(headers, pdfContent);
    }

    private byte[] printPdfFromPng(Image asPng) {
        return new byte[0];
    }

    private Image convertToPng(Image image) {
        //feel the pain of resizing
        return new Image(image.content(), "png");
    }

    private String headersFromRawPdf(byte[] pdfContent) {
        return null;
    }

    record Pdf(String headers, byte[] content) {

    }
}
