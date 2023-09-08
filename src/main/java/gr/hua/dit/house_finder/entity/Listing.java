package gr.hua.dit.house_finder.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="listings")
public class Listing
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String street;

    @Column
    private String area;

    @Column
    private int areaCode;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private int squareMeters;

    @Column(nullable = false)
    private LocalDate builtDate;

    @Column(nullable = false)
    private int floor;

    @OneToMany(mappedBy = "listing", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Photo> photos = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User lister;

}
