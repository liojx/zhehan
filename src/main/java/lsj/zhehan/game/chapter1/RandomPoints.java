package lsj.zhehan.game.chapter1;

import java.util.Random;

/**
 * Author: liaosijun
 * Description:
 * DateTime: 2018年9月21日 下午3:11:53
 * Modified By:
 */
public class RandomPoints {
	
	public static Integer generatePoint() {
		Random ran = new Random();
		Double d = ran.nextDouble() * 10 ;
		Integer num = d.intValue();
		return num;
	}
	
	public static void main(String[] args) {
		int i = RandomPoints.generatePoint();
		System.out.println(i);
	}
}
