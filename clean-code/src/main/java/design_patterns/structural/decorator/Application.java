package design_patterns.structural.decorator;

class Application {

    public static void main(String[] args) {

        var client = new ThirdPartyService(new Metrics());
        System.out.println(client.getData());

    }
}
