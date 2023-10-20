import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class HelloApplication extends Application {
	@Override
	public void start(Stage stage) {
		// 整个窗口大小
		double width = 428.0, heigth = 328.0;
		double lenx = width / 2, leny = heigth / 10 - 3;

		// 图片路径
		var PATH = "C:\\Users\\21396\\Pictures\\java实验一图片\\";

		// 登录背景
		var bgImage = new Image(PATH + "登录背景.png");
		var bgView = new ImageView(bgImage);
		bgView.relocate(0, 0);
		var spaceImage = new Image(PATH + "空白背景.png", bgImage.getWidth(), 328 - bgImage.getHeight(), false, false);
		var spView = new ImageView(spaceImage);
		spView.relocate(0, bgImage.getHeight());

		// 头像
		var headImage = new Image(PATH + "qq头像.png");
		var circle = new Circle(width / 2, bgImage.getHeight(), 35);
		circle.setFill(new ImagePattern(headImage));

		// 用户名
		TextField user = new TextField();
		user.relocate((width - lenx) / 2 + 12, 165);
		user.setPrefSize(lenx, leny);
		user.setPromptText("QQ号码/手机/邮箱");

		// 同户名左边的小企鹅
		var pengImageView = new ImageView(PATH + "小企鹅.png");
		pengImageView.relocate(user.getLayoutX() - 22, user.getLayoutY());

		// 用户名右边的小尖尖
		var choice = new ImageView(PATH + "choice.png");
		choice.relocate(user.getLayoutX() + lenx + 2, user.getLayoutY() + 2);

		// 密码
		PasswordField password = new PasswordField();
		password.relocate(user.getLayoutX(), 5 + user.getLayoutY() + user.getPrefHeight());
		password.setPrefSize(lenx, leny);
		password.setPromptText("密码");

		// 密码左边的锁
		var lockImageView = new ImageView(PATH + "Lock.png");
		lockImageView.relocate(password.getLayoutX() - 22, password.getLayoutY());

		// 密码右边的键盘
		var keyb = new ImageView(PATH + "keyb.png");
		keyb.relocate(password.getLayoutX() + lenx, password.getLayoutY());

		// 登录按钮
		Button login = new Button("登录");
		login.relocate(password.getLayoutX(), password.getLayoutY() + leny * 2);
		login.setPrefSize(lenx, leny);

		// 自动登录，记住密码 checkBox
		var check1 = new CheckBox();
		check1.setLayoutX(password.getLayoutX());
		check1.setLayoutY(password.getLayoutY() + leny);
		check1.setPrefWidth(lenx/5);
		check1.setPrefHeight(leny);
		var autoLogin=new Label("自动登录");
		autoLogin.relocate(check1.getLayoutX()+17, check1.getLayoutY()+6);
		
		var check2 = new CheckBox();
		check2.setLayoutX(width/2-10);
		check2.setLayoutY(check1.getLayoutY()+5);
		var remPass=new Label("记住密码");
		remPass.relocate(check2.getLayoutX()+17, check2.getLayoutY());

		// 找回密码
		var getBack = new Label("找回密码");
		getBack.relocate(password.getLayoutX() + lenx - 50, password.getLayoutY() + leny + 5);

		// 注册账号标签
		Label regi = new Label("注册账号");
		regi.relocate(10, heigth - 24);

		// 二维码
		var qrcode = new ImageView(PATH + "qrcode.png");
		qrcode.relocate(width - 35, heigth - 35);

		// rootNode
		var root = new Pane(bgView, spView, login, user, password, regi, circle, pengImageView, lockImageView, qrcode,
				getBack, choice, keyb, check1,autoLogin,check2,remPass);

		// 展示
		var scene = new Scene(root, width, heigth);
		stage.setScene(scene);
		stage.setTitle("QQ");
		stage.show();
	}
}
