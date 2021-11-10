package com.revature.service;

import com.revature.model.Item;
import com.revature.model.Offer;
import com.revature.model.User;
import com.revature.repository.OfferRepository;
import com.revature.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

public class OfferServiceTest {

    private OfferRepository offerRepository;
    private OfferService offerService;

    Offer offer =new Offer();

    User user=new User();

    @BeforeEach
    public void setup(){
        offerRepository= Mockito.mock(OfferRepository.class);
        offerService = new OfferServiceImpl(offerRepository);
    }

    @Test
    public void findAllOfferTest(){

        user.setUserId(1);
        Mockito.when(offerRepository.findAllOffer()).thenReturn(
                List.of(
                        new Offer(),
                        new Offer()
                )
        );
        offerService.setUser(user);
        List<Offer> offer = offerService.findAllOffer();
        Assertions.assertEquals(2, offer.size());
    }


    @Test
    public void findMyOfferTest(){

        user.setUserId(1);
        Mockito.when(offerRepository.findMineOffer(user.getUserId())).thenReturn(
                List.of(
                        new Offer(),
                        new Offer()
                )
        );
        offerService.setUser(user);
        List<Offer> offer = offerService.findMyOffer();
        Assertions.assertEquals(2, offer.size());
    }

    @Test
    public void rejectOfferTest(){

        offerService.rejectOffer(offer.getId());
        Mockito.verify(offerRepository, Mockito.times(1)).rejectOffer(offer.getId());
    }

    @Test
    public void acceptOfferTest(){

        offerService.acceptOffer(offer.getId());
        Mockito.verify(offerRepository, Mockito.times(1)).acceptOffer(offer.getId());
    }

    @Test
    public void makeOfferTest(){

        offerService.makeOffer(offer);
        Mockito.verify(offerRepository, Mockito.times(1)).makeOffer(offer, offer.getUserId());
    }

}
