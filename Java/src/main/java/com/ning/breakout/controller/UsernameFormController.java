package com.ning.breakout.controller;

import com.ning.breakout.StartGame;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.*;
import java.util.Objects;

/**
 * The controller class of the username form, extends from the abstract class
 * NormalSceneController and defines the event handlers of the username form.
 *
 * @see com.ning.breakout.controller.NormalSceneController
 *
 * @author Ning ZHU
 */
public class UsernameFormController extends NormalSceneController {

    /**
     * The text field used to get the user input
     */
    @FXML
    private TextField nameInput;


    private TextField getNameInput() {
        return this.nameInput;
    }


    /**
     * Cancels upload and switches back to the game end menu.
     * <p>
     * By clicking the back arrow, the upload will be canceled and the root of
     * the scene will be set to the game end menu (switch to the game end menu).
     *
     * @see StartGame#switchToGameEndMenu()
     */
    @FXML
    public void cancelUpload() {
        getStartGame().switchToGameEndMenu();
    }

    /**
     * Sees the ranking list.
     * <p>
     * By clicking the next arrow, the record (score + name) will be saved into
     * the historyScore file and then the root of the scene will be set to the
     * ranking list (switch to the ranking list).
     *
     * @see StartGame#switchToRankingList()
     */
    @FXML
    public void seeRankingList() {
        //The name will be set to "UNKNOWN" if user enters nothing
        String name = Objects.equals(getNameInput().getText(), "") ? "UNKNOWN"
                          : getNameInput().getText();

        writeRecord(getStartGame().getGameBoard().getManager().getGameScore() +
                        "," + name);
        getStartGame().switchToRankingList();
    }

    /**
     * Writes the new record at the end of the historyScore file.
     */
    private void writeRecord(String record) {
        File file = new File(
            "src/main/resources/com/ning/breakout/historyScore.csv");
        BufferedWriter bw = null;
        try {
            FileOutputStream fos = new FileOutputStream(file, true);
            bw = new BufferedWriter(new OutputStreamWriter(fos));
            bw.write(record);
            bw.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
