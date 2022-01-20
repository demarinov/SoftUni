public class ComputerMain {

    public static void main(String[] args) {

        Computer computer = Computer.builder()
                .withProcessor("Intel")
                .withRam("64GB")
                .withGraphicCard("RTX")
                .withKeyboard("LogicTech")
                .withMonitor("Toshiba")
                .withHardDisk("512GB")
                .withMotherBoard("Intel")
                .build();

    }
}
