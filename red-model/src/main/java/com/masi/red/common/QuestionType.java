package com.masi.red.common;

public enum QuestionType {
    OPEN("O"),
    SINGLE_CHOICE("W"),
    SCALE("S"),
    NUMERIC("L");

    private final String symbol;

    public String getSymbol() {
        return this.symbol;
    }

    QuestionType(String symbol) {
        this.symbol = symbol;
    }
}
