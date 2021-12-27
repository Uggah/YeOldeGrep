package org.ye.yeoldegrep.utils;

/**
 * Represents Options used for the grep-search
 */

public enum Argument {
    IgnoreCase('i',"ignore-case"),
    FilesWithMatches('l',"files-with-matches");

    private char shortAlias;
    private String longAlias;

    Argument(char sA, String lA) {
        this.shortAlias = sA;
        this.longAlias = lA;
    }

    public char getShortAlias() {
        return this.shortAlias;
    }
    public String getLongAlias() {
        return this.longAlias;
    }

    /**
     * Returns the enum-value with the given shortAlias or null
     *
     * @param sA The shortAlias of an Argument
     * @return The Argument with that shortAlias
     */
    public static Argument getByShortAlias(char sA) {
        for(Argument a : values()){
            if( a.getShortAlias() == sA){
                return a;
            }
        }
        return null;
    }
    /**
     * Returns the enum-value with the given longAlias or null
     *
     * @param lA The longAlias of an Argument
     * @return The Argument with that longAlias
     */
    public static Argument getByLongAlias(String lA) {
        for(Argument a : values()){
            if( a.getLongAlias() == lA){
                return a;
            }
        }
        return null;
    }
}
