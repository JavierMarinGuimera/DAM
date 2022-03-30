package application;

import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class MainController implements Initializable {

	@FXML
	private RadioButton rb_hombre;
	@FXML
	private RadioButton rb_mujer;
	@FXML
	private Slider slider_altura;
	@FXML
	private Label lb_altura;
	@FXML
	private DatePicker fecha_nacimiento;
	@FXML
	private TextField tf_peso;
	@FXML
	private TextField tf_tasa;
	@FXML
	private Button bt_calcular;

	private Alert alert = new Alert(Alert.AlertType.WARNING);
	private LocalDate data = LocalDate.now();

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Radiobuttons:
		this.rb_hombre.setSelected(false);
		this.rb_mujer.setSelected(true);

		// Slider side:
		this.slider_altura.setValue(165);
		this.slider_altura.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> slider, Number numberBefore, Number numberAfter) {
				lb_altura.textProperty().setValue(numberAfter.intValue() + " cm");
			}
		});
		this.lb_altura.setText(165 + " cm");

		// Date input:
		this.fecha_nacimiento.setEditable(false);
		this.fecha_nacimiento.setValue(data.plusYears(-20));
		
		// Weight input:
		this.tf_peso.setText("60");
		this.tf_peso.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					tf_peso.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});

		// Result input:
		this.tf_tasa.setText("0");
		this.tf_tasa.setEditable(false);
	}

	public void calcTasa() {
		LocalDate birthDate = this.fecha_nacimiento.getValue();
		LocalDate currentDate = this.data;

		Period age = Period.between(birthDate, currentDate);
		int weight = Integer.parseInt(this.tf_peso.getText());

		if (weight < 20 || weight > 150) {
			alert.setTitle("Peso incorrecto");
			alert.setContentText("El pes tiene que estar entre los 20 y los 150 kg.");
			alert.showAndWait();
		} else if (age.getYears() < 10 || age.getYears() > 100) {
			alert.setTitle("Edad incorrecta");
			alert.setContentText("L'edat tiene que estar entre los 10 y los 100 años.");
			alert.showAndWait();
		} else {
			int rate = 0;
			int height = (int) this.slider_altura.getValue();
			if (this.rb_mujer.isSelected()) {
				rate = (int) Math.round((10 * weight) + (6.25 * height) - (5 * age.getYears()) - 161);
			} else {
				rate = (int) Math.round((10 * weight) + (6.25 * height) - (5 * age.getYears()) + 5);
			}
			this.tf_tasa.setText(String.valueOf(rate));
		}
	}

}
