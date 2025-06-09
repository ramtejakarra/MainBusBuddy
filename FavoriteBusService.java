package com.MainBus.Service;

import com.MainBus.Model.FavoriteBus;
import com.MainBus.Repository.FavoriteBusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FavoriteBusService {

    @Autowired
    private FavoriteBusRepository favoriteBusRepository;

    public FavoriteBus addFavoriteBus(FavoriteBus favoriteBus) {
        return favoriteBusRepository.save(favoriteBus);
    }

    public List<FavoriteBus> getFavoriteBusesByUserId(Long userId) {
        return favoriteBusRepository.findByUserId(userId);
    }

    public void removeFavoriteBus(Long userId, String busId) {
        favoriteBusRepository.deleteByUserIdAndBusId(userId, busId);
    }
}
