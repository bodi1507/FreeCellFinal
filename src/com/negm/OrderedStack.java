package com.negm;

import java.util.*;

/**
 * OrderedStack class does the following
 * 1) generates the complete deck
 * 2) shuffles the deck into columns
 * 3) handles the card moving between columns and piles
 * 4) handles user input errors and display error messages
 */

public class OrderedStack {
    public ArrayList<String> deck = new ArrayList<String>(

            /*
             * Declaring and initializing the deck of 52 cards
             */
            Arrays.asList("cA", "c2", "c3", "c4", "c5", "c6", "c7", "c8", "c9", "c10", "cJ", "cQ", "cK",
                    "dA", "d2", "d3", "d4", "d5", "d6", "d7", "d8", "d9", "d10", "dJ", "dQ", "dK",
                    "hA", "h2", "h3", "h4", "h5", "h6", "h7", "h8", "h9", "h10", "hJ", "hQ", "hK",
                    "sA", "s2", "s3", "s4", "s5", "s6", "s7", "s8", "s9", "s10", "sJ", "sQ", "sK"));


    public ArrayList<String> deckCopy = new ArrayList<String>(deck);
    public Stack<String> pileC = new Stack<String>();
    public Stack<String> pileD = new Stack<String>();
    public Stack<String> pileH = new Stack<String>();
    public Stack<String> pileS = new Stack<String>();
    public Stack<String> column1 = new Stack<String>();
    public Stack<String> column2 = new Stack<String>();
    public Stack<String> column3 = new Stack<String>();
    public Stack<String> column4 = new Stack<String>();
    public Stack<String> column5 = new Stack<String>();
    public Stack<String> column6 = new Stack<String>();
    public Stack<String> column7 = new Stack<String>();
    public Stack<String> column8 = new Stack<String>();
    public Stack<String> column9 = new Stack<String>();
    public GenerateColSize size = new GenerateColSize();

    public Random rd = new Random();
    ColouredSystemOutPrintln colors = new ColouredSystemOutPrintln();

    /**
     * OrderedStack Constructor
     */
    public OrderedStack() {
    }


    /**
     * Checks whether the user wants to move to a pile or a column
     *
     * @param colFrom  The column you wish to move the card from
     * @param cardName The name of the card
     * @param colTo    The column you wish to move the card to
     */
    public void moveTo(String colFrom, String cardName, String colTo) {
        if (colTo.equals("h") || colTo.equals("c") || colTo.equals("s") || colTo.equals("d")) {
            if (cardName.compareTo(getTopCard(colFrom)) == 0) {
                pilePush(cardName, colTo, colFrom);
            } else
                System.out.println(colors.ANSI_RED + "Invalid check card" + colors.ANSI_RESET);
        } else if (isValidCol(colTo)) {
            if (cardName.compareTo(getTopCard(colFrom)) == 0) {
                colPush(cardName, colTo, colFrom);
            } else
                System.out.println(colors.ANSI_RED + "Invalid check card" + colors.ANSI_RESET);
        } else {
            System.out.println(colors.ANSI_RED  + "Invalid column or pile move" + colors.ANSI_RESET );
        }
    }


    /**
     * Checks if the card you wish to move to the column follows
     * the rules and will send it to the push function if successful
     *
     * @param cardName card name
     * @param colTo    column moving to
     */
    public void colPush(String cardName, String colTo, String colFrom) {
        if (isAllEmpty(colTo)) {
            pushTo(colTo, cardName);
            popFrom(colFrom);
        } else if (isValidCol(colTo)) {
            // Checks if the value of cardName is 1 lower than
            // the value of the top card of the colTo stack
            if (getCardValue(getTopCard(colTo)) == getCardValue(cardName) + 1) {
                // If successful, it will push the cardName onto the colTo
                pushTo(colTo, cardName);
                popFrom(colFrom);
            } else
                // If unsuccessful, system will print out "Illegal move"
                System.out.println(colors.ANSI_RED + "Illegal move" + colors.ANSI_RESET);
        }
    }

    /**
     * Checks if the card you wish to move to the pile follows
     * the rules and will push the cardName to the colTo will send it to the popFrom function
     *
     * @param cardName card name
     * @param colTo    column moving to
     * @param colFrom  column moving from
     */
    public void pilePush(String cardName, String colTo, String colFrom) {
        Character temp = cardName.charAt(0);
        // Checks if the cardName value is 1 higher than the card at the top of the pile
        if ((cardName.equals("cA") && pileC.isEmpty() && colTo.equals("c")) || (temp == 'c' && ((getCardValue(getTopCard(colTo)) == getCardValue(cardName) - 1)) && colTo.equals("c"))) {
            // If move is valid it will push the card onto the desired pile
            // and pop from the column it came from
            pileC.push(cardName);
            popFrom(colFrom);
        } else if
        ((cardName.equals("hA") && pileH.isEmpty() && colTo.equals("h")) || (temp == 'h' && ((getCardValue(getTopCard(colTo)) == getCardValue(cardName) - 1)) && colTo.equals("h"))) {
            pileH.push(cardName);
            popFrom(colFrom);
        } else if
        ((cardName.equals("sA") && pileS.isEmpty() && colTo.equals("s")) || (temp == 's' &&((getCardValue(getTopCard(colTo)) == getCardValue(cardName) -1 )) && colTo.equals("s"))) {
            pileS.push(cardName);
            popFrom(colFrom);
        } else if
        ((cardName.equals("dA") && pileD.isEmpty() && colTo.equals("d")) || (temp == 'd' && ((getCardValue(getTopCard(colTo)) == getCardValue(cardName) -1))&& colTo.equals("d"))) {
            pileD.push(cardName);
            popFrom(colFrom);
        } else {
            // If unsuccessful it will print out "Illegal move"
            System.out.println(colors.ANSI_RED + "Illegal move" + colors.ANSI_RESET);
        }
    }


    /**
     * Pushes the cardName to the column
     *
     * @param cardName card name
     * @param colTo    column moving to
     */
    public void pushTo(String colTo, String cardName) {
        switch (colTo) {
            case "1":
                column1.push(cardName);
                break;
            case "2":
                column2.push(cardName);
                break;
            case "3":
                column3.push(cardName);
                break;
            case "4":
                column4.push(cardName);
                break;
            case "5":
                column5.push(cardName);
                break;
            case "6":
                column6.push(cardName);
                break;
            case "7":
                column7.push(cardName);
                break;
            case "8":
                column8.push(cardName);
                break;
            case "9":
                column9.push(cardName);
                break;
            default:
                System.out.println("error: at pushTo method");
        }
    }

    /**
     * Pop the last element of selected pile/column
     *
     * @param colFrom moving from
     * @
     */
    public void popFrom(String colFrom) {
        switch (colFrom) {
            case "1":
                column1.pop();
                break;
            case "2":
                column2.pop();
                break;
            case "3":
                column3.pop();
                break;
            case "4":
                column4.pop();
                break;
            case "5":
                column5.pop();
                break;
            case "6":
                column6.pop();
                break;
            case "7":
                column7.pop();
                break;
            case "8":
                column8.pop();
                break;
            case "9":
                column9.pop();
                break;
            default:
                System.out.println("error: at popFrom method");
        }
    }

    /**
     * Gets the top card (peek) of the selected column/pile
     *
     * @param columnNum column number
     * @return peek of the specific file
     */
    public String getTopCard(String columnNum) {
        switch (columnNum) {
            case "1":
                if (column1.isEmpty()) {
                    // To avoid EmptyStackException we will return 0
                    // if the stack is empty
                    return "0";
                } else
                    return column1.peek();
            case "2":
                if (column2.isEmpty()) {
                    return "0";
                } else
                    return column2.peek();
            case "3":
                if (column3.isEmpty()) {
                    return "0";
                } else
                    return column3.peek();
            case "4":
                if (column4.isEmpty()) {
                    return "0";
                } else
                    return column4.peek();
            case "5":
                if (column5.isEmpty()) {
                    return "0";
                } else
                    return column5.peek();
            case "6":
                if (column6.isEmpty()) {
                    return "0";
                } else
                    return column6.peek();
            case "7":
                if (column7.isEmpty()) {
                    return "0";
                } else
                    return column7.peek();
            case "8":
                if (column8.isEmpty()) {
                    return "0";
                } else
                    return column8.peek();
            case "9":
                if (column9.isEmpty()) {
                    return "0";
                } else
                    return column9.peek();
            case "c":
                if (pileC.isEmpty())
                    return "0";
                else return pileC.peek();
            case "d":
                if (pileD.isEmpty())
                    return "0";
                else return pileD.peek();
            case "h":
                if (pileH.isEmpty())
                    return "0";
                else return pileH.peek();
            case "s":
                if (pileS.isEmpty())
                    return "0";
                else return pileS.peek();
            default:
                return "error: at getTopCard method";
        }

    }

    /**
     * Returns the value of selected card
     *
     * @param card card name
     * @return value of the card
     */
    public int getCardValue(String card) {
        Map<String, Integer> carValue = new HashMap<>();
        carValue.put("cA", 1);
        carValue.put("c2", 2);
        carValue.put("c3", 3);
        carValue.put("c4", 4);
        carValue.put("c5", 5);
        carValue.put("c6", 6);
        carValue.put("c7", 7);
        carValue.put("c8", 8);
        carValue.put("c9", 9);
        carValue.put("c10", 10);
        carValue.put("cJ", 11);
        carValue.put("cQ", 12);
        carValue.put("cK", 13);
        carValue.put("dA", 1);
        carValue.put("d2", 2);
        carValue.put("d3", 3);
        carValue.put("d4", 4);
        carValue.put("d5", 5);
        carValue.put("d6", 6);
        carValue.put("d7", 7);
        carValue.put("d8", 8);
        carValue.put("d9", 9);
        carValue.put("d10", 10);
        carValue.put("dJ", 11);
        carValue.put("dQ", 12);
        carValue.put("dK", 13);
        carValue.put("hA", 1);
        carValue.put("h2", 2);
        carValue.put("h3", 3);
        carValue.put("h4", 4);
        carValue.put("h5", 5);
        carValue.put("h6", 6);
        carValue.put("h7", 7);
        carValue.put("h8", 8);
        carValue.put("h9", 9);
        carValue.put("h10", 10);
        carValue.put("hJ", 11);
        carValue.put("hQ", 12);
        carValue.put("hK", 13);
        carValue.put("sA", 1);
        carValue.put("s2", 2);
        carValue.put("s3", 3);
        carValue.put("s4", 4);
        carValue.put("s5", 5);
        carValue.put("s6", 6);
        carValue.put("s7", 7);
        carValue.put("s8", 8);
        carValue.put("s9", 9);
        carValue.put("s10", 10);
        carValue.put("sJ", 11);
        carValue.put("sQ", 12);
        carValue.put("sK", 13);
        carValue.put("0", 0);
        return carValue.get(card);
    }

    /**
     * Shuffles the copy of the deck
     */
    public void shuffleDeck() {
        for (int index = 0; index < size.getcol1(); index++) {
            int indexTemp = rd.nextInt(deckCopy.size());
            column1.push(deckCopy.get(indexTemp));
            deckCopy.remove(indexTemp);
            indexTemp--;
        }
        for (int index = 0; index < size.getcol2(); index++) {
            int indexTemp = rd.nextInt(deckCopy.size());
            column2.add(deckCopy.get(indexTemp));
            deckCopy.remove(indexTemp);
            indexTemp--;
        }
        for (int index = 0; index < size.getcol3(); index++) {
            int indexTemp = rd.nextInt(deckCopy.size());
            column3.add(deckCopy.get(indexTemp));
            deckCopy.remove(indexTemp);
            indexTemp--;
        }
        for (int index = 0; index < size.getcol4(); index++) {
            int indexTemp = rd.nextInt(deckCopy.size());
            column4.add(deckCopy.get(indexTemp));
            deckCopy.remove(indexTemp);
            indexTemp--;
        }
        for (int index = 0; index < size.getcol5(); index++) {
            int indexTemp = rd.nextInt(deckCopy.size());
            column5.add(deckCopy.get(indexTemp));
            deckCopy.remove(indexTemp);
            indexTemp--;
        }
        for (int index = 0; index < size.getcol6(); index++) {
            int indexTemp = rd.nextInt(deckCopy.size());
            column6.add(deckCopy.get(indexTemp));
            deckCopy.remove(indexTemp);
            indexTemp--;
        }
        for (int index = 0; index < size.getcol7(); index++) {
            int indexTemp = rd.nextInt(deckCopy.size());
            column7.add(deckCopy.get(indexTemp));
            deckCopy.remove(indexTemp);
            indexTemp--;
        }
        for (int index = 0; index < size.getcol8(); index++) {
            int indexTemp = rd.nextInt(deckCopy.size());
            column8.add(deckCopy.get(indexTemp));
            deckCopy.remove(indexTemp);
            indexTemp--;
        }
        //column9 is just an extra column no data need to be inserted in it

    }

    /**
     * Rotates the selected column
     *
     * @param colRot column number to rotate
     */
    public void colRotation(String colRot) {
        switch (colRot) {
            case "1":
                if (column1.isEmpty()) {
                    // To avoid EmptyStackException we first check if the stack is
                    // empty
                    System.out.println("The column is empty");
                } else
                    column1.add(0, column1.remove(column1.size() - 1));
                break;
            case "2":
                if (column2.isEmpty()) {
                    System.out.println("The column is empty");
                } else
                    column2.add(0, column2.remove(column2.size() - 1));
                break;
            case "3":
                if (column3.isEmpty()) {
                    System.out.println("The column is empty");
                } else
                    column3.add(0, column3.remove(column3.size() - 1));
                break;
            case "4":
                if (column4.isEmpty()) {
                    System.out.println("The column is empty");
                } else
                    column4.add(0, column4.remove(column4.size() - 1));
                break;
            case "5":
                if (column5.isEmpty()) {
                    System.out.println("The column is empty");
                } else
                    column5.add(0, column5.remove(column5.size() - 1));
                break;
            case "6":
                if (column6.isEmpty()) {
                    System.out.println("The column is empty");
                } else
                    column6.add(0, column6.remove(column6.size() - 1));
                break;
            case "7":
                if (column7.isEmpty()) {
                    System.out.println("The column is empty");
                } else
                    column7.add(0, column7.remove(column7.size() - 1));
                break;
            case "8":
                if (column8.isEmpty()) {
                    System.out.println("The column is empty");
                } else
                    column8.add(0, column8.remove(column8.size() - 1));
                break;
            case "9":
                if (column9.isEmpty()) {
                    System.out.println("The column is empty");
                } else
                    column9.add(0, column9.remove(column9.size() - 1));
                break;
            default:
                System.out.println("error:  at colRotation method");
        }
    }

    /**
     * Prints the board
     */
    public void printBoard() {
        System.out.println("Pile   C : " + pileC +
                "\nPile   D : " + pileD +
                "\nPile   H : " + pileH +
                "\nPile   S : " + pileS +
                "\nColumn 1 : " + column1 +
                "\nColumn 2 : " + column2 +
                "\nColumn 3 : " + column3 +
                "\nColumn 4 : " + column4 +
                "\nColumn 5 : " + column5 +
                "\nColumn 6 : " + column6 +
                "\nColumn 7 : " + column7 +
                "\nColumn 8 : " + column8 +
                "\nColumn 9 : " + column9);
    }

    /**
     * Clears all of the piles and columns
     */
    public void clearAll() {
        pileS.clear();
        pileH.clear();
        pileD.clear();
        pileC.clear();
        column1.clear();
        column2.clear();
        column3.clear();
        column4.clear();
        column5.clear();
        column6.clear();
        column7.clear();
        column8.clear();
        column9.clear();
    }

    /**
     * Restarts the game
     */
    public void restart() {
        clearAll();
        // Since shuffle moves the shuffled cards onto the columns,
        // we make a copy of the deck before the shuffling in case
        // the user wishes to restart multiple times
        deckCopy.addAll(deck);
        shuffleDeck();
    }

    /**
     * Returns true if the game is complete
     *
     * @return true if game is finished
     */
    public Boolean isDone() {
        if ((column1.isEmpty() && column2.isEmpty() && column3.isEmpty() && column4.isEmpty() && column5.isEmpty() && column6.isEmpty() && column7.isEmpty() && column8.isEmpty() && column9.isEmpty())) {
            return true;
        } else
            return false;
    }

    /**
     * @return true if a column is empty
     */
    public Boolean isAllEmpty(String colTo) {
        if (colTo.equals("1") && column1.isEmpty()) {
            return true;
        }
        if (colTo.equals("2") && column2.isEmpty()) {
            return true;
        }
        if (colTo.equals("3") && column3.isEmpty()) {
            return true;
        }
        if (colTo.equals("4") && column4.isEmpty()) {
            return true;
        }
        if (colTo.equals("5") && column5.isEmpty()) {
            return true;
        }
        if (colTo.equals("6") && column6.isEmpty()) {
            return true;
        }
        if (colTo.equals("7") && column7.isEmpty()) {
            return true;
        }
        if (colTo.equals("8") && column8.isEmpty()) {
            return true;
        }
        if (colTo.equals("9") && column9.isEmpty()) {
            return true;
        } else return false;
    }

    /**
     * Returns true if colTo is a valid column to move to
     *
     * @param colTo column to check if valid
     * @return if it's a valid column to use
     */
    public Boolean isValidCol(String colTo) {
        if (colTo.equals("1") || colTo.equals("2") || colTo.equals("3") || colTo.equals("4") || colTo.equals("5") || colTo.equals("6") || colTo.equals("7") || colTo.equals("8") || colTo.equals("9")) {
            return true;
        } else
            return false;
    }

    /**
     * Check if user input is in lower case
     * @param cardName
     * @return uppercase cardName
     */
    public String checkLowerCase(String cardName) {
        if (cardName == "ca")
            return "cA";
        else if (cardName.equals("cj"))
            return "cJ";
        else if (cardName.equals("cq"))
            return "cK";
        else if (cardName.equals("ck"))
            return "cK";
        else if (cardName.equals("ha"))
            return "hA";
        else if (cardName.equals("hj"))
            return "hJ";
        else if (cardName.equals("hq"))
            return "hK";
        else if (cardName.equals("hk"))
            return "hK";
        else if (cardName.equals("sa"))
            return "sA";
        else if (cardName.equals("sj"))
            return "sJ";
        else if (cardName.equals("sq"))
            return "sK";
        else if (cardName.equals("sk"))
            return "sK";
        else if (cardName.equals("da"))
            return "dA";
        else if (cardName.equals("dj"))
            return "dJ";
        else if (cardName.equals("dq"))
            return "dK";
        else if (cardName.equals("dk"))
            return "dK";
        else
            return cardName;


    }
}