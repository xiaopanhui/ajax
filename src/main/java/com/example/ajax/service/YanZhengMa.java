package com.example.ajax.service;


import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.ImageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
@Service
public class YanZhengMa {

    public  String yanzhengma(byte[] img){
        String ocrResult = "";
        try{
            ITesseract instance = new Tesseract();
            //设置识别语言包路径
            instance.setDatapath("D:/tessdata");
            instance.setLanguage("eng");//设置语言库

            ByteArrayInputStream in = new ByteArrayInputStream(img);//将img作为输入流；
            BufferedImage grayImage = ImageHelper.convertImageToBinary(ImageIO.read(in));//二值化处理图像
            //BufferedImage image = ImageIO.read(in);//将in作为输入流，读取图片存入image中
            ocrResult = instance.doOCR(grayImage);
        }catch (TesseractException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return ocrResult;
    }
}
