/**
 * This class defines the material and its properties
 */
public class Material {
    /* Cost of material normalized for a ton */
    private CostContainer acquisitionCost;

    /**
     *
     * @param acquisitionCost cost of the acquisition of the material
     */
    public Material(CostContainer acquisitionCost) {
        //double cost, double co2, double deteriorationRate
        this.acquisitionCost = acquisitionCost;
    }

    public CostContainer getCost() {
        return acquisitionCost;
    }

}
