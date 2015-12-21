package com.emekhanikov.image;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

import javax.swing.*;

public class MainFloorWindow {
    public static void main(String arg[]){
        // Load the native library.
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        String window_name = "Capture - Face detection";
        JFrame frame = new JFrame(window_name);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,400);
        Processor my_processor=new Processor();
        MyPanel my_panel = new MyPanel();
        frame.setContentPane(my_panel);
        frame.setVisible(true);

        Mat image = Imgcodecs.imread("D:/prj/opencv3/module1/resources/floor.jpg");
        Mat res = new Mat();
        Imgproc.threshold(image, res, 127, 255, Imgproc.THRESH_BINARY);
        frame.setSize(image.width(), image.height());

        my_panel.MatToBufferedImage(res);
        my_panel.repaint();
    }
}