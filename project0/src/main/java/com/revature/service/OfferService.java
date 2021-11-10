package com.revature.service;

import com.revature.model.*;

import java.util.List;

public interface OfferService {

    void setUser(User user);

    void makeOffer(Offer offer);

    void acceptOffer(int offerId);

    void rejectOffer(int offerId);

    List<Offer> findMyOffer();

    List<Offer> findAllOffer();



}
