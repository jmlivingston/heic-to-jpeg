package com.example;

import java.io.File;
import java.io.IOException;

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

public class App {
    public enum CONVERSION_MODE { JMAGICK, IM4JAVA}
    public static void main(String[] args) throws InterruptedException, IOException, MagickException, IM4JavaException {
        long startTime = System.currentTimeMillis();
        CONVERSION_MODE conversionMode = CONVERSION_MODE.IM4JAVA;
        File baseDirectory = new File(".");
        String projectDirectory = baseDirectory.getAbsolutePath().replace(".", "");
        String fileName = "20MB";
        String sourceImagePath = projectDirectory + "images/source/" + fileName + ".heic";
        String targetImagePath = projectDirectory + "images/target/" + fileName + ".jpg";
        int targetWidth = 1024; // Resizing before converting improves performance, so we can tweak accordingly.

        // Cleanup
        File targetFile = new File(targetImagePath);
        targetFile.delete();

        if (conversionMode == CONVERSION_MODE.IM4JAVA) {
            ConvertCmd convertCmd = new ConvertCmd();         
            // Add this if we include library in project
            // cmd.setSearchPath(IMAGE_MAGICK_PATH);
            Info info = new Info(sourceImagePath, true);
            int width = info.getImageWidth();
            IMOperation imOperation = new IMOperation();
            imOperation.addImage(sourceImagePath);
            if(width > targetWidth) {
                imOperation.resize(targetWidth);
            }
            imOperation.addImage(targetImagePath);
            convertCmd.run(imOperation);  
        } else {
            ImageInfo imageInfo = new ImageInfo(sourceImagePath);
            MagickImage magickImage = new MagickImage(imageInfo);
            magickImage.setFileName(targetImagePath);
            magickImage.writeImage(imageInfo);
        }

        // Show time elapses
        long endTime = System.currentTimeMillis();
        double timeElapsed = (endTime - startTime) / 1000.0;
        System.out.println("--------------------------------------------");       
        System.out.println("Conversion took " + timeElapsed + " seconds."); 
        System.out.println("--------------------------------------------");       
    }
}