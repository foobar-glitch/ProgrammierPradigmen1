
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    static public BuildingSpecs giveBuildSpecs(
            Material[] materials,
            Double[] interiorAmounts,
            Double[] shellAmounts,
            int residentPerApartment,
            int numberOfApartments,
            int lifeTimeApartment,
            int lifeTimeBuilding,
            double happinessUpperBound,
            double recycleRate
    ){
        MaterialBag interiorMaterialInventory = new MaterialBag(materials, interiorAmounts);
        MaterialBag shellMaterialInventory = new MaterialBag(materials, shellAmounts);
        
        ApartmentSpecs apartmentSpec = new ApartmentSpecs(
                interiorMaterialInventory, residentPerApartment, numberOfApartments, lifeTimeApartment, happinessUpperBound
                );
        return new BuildingSpecs(
                lifeTimeBuilding, shellMaterialInventory, apartmentSpec, recycleRate
        );
    }



    public static void main(String[] args) {
        Material[] materials = new Material[] {
                new Material("wood", new CostContainer(125.0, 0.4, 0.01)),
                new Material("concrete", new CostContainer(250.0, 0.8, 0.15)),
                new Material("steel", new CostContainer(1200.0, 1.4, 0.007))
        };

        /*
        * Assuming that we have 10 apartments with each 50 sqm.
        * */

        int residentsPerApartment = 1, numberOfApartments = 50,
                lifeTimeApartment = 25, lifeTimeBuilding = 50;
        double happinessUpperBound = 1.0f, recycleRate = 0.5f;


        Double[] interiorMinimal = new Double[] {14.4, 2.0, 0.875};
        Double[] shellMinimal = new Double[] {216.0, 30.0, 3.75};

        Double[] shellEco = new Double[] {350.0, 10.0, 2.50};
        Double[] interiorEco = new Double[] {14.4, 2.0, 0.875};

        Double[] shellHighEnd = new Double[] {216.0, 30.0, 3.75};
        Double[] interiorHighEnd = new Double[] {14.4, 2.0, 0.875};


        BuildingSpecs buildingMinimal = giveBuildSpecs(
                materials,
                interiorMinimal,
                shellMinimal,
                residentsPerApartment,
                numberOfApartments,
                lifeTimeApartment,
                lifeTimeBuilding,
                happinessUpperBound,
                recycleRate
                );


        BuildingSpecs buildingEco = giveBuildSpecs(
                materials,
                interiorEco,
                shellEco, residentsPerApartment, numberOfApartments,
                lifeTimeApartment, lifeTimeBuilding, 0.9,
                recycleRate
        );


        BuildingSpecs buildingHighEnd = giveBuildSpecs(
                materials, interiorHighEnd, shellHighEnd, residentsPerApartment,
                numberOfApartments, lifeTimeApartment, 100, 0.8,
                recycleRate
        );
        
        String[] namesTestCases = {
                "MINIMAL",
                "OEKOLOGISCH",
                "HOCHWERTIG"};

        BuildingSpecs[] buildingsTestConfigs = new BuildingSpecs[] {
                buildingMinimal,
                buildingEco,
                buildingHighEnd
        };

        ApartmentSpecs[] interiorsTestConfigs = new ApartmentSpecs[] {
                buildingMinimal.getApartmentSpecs(),
                buildingEco.getApartmentSpecs(),
                buildingHighEnd.getApartmentSpecs()
        };

        for (int i = 0; i < buildingsTestConfigs.length; i++) {
            System.out.printf("---TEST CASE %d %s---%n", i + 1, namesTestCases[i]);
            System.out.println();
            // ten simulations per case
            ArrayList<SimulationResult> results = new ArrayList<SimulationResult>();
            for (int j = 0; j < 10; j++) {
                Simulation simulation = new Simulation(buildingsTestConfigs[i], interiorsTestConfigs[i]);
                results.add(simulation.runSimulation());
                System.out.printf("Nachhaltigkeits-Score fuer Simulation%d %f%n", j + 1, results.get(j).getSustainabilityScore());
            }


            results.sort((r1, r2) -> (int) Math.signum(r1.getSustainabilityScore() - r2.getSustainabilityScore()));
            SimulationResult medianResult = results.get(results.size()/2 + 1);
            System.out.println();
            System.out.printf("Alle Kennzahlen fuer den Simulationsdurchlauf mit dem Median-Nachhaltigkeits-Score:%n");
            System.out.printf("- Nachhaltigkeits-Score: %f%n", medianResult.getSustainabilityScore());
            System.out.printf("- averageCostOverLifetime: %f%n", medianResult.getAverageCostOverLifetime());
            System.out.printf("- averageCo2OverLifetime: %f%n", medianResult.getAverageCostOverLifetime());
            System.out.printf("- averageWasteOverLifetime: %f%n", medianResult.getAverageWasteOverLifetime());
            for (int j = 0; j < medianResult.getAverageCostPerDecade().size(); j++) {
                System.out.printf("- AverageCostDecade%d: %f%n", j + 1, medianResult.getAverageCostPerDecade().get(j));
            }
            for (int j = 0; j < medianResult.getAverageCostPerDecade().size(); j++) {
                System.out.printf("- AverageHappinessDecade%d: %f%n", j + 1, medianResult.getAverageHappinessPerDecade().get(j));
            }
            System.out.println();

        }


    }


}