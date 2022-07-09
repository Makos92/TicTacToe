package com.example.tictactoe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Controller {

    private boolean xTurn;

    private Random random = new Random();

    private  ArrayList<Button> buttons;

    private  int i = 0;

    @FXML
    private  Label label;

    @FXML
    private Button start;

    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button button4;
    @FXML
    private Button button5;
    @FXML
    private Button button6;
    @FXML
    private Button button7;
    @FXML
    private Button button8;
    @FXML
    private Button button9;



    @FXML
    private void buttonStartClicked(){
        newGame();
        whoMakeFirstTurn();
        play();

    }

    private void newGame(){

        buttons = new ArrayList<>(Arrays.asList(button1,button2,button3,button4,button5,button6,button7,button8,button9));
        buttons.forEach(button -> {
            button.setDisable(false);
            button.setText("");
            this.i = 0;
        });
    }


    private void whoMakeFirstTurn() {
        if(random.nextInt(2)==0){
            xTurn = true;
            label.setText("Spieler X ist Dran");

        }
        else {
            xTurn = false;
            label.setText("Spieler O ist Dran");

        }
        start.setText("Neu Start");
    }



    private void play() {
        buttons.forEach(button ->
                playerMove(button));

    }

    private void playerMove(Button button){
        button.setOnMouseClicked(MouseEvent -> {
        if (xTurn==true){
            button.setText("X");
            button.setDisable(true);
            xTurn = false;
            label.setText("Spieler O ist Dran");
         }
        else{
            button.setText("O");
            button.setDisable(true);
            xTurn = true;
            label.setText("Spieler X ist Dran");
        }
        anybodyWin();
        });

    }

    private void anybodyWin() {

        for(int i = 0; i < 8; i++){
            String win = switch (i){
                case 0 -> button1.getText() + button2.getText() + button3.getText();
                case 1 -> button4.getText() + button5.getText() + button6.getText();
                case 2 -> button7.getText() + button8.getText() + button9.getText();
                case 3 -> button1.getText() + button4.getText() + button7.getText();
                case 4 -> button2.getText() + button5.getText() + button8.getText();
                case 5 -> button3.getText() + button6.getText() + button9.getText();
                case 6 -> button1.getText() + button5.getText() + button9.getText();
                case 7 -> button3.getText() + button5.getText() + button7.getText();
                default -> null;
            };

            if(win.equals("XXX")){
                label.setText("Spieler X hat gewonnen");
                buttons.forEach(button -> {
                    button.setDisable(true);
                });
            }
            if(win.equals("OOO")){
                label.setText("Spieler O hat gewonnen");
                buttons.forEach(button -> {
                    button.setDisable(true);
                });
            }

        }
        this.i++;
        if(this.i==9 ){
            label.setText("Unentschieden");
            buttons.forEach(button -> {
                button.setDisable(true);
            });
        }
    }


}