public class CostContainer {
    /**
     * This is a container for all the costs both financial
     * and environmental.
     *
     * cost financial cost of an object
     * */
    /* Costs of material*/
    private float cost;
    /* Usage of co2*/
    private float co2;
    /* Amount of waste created */
    private float waste;

    public CostContainer(float cost, float co2, float waste) {
        this.cost = cost;
        this.co2 = co2;
        this.waste = waste;

    }

}
