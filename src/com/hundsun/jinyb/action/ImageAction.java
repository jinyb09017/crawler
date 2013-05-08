package com.hundsun.jinyb.action;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Map;

import com.jinyb.crawler.util.ImageUtil;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImageAction extends BaseAction{
  //type stream Result获取此属性值输出
	//类型必须为InputStream
	private InputStream imageStream;
    public String execute(){
  	Map<String,BufferedImage> map=ImageUtil.createImage();
  	//获取验证码字符，存入session
  	String code=map.keySet().iterator().next();
  	session.put("code", code);//存入session中
  	//获取图片
  	BufferedImage image=map.get(code);
  	//将image给imageStream赋值
  	ByteArrayOutputStream  bos=new ByteArrayOutputStream();
  	JPEGImageEncoder encoder=JPEGCodec.createJPEGEncoder(bos);//表示按jpeg的方式进行编码
  	try {
			encoder.encode(image);
			byte[] bts=bos.toByteArray();
			imageStream=new ByteArrayInputStream(bts);//在xml文件中输出这个值
		} catch (Exception e) {
			e.printStackTrace();
		}
  	return "success";
  }
	public InputStream getImageStream() {
		return imageStream;
	}

	public void setImageStream(InputStream imageStream) {
		this.imageStream = imageStream;
	}
	
}
