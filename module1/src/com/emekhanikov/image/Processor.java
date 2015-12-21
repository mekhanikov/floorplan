package com.emekhanikov.image;

import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

class Processor {

    // Create a constructor method
    public Processor() {

    }

    public Mat detect(Mat inputframe) {
        long startTime = System.nanoTime();
        Mat mRgba = new Mat();
        Mat mGrey = new Mat();
        MatOfRect faces = new MatOfRect();
        inputframe.copyTo(mRgba);
        inputframe.copyTo(mGrey);
        Imgproc.cvtColor(mRgba, mGrey, Imgproc.COLOR_BGR2GRAY);
        Imgproc.equalizeHist(mGrey, mGrey);
        long endTime = System.nanoTime();
        System.out.println(String.format("Detect time: %.2f ms", (float) (endTime - startTime) / 1000000));
        System.out.println(String.format("Detected %s faces", faces.toArray().length));

        for (Rect rect : faces.toArray()) {
            Imgproc.rectangle(mRgba, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0));
        }
        return mRgba;
    }
}