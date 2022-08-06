package comdido.hotelprovider.model.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="hotels")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class HotelEntity extends BaseEntity {

    @Column(name="name")
    private String name;

    @Column(name="address")
    private String address;

    @Column(name="image_url")
    private String imageUrl;

    @OneToMany(fetch=FetchType.EAGER, mappedBy = "hotel",
            targetEntity = RoomEntity.class, cascade=CascadeType.ALL)
    private List<RoomEntity> rooms;

    @Lob
    @Column(name="content")
    private String content;
}
