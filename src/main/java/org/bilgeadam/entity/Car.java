package org.bilgeadam.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@NamedQuery(name = "Car.findRentedCars", query = "SELECT c FROM Car c JOIN c.rentals r")
@NamedQuery(name = "Car.findIdleCars", query = "SELECT c FROM Car c WHERE c NOT IN (SELECT r.car FROM Rental r)")
@Table(name = "tbl_cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String brand;
    private String model;
    private Integer year;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Rental> rentals;

    @Builder.Default
    private BaseEntity baseEntity = new BaseEntity();

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", rentals=" + rentals +
                '}';
    }
}
