package vampire.city.RabbitMQ;

import vampire.city.model.PlaceDTO;

public class PlaceEventPayload {
    private String event;
    private PlaceDTO place;
    private Integer id;

    public PlaceEventPayload() {
    }

    public PlaceEventPayload(String event, PlaceDTO place) {
        this.event = event;
        this.place = place;
    }

    public PlaceEventPayload(String event, Integer id) {
        this.event = event;
        this.id = id;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public PlaceDTO getPlace() {
        return place;
    }

    public void setPlace(PlaceDTO place) {
        this.place = place;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
