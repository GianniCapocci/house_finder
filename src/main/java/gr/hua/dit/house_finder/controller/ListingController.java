package gr.hua.dit.house_finder.controller;

import gr.hua.dit.house_finder.entity.Listing;
import gr.hua.dit.house_finder.entity.Photo;
import gr.hua.dit.house_finder.entity.User;
import gr.hua.dit.house_finder.filter.ListingFilter;
import gr.hua.dit.house_finder.repository.ListingRepository;
import gr.hua.dit.house_finder.repository.UserRepository;
import gr.hua.dit.house_finder.service.ListingService;
import gr.hua.dit.house_finder.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/listings")
public class ListingController {

    @Autowired
    private ListingRepository listingRepository;

    @Autowired
    private ListingService listingService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PhotoService photoService;

    @GetMapping("")
    public String showListingsPage(@ModelAttribute("filter") ListingFilter filter, Model model) {
        List<Listing> listings;

        if (filter.isEmpty()) {
            // Show all listings by default
            listings = listingService.viewAll();
        } else {
            // Apply filters and show filtered results
            listings = listingService.getFiltered(filter);
        }

        model.addAttribute("listings", listings);
        return "listings";
    }

    @PostMapping("/filtered")
    public String applyFilters(@ModelAttribute("filter") ListingFilter filter, Model model) {
        List<Listing> filteredListings = listingService.getFiltered(filter);
        filter.setGivenStreet("");
        model.addAttribute("listings", filteredListings);
        model.addAttribute("pageTitle", "Filtered Listings"); // Set the page title

        return "listings";
    }

    @GetMapping("/add-listing")
    public String showAddListingForm(Model model) {
        model.addAttribute("listing", new Listing());
        return "addlisting";
    }

    @PostMapping("/add-listing")
    public String addListing(@ModelAttribute Listing listing,
                             @RequestParam("files") List<MultipartFile> files,
                             RedirectAttributes redirectAttributes, Principal principal) throws IOException {

        User currentUser = userRepository.findByEmail(principal.getName());
        Listing newListing = new Listing();
        newListing.setStreet(listing.getStreet());
        newListing.setArea(listing.getArea());
        newListing.setAreaCode(listing.getAreaCode());
        newListing.setPrice(listing.getPrice());
        newListing.setSquareMeters(listing.getSquareMeters());
        newListing.setBuiltDate(listing.getBuiltDate());
        newListing.setFloor(listing.getFloor());
        newListing.setLister(currentUser);
        // Save the listing to get its ID
        Listing savedListing = listingRepository.save(newListing);

        //This is to ensure a file was given so that we don't create empty files in the uploads directory.
        if(files.get(0).getSize() > 0) {
            for (MultipartFile imageFile : files) {
                String filename = photoService.savePhoto(imageFile);
                Photo photo = new Photo(savedListing, filename);
                savedListing.getPhotos().add(photo);
            }
        }

        // Update the listing to include the associated images
        listingRepository.save(savedListing);

        redirectAttributes.addFlashAttribute("successMessage", "Listing added successfully.");
        return "redirect:/listings";
    }

    @GetMapping("/delete")
    public String showDeleteListingForm() {
        return "deletelisting";
    }

    @PostMapping("/delete")
    public String searchListingForDelete(@RequestParam Long listingId, Model model) {
        Listing listing = listingRepository.findById(listingId).orElse(null);
        model.addAttribute("listing", listing);
        return "deletelisting";
    }

    @PostMapping("/delete/{id}")
    public String deleteListing(@PathVariable Long id) {
        listingRepository.deleteById(id);
        return "redirect:/listings";
    }

    @GetMapping("/update")
    public String showUpdateForm() {
        return "updatelisting";
    }

    @PostMapping("/update")
    public String searchListingForUpdate(@RequestParam("listingId") Long listingId, Model model) {
        Listing listing = listingRepository.findById(listingId).orElse(null);
        model.addAttribute("listing", listing);
        return "updatelisting";
    }

    @PostMapping("/update/{id}")
    public String updateListing(@ModelAttribute Listing listing, @PathVariable Long id) {
        listingRepository.updateListing(id, listing);
        return "redirect:/listings";
    }
}
