package com.ajennings.beat21;

import java.util.*;

public final class BlackJackUtil {

    protected static final int MAX_SCORE = 21;

    public static List<Card> buildAndShuffleDeck()
    {
        List<Card> myDeck = new ArrayList<>();

        for (Card.Suit suit: Card.Suit.values())
        {
            for (Card.Number number: Card.Number.values())
            {
                myDeck.add(new Card(suit, number));
            }
        }

        Collections.shuffle(myDeck);

        return myDeck;
    }


    public static int calculateHand(List<Card> deckToProcess)
    {
        if (deckToProcess == null)
            return 0;

        int total = deckToProcess.stream()
                .mapToInt(card -> card.getNumber().value)
                .sum();

        return total;
    }

    public static boolean doWeHaveBlackJack(List<Card> hand)
    {
        if (hand == null)
            return false;

        return hand.size() == 2 && calculateHand(hand) == MAX_SCORE;
    }

    public static void outputHand(List<Card> hand)
    {
        hand.forEach(card -> System.out.println(String.format("Card Suit [%s], Card Value [%s]", card.getSuit().name(), card.getNumber().name())));
    }

    public static void dealCard(List<Card> deckToDealFrom, List<Card> cardsToDealTo, int numberOfCards)
    {
        if (numberOfCards > deckToDealFrom.size())
        {
            throw new ArrayIndexOutOfBoundsException("Not enough cards to deal from");
        }

        for (int i = 0; i < numberOfCards; i++)
        {
            dealCard(deckToDealFrom, cardsToDealTo);
        }
    }

    public static void dealCard(List<Card> deckToDealFrom, List<Card> cardsToDealTo)
    {
        cardsToDealTo.add(deckToDealFrom.remove(0));
    }

    public static Integer dealHand(List<Card> deckToDealFrom, List<Card> cardsToDealTo, Integer totalToBeat)
    {
        Integer playerTotal = calculateHand(cardsToDealTo);

        while (playerTotal <= totalToBeat)
        {
            BlackJackUtil.dealCard(deckToDealFrom, cardsToDealTo);
            playerTotal = BlackJackUtil.calculateHand(cardsToDealTo);

            if (playerTotal > MAX_SCORE)
            {
                break;
            }
        }

        return playerTotal;
    }

}
