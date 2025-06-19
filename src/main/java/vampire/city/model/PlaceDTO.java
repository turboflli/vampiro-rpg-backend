package vampire.city.model;

import java.util.List;
import java.util.Objects;

public class PlaceDTO {
    private Integer id;
    private String name;
    private String description;
    private float x_coordinate;
    private float y_coordinate;
    private byte[] image;
    private Integer domainId;
    private List<SubPlaceDTO> subPlaces;

    public PlaceDTO(Integer id, String name, String description, float x_coordinate, float y_coordinate, byte[] image, Integer domain_id, List<SubPlaceDTO> subPlaces) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.x_coordinate = x_coordinate;
        this.y_coordinate = y_coordinate;
        this.image = image;
        this.domainId = domain_id;
        this.subPlaces = subPlaces;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getX_coordinate() {
        return x_coordinate;
    }

    public void setX_coordinate(float x_coordinate) {
        this.x_coordinate = x_coordinate;
    }

    public float getY_coordinate() {
        return y_coordinate;
    }

    public void setY_coordinate(float y_coordinate) {
        this.y_coordinate = y_coordinate;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Integer getDomainId() {
        return domainId;
    }

    public void setDomainId(Integer domain_id) {
        this.domainId = domain_id;
    }

    public List<SubPlaceDTO> getSubPlaces() {
        return subPlaces;
    }

    public void setSubPlaces(List<SubPlaceDTO> subPlaces) {
        this.subPlaces = subPlaces;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlaceDTO placeDTO = (PlaceDTO) o;

        if (!Objects.equals(id, placeDTO.id)) return false;
        if (!Objects.equals(name, placeDTO.name)) return false;
        if (!Objects.equals(description, placeDTO.description)) return false;
        if (!Objects.equals(x_coordinate, placeDTO.x_coordinate)) return false;
        if (!Objects.equals(y_coordinate, placeDTO.y_coordinate)) return false;
        if (!Objects.equals(image, placeDTO.image)) return false;
        if (!Objects.equals(domainId, placeDTO.domainId)) return false;
        if (!Objects.equals(subPlaces, placeDTO.subPlaces)) return false;
        return true;
    }
    
}
    