package entity;

public class User {
    private long id;
    private String username;
    private String password;
    private boolean isActive;

    private boolean isBuyer;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isBuyer() {
        return isBuyer;
    }

    public void setBuyer(boolean buyer) {
        isBuyer = buyer;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isActive=" + isActive +
                ", isBuyer=" + isBuyer +
                '}';
    }

    public User(String username, String password, boolean isActive, boolean isBuyer) {
        this.username = username;
        this.password = password;
        this.isActive = isActive;
        this.isBuyer = isBuyer;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, boolean isBuyer) {
        this.username = username;
        this.password = password;
        this.isBuyer = isBuyer;
    }

    public User(long id) {
        this.id = id;
    }
}
