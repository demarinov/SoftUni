package com.dido.exercise;

import javax.swing.plaf.nimbus.State;
import java.util.*;

public class MilitaryElite {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        List<Private> privateList = new LinkedList<>();
        List<LieutenantGeneral> generalList  =new LinkedList<>();
        List<Engineer> engineerList = new LinkedList<>();
        List<Commando> commandoList  =new LinkedList<>();
        List<Spy> spyList = new LinkedList<>();
        while(!"End".equals(input)) {

            String[] soldierData = input.split("\\s");
            // Private <id> <firstName> <lastName> <salary>"
            if ("Private".equals(soldierData[0])) {
                int id = Integer.parseInt(soldierData[1]);
                String firstName = soldierData[2];
                String lastName = soldierData[3];
                double salary = Double.parseDouble(soldierData[4]);
                Private privateSoldier = new PrivateImpl(id,firstName, lastName,salary);

                privateList.add(privateSoldier);
                System.out.println(privateSoldier);
            } else if ("LieutenantGeneral".equals(soldierData[0])) {
                // LieutenantGeneral <id> <firstName> <lastName> <salary>
                // <private1Id> <private2Id> … <privateNId>"
                int id = Integer.parseInt(soldierData[1]);
                String firstName = soldierData[2];
                String lastName = soldierData[3];
                double salary = Double.parseDouble(soldierData[4]);

                LieutenantGeneral general = new LieutenantGeneralImpl(id, firstName, lastName, salary);

                generalList.add(general);
                for (int i = 5; i < soldierData.length; i++) {
                    int privateId = Integer.parseInt(soldierData[i]);
                    general.addPrivate(findSoldierById(privateList,privateId));
                }
                System.out.println(general);
            } else if ("Engineer".equals(soldierData[0])) {
                // "Engineer <id> <firstName> <lastName> <salary> <corps> <repair1Part> <repair1Hours>
                // … <repairNPart> <repairNHours>"
                int id = Integer.parseInt(soldierData[1]);
                String firstName = soldierData[2];
                String lastName = soldierData[3];
                double salary = Double.parseDouble(soldierData[4]);
                SpecialisedSoldier.Corps corps = SpecialisedSoldier.Corps.getByName(soldierData[5]);

                try {
                    Engineer engineer = new EngineerImpl(id, firstName, lastName, corps, salary);

                    engineerList.add(engineer);

                    for (int i = 6; i < soldierData.length; i+=2) {
                        String repairPart = soldierData[i];
                        Integer repairHours = Integer.parseInt(soldierData[i+1]);
                        Repair repair = new RepairImpl(repairPart, repairHours);
                        engineer.addRepair(repair);
                    }

                    System.out.println(engineer);
                } catch (IllegalArgumentException e) {

                }

            } else if ("Commando".equals(soldierData[0])) {

                // Commando <id> <firstName> <lastName> <salary> <corps> <mission1CodeName>  <mission1state>
                // … <missionNCodeName> <missionNstate>"
                int id = Integer.parseInt(soldierData[1]);
                String firstName = soldierData[2];
                String lastName = soldierData[3];
                double salary = Double.parseDouble(soldierData[4]);
                SpecialisedSoldier.Corps corps = SpecialisedSoldier.Corps.getByName(soldierData[5]);

                try {
                    Commando commando = new CommandoImpl(id, firstName, lastName, corps, salary);

                    commandoList.add(commando);

                    for (int i = 6; i < soldierData.length; i+=2) {
                        String missionCodeName = soldierData[i];
                        String missionStateStr = soldierData[i+1];

                        Mission.State state = Mission.State.getStateByName(missionStateStr);

                        if (state == null) {
                            continue;
                        }
                        Mission mission = new MissionImpl(missionCodeName,state);
                        commando.addMission(mission);
                    }

                    System.out.println(commando);

                } catch (IllegalArgumentException e) {

                }
            } else if ("Spy".equals(soldierData[0])) {
                // "Spy <id> <firstName> <lastName> <codeNumber>"
                int id = Integer.parseInt(soldierData[1]);
                String firstName = soldierData[2];
                String lastName = soldierData[3];
                String codeNumber = soldierData[4];

                Spy spy = new SpyImpl(id, firstName, lastName, codeNumber);
                spyList.add(spy);
                System.out.println(spy);
            }

            input = sc.nextLine();
        }

//        privateList.stream().forEach(System.out::println);
//        spyList.stream().forEach(System.out::println);
//        generalList.stream().forEach(System.out::println);
//        engineerList.stream().forEach(System.out::println);
//        commandoList.stream().forEach(System.out::println);

    }

    public static Private findSoldierById(List<Private> privateList, int privateId) {

        Private privateSoldier = privateList.stream().filter(p -> privateId == p.getId())
                .findFirst().orElse(null);
        return privateSoldier;
    }

    interface Soldier {
        int getId();
        String getFirstName();
        String getLastName();
    }

    interface Private extends Soldier{

        double salary();
    }

    interface LieutenantGeneral extends Soldier {
        void addPrivate(Private priv);
    }

    interface Repair {
        String getPartName();
        int getHoursWorked();

    }

    interface Engineer extends SpecialisedSoldier {
        void addRepair(Repair repair);
        Collection<Repair> getRepairs();

    }


    interface Mission{

        enum State {
            IN_PROGRESS("inProgress"), FINISHED("finished");

            private String value;
            State(String value) {
                this.value = value;
            }

            public static State getStateByName(String name) {
                for (State state : Arrays.asList(values())) {

                    if (name.equals(state.getValue())) {
                        return state;
                    }
                }

                return null;
            }

            public String getValue() {
                return value;
            }
        };
        String getCodeName();
        State getState();
    }

    interface Commando extends SpecialisedSoldier {
        void addMission(Mission mission);
        Collection<Mission> getMissions();

    }

    interface SpecialisedSoldier extends Private{
        enum Corps{
            AIR_FORCES("Airforces"),MARINES("Marines");

            private String value;
            Corps(String value) {
                this.value = value;
            }

            public String getValue() {
                return value;
            }

            public static boolean isValid(Corps corps) {
                for(Corps corpsPresent : values()) {

                    if (corps.getValue().equals(corpsPresent.getValue())) {
                        return true;
                    }
                }

                return false;
            }

            public static Corps getByName(String name) {
                for(Corps corpsPresent : values()) {

                    if (name.equals(corpsPresent.getValue())) {
                        return corpsPresent;
                    }
                }

                return null;
            }
        }

        Corps getCorps();
    }

    interface Spy {
        String getCodeNumber();
    }

    static class SpyImpl extends SoldierImpl implements Spy {

        private String codeNumber;

        public SpyImpl(int id, String firstName, String lastName, String codeNumber) {
            super(id, firstName, lastName);
            setCodeNumber(codeNumber);
        }

        private void setCodeNumber(String codeNumber) {
            this.codeNumber = codeNumber;
        }

        @Override
        public String getCodeNumber() {
            return this.codeNumber;
        }

        @Override
        public String toString() {
            // "Name: <firstName> <lastName> Id: <id>
            //Code Number: <codeNumber>"
            return String.format("Name: %s %s Id: %d%n" +
                    "Code Number: %s",this.getFirstName(), this.getLastName(),
                    this.getId(), this.getCodeNumber());
        }
    }

    static class RepairImpl implements Repair {

        private String partName;
        private int hoursWorked;

        public RepairImpl(String partName, int hoursWorked) {
            setPartName(partName);
            setHoursWorked(hoursWorked);
        }

        private void setPartName(String partName) {
            this.partName = partName;
        }

        private void setHoursWorked(int hoursWorked) {
            this.hoursWorked = hoursWorked;
        }

        @Override
        public String getPartName() {
            return this.partName;
        }

        @Override
        public int getHoursWorked() {
            return this.hoursWorked;
        }

        @Override
        public String toString() {
            // "Part Name: <partName> Hours Worked: <hoursWorked>"
            return String.format("Part Name: %s Hours Worked: %d",this.getPartName(),
                    this.getHoursWorked());
        }
    }

    static class MissionImpl implements Mission {

        private String codeName;
        private State state;

        public MissionImpl(String codeName, State state) {
            setCodeName(codeName);
            setState(state);
        }

        private void setCodeName(String codeName) {
            this.codeName = codeName;
        }

        private void setState(State state) {
            this.state = state;
        }

        @Override
        public String getCodeName() {
            return this.codeName;
        }

        @Override
        public State getState() {
            return this.state;
        }

        @Override
        public String toString() {

            // "Code Name: <codeName> State: <state>"
            return String.format("Code Name: %s State: %s",this.getCodeName(),
                    this.getState().getValue());
        }
    }

    static class CommandoImpl extends SpecialisedSoldierImpl implements Commando {

        private List<Mission> missionList;

        public CommandoImpl(int id, String firstName, String lastName, Corps corps, double salary) {
            super(id, firstName, lastName, corps, salary);
            this.missionList = new LinkedList<>();
        }

        @Override
        public void addMission(Mission mission) {
            this.missionList.add(mission);
        }

        @Override
        public Collection<Mission> getMissions() {
            return Collections.unmodifiableCollection(this.missionList);
        }

        @Override
        public String toString() {
            // "Name: <firstName> <lastName> Id: <id> Salary: <salary>
            //Corps: <corps>
            //Missions:
            //  <mission1 ToString()>
            //  <mission2 ToString()>
            //  …
            //  <missionN ToString()>"
            String missionOutput = "";
            if (!missionList.isEmpty()) {
                missionOutput = missionList.stream()
                        .map(m -> "  "+m.toString() + "\n")
                        .reduce("", String::concat);
                return String.format("%s%n" +
                        "Missions:%n%s",super.toString(),
                        missionOutput.substring(0,missionOutput.length()-1));
            }
            return String.format("%s%n" +
                    "Missions:",super.toString());
        }
    }

    static class EngineerImpl extends SpecialisedSoldierImpl implements Engineer {

        private List<Repair> repairsList;

        public EngineerImpl(int id, String firstName, String lastName, Corps corps, double salary) {
            super(id, firstName, lastName, corps, salary);
            this.repairsList = new LinkedList<>();
        }

        @Override
        public void addRepair(Repair repair) {
            this.repairsList.add(repair);
        }

        @Override
        public Collection<Repair> getRepairs() {
            return Collections.unmodifiableCollection(this.repairsList);
        }

        @Override
        public String toString() {

            // "Name: <firstName> <lastName> Id: <id> Salary: <salary>
            //Corps: <corps>
            //Repairs:
            //  <repair1 ToString()>
            //  <repair2 ToString()>
            //  …
            //  <repairN ToString()>"
            String repairsOutput = "";
            if (!repairsList.isEmpty()) {
                repairsOutput = repairsList.stream()
                        .map(r -> "  "+r.toString() + "\n")
                        .reduce("", String::concat);
                return String.format("%s%n" +
                        "Repairs:%n%s", super.toString(), repairsOutput.substring(0, repairsOutput.length()-1));
            }
            return String.format("%s%n" +
                    "Repairs:", super.toString());

        }
    }

    static class SpecialisedSoldierImpl extends PrivateImpl implements SpecialisedSoldier{

        private Corps corps;

        public SpecialisedSoldierImpl(int id, String firstName, String lastName, Corps corps,
                                      double salary) {
            super(id, firstName, lastName, salary);
            setCorps(corps);
        }

        private void setCorps(Corps corps) {

            if (corps == null || !Corps.isValid(corps)) {
                throw new IllegalArgumentException("Invalid Corps");
            }

            this.corps = corps;
        }

        @Override
        public Corps getCorps() {
            return this.corps;
        }

        @Override
        public String toString() {
            return String.format("%s%n" +
                    "Corps: %s", super.toString(), this.getCorps().getValue());
        }
    }

    static class LieutenantGeneralImpl extends PrivateImpl implements LieutenantGeneral {

        private List<Private> privateList;

        public LieutenantGeneralImpl(int id, String firstName, String lastName, double salary) {
            super(id, firstName, lastName, salary);
            privateList = new LinkedList<>();
        }

        @Override
        public void addPrivate(Private priv) {
            privateList.add(priv);
        }

        @Override
        public String toString() {
            String privatesOutput = "";
            if (!privateList.isEmpty()) {
                privatesOutput = privateList.stream().sorted((p1, p2) ->
                        Integer.valueOf(p2.getId()).compareTo(Integer.valueOf(p1.getId())))
                        .map(p -> "  "+p.toString() + "\n")
                        .reduce("", String::concat);
                return String.format("%s%n" +
                        "Privates:%n%s", super.toString(),
                        privatesOutput.substring(0,privatesOutput.length()-1));
            }
            return String.format("%s%n" +
                    "Privates:", super.toString());
        }
    }

    static class PrivateImpl extends SoldierImpl implements Private {

        private double salary;

        public PrivateImpl(int id, String firstName, String lastName, double salary) {
            super(id, firstName, lastName);
            setSalary(salary);
        }

        private void setSalary(double salary) {
            this.salary = salary;
        }

        @Override
        public double salary() {
            return this.salary;
        }

        @Override
        public String toString() {
            // "Name: <firstName> <lastName> Id: <id> Salary: <salary>"
            return String.format("%s Salary: %.2f",super.toString(), this.salary());
        }
    }

    static class SoldierImpl implements Soldier {

        private int id;
        private String firstName;
        private String lastName;

        public SoldierImpl(int id, String firstName, String lastName) {
            setId(id);
            setFirstName(firstName);
            setLastName(lastName);
        }

        private void setId(int id) {
            this.id = id;
        }

        private void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        private void setLastName(String lastName) {
            this.lastName = lastName;
        }

        @Override
        public int getId() {
            return this.id;
        }

        @Override
        public String getFirstName() {
            return this.firstName;
        }

        @Override
        public String getLastName() {
            return this.lastName;
        }

        @Override
        public String toString() {
            return String.format("Name: %s %s Id: %d",this.getFirstName(),
                    this.getLastName(), this.getId());
        }
    }
}
