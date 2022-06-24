package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity

@NoArgsConstructor
@Getter
@Setter


public class Address {
    @Id
    @GeneratedValue
    private Integer id;

    private String address;

    private String zip;

    private String city;

    private String country;

    @ManyToOne
    private Person person;

    public Address(String address, String zip, String city, String country, Person person) {
        this.address = address;
        this.zip = zip;
        this.city = city;
        this.country = country;
        this.person = person;
    }
}

