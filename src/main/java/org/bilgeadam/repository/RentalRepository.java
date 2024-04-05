package org.bilgeadam.repository;

import org.bilgeadam.entity.Rental;

public class RentalRepository extends RepositoryManager<Rental,Long> {
    public RentalRepository() {
        super(Rental.class);
    }

}
