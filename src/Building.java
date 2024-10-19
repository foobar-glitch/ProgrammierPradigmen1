public class Building {
    private int lifetime;
    /* The materials of the shell of the building */
    private MaterialBag shellConstruct;
    /* The materials of the building part that has to be renovated*/
    private MaterialBag renovatingConstruct;
    /* The total costs of the initial building */
    private CostContainer totalCosts = new CostContainer(0, 0, 0);

    public Building(int lifetime, MaterialBag shellConstruct, MaterialBag renovatingConstruct) {
        this.lifetime = lifetime;
        this.shellConstruct = shellConstruct;
        this.renovatingConstruct = renovatingConstruct;

        /* Adding the costs of all elements into the total costs initially */
        CostContainer shellCost = shellConstruct.getTotalCost();
        CostContainer renovatingCost = renovatingConstruct.getTotalCost();
        this.totalCosts.addCostContainer(shellCost);
        this.totalCosts.addCostContainer(renovatingCost);
    }


    /**
     * Depending on the household and degree of renovations replace material
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
     * @param recycleRate Is the rate at which we can recycle the material [0,1]
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
    

}
