package com.zh.container;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.access.BeanFactoryLocator;
import org.springframework.beans.factory.access.BeanFactoryReference;
import org.springframework.context.ApplicationContext;
import org.springframework.context.access.ContextSingletonBeanFactoryLocator;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * mq容器
 * 
 * @copyrights 新国都技术股份有限公司
 * @author lin_song
 * @Date 2014-3-12
 * @desc
 * @version
 * @tosee
 * 
 */
public class ZHSpringContainer
{
//	private static final Logger logger = LoggerFactory.getLogger(ZHSpringContainer.class);

	private static final String REDIS_CONFIG = "classpath*:redisContext.xml";
	
	private BeanFactoryReference parentContextRef;

	static ClassPathXmlApplicationContext context;

	public static ClassPathXmlApplicationContext getContext()
	{
		return context;
	}

	public void start()
	{
		context = new ClassPathXmlApplicationContext(new String[] { REDIS_CONFIG }, true, loadParentContext());
		context.start();

	}

	public void stop()
	{
		try
		{
			if (context != null)
			{
				context.stop();
				context.close();
				context = null;
			}
		} catch (Throwable e)
		{
//			logger.error( e.getMessage(), e);
		}

	}

	protected ApplicationContext loadParentContext()
	{
		ApplicationContext parentContext = null;
		String locatorFactorySelector = "classpath*:beanRefFactory.xml";
		String parentContextKey = "businessBeanFactory";

		if (parentContextKey != null)
		{
			// locatorFactorySelector may be null, indicating the default
			// "classpath*:beanRefContext.xml"
			BeanFactoryLocator locator = ContextSingletonBeanFactoryLocator.getInstance(locatorFactorySelector);
			Log logger = LogFactory.getLog(ZHSpringContainer.class);
			if (logger.isDebugEnabled())
			{
				logger.debug("Getting parent context definition: using parent context key of '" + parentContextKey + "' with BeanFactoryLocator");
			}
			this.parentContextRef = locator.useBeanFactory(parentContextKey);
			parentContext = (ApplicationContext) this.parentContextRef.getFactory();
		}

		return parentContext;
	}
}
