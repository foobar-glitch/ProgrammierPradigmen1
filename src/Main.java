import java.util.ArrayList;
import java.util.List;

public class Main {
    public Material getBuilding(){
        return null;
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
        MaterialBag shellMinimal = new MaterialBag(materials, new Double[] {216.0, 30.0, 3.75});
        MaterialBag  interiorMinimal = new MaterialBag(materials, new Double[] {14.4, 2.0, 0.875});
        CostContainer heatingAndMaintenanceCostsMinimal = new CostContainer(0.35, 0.6, 0.05);
        ApartmentSpecs apartmentsMinimal = new ApartmentSpecs(interiorMinimal, 1, 10, 20 ,0.8);
        BuildingSpecs buildingMinimal = new BuildingSpecs(50, shellMinimal, apartmentsMinimal, heatingAndMaintenanceCostsMinimal, 0.5f);

        MaterialBag shellEco = new MaterialBag(materials, new Double[] {257.3, 10.0, 2.45});
        MaterialBag  interiorEco = new MaterialBag(materials, new Double[] {14.4, 2.0, 0.875});
        CostContainer heatingAndMaintenanceCostsEco = new CostContainer(0.35, 0.3, 0.05);
        ApartmentSpecs apartmentsEco = new ApartmentSpecs(interiorEco, 1, 10, 20,0.9);
        BuildingSpecs buildingEco = new BuildingSpecs(50, shellEco, apartmentsEco, heatingAndMaintenanceCostsEco,0.7f);

        MaterialBag shellHighEnd = new MaterialBag(materials, new Double[] {382.3, 48.6, 5.35});
        MaterialBag  interiorHighEnd = new MaterialBag(materials, new Double[] {12.3, 1.9, 1.35});
        CostContainer heatingAndMaintenanceHighEnd = new CostContainer(0.37, 0.32, 0.05);
        ApartmentSpecs apartmentsHighEnd = new ApartmentSpecs(interiorHighEnd, 1, 10, 25,1.0);
        BuildingSpecs buildingHighEnd = new BuildingSpecs(100, shellHighEnd, apartmentsHighEnd, heatingAndMaintenanceHighEnd,0.5f);

        String[] namesTestCases = {
                "MINIMAL",
                "OEKOLOGISCH",
                "HOCHWERTIG"};
        ArrayList<BuildingSpecs> buildingsTestConfigs = new ArrayList<BuildingSpecs>(List.of(new BuildingSpecs[]{
                buildingMinimal,
                buildingEco,
                buildingHighEnd}));
        ArrayList<ApartmentSpecs> interiorsTestConfigs = new ArrayList<ApartmentSpecs>(List.of(new ApartmentSpecs[]{
                apartmentsMinimal,
                apartmentsEco,
                apartmentsHighEnd
        }));

        for (int i = 0; i < buildingsTestConfigs.size(); i++) {
            System.out.printf("---TEST CASE %d %s---%n", i + 1, namesTestCases[i]);
            System.out.println();
            // ten simulations per case
            ArrayList<SimulationResult> results = new ArrayList<SimulationResult>();
            for (int j = 0; j < 10; j++) {
                Simulation simulation = new Simulation(buildingsTestConfigs.get(i), interiorsTestConfigs.get(i));
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