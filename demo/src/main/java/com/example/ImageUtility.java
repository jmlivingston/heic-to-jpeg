package com.example;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Base64;

import javax.imageio.ImageIO;

public class ImageUtility {
    public static String convertFileToBase64String(File file, String type) {
        try {
            byte[] fileContent = Files.readAllBytes(file.toPath());
            return "data:image/" + type + ";base64," + java.util.Base64.getEncoder().encodeToString(fileContent);
        } catch (IOException e) {
            throw new IllegalStateException("could not read file " + file, e);
        }
    }

    public static InputStream convertBase64ToInputStream(String fileString) throws IOException {
        String base64Image = fileString.split(",")[1];
        byte[] imageBytes = org.apache.commons.codec.binary.Base64.decodeBase64(base64Image);
        InputStream inputStream = new ByteArrayInputStream(imageBytes);
        return inputStream;
    }

    public static String convertBufferedImageToString(BufferedImage image, String type) throws IOException {
        String imageString = null;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, type, byteArrayOutputStream);
        byte[] imageBytes = byteArrayOutputStream.toByteArray();
        Base64.Encoder encoder = Base64.getEncoder();
        imageString = encoder.encodeToString(imageBytes);
        byteArrayOutputStream.close();
        return "data:image/" + type + ";base64," + imageString;
    }
}
