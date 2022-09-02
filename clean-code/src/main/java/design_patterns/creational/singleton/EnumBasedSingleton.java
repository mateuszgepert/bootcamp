package design_patterns.creational.singleton;

enum EnumBasedSingleton {
    INSTANCE;

    EnumBasedSingleton() {
        System.out.println("EnumBasedSingleton called");
    }

    public static void touchClass() {
        System.out.println("EnumBasedSingleton class touched");
    }
}
