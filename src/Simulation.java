import java.util.ArrayList;

// contains state of the simulation and runs it
// updates state with each time step accordingly
// returns the values that we are measurement for
// nominal abstraction: simulates the specific simulation that'S specified in the assignment
// and returns measurements for a buildings sustainability
public class Simulation {


    private Building building;

    private ArrayList<CostContainer> costsPerYear;
    private ArrayList<Double> happinessPerYear;

    public Simulation(BuildingSpecs buildingSpecs, ApartmentSpecs apartmentSpecs) {
        this.building = new Building(buildingSpecs, apartmentSpecs);
        this.costsPerYear = new ArrayList<CostContainer>();
        this.happinessPerYear = new ArrayList<Double>();
    }

    // run the simulation with the parameters that have been specified in the objects initialization
    // nominal abstraction: behaviour of the simulation models what the assignment's text described
    public SimulationResult runSimulation() {
        while (building.checkAge()) {
            CostContainer costsThisYear = new CostContainer(0.0f, 0.0f, 0.0f);
            happinessPerYear.add(building.satisfaction());

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
