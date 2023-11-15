package javafxapplication5;

import javafx.scene.control.ButtonBar.ButtonData;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.Optional;
import javafx.geometry.Insets;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class HotelManagementApp extends Application {
    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Hotel Management System");

        Scene loginScene = createLoginScene();

        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

  private Scene createLoginScene() {
    BorderPane loginPane = new BorderPane();
    loginPane.setStyle("-fx-background-color: #e0ffe0;");

    Label welcomeLabel = new Label("Welcome to");
    Label hotelLabel = new Label("Hotel SkyView");

    Font timesNewRoman24 = Font.font("Times New Roman", 24);
    Font timesNewRoman36 = Font.font("Times New Roman", 36);

    welcomeLabel.setFont(timesNewRoman24);
    hotelLabel.setFont(timesNewRoman36);

    welcomeLabel.setStyle("-fx-text-fill: #4CAF50;");
    hotelLabel.setStyle("-fx-text-fill: #4CAF50; -fx-underline: true;");

    VBox headingBox = new VBox(5, welcomeLabel, hotelLabel);
    headingBox.setAlignment(Pos.CENTER);
    BorderPane.setMargin(headingBox, new Insets(0, 0, 20, 0));

    GridPane centerGrid = new GridPane();
    centerGrid.setAlignment(Pos.CENTER);
    centerGrid.setHgap(10);
    centerGrid.setVgap(10);
    centerGrid.setStyle("-fx-background-color: #ffffff; -fx-padding: 20; -fx-border-radius: 10; -fx-border-color: #cccccc;");

    Label usernameLabel = new Label("Username:");
    TextField usernameInput = new TextField();
    usernameInput.setPromptText("Enter your username");
    usernameInput.setStyle("-fx-background-color: #e0ffe0;");
    usernameInput.setFont(Font.font("Times New Roman", 18)); // Setting font for username input

    Label passwordLabel = new Label("Password:");
    PasswordField passwordInput = new PasswordField();
    passwordInput.setPromptText("Enter your password");
    passwordInput.setStyle("-fx-background-color: #e0ffe0;");
    passwordInput.setFont(Font.font("Times New Roman", 18)); // Setting font for password input

    Button loginButton = new Button("Log In");
    loginButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
    loginButton.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20)); // Setting the font size for the button
    loginButton.setPrefWidth(110); // Adjusting the width
    loginButton.setPrefHeight(30); // Adjusting the height
    loginButton.setOnAction(e -> handleLogin(usernameInput.getText(), passwordInput.getText()));

    centerGrid.add(usernameLabel, 0, 0);
    centerGrid.add(usernameInput, 1, 0);
    centerGrid.add(passwordLabel, 0, 1);
    centerGrid.add(passwordInput, 1, 1);
    centerGrid.add(loginButton, 1, 2);

    loginPane.setTop(headingBox);
    loginPane.setCenter(centerGrid);

    return new Scene(loginPane, 600, 500);
}
    private void handleLogin(String id, String password) {
        if (id.equals("Manager") && password.equals("1234")) {
            Scene menuScene = createMenuScene();
            primaryStage.setScene(menuScene);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Error");
            alert.setHeaderText("Invalid credentials");
            alert.setContentText("Please enter a valid ID and password.");
            alert.showAndWait();
        }
    }

    private Scene createMenuScene() {
    BorderPane menuPane = new BorderPane();
    String backgroundColor = "#e0ffe0"; // Same color as login scene
    menuPane.setStyle("-fx-background-color: " + backgroundColor + ";");

    Font buttonFont = Font.font("Times New Roman", FontWeight.BOLD, 14);
    Font titleFont = Font.font("Times New Roman", FontWeight.BOLD, 30);

    Label titleLabel = new Label("Menu");
    titleLabel.setFont(titleFont);
    titleLabel.setStyle("-fx-text-fill: #4CAF50;");
    BorderPane.setAlignment(titleLabel, Pos.CENTER);
    BorderPane.setMargin(titleLabel, new Insets(0, 0, 20, 0));

    VBox optionsBox = new VBox(20);
    optionsBox.setAlignment(Pos.CENTER);

    Button roomInfoButton = new Button("Room Info");
    roomInfoButton.setPrefWidth(200);
    roomInfoButton.setPrefHeight(50);
    roomInfoButton.setStyle("-fx-font-size: 16; -fx-background-color: #4CAF50; -fx-text-fill: white;");
    roomInfoButton.setFont(buttonFont);
    roomInfoButton.setOnAction(event -> handleRoomInfoButtonClick());

    Button staffInfoButton = new Button("Staff Info");
    staffInfoButton.setPrefWidth(200);
    staffInfoButton.setPrefHeight(50);
    staffInfoButton.setStyle("-fx-font-size: 16; -fx-background-color: #4CAF50; -fx-text-fill: white;");
    staffInfoButton.setFont(buttonFont);
    staffInfoButton.setOnAction(event -> handleStaffInfoButtonClick());

    optionsBox.getChildren().addAll(roomInfoButton, staffInfoButton);

    menuPane.setTop(titleLabel);
    menuPane.setCenter(optionsBox);

    return new Scene(menuPane, 600, 500);
}


    private void handleRoomInfoButtonClick() {

        Stage roomInfoStage = new Stage();
        roomInfoStage.setTitle("Room Information");

        VBox roomInfoLayout = new VBox(10);
        roomInfoLayout.setAlignment(Pos.CENTER);
        roomInfoLayout.setStyle("-fx-background-color: #e0ffe0; -fx-padding: 20;");


        TableView<Room> roomTable = new TableView<>();
        TableColumn<Room, String> roomNumberColumn = new TableColumn<>("Room Number");
        roomNumberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));

        TableColumn<Room, String> bedTypeColumn = new TableColumn<>("Bed Type");
        bedTypeColumn.setCellValueFactory(new PropertyValueFactory<>("bedType"));

        TableColumn<Room, Double> priceColumn = new TableColumn<>("Price per Day");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Room, String> statusColumn = new TableColumn<>("Status");
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        roomTable.getColumns().addAll(roomNumberColumn, bedTypeColumn, priceColumn, statusColumn);

        roomTable.getItems().add(new Room("101", "Double Bed", 10000, "Booked"));
        roomTable.getItems().add(new Room("102", "Double Bed", 10000, "Booked"));
        roomTable.getItems().add(new Room("103", "Double Bed", 10000, "Available"));
        roomTable.getItems().add(new Room("201", "Twin Bed", 8000, "Available"));
        roomTable.getItems().add(new Room("202", "Twin Bed", 8000, "Booked"));
        roomTable.getItems().add(new Room("203", "Twin Bed", 8000, "Available"));
        roomTable.getItems().add(new Room("301", "Suite", 20000, "Available"));
        roomTable.getItems().add(new Room("302", "Suite", 20000, "Booked"));
        roomTable.getItems().add(new Room("303", "Suite", 20000, "Booked"));

         roomInfoLayout.getChildren().add(roomTable);

    Button showBookedRoomsButton = new Button("Show Booked Rooms");
    showBookedRoomsButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;"); // Changed styling
    showBookedRoomsButton.setFont(Font.font("Times New Roman", FontWeight.BOLD, 14)); // Adjusted font
    showBookedRoomsButton.setOnAction(event -> showBookedRooms(roomTable));

    Button backButton = new Button("Back to Menu");
    backButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;"); // Changed styling
    backButton.setFont(Font.font("Times New Roman", FontWeight.BOLD, 14)); // Adjusted font
    backButton.setOnAction(event -> {
        roomInfoStage.close();
        primaryStage.setScene(createMenuScene());
    });

    roomInfoLayout.getChildren().addAll(showBookedRoomsButton, backButton);

    Scene roomInfoScene = new Scene(roomInfoLayout, 600, 500);
    roomInfoStage.setScene(roomInfoScene);

    roomInfoStage.show();
}

    private void showBookedRooms(TableView<Room> roomTable) {

        Stage bookedRoomsStage = new Stage();
        bookedRoomsStage.setTitle("Booked Rooms");

         VBox bookedRoomsLayout = new VBox(10);
         bookedRoomsLayout.setAlignment(Pos.CENTER);
         bookedRoomsLayout.setStyle("-fx-background-color: #e0ffe0; -fx-padding: 20;");


        TableView<Room> bookedRoomsTable = new TableView<>();
        TableColumn<Room, String> roomNumberColumn = new TableColumn<>("Room Number");
        roomNumberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));

        TableColumn<Room, String> bedTypeColumn = new TableColumn<>("Bed Type");
        bedTypeColumn.setCellValueFactory(new PropertyValueFactory<>("bedType"));

        TableColumn<Room, Double> priceColumn = new TableColumn<>("Price per Day");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Room, String> statusColumn = new TableColumn<>("Status");
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        bookedRoomsTable.getColumns().addAll(roomNumberColumn, bedTypeColumn, priceColumn, statusColumn);

        for (Room room : roomTable.getItems()) {
            if (room.getStatus().equalsIgnoreCase("Booked")) {
                bookedRoomsTable.getItems().add(room);
            }
        }

        Button backButton = new Button("Back to Room Info");
    backButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;"); // Changed styling
    backButton.setFont(Font.font("Times New Roman", FontWeight.BOLD, 14)); // Adjusted font
    backButton.setOnAction(event -> bookedRoomsStage.close());

    bookedRoomsLayout.getChildren().addAll(bookedRoomsTable, backButton);

    Scene bookedRoomsScene = new Scene(bookedRoomsLayout, 600, 500);
    bookedRoomsStage.setScene(bookedRoomsScene);

    bookedRoomsStage.show();
}
    // hello 

    private void handleStaffInfoButtonClick() {

        Stage staffInfoStage = new Stage();
        staffInfoStage.setTitle("Staff Information");

        VBox staffInfoLayout = new VBox(10);
        staffInfoLayout.setAlignment(Pos.CENTER);
        staffInfoLayout.setStyle("-fx-background-color: #e0ffe0; -fx-padding: 20;");

        TableView<Staff> staffTable = new TableView<>();
        TableColumn<Staff, String> staffNameColumn = new TableColumn<>("Staff Name");
        staffNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Staff, String> designationColumn = new TableColumn<>("Designation");
        designationColumn.setCellValueFactory(new PropertyValueFactory<>("designation"));

        TableColumn<Staff, Double> salaryColumn = new TableColumn<>("Salary");
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));

        staffTable.getColumns().addAll(staffNameColumn, designationColumn, salaryColumn);

        staffTable.getItems().add(new Staff("Tawsif Ahmed", "Receptionist", 75000));
        staffTable.getItems().add(new Staff("Wasif Chy", "Housekeeping", 40000));
        staffTable.getItems().add(new Staff("Rehan Hussain", "Asst. Manager", 60000));

        Button addStaffButton = new Button("Add Staff");
    addStaffButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
    addStaffButton.setFont(Font.font("Times New Roman", FontWeight.BOLD, 14)); // Adjust font if needed
    addStaffButton.setOnAction(event -> handleAddStaffButtonClick(staffTable));

    Button deleteStaffButton = new Button("Delete Staff");
    deleteStaffButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
    deleteStaffButton.setFont(Font.font("Times New Roman", FontWeight.BOLD, 14)); // Adjust font if needed
    deleteStaffButton.setOnAction(event -> handleDeleteStaffButtonClick(staffTable));

    HBox buttonBox = new HBox(10);
    buttonBox.setAlignment(Pos.BOTTOM_LEFT);
    buttonBox.getChildren().addAll(addStaffButton, deleteStaffButton);

    Button backButton = new Button("Back to Menu");
    backButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
    backButton.setFont(Font.font("Times New Roman", FontWeight.BOLD, 14)); // Adjust font if needed
    backButton.setOnAction(event -> {
        staffInfoStage.close();
        primaryStage.setScene(createMenuScene());
    });

    staffInfoLayout.getChildren().addAll(staffTable, buttonBox, backButton);

    Scene staffInfoScene = new Scene(staffInfoLayout, 600, 500);
    staffInfoStage.setScene(staffInfoScene);

    staffInfoStage.show();
}

    private void handleAddStaffButtonClick(TableView<Staff> staffTable) {

        Dialog<Staff> addStaffDialog = new Dialog<>();
        addStaffDialog.setTitle("Add Staff");
        addStaffDialog.setHeaderText("Enter Staff Information:");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField staffNameField = new TextField();
        TextField staffDesignationField = new TextField();
        TextField staffSalaryField = new TextField();

        grid.add(new Label("Staff Name:"), 0, 0);
        grid.add(staffNameField, 1, 0);
        grid.add(new Label("Designation:"), 0, 1);
        grid.add(staffDesignationField, 1, 1);
        grid.add(new Label("Salary:"), 0, 2);
        grid.add(staffSalaryField, 1, 2);

        addStaffDialog.getDialogPane().setContent(grid);

        ButtonType addButton = new ButtonType("Add", ButtonData.OK_DONE);
        addStaffDialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);

        addStaffDialog.setResultConverter(buttonType -> {
            if (buttonType == addButton) {
                String staffName = staffNameField.getText();
                String staffDesignation = staffDesignationField.getText();
                double staffSalary = Double.parseDouble(staffSalaryField.getText());

                return new Staff(staffName, staffDesignation, staffSalary);
            }
            return null;
        });

        Optional<Staff> result = addStaffDialog.showAndWait();

        result.ifPresent(staff -> {
            staffTable.getItems().add(staff);
        });
    }

    private void handleDeleteStaffButtonClick(TableView<Staff> staffTable) {

        Dialog<Staff> deleteStaffDialog = new Dialog<>();
        deleteStaffDialog.setTitle("Delete Staff");
        deleteStaffDialog.setHeaderText("Select Staff to Delete:");

        TableView<Staff> staffTableView = new TableView<>();
        staffTableView.setItems(staffTable.getItems());
        TableColumn<Staff, String> staffNameColumn = new TableColumn<>("Staff Name");
        staffNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        staffTableView.getColumns().add(staffNameColumn);

        deleteStaffDialog.getDialogPane().setContent(staffTableView);

        ButtonType deleteButton = new ButtonType("Delete", ButtonData.OK_DONE);
        deleteStaffDialog.getDialogPane().getButtonTypes().addAll(deleteButton, ButtonType.CANCEL);

        deleteStaffDialog.setResultConverter(buttonType -> {
            if (buttonType == deleteButton) {
                return staffTableView.getSelectionModel().getSelectedItem();
            }
            return null;
        });

        Optional<Staff> result = deleteStaffDialog.showAndWait();

        result.ifPresent(staff -> {
            staffTable.getItems().remove(staff);
        });
    }

    public class Room {
        private String number;
        private String bedType;
        private double price;
        private String status;

        public Room(String number, String bedType, double price, String status) {
            this.number = number;
            this.bedType = bedType;
            this.price = price;
            this.status = status;
        }

        public String getNumber() {
            return number;
        }

        public String getBedType() {
            return bedType;
        }

        public double getPrice() {
            return price;
        }

        public String getStatus() {
            return status;
        }
    }

    public class Staff {
        private String name;
        private String designation;
        private double salary;

        public Staff(String name, String designation, double salary) {
            this.name = name;
            this.designation = designation;
            this.salary = salary;
        }

        public String getName() {
            return name;
        }

        public String getDesignation() {
            return designation;
        }

        public double getSalary() {
            return salary;
        }
    }
}
