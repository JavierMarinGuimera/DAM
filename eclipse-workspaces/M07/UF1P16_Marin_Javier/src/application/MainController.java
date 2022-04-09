package application;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * Couples game controller.
 * 
 * @author Javier Marín Guimerà
 */
public class MainController implements Initializable {
	private static final String CONFIRMATION_TEXT = "Ya has terminado el juego. ¿Quieres volver a jugar?";
	private static final String CONFIRMATION_TITLE = "Confirmación";
	private static final String IMG = "img";
	private static final String JPG = ".jpg";

	/**
	 * Algorithm variables.
	 */
	private static final int TOTAL_COLUMNS = 4;
	private static final int TOTAL_ROWS = 3;
	private static final int SCORE_MULTIPLIER = 2;

	/**
	 * App IDs.
	 */
	@FXML
	private TextField tf_points;
	@FXML
	private ImageView iv_button;
	@FXML
	private GridPane gpTable;

	/**
	 * Local variables:
	 */
	private String imgFolder = "resources/img/";
	private String defaultImg = "img_off.jpg";

	@SuppressWarnings("serial")
	private HashMap<String, Integer> imgListCounter = new HashMap<>() {
		{
			put(IMG + "01" + JPG, 0);
			put(IMG + "02" + JPG, 0);
			put(IMG + "03" + JPG, 0);
			put(IMG + "04" + JPG, 0);
			put(IMG + "05" + JPG, 0);
			put(IMG + "06" + JPG, 0);
		}
	};

	private HashMap<ImageView, String> gridChilds = new HashMap<>();
	private List<ImageView> lastSelectedImgs = null;
	private boolean hasToResetLastImages = false;
	private int userTotalClicks = 0;
	private int unfoundedImagesCouples = TOTAL_ROWS * TOTAL_COLUMNS / 2;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		addResetButtonFunctionality();
		
		getRandomImages();

		addImageViewsFunctionality();
		
		updateUserScore();
	}
	
	/**
	 * Simple method to create a custom alert dialog.
	 * 
	 * @param alert The reference to the alert that we need to create.
	 * @param type  Type of the alert.
	 * @param title Title for the alert.
	 * @param text  Text for the alert.
	 */
	private void createAndShowCustomAlert(AlertType type, String title, String text) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setContentText(text);
		Optional<ButtonType> option = alert.showAndWait();

		if (type == Alert.AlertType.CONFIRMATION) {
			if (option.get() == ButtonType.OK) {
				reloadGame();
			}
		}
	}

	/**
	 * this method will put the default image for every image.
	 */
	private void setDefaultImgs() {
		for (Map.Entry<ImageView, String> entry : gridChilds.entrySet()) {
			setDefaultImg(entry.getKey());
		}
	}

	/**
	 * this method will put the default image for a single image.
	 * 
	 * @param imageView The ImageView to reset the image.
	 */
	private void setDefaultImg(ImageView imageView) {
		imageView.setImage(new Image(new File(imgFolder + defaultImg).getAbsolutePath()));
	}

	/**
	 * This method gives the click event listener to the reset button.
	 */
	private void addResetButtonFunctionality() {
		this.iv_button.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {
				reloadGame();
			}

		});
	}

	/**
	 * This method gives the click event listener to the image views.
	 */
	private void addImageViewsFunctionality() {
		for (Node child : gpTable.getChildren()) {
			if (child instanceof ImageView) {
				ImageView childAsImageView = (ImageView) child;

				childAsImageView.setOnMouseClicked(new EventHandler<Event>() {
					@Override
					public void handle(Event arg0) {
						if (unfoundedImagesCouples != 0) {
							updateGame(childAsImageView);
						} else {
							createAndShowCustomAlert(Alert.AlertType.CONFIRMATION, CONFIRMATION_TITLE,
									CONFIRMATION_TEXT);
						}
					}
				});
				;
			}
		}
	}

	/**
	 * This method will put the new hiding images on the grid.
	 */
	private void getRandomImages() {
		for (Node child : gpTable.getChildren()) {
			if (child instanceof ImageView) {
				gridChilds.put((ImageView) child, getRandomImg());
			}
		}
	}

	/**
	 * This method will return an available image to set on the current GridPane
	 * child that we are looking on.
	 * 
	 * @return Return the image name.
	 */
	private String getRandomImg() {
		while (true) {
			int imgNumber = (int) (Math.random() * imgListCounter.size()) + 1;
			if (imgListCounter.get(imgListCounter.keySet().toArray()[imgNumber - 1]) < 2) {
				// Simplifying, this will update the counter of the image to know if we already
				// placed twice on the game.
				imgListCounter.put(imgListCounter.keySet().toArray()[imgNumber - 1].toString(),
						imgListCounter.get(imgListCounter.keySet().toArray()[imgNumber - 1]) + 1);
				return imgListCounter.keySet().toArray()[imgNumber - 1].toString();
			}
		}
	}

	/**
	 * This is the method that will be called every time we press on an image. If an
	 * image has been discovered, this will be avoided.
	 * 
	 * @param selectedImageView Pressed image.
	 */
	private void updateGame(ImageView selectedImageView) {
		if (hasToResetLastImages) {
			setDefaultImg(lastSelectedImgs.get(0));
			setDefaultImg(lastSelectedImgs.get(1));

			lastSelectedImgs = null;
			hasToResetLastImages = false;
		}

		/**
		 * This "if" check the next:
		 * 
		 * 1. We selected an image before.
		 * 
		 * 2. The current selected image its different than the last one selected.
		 * 
		 * 3. Checks if the current selected image has the default image on the user
		 * view.
		 */
		if (lastSelectedImgs != null && lastSelectedImgs != selectedImageView
				&& selectedImageView.getImage().getUrl().endsWith(defaultImg)) {
			userTotalClicks++;

			selectedImageView
					.setImage(new Image(new File(imgFolder + gridChilds.get(selectedImageView)).getAbsolutePath()));

			if (new File(lastSelectedImgs.get(0).getImage().getUrl()).getAbsolutePath()
					.endsWith(gridChilds.get(selectedImageView))) {
				lastSelectedImgs = null;
				unfoundedImagesCouples--;
			} else {
				lastSelectedImgs.add(selectedImageView);
				hasToResetLastImages = true;
			}
		} else if (lastSelectedImgs == null && selectedImageView.getImage().getUrl().endsWith(defaultImg)) {
			userTotalClicks++;
			selectedImageView
					.setImage(new Image(new File(imgFolder + gridChilds.get(selectedImageView)).getAbsolutePath()));
			lastSelectedImgs = new ArrayList<>();
			lastSelectedImgs.add(selectedImageView);
		}
		
		updateUserScore();
		
		if (unfoundedImagesCouples == 0) {
			createAndShowCustomAlert(Alert.AlertType.CONFIRMATION, CONFIRMATION_TITLE, CONFIRMATION_TEXT);
		}
	}

	/**
	 * Simple method to update the user score with a little algorithm.
	 */
	private void updateUserScore() {
		int score = Math.max(((SCORE_MULTIPLIER * TOTAL_ROWS * TOTAL_COLUMNS) - userTotalClicks), 0);
		tf_points.setText(Integer.toString(score));
	}

	/**
	 * Here comes the reload game part:
	 */
	public void reloadGame() {
		unfoundedImagesCouples = TOTAL_ROWS * TOTAL_COLUMNS / 2;
		userTotalClicks = 0;
		
		updateUserScore();
		
		setDefaultImgs();

		resetImgCounter();
		
		getRandomImages();

		createAndShowCustomAlert(Alert.AlertType.INFORMATION, "Información", "Juego reiniciado");
	}

	
	/**
	 * This method reset the counter for every image.
	 * 
	 * The counter is used to know if we have put a max of 2 same images per game.
	 */
	private void resetImgCounter() {
		for (Map.Entry<String, Integer> entry : imgListCounter.entrySet()) {
			entry.setValue(0);
		}
	}
}
