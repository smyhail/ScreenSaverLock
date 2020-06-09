package com.sub;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.util.Duration;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ResourceBundle;
import static javafx.animation.Animation.Status.RUNNING;

public class Controller  implements Initializable {
    public CheckBox cbShift;
    public CheckBox cbCLock;
    public Label infoLabel;
    public  Boolean statusCL;
    public  Boolean statusNL;
    public  Timeline fiveSecondsWonder;
    public Boolean dodo;
    public ToggleButton tbDoRun;
    public CheckBox cbNLock;
    public Slider sliderTime;
    public Label infoLabel2;
    public Label infoLabel3;

    public void initialize(URL location, ResourceBundle resources) {
        tbDoRun.setText( "Ready" );
        tbDoRun.setStyle("-fx-background-color: #00ff00");
        dodo = false;
        infoLabel2.setText("Frequency: " + Integer.toString( (int) sliderTime.getValue() ) + " s");

        sliderTime.valueProperty().addListener((observable, oldValue, newValue) -> {
            infoLabel2.setText("Frequency: " + Integer.toString(newValue.intValue()) + " s");
        });
    }

    public void doRun(ActionEvent actionEvent) throws AWTException {
        if(cbCLock.isSelected() || cbNLock.isSelected() || cbShift.isSelected()) {

            if (tbDoRun.isSelected()) {
                sliderTime.setDisable( true );
                tbDoRun.setText( "Working" );
                tbDoRun.setStyle( "-fx-background-color: #ff0000" );
                fiveSecondsWonder = new Timeline( new KeyFrame( Duration.seconds( (int)sliderTime.getValue() ), new EventHandler<ActionEvent>() {

                    public void handle(ActionEvent event) {
                        /*** SHIFT */
                        if (cbShift.isSelected()) {
                            try {
                                Robot robot1 = new Robot();
                                robot1.keyPress( KeyEvent.VK_SHIFT );
                                robot1.keyRelease( KeyEvent.VK_SHIFT );
                            } catch (AWTException e) {
                                e.printStackTrace();
                            }
                            System.out.println( "Shift" );
                        }
                        /** CAPS LOCK*/
                        if (cbCLock.isSelected()) {
                            statusCL = Toolkit.getDefaultToolkit().getLockingKeyState( KeyEvent.VK_CAPS_LOCK );
                            if (statusCL == false) {
                                Toolkit.getDefaultToolkit().setLockingKeyState( KeyEvent.VK_CAPS_LOCK, true );

                            } else {
                                Toolkit.getDefaultToolkit().setLockingKeyState( KeyEvent.VK_CAPS_LOCK, false );
                            }
                            System.out.println( "Caps Lock" );
                        }
                        /** NUM  LOCK*/
                        if (cbNLock.isSelected()) {
                            statusNL = Toolkit.getDefaultToolkit().getLockingKeyState( KeyEvent.VK_NUM_LOCK );
                            if (statusNL == false) {
                                Toolkit.getDefaultToolkit().setLockingKeyState( KeyEvent.VK_NUM_LOCK, true );
                            } else {
                                Toolkit.getDefaultToolkit().setLockingKeyState( KeyEvent.VK_NUM_LOCK, false );
                            }
                            System.out.println( "NumLock" );
                        }
                    }
                }, new javafx.animation.KeyValue[]{} ) );
                fiveSecondsWonder.setCycleCount( Timeline.INDEFINITE );
                fiveSecondsWonder.play();
                System.out.println( "selected " );
            } else {
                tbDoRun.setText( "Ready" );
                tbDoRun.setStyle( "-fx-background-color: #00ff00" );

                System.out.println(fiveSecondsWonder.getStatus());
                if (fiveSecondsWonder.getStatus() ==  RUNNING){
                    fiveSecondsWonder.stop();
                }
                sliderTime.setDisable( false );
            }
        }else{
            JOptionPane.showMessageDialog(null, "No key selected for simulation");
        }
    }
}

