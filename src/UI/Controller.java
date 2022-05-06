package UI;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.util.Random;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import Model.TypingTest;

public class Controller {
    private TypingTest test;
    private boolean canStart = true;
    @FXML
    ToggleGroup toggleGroup;
    @FXML
    Label wpmLabel;
    @FXML
    Label accLabel;
    @FXML
    TextField userText;
    @FXML
    Text textToType;

    public Controller() {
    }

    @FXML
    void onRadioButton() {
        RadioButton selectedRadioButton = (RadioButton)this.toggleGroup.getSelectedToggle();
        String selectedID = selectedRadioButton.getId();
        switch(selectedID) {
            case "thirty":
                this.test = new TypingTest(30);
                break;
            case "lng":
                this.test = new TypingTest("long");
                break;
            case "med":
                this.test = new TypingTest("medium");
                break;
            case "ran":
                Random rand = new Random();
                this.test = new TypingTest(rand.nextInt(15) + 5);
                break;
            case "shrt":
                this.test = new TypingTest("short");
                break;
            case "sixty":
                this.test = new TypingTest(60);
        }

    }

    @FXML
    void onStartButton() {
        String rawText = String.join(" ", this.test.getWords());
        this.textToType.setText(rawText);
    }

    @FXML
    void onEnterPressed() {
        this.test.setEndTime((double)System.currentTimeMillis());
        this.test.setRawInput(this.userText.getText());
        this.test.updateStats();
        String wpm = "WPM: " + this.test.getGrossWPM();
        String acc = "Accuracy: " + this.test.getAccuracy();
        this.wpmLabel.setText(wpm);
        this.accLabel.setText(acc);
        this.canStart = true;
        this.userText.setText("");
    }

    @FXML
    void handleKeyPress(KeyEvent event) {
        if (this.canStart && this.test != null) {
            this.test.setStartTime((double)System.currentTimeMillis());
            this.canStart = false;
        }

        if (event.getCode().equals(KeyCode.ENTER)) {
            this.onEnterPressed();
        }

    }
}
