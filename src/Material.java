public class Material {
    /**
     * Calculates the sum of two integers.
     *
     * @param cost Costs of material per ton
     * @param co2Usage Usage of co2 per ton of material
     * @param wasteRate Rate at which the material deteriorates per year
     * */

    private float cost;
    private float co2Usage;
    private float wasteRate;


    public Material(float cost, float co2Usage, float wasteRate) {
        this.cost = cost;
        this.co2Usage = co2Usage;
        this.wasteRate = wasteRate;
    }


}
