package service;

import dto.AddressDto;
import entity.Address;
import entity.Person;
import exceptions.AddressNotFoundException;
import exceptions.PersonNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import repository.AddressRepository;
import repository.PersonRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
    private final PersonRepository personRepository;


    public AddressDto get(Integer id) {
        return addressRepository.findById(id)
                .map(this::mapAddressToDto)
                .orElseThrow(() -> new AddressNotFoundException("There is no such address"));
    }

    private AddressDto mapAddressToDto(Address address) {
        return new AddressDto(
                address.getId(),
                address.getAddress(),
                address.getZip(),
                address.getCity(),
                address.getCountry(),
                address.getPerson().getId()
        );
    }

    public AddressDto create(AddressDto addressDto) {
        Person person = personRepository.findById(addressDto.getPersonId()).orElseThrow(() -> new PersonNotFoundException("There is no person with such id"));

        Address address = new Address(
                addressDto.address,
                addressDto.zip,
                addressDto.city,
                addressDto.country,
                person
        );
        return mapAddressToDto(addressRepository.save(address));
    }

    public void edit(AddressDto addressDto) {
        Address address = addressRepository.findById(addressDto.id).orElseThrow(() -> new AddressNotFoundException("There is no such address"));

        address.setCity(addressDto.city);
        address.setCountry(addressDto.country);
        address.setZip(addressDto.zip);
        address.setAddress(addressDto.address);

        addressRepository.save(address);
    }

    public void remove(int id) {
        addressRepository.deleteById(id);
    }

    public List<AddressDto> getAllByPersonId(Optional<Integer> personId) {
        List<Address> addresses;
        if (personId.isPresent())
            addresses = addressRepository.returnAllByPersonId(personId.get());
        else
            addresses = addressRepository.findAll();

        return addresses.stream()
                .map(this::mapAddressToDto)
                .collect(Collectors.toList());

    }

}
