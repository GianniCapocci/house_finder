package gr.hua.dit.house_finder.repository;

import gr.hua.dit.house_finder.entity.Listing;
import gr.hua.dit.house_finder.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ListingRepository extends JpaRepository<Listing, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Listing l " +
            "SET " +
            "   l.street = CASE WHEN :#{#updatedListing.street} IS NOT NULL THEN :#{#updatedListing.street} ELSE l.street END, " +
            "   l.area = CASE WHEN :#{#updatedListing.area} IS NOT NULL THEN :#{#updatedListing.area} ELSE l.area END, " +
            "   l.areaCode = CASE WHEN :#{#updatedListing.areaCode} IS NOT NULL THEN :#{#updatedListing.areaCode} ELSE l.areaCode END, " +
            "   l.price = CASE WHEN :#{#updatedListing.price} IS NOT NULL THEN :#{#updatedListing.price} ELSE l.price END, " +
            "   l.squareMeters = CASE WHEN :#{#updatedListing.squareMeters} IS NOT NULL THEN :#{#updatedListing.squareMeters} ELSE l.squareMeters END, " +
            "   l.builtDate = CASE WHEN :#{#updatedListing.builtDate} IS NOT NULL THEN :#{#updatedListing.builtDate} ELSE l.builtDate END, " +
            "   l.floor = CASE WHEN :#{#updatedListing.floor} IS NOT NULL THEN :#{#updatedListing.floor} ELSE l.floor END " +
            "WHERE l.id = :id")
    void updateListing(@Param("id") Long id, @Param("updatedListing") Listing updatedListing);

    List<Listing> findByLister(User lister);

}
