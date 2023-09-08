package gr.hua.dit.house_finder.service.impl;

import gr.hua.dit.house_finder.entity.Listing;
import gr.hua.dit.house_finder.entity.User;
import gr.hua.dit.house_finder.filter.ListingFilter;
import gr.hua.dit.house_finder.repository.ListingRepository;
import gr.hua.dit.house_finder.service.ListingService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.criteria.Predicate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListingServiceImpl implements ListingService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ListingRepository listingRepository;

    @Override
    public List<Listing> getListingsByLister(User lister) {
        return listingRepository.findByLister(lister);
    }

    @Override
    public List<Listing> viewAll() {
        return (List<Listing>) listingRepository.findAll();
    }

    @Override
    public List<Listing> getFiltered(ListingFilter filter) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Listing> query = cb.createQuery(Listing.class);
        Root<Listing> root = query.from(Listing.class);
        List<Predicate> predicates = new ArrayList<>();


        if (filter.getMinPrice() != null) {
            predicates.add(cb.ge(root.get("price"), filter.getMinPrice()));
        }

        if (filter.getMaxPrice() != null) {
            predicates.add(cb.le(root.get("price"), filter.getMaxPrice()));
        }

        if (filter.getStartDate() != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("builtDate"), filter.getStartDate()));
        }

        if (filter.getEndDate() != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("builtDate"), filter.getEndDate()));
        }

        if (filter.getGivenStreet() != null && !filter.getGivenStreet().isEmpty()) {
            predicates.add(cb.like(root.get("street"), "%" + filter.getGivenStreet() + "%"));
        }

        if (filter.getGivenArea() != null && !filter.getGivenArea().isEmpty()) {
            predicates.add(cb.equal(root.get("area"), filter.getGivenArea()));
        }

        if (filter.getGivenAreaCode() != null) {
            predicates.add(cb.equal(root.get("areaCode"), filter.getGivenAreaCode()));
        }

        if (filter.getMinSquareMeters() != null) {
            predicates.add(cb.ge(root.get("squareMeters"), filter.getMinSquareMeters()));
        }

        if (filter.getMaxSquareMeters() != null) {
            predicates.add(cb.le(root.get("squareMeters"), filter.getMaxSquareMeters()));
        }

        if (filter.getGivenFloor() != null) {
            predicates.add(cb.equal(root.get("floor"), filter.getGivenFloor()));
        }

        if (filter.getGivenId() != null) {
            predicates.add(cb.equal(root.get("id"), filter.getGivenId()));
        }


        if (predicates.isEmpty()) {
            // No filters provided, create a query without a WHERE clause
            query.select(root);
        } else {
            // Apply the predicates to the query
            query.select(root).where(predicates.toArray(new Predicate[0]));
        }

        return entityManager.createQuery(query).getResultList();
    }
}