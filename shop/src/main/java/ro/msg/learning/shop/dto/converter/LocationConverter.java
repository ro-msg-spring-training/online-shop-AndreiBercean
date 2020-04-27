package ro.msg.learning.shop.dto.converter;

import ro.msg.learning.shop.dto.LocationDTO;
import ro.msg.learning.shop.model.Location;

public class LocationConverter {
    public static LocationDTO generateDTOFromEntity(Location location){
        return new LocationDTO(
                location.getLocationId(),
                location.getName(),
                location.getAddressCountry(),
                location.getAddressCity(),
                location.getAddressCounty(),
                location.getAddressStreetAddress()
        );
    }

    public static Location generateEntityFromDTO(LocationDTO locationDTO){
        return new Location(
                locationDTO.getLocationId(),
                locationDTO.getName(),
                locationDTO.getAddressCountry(),
                locationDTO.getAddressCity(),
                locationDTO.getAddressCounty(),
                locationDTO.getAddressStreetAddress()
        );
    }
}
