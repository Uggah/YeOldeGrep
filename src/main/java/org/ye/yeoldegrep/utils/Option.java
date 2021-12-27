package org.ye.yeoldegrep.utils;

/**
 * Represents Options used for the grep-search
 */

public enum Option {
    /**
     * Ignore-Case-Option
     */
    IgnoreCase('i',"ignore-case"),
    /**
     * FilesWithMatches-Option
     */
    FilesWithMatches('l',"files-with-matches");

    private char shortAlias;
    private String longAlias;

    Option(char sA, String lA) {
        this.shortAlias = sA;
        this.longAlias = lA;
    }

    /**
     * Getter for shortAlias
     *
     * @return The short alias of the Option
     */
    public char getShortAlias() {
        return this.shortAlias;
    }
    /**
     * Getter for longAlias
     *
     * @return The long alias of the Option
     */
    public String getLongAlias() {
        return this.longAlias;
    }

    /**
     * <p>Returns the enum-value with the given shortAlias</p>
     *
     * Returns null if there is no Option with that shortAlias
     *
     * @param sA The shortAlias of an Option
     * @return The Option with that shortAlias or null
     */
    public static Option getByShortAlias(char sA) {
        for(Option opt : values()){
            if( opt.getShortAlias() == sA){
                return opt;
            }
        }
        return null;
    }
    /**
     * <p>Returns the enum-value with the given longAlias</p>
     *
     * Returns null if there is no Option with that longAlias
     *
     * @param lA The longAlias of an Option
     * @return The Option with that longAlias or null
     */
    public static Option getByLongAlias(String lA) {
        for(Option opt : values()){
            if(opt.getLongAlias().equals(lA)){
                return opt;
            }
        }
        return null;
    }
}
