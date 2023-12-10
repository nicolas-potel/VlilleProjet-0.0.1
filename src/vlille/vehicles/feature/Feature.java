package vlille.vehicles.feature;
import vlille.vehicles.*;

public abstract class Feature {

    /* the vehicle that has the feature */
    private Vehicle vehicle;


    /** The constructor for Feature class*/
    public Feature(Vehicle vehicle) {
        this.vehicle = vehicle;
        vehicle.addFeature(this);
    }

}
