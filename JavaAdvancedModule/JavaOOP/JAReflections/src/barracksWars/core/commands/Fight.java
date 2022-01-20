package barracksWars.core.commands;

import barracksWars.core.Inject;
import barracksWars.interfaces.Repository;
import barracksWars.interfaces.UnitFactory;

public class Fight extends Command{

    @Inject(name="barracksWars.data.UnitRepository")
    private Repository repository;
    @Inject(name="barracksWars.core.factories.UnitFactoryImpl")
    private UnitFactory unitFactory;

    public Fight(String[] data) {
        super(data);
    }

//    public Fight(String[] data, Repository repository, UnitFactory unitFactory) {
//        super(data, repository, unitFactory);
//    }

    @Override
    public String execute() {
        return "fight";
    }

    public Repository getRepository() {
        return repository;
    }

    public UnitFactory getUnitFactory() {
        return unitFactory;
    }
}
