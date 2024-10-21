/*
hier ueber die Aufteilung der Arbeit etc. schreiben

Julian - Apartments, ApartmentSpecs, MaterialBag

Marian - Main, Simulation, SimulationResult

Hamed - CostContainer, Building, Material

 */

public class Test {

    public static void main(String[] args) {
        Main mainFunction = new Main();
        Material[] materials = new Material[] {
                new Material("wood", new CostContainer(125.0, 0.4, 0.01)),
                new Material("concrete", new CostContainer(250.0, 0.8, 0.15)),
                new Material("steel", new CostContainer(1200.0, 1.4, 0.007))
        };
        int numberOfApartments = 10, lifetimeBuilding = 50;
        MaterialBag shellEco = new MaterialBag(materials, new Double[] {257.3, 10.0, 2.45});
        MaterialBag  interiorEco = new MaterialBag(materials, new Double[] {14.4, 2.0, 0.875});
        CostContainer heatingAndMaintenanceCostsEco = new CostContainer(0.35, 0.3, 0.05);
        ApartmentSpecs apartmentsEco = new ApartmentSpecs(interiorEco, 1, numberOfApartments, 20,0.9);
        BuildingSpecs buildingEco = new BuildingSpecs(lifetimeBuilding, shellEco, apartmentsEco, heatingAndMaintenanceCostsEco,0.7f);

        Simulation simulation = new Simulation(buildingEco, buildingEco.getApartmentSpecs());
        SimulationResult simResult = simulation.runSimulation();


        /* Calculate Lower and Upper Bounds for the costs of a building in simulation */
        CostContainer shellCosts = shellEco.getTotalCost();
        CostContainer interiorCosts = interiorEco.getTotalCost();

        CostContainer costLowerBound = shellCosts.addCostContainer(
                interiorCosts.multiplyContainer(numberOfApartments)
        );

        CostContainer costUpperBound = shellCosts.addCostContainer(
                interiorCosts.multiplyContainer(numberOfApartments*lifetimeBuilding)
        );



    }
}