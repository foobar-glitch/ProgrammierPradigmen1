public class BuildingSpecs {
    public int lifetime;
    public int age;
    public MaterialBag shellConstruct;
    public ApartmentSpecs apartmentSpecs;

    /**
     *
     * @param lifetime the general lifetime of the building
     * @param shellConstruct the shell construction of the building
     * @param apartmentSpecs config for the buildings apartments
     * @param recycleRate Recycle rate [0,1] when building is demolished
     */
    public BuildingSpecs(
            int lifetime,
            MaterialBag shellConstruct,
            ApartmentSpecs apartmentSpecs,
            double recycleRate
    ) {
        this.age = 0;
        this.lifetime = lifetime;
        this.shellConstruct = shellConstruct;
        this.apartmentSpecs = apartmentSpecs;
        this.recycleRate=recycleRate;
    }

    public int getLifetime() {
        return lifetime;
    }

    public int getAge() {
        return age;
    }

    public MaterialBag getShellConstruct() {
        return shellConstruct;
    }

    public double getRecycleRate() {
        return recycleRate;
    }

    public double recycleRate;

    public ApartmentSpecs getApartmentSpecs() {
        return apartmentSpecs;
    }
}
