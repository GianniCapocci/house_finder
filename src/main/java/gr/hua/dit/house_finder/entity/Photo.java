package gr.hua.dit.house_finder.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String filename;

    @ManyToOne
    @JoinColumn(name = "listing_id")
    private Listing listing;

    public Photo(Listing listing, String filename) {
        this.listing = listing;
        this.filename = filename;
    }

}
