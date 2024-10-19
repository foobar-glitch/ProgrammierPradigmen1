import java.util.HashMap;

/**
 * This stores all materials and their corresponding amounts
 *
 * */
public class MaterialBag {
    /* A hashmap of the material and its amount */
    private HashMap<Material, Float> materialInventory;
    private CostContainer totalCost;

    public MaterialBag() {
        this.materialInventory = new HashMap<>();
    }

    /**
     *
     * @param material The material we are using
     * @param amount The amount of material we are defining
     */
    public void setMaterial(Material material, float amount) {
        this.materialInventory.put(material, amount);
    }

    public void subtractMaterial(Material material, float subtractAmount) {
        float newAmount = this.materialInventory.get(material) - subtractAmount;
        this.materialInventory.put(material, newAmount);
    }

    public void addMaterial(Material material, float addAmount) {
        float newAmount = this.materialInventory.get(material) + addAmount;
        this.materialInventory.put(material, newAmount);
    }

    /**
     * This functions calculates the cost of all materials and costs of the
     * current instance
     *
     * @return Costs of MaterialBag
     */
    public void calculateTotalCosts(){
        CostContainer cost;
        for (Material material : materialInventory.keySet()) {
            cost = material.getCost();
            cost.multiplyContainer(materialInventory.get(material));
            totalCost.addCostContainer(cost);
        }
    }

    /**
     * This function makes a copy of the object in which the amount of every
     * Material is changed by the factor in param multiplier
     *
     * @param multiplier Factor with which the amount of every material is multiplied
     * @return New, copied and (amount by multiplier) changed MaterialBag-Object
     */
    public MaterialBag times(float multiplier){
        MaterialBag tmp = new MaterialBag();
        for(Material m : materialInventory.keySet()){
            tmp.setMaterial(
                    m,
                    materialInventory.get(m)*multiplier
            );
        }
        return tmp;
    }

    /**
     * Copies an MaterialBag-Object
     * @return Exact Copy of original Object
     */
    public MaterialBag copy(){
        MaterialBag tmp = new MaterialBag();
        for(Material m : materialInventory.keySet()){
            tmp.setMaterial(
                    m,
                    materialInventory.get(m)
            );
        }
        return tmp;
    }

    public CostContainer getTotalCost() {
        return totalCost;
    }

    public HashMap<Material, Float> getMaterialInventory() {
        return materialInventory;
    }
}
