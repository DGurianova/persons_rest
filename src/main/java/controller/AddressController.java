package controller;

import dto.AddressDto;
import entity.Address;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import service.AddressService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }
    //Get the address by id

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public AddressDto get(@PathVariable Integer id) {
        return addressService.get(id);
    }

    //create a new Address
    @PostMapping
    public AddressDto create(@RequestBody AddressDto addressDto) {
        return addressService.create(addressDto);
    }

    //edit Address
    @PutMapping
    public void edit(@RequestBody AddressDto addressDto) {
        addressService.edit(addressDto);
    }

    //delete Address
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        addressService.remove(id);
    }

    @GetMapping
    public List<AddressDto> getAll(@RequestParam(required = false) Optional<Integer> personId) {
        return addressService.getAllByPersonId(personId);
    }
}
