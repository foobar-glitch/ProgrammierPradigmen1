public class Building {
    private int lifetime;
    /* The materials of the shell of the building */
    private MaterialBag shellConstruct;
    /* The materials of the building part that has to be renovated*/
    private MaterialBag renovatingConstruct;

    public Building(int lifetime, MaterialBag shellConstruct, MaterialBag renovatingConstruct) {
        this.lifetime = lifetime;
        this.shellConstruct = shellConstruct;
        this.renovatingConstruct = renovatingConstruct;

    }

}
