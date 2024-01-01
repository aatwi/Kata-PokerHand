package com.murex.ranking;

import com.murex.Hand;
import com.murex.Result;

import static com.murex.ResultHelper.aFlushWinningResult;
import static com.murex.ResultHelper.aTieResult;

public class Flush extends RankingCategory {

    public Flush(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
    }

    @Override
    public Result evaluate() {
        if (bothHandsHaveFlush()) {
            return getHigherHand();
        }

        if (noHandHasAFlush()) {
            return super.evaluate();
        }

        return isFlush(blackHand) ?
                aFlushWinningResult(blackHand, false) :
                aFlushWinningResult(whiteHand, false);
    }

    private boolean noHandHasAFlush() {
        return !isFlush(blackHand) && !isFlush(whiteHand);
    }

    private Result getHigherHand() {
        for (int index = 4; index >= 0; index--) {
            int cardComparison = blackHand.getCardAt(index).compareTo(whiteHand.getCardAt(index));
            if (cardComparison != 0) {
                return cardComparison > 0 ?
                        aFlushWinningResult(blackHand, true) :
                        aFlushWinningResult(whiteHand, true);
            }
        }
        return aTieResult();
    }

    private boolean bothHandsHaveFlush() {
        return isFlush(blackHand) && isFlush(whiteHand);
    }

    protected static boolean isFlush(Hand hand) {
        for (int index = 1; index < 5; index++) {
            if (!hand.getCardAt(index - 1).getSuite().equals(hand.getCardAt(index).getSuite())) {
                return false;
            }
        }
        return true;
    }
}