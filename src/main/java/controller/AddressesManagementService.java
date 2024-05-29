package controller;

import db.DbAddressesOperations;
import entity.Addresses;
import entity.AddressesDisplay;

import java.util.List;

public class AddressesManagementService {

    public boolean insertInAddresses(Addresses addresses) {
        DbAddressesOperations dbAddressesOperations = new DbAddressesOperations();
        return dbAddressesOperations.insert(addresses);
    }
    public List<AddressesDisplay> readAddresses(Long idUser){
        DbAddressesOperations dbAddressesOperations = new DbAddressesOperations();
        return dbAddressesOperations.readAddressOfAUser(idUser);
    }
    public boolean deleteAddress(Long idAddressToBeDeleted){
        DbAddressesOperations dbAddressesOperations = new DbAddressesOperations();
        return dbAddressesOperations.deleteAddressFromDB(idAddressToBeDeleted);
    }

}
