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
    public CostContainer addCostContainer(CostContainer other){
        CostContainer tmp = new CostContainer(cost, co2, waste);

        tmp.cost += other.getCost();
        tmp.co2 += other.getCo2();
        tmp.waste += other.getWaste();
        return tmp;
    }

    public CostContainer subtractCostContainer(CostContainer other){
        CostContainer tmp = new CostContainer(cost, co2, waste);

        tmp.cost -= other.getCost();
        tmp.co2 -= other.getCo2();
        tmp.waste -= other.getWaste();
        return tmp;

    }

    /**
     * Multiplying all costs by the amount of material
     * @param amount of the material
     */
    public CostContainer multiplyContainer(float amount){
        CostContainer tmp = new CostContainer(cost, co2, waste);

        tmp.cost *= amount;
        tmp.co2 *= amount;
        tmp.waste *= amount;
        return tmp;

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
