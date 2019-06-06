/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sound;

import java.io.File;
import java.util.UUID;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;
import javax.swing.JOptionPane;

public class Recorder {

    TargetDataLine line;
    AudioFormat a = new AudioFormat(16000, 8, 2, true, true);

    public void start() {
        try {
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, a);
            line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(a);
            line.start();
            AudioInputStream ais = new AudioInputStream(line);
            AudioSystem.write(ais, AudioFileFormat.Type.WAVE,
                    new File("1minute/Main Recordinf of 1 minute.mp3"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
    }

    public Recorder() {
        Thread stopper = new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(60000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                line.stop();
                line.close();
                JOptionPane.showMessageDialog(null, "Finished.");
            }
        });
        stopper.start();
        start();
    }
}
