/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memory.game;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

/**
 *
 * @author joaoLetra
 */
public class MemoryFXMLController implements Initializable {

    @FXML
    private Button startBtn;
    @FXML
    private GridPane tableGame;
    private final Button[][] btn = new Button[4][4];
    int table[][] = new int[4][4];
    int val = 0;
    int val2 = 0;
    int play = 0;
    int ai, aj, bi, bj;

    public void shuffle(int[][] a) {
        Random random = new Random();

        for (int i = a.length - 1; i > 0; i--) {
            for (int j = a[i].length - 1; j > 0; j--) {
                int m = random.nextInt(i + 1);
                int n = random.nextInt(j + 1);

                int temp = a[i][j];
                a[i][j] = a[m][n];
                a[m][n] = temp;
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tableGame.setVisible(false);
        int X = 1;
        for (int i = 0; i < 4; i++) { //loop que cria os botões na grid pane
            for (int j = 0; j < 4; j++) {
                table[i][j] = X;
                X++;
                if (X == 9) {
                    X = 1;
                }
            }
        }
    }

    @FXML
    private void handleStart(ActionEvent event) {
        
        val = 0;
        val2 = 0;
        play = 0;

        tableGame.setVisible(true);
        shuffle(table);

        int X = 1;

        for (int i = 0; i < 4; i++) { //loop que cria os botões na grid pane
            for (int j = 0; j < 4; j++) {
                btn[i][j] = new Button();//cria os botões
                btn[i][j].setPadding(Insets.EMPTY);
                btn[i][j].setMinSize(95, 95);
                btn[i][j].setMaxSize(95, 95);
                btn[i][j].setVisible(true);
                btn[i][j].setStyle("-fx-background-image: url('/testing/img0" + ".png')");

                btn[i][j].setOnAction(this::handleBtn);
            }
        }

        for (int i = 0; i < 4; i++) { //loop que cria os botões na grid pane
            for (int j = 0; j < 4; j++) {
                tableGame.add(btn[i][j], j, i);
            }
        }
    }

    @FXML
    private void handleBtn(ActionEvent event) {
        int X = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (event.getSource() == btn[i][j]) {
                    X = table[i][j];
                    String imagem = String.valueOf(X);
                    btn[i][j].setStyle("-fx-background-image: url('/testing/img" + (imagem) + ".png')");

                    switch (play) {
                        case 0:
                            btn[i][j].setVisible(true);
                            val = X;
                            ai = i;
                            aj = j;

                            play = 2;
                            break;

                        case 2:
                            btn[i][j].setVisible(true);
                            val2 = X;
                            bi = i;
                            bj = j;

                            play = 1;
                            break;

                        case 1:
                            if (val == val2) {
                                btn[i][j].setVisible(true);
                                btn[ai][aj].setOnAction(null);
                                btn[bi][bj].setOnAction(null);
                                val = X;
                                ai = i;
                                aj = j;
                                play = 2;
                            } else {
                                btn[bi][bj].setStyle("-fx-background-image: url('/testing/img0" + ".png')");
                                btn[ai][aj].setStyle("-fx-background-image: url('/testing/img0" + ".png')");
                                ai = i;
                                aj = j;
                                val = X;
                                val2 = 0;
                                play = 2;

                            }
                            break;
                    }
                }
            }
        }
    }
}
