import java.util.ArrayList;

public class Simulation {


    private Building building;

    private ArrayList<CostContainer> costsPerYear;
    private ArrayList<Double> happinessPerYear;

    public Simulation(Building building) {
        this.building = building;
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
<<<<<<< HEAD
            float renovations = (float) Math.random();
            costsThisYear = costsThisYear.addCostContainer(building.renovate(renovations));
=======
            double renovations = (double) Math.random();
            costsThisYear.addCostContainer(building.renovate(renovations));
>>>>>>> 53628ceb640aff10c414939d5e8926f232e9d3b3
            System.out.println("CO2_3: " + costsThisYear.getCo2());

            double randomVal = Math.random();
            if (randomVal < 0.05) {
                if (randomVal < 0.005) {
                    costsThisYear = costsThisYear.addCostContainer(building.demolishing());
                    System.out.println("CO2_4: " + costsThisYear.getCo2());
                    costsPerYear.add(costsThisYear);
                    break;
                }
                costsThisYear = costsThisYear.addCostContainer(building.renovate(0.9f));
                System.out.println("CO2_5: " + costsThisYear.getCo2());
            }

            costsThisYear = costsThisYear.addCostContainer(building.age());

            costsPerYear.add(costsThisYear);
            System.out.println("CO2_6: " + costsThisYear.getCo2());
        }
        return new SimulationResult(costsPerYear, happinessPerYear);
    }

}
