package vampire.city.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name="users")
@Table(name = "users")
public class User {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    @Column(name = "username")
    private String username;
    @Column(name = "senha")
    private String senha;
    @Column(name = "gm")
    private boolean gm;

    public User(String username, String senha, boolean gm) {
        this.username = username;
        this.senha = senha;
        this.gm = gm;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isGm() {
        return gm;
    }

    public void setGm(boolean gm) {
        this.gm = gm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (Id != user.Id) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        if (senha != null ? !senha.equals(user.senha) : user.senha != null) return false;
        return gm != user.gm;
    }

    public User() {
    }

    @Override
    public int hashCode() {
        int result = Id;
        result = 31 * result + username.hashCode();
        result = 31 * result + senha.hashCode();
        result = 31 * result + Boolean.hashCode(gm);
        return result;
    }
}
