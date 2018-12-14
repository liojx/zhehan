package lsj.zhehan;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import studiii.zlsj_test.service.DubboTestService;


/**
 * @author liaosijun
 * @since 2018年12月13日 下午3:47:28
 */
public class Consumer {
	public static void main(String[] args) {
		
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"consumer.xml"});
	    context.start();
	    DubboTestService dubboTestService = (DubboTestService)context.getBean("dubboTestService"); // 获取远程服务代理
	    dubboTestService.getSome("AAAAAAAAAAAAAAAAAAAAAAAAAAA");// 执行远程方法
	}
}
