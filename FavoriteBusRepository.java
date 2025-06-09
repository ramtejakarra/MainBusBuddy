package com.MainBus.Repository;

import com.MainBus.Model.FavoriteBus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FavoriteBusRepository extends JpaRepository<FavoriteBus, Long> {
    List<FavoriteBus> findByUserId(Long userId);
    void deleteByUserIdAndBusId(Long userId, String busId);
}
