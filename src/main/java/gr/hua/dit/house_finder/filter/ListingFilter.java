package gr.hua.dit.house_finder.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
public class ListingFilter {

    private Double minPrice;
    private Double maxPrice;
    LocalDate startDate;
    LocalDate endDate;
    String givenStreet;
    String givenArea;
    Integer givenAreaCode;
    Integer minSquareMeters;
    Integer maxSquareMeters;
    Integer givenFloor;
    Double givenId;

    public boolean isEmpty() {
        return minPrice == null &&
                maxPrice == null &&
                startDate == null &&
                endDate == null &&
                givenStreet == null &&
                givenArea == null &&
                givenAreaCode == null &&
                minSquareMeters == null &&
                maxSquareMeters == null &&
                givenFloor == null &&
                givenId == null;
    }
}
