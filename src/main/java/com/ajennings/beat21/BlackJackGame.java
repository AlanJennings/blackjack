package com.ajennings.beat21;

import java.util.ArrayList;
import java.util.List;

public class BlackJackGame {
    private List<Card> dealersHand = new ArrayList<>();
    private List<Card> samsHand = new ArrayList<>();
    private final int samsLowerLimit = 17;
    private Integer samsTotal = 0;
    private Integer dealersTotal = 0;

    public BlackJackGame() {
    }

    public static void main(String[] args) {
        BlackJackGame myGame = new BlackJackGame();
        System.out.println(myGame.playGame());
    }

    public String playGame() {

        List<Card> deck = BlackJackUtil.buildAndShuffleDeck();

        BlackJackUtil.dealCard(deck, samsHand, 2);

        System.out.println("====Sams hand=====");
        BlackJackUtil.outputHand(samsHand);

        BlackJackUtil.dealCard(deck, dealersHand, 2);

        System.out.println("====Dealers hand=====");
        BlackJackUtil.outputHand(dealersHand);


        // does dealer have blackjack
        if (BlackJackUtil.doWeHaveBlackJack(dealersHand))
        {
            return "Dealer wins with Blackjack";
        }
        else if (BlackJackUtil.doWeHaveBlackJack(samsHand))
        {
            return "Sam wins with Blackjack";
        }

        //calculate the values of cards
        samsTotal = BlackJackUtil.calculateHand(samsHand);
        dealersTotal = BlackJackUtil.calculateHand(dealersHand);

        // apply sams logic to take a card if under 17
        samsTotal = BlackJackUtil.dealHand(deck, samsHand, samsLowerLimit);
        if (samsTotal > BlackJackUtil.MAX_SCORE)
        {
            System.out.println("====Sams hand=====");
            BlackJackUtil.outputHand(samsHand);
            return String.format("Dealer Wins [%s], Sam is Bust [%s]", dealersTotal, samsTotal);
        }

        System.out.println("====Sams hand=====");
        BlackJackUtil.outputHand(samsHand);


        // when sam is done, pull dealer cards if game not over
        dealersTotal = BlackJackUtil.dealHand(deck, dealersHand, samsTotal -1);
        if (dealersTotal > BlackJackUtil.MAX_SCORE)
        {
            System.out.println("====Dealers hand=====");
            BlackJackUtil.outputHand(dealersHand);
            return String.format("Dealer is Bust [%s], Sam Wins [%s]", dealersTotal, samsTotal);
        }

        System.out.println("====Dealers hand=====");
        BlackJackUtil.outputHand(dealersHand);


        return String.format("Dealer Wins [%s], Sam Loses [%s]", dealersTotal, samsTotal);
    }


}
