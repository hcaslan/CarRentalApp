package org.bilgeadam.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.bilgeadam.entity.Car;
import org.bilgeadam.entity.Rental;
import org.bilgeadam.utility.OutputHelper;

import java.sql.Date;
import java.util.List;

public class CarRepository extends RepositoryManager<Car,Long>{
    public CarRepository() {
        super(Car.class);
    }

    public List<Car> searchCarByKeyword(String keyword) {
        String jpql = "SELECT c FROM Car c WHERE c.brand LIKE :keyword OR c.model LIKE :keyword";
        TypedQuery<Car> query = getEntityManager().createQuery(jpql, Car.class);
        query.setParameter("keyword", "%" + keyword + "%");
        return query.getResultList();
    }

    public List<Car> listCarsOnRent() {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = null;
        List<Car> rentedCars = null;
        try {
            transaction = em.getTransaction();
            transaction.begin();

            Query query = em.createNamedQuery("Car.findRentedCars");
            rentedCars = query.getResultList();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            OutputHelper.errorMessage(this.getClass().getName() + "/listCarsOnRent");
        } finally {
            em.close();
        }

        return rentedCars;
    }

    public List<Car> listIdleCars() {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = null;
        List<Car> idleCars = null;
        try {
            transaction = em.getTransaction();
            transaction.begin();

            Query query = em.createNamedQuery("Car.findIdleCars");
            idleCars = query.getResultList();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            OutputHelper.errorMessage(this.getClass().getName() + "/listIdleCars");
        } finally {
            em.close();
        }

        return idleCars;
    }

    public List<Car> listCarsRentedBySpecificUser(Long userId) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = null;
        List<Car> rentedCars = null;

        try {
            transaction = em.getTransaction();
            transaction.begin();

            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Car> query = cb.createQuery(Car.class);
            Root<Car> carRoot = query.from(Car.class);
            Join<Car, Rental> rentalJoin = carRoot.join("rentals");
            query.select(carRoot).where(cb.equal(rentalJoin.get("user").get("id"), userId));

            rentedCars = em.createQuery(query).getResultList();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            OutputHelper.errorMessage(this.getClass().getName() + "/listCarsRentedBySpecificUser");
        } finally {
            em.close();
        }
        return rentedCars;
    }
    public List<Car> findAvailableCars(Date startDate, Date endDate) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = null;
        List<Car> availableCars = null;

        try {
            transaction = em.getTransaction();
            transaction.begin();

            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Car> query = cb.createQuery(Car.class);
            Root<Car> carRoot = query.from(Car.class);

            Subquery<Long> subquery = query.subquery(Long.class);
            Root<Rental> rentalRoot = subquery.from(Rental.class);
            subquery.select(cb.count(rentalRoot.get("id")));
            subquery.where(
                    cb.and(
                            cb.equal(rentalRoot.get("car"), carRoot),
                            cb.or(
                                    cb.between(rentalRoot.get("startDate"), startDate, endDate),
                                    cb.between(rentalRoot.get("endDate"), startDate, endDate),
                                    cb.and(
                                            cb.lessThanOrEqualTo(rentalRoot.get("startDate"), startDate),
                                            cb.greaterThanOrEqualTo(rentalRoot.get("endDate"), endDate)
                                    )
                            )
                    )
            );

            query.select(carRoot).where(cb.equal(subquery, 0L));

            availableCars = em.createQuery(query).getResultList();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            OutputHelper.errorMessage(this.getClass().getName() + "/findAvailableCars");
        } finally {
            em.close();
        }
        return availableCars;
    }
}
