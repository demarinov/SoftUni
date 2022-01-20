package barracksWars.core;

import barracksWars.core.commands.Command;
import barracksWars.interfaces.Repository;
import barracksWars.interfaces.Runnable;
import barracksWars.interfaces.Unit;
import barracksWars.interfaces.UnitFactory;
import jdk.jshell.spi.ExecutionControl;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.TreeMap;

public class Engine implements Runnable {

	private Repository repository;
	private UnitFactory unitFactory;
	private Map<String, Object> injectContainer;

	public Engine(Repository repository, UnitFactory unitFactory) {
		this.repository = repository;
		this.unitFactory = unitFactory;
		injectContainer = new TreeMap<>();
	}

	@Override
	public void run() {
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(System.in));
		while (true) {
			try {
				String input = reader.readLine();
				String[] data = input.split("\\s+");
				String commandName = data[0];
				String result = interpretCommand(data, commandName);
				if (result.equals("fight")) {
					break;
				}
				System.out.println(result);
			} catch (RuntimeException e) {
				System.out.println(e.getMessage());
			} catch (IOException | ExecutionControl.NotImplementedException e) {
				e.printStackTrace();
			}
		}
	}

	// TODO: refactor for problem 4
	private String interpretCommand(String[] data, String commandName) throws ExecutionControl.NotImplementedException {
		String result = "";

		String modifiedCommandName = String.format("%s%s",
				commandName.substring(0,1).toUpperCase(),commandName.substring(1));

		try {
			Class commandClass = Class.forName("barracksWars.core.commands."+modifiedCommandName);

			Field[] fields = commandClass.getDeclaredFields();
			Command command = (Command) commandClass
					.getDeclaredConstructor(String[].class)
					.newInstance(new Object[] {data});
			for (Field field : fields) {
				Inject fieldInject = field.getDeclaredAnnotation(Inject.class);
				if (fieldInject != null) {


					Class fieldClass = Class.forName(fieldInject.name());
					field.setAccessible(true);
					injectContainer
							.putIfAbsent(fieldInject.name(), fieldClass.getConstructor().newInstance());

					field.set(command, injectContainer.get(fieldInject.name()));
				}
			}


			result = command.execute();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}

		return result;
	}

	private String reportCommand(String[] data) {
		String output = this.repository.getStatistics();
		return output;
	}

	private String addUnitCommand(String[] data) throws ExecutionControl.NotImplementedException {
		String unitType = data[1];
		Unit unitToAdd = this.unitFactory.createUnit(unitType);
		this.repository.addUnit(unitToAdd);
		String output = unitType + " added!";
		return output;
	}
	
	private String fightCommand(String[] data) {
		return "fight";
	}
}
