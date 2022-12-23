package agh.ics.oop.Gui.Input;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Dropdown {
    private final ObservableList<String> options;
    private final String titleText;
    private ComboBox<String> input;

    public Dropdown(String[] options, String titleText) {
        this.options = FXCollections.observableArrayList(options);
        this.titleText = titleText;
    }

    public VBox generateInput() {
        Text title = new Text(titleText);
        title.setStyle("-fx-font-size: 12");
        input = new ComboBox<>(options);


        VBox inputContainer = new VBox(title, input);
        inputContainer.setSpacing(5);
        inputContainer.setStyle("-fx-padding: 5");
        return inputContainer;
    }

    public String getValue() {
        return input.getValue();
    }

    public void setValue(String value) {
        input.setValue(value);
    }
}
