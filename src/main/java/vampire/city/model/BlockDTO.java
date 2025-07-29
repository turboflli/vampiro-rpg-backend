package vampire.city.model;

import java.util.List;

public class BlockDTO {
    public Integer id;
    public List<List<Integer>> coordinates;
    public String color;

    public BlockDTO() {}

    public BlockDTO(Integer id, List<List<Integer>> coordinates, String color) {
        this.id = id;
        this.coordinates = coordinates;
        this.color = color;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<List<Integer>> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<List<Integer>> coordinates) {
        this.coordinates = coordinates;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
