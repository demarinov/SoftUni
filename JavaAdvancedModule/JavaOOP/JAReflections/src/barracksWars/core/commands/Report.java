package barracksWars.core.commands;

import barracksWars.core.Inject;
import barracksWars.interfaces.Repository;
import barracksWars.interfaces.UnitFactory;

public class Report extends Command {

    @Inject(name="barracksWars.data.UnitRepository")
    private Repository repository;
    @Inject(name="barracksWars.core.factories.UnitFactoryImpl")
    private UnitFactory unitFactory;

    public Report(String[] data) {
        super(data);
    }
//
//    public Report(String[] data, Repository repository, UnitFactory unitFactory) {
//        super(data, repository, unitFactory);
//    }

    @Override
    public String execute() {
        String output = this.getRepository().getStatistics();
        return output;
    }

    public Repository getRepository() {
        return repository;
    }

    public UnitFactory getUnitFactory() {
        return unitFactory;
    }
}
