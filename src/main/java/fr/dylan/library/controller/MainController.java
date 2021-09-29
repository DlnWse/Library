package fr.dylan.library.controller;

import fr.dylan.library.entity.Book;
import fr.dylan.library.utils.ConnectionBDD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class MainController implements Initializable {

@FXML private TableView<Book> table;
@FXML private TableColumn<Book, Integer> tableId;
@FXML private TableColumn<Book, String> tableTitle;
@FXML private TableColumn<Book, String> tableAuthor;
@FXML private TableColumn<Book, Integer> tableYear;
@FXML private TableColumn<Book, Integer> tablePages;

@FXML private TextField tfTitle, tfId, tfAuthor, tfYear,tfPages;

    private Book selectedBook = null;
    private ObservableList<Book> data = FXCollections.observableArrayList();


    @FXML
public void onViewAction(){
    display();
}

private void display() {

    try {
        // Connection a la BDD
        ConnectionBDD dataBaseConnection = new ConnectionBDD();
        Connection connectDB = dataBaseConnection.getConnection();

        // Création de la requete d'affichage
        String reqAffichage = "SELECT * FROM book";
        PreparedStatement stat = connectDB.prepareStatement(reqAffichage);
        ResultSet rs = stat.executeQuery();

        //reinitialisation du tableview
        data.removeAll(data);
        while (rs.next()){
            Book book = new Book(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5));
            data.add(book);
        }
        connectDB.close();


    }catch (Exception e){
        e.printStackTrace();

    }

    tableId.setCellValueFactory(new PropertyValueFactory<Book, Integer>("_id"));
    tableTitle.setCellValueFactory(new PropertyValueFactory<Book, String>("_title"));
    tableAuthor.setCellValueFactory(new PropertyValueFactory<Book, String>("_author"));
    tableYear.setCellValueFactory(new PropertyValueFactory<Book, Integer>("_year"));
    tablePages.setCellValueFactory(new PropertyValueFactory<Book, Integer>("_pages"));
    table.setItems(data);
}

    @FXML
    void onDeleteAction(ActionEvent event) {
        try {
            // Connection a la BDD
            ConnectionBDD dataBaseConnection = new ConnectionBDD();
            Connection connectDB = dataBaseConnection.getConnection();
            String reqDelete = "DELETE FROM book WHERE id=" + selectedBook.get_id() + ";";
            connectDB.createStatement().execute(reqDelete);
        } catch (Exception e){
            e.printStackTrace();
        }
        display();
    }



    @FXML
    void onInsertAction(ActionEvent event) {
        try {

            // variables locale pour récupérer le texte d'un textfield
            String tfTitleGetText = tfTitle.getText();
            String tfAuthorGetText = tfAuthor.getText();
            int tfYearGetText = Integer.parseInt(tfYear.getText());
            int tfPagesGetText = Integer.parseInt(tfPages.getText());

            // Connection a la BDD
            ConnectionBDD dataBaseConnection = new ConnectionBDD();
            Connection connectDB = dataBaseConnection.getConnection();
            String reqAdd = "INSERT INTO book VALUES (null, '" + tfTitleGetText + "', '" + tfAuthorGetText + "', '" + tfYearGetText + "', '" + tfPagesGetText + "')";
            Statement stat = connectDB.createStatement();
            stat.executeUpdate(reqAdd);

        } catch (Exception e){
            e.printStackTrace();
        }

        display();

    }

    @FXML
    void onUpdateAction(ActionEvent event) {
        try {

            selectedBook = table.getSelectionModel().getSelectedItem();


            // variables locale pour récupérer le texte d'un textfield
            String tfTitleGetText = tfTitle.getText();
            String tfAuthorGetText = tfAuthor.getText();
            int tfYearGetText = Integer.parseInt(tfYear.getText());
            int tfPagesGetText = Integer.parseInt(tfPages.getText());

            // Connection a la BDD
            ConnectionBDD dataBaseConnection = new ConnectionBDD();
            Connection connectDB = dataBaseConnection.getConnection();
            String reqUpdate = "UPDATE book SET title = '" + tfTitleGetText + "', author = '" + tfAuthorGetText + "', year = '" + tfYearGetText + "', pages = '" + tfPagesGetText + "' WHERE Id = '" + selectedBook.get_id() +"'";
            System.out.println(reqUpdate);
            Statement stat = connectDB.createStatement();
            stat.executeUpdate(reqUpdate);

        } catch (Exception e){
            e.printStackTrace();
        }

        display();


    }

    @FXML
    void handleTableView(Event event){
        selectedBook = table.getSelectionModel().getSelectedItem();

        int selectId = selectedBook.get_id();
        String selectTitle = selectedBook.get_title();
        String selectAuthor = selectedBook.get_author();
        int selectYear = selectedBook.get_year();
        int selectPages = selectedBook.get_pages();

        tfId.setText(String.valueOf(selectId));
        tfTitle.setText(selectTitle);
        tfAuthor.setText(selectAuthor);
        tfYear.setText(String.valueOf(selectYear));
        tfPages.setText(String.valueOf(selectPages));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
display();
    }
}