/**
 * This class defines the material and its properties
 */
public class Material {
    /* Cost of material normalized for a ton */
    private CostContainer acquisitionCost;
    /* Cost of demolishing the material normalized to a ton */
    private CostContainer demolishCost;

    /**
     *
     * @param acquisitionCost cost of the acquisition of the material
     * @param demolishCost cost for demolishing the material
     */
    public Material(CostContainer acquisitionCost, CostContainer demolishCost) {
        //float cost, float co2, float deteriorationRate
        this.acquisitionCost = acquisitionCost;
        this.demolishCost = demolishCost;
    }

    public CostContainer getCost() {
        return acquisitionCost;
    }

    public CostContainer getDemolishingCost(){
        return demolishCost;
    }


}
