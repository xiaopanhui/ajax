package com.example.ajax.service;


import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.ImageHelper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class YanZhengMa2 {
    public static void main(String[] args) {
        yanzhengma();
    }


    public static String yanzhengma(){
        String ocrResult = "";
        try{
            ITesseract instance = new Tesseract();
            //instance.setLanguage("chi_sim");
            //Set the tessdata pat
            //tessdata 识别语言包
            instance.setDatapath("D:/tessdata");
            instance.setLanguage("eng");//设置语言库
            //long startTime = System.currentTimeMillis();
            File imgDir = new File("d:/tem.jpg");
            BufferedImage grayImage = ImageHelper.convertImageToBinary(ImageIO.read(imgDir));
            ImageIO.write(grayImage, "jpg", new File("d:/", "test2.jpg"));
            File imgDir2 = new File("d:/test2.jpg");
            //ByteArrayInputStream in = new ByteArrayInputStream(img);//将img作为输入流；
            // BufferedImage image = ImageIO.read(in);//将in作为输入流，读取图片存入image中，而这里in可以
            //ocrResult = instance.doOCR(image);
            ocrResult = instance.doOCR(imgDir2);
        }catch (TesseractException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return ocrResult;
    }

}
