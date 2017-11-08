package com.fico.testCaseGenerator.util;

import java.text.NumberFormat;
import java.util.Date;
import java.util.Random;

public class RandomFactory {

	private static NumberFormat nf = NumberFormat.getNumberInstance();
	
	static {
		nf.setMaximumFractionDigits(2); 
	}
	
	private static Random random = new Random();
	
	public static double random100(double para){
		return random.nextDouble() * para;
	}
	
	public static double random(){
		return random.nextDouble();
	}
	
	//左开右闭
	public static int randomIntBetween(int min, int max){
		
		if(max == min){
			return max;
		}
		if(max < min){
			System.out.println("max is smaller than min" + " max is " + max + " min is " + min);
		}
		return random.nextInt( Math.abs(max - min) ) + Math.min(max, min) + 1 ;
	}
	
	public static double randomDoubleBetween(double min, double max){
	
		if(max == min){
			return max;
		}
		
		double rtnDouble = random.nextDouble()*(max - min) + min;
		
		//现在去小数后4位
		return (double)Math.round( rtnDouble *100)/100;
	}
	
	public static Date randomDateBetween(Date datemin, Date datemax){
		Long minDateTime = datemin.getTime();
		
		Long maxDateTime = datemax.getTime();
		
		if(minDateTime == maxDateTime){
			
			return datemin;
		}
		
		long rtnDateTime = new Double(random.nextDouble()*(maxDateTime - minDateTime) + minDateTime).longValue();
		
		Date rtnDate = new Date();
		
		rtnDate.setTime( rtnDateTime );
		
		return rtnDate ;
	}
	
	public static void main(String[] args){
		
		for( int i=0; i<20; i++ ){
			System.out.println( random() );
		}
	}
}
