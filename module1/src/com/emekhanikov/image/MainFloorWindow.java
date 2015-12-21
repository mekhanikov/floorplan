package com.emekhanikov.image;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

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
        Mat canny_output = new Mat();
        Mat hierarchy = new Mat();
        Point offset = new Point(0, 0);
        Imgproc.threshold(image, res, 127, 255, Imgproc.THRESH_BINARY);

        /// Detect edges using canny
        double thresh = 127;
        Imgproc.Canny(res, canny_output, thresh, thresh * 2, 3, true);
        /// Find contours
        List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
        //Imgproc.findContours(canny_output, contours, hierarchy, Imgproc.CV_RETR_TREE, Imgproc.CV_CHAIN_APPROX_SIMPLE, offset);
        Imgproc.findContours(canny_output, contours, hierarchy, 3, 2, offset);

        Imgproc.polylines(res, contours, true, new Scalar(0, 0, 255));

        frame.setSize(image.width(), image.height());

        my_panel.MatToBufferedImage(res);
        my_panel.repaint();
    }
}