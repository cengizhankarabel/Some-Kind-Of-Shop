package com.revature.service;

import com.revature.model.Offer;
import com.revature.model.User;
import com.revature.repository.OfferRepository;
import org.apache.log4j.Logger;

import java.util.List;

public class OfferServiceImpl implements OfferService{

    private static final Logger logger=Logger.getLogger("shoppingCenter");


    private OfferRepository offerRepository;
    private User user;

    public OfferServiceImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void makeOffer(Offer offer) {
        logger.info("Offer sent to Database...");
        offerRepository.makeOffer(offer, user.getUserId());


    }

    @Override
    public void acceptOffer(int offerId) {
        logger.info("Offer accepting");
        offerRepository.acceptOffer(offerId);

    }

    @Override
    public void rejectOffer(int offerId) {
        logger.info("Offer rejecting");
        offerRepository.rejectOffer(offerId);
    }

    @Override
    public List<Offer> findAllOffer() {
        return offerRepository.findAllOffer();
    }

    @Override
    public List<Offer> findMyOffer() {
        return offerRepository.findMineOffer(user.getUserId());
    }
}
