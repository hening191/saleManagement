package com.hening.sale.utils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public class ImageUtil{

	/**
	 * 缩小图片
	 *@author (作者): lixr 
	 *@date 日期： 2016年8月29日下午5:46:00.
	 *@method:compressImg
	 *@description 此方法描述的是：in 输入流， suffix 图片的后缀名， w 宽度, h 高度
	 */
	public static InputStream compressImg(InputStream in, String suffix ,int w, int h) {
		InputStream ins = null;
		try {
			Image img = ImageIO.read(in);
			BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
			Graphics g = bi.getGraphics();
			g.drawImage(img, 0, 0, w, h, Color.white, null);
			g.dispose();
			ByteArrayOutputStream bout = new ByteArrayOutputStream();  
			ImageIO.write(bi, suffix, bout);  
			ins = new ByteArrayInputStream(bout.toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ins;
	}
	
	/**
	 * 截取图片
	 *@author (作者): lixr 
	 *@date 日期： 2016年8月29日下午5:43:14.
	 *@method:cutImg
	 *@description 此方法描述的是： in 输入流， suffix 图片的后缀名， x 横坐标， y 纵坐标, w 宽度, h 高度
	 */
	public static InputStream cutImg(InputStream in, String suffix,  int x, int y, int w, int h) {
		InputStream ins = null;
		ImageInputStream iis = null;
		try {
			Rectangle rect = new Rectangle(x, y, w, h); 
			iis = ImageIO.createImageInputStream(in);
			ImageReader reader = ImageIO.getImageReadersBySuffix(suffix).next();
			reader.setInput(iis,true);
			ImageReadParam param = reader.getDefaultReadParam();
			param.setSourceRegion(rect);
			BufferedImage bi = reader.read(0, param);
			ByteArrayOutputStream bout = new ByteArrayOutputStream();  
			ImageIO.write(bi, suffix, bout);  
			ins = new ByteArrayInputStream(bout.toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ins;
	}

	/**
	 * 截取缩略图
	 *@author (作者): lixr 
	 *@date 日期： 2016年8月29日下午5:46:38.
	 *@method:getCompressImg
	 *@description 此方法描述的是：yw 缩略的宽度， yh 缩略的高度
	 */
	public static InputStream getCompressImg(File file,int yw, int yh) {
		InputStream in = null;
		try {
			Image img = javax.imageio.ImageIO.read(file); // 构造Image对象  
			int width = img.getWidth(null); // 得到源图宽  
			int height = img.getHeight(null); // 得到源图长  
			
			String suffix = getFormatName(file);

			int y = 0;
			int x = 0;
			int w = 0;
			int h = 0;
			
			float ny = (float)height/yh;
			float nw = (float)width/yw;
			if(ny>nw){
				h = (int) (yh * nw); 
				w = (int) (yw * nw); 
			}else{
				h = (int) (yh * ny); 
				w = (int) (yw * ny); 
			}
			
			y = (height - h)/2;
			x = (width - w)/2;

			InputStream in1 = new FileInputStream(file);
			InputStream in2 = cutImg(in1, suffix, x, y, w, h);//截取	 
			in = compressImg(in2, suffix, yw, yh);//缩小
		} catch (Exception e) {
			e.printStackTrace();
		}
		return in;
	}
	
	public static String getFormatName(File file) {
        try {
            ImageInputStream iis = ImageIO.createImageInputStream(file);
    
            Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);
            if (!iter.hasNext()) {
                return "jpg";
            }
    
            ImageReader reader = iter.next();
    
            iis.close();
    
            return reader.getFormatName();
        } catch (IOException e) {
           e.printStackTrace();
        }
        return "jpg";
    }
	
	public static void main(String[] args) throws IOException {
		//String fileName  = "D:/安装文件/电脑图片/桌面图片/btn-a-tg.png";
		//File file = new File(fileName); // 读入文件  
		  // 读入文件  
		//InputStream in = getCompressImg(file, 250 * 100, 188 * 100);
		//InputStream in2 = getCompressImg(file, 320, 240);
		
		
//		@SuppressWarnings("resource")
//		OutputStream out1 = new FileOutputStream("D:/image/4.jpg");
//		byte [] buffer1 = new byte [1024];
//		while (in2.read(buffer1) > 0 ) {
//			out1.write(buffer1);
//		}
		
		/*@SuppressWarnings("resource")
		OutputStream out = new FileOutputStream("D:/image/2.jpg");
		byte [] buffer = new byte [1024];
		while (in.read(buffer) > 0 ) {
			out.write(buffer);
		}
		
		@SuppressWarnings("resource")
		InputStream artworkFile = new FileInputStream(file);
		@SuppressWarnings("resource")
		OutputStream outArtwork = new FileOutputStream("D:/image/1.jpg");
		byte[] b = new byte[1024];
		while (artworkFile.read(b) > 0){
			outArtwork.write(b);
		}*/
		
		
		/*
		URL url = new URL("http://192.168.1.85:8000/SA360/ARTWORKS/user_563/1481016216256_320.jpg");
		
		HttpURLConnection httpUrl = (HttpURLConnection) url.openConnection();  
		
		InputStream inurl = httpUrl.getInputStream();
		
		@SuppressWarnings("resource")
		OutputStream outArtwork = new FileOutputStream("D:/1.jpg");
		byte[] b = new byte[1024];
		while (inurl.read(b) > 0){
			outArtwork.write(b);
		}*/
		
	}
	
	
}