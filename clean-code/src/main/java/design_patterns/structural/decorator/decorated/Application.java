package design_patterns.structural.decorator.decorated;

class Application {

    public static void main(String[] args) {

        var thirdPartyClient = new RestThirdPartyService();
        var meteredThirdPartyClient = new MeteredThirdPartyClient(thirdPartyClient, new Metrics());
        var data = meteredThirdPartyClient.getData();

        System.out.println(data);

    }
}
