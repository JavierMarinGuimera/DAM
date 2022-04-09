package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * MainController Exam.
 * 
 * @author Javier Marín Guimerà
 */
public class MainController implements Initializable {

	/**
	 * App IDs.
	 */
	@FXML
	private GridPane gpTable;
	@FXML
	private ChoiceBox<Integer> filas;
	@FXML
	private ChoiceBox<Integer> columnas;
	@FXML
	private Button resetButton;

	private Random random;

	private ArrayList<ArrayList<TextField>> gridChilds = new ArrayList<>();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO - Realizar lista observable para los choice box.
		random = new Random();

		filas.setValue(2);
		columnas.setValue(3);

		filas.onActionProperty();

		resetButton.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {
				reloadGame();
			}
		});
		;

		filas.getSelectionModel().selectedIndexProperty()
				.addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
					reloadGame();
				});

		columnas.getSelectionModel().selectedIndexProperty()
				.addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
					reloadGame();
				});

		getGridChilds();

		putRandomNumbers();
	}

	private void getGridChilds() {
		for (Node child : gpTable.getChildren()) {
			if (child instanceof TextField) {
				Integer row = (GridPane.getRowIndex(child) == null ? 0 : GridPane.getRowIndex(child));
				Integer column = (GridPane.getColumnIndex(child) == null ? 0 : GridPane.getColumnIndex(child));

				if (gridChilds.size() <= row) {
					gridChilds.add(new ArrayList<>());
				}

				gridChilds.get(row).add((TextField) child);

				if (isResultTextField(row, column)) {
					((TextField) child).textProperty().addListener(new ChangeListener<String>() {
						@Override
						public void changed(ObservableValue<? extends String> observable, String oldValue,
								String newValue) {
							if (checkResults(row, column)) {
								createAndShowCustomAlert(Alert.AlertType.INFORMATION, "Información",
										"Has acertado la suma de los números!");
							}
						}
					});
				}
			}
		}
	}

	private void putRandomNumbers() {
		for (Node child : gpTable.getChildren()) {
			if (child instanceof TextField) {
				Integer row = (GridPane.getRowIndex(child) == null ? 0 : GridPane.getRowIndex(child));
				Integer column = (GridPane.getColumnIndex(child) == null ? 0 : GridPane.getColumnIndex(child));

				if (isActive(row, column)) {
					((TextField) child).setText(Integer.toString(random.nextInt(10)));
				}
			}
		}
	}

	private boolean isActive(Integer row, Integer column) {
		return row < filas.getValue() && column < columnas.getValue();
	}

	private Boolean checkResults(Integer row, Integer column) {
		if (column == gridChilds.get(0).size() - 1) {
			int rowSum = 0;
			for (int i = 0; i < gridChilds.get(row).size() - 1; i++) {
				int currentNumber = 0;
				try {
					currentNumber = Integer.parseInt(gridChilds.get(row).get(i).getText());
					rowSum += currentNumber;
				} catch (Exception e) {
				}
			}

			try {
				if (!"".equals(gridChilds.get(row).get(gridChilds.get(row).size() - 1).getText()) && rowSum != 0) {
					return Integer
							.parseInt(gridChilds.get(row).get(gridChilds.get(row).size() - 1).getText()) == rowSum;
				}

				return false;
			} catch (Exception e) {
				createAndShowCustomAlert(Alert.AlertType.ERROR, "ERROR", "¡Solo puedes introducir números!");
			}

		} else {
			int columnSum = 0;
			for (int i = 0; i < gridChilds.size() - 1; i++) {
				int currentNumber = 0;
				try {
					currentNumber = Integer.parseInt(gridChilds.get(i).get(column).getText());
					columnSum += currentNumber;
				} catch (Exception e) {
				}
			}

			try {
				if (!"".equals(gridChilds.get(gridChilds.size() - 1).get(column).getText()) && columnSum != 0) {
					return Integer.parseInt(gridChilds.get(gridChilds.size() - 1).get(column).getText()) == columnSum;
				}

				return false;
			} catch (Exception e) {
				createAndShowCustomAlert(Alert.AlertType.ERROR, "ERROR", "¡Solo puedes introducir números!");
			}
		}

		return false;
	}

	private boolean isResultTextField(Integer row, Integer column) {
		return row == 4 || column == 4;
	}

	private void createAndShowCustomAlert(AlertType type, String title, String text) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setContentText(text);
		Optional<ButtonType> option = alert.showAndWait();

		if (type == Alert.AlertType.CONFIRMATION) {
			if (option.get() == ButtonType.OK) {
//				reloadGame();
			}
		}
	}

	private void reloadGame() {
		for (ArrayList<TextField> arrayList : gridChilds) {
			for (TextField textField : arrayList) {
				textField.setText("");
			}
		}

		putRandomNumbers();
	}
}
