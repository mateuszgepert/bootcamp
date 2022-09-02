package solid.l;

public class Application {

    public static void main(String[] args) {
        var service = new SomeVeryComplexService();
        var listProvider = new ComplexListProvider();

        System.out.println(service.performOperation(listProvider.getSomeComplexList()));

        listProvider.switchLists();

        System.out.println(service.performOperation(listProvider.getSomeComplexList()));
    }
}
