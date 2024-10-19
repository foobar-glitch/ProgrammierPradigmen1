/**
 * This class defines the material and its properties
 */
public class Material {
    /* Cost of material normed for a ton */
    private CostContainer cost;


    public Material(float cost, float co2, float waste) {
        this.cost = new CostContainer(cost, co2, waste);
    }


}
