package vampire.city.RabbitMQ;

public class CharacterEventPayload {
    String event;
    Integer id;

    public CharacterEventPayload() {
    }

    public CharacterEventPayload(String event, Integer id) {
        this.event = event;
        this.id = id;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
