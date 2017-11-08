package at.fhv.team05.Application;

import at.fhv.team05.domain.Address;
import at.fhv.team05.domain.IDomainObject;
import at.fhv.team05.dtos.AddressDTO;

public class AddressController extends BaseController<Address,AddressDTO> {

    public AddressController(Class<Address> addressClass) {
        super(addressClass);
    }

    @Override
    protected AddressDTO createDTO(Address object) {
        return null;
    }

    @Override
    protected boolean compareInput(Address object, AddressDTO addressDTO) {
        return false;
    }
}
