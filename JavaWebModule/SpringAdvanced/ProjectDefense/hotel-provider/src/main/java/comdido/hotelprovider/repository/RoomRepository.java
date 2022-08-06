package comdido.hotelprovider.repository;

import comdido.hotelprovider.model.entity.RoomEntity;
import comdido.hotelprovider.model.enums.RoomTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, Long> {

    Optional<RoomEntity> findFirstByRoomTypeAndIsFree(RoomTypeEnum roomTypeEnum, boolean isFree);

    List<RoomEntity> findAllByRoomTypeAndIsFree(RoomTypeEnum roomTypeEnum, boolean isFree);
}
