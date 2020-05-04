package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.repository.LocationRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocationService {
    private final LocationRepository locationRepository;

    public Location findById(Location location){
        Optional<Location> locationFound  = locationRepository.findById(location.getLocationId());
        return locationFound.orElseGet(Location::new);
    }

    public List<Location> findAll(){
        return locationRepository.findAll();
    }

    public Location insert(Location location) {
        return locationRepository.save(location);
    }

    public Location update(Location location) {
        return locationRepository.save(location);
    }

    public Location delete(Location location) {
        locationRepository.deleteById(location.getLocationId());
        return location;
    }
}
