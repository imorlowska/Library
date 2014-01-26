package mo;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ferdiansyah Dolot
 */
public class Management {

    private static Connect connect;
    private static String loginname;
    private static GenresList genresList;
    private static ItemsList itemsList;
    private static AuthorsList authorsList;
    private static ClientsList clientsList;
    private static OrdersList ordersList;

    private static void load() throws SQLException {
        genresList = new GenresList();
        genresList.load(connect);
        itemsList = new ItemsList();
        itemsList.load(connect);
        authorsList = new AuthorsList();
        authorsList.load(connect);
        clientsList = new ClientsList();
        clientsList.load(connect);
        ordersList = new OrdersList();
        ordersList.load(connect);
        System.out.println("Loading complete.\n");
    }

    private static void clearScreen() {
        try {
            Robot robbie = new Robot();
            robbie.keyPress(17); // Holds CTRL key.
            robbie.keyPress(76); // Holds L key.
            robbie.keyRelease(17); // Releases CTRL key.
            robbie.keyRelease(76); // Releases L key.
        } catch (AWTException ex) {
            System.err.println("Robot Failure. Cannot clear screen.");
        }
    }

    private static boolean login() throws IOException, SQLException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Provide login: ");
        loginname = br.readLine();
        System.out.println("Provide password: ");
        String pass = br.readLine();
        clearScreen();
        return EmployeeVerificator.verify(loginname, pass, connect);
    }

    public static void manage() throws IOException, InterruptedException, SQLException {
        while (true) {
            System.out.println("What would you like to do?\n"
                    + "1\t See clients list\n"
                    + "2\t See client's details\n"
                    + "3\t Add new client\n"
                    + "4\t See items list\n"
                    + "5\t See item's details\n"
                    + "6\t Add new item\n"
                    + "7\t See authors list\n"
                    + "8\t See author's details\n"
                    + "9\t Add new author\n"
                    + "10\t See orders list\n"
                    + "11\t Add new order\n"
                    + "12\t Finalize an order\n"
                    + "13\t Exit\n");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int i = Integer.parseInt(br.readLine());
            if (i == 1) {
                showClientsList();
            } else if (i == 2) {
                showClientsDetails();
            } else if (i == 3) {
                addNewClient();
            } else if (i == 4) {
                showItemsList();
            } else if (i == 5) {
                showItemsDetails();
            } else if (i == 6) {
                addNewItem();
            } else if (i == 7) {
                seeAuthorsList();
            } else if (i == 8) {
                seeAuthorsDetails();
            } else if (i == 9) {
                addNewAuthor();
            } else if (i == 10) {
                showOrdersList();
            } else if (i == 11) {
                addNewOrder();
            } else if (i == 12) {
                finalizeOrder();
            } else if (i == 13) {
                break;
            } else {
                System.out.println("Wrong command");
            }
        }
    }

    public static void main(String args[]) throws IOException, InterruptedException {
        try {
            connect = new Connect();
            System.out.println("Connection established.");
            if (login()) {
                System.out.println("Logged in");
                load();
                manage();
            } else {
                System.out.println("Failed to log in. Shutting down.");
            }
            System.out.println("Goodbye.");
            connect.makeConnection().commit();
            connect.makeConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Connection Failure. Shutting down.");
        }
    }

    private static void showClientsList() {
        System.out.println("Clients list:\nid\t\tname\t\tlastname\t\temail\t\tis banned");
        for (Client c : clientsList.clients) {
            System.out.println(c.id + "\t\t" + c.name + "\t\t" + c.lastname + "\t\t" + c.email + "\t\t" + c.banned);
        }
    }

    private static void showClientsDetails() throws IOException {
        System.out.println("Provide client's id: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int i = Integer.parseInt(br.readLine());
        Client found = null;
        for (Client c : clientsList.clients) {
            if (c.id == i) {
                found = c;
            }
        }
        if (found == null) {
            System.out.println("Client not found.");
        } else {
            System.out.println("id = " + found.id);
            System.out.println("name = " + found.name);
            System.out.println("lastname = " + found.lastname);
            System.out.println("email = " + found.email);
            System.out.println("is banned? = " + found.banned);
            System.out.println();
            System.out.println("Currently held items: ");
            List<Integer> items_ids = new ArrayList();
            for (Order o : ordersList.orders) {
                if (o.client_id == found.id) {
                    items_ids.add(o.item_id);
                }
            }
            if (items_ids.isEmpty()) {
                System.out.println("none");
            } else {
                for (Item b : itemsList.items) {
                    if (items_ids.contains(b.id)) {
                        System.out.println(b.name);
                    }
                }
            }
        }
        System.out.println();
    }

    private static void addNewClient() throws IOException, SQLException, InterruptedException {
        Client c = new Client();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Provide client's first name: ");
        String name = br.readLine();
        System.out.println("Provide client's last name: ");
        String lastname = br.readLine();
        System.out.println("Provide client's email: ");
        String email = br.readLine();
        clientsList.addClient(name, lastname, email, connect);
    }

    private static void showItemsList() throws IOException {
        System.out.println("1 - Books\n2 - Magazines");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int i = Integer.parseInt(br.readLine());
        if (i == 1) {
            System.out.println("Books:\nid\tname\tauthor\tiban\tquantity available");
            for (Item b : itemsList.items) {
                if (b instanceof Book) {
                    System.out.println(b.id + "\t" + b.name + "\t" + b.author_id + "\t" + ((Book) b).iban + "\t" + b.quantity);
                }
            }
            System.out.println("End.");
        } else if (i == 2) {
            System.out.println("Magazines:\nid\tname\tauthor\tissue nb\tquantity available");
            for (Item b : itemsList.items) {
                if (b instanceof Magazine) {
                    System.out.println(b.id + "\t" + b.name + "\t" + b.author_id + "\t" + ((Magazine) b).issue_nb + "\t" + b.quantity);
                }
            }
            System.out.println("End.");
        } else {
            System.out.println("Wrong command.");
        }
    }

    private static void showItemsDetails() throws IOException {
        System.out.println("Provide item id: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int i = Integer.parseInt(br.readLine());
        Item it = null;
        for (Item item : itemsList.items) {
            if (item.id == i) {
                it = item;
            }
        }
        if (it != null) {
            System.out.println("id = " + it.id);
            System.out.println("name = " + it.name);
            for (Author a : authorsList.authors) {
                if (a.id == it.author_id) {
                    System.out.println("author = " + a.name + (it instanceof Book? a.lastname: ""));
                }
            }
            if (it instanceof Book) {
                System.out.println("iban = " + ((Book)it).iban);
            } else {
                System.out.println("issue nb = " + ((Magazine)it).issue_nb);
            }
            System.out.println("Currently held by: ");
            List<Integer> client_ids = new ArrayList();
            for (Order o : ordersList.orders) {
                if (o.item_id == it.id) {
                    client_ids.add(o.client_id);
                }
            }
            if (client_ids.isEmpty()) {
                System.out.println("None.");
            } else {
                for (Client c : clientsList.clients) {
                    if (client_ids.contains(c.id)) {
                        System.out.println(c.name + " " + c.lastname);
                    }
                }
            }
            
        } else {
            System.out.println("Item not found.");
        }
    }

    private static void addNewItem() {
    }

    private static void seeAuthorsList() {
    }

    private static void seeAuthorsDetails() {
    }

    private static void addNewAuthor() {
    }

    private static void showOrdersList() {
    }

    private static void addNewOrder() {
    }

    private static void finalizeOrder() {
    }
}
