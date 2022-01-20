package softuni.exam.instagraphlite.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Table(name="pictures")
public class Picture extends BaseEntity{

    // •	id – integer number, primary identification field, auto increment.
    //•	path – a char sequence. Cannot be null. The path is unique.
    @Column(name="path", nullable = false, unique = true)
    private String path;
    //•	size – a floating point number. Cannot be null.
    @Column(name = "size", nullable = false)
    @Min(value = 500)
    @Max(value=60000)
    private Double size;
    // Must be between 500 and 60000 (both numbers are INCLUSIVE)


    public Picture() {
    }

    public Picture(String path, @Size(min = 500, max = 60000) Double size) {
        this.path = path;
        this.size = size;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }
}
