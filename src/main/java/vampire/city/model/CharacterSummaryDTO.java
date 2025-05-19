package vampire.city.model;

public class CharacterSummaryDTO {
    private int id;
    private String name;
    private String clanName;
    private String roadName;
    private int generation;

    public CharacterSummaryDTO(int id, String name, String clanName, String roadName, int generation) {
        this.id = id;
        this.name = name;
        this.clanName = clanName;
        this.roadName = roadName;
        this.generation = generation;
    }

    public CharacterSummaryDTO() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClanName() {
        return clanName;
    }

    public void setClanName(String clanName) {
        this.clanName = clanName;
    }

    public String getRoadName() {
        return roadName;
    }

    public void setRoadName(String roadName) {
        this.roadName = roadName;
    }

    public int getGeneration() {
        return generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }
}
