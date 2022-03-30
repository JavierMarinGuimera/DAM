package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class MainController implements Initializable {

	@FXML
	private TextField tf_points;

	@FXML
	private ImageView iv_button;

	// Local variables:
	private String imgFolder = "../../resources/img/";
	private List<String> imgList= Arrays.asList("", "", "", "", "", "", "", "", "", "", "");

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
//		// Radiobuttons:
//		this.rb_hombre.setSelected(false);
//		this.rb_mujer.setSelected(true);
//
//		// Slider side:
//		this.slider_altura.setValue(165);
//		this.slider_altura.valueProperty().addListener(new ChangeListener<Number>() {
//			@Override
//			public void changed(ObservableValue<? extends Number> slider, Number numberBefore, Number numberAfter) {
//				lb_altura.textProperty().setValue(numberAfter.intValue() + " cm");
//			}
//		});
//		this.lb_altura.setText(165 + " cm");
//
//		// Date input:
//		this.fecha_nacimiento.setEditable(false);
//		this.fecha_nacimiento.setValue(data.plusYears(-20));
//		
//		// Weight input:
//		this.tf_peso.setText("60");
//		this.tf_peso.textProperty().addListener(new ChangeListener<String>() {
//			@Override
//			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//				if (!newValue.matches("\\d*")) {
//					tf_peso.setText(newValue.replaceAll("[^\\d]", ""));
//				}
//			}
//		});
//
//		// Result input:
//		this.tf_tasa.setText("0");
//		this.tf_tasa.setEditable(false);

		this.iv_button.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				System.out.println("Hola");
			}

		});
	}

	public void reloadGame() {

	}
}
