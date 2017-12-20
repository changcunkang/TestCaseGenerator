package com.fico.testCaseGenerator.util;

import org.apache.commons.math3.random.RandomDataGenerator;

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
	
	//左闭右闭
	public static int randomIntBetween(int min, int max){
		return new RandomDataGenerator().nextInt(min, max);
	}

	//左闭右闭
	public static long randomLongBetween(long min, long max){

		if(min == max){
			return min;
		}

		if(min>max){
			return max;
		}

		return new RandomDataGenerator().nextLong(min, max);
	}

	public static Double randomDoubleBetween(double min, double max){

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
