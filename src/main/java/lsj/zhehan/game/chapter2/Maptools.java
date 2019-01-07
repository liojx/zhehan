package lsj.zhehan.game.chapter2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Author: liaosijun
 * Description:
 * DateTime: 2018年9月21日 下午3:40:08
 * Modified By:
 */
public class Maptools {
	
	final static Integer WIDTH = 10;
	final static Integer HEIGHT = 10;
	
	final static Integer canOK = 2;
	
	Meta next = null;
	
	Meta pre = null;
	
	Map<String, Meta> growMap = new HashMap<String, Meta>();
	
	static Map<String, Meta> allMap = new HashMap<String, Meta>();
	
	List<Meta> allList = new ArrayList<Meta>();
	
	List<String> coordinateList = new ArrayList<String>();
	
	public static void main(String[] args) {
//		for(int i = 0 ; i < HEIGHT ;i ++) {
//			for(int j = 0; j < WIDTH ; j ++) {	
//				System.out.print(j + "" + i);
//				System.out.print(" ");
//			}
//			System.out.println();
//		}
//		
//		Maptools mp = new Maptools();
//		allMap = mp.generateAllMap();
//		Meta meta = new Meta(0,0);
//		meta = mp.RandowMeta(meta, true);
//		System.out.println(meta);
//		System.out.println(mp.nextMeta(meta));
		
//		for(Meta m : mp.generateMap()) {
//			System.out.println(m);
//		}
//		mp.line(meta);
		for(int i = 0 ; i < HEIGHT ;i ++) {
			for(int j = 0; j < WIDTH ; j ++) {	
				System.out.print(j + "" + i);
				System.out.print(" ");
			}
			System.out.println();
		}
		
		Maptools mt = new Maptools();
		mt.generateWayOut();
	}
	
	List<Meta> generateWayOut(){
		Map<String,Meta> map = generateAllMap();
		Meta first = new Meta();
		first.setX(0);
		first.setY(0);
		List<Meta> wayOut = new ArrayList<Meta>();
//		wayOut.add(first);
		while(true) {
			Meta next = lineMoveNext(first);
			if(map.containsKey(getXY(next))) {
				boolean exist = false;
				for(Meta one : wayOut) {
					if(next.getX() == one.getX() && next.getY() == one.getY()) {
						exist = true;
						break;
					}
				}
				if(!exist) {
					wayOut.add(new Meta(next.getX(),next.getY()));
					first = next;
				}
			}
			if(next.getX() == HEIGHT - 1 && next.getY() == HEIGHT -1) {
				break;
			}
		}
		for(Meta s : wayOut) {
			System.out.println(s.toString());
		}
		return null;
	}
	
	void allXY(){
		List<Meta> list = new ArrayList<Meta>();
		for(int i = 0 ; i < HEIGHT ;i ++) {
			for(int j = 0; j < WIDTH ; j ++) {	
				Meta meta = new Meta();
				meta.setX(j);
				meta.setY(i);
				meta = RandowMeta(meta, true);
				list.add(meta);
			}
		}
		allList = list;
	}
	static boolean compare(Meta meta1, Meta meta2) {
		boolean bool = false;
		if(meta1 != null && meta2 != null) {
			if(meta1.getX().equals(meta2.getX()) && meta1.getY().equals(meta2.getY())) {
				bool = true;
			}
		}
		return bool;
	}
	/**
	 * 根据当前meta,生产它的四个方向是否能进入，
	 * hasExit=true 表明至少有一个方向可以进入
	 * hasExit=false 有可能4个方向都不能进入
	 * @param meta
	 * @return
	 */
	synchronized Meta RandowMeta(Meta meta, boolean hasExit) {
		
		Random ran = new Random();
		meta.setLeft(ran.nextBoolean());
		meta.setUp(ran.nextBoolean());
		meta.setRight(ran.nextBoolean());
		meta.setDown(ran.nextBoolean());
		int x = meta.getX();
		int y = meta.getY();
		if(x <= 0) {
			meta.setLeft(false);
		}else if(x >= WIDTH - 1) {
			meta.setRight(false);
		}
		
		if(y <= 0) {
			meta.setUp(false);
		}else if(y >= HEIGHT -1) {
			meta.setDown(false);
		}
		if(hasExit) {
			if(!meta.isLeft() && !meta.isUp() && !meta.isRight() && !meta.isDown()) {
				RandowMeta(meta, hasExit);
			}
		}
		if(allList.size() == 0) {
			Meta meta_fill = new Meta(meta.getX(),meta.getY());
			meta_fill.setUp(meta.isUp());
			meta_fill.setDown(meta.isDown());
			meta_fill.setLeft(meta.isLeft());
			meta_fill.setRight(meta.isRight());
			allList.add(meta_fill);
		}else {
			boolean has = false;
			for(Meta one : allList) {
				if(compare(meta,one)) {
					has = true;
					meta = one;
					break;
				}
			}
			if(!has) {
				Meta meta_fill = new Meta(meta.getX(),meta.getY());
				meta_fill.setUp(meta.isUp());
				meta_fill.setDown(meta.isDown());
				meta_fill.setLeft(meta.isLeft());
				meta_fill.setRight(meta.isRight());
				allList.add(meta_fill);
			}
		}
//		if(growMap.size() == 0) {
//			Meta meta_fill = new Meta(meta.getX(),meta.getY());
//			meta_fill.setUp(meta.isUp());
//			meta_fill.setDown(meta.isDown());
//			meta_fill.setLeft(meta.isLeft());
//			meta_fill.setRight(meta.isRight());
//			growMap.put(new String(getXY(meta_fill)),meta_fill);
//		}else {
//			if(growMap.containsKey(getXY(meta))) {
//				meta = growMap.get(getXY(meta));
//			}else {
//				Meta meta_fill = new Meta(meta.getX(),meta.getY());
//				meta_fill.setUp(meta.isUp());
//				meta_fill.setDown(meta.isDown());
//				meta_fill.setLeft(meta.isLeft());
//				meta_fill.setRight(meta.isRight());
//				growMap.put(new String(getXY(meta_fill)),meta_fill);
//			}
//		}
//		if(allMap.containsKey(getXY(meta))) {
//			System.out.println("已经存在得-------------"+meta);
//		}
		return meta;
	}
	
	/**
	 * 当前meta 的下一个metas集合，可能多个，最大4
	 * @param meta
	 * @return
	 */
	List<Meta> nextMeta(Meta meta) {
		List<Meta> canMoveList = new ArrayList<Meta>();
		int x = meta.getX();
		int y = meta.getY();
			if(meta.isLeft()) {
				Meta nextMeta = new Meta();
				nextMeta.setX(x  - 1);
				nextMeta.setY(y);
				canMoveList.add(nextMeta);
			}
			if (meta.isUp()) {
				Meta nextMeta = new Meta();
				nextMeta.setX(x);
				nextMeta.setY(y - 1);
				canMoveList.add(nextMeta);
			}
			if (meta.isRight()) {
				Meta nextMeta = new Meta();
				nextMeta.setX(x + 1);
				nextMeta.setY(y);
				canMoveList.add(nextMeta);
			}
			if (meta.isDown()){
				Meta nextMeta = new Meta();
				nextMeta.setX(x);
				nextMeta.setY(y + 1);
				canMoveList.add(nextMeta);
			}
//		canMoveList.stream().distinct().collect(Collectors.toList()); //去重 ，不生效，都是new的
		return canMoveList;
	}
	
	Map<String,Meta> generateAllMap() {
		Map<String,Meta> map = new HashMap<String,Meta>();
		for(int i = 0; i < HEIGHT; i++ ) {
			for(int j = 0; j < WIDTH; j++) {
				Meta meta = new Meta();
				meta.setX(j);
				meta.setY(i);
				map.put(getXY(meta),meta);
			}
		}
		return map;
	}
	
	/**
	 * 从meta 开始移动
	 * @param meta
	 */
	Meta moveLeft(Meta meta) {
		Meta res = new Meta();
		res.setX(meta.getX() - 1);
		return new Meta(res.getX(),meta.getY());
	}
	
	Meta moveUp(Meta meta) {
		Meta res = new Meta();
		res.setY(meta.getY() - 1);
		return new Meta(meta.getX(),res.getY());
	}
	
	Meta moveRight(Meta meta) {
		Meta res = new Meta();
		res.setX(meta.getX() + 1);
		return new Meta(res.getX(),meta.getY());
	}
	
	Meta moveDown(Meta meta) {
		Meta res = new Meta();
		res.setY(meta.getY() + 1);
		return new Meta(meta.getX(),res.getY());
	}
	
	synchronized Meta moveNext(Meta meta, int moveCount) {
		Meta next = new Meta();
		int mod = moveCount % 4;
		switch (mod) {
		case 0 :
			if(meta.isRight()) {
				next = moveRight(meta);
				return next;
			}
			if(meta.isDown()) {
				next = moveDown(meta);
				return next;
			}
			if(meta.isLeft()) {
				next = moveLeft(meta);
				return next;
			}
			if(meta.isUp()) {
				next = moveUp(meta);
				return next;
			}
			
		break;
		case 1 :
			if(meta.isUp()) {
				next = moveUp(meta);
				return next;
			}
			if(meta.isRight()) {
				next = moveRight(meta);
				return next;
			}
			if(meta.isDown()) {
				next = moveDown(meta);
				return next;
			}
			if(meta.isLeft()) {
				next = moveLeft(meta);
				return next;
			}
			break;
		case 2 :
			if(meta.isLeft()) {
				next = moveLeft(meta);
				return next;
			}
			if(meta.isUp()) {
				next = moveUp(meta);
				return next;
			}
			if(meta.isRight()) {
				next = moveRight(meta);
				return next;
			}
			if(meta.isDown()) {
				next = moveDown(meta);
				return next;
			}
			break;
		case 3 :
			if(meta.isDown()) {
				next = moveDown(meta);
				return next;
			}
			if(meta.isLeft()) {
				next = moveLeft(meta);
				return next;
			}
			if(meta.isUp()) {
				next = moveUp(meta);
				return next;
			}
			if(meta.isRight()) {
				next = moveRight(meta);
				return next;
			}
		default: ;
		}
		return null;
	}
	
	
	synchronized Meta lineMoveNext(Meta meta) {
		Meta next = new Meta();
		Random rd = new Random();
		int md = rd.nextInt(4) + 1;
		switch(md) {
			case 1 : {
				next = moveRight(meta); 
				if(next.getX() >= 0 && next.getX() < WIDTH) {
					break;
				}
			}
			case 2 : {
				next = moveDown(meta); 
				if(next.getY() >=0 && next.getY() < HEIGHT) {
					break;
				}
			}
			case 3 : 
				{
					next = moveLeft(meta);
					if(next.getX() >= 0 && next.getX() < WIDTH) {
						break;
					}
				}
			case 4 :{
				next = moveUp(meta);
				if(next.getY() >=0 && next.getY() < HEIGHT) {
					break;
				}else {
					while(true) {
						Meta xy = notCanUseDIGUI(meta);
						if(xy != null && xy.getX() >= 0 && xy.getX() < WIDTH && xy.getY() >=0 && xy.getY() < HEIGHT) {
							next = xy;
							break;
						}
					}
				}
			}
			default : ;
		}
		return next;
	}
	
	private Meta notCanUseDIGUI(Meta meta) {
		return lineMoveNext(meta);
	}
	String getXY(Meta meta) {
		return meta.getX() + "" + meta.getY() ;
 	}
	
	void line(Meta start) {
		int i = 0;
		System.out.println(start);
		do {
			Meta next = moveNext(start,i);
//			System.out.println("next============>>>     "+next);
			start = RandowMeta(next,true);
			System.out.println(start);
			i ++;
		}while(!(start.getX().equals(WIDTH - 1) && start.getY().equals(HEIGHT -1)));
	}
}
