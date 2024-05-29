package entity;

public class AddressesDisplay {

    private Long id;
    private String addressName;
    private Long idUser;

    public AddressesDisplay(Long id, String addressName, Long idUser) {
        this.id = id;
        this.addressName = addressName;
        this.idUser = idUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "AddressesDisplay{" +
                "id=" + id +
                ", addressName='" + addressName + '\'' +
                ", idUser=" + idUser +
                '}';
    }
}
