package com.atanu.java.spring.offersvc.logger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Atanu Bhowmick
 * 
 */
public class ApplicationLogger {

	private Log log;

	private final String NULL_STRING = "null";
	private final String PATTERN = "[{]\\s{0,}[}]";

	@SuppressWarnings("rawtypes")
	public ApplicationLogger(Class clazz) {
		this.log = LogFactory.getLog(clazz);
	}

	public boolean isTraceEnabled() {
		return log.isTraceEnabled();
	}

	public boolean isDebugEnabled() {
		return log.isDebugEnabled();
	}

	public boolean isInfoEnabled() {
		return log.isInfoEnabled();
	}

	public boolean isWarnEnabled() {
		return log.isWarnEnabled();
	}

	public boolean isErrorEnabled() {
		return log.isErrorEnabled();
	}

	public boolean isFatalEnabled() {
		return log.isFatalEnabled();
	}

	public void trace(Object msg) {
		if (isTraceEnabled()) {
			log.trace(msg);
		}
	}

	public void debug(Object msg) {
		if (isDebugEnabled()) {
			log.debug(msg);
		}
	}

	public void info(Object msg) {
		if (isInfoEnabled()) {
			log.info(msg);
		}
	}

	public void warning(Object msg) {
		if (isWarnEnabled()) {
			warn(msg);
		}
	}

	public void warn(Object msg) {
		if (isWarnEnabled()) {
			log.warn(msg);
		}
	}

	public void error(Object msg) {
		if (isErrorEnabled()) {
			log.error(msg);
		}
	}

	public void fatal(Object msg) {
		if (isFatalEnabled()) {
			log.fatal(msg);
		}
	}

	public void trace(String msg, Throwable ex) {
		if (isTraceEnabled()) {
			log.trace(msg, ex);
		}
	}

	public void debug(String msg, Throwable ex) {
		if (isDebugEnabled()) {
			log.debug(msg, ex);
		}
	}

	public void info(String msg, Throwable ex) {
		if (isInfoEnabled()) {
			log.info(msg, ex);
		}
	}

	public void warn(String msg, Throwable ex) {
		if (isWarnEnabled()) {
			log.warn(msg, ex);
		}
	}

	public void error(String msg, Throwable ex) {
		if (isErrorEnabled()) {
			log.error(msg, ex);
		}
	}

	public void fatal(String msg, Throwable ex) {
		if (isFatalEnabled()) {
			log.fatal(msg, ex);
		}
	}

	public void trace(String msg, Object obj) {
		if (isTraceEnabled()) {
			if (null != obj) {
				trace(msg.replaceAll(PATTERN, obj.toString()));
			} else {
				trace(msg.replaceAll(PATTERN, NULL_STRING));
			}
		}
	}

	public void debug(String msg, Object obj) {
		if (isDebugEnabled()) {
			if (null != obj) {
				debug(msg.replaceAll(PATTERN, obj.toString()));
			} else {
				debug(msg.replaceAll(PATTERN, NULL_STRING));
			}
		}
	}

	public void info(String msg, Object obj) {
		if (isInfoEnabled()) {
			if (null != obj) {
				info(msg.replaceAll(PATTERN, obj.toString()));
			} else {
				info(msg.replaceAll(PATTERN, NULL_STRING));
			}
		}
	}

	public void warn(String msg, Object obj) {
		if (isWarnEnabled()) {
			if (null != obj) {
				warn(msg.replaceAll(PATTERN, obj.toString()));
			} else {
				warn(msg.replaceAll(PATTERN, NULL_STRING));
			}
		}
	}

	public void error(String msg, Object obj) {
		if (isErrorEnabled()) {
			if (null != obj) {
				error(msg.replaceAll(PATTERN, obj.toString()));
			} else {
				error(msg.replaceAll(PATTERN, NULL_STRING));
			}
		}
	}

	public void trace(String msg, Object obj1, Object obj2) {
		if (isTraceEnabled()) {
			String obj1Str = NULL_STRING;
			String obj2Str = NULL_STRING;
			if (obj1 != null) {
				obj1Str = obj1.toString();
			}
			if (obj2 != null) {
				obj2Str = obj2.toString();
			}
			trace(msg.replaceFirst(PATTERN, obj1Str).replaceAll(PATTERN, obj2Str));
		}
	}

	public void debug(String msg, Object obj1, Object obj2) {
		if (isDebugEnabled()) {
			String obj1Str = NULL_STRING;
			String obj2Str = NULL_STRING;
			if (obj1 != null) {
				obj1Str = obj1.toString();
			}
			if (obj2 != null) {
				obj2Str = obj2.toString();
			}
			debug(msg.replaceFirst(PATTERN, obj1Str).replaceAll(PATTERN, obj2Str));
		}
	}

	public void info(String msg, Object obj1, Object obj2) {
		if (isInfoEnabled()) {
			String obj1Str = NULL_STRING;
			String obj2Str = NULL_STRING;
			if (obj1 != null) {
				obj1Str = obj1.toString();
			}
			if (obj2 != null) {
				obj2Str = obj2.toString();
			}
			info(msg.replaceFirst(PATTERN, obj1Str).replaceAll(PATTERN, obj2Str));
		}
	}

	public void warn(String msg, Object obj1, Object obj2) {
		if (isWarnEnabled()) {
			String obj1Str = NULL_STRING;
			String obj2Str = NULL_STRING;
			if (obj1 != null) {
				obj1Str = obj1.toString();
			}
			if (obj2 != null) {
				obj2Str = obj2.toString();
			}
			warn(msg.replaceFirst(PATTERN, obj1Str).replaceAll(PATTERN, obj2Str));
		}
	}

	public void error(String msg, Object obj1, Object obj2) {
		if (isErrorEnabled()) {
			String obj1Str = NULL_STRING;
			String obj2Str = NULL_STRING;
			if (obj1 != null) {
				obj1Str = obj1.toString();
			}
			if (obj2 != null) {
				obj2Str = obj2.toString();
			}
			error(msg.replaceFirst(PATTERN, obj1Str).replaceAll(PATTERN, obj2Str));
		}
	}

	public void error(String msg, Object obj, Throwable ex) {
		if (null != obj) {
			error(msg.replaceAll(PATTERN, obj.toString()), ex);
		} else {
			error(msg.replaceAll(PATTERN, NULL_STRING), ex);
		}
	}

	public void fatal(String msg, Object obj, Throwable ex) {
		if (null != obj) {
			fatal(msg.replaceAll(PATTERN, obj.toString()), ex);
		} else {
			fatal(msg.replaceAll(PATTERN, NULL_STRING), ex);
		}
	}
}
