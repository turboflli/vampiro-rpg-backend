package vampire.city.mapper;

import vampire.city.model.Domain;
import vampire.city.model.Place;
import vampire.city.model.PlaceDTO;
import vampire.city.model.SubPlace;
import vampire.city.model.SubPlaceDTO;
import java.util.ArrayList;
import java.util.List;
public class PlaceMapper {
    public PlaceDTO toDTO(Place place) {
        List<SubPlaceDTO> subPlaces = new ArrayList<>();
        for (SubPlace subPlace : place.getSubPlaces()) {
            subPlaces.add(new SubPlaceDTO(subPlace.getId(), subPlace.getName(), subPlace.getDescription()));
        }
        return new PlaceDTO(place.getId(), place.getName(), place.getDescription(), place.getX_coordinate(), place.getY_coordinate(), place.getImage(), place.getDomain().getId(), subPlaces);
    }
    public Place fromDTO(PlaceDTO dto, Domain domain) {
        Place place = new Place();
        place.setId(dto.getId());
        place.setName(dto.getName());
        place.setDescription(dto.getDescription());
        place.setDomain(domain);
        place.setX_coordinate(dto.getX_coordinate());
        place.setY_coordinate(dto.getY_coordinate());
        place.setImage(dto.getImage());
        List<SubPlace> subPlaces = new ArrayList<>();
        for (SubPlaceDTO subPlaceDTO : dto.getSubPlaces()) {
            subPlaces.add(new SubPlace(subPlaceDTO.getId(), subPlaceDTO.getName(), subPlaceDTO.getDescription(), place));
        }
        place.setSubPlaces(subPlaces);
        return place;
    }
}
