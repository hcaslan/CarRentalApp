package org.bilgeadam.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.sql.Date;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_rentals")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Date startDate;
    private Date endDate;

    @Builder.Default
    private BaseEntity baseEntity = new BaseEntity();

    @Override
    public String toString() {
        return "Rental{" +
                "id=" + id +
                ", car_brand=" + car.getBrand() +
                ", car_model=" + car.getModel() +
                ", car_year=" + car.getYear() +
                ", user=" + user.getUsername() +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
