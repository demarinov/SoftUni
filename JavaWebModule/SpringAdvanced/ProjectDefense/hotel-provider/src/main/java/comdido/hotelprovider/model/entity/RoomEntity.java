package comdido.hotelprovider.model.entity;

import comdido.hotelprovider.model.enums.RoomTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name="rooms")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class RoomEntity extends BaseEntity {

    @Column(name="number")
    private String number;

    @Column(name="room_type")
    @Enumerated(EnumType.STRING)
    private RoomTypeEnum roomType;

    @Column(name="is_free")
    private boolean isFree;

    @ManyToOne()
    @JoinColumn(name="hotel_id", referencedColumnName = "id")
    private HotelEntity hotel;

    @Column(name="price")
    private double price;

    @Column(name="image_url")
    private String imageUrl;

}
