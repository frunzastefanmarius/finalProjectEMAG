package entity;

public class Addresses {
    private long id;
    private String address;
    private long idUser;

    public Addresses(String address, long idUser) {
        this.address = address;
        this.idUser = idUser;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "Addresses{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", idUser=" + idUser +
                '}';
    }
}
