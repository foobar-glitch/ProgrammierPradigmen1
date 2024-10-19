public class Building {
    private int lifetime;
    /* The materials of the shell of the building */
    private MaterialBag shellConstruct;
    /* The materials of the building part that has to be renovated*/
    private MaterialBag renovatingConstruct;
    /* The total costs of the building */
    private CostContainer totalCost = new CostContainer(0, 0, 0);

    public Building(int lifetime, MaterialBag shellConstruct, MaterialBag renovatingConstruct) {
        this.lifetime = lifetime;
        this.shellConstruct = shellConstruct;
        this.renovatingConstruct = renovatingConstruct;

        /* Adding the costs of all elements into the total costs initially*/
        CostContainer shellCost = shellConstruct.getTotalCost();
        CostContainer renovatingCost = renovatingConstruct.getTotalCost();
        this.totalCost.addCostContainer(shellCost);
        this.totalCost.addCostContainer(renovatingCost);
    }

}
