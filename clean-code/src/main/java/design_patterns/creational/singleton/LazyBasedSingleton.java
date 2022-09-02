package design_patterns.creational.singleton;

class LazyBasedSingleton {

    private static volatile LazyBasedSingleton instance;

    private LazyBasedSingleton() {
        System.out.println("LazyBasedSingleton called");
    }

    public static LazyBasedSingleton getInstance() {
        if (instance == null) {
            synchronized (LazyBasedSingleton.class) {
                if (instance == null) {
                    instance = new LazyBasedSingleton();
                }
            }
        }
        return instance;
    }

    public static void touchClass() {
        System.out.println("LazyBasedSingleton class touched");
    }
}