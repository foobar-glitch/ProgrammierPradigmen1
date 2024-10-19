public class CostContainer {
    /**
     * This is a container for all the costs both financial
     * and environmental.
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

    /**
     * This adds another CostContainer on the current one
     * @param other The other CostContainer that it is summing with
     */
    public void addCostContainer(CostContainer other){
        cost += other.getCost();
        co2 += other.getCo2();
        waste += other.getWaste();
    }

    public void addFinancialCost(float amount){
        this.cost += amount;
    }

    public void addCo2(float amount){
        this.co2 += amount;
    }

    public void addWaste(float amount){
        this.waste += amount;
    }

    public float getCost(){
        return cost;
    }

    public float getCo2(){
        return co2;
    }

    public float getWaste(){
        return waste;
    }
}
