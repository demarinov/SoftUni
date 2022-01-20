package barracksWars.core.factories;

import barracksWars.interfaces.Unit;
import barracksWars.interfaces.UnitFactory;
import jdk.jshell.spi.ExecutionControl;

import java.lang.reflect.InvocationTargetException;

import static jdk.jshell.spi.ExecutionControl.*;

public class UnitFactoryImpl implements UnitFactory {

	private static final String UNITS_PACKAGE_NAME =
			"barracksWars.models.units.";

	@Override
	public Unit createUnit(String unitType) throws NotImplementedException {
		// TODO: implement for problem 3

		Class unitClass = null;
		try {
			unitClass = Class.forName("barracksWars.models.units."+unitType);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Unit unitObject = null;
		try {
			unitObject = (Unit) unitClass.getConstructor().newInstance();
		} catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}

		return unitObject;
	}
}
