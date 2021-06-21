package com.example;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.im4java.core.IM4JavaException;

import magick.MagickException;

// These functions convert HEIC (base64 strings or files) into JPEG (base64 strings or files).

// Note: Requires ImageMagick or GraphicsMagick (a fork) to be installed locally or binary added to library
// https://imagemagick.org/script/download.php
// http://www.graphicsmagick.org/ - This is preferred as it is faster.

// Two potential conversion libraries. Both rely on ImageMagick or GraphicsMagick
// - im4java - http://im4java.sourceforge.net/
// - jmagick - https://github.com/techblue/jmagick - Not working due to this error: https://bit.ly/3gFWs4N

public class App {
    public static void main(String[] args) throws IOException, IM4JavaException, InterruptedException, MagickException {
        long startTime = System.currentTimeMillis();
        File baseDirectory = new File(".");
        String projectDirectory = baseDirectory.getAbsolutePath().replace(".", "");
        String fileName = "20MB";
        String type = "jpg";
        String sourceImagePath = projectDirectory + "images/source/" + fileName + ".heic";
        // String targetImagePath = projectDirectory + "images/target/" + fileName + ".jpg";
        String sourceFileBase64Encoded = ImageUtility.convertFileToBase64String(new File(sourceImagePath), type);
        int width = 1024;

        // Uncomment to test out other functions
        // Cleanup target folder
        // File targetFile = new File(targetImagePath);
        // targetFile.delete();
        // File targetDirectoryPath = new File(projectDirectory + "images/target/");
        // if (!targetDirectoryPath.exists()) {
        //     targetDirectoryPath.mkdir();
        // }
        // Im4JavaHelper.heicToJpegFileByFilePath(sourceImagePath, targetImagePath,
        // width);
        // Im4JavaHelper.heicToJpegFileByEncodedString(sourceFileBase64Encoded,
        // targetImagePath, width);

        String jpegEncodedString = Im4JavaHelper.heicToJpegStringByEncodedString(sourceFileBase64Encoded, type, width);
        PrintWriter out = new PrintWriter("datauri.txt");
        out.print(jpegEncodedString);
        out.close();

        // Show time elapses
        long endTime = System.currentTimeMillis();
        double timeElapsed = (endTime - startTime) / 1000.0;
        System.out.println("--------------------------------------------");
        System.out.println("Conversion took " + timeElapsed + " seconds.");
        System.out.println("--------------------------------------------");
    }
}