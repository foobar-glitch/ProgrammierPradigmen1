public class Material {
    /**
     * Calculates the sum of two integers.
     *
     * @param amount Amount of material in tonnes
     * @param cost Costs of material per ton
     * @param co2Usage Usage of co2 per ton of material
     * @param wasteRate Rate at which the material deteriorates per year
     * */

    float amount;
    float cost;
    float co2Usage;
    float wasteRate;


    public Material(float amount, float cost, float co2Usage, float wasteRate) {
        this.amount = amount;
        this.cost = cost;
        this.co2Usage = co2Usage;
        this.wasteRate = wasteRate;
    }


}
