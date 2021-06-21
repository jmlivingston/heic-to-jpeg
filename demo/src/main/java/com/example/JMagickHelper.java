package com.example;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

import javax.imageio.ImageIO;

import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;
import org.im4java.core.Info;

import magick.ImageInfo;
import magick.MagickException;
import magick.MagickImage;

// This function simply takes one HEIC file and converts it into a JPEG.

// Note: Requires ImageMagick or GraphicsMagick (a fork) to be installed locally or binary added to library
// https://imagemagick.org/script/download.php
// http://www.graphicsmagick.org/ - This is preferred as it is faster.

// Two potential conversion libraries. Both rely on ImageMagick or GraphicsMagick
// - im4java - http://im4java.sourceforge.net/
// - jmagick - https://github.com/techblue/jmagick - Not working due to this error: https://bit.ly/3gFWs4N

public class JMagickHelper {
    public static void heicToJpegFilePath(String file, int width) throws InterruptedException, IOException, IM4JavaException {
        File baseDirectory = new File(".");
        String projectDirectory = baseDirectory.getAbsolutePath().replace(".", "");
        String fileName = "20MB";
        String sourceImagePath = projectDirectory + "images/source/" + fileName + ".heic";
        String targetImagePath = projectDirectory + "images/target/" + fileName + ".jpg";

        // Cleanup
        File targetFile = new File(targetImagePath);
        targetFile.delete();
        File targetDirectoryPath = new File(projectDirectory + "images/target/");
        if (!targetDirectoryPath.exists()) {
            targetDirectoryPath.mkdir();
        }

        ConvertCmd convertCmd = new ConvertCmd();
        // cmd.setSearchPath(IMAGE_MAGICK_PATH); // Add this if we include library in project
        Info info = new Info(file, true); //sourceImagePath, true);
        int currentWidth = info.getImageWidth();
        IMOperation imOperation = new IMOperation();
        imOperation.addImage(sourceImagePath);
        if (currentWidth > width) {
            imOperation.resize(width);
        }
        imOperation.addImage(targetImagePath);
        convertCmd.run(imOperation);
    }

    public static void heicToJpegByEncodedString(String file, int width) throws InterruptedException, IOException, IM4JavaException {
        File baseDirectory = new File(".");
        String projectDirectory = baseDirectory.getAbsolutePath().replace(".", "");
        String fileName = "20MB";
        String sourceImagePath = projectDirectory + "images/source/" + fileName + ".heic";
        String targetImagePath = projectDirectory + "images/target/" + fileName + ".jpg";

        // Cleanup
        File targetFile = new File(targetImagePath);
        targetFile.delete();
        File targetDirectoryPath = new File(projectDirectory + "images/target/");
        if (!targetDirectoryPath.exists()) {
            targetDirectoryPath.mkdir();
        }

        ConvertCmd convertCmd = new ConvertCmd();
        // cmd.setSearchPath(IMAGE_MAGICK_PATH); // Add this if we include library in project
        Info info = new Info(file, true); //sourceImagePath, true);
        int currentWidth = info.getImageWidth();
        IMOperation imOperation = new IMOperation();
        imOperation.addImage(sourceImagePath);
        if (currentWidth > width) {
            imOperation.resize(width);
        }
        imOperation.addImage(targetImagePath);
        convertCmd.run(imOperation);
    }

    public static void heicToJpegJMagick() throws MagickException {
        File baseDirectory = new File(".");
        String projectDirectory = baseDirectory.getAbsolutePath().replace(".", "");
        String fileName = "20MB";
        String sourceImagePath = projectDirectory + "images/source/" + fileName + ".heic";
        String targetImagePath = projectDirectory + "images/target/" + fileName + ".jpg";

        // Cleanup
        File targetFile = new File(targetImagePath);
        targetFile.delete();
        File targetDirectoryPath = new File(projectDirectory + "images/target/");
        if (!targetDirectoryPath.exists()) {
            targetDirectoryPath.mkdir();
        }
        ImageInfo imageInfo = new ImageInfo(sourceImagePath);
        MagickImage magickImage = new MagickImage(imageInfo);
        magickImage.setFileName(targetImagePath);
        magickImage.writeImage(imageInfo);
    }

    private static String encodeFileToBase64(File file) {
        try {
            byte[] fileContent = Files.readAllBytes(file.toPath());
            return "data:image/heic;base64," + Base64.getEncoder().encodeToString(fileContent);
        } catch (IOException e) {
            throw new IllegalStateException("could not read file " + file, e);
        }
    }

    private static BufferedImage decodeBase64ToBufferedImage(String fileString) {
        String base64Image = fileString.split(",")[1];
        byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64Image);
        BufferedImage img = ImageIO.read(new ByteArrayInputStream(imageBytes));
        return img;
    }

    public static void main(String[] args) throws IOException, IM4JavaException, InterruptedException, MagickException { 
        File baseDirectory = new File(".");
        String projectDirectory = baseDirectory.getAbsolutePath().replace(".", "");
        String fileName = "20MB";
        String sourceImagePath = projectDirectory + "images/source/" + fileName + ".heic";
        String targetImagePath = projectDirectory + "images/target/" + fileName + ".jpg";
        String sourceFileBase64Encoded = encodeFileToBase64(new File(sourceImagePath));
        int width = 1024;

        long startTime = System.currentTimeMillis();
        heicToJpegIm4JavaByFilePath(sourceFileBase64Encoded, width);
        heicToJpegIm4JavaByEncodedString(sourceFileBase64Encoded, width);
        // heicToJpegJMagick();
        // Show time elapses
        long endTime = System.currentTimeMillis();
        double timeElapsed = (endTime - startTime) / 1000.0;
        System.out.println("--------------------------------------------");
        System.out.println("Conversion took " + timeElapsed + " seconds.");
        System.out.println("--------------------------------------------");
    }
}