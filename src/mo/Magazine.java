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
public class Magazine extends Item {
    private String issue_nb;
    
    public static Magazine createMagazine() {
        return new Magazine();
    }

    /**
     * @return the issue_nb
     */
    public String getIssue_nb() {
        return issue_nb;
    }

    /**
     * @param issue_nb the issue_nb to set
     */
    public void setIssue_nb(String issue_nb) {
        this.issue_nb = issue_nb;
    }
    
    
}
