import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.round;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public Material getBuilding(){
        return null;
    }



    public static void main(String[] args) {
        Material[] materials = new Material[] {
                new Material("wood", new CostContainer(350.0, 0.0015, 0.01)),
                new Material("concrete", new CostContainer(100.0, 0.015, 0.02)),
                new Material("steel", new CostContainer(1200.0, 0.09, 0.005))
        };

        /*
        * Assuming that we have 10 apartments with each 50 sqm.
        * */
        MaterialBag shellMinimal = new MaterialBag(materials, new Double[] {216.0, 30.0, 3.75});
        MaterialBag  interiorMinimal = new MaterialBag(materials, new Double[] {14.4, 2.0, 0.875});
        ApartmentConfig apartmentsMinimal = new ApartmentConfig(interiorMinimal, 1, 10, 25, 50 ,1.0);

        MaterialBag shellEco = new MaterialBag(materials, new Double[] {216.0, 30.0, 3.75});
        MaterialBag  interiorEco = new MaterialBag(materials, new Double[] {14.4, 2.0, 0.875});
        ApartmentConfig apartmentsEco = new ApartmentConfig(interiorMinimal, 1, 10, 25, 50 ,1.0);

        MaterialBag shellHighEnd = new MaterialBag(materials, new Double[] {216.0, 30.0, 3.75});
        MaterialBag  interiorHighEnd = new MaterialBag(materials, new Double[] {14.4, 2.0, 0.875});
        ApartmentConfig apartmentsHighEnd = new ApartmentConfig(interiorMinimal, 1, 10, 25, 50 ,1.0);

        /*
        * Each apartment has 1 resident. Every apartment
        * is the same size and need the same amount of material
         */





        BuildingConfig config = new BuildingConfig(50, shellMinimal, apartmentsMinimal, 0.5f);

        BuildingConfig buildingMinimal = config;
        BuildingConfig buildingEco = config;
        BuildingConfig buildingHighEnd = config;

        ArrayList<BuildingConfig> buildingsTestConfigs = new ArrayList<BuildingConfig>(List.of(new BuildingConfig[]{
                buildingMinimal,
                buildingEco,
                buildingHighEnd}));

        ArrayList<ApartmentConfig> interiorsTestConfigs = new ArrayList<ApartmentConfig>(List.of(new ApartmentConfig[]{
                apartmentsMinimal,
                apartmentsEco,
                apartmentsHighEnd
        }));


        String[] namesTestCases = {"MINIMAL", "OEKOLOGISCH", "HOCHWERTIG"};

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


            // TODO ist der Median hier eine gute Metrik um einen raepresentativen Simulationsdurchlauf zu finden?
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