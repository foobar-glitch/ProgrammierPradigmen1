public class Building {
    int lifetime;
    ShellContruct shell;
    RenovatingConstruct renovate;

    public Building(int lifetime, ShellConstruct shell, RenovatingConstruct renovate){
        this.lifetime = lifetime;
        this.shell = shell;
        this.renovate = renovate;

    }

}
