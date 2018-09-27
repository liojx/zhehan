package lsj.zhehan;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: liaosijun
 * Description:
 * DateTime: 2018年9月26日 下午3:31:15
 * Modified By:
 */
public class TestMap {
	public static void main(String[] args) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("a", Integer.parseInt("100"));
		map.put("b", Integer.parseInt("200"));
		map.put("c", Integer.parseInt("300"));
		map.put("d", Integer.parseInt("400"));
		if(map.containsKey("c")) {
			System.out.println(map.get("c"));
		}
	}
}
