package com.example;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;
import org.im4java.core.Stream2BufferedImage;
import org.im4java.process.Pipe;

public class Im4JavaHelper {
    // Converts an HEIC file into a JPEG file
    public static void convertImageFileToImageFile(String sourceImagePath, String targetImagePath, int width)
            throws InterruptedException, IOException, IM4JavaException {
        ConvertCmd convertCmd = new ConvertCmd();
        // cmd.setSearchPath(IMAGE_MAGICK_PATH); // include if binary in project
        IMOperation imOperation = new IMOperation();
        imOperation.addImage(sourceImagePath);
        imOperation.resize(width);
        imOperation.addImage(targetImagePath);
        convertCmd.run(imOperation);
    }

    // Converts a base64 encoded image string into an image file
    public static void convertImageFileToEncodedString(String base64EncodedFileString, String targetImagePath, String type, int width)
            throws InterruptedException, IOException, IM4JavaException {
        IMOperation imOperation = new IMOperation();
        ConvertCmd convertCmd = new ConvertCmd();
        // cmd.setSearchPath(IMAGE_MAGICK_PATH); // include if binary in project
        InputStream inputStream = ImageUtility.convertBase64ToInputStream(base64EncodedFileString);
        imOperation.addImage("-");
        imOperation.resize(width);
        imOperation.addImage(type + ":-");
        FileOutputStream fileOutputStream = new FileOutputStream(targetImagePath);
        Pipe pipeIn = new Pipe(inputStream, null);
        Pipe pipeOut = new Pipe(null, fileOutputStream);
        convertCmd.setInputProvider(pipeIn);
        convertCmd.setOutputConsumer(pipeOut);
        convertCmd.run(imOperation);
        inputStream.close();
        fileOutputStream.close();
    }

    // Converts a base64 encoded image string into a base64 encoded image string
    public static String convertImageEncodedStringToEncodedString(String base64EncodedFileString, String type, int width)
            throws InterruptedException, IOException, IM4JavaException {
        IMOperation imOperation = new IMOperation();
        ConvertCmd convertCmd = new ConvertCmd();
        // cmd.setSearchPath(IMAGE_MAGICK_PATH); // include if binary in project
        InputStream inputStream = ImageUtility.convertBase64ToInputStream(base64EncodedFileString);
        imOperation.addImage("-");
        imOperation.resize(width);
        imOperation.addImage(type + ":-");
        Pipe pipeIn = new Pipe(inputStream, null);
        convertCmd.setInputProvider(pipeIn);
        Stream2BufferedImage stream2BufferedImage = new Stream2BufferedImage();
        convertCmd.setOutputConsumer(stream2BufferedImage);
        convertCmd.run(imOperation);
        inputStream.close();
        BufferedImage bufferedImage = stream2BufferedImage.getImage();
        String imageString = ImageUtility.convertBufferedImageToString(bufferedImage, type);
        return imageString;
    }
}