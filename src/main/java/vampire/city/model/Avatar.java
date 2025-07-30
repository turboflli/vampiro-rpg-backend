package vampire.city.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "avatar")
@Table(name = "avatar")
public class Avatar {
    @Id
    private Integer id;
    private String hairColor;
    private String skinColor;
    private String accessory;
    private String accessoryColor;
    private String clotheColor;
    private String clothing;
    private String eyebrows;
    private String top;
    private String mouth;
    private String facialHair;
    private String eyeColor;

    public Avatar() {
    }

    public Avatar(Integer id, String hairColor, String skinColor, String accessory, String accessoryColor, String clotheColor, String clothing, String eyebrows, String top, String mouth, String facialHair, String eyeColor) {
        this.id = id;
        this.hairColor = hairColor;
        this.skinColor = skinColor;
        this.accessory = accessory;
        this.accessoryColor = accessoryColor;
        this.clotheColor = clotheColor;
        this.clothing = clothing;
        this.eyebrows = eyebrows;
        this.top = top;
        this.mouth = mouth;
        this.facialHair = facialHair;
        this.eyeColor = eyeColor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public String getSkinColor() {
        return skinColor;
    }

    public void setSkinColor(String skinColor) {
        this.skinColor = skinColor;
    }

    public String getAccessory() {
        return accessory;
    }

    public void setAccessory(String accessory) {
        this.accessory = accessory;
    }

    public String getAccessoryColor() {
        return accessoryColor;
    }

    public void setAccessoryColor(String accessoryColor) {
        this.accessoryColor = accessoryColor;
    }

    public String getClotheColor() {
        return clotheColor;
    }

    public void setClotheColor(String clotheColor) {
        this.clotheColor = clotheColor;
    }

    public String getClothing() {
        return clothing;
    }

    public void setClothing(String clothing) {
        this.clothing = clothing;
    }

    public String getEyebrows() {
        return eyebrows;
    }

    public void setEyebrows(String eyebrows) {
        this.eyebrows = eyebrows;
    }

    public String getTop() {
        return top;
    }

    public void setTop(String top) {
        this.top = top;
    }

    public String getMouth() {
        return mouth;
    }

    public void setMouth(String mouth) {
        this.mouth = mouth;
    }

    public String getFacialHair() {
        return facialHair;
    }

    public void setFacialHair(String facialHair) {
        this.facialHair = facialHair;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Avatar avatar = (Avatar) o;
        return id.equals(avatar.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
