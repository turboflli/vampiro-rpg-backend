package vampire.city.model;

import javax.persistence.*;

@Entity(name="road")
@Table(name="road")
public class Road {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;
    @Column
    private String pathName;
    @Column
    private boolean useConscience = true;//falso quer dizer que usa conviction
    @Column
    private boolean useSelf_control = true;//falso quer dizer que usa instinction
    @Column
    private String ethics;
    @Column
    private String sins;
    @Column
    private String aura;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPathName() {
        return pathName;
    }

    public boolean isUseConscience() {
        return useConscience;
    }

    public boolean isUseSelf_control() {
        return useSelf_control;
    }

    public String getEthics() {
        return ethics;
    }

    public String getSins() {
        return sins;
    }

    public String getAura() {
        return aura;
    }
}
