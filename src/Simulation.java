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
            costsThisYear.addCostContainer(building.betriebskosten());
            happinessPerYear.add(building.zufriedenheit());

            // amount of renovations will be correctly scaled for number of apartment and
            // the time it should take for a full renovation by the gebauede
            // random function can be anything as long as expected value = 1
            float renovations = (float) (Math.random() * 2.0);
            costsThisYear.addCostContainer(building.renovieren(renovations));

            double randomVal = Math.random();
            if (randomVal < 0.05) {
                if (randomVal < 0.005) {
                    break;
                }
                // TODO replace/make up formula
                costsThisYear.addCostContainer(building.renovieren(10));
            }

            costsPerYear.add(costsThisYear);

            building.age();
        }

        costsPerYear.get(0).addCostContainer(building.hausbauen());
        costsPerYear.get(costsPerYear.size() - 1).addCostContainer(building.abriss());

        return new SimulationResult(costsPerYear, happinessPerYear);
    }

}
