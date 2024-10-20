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

    /**
     *
     * @param material The material we are using
     * @param amount The amount of material we are defining in tons
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
    public CostContainer calculateTotalCosts(){
        CostContainer totalCost = new CostContainer(0f, 0f, 0f);
        for (Material material : materialInventory.keySet()) {
            CostContainer cost = material.getCost();
            cost.multiplyContainer(materialInventory.get(material));
            totalCost.addCostContainer(cost);
        }
        return totalCost;
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

    /**
     * @param bag MaterialBag which should be added
     * @return New MaterialBag made out of combining this and bag
     */
    public MaterialBag add(MaterialBag bag){
        MaterialBag tmp = copy();
        for(Material m : bag.materialInventory.keySet()){
            if(tmp.materialInventory.containsKey(m))
                tmp.addMaterial(m, bag.materialInventory.get(m));
            else
                tmp.setMaterial(m, bag.materialInventory.get(m));
        }
        return tmp;
    }


    /**
     * @return New MaterialBag containing all Waste-Materials
     */
    public MaterialBag getWaste(){
        MaterialBag tmp = new MaterialBag();

        for(Material m : materialInventory.keySet()){
            tmp.setMaterial(
                    m,
                    materialInventory.get(m)*m.getCost().getWaste()
            );
        }

        return tmp;
    }

    /**
     * @return Number of Materials inside MaterialBag
     */
    public int size(){
        int size = 0;
        for(Material m: materialInventory.keySet()){
            if(materialInventory.get(m) > 0) size++;
        }
        return size;
    }

    public CostContainer getTotalCost() { return calculateTotalCosts(); }

    public HashMap<Material, Float> getMaterialInventory() { return materialInventory; }
}
