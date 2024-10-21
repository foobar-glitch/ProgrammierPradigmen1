import java.util.ArrayList;

public class Simulation {


    private Building building;

    private ArrayList<CostContainer> costsPerYear;
    private ArrayList<Double> happinessPerYear;

    public Simulation(BuildingConfig config) {
        this.building = new Building(config);
        this.costsPerYear = new ArrayList<CostContainer>();
        this.happinessPerYear = new ArrayList<Double>();
    }

    public SimulationResult runSimulation() {
        while (building.checkAge()) {
            CostContainer costsThisYear = new CostContainer(0.0f, 0.0f, 0.0f);
            happinessPerYear.add(building.satisfaction());

            // amount of renovations will be correctly scaled for number of apartment and
            // the time it should take for a full renovation by the gebauede
            // random function can be anything as long as expected value = 1
            double renovations = (double) Math.random();
            costsThisYear = costsThisYear.addCostContainer(building.renovate(renovations));

            double randomVal = Math.random();
            if (randomVal < 0.05) {
                if (randomVal < 0.005) {
                    costsThisYear = costsThisYear.addCostContainer(building.demolishing());
                    costsPerYear.add(costsThisYear);
                    break;
                }
                costsThisYear = costsThisYear.addCostContainer(building.renovate(0.9f));
            }

            costsThisYear = costsThisYear.addCostContainer(building.age());

            costsPerYear.add(costsThisYear);
        }
        return new SimulationResult(costsPerYear, happinessPerYear);
    }

}
