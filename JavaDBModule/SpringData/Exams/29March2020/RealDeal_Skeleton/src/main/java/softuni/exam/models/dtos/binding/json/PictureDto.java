package softuni.exam.models.dtos.binding.json;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

public class PictureDto implements Serializable {

    @Expose
    @Size(min=2, max=20)
    private String name;
    //•	dateAndTime – The date and time of a picture.
    @Expose
    private String dateAndTime;
    @Expose
    private Long car;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return dateAndTime;
    }

    public void setDate(String date) {
        this.dateAndTime = date;
    }

    public Long getCar() {
        return car;
    }

    public void setCar(Long car) {
        this.car = car;
    }
}
