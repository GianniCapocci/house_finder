package gr.hua.dit.house_finder.controller;

import gr.hua.dit.house_finder.entity.Listing;
import gr.hua.dit.house_finder.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import gr.hua.dit.house_finder.repository.UserRepository;
import gr.hua.dit.house_finder.service.ListingService;

import java.util.List;

@Controller
@RequestMapping("/lister")
public class ListerController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ListingService listingService;

    @GetMapping("/{username}")
    public String getUserListings(Model model, @PathVariable String username) {
        User lister = userRepository.findByName(username);
        if (lister != null) {
            List<Listing> userListings = listingService.getListingsByLister(lister);
            model.addAttribute("userListings", userListings);
            model.addAttribute("lister", lister);
            return "userlistings";
        }
        return "redirect:/listings";
    }
}
