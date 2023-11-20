package com.example.demo;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.text.Font;

import java.sql.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class HelloApplication extends Application {
    public static class Person {
        private final SimpleStringProperty firstName;
        private final SimpleStringProperty lastName;
        private final SimpleStringProperty email;

        private Person(String fName, String lName, String email) {
            this.firstName = new SimpleStringProperty(fName);
            this.lastName = new SimpleStringProperty(lName);
            this.email = new SimpleStringProperty(email);
        }

        public String getFirstName() {
            return firstName.get();
        }

        public void setFirstName(String fName) {
            firstName.set(fName);
        }

        public String getLastName() {
            return lastName.get();
        }

        public void setLastName(String fName) {
            lastName.set(fName);
        }

        public String getEmail() {
            return email.get();
        }

        public void setEmail(String fName) {
            email.set(fName);
        }
    }

    private final ObservableList<Person> data = FXCollections.observableArrayList();
    private PreparedStatement loginStatement, tableSelectStatement, tableAddStatement;

    @Override
    public void start(Stage stage) {
        initStatement();

        stage.setScene(loginScene(stage));
//        stage.setScene(tableScene(stage)); //debug
        stage.show();
    }

    private void initStatement() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/qqdb?user=root&password=362573");
            loginStatement = connection.prepareStatement("select password from users where username = ?");
            tableSelectStatement = connection.prepareStatement("select * from addressbook");
            tableAddStatement = connection.prepareStatement("insert into addressbook values (?, ?, ?)");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void initTable() {
        try {
            ResultSet resultSet = tableSelectStatement.executeQuery();
            while (resultSet.next())
                data.add(new Person(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3)));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Scene loginScene(Stage stage) {
        stage.setTitle("QQ");

        // 整个窗口大小
        double width = 428.0, height = 328.0;
        double lenx = width / 2, leny = height / 10 - 3;

        // 图片路径
        String PATH = "E:\\java\\JavaFX\\src\\main\\resources\\image\\";

        // 登录背景
        Image bgImage = new Image(PATH + "登录背景.png");
        ImageView bgView = new ImageView(bgImage);
        bgView.relocate(0, 0);
        Image spaceImage = new Image(PATH + "空白背景.png", bgImage.getWidth(), 328 - bgImage.getHeight(), false, false);
        ImageView spView = new ImageView(spaceImage);
        spView.relocate(0, bgImage.getHeight());

        // 头像
        Image headImage = new Image(PATH + "qq头像.png");
        Circle circle = new Circle(width / 2, bgImage.getHeight(), 35);
        circle.setFill(new ImagePattern(headImage));

        // 用户名
        TextField user = new TextField();
        user.relocate((width - lenx) / 2 + 12, 165);
        user.setPrefSize(lenx, leny);
        user.setPromptText("QQ号码/手机/邮箱");

        // 同户名左边的小企鹅
        ImageView pengImageView = new ImageView(PATH + "小企鹅.png");
        pengImageView.relocate(user.getLayoutX() - 22, user.getLayoutY());

        // 用户名右边的小尖尖
        ImageView choice = new ImageView(PATH + "choice.png");
        choice.relocate(user.getLayoutX() + lenx + 2, user.getLayoutY() + 2);

        // 密码
        PasswordField password = new PasswordField();
        password.relocate(user.getLayoutX(), 5 + user.getLayoutY() + user.getPrefHeight());
        password.setPrefSize(lenx, leny);
        password.setPromptText("密码");

        // 密码左边的锁
        ImageView lockImageView = new ImageView(PATH + "Lock.png");
        lockImageView.relocate(password.getLayoutX() - 22, password.getLayoutY());

        // 密码右边的键盘
        ImageView keyb = new ImageView(PATH + "keyb.png");
        keyb.relocate(password.getLayoutX() + lenx, password.getLayoutY());

        // 登录按钮
        Button login = new Button("登录");
        login.relocate(password.getLayoutX(), password.getLayoutY() + leny * 2);
        login.setPrefSize(lenx, leny);

        //登录按钮下方的提示信息
        Label loginInfo = new Label();
        loginInfo.relocate(login.getLayoutX(), login.getLayoutY() + login.getPrefHeight());
        login.setOnAction(e -> {
            String passwd;
            try {
                loginStatement.setString(1, user.getText());
                ResultSet resultSet = loginStatement.executeQuery();
                if (resultSet.next())
                    passwd = resultSet.getString(1);
                else {
                    loginInfo.setTextFill(Color.RED);
                    loginInfo.setText("用户名不存在");
                    return;
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            if (password.getText().equals(passwd)) {
                loginInfo.setTextFill(Color.GREEN);
                loginInfo.setText("登录成功");
                stage.setScene(tableScene(stage));
            } else {
                loginInfo.setTextFill(Color.RED);
                loginInfo.setText("密码错误");
            }
        });

        // 自动登录，记住密码 checkBox
        CheckBox check1 = new CheckBox();
        check1.setLayoutX(password.getLayoutX());
        check1.setLayoutY(password.getLayoutY() + leny);
        check1.setPrefWidth(lenx / 5);
        check1.setPrefHeight(leny);
        Label autoLogin = new Label("自动登录");
        autoLogin.relocate(check1.getLayoutX() + 17, check1.getLayoutY() + 6);

        CheckBox check2 = new CheckBox();
        check2.setLayoutX(width / 2 - 10);
        check2.setLayoutY(check1.getLayoutY() + 5);
        Label remPass = new Label("记住密码");
        remPass.relocate(check2.getLayoutX() + 17, check2.getLayoutY());

        // 找回密码
        Label getBack = new Label("找回密码");
        getBack.relocate(password.getLayoutX() + lenx - 50, password.getLayoutY() + leny + 5);

        // 注册账号标签
        Label regi = new Label("注册账号");
        regi.relocate(10, height - 24);

        // 二维码
        ImageView qrcode = new ImageView(PATH + "qrcode.png");
        qrcode.relocate(width - 35, height - 35);

        // rootNode
        Pane root = new Pane(bgView, spView, login, user, password, regi, circle, pengImageView, lockImageView, qrcode,
                getBack, choice, keyb, check1, autoLogin, check2, remPass, loginInfo);

        // 展示
        return new Scene(root, width, height);
    }

    private Scene tableScene(Stage stage) {
        int width = 450, height = 500;

        initTable();

        stage.setTitle("Table View Sample");
        stage.setWidth(width);
        stage.setHeight(height);

        Label label = new Label("Address Book");
        label.setFont(new Font("Arial", 20));

        HBox hBox = new HBox(width / 3, label, getClock());

        VBox vbox = new VBox(5);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.getChildren().addAll(hBox, getTable(), getAddInput());

        return new Scene(vbox);
    }

    private Label getClock() {
        Label clock = new Label();
        clock.setFont(new Font("Arial", 20));

        new Thread(new Runnable() {
            @Override
            public void run() {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

                while (true) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            clock.setText(formatter.format(LocalTime.now()));
                        }
                    });

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ignored) {
                    }
                }
            }
        }).start();

        return clock;
    }


    private TableView getTable() {
        TableView<Person> table = new TableView<>();
        table.setEditable(true);

        TableColumn firstNameCol = new TableColumn("First Name");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        firstNameCol.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn lastNameCol = new TableColumn("Last Name");
        lastNameCol.setMinWidth(100);
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        lastNameCol.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn emailCol = new TableColumn("Email");
        emailCol.setMinWidth(200);
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        emailCol.setCellFactory(TextFieldTableCell.forTableColumn());

        table.setItems(data);
        table.getColumns().addAll(firstNameCol, lastNameCol, emailCol);

        return table;
    }

    private HBox getAddInput() {
        TextField firstNameInput = new TextField();
        firstNameInput.setPromptText("First Name");

        TextField lastNameInput = new TextField();
        lastNameInput.setPromptText("Last Name");

        TextField emailInput = new TextField();
        emailInput.setPromptText("Email");

        Button addButton = new Button("Add");
        addButton.setMinWidth(50);
        addButton.setOnAction((ActionEvent e) -> {
            String firstName = firstNameInput.getText();
            String lastName = lastNameInput.getText();
            String email = emailInput.getText();
            if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty())
                return;

            try {
                tableAddStatement.setString(1, firstName);
                tableAddStatement.setString(2, lastName);
                tableAddStatement.setString(3, email);
                tableAddStatement.executeUpdate();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            data.add(new Person(firstName, lastName, email));
        });

        return new HBox(5, firstNameInput, lastNameInput, emailInput, addButton);
    }
}