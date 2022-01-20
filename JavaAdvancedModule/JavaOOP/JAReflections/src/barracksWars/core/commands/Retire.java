package barracksWars.core.commands;

import barracksWars.core.Inject;
import barracksWars.interfaces.Repository;
import barracksWars.interfaces.UnitFactory;
import jdk.jshell.spi.ExecutionControl;

public class Retire extends Command{

    @Inject(name="barracksWars.data.UnitRepository")
    private Repository repository;
    @Inject(name="barracksWars.core.factories.UnitFactoryImpl")
    private UnitFactory unitFactory;

    public Retire(String[] data) {
        super(data);
    }

//    public Retire(String[] data, Repository repository, UnitFactory unitFactory) {
//        super(data, repository, unitFactory);
//    }

    @Override
    public String execute() {
        String unitType = this.getData()[1];
        try {
            this.getRepository().removeUnit(unitType);
        } catch (ExecutionControl.NotImplementedException e) {
            return e.getLocalizedMessage();
        }

        return unitType+" retired!";
    }

    public Repository getRepository() {
        return repository;
    }

    public UnitFactory getUnitFactory() {
        return unitFactory;
    }
}
