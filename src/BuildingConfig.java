public class BuildingConfig {
    public int lifetime;
    public int age;
    /* The materials of the shell of the building */
    public MaterialBag shellConstruct;
    /* The materials of the building part that has to be renovated*/
    public Apartment[] apartments;
    /* The total costs of the initial building */
    public int renovationIndex;
    public double recycleRate;

    public BuildingConfig(
            int lifetime,
            MaterialBag shellConstruct,
            Apartment[] apartments,
            double recycleRate
    ) {
        this.age = 0;
        this.lifetime = lifetime;
        this.shellConstruct = shellConstruct;
        this.apartments = apartments;
        this.recycleRate=recycleRate;
    }

}
