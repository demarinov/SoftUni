package entities;

import javax.persistence.*;

@Entity
@Table(name="labels")
public class BasicLabel implements Label{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;


    @Basic
    private String title;

    @Basic
    private String subTitle;

    @OneToOne(mappedBy = "label", targetEntity = BasicShampoo.class
    , cascade = CascadeType.ALL)
    private BasicShampoo basicShampoo;

    public BasicLabel() {
    }

    public BasicLabel(String title, String subTitle) {
        this.title = title;
        this.subTitle = subTitle;
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public void setId(int id) {

    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public void setTitle(String title) {

    }

    @Override
    public String getSubtitle() {
        return null;
    }

    @Override
    public void setSubTitle(String subTitle) {

    }
}
