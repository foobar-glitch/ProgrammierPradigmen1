import java.util.HashMap;

/**
 * This stores all materials and their corresponding amounts
 *
 * */
public class MaterialBag {
    /* A hashmap of the material and its amount */
    private HashMap<Material, Float> materialInventory;

    public MaterialBag() {
        this.materialInventory = new HashMap<>();
    }

    public void addMaterial(Material material, float amount) {
        this.materialInventory.put(material, amount);
    }

    /**
     * This functions calculates the cost of all materials and costs of the
     * current instance
     *
     * @return Costs of MaterialBag
     */
    public float calculateTotalCosts(){
        float totalCost = 0;
        for (Material material : materialInventory.keySet()) {
            totalCost += materialInventory.get(material);
        }
        return totalCost;
    }

}
