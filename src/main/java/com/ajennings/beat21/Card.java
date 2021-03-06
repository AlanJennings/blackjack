package com.ajennings.beat21;

public final class Card {
    public enum Suit {
        CLUB,
        SPADE,
        HEART,
        DIAMOND
    }

    public enum Number {
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5),
        SIX(6),
        SEVEN(7),
        EIGHT(8),
        NINE(9),
        TEN(10),
        JACK(10),
        QUEEN(10),
        KING(10),
        ACE(11);

        int value;

        Number(int value) {
            this.value = value;
        }
    }

    private Suit suit;
    private Number number;

    public Card(Suit suit, Number number) {

        this.suit = suit;
        this.number = number;
    }


    public Suit getSuit() {
        return suit;
    }

    public Number getNumber() {
        return number;
    }
}
