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
import sample.Model.TypingTest;

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
        byte var4 = -1;
        switch(selectedID.hashCode()) {
            case -874698270:
                if (selectedID.equals("thirty")) {
                    var4 = 0;
                }
                break;
            case 107301:
                if (selectedID.equals("lng")) {
                    var4 = 4;
                }
                break;
            case 107980:
                if (selectedID.equals("med")) {
                    var4 = 3;
                }
                break;
            case 112671:
                if (selectedID.equals("ran")) {
                    var4 = 5;
                }
                break;
            case 3529559:
                if (selectedID.equals("shrt")) {
                    var4 = 2;
                }
                break;
            case 109452007:
                if (selectedID.equals("sixty")) {
                    var4 = 1;
                }
        }

        switch(var4) {
            case 0:
                this.test = new TypingTest(30);
                break;
            case 1:
                this.test = new TypingTest(60);
                break;
            case 2:
                this.test = new TypingTest("short");
                break;
            case 3:
                this.test = new TypingTest("medium");
                break;
            case 4:
                this.test = new TypingTest("long");
                break;
            case 5:
                Random rand = new Random();
                this.test = new TypingTest(rand.nextInt(40) + 10);
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
