/**
 * This class defines the material and its properties
 */
public class Material {
    /* Cost of material normed for a ton */
    private CostContainer cost;

    /**
     *
     * @param cost financial cost of one ton of material
     * @param co2 co2 usage of on ton of material
     * @param waste waste production per ton of material and year
     */
    public Material(float cost, float co2, float waste) {
        this.cost = new CostContainer(cost, co2, waste);
    }

    public CostContainer getCost() {
        return cost;
    }

}
