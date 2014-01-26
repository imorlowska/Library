/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mo;

/**
 *
 * @author Izabela
 */
abstract class Item {
    protected String name;
    protected int genre_id;
    protected int author_id;
    protected int quantity;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the genre_id
     */
    public int getGenre_id() {
        return genre_id;
    }

    /**
     * @param genre_id the genre_id to set
     */
    public void setGenre_id(int genre_id) {
        this.genre_id = genre_id;
    }

    /**
     * @return the author_id
     */
    public int getAuthor_id() {
        return author_id;
    }

    /**
     * @param author_id the author_id to set
     */
    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public void decreaseQuantity(int nb) {
        this.quantity -= nb;
    }
    
    public void increaseQuantity(int nb) {
        this.quantity += nb;
    }
}
