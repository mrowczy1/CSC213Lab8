import controllers.TimerController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

/**
 * Launches the timer app!
 *
 * @author Jon Mrowczynski
 */
public class TimerAppLauncher extends Application {
	
	@Override public void start(final Stage primaryStage) throws Exception {
		// Set up the primary Stage (Window) before showing it to the user.
		primaryStage.setTitle("Timer");
		
		// Load the presentation layer.
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// TODO: Only change this line!
		final var loader = new FXMLLoader(TimerAppLauncher.class.getResource("/fxml/timer.fxml"));
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////
		primaryStage.setScene(loader.load());
		final TimerController controller = loader.getController();
		// Need to ensure that our spawned thread gets properly terminated.
		primaryStage.setOnCloseRequest(_ -> controller.shutdown());
		
		// Once everything is set up, show the window!
		primaryStage.show();
	}
}