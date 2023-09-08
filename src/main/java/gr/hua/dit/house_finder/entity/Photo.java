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

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] image;

    @ManyToOne
    @JoinColumn(name = "listing_id")
    private Listing listing;

    public Photo(byte[] image, Listing listing, String filename) {
        this.image = image;
        this.listing = listing;
        this.filename = filename;
    }

}
