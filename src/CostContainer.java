public class CostContainer {
    /**
     * This is a container for all the costs both financial
     * and environmental.
     * */
    /* Costs of material*/
    private double cost;
    /* Usage of co2*/
    private double co2;
    /* Amount of waste created */
    private double waste;

    public CostContainer(double cost, double co2, double waste) {
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

    public void subtractCostContainer(CostContainer other){
        cost -= other.getCost();
        co2 -= other.getCo2();
        waste -= other.getWaste();
    }

    /**
     * Multiplying all costs by the amount of material
     * @param amount of the material
     */
    public void multiplyContainer(double amount){
        cost *= amount;
        co2 *= amount;
        waste *= amount;

    }

    public void addFinancialCost(double amount){
        this.cost += amount;
    }

    public void addCo2(double amount){
        this.co2 += amount;
    }

    public void addWaste(double amount){
        this.waste += amount;
    }

    public double getCost(){
        return cost;
    }

    public double getCo2(){
        return co2;
    }

    public double getWaste(){
        return waste;
    }
}
