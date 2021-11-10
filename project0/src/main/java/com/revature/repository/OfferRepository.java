package com.revature.repository;

import com.revature.model.*;

import java.util.List;

public interface OfferRepository {


    void makeOffer(Offer offer, int userId);

    void acceptOffer(int offerId);

    void rejectOffer(int offerId);

    List<Offer> findAllOffer();

    List<Offer> findMineOffer(int userId);


}
