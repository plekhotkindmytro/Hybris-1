package com.yotpo.jalo;

import com.yotpo.constants.YotpoConstants;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;
import org.apache.log4j.Logger;

@SuppressWarnings("PMD")
public class YotpoManager extends GeneratedYotpoManager
{
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger( YotpoManager.class.getName() );
	
	public static final YotpoManager getInstance()
	{
		ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (YotpoManager) em.getExtension(YotpoConstants.EXTENSIONNAME);
	}
	
}
