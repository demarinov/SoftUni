package softuni.exam.instagraphlite.models.dtos.json;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.UniqueElements;
import softuni.exam.instagraphlite.annotation.UniqueField;

import javax.persistence.Column;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class PictureJsonDto implements Serializable {

    @Expose
    @NotNull
    private String path;
    //•	size – a floating point number. Cannot be null.
    @Expose
    @Min(value= 500)
    @Max(value=60000)
    @NotNull
    private String size;

    public PictureJsonDto() {
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
