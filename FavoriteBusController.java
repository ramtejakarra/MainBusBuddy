package com.MainBus.Controller;

import com.MainBus.Model.FavoriteBus;
import com.MainBus.Service.FavoriteBusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteBusController {

    @Autowired
    private FavoriteBusService favoriteBusService;

    @PostMapping
    public FavoriteBus addFavoriteBus(@RequestBody FavoriteBus favoriteBus) {
        return favoriteBusService.addFavoriteBus(favoriteBus);
    }

    @GetMapping("/{userId}")
    public List<FavoriteBus> getFavoriteBuses(@PathVariable Long userId) {
        return favoriteBusService.getFavoriteBusesByUserId(userId);
    }

    @DeleteMapping("/{userId}/{busId}")
    public void removeFavoriteBus(@PathVariable Long userId, @PathVariable String busId) {
        favoriteBusService.removeFavoriteBus(userId, busId);
    }
}
