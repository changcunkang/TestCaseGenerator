package com.fico.testCaseGenerator.util;

import org.apache.commons.math3.random.RandomDataGenerator;

import java.text.NumberFormat;
import java.util.Date;
import java.util.Random;

public class RandomFactory {

	private RandomDataGenerator randomDataGenerator = new RandomDataGenerator();

	private  NumberFormat nf = NumberFormat.getNumberInstance();
	
	 {
		nf.setMaximumFractionDigits(2); 
	}
	
	//private  Random random = new Random();
	
	public   double random100(double para){
		return new Random().nextDouble() * para;
	}
	
	public   double random(){
		return new Random().nextDouble()*100;
	}
	
	//左闭右闭
	public   int randomIntBetween(int min, int max){

		if(min == max){
			return min;
		}

		if(min>max){
			return max;
		}
		return randomDataGenerator.nextInt(min, max);
	}

	//左闭右闭
	public   long randomLongBetween(long min, long max){

		if(min == max){
			return min;
		}

		if(min>max){
			return max;
		}

		return randomDataGenerator.nextLong(min, max);
	}

	public   Double randomDoubleBetween(double min, double max){

		if(max == min){
			return max;
		}
		
		double rtnDouble = new Random().nextDouble()*(max - min) + min;
		
		//现在去小数后4位
		return (double)Math.round( rtnDouble *100)/100;
	}
	
	public   Date randomDateBetween(Date datemin, Date datemax){
		Long minDateTime = datemin.getTime();
		
		Long maxDateTime = datemax.getTime();
		
		if(minDateTime == maxDateTime){
			
			return datemin;
		}
		
		long rtnDateTime = new Double(new Random().nextDouble()*(maxDateTime - minDateTime) + minDateTime).longValue();
		
		Date rtnDate = new Date();
		
		rtnDate.setTime( rtnDateTime );
		
		return rtnDate ;
	}

}
