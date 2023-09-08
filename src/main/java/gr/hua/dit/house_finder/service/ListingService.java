package gr.hua.dit.house_finder.service;

import gr.hua.dit.house_finder.entity.Listing;
import gr.hua.dit.house_finder.entity.User;
import gr.hua.dit.house_finder.filter.ListingFilter;

import java.util.List;

public interface ListingService {

    public List<Listing> getListingsByLister(User lister);

    public List<Listing> viewAll();

    public List<Listing> getFiltered(ListingFilter filter);

}
