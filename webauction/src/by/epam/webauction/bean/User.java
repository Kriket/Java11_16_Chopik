package by.epam.webauction.bean;

public class User {
    private String password;
    private String nickname;
    private String email;
    private Integer id;
    private Boolean admin;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean isAdmin() {
        return admin;
    }

    public void setIsAdmin(Boolean admin) {
        this.admin = admin;
    }
}
