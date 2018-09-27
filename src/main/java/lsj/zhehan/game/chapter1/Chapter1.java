package lsj.zhehan.game.chapter1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: liaosijun
 * Description:
 * DateTime: 2018年9月21日 下午3:03:37
 * Modified By:
 */
@RestController
@RequestMapping("/game/chapter1")
public class Chapter1 {
	
	
	@RequestMapping("/point")
	public Chapter1Result forward() throws Exception
	{
		Chapter1Result result = new Chapter1Result();
		return result;
	}
}
