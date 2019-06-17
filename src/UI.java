import javafx.animation.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import java.io.File;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Scanner;

import javafx.geometry.Orientation;

import javax.swing.text.Position;
import javax.swing.text.StyledEditorKit;


public class UI extends Application {

	private ProgressBar loadProgress;
	private Pane splashLayout;
	private ProgressBar load;
	private static final int SPLASH_WIDTH = 875, SPLASH_HEIGHT = 500;
	private Button exit = new Button();
	private ImageView webIcon = new ImageView(new Image("Images/APPICON.png")),
			welcomeImage = new ImageView(new Image("Images/welcomeImage.png")),
			closeSymbol = new ImageView(new Image("Images/closeSymbol3.png")),
			aboutUs = new ImageView(new Image("Images/online-grocery.jpg")),
			logo2 = new ImageView(new Image("Images/WebIcon2.png"));
	private String userSearch = new String();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(final Stage loadingStage) {

		showSplashScreen(loadingStage);
		Timeline timeLine = new Timeline(new KeyFrame(Duration.seconds(7), e-> {
			if(Main.netIsAvailable()) {
				loadingStage.hide();
				Main();
			}
			else {
				loadingStage.hide();
				System.out.println("no connection");
				showNoConnection();
			}
		}));
		timeLine.setCycleCount(1);
		timeLine.play();

	}

	public void showNoConnection(){
		Group root = new Group();
		Scene Scene = new Scene(root);
		Stage main = new Stage();
		main.setScene(Scene);
		Text t = new Text();
		t.setText("No internet connection, please restart the program with a secure connection");
		t.setFont(new Font("Times New Roman", 18));
		t.setFill(Color.RED);
		t.setX(100);
		t.setY(100);
		main.setMinHeight(200);
		main.setMaxHeight(200);
		main.setMinWidth(700);
		main.setMaxWidth(700);
		root.getChildren().add(t);
		main.show();

	}

	public void showSplashScreen(Stage loadingStage){
		Scene loadingScreen = new Scene(splashLayout);
		loadingStage.setScene(loadingScreen);
		loadingStage.initStyle(StageStyle.UNDECORATED);
		final Rectangle2D bounds = Screen.getPrimary().getBounds();
		loadingStage.setX(bounds.getMinX() + bounds.getWidth() / 2 - SPLASH_WIDTH / 2);
		loadingStage.setY(bounds.getMinY() + bounds.getHeight() / 2 - SPLASH_HEIGHT / 2);
		loadingStage.show();
	}

	public void init(){
		splashLayout = new VBox();

		loadProgress = new ProgressBar();
		loadProgress.setPrefWidth(SPLASH_WIDTH);

		splashLayout.getChildren().addAll(webIcon, loadProgress);
		String style = "-fx-background-color:rgb(0,0,0,1);";
		splashLayout.setStyle(style);
		splashLayout.setEffect(new DropShadow());
	}

	public void Main(){
		Group root = new Group();
		Scene Scene = new Scene(root);
		Stage main = new Stage();
		main.setScene(Scene);
		main.setResizable(true);
		main.setFullScreen(true);
		main.setTitle("Grocery Checker");

		double x = 1238, y = 17;


		exit.setPrefSize(40, 40);
		exit.setBackground(Background.EMPTY);
		exit.setLayoutX(x);
		exit.setLayoutY(y);

		closeSymbol.setLayoutX(x);
		closeSymbol.setLayoutY(y);

		welcomeImage.setFitWidth(1250);
		welcomeImage.setLayoutX(17);
		welcomeImage.setLayoutY(75);
		welcomeImage.setPreserveRatio(true);

		ImageView background = new ImageView(new Image("Images/black.jpg"));
		background.setFitWidth(1200);
		background.setFitHeight(50);
		background.setLayoutX(30);
		background.setLayoutY(10);

		Button home = new Button ("HOME");
		home.setLayoutX(30);
		home.setLayoutY(10);
		home.setTextAlignment(TextAlignment.CENTER);
		home.setMaxSize(180, 50);
		home.setMinHeight(50);
		home.setMinWidth(180);
		home.setBackground(Background.EMPTY);
		home.setTextFill(Color.WHITE);
		home.setStyle("-fx-border-color: white;");

		Button about = new Button ("ABOUT US");
		about.setLayoutX(210);
		about.setLayoutY(10);
		about.setTextAlignment(TextAlignment.CENTER);
		about.setMaxSize(180, 50);
		about.setMinHeight(50);
		about.setMinWidth(180);
		about.setBackground(Background.EMPTY);
		about.setTextFill(Color.WHITE);
		about.setStyle("-fx-border-color: white;");

		Button partners = new Button ("OUR PARTNER");
		partners.setLayoutX(390);
		partners.setLayoutY(10);
		partners.setTextAlignment(TextAlignment.CENTER);
		partners.setMaxSize(180, 50);
		partners.setMinHeight(50);
		partners.setMinWidth(180);
		partners.setBackground(Background.EMPTY);
		partners.setTextFill(Color.WHITE);
		partners.setStyle("-fx-border-color: white;");

		Button start = new Button ("GET STARTED");
		start.setLayoutX(570);
		start.setLayoutY(10);
		start.setTextAlignment(TextAlignment.CENTER);
		start.setMaxSize(180, 50);
		start.setMinHeight(50);
		start.setMinWidth(180);
		start.setBackground(Background.EMPTY);
		start.setTextFill(Color.WHITE);
		start.setStyle("-fx-border-color: white;");

		TextField search = new TextField();
		search.setLayoutX(750);
		search.setLayoutY(11);
		search.setMinSize(300,48);
		search.setMaxSize(300, 48);

		Button Bsearch = new Button("SEARCH");
		Bsearch.setLayoutX(1050);
		Bsearch.setLayoutY(10);
		Bsearch.setTextAlignment(TextAlignment.CENTER);
		Bsearch.setMaxSize(180, 50);
		Bsearch.setMinHeight(50);
		Bsearch.setMinWidth(180);
		Bsearch.setBackground(Background.EMPTY);
		Bsearch.setTextFill(Color.WHITE);
		Bsearch.setStyle("-fx-border-color: white;");

		Bsearch.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						userSearch = search.getText();
						//System.out.println(Main.GetIngredients(userSearch));
						Text b = new Text();
						search.clear();
						showSearchResults(Main.GetIngredients(userSearch));
						userSearch = "";
						main.hide();
					}
				});

		final ScrollBar scrollBar = new ScrollBar();

		exit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				main.hide();
			}
		});

		home.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Main();
				main.hide();
			}
		});

		about.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				aboutScreen();
				main.hide();
			}
		});

		partners.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				partners();
				main.hide();
			}
		});

		start.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				getStarted();
				main.hide();
			}
		});

		Scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
					case ENTER: userSearch = search.getText(); Text b = new Text(); search.clear(); showSearchResults(Main.GetIngredients(userSearch)); userSearch = ""; main.hide();
				}
			}
		});

		root.getChildren().addAll(welcomeImage, background, closeSymbol, exit, home, about, partners, start, Bsearch, search);
		main.show();

	}

	public void aboutScreen(){
		Group root = new Group();
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setFullScreen(true);

		double x = 1238, y = 17;

		Text title = new Text();
		title.setText("WELCOME");
		title.setTextAlignment(TextAlignment.CENTER);
		title.setFont(new Font("Times New Roman", 30));
		title.setX(550);
		title.setY(118);

		Text t = new Text();
		t.setText("\n" +
				"Hi, and welcome to FoodInfo. FoodInfo is an simple desktop application that helps you find out the ingredients for any grocery products. Our" +
				"\nmain goal to develop the program is to provide a simple search engine that tells you the exact ingredients of the product that you are concern" +
				"\nabout. Product informations will be display to assist you in determining whether or not product is good for you, and threat of allergies. The" +
				"\napp is easy to use, and save time, instead of you going to grocery stores and check out about the ingredients. We hope FoodInfo could make your" +
				"\nlife more convenient!");
		t.setX(30);
		t.setY(150);
		t.setFont(new Font("Times New Roman", 20));
		t.setTextAlignment(TextAlignment.CENTER);


		exit.setPrefSize(40, 40);
		exit.setBackground(Background.EMPTY);
		exit.setLayoutX(x);
		exit.setLayoutY(y);

		closeSymbol.setLayoutX(x);
		closeSymbol.setLayoutY(y);

		ImageView background = new ImageView(new Image("Images/black.jpg"));
		background.setFitWidth(1200);
		background.setFitHeight(50);
		background.setLayoutX(30);
		background.setLayoutY(10);

		Button home = new Button ("HOME");
		home.setLayoutX(30);
		home.setLayoutY(10);
		home.setTextAlignment(TextAlignment.CENTER);
		home.setMaxSize(180, 50);
		home.setMinHeight(50);
		home.setMinWidth(180);
		home.setBackground(Background.EMPTY);
		home.setTextFill(Color.WHITE);
		home.setStyle("-fx-border-color: white;");

		Button about = new Button ("ABOUT US");
		about.setLayoutX(210);
		about.setLayoutY(10);
		about.setTextAlignment(TextAlignment.CENTER);
		about.setMaxSize(180, 50);
		about.setMinHeight(50);
		about.setMinWidth(180);
		about.setBackground(Background.EMPTY);
		about.setTextFill(Color.WHITE);
		about.setStyle("-fx-border-color: white;");

		Button partners = new Button ("OUR PARTNER");
		partners.setLayoutX(390);
		partners.setLayoutY(10);
		partners.setTextAlignment(TextAlignment.CENTER);
		partners.setMaxSize(180, 50);
		partners.setMinHeight(50);
		partners.setMinWidth(180);
		partners.setBackground(Background.EMPTY);
		partners.setTextFill(Color.WHITE);
		partners.setStyle("-fx-border-color: white;");

		Button start = new Button ("GET STARTED");
		start.setLayoutX(570);
		start.setLayoutY(10);
		start.setTextAlignment(TextAlignment.CENTER);
		start.setMaxSize(180, 50);
		start.setMinHeight(50);
		start.setMinWidth(180);
		start.setBackground(Background.EMPTY);
		start.setTextFill(Color.WHITE);
		start.setStyle("-fx-border-color: white;");

		TextField search = new TextField();
		search.setLayoutX(750);
		search.setLayoutY(11);
		search.setMinSize(300,48);
		search.setMaxSize(300, 48);

		Button Bsearch = new Button("SEARCH");
		Bsearch.setLayoutX(1050);
		Bsearch.setLayoutY(10);
		Bsearch.setTextAlignment(TextAlignment.CENTER);
		Bsearch.setMaxSize(180, 50);
		Bsearch.setMinHeight(50);
		Bsearch.setMinWidth(180);
		Bsearch.setBackground(Background.EMPTY);
		Bsearch.setTextFill(Color.WHITE);
		Bsearch.setStyle("-fx-border-color: white;");

		aboutUs.setLayoutX(30);
		aboutUs.setLayoutY(300);

		Bsearch.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						userSearch = search.getText();
						search.clear();
						showSearchResults(Main.GetIngredients(userSearch));
						userSearch = "";
						stage.hide();
					}
				});

		exit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				stage.hide();
			}
		});

		home.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Main();
				stage.hide();
			}
		});

		about.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				aboutScreen();
				stage.hide();
			}
		});

		partners.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				partners();
				stage.hide();
			}
		});

		start.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				getStarted();
				stage.hide();
			}
		});

		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
					case ENTER: userSearch = search.getText(); Text b = new Text(); search.clear(); showSearchResults(Main.GetIngredients(userSearch)); userSearch = ""; stage.hide();
				}
			}
		});

		root.getChildren().addAll(title, t, aboutUs, background, closeSymbol, exit, home, about, partners, start, Bsearch, search);

		stage.show();
	}


	public void showSearchResults(String display){
		Group root = new Group();
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setFullScreen(true);

		ImageView img1;
		ImageView img2;
		ImageView img3;
		ImageView img4;
		ImageView img5;


		Text n1, n2, n3, n4, n5;
		n1 = new Text(GetItemBarcode.name1);
		n2 = new Text(GetItemBarcode.name2);
		n3 = new Text(GetItemBarcode.name3);
		n4 = new Text(GetItemBarcode.name4);
		n5 = new Text(GetItemBarcode.name5);

		n1.setX(180);
		n2.setX(180);
		n3.setX(180);
		n4.setX(180);
		n5.setX(180);

		n1.setY(110);
		n2.setY(230);
		n3.setY(350);
		n4.setY(470);
		n5.setY(590);

		Text names = new Text(GetItemBarcode.name);
		names.setX(180);
		names.setY(200);
		names.setFont(new Font("Times New Roman", 12));


		Text results;
		Text results1, results2, results3, results4, results5;

		if (Main.result1.length() < 1){
			results = new Text("No results");
			results.setX(30);
			results.setY(200);
			results.setFont(new Font("Times New Roman", 40));
			root.getChildren().add(results);
		}else {

			results1 = new Text(Main.result1);
			results2 = new Text(Main.result2);
			results3 = new Text(Main.result3);
			results4 = new Text(Main.result4);
			results5 = new Text(Main.result5);

			img1 = new ImageView(new Image(GetItemBarcode.link));
			img2 = new ImageView(new Image(GetItemBarcode.link1));
			img3 = new ImageView(new Image(GetItemBarcode.link2));
			img4 = new ImageView(new Image(GetItemBarcode.link3));
			img5 = new ImageView(new Image(GetItemBarcode.link4));

			results1.setX(550);
			results2.setX(550);
			results3.setX(550);
			results4.setX(550);
			results5.setX(550);

			results1.setY(110);
			results2.setY(230);
			results3.setY(350);
			results4.setY(470);
			results5.setY(590);

			img1.setFitHeight(100);
			img2.setFitHeight(100);
			img3.setFitHeight(100);
			img4.setFitHeight(100);
			img5.setFitHeight(100);

			img1.setFitWidth(100);
			img2.setFitWidth(100);
			img3.setFitWidth(100);
			img4.setFitWidth(100);
			img5.setFitWidth(100);

			img1.setX(30);
			img1.setY(100);

			img2.setX(30);
			img2.setY(220);

			img3.setX(30);
			img3.setY(340);

			img4.setX(30);
			img4.setY(460);

			img5.setX(30);
			img5.setY(580);

			root.getChildren().addAll(n1, n2, n3, n4, n5, results1, results2, results3, results4, results5);
			root.getChildren().addAll(img1);
			if(results2.getText().length()>1)
				root.getChildren().addAll(img2);
			if(results3.getText().length()>1)
				root.getChildren().addAll(img3);
			if(results4.getText().length()>1)
				root.getChildren().addAll(img4);
			if(results5.getText().length()>1)
				root.getChildren().addAll(img5);
		}

		double x = 1238, y = 17;


		exit.setPrefSize(40, 40);
		exit.setBackground(Background.EMPTY);
		exit.setLayoutX(x);
		exit.setLayoutY(y);

		closeSymbol.setLayoutX(x);
		closeSymbol.setLayoutY(y);

		ImageView background = new ImageView(new Image("Images/black.jpg"));
		background.setFitWidth(1200);
		background.setFitHeight(50);
		background.setLayoutX(30);
		background.setLayoutY(10);

		Button home = new Button ("HOME");
		home.setLayoutX(30);
		home.setLayoutY(10);
		home.setTextAlignment(TextAlignment.CENTER);
		home.setMaxSize(180, 50);
		home.setMinHeight(50);
		home.setMinWidth(180);
		home.setBackground(Background.EMPTY);
		home.setTextFill(Color.WHITE);
		home.setStyle("-fx-border-color: white;");

		Button about = new Button ("ABOUT US");
		about.setLayoutX(210);
		about.setLayoutY(10);
		about.setTextAlignment(TextAlignment.CENTER);
		about.setMaxSize(180, 50);
		about.setMinHeight(50);
		about.setMinWidth(180);
		about.setBackground(Background.EMPTY);
		about.setTextFill(Color.WHITE);
		about.setStyle("-fx-border-color: white;");

		Button partners = new Button ("OUR PARTNER");
		partners.setLayoutX(390);
		partners.setLayoutY(10);
		partners.setTextAlignment(TextAlignment.CENTER);
		partners.setMaxSize(180, 50);
		partners.setMinHeight(50);
		partners.setMinWidth(180);
		partners.setBackground(Background.EMPTY);
		partners.setTextFill(Color.WHITE);
		partners.setStyle("-fx-border-color: white;");

		Button start = new Button ("GET STARTED");
		start.setLayoutX(570);
		start.setLayoutY(10);
		start.setTextAlignment(TextAlignment.CENTER);
		start.setMaxSize(180, 50);
		start.setMinHeight(50);
		start.setMinWidth(180);
		start.setBackground(Background.EMPTY);
		start.setTextFill(Color.WHITE);
		start.setStyle("-fx-border-color: white;");

		TextField search = new TextField();
		search.setLayoutX(750);
		search.setLayoutY(11);
		search.setMinSize(300,48);
		search.setMaxSize(300, 48);

		Button Bsearch = new Button("SEARCH");
		Bsearch.setLayoutX(1050);
		Bsearch.setLayoutY(10);
		Bsearch.setTextAlignment(TextAlignment.CENTER);
		Bsearch.setMaxSize(180, 50);
		Bsearch.setMinHeight(50);
		Bsearch.setMinWidth(180);
		Bsearch.setBackground(Background.EMPTY);
		Bsearch.setTextFill(Color.WHITE);
		Bsearch.setStyle("-fx-border-color: white;");

		Bsearch.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						userSearch = search.getText();
						search.clear();
						showSearchResults(Main.GetIngredients(userSearch));
						userSearch = "";
						stage.hide();
					}
				});

		exit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				stage.hide();
			}
		});

		home.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Main();
				stage.hide();
			}
		});

		about.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				aboutScreen();
				stage.hide();
			}
		});

		partners.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				partners();
				stage.hide();
			}
		});

		start.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				getStarted();
				stage.hide();
			}
		});

		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
					case ENTER: userSearch = search.getText(); System.out.println(userSearch); search.clear(); showSearchResults(Main.GetIngredients(userSearch)); userSearch = ""; stage.hide();
				}
			}
		});

		root.getChildren().addAll(background, closeSymbol, exit, home, about, partners, start, Bsearch, search);
		stage.show();

		stage.show();

	}

	public void partners(){
		Group root = new Group();
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setFullScreen(true);

		Text t = new Text();
		t.setText("We are partnered with Open Food Facts - Canada organization and all our product informations come from the public API that the organization" +
				"\nhad provided us. The organization has around 80k grocery products information stored in its database, it has comprehensive information " +
				"\nabout most Canadian grocery products!");
		t.setX(50);
		t.setY(175);
		t.setTextAlignment(TextAlignment.CENTER);
		t.setFont(new Font("Times New Roman", 20));

		Text title = new Text();
		title.setText("Partnership");
		title.setTextAlignment(TextAlignment.CENTER);
		title.setFont(new Font("Times New Roman", 30));
		title.setX(550);
		title.setY(115);

		double x = 1238, y = 17;


		exit.setPrefSize(40, 40);
		exit.setBackground(Background.EMPTY);
		exit.setLayoutX(x);
		exit.setLayoutY(y);

		closeSymbol.setLayoutX(x);
		closeSymbol.setLayoutY(y);

		ImageView background = new ImageView(new Image("Images/black.jpg"));
		background.setFitWidth(1200);
		background.setFitHeight(50);
		background.setLayoutX(30);
		background.setLayoutY(10);

		Button home = new Button ("HOME");
		home.setLayoutX(30);
		home.setLayoutY(10);
		home.setTextAlignment(TextAlignment.CENTER);
		home.setMaxSize(180, 50);
		home.setMinHeight(50);
		home.setMinWidth(180);
		home.setBackground(Background.EMPTY);
		home.setTextFill(Color.WHITE);
		home.setStyle("-fx-border-color: white;");

		Button about = new Button ("ABOUT US");
		about.setLayoutX(210);
		about.setLayoutY(10);
		about.setTextAlignment(TextAlignment.CENTER);
		about.setMaxSize(180, 50);
		about.setMinHeight(50);
		about.setMinWidth(180);
		about.setBackground(Background.EMPTY);
		about.setTextFill(Color.WHITE);
		about.setStyle("-fx-border-color: white;");

		Button partners = new Button ("OUR PARTNER");
		partners.setLayoutX(390);
		partners.setLayoutY(10);
		partners.setTextAlignment(TextAlignment.CENTER);
		partners.setMaxSize(180, 50);
		partners.setMinHeight(50);
		partners.setMinWidth(180);
		partners.setBackground(Background.EMPTY);
		partners.setTextFill(Color.WHITE);
		partners.setStyle("-fx-border-color: white;");

		Button start = new Button ("GET STARTED");
		start.setLayoutX(570);
		start.setLayoutY(10);
		start.setTextAlignment(TextAlignment.CENTER);
		start.setMaxSize(180, 50);
		start.setMinHeight(50);
		start.setMinWidth(180);
		start.setBackground(Background.EMPTY);
		start.setTextFill(Color.WHITE);
		start.setStyle("-fx-border-color: white;");

		TextField search = new TextField();
		search.setLayoutX(750);
		search.setLayoutY(11);
		search.setMinSize(300,48);
		search.setMaxSize(300, 48);

		Button Bsearch = new Button("SEARCH");
		Bsearch.setLayoutX(1050);
		Bsearch.setLayoutY(10);
		Bsearch.setTextAlignment(TextAlignment.CENTER);
		Bsearch.setMaxSize(180, 50);
		Bsearch.setMinHeight(50);
		Bsearch.setMinWidth(180);
		Bsearch.setBackground(Background.EMPTY);
		Bsearch.setTextFill(Color.WHITE);
		Bsearch.setStyle("-fx-border-color: white;");

		aboutUs.setLayoutX(30);
		aboutUs.setPreserveRatio(false);
		aboutUs.setFitWidth(1200);
		aboutUs.setFitHeight(500);
		aboutUs.setLayoutY(300);

		Bsearch.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						userSearch = search.getText();
						search.clear();
						showSearchResults(Main.GetIngredients(userSearch));
						userSearch = "";
						stage.hide();
					}
				});

		final ScrollBar scrollBar = new ScrollBar();

		exit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				stage.hide();
			}
		});

		home.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Main();
				stage.hide();
			}
		});

		about.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				aboutScreen();
				stage.hide();
			}
		});

		partners.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				partners();
				stage.hide();
			}
		});

		start.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				getStarted();
				stage.hide();
			}
		});

		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
					case ENTER: userSearch = search.getText(); search.clear(); showSearchResults(Main.GetIngredients(userSearch)); userSearch = ""; stage.hide();
				}
			}
		});

		root.getChildren().addAll(title, t, aboutUs, background, closeSymbol, exit, home, about, partners, start, Bsearch, search);
		stage.show();

		stage.show();
	}


	public void getStarted(){
		Group root = new Group();
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setFullScreen(true);

		double x = 1238, y = 17;

		TextField search2 = new TextField();
		search2.setLayoutX(380);
		search2.setLayoutY(450);
		search2.setMinSize(500,75);
		search2.setMaxSize(500, 75);
		search2.setFont(new Font("Times New Roman", 20));


		Text t = new Text();
		t.setText("Get started by searching for a product!");
		t.setFont(new Font("Times New Roman", 30));
		t.setTextAlignment(TextAlignment.CENTER);
		t.setX(400);
		t.setY(575);

		logo2.setPreserveRatio(true);
		logo2.setFitHeight(400);
		logo2.setX(425);
		logo2.setY(50);

		exit.setPrefSize(40, 40);
		exit.setBackground(Background.EMPTY);
		exit.setLayoutX(x);
		exit.setLayoutY(y);

		closeSymbol.setLayoutX(x);
		closeSymbol.setLayoutY(y);

		ImageView background = new ImageView(new Image("Images/black.jpg"));
		background.setFitWidth(1200);
		background.setFitHeight(50);
		background.setLayoutX(30);
		background.setLayoutY(10);

		Button home = new Button ("HOME");
		home.setLayoutX(30);
		home.setLayoutY(10);
		home.setTextAlignment(TextAlignment.CENTER);
		home.setMaxSize(180, 50);
		home.setMinHeight(50);
		home.setMinWidth(180);
		home.setBackground(Background.EMPTY);
		home.setTextFill(Color.WHITE);
		home.setStyle("-fx-border-color: white;");

		Button about = new Button ("ABOUT US");
		about.setLayoutX(210);
		about.setLayoutY(10);
		about.setTextAlignment(TextAlignment.CENTER);
		about.setMaxSize(180, 50);
		about.setMinHeight(50);
		about.setMinWidth(180);
		about.setBackground(Background.EMPTY);
		about.setTextFill(Color.WHITE);
		about.setStyle("-fx-border-color: white;");

		Button partners = new Button ("OUR PARTNER");
		partners.setLayoutX(390);
		partners.setLayoutY(10);
		partners.setTextAlignment(TextAlignment.CENTER);
		partners.setMaxSize(180, 50);
		partners.setMinHeight(50);
		partners.setMinWidth(180);
		partners.setBackground(Background.EMPTY);
		partners.setTextFill(Color.WHITE);
		partners.setStyle("-fx-border-color: white;");

		Button start = new Button ("GET STARTED");
		start.setLayoutX(570);
		start.setLayoutY(10);
		start.setTextAlignment(TextAlignment.CENTER);
		start.setMaxSize(180, 50);
		start.setMinHeight(50);
		start.setMinWidth(180);
		start.setBackground(Background.EMPTY);
		start.setTextFill(Color.WHITE);
		start.setStyle("-fx-border-color: white;");

		TextField search = new TextField();
		search.setLayoutX(750);
		search.setLayoutY(11);
		search.setMinSize(300,48);
		search.setMaxSize(300, 48);

		Button Bsearch = new Button("SEARCH");
		Bsearch.setLayoutX(1050);
		Bsearch.setLayoutY(10);
		Bsearch.setTextAlignment(TextAlignment.CENTER);
		Bsearch.setMaxSize(180, 50);
		Bsearch.setMinHeight(50);
		Bsearch.setMinWidth(180);
		Bsearch.setBackground(Background.EMPTY);
		Bsearch.setTextFill(Color.WHITE);
		Bsearch.setStyle("-fx-border-color: white;");

		Bsearch.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						userSearch = search.getText();
						search.clear();
						showSearchResults(Main.GetIngredients(userSearch));
						userSearch = "";
						stage.hide();
					}
				});

		final ScrollBar scrollBar = new ScrollBar();

		exit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				stage.hide();
			}
		});

		home.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Main();
				stage.hide();
			}
		});

		about.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				aboutScreen();
				stage.hide();
			}
		});

		partners.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				partners();
				stage.hide();
			}
		});

		start.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				getStarted();
				stage.hide();
			}
		});

		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
					case ENTER: userSearch = search2.getText(); Text b = new Text(); search2.clear(); search.clear(); showSearchResults(Main.GetIngredients(userSearch)); userSearch = ""; stage.hide();
				}
			}
		});

		root.getChildren().addAll(logo2, t, search2, background, closeSymbol, exit, home, about, partners, start, Bsearch, search);
		stage.show();

		stage.show();
	}

}
