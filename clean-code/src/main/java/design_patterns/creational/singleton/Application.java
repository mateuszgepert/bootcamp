package design_patterns.creational.singleton;

class Application {

    public static void main(String[] args) {
        System.out.println("Touching classes...");
        EnumBasedSingleton.touchClass();
        LazyBasedSingleton.touchClass();
        InnerClassBasedSingleton.touchClass();
        System.out.println("Done");
        System.out.println("Getting instances");
        var enumBasedSingleton = EnumBasedSingleton.INSTANCE;
        var lazyBasedSingleton = LazyBasedSingleton.getInstance();
        var innerClassBasedSingleton = InnerClassBasedSingleton.getInstance();
        System.out.println("All instances called");
    }
}
