package agh.ics.oop.Gui.Input;

import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.function.UnaryOperator;

public class IntInput {
    private final String titleText;

    private static final UnaryOperator<TextFormatter.Change> filter = change -> {
        String text = change.getText();

        if (text.matches("[0-9]*")) {
            return change;
        }

        return null;
    };

    private TextField input;
    public IntInput(String titleText) {
        this.titleText = titleText;
    }

    public VBox generateInput() {
        Text title = new Text(titleText);
        title.setStyle("-fx-font-size: 12");
        input = new TextField();
        TextFormatter<String> textFormatter = new TextFormatter<>(filter);
        input.setTextFormatter(textFormatter);

        VBox inputContainer = new VBox(title, input);
        inputContainer.setSpacing(5);
        inputContainer.setStyle("-fx-padding: 5");
        return inputContainer;
    }

    public int getValue() {
        return Integer.parseInt(input.getText());
    }


}
