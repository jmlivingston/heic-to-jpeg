package com.example;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;
import org.im4java.core.Info;
import org.im4java.core.Stream2BufferedImage;
import org.im4java.process.Pipe;

public class Im4JavaHelper {
    // Converts an HEIC file into a JPEG file
    public static void heicToJpegFileByFilePath(String sourceImagePath, String targetImagePath, int width)
            throws InterruptedException, IOException, IM4JavaException {
        ConvertCmd convertCmd = new ConvertCmd();
        // cmd.setSearchPath(IMAGE_MAGICK_PATH); // include if binary in project
        Info info = new Info(sourceImagePath, true);
        int currentWidth = info.getImageWidth();
        IMOperation imOperation = new IMOperation();
        imOperation.addImage(sourceImagePath);
        if (currentWidth > width) {
            imOperation.resize(width);
        }
        imOperation.addImage(targetImagePath);
        convertCmd.run(imOperation);
    }

    // Converts a base64 encoded HEIC image string into a JPEG file
    public static void heicToJpegFileByEncodedString(String base64EncodedFileString, String targetImagePath, int width)
            throws InterruptedException, IOException, IM4JavaException {
        IMOperation imOperation = new IMOperation();
        ConvertCmd convertCmd = new ConvertCmd();
        // cmd.setSearchPath(IMAGE_MAGICK_PATH); // include if binary in project
        InputStream inputStream = ImageUtility.convertBase64ToInputStream(base64EncodedFileString);
        imOperation.addImage("-");
        imOperation.addImage("jpg:-");
        FileOutputStream fileOutputStream = new FileOutputStream(targetImagePath);
        Pipe pipeIn = new Pipe(inputStream, null);
        Pipe pipeOut = new Pipe(null, fileOutputStream);
        convertCmd.setInputProvider(pipeIn);
        convertCmd.setOutputConsumer(pipeOut);
        convertCmd.run(imOperation);
        inputStream.close();
        fileOutputStream.close();
    }

    // Converts a base64 encoded HEIC image string into a base64 JPEG image string
    public static String heicToJpegStringByEncodedString(String base64EncodedFileString, String type, int width)
            throws InterruptedException, IOException, IM4JavaException {
        IMOperation imOperation = new IMOperation();
        ConvertCmd convertCmd = new ConvertCmd();
        // cmd.setSearchPath(IMAGE_MAGICK_PATH); // include if binary in project
        InputStream inputStream = ImageUtility.convertBase64ToInputStream(base64EncodedFileString);
        imOperation.addImage("-");
        imOperation.addImage(type + ":-");
        Pipe pipeIn = new Pipe(inputStream, null);
        convertCmd.setInputProvider(pipeIn);
        Stream2BufferedImage s2b = new Stream2BufferedImage();
        convertCmd.setOutputConsumer(s2b);
        convertCmd.run(imOperation);
        inputStream.close();
        BufferedImage bufferedImage = s2b.getImage();
        String imageString = ImageUtility.convertBufferedImageToString(bufferedImage, type);
        return imageString;
    }
}