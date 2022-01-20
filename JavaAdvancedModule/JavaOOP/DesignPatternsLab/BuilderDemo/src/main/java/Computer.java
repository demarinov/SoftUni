public class Computer {

    private String processor;
    private String ram;
    private String motherBoard;
    private String hardDisk;
    private String graphicCard;
    private String monitor;
    private String keyBoard;
    private String mouse;

    private Computer() {
    }

    public static Builder builder() {

        return new Builder();
    }

    public static class Builder{

        private Computer computer;

        private Builder() {
            this.computer  = new Computer();
        }

        public Builder withProcessor(String processor) {
            computer.processor = processor;
            return this;
        }

        public Builder withRam(String ram) {
            computer.ram = ram;
            return this;
        }

        public Builder withMotherBoard(String motherBoard) {
            computer.motherBoard = motherBoard;
            return this;
        }

        public Builder withGraphicCard(String graphicCard) {
            computer.graphicCard = graphicCard;
            return this;
        }

        public Builder withHardDisk(String hardDisk) {
            computer.hardDisk = hardDisk;
            return this;
        }

        public Builder withMonitor(String monitor) {
            computer.monitor = monitor;
            return this;
        }

        public Builder withKeyboard(String keyboard) {
            computer.keyBoard = keyboard;
            return this;
        }

        public Builder withMouse(String mouse) {
            computer.mouse = mouse;
            return this;
        }

        public Computer build() {

            if (computer.processor == null
            || computer.ram == null || computer.motherBoard == null
            || computer.graphicCard == null || computer.hardDisk == null) {

                throw new IllegalStateException("Some element of computer configuration is required" +
                        " but was not present.");
            }

            return computer;
        }
    }
}
