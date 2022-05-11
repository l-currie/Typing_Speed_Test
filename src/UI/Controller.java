package UI;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import Model.WordSet;
import Persistence.JsonReader;
import Persistence.JsonWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import Model.TypingTest;

import javax.swing.*;

public class Controller {
    private TypingTest test;
    private boolean canStart = true;
    private boolean isCustomSelected = false;
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
    @FXML
    TextField nameText;
    @FXML
    Button saveTextButton;
    @FXML
    ComboBox<String> comboBox;

    public Controller() {
    }

    @FXML
    void onRadioButton() {
        RadioButton selectedRadioButton = (RadioButton)this.toggleGroup.getSelectedToggle();
        if(selectedRadioButton == null) {
            return;
        }
        String selectedID = selectedRadioButton.getId();
        switch(selectedID) {
            case "thirty":
                this.test = new TypingTest(30);
                comboBox.setVisible(false);
                break;
            case "lng":
                this.test = new TypingTest("long");
                comboBox.setVisible(false);
                break;
            case "med":
                this.test = new TypingTest("medium");
                comboBox.setVisible(false);
                break;
            case "ran":
                Random rand = new Random();
                this.test = new TypingTest(rand.nextInt(15) + 5);
                comboBox.setVisible(false);
                break;
            case "shrt":
                this.test = new TypingTest("short");
                comboBox.setVisible(false);
                break;
            case "sixty":
                this.test = new TypingTest(60);
                comboBox.setVisible(false);
                break;
            case "custom":
                comboBox.setVisible(true);
                textToType.setText("To add a custom test, enter words and a name and save! \n To load one, select one and press start!");

        }

    }

    @FXML
    void onStartButton()  throws InterruptedException{
        onRadioButton();
        String rawText = String.join(" ", this.test.getWords());
        TimeUnit.SECONDS.sleep(1);
        this.userText.requestFocus();

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

    @FXML
    void saveTextToFile(){
        try{
            String setName = nameText.getText();
            System.out.println(setName);
            String rawText = userText.getText();
            System.out.println(rawText);
            String destination = "./data/Custom/" + setName + ".json";
            WordSet set = new WordSet(rawText, setName);
            JsonWriter writer = new JsonWriter(destination);

            writer.open();
            writer.write(set);
            writer.close();

        } catch (IOException e) {
            System.out.println("ERROR saving file");
        }

    }

    @FXML
    void onComboBox() {

        String[] customFileNames;
        File file = new File("./data/Custom/");

        customFileNames = file.list();

        ObservableList<String> namesList = FXCollections.observableArrayList();
        comboBox.setItems(namesList);
        assert customFileNames != null;
        for (String name : customFileNames) {
            String trimmedName = name.substring(0, name.length()-5);
            System.out.println(trimmedName);
            comboBox.getItems().add(trimmedName);
        }

    }

    @FXML
    void onComboAction() throws IOException {
        try {
        String name = comboBox.getValue();
        String fileSrc = "./data/Custom/" + name + ".json";
        JsonReader reader = new JsonReader(fileSrc);
        WordSet ws = reader.read();
        if(test == null) {
            test = new TypingTest(20);
        }
        test.setWordSet(ws);
    } catch (NoSuchFileException exception) {
            System.out.println("error loading file " );}
    }

}
