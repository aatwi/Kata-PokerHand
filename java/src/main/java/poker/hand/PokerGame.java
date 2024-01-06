/*
Copyright (c) 2023 Murex

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

package com.murex;

import com.murex.ranking.*;
import com.murex.ranking.Tie;

import java.util.*;

import static com.murex.HandBuilder.aHand;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

class PokerGame {
    private final Hand firstPlayerHand;
    private final Hand secondPlayerHand;
    private final List<RankingCategory> ranks = new ArrayList<>();

    public PokerGame(String firstPlayerName, String firstPlayerCards, String secondPlayerName, String secondPlayerCards) {
        this.firstPlayerHand = aHand().withPlayer(firstPlayerName).withCards(firstPlayerCards).build();
        this.secondPlayerHand = aHand().withPlayer(secondPlayerName).withCards(secondPlayerCards).build();
        addRankingCategories();
    }
    private void addRankingCategories () {
        ranks.add(new RoyalFlush(firstPlayerHand, secondPlayerHand));
        ranks.add(new StraightFlush(firstPlayerHand, secondPlayerHand));
        ranks.add(new FourOfAKind(firstPlayerHand, secondPlayerHand));
        ranks.add(new FullHouse(firstPlayerHand, secondPlayerHand));
        ranks.add(new Flush(firstPlayerHand, secondPlayerHand));
        ranks.add(new Straight(firstPlayerHand, secondPlayerHand));
        ranks.add(new ThreeOfAKind(firstPlayerHand, secondPlayerHand));
        ranks.add(new TwoPairs(firstPlayerHand, secondPlayerHand));
        ranks.add(new Pair(firstPlayerHand, secondPlayerHand));
        ranks.add(new HighCard(firstPlayerHand, secondPlayerHand));
        ranks.add(new Tie(firstPlayerHand, secondPlayerHand));
    }

    public String getWinner() {
        return ranks.stream().map(RankingCategory::evaluate).filter(Result::isMatch).findFirst().map(Result::getMessage).orElse(null);
    }
}

