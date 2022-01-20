package barracksWars.core.commands;

import barracksWars.core.Inject;
import barracksWars.interfaces.Repository;
import barracksWars.interfaces.Unit;
import barracksWars.interfaces.UnitFactory;
import jdk.jshell.spi.ExecutionControl;

public class Add extends Command{

    @Inject(name="barracksWars.data.UnitRepository")
    private Repository repository;
    @Inject(name="barracksWars.core.factories.UnitFactoryImpl")
    private UnitFactory unitFactory;

    public Add(String[] data) {
        super(data);
    }

//    public Add(String[] data, Repository repository, UnitFactory unitFactory) {
//        super(data, repository, unitFactory);
//    }

    @Override
    public String execute() {
        String unitType = this.getData()[1];
        Unit unitToAdd = null;
        try {
            unitToAdd = this.getUnitFactory().createUnit(unitType);
        } catch (ExecutionControl.NotImplementedException e) {
            e.printStackTrace();
        }
        this.getRepository().addUnit(unitToAdd);
        String output = unitType + " added!";
        return output;
    }

    public Repository getRepository() {
        return repository;
    }

    public UnitFactory getUnitFactory() {
        return unitFactory;
    }
}
