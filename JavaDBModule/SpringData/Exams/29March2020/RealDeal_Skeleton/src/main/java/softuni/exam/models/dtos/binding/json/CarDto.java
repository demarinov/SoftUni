package softuni.exam.models.dtos.binding.json;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.Date;

public class CarDto implements Serializable {

    @Expose
    @Length(min=2, max=20)
    private String make;
    //•	model – a char sequence (between 2 to 20 exclusive).
    @Expose
    @Length(min=2, max=20)
    private String model;
    //•	kilometers – a number (must be positive).
    @Expose
    @Min(value=0)
    private String kilometers;
    //•	registeredOn – a date.
    @Expose
    private String registeredOn;
    //The combination of make, model and kilometers makes a car unique.


    public CarDto() {
    }

    public CarDto(String make, String model, String kilometers, String registeredOn) {
        this.make = make;
        this.model = model;
        this.kilometers = kilometers;
        this.registeredOn = registeredOn;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getKilometers() {
        return kilometers;
    }

    public void setKilometers(String kilometers) {
        this.kilometers = kilometers;
    }

    public String getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(String registeredOn) {
        this.registeredOn = registeredOn;
    }
}
