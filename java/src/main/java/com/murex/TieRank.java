package com.murex;

import java.util.Optional;

public class TieRank extends PokerHandRank {
    public TieRank(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
    }

    @Override
    public Optional<String> verify() {
        Card[] blackCards = this.blackHand.getCards();
        Card[] whiteCards = this.blackHand.getCards();

        for (int index = 0; index < blackCards.length; index++) {
            if (blackCards[index].compareTo(whiteCards[index]) != 0) {
                return Optional.empty();
            }
        }
        return Optional.of("Tie.");
    }

}
