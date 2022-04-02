package application;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class MainController implements Initializable {
	private static final String IMG = "img";
	private static final String JPG = ".jpg";

	@FXML
	private TextField tf_points;

	@FXML
	private ImageView iv_button;

	@FXML
	private GridPane gpTable;

	// Local variables:
	private String imgFolder = "resources/img/";
	private String defaultImg = imgFolder + "img_off.jpg";

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
	private List<ImageView>lastSelectedImgs = null;
	private boolean hasToResetLastImages = false;
	private int userTotalClicks = 0;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		this.iv_button.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {
				reloadGame();
			}

		});

		for (Node child : gpTable.getChildren()) {
			if (child instanceof ImageView) {
				gridChilds.put((ImageView) child, getRandomImg());

				ImageView childAsImageView = (ImageView) child;

				childAsImageView.setOnMouseClicked(new EventHandler<Event>() {
					@Override
					public void handle(Event arg0) {
						updateGame(childAsImageView);
					}
				});
				;
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
		alert.show();
	}

	/**
	 * This is the method that will be called every time we press on an image. If an
	 * image has been discovered, this will be avoided.
	 * 
	 * @param selectedImageView Pressed image.
	 */
	private void updateGame(ImageView selectedImageView) {
		// TODO - Hacer el cálculo de la puntuación total.
		userTotalClicks++;
		
		if (hasToResetLastImages) {
			setDefaultImg(lastSelectedImgs.get(0));
			setDefaultImg(lastSelectedImgs.get(1));
			
			lastSelectedImgs = null;
			hasToResetLastImages = false;
		}
		
		// TODO - Comentario aquí sobre qué hace el condicional.
		if (lastSelectedImgs != null && lastSelectedImgs != selectedImageView && selectedImageView.getImage().getUrl().endsWith(defaultImg)) {
			selectedImageView.setImage(new Image(new File(imgFolder + gridChilds.get(selectedImageView)).getAbsolutePath()));
			
			if (new File(lastSelectedImgs.get(0).getImage().getUrl()).getAbsolutePath().endsWith(gridChilds.get(selectedImageView))) {
				lastSelectedImgs = null;
				System.out.println("primero");
			} else {
				lastSelectedImgs.add(selectedImageView);
				hasToResetLastImages = true;
				System.out.println("segundo");
			}
		} else if (lastSelectedImgs == null) {
			selectedImageView.setImage(new Image(new File(imgFolder + gridChilds.get(selectedImageView)).getAbsolutePath()));
			lastSelectedImgs = new ArrayList<>();
			lastSelectedImgs.add(selectedImageView);
			System.out.println("tercero");
		}
	}

	/**
	 * Here comes the reload game part:
	 */
	public void reloadGame() {
		setDefaultImgs();

		resetImgCounter();

		createAndShowCustomAlert(Alert.AlertType.INFORMATION, "Información", "Juego reiniciado");
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
		imageView.setImage(new Image(new File(defaultImg).getAbsolutePath()));
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
