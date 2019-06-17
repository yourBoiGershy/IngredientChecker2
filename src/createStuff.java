import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class createStuff extends Application {
	public FadeTransition fadeTransition;
	public Text instructions;
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {

	}

	public void setFadeTransition (Text o){
		fadeTransition = new FadeTransition(Duration.seconds(1.5), o);
		fadeTransition.setFromValue(1.0);
		fadeTransition.setToValue(0.0);
		fadeTransition.setCycleCount(Animation.INDEFINITE);
		fadeTransition.setAutoReverse(true);
	}

	public FadeTransition getFadeTransition (){
		return fadeTransition;
	}



}
