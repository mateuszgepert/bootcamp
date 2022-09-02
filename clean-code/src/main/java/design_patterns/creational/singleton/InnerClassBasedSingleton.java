package design_patterns.creational.singleton;

class InnerClassBasedSingleton {

    private InnerClassBasedSingleton() {
        System.out.println("InnerClassBasedSingleton constructor called");
    }

    private static class InnerClass {
        private static final InnerClassBasedSingleton INSTANCE = new InnerClassBasedSingleton();
    }

    public static InnerClassBasedSingleton getInstance() {
        return InnerClass.INSTANCE;
    }

    public static void touchClass() {
        System.out.println("InnerClassBasedSingleton class touched");
    }
}