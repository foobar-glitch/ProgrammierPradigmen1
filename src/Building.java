import java.util.HashMap;
import java.util.LinkedList;

public class Building {
    private int lifetime;
    /* The materials of the shell of the building */
    private MaterialBag shellConstruct;
    /* The materials of the building part that has to be renovated*/
    private Apartment[] apartments;;
    /* The total costs of the initial building */
    private CostContainer totalCosts = new CostContainer(0, 0, 0);

    /**
     *
     * @param lifetime the general lifetime of the building
     * @param shellConstruct the shell construction of the building
     * @param apartmentsWithResidents every apartment with its resident
     */
    public Building(
            int lifetime,
            MaterialBag shellConstruct,
            HashMap<MaterialBag, Integer> apartmentsWithResidents
    ) {
        this.lifetime = lifetime;
        this.shellConstruct = shellConstruct;
        apartments = new Apartment[apartmentsWithResidents.size()];

        int i = 0;
        for (MaterialBag mb : apartmentsWithResidents.keySet()) {
            apartments[i] = new Apartment(mb, lifetime, apartmentsWithResidents.get(mb));
            i++;
        }

        /* Adding the costs of all elements into the total costs initially */
        CostContainer shellCost = shellConstruct.getTotalCost();
        this.totalCosts.addCostContainer(shellCost);
    }


    /**
     * Depending on the household amount of material I have to renovate
     *
     * @param replaceMaterial The materials we are replacing
     * */
    public void renovate(MaterialBag replaceMaterial) {
        CostContainer replaceMaterialCost = replaceMaterial.getTotalCost();
        this.totalCosts.addCostContainer(replaceMaterialCost);
    }

    /**
     * When demolishing you can recycle x percent of material and subtract that value
     * from the total costs
     * @param recycleRate Is the rate at which we can recycle the leftover material [0,1]
     *
     * */
    public void demolishing(float recycleRate){
        CostContainer leftoverMaterial = shellConstruct.getTotalCost();
        CostContainer recycledProfit = new CostContainer(
                leftoverMaterial.getCost() * recycleRate,
                leftoverMaterial.getCo2() * recycleRate,
                leftoverMaterial.getWaste() * recycleRate
        );
        this.totalCosts.subtractCostContainer(recycledProfit);
    }

    public void age(){
        lifetime -= 1;
        // get waste rate of every material
        // subtract waste from material amount
        float wasteAmount, materialAmount;
        for (Material material : renovatingConstruct.getMaterialInventory().keySet()) {
            materialAmount = renovatingConstruct.getMaterialInventory().get(material);
            wasteAmount = material.getCost().getWaste();
            materialAmount -= wasteAmount;
            renovatingConstruct.setMaterial(material, materialAmount);
        }
    }

    public boolean checkAge(){
        return lifetime > 0;
    }

}
