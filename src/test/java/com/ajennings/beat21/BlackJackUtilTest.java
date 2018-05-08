package com.ajennings.beat21;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BlackJackUtilTest {

    @Test
    public void testBuildPackOfCards()
    {
        List<Card> deck = BlackJackUtil.buildAndShuffleDeck();

        assertEquals(52, deck.size());
    }

    @Test
    public void testSumOfDeck()
    {
        // Given
        List<Card> deck = BlackJackUtil.buildAndShuffleDeck();

        // When
        int total = BlackJackUtil.calculateHand(new ArrayList<>(deck));

        // Then
        assertEquals(380, total);
    }

    @Test
    public void testSumOfNullHand()
    {
        // When
        int handTotal = BlackJackUtil.calculateHand(null);

        // Then
        assertEquals(handTotal, 0);
    }

    @Test
    public void testSumOfEmptyHand()
    {
        // Given
        List<Card> cards = new ArrayList<>();

        // When
        int handTotal = BlackJackUtil.calculateHand(cards);

        // Then
        assertEquals(handTotal, 0);
    }

    @Test
    public void dealSamBlackJack()
    {
        // Given
        List<Card> blackJackHand = new ArrayList<>();
        blackJackHand.add(new Card(Card.Suit.CLUB, Card.Number.JACK));
        blackJackHand.add(new Card(Card.Suit.DIAMOND, Card.Number.ACE));

        // When
        int handTotal = BlackJackUtil.calculateHand(blackJackHand);

        // Then
        assertEquals(handTotal, 21);
    }

    @Test
    public void dealDealer14()
    {
        // Given
        List<Card> dealerHand = new ArrayList<>();
        dealerHand.add(new Card(Card.Suit.DIAMOND, Card.Number.TEN));
        dealerHand.add(new Card(Card.Suit.DIAMOND, Card.Number.FOUR));

        // When
        int handTotal = BlackJackUtil.calculateHand(dealerHand);

        //Then
        assertEquals(handTotal, 14);
    }

    @Test
    public void doWeHaveBlackJack()
    {
        //Given
        List<Card> blackJackHand = new ArrayList<>();
        blackJackHand.add(new Card(Card.Suit.CLUB, Card.Number.JACK));
        blackJackHand.add(new Card(Card.Suit.DIAMOND, Card.Number.ACE));

        List<Card> dealerHand = new ArrayList<>();
        dealerHand.add(new Card(Card.Suit.DIAMOND, Card.Number.FIVE));
        dealerHand.add(new Card(Card.Suit.DIAMOND, Card.Number.FOUR));

        //Then
        assertTrue(BlackJackUtil.doWeHaveBlackJack(blackJackHand));
        assertFalse(BlackJackUtil.doWeHaveBlackJack(dealerHand));
    }

    @Test
    public void canWeDealACard()
    {
        // Given
        List<Card> deckHand = new ArrayList<>();
        deckHand.add(new Card(Card.Suit.CLUB, Card.Number.JACK));
        deckHand.add(new Card(Card.Suit.DIAMOND, Card.Number.ACE));

        List<Card> dealerHand = new ArrayList<>();
        dealerHand.add(new Card(Card.Suit.DIAMOND, Card.Number.FIVE));
        dealerHand.add(new Card(Card.Suit.DIAMOND, Card.Number.FOUR));

        //When
        BlackJackUtil.dealCard(deckHand,dealerHand);

        // Then
        assertEquals(dealerHand.size(), 3);
        assertEquals(BlackJackUtil.calculateHand(dealerHand), 19);
    }

    @Test
    public void canWeDealMultipleCards()
    {
        // Given
        List<Card> deckHand = new ArrayList<>();
        deckHand.add(new Card(Card.Suit.CLUB, Card.Number.JACK));
        deckHand.add(new Card(Card.Suit.DIAMOND, Card.Number.ACE));

        List<Card> dealerHand = new ArrayList<>();
        dealerHand.add(new Card(Card.Suit.DIAMOND, Card.Number.FIVE));
        dealerHand.add(new Card(Card.Suit.DIAMOND, Card.Number.FOUR));

        //When
        BlackJackUtil.dealCard(deckHand,dealerHand, 2);

        // Then
        assertEquals(dealerHand.size(), 4);
        assertEquals(BlackJackUtil.calculateHand(dealerHand), 30);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void canWeDealTooManyCards()
    {
        // Given
        List<Card> deckHand = new ArrayList<>();
        deckHand.add(new Card(Card.Suit.CLUB, Card.Number.JACK));
        deckHand.add(new Card(Card.Suit.DIAMOND, Card.Number.ACE));

        List<Card> dealerHand = new ArrayList<>();
        dealerHand.add(new Card(Card.Suit.DIAMOND, Card.Number.FIVE));
        dealerHand.add(new Card(Card.Suit.DIAMOND, Card.Number.FOUR));

        //When
        BlackJackUtil.dealCard(deckHand,dealerHand, 3);
    }

    @Test
    public void needsToDealTwoToWin()
    {
        // Given
        List<Card> deckHand = new ArrayList<>();
        deckHand.add(new Card(Card.Suit.CLUB, Card.Number.TWO));
        deckHand.add(new Card(Card.Suit.DIAMOND, Card.Number.SIX));

        List<Card> playerHand = new ArrayList<>();
        playerHand.add(new Card(Card.Suit.DIAMOND, Card.Number.FIVE));
        playerHand.add(new Card(Card.Suit.DIAMOND, Card.Number.FOUR));

        Integer scoreToBeat = 15;

        // When
        BlackJackUtil.dealHand(deckHand, playerHand, scoreToBeat);

        // Then
        assertEquals(playerHand.size(), 4);
        assertEquals(BlackJackUtil.calculateHand(playerHand), 17);
        assertEquals(deckHand.size(), 0);
    }

    @Test
    public void doesNotNeedToDrawToWin()
    {
        // Given
        List<Card> deckHand = new ArrayList<>();
        deckHand.add(new Card(Card.Suit.CLUB, Card.Number.TWO));
        deckHand.add(new Card(Card.Suit.DIAMOND, Card.Number.SIX));

        List<Card> playerHand = new ArrayList<>();
        playerHand.add(new Card(Card.Suit.DIAMOND, Card.Number.TEN));
        playerHand.add(new Card(Card.Suit.HEART, Card.Number.TEN));

        Integer scoreToBeat = 19;

        // When
        BlackJackUtil.dealHand(deckHand, playerHand, scoreToBeat);

        // Then
        assertEquals(playerHand.size(), 2);
        assertEquals(BlackJackUtil.calculateHand(playerHand), 20);
        assertEquals(deckHand.size(), 2);
    }

}
