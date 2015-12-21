package com.emekhanikov.image;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;

import javax.swing.*;

public class MainWindow {
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
        //-- 2. Read the video stream  
        //Mat webcam_image=new Mat();
        Mat image = Imgcodecs.imread("D:/prj/opencv3/module1/resources/lena.png");
//         if( !image.empty() )
//                {
                    frame.setSize(image.width(), image.height());
                    //-- 3. Apply the classifier to the captured image  
                    image = my_processor.detect(image);

                    //-- 4. Display the image  
                    my_panel.MatToBufferedImage(image); // We could look at the error...
                    my_panel.repaint();
//                }



        return;
    }
}