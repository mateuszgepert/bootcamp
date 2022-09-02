package solid.d;

class BikeFactory {

    //what x
    //who x
    //where x
    //how x

    Bike createBike() {
        Wheels wheels = new Wheels();
        BikeFrame frame = new BikeFrame();
        DriveUnit driveUnit = new DriveUnit();

        var bike = new Bike(wheels, frame, driveUnit);

        if(!bike.rides()) {
            fix(bike);
        }

        return bike;
    }

    private void fix(Bike bike) {

    }

}
