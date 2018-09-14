package com.pera.tanping.peratech.framework.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * float 格式化方法
 * @author tp
 *
 */
public class DoubleFromat {

	/**
	 * 精度控制 十万分之一
	 * @param d
	 * @param digit
	 * @param mode
	 * @return
	 */
	public static String fromat2(double d,int digit,RoundingMode mode){

		BigDecimal b = new BigDecimal(d);


		if (mode.equals(RoundingMode.UP)) {
			if (digit == 0) {
				b = b.add(new BigDecimal( 0.49999));
				b = b.setScale(digit, BigDecimal.ROUND_HALF_UP);
			}else {
				double x = (500-1)/Math.pow(10,5);
				b = b.add(new BigDecimal(x));
				b = b.setScale(digit, BigDecimal.ROUND_HALF_UP);
			}
		} else if (mode.equals(RoundingMode.FLOOR)) {
			b = b.add(new BigDecimal("0.0001"));
			b = b.setScale(digit, BigDecimal.ROUND_FLOOR);
		} else {
			b = b.setScale(digit, BigDecimal.ROUND_HALF_UP);
		}



		return b.toString();
	}

	
	public static String fromat(double d,int digit,RoundingMode mode){
		  
	     BigDecimal b = new BigDecimal(d);

	     //精度控制在万分之一
	     b = b.add(new BigDecimal("0.0001"));

	     String pattern ="0.";
	     for (int i = 0; i < digit; i++) {
			pattern +="0";
		 }
	     DecimalFormat dd =new DecimalFormat(pattern,new DecimalFormatSymbols(Locale.US));
	     dd.setRoundingMode(mode);
		
		return dd.format(b);
	}
	
	/**
	 * 四舍5入 舍去
	 * @param d
	 * @param digit 位数
	 * @return
	 */
	public static String fromat(double d,int digit){
	     return fromat(d, digit,RoundingMode.FLOOR);
	}

	/**
	 * 四舍5入
	 * @param d
	 * @param digit 位数
	 * @return
	 */
	public static String fromat(float d,int digit){
		return fromat(d, digit,RoundingMode.FLOOR);
	}
	
	

}
