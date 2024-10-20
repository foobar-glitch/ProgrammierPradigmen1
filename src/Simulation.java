import java.util.ArrayList;

public class Simulation {


    private Building building;

    private ArrayList<CostContainer> costsPerYear;
    private ArrayList<Float> happinessPerYear;

    public Simulation(Building building) {
        this.building = building;
        this.costsPerYear = new ArrayList<CostContainer>();
        this.happinessPerYear = new ArrayList<Float>();
    }

    public SimulationResult runSimulation() {
        while (building.checkAge()) {
            CostContainer costsThisYear = new CostContainer(0.0f, 0.0f, 0.0f);
            happinessPerYear.add(building.satisfaction());

            // amount of renovations will be correctly scaled for number of apartment and
            // the time it should take for a full renovation by the gebauede
            // random function can be anything as long as expected value = 1
            float renovations = (float) Math.random();
            costsThisYear.addCostContainer(building.renovate(renovations));

            double randomVal = Math.random();
            if (randomVal < 0.05) {
                if (randomVal < 0.005) {
                    costsThisYear.addCostContainer(building.demolishing());
                    costsPerYear.add(costsThisYear);
                    break;
                }
                costsThisYear.addCostContainer(building.renovate(0.9f));
            }

            costsThisYear.addCostContainer(building.age());
            costsPerYear.add(costsThisYear);
        }
        return new SimulationResult(costsPerYear, happinessPerYear);
    }

}
