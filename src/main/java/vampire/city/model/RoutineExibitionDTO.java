package vampire.city.model;

public class RoutineExibitionDTO extends RoutineDTO{
    private String characterName;
    private String placeName;
    
    public RoutineExibitionDTO(Integer id, Integer characterId, Integer placeId, Integer weekday, String startTime, String endTime, String description, String characterName, String placeName) {
        super(id, characterId, placeId, weekday, startTime, endTime, description);
        this.characterName = characterName;
        this.placeName = placeName;
    }
    
    public String getCharacterName() {
        return characterName;
    }
    
    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }
    
    public String getPlaceName() {
        return placeName;
    }
    
    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }
}
