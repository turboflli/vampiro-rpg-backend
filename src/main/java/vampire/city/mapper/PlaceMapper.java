package vampire.city.mapper;

import vampire.city.model.Domain;
import vampire.city.model.Place;
import vampire.city.model.PlaceDTO;
import vampire.city.model.SubPlace;
import vampire.city.model.SubPlaceDTO;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
@Component
public class PlaceMapper {
    public PlaceDTO toDTO(Place place) {
        List<SubPlaceDTO> subPlaces = new ArrayList<>();
        for (SubPlace subPlace : place.getSubPlaces()) {
            subPlaces.add(new SubPlaceDTO(subPlace.getName(), subPlace.getDescription()));
        }
        Integer domainId = place.getDomain() != null ? place.getDomain().getId() : null;
        return new PlaceDTO(place.getId(), place.getName(), place.getDescription(), place.getX_coordinate(), place.getY_coordinate(), place.getImage(), place.getType(), domainId, subPlaces);
    }
    public Place fromDTO(PlaceDTO dto, Domain domain) {
        Place place = new Place();
        place.setId(dto.getId());
        place.setName(dto.getName());
        place.setDescription(dto.getDescription());
        place.setX_coordinate(dto.getX_coordinate());
        place.setY_coordinate(dto.getY_coordinate());
        place.setImage(dto.getImage());
        place.setType(dto.getType());
        place.setDomain(domain);
        List<SubPlace> subPlaces = new ArrayList<>();
        if(dto.getSubPlaces() != null){
            for (SubPlaceDTO subPlaceDTO : dto.getSubPlaces()) {
                subPlaces.add(new SubPlace(subPlaceDTO.getName(), subPlaceDTO.getDescription(), place));
            }
        }
        place.setSubPlaces(subPlaces);
        return place;
    }
}
