package vampire.city.model;

import javax.persistence.*;

@Entity(name="clan")
@Table(name="clan")
public class Clan {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;
    @Column
    private String weakness;
    @Column
    private String discipline1;
    @Column
    private String discipline2;
    @Column
    private String discipline3;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getWeakness() {
        return weakness;
    }

    public String getDiscipline1() {
        return discipline1;
    }

    public String getDiscipline2() {
        return discipline2;
    }

    public String getDiscipline3() {
        return discipline3;
    }
}
