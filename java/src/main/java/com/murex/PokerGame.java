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

class PokerGame {
    public static String getWinner(String blackHand, String whiteHand) {
        String[] blackCards = blackHand.split(" ");
        String[] whiteCards = whiteHand.split(" ");

        Card blackCard = new Card(blackCards[4].charAt(0));
        Card whiteCard = new Card(whiteCards[4].charAt(0));

        int comparison = blackCard.compareTo(whiteCard);
        if (comparison > 0) {
            return buildMessage("Black",  blackCard.getValue());
        } else if (comparison < 0) {
            return buildMessage("White",  whiteCard.getValue());
        } else {
            boolean isTie = true;
            for (int i = 0; i < 5; i++) {
                Card bCard = new Card(blackCards[i].charAt(0));
                Card wCard = new Card(whiteCards[i].charAt(0));
                if(bCard.compareTo(wCard) != 0) {
                    isTie = false;
                    break;
                }
            }
            if(isTie) {
                return "Tie.";
            }
        }
        return null;
    }

    private static String buildMessage(String winner, String card) {
        return winner + " wins. - with high card: " + card;
    }
}
