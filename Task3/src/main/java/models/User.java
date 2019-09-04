package models;

import java.util.Objects;

public class User {
    //сервлет
    //модель, дао(+запрос поиск по юзернэйму), сервис(+метод ищет юзера по логину и сравнивает пароли возвращает true или false), маппер

    private long id;
    private String userName;
    private String password;
    private Entrant entrant;



    public User(long id, String userName, String password, Entrant entrant) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.entrant = entrant;
    }

    public User(String userName, String password, Entrant entrant) {
        this.userName = userName;
        this.password = password;
        this.entrant = entrant;
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Entrant getEntrant() {
        return entrant;
    }

    public void setEntrant(Entrant entrant) {
        this.entrant = entrant;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId() == user.getId() &&
                Objects.equals(getUserName(), user.getUserName()) &&
                Objects.equals(getPassword(), user.getPassword()) &&
                Objects.equals(getEntrant(), user.getEntrant());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserName(), getPassword(), getEntrant());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", entrant=" + entrant +
                '}';
    }
}
