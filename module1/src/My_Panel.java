/*  
 * Captures the camera stream with OpenCV  
 * Search for the faces  
 * Display a circle around the faces using Java
 */
import org.opencv.core.*;
import org.opencv.core.Point;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
class My_Panel extends JPanel{
    private static final long serialVersionUID = 1L;
    private BufferedImage image;
    // Create a constructor method  


    public My_Panel(){
        super();
    }
    /**
     * Converts/writes a Mat into a BufferedImage.  
     *
     * @param matBGR Mat of type CV_8UC3 or CV_8UC1
     * @return BufferedImage of type TYPE_3BYTE_BGR or TYPE_BYTE_GRAY  
     */
    public boolean MatToBufferedImage(Mat matBGR){
        long startTime = System.nanoTime();
        int width = matBGR.width(), height = matBGR.height(), channels = matBGR.channels() ;
        byte[] sourcePixels = new byte[width * height * channels];
        matBGR.get(0, 0, sourcePixels);
        // create new image and get reference to backing data  
        image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(sourcePixels, 0, targetPixels, 0, sourcePixels.length);
        long endTime = System.nanoTime();
        System.out.println(String.format("Elapsed time: %.2f ms", (float)(endTime - startTime)/1000000));
        return true;
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if (this.image==null) return;
        g.drawImage(this.image,10,10,this.image.getWidth(),this.image.getHeight(), null);
        //g.drawString("This is my custom Panel!",10,20);  
    }
}


class processor {
    private CascadeClassifier face_cascade;
    // Create a constructor method  
    public processor(){
        face_cascade=new CascadeClassifier("D:/prj/opencv3/module1/resources/lbpcascade_frontalface.xml");
        if(face_cascade.empty())
        {
            System.out.println("--(!)Error loading A\n");
            return;
        }
        else
        {
            System.out.println("Face classifier loooaaaaaded up");
        }
    }
    public Mat detect(Mat inputframe){
        long startTime = System.nanoTime();
        Mat mRgba=new Mat();
        Mat mGrey=new Mat();
        MatOfRect faces = new MatOfRect();
        inputframe.copyTo(mRgba);
        inputframe.copyTo(mGrey);
        Imgproc.cvtColor( mRgba, mGrey, Imgproc.COLOR_BGR2GRAY);
        Imgproc.equalizeHist( mGrey, mGrey );
        face_cascade.detectMultiScale(mGrey, faces);
        long endTime = System.nanoTime();
        System.out.println(String.format("Detect time: %.2f ms", (float)(endTime - startTime)/1000000));
        System.out.println(String.format("Detected %s faces", faces.toArray().length));

        for (Rect rect : faces.toArray()) {
            Imgproc.rectangle(mRgba, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0));
        }
        return mRgba;
    }
}