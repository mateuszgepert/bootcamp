package design_patterns.structural.adapter;

import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
class ImagePrinter {

    private static final int PRINT_SIZE = 1024;

    private final UglyAndLowLevelOfImageTransformingAPI api;

    Picture print(Image image) {
        var resized = resize(image);
        return convertToPrintableMap(image);
    }

    private Picture convertToPrintableMap(Image image) {
        return new Picture(Map.of());
    }

    private Image resize(Image image) {
        //see the pain of implementing
        return image;
    }

    record Picture(Map<String, String> map) {

    }

}
