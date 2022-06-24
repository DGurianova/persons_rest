package dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

    public Integer id;
    public String address;
    public String zip;
    public String city;
    public String country;
    public int personId;


}
