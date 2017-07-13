package com.shinefy.project.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 
 * @author lee
 *
 */
public class CustomException  extends Exception {
	private static final Logger logger = LoggerFactory.getLogger(CustomException.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code ;  //异常对应的返回码
    private String message;  //异常对应的描述信息
     
    public CustomException() {
        super();
    }
 
    public CustomException(String message) {
        super(message);
        this.message = message;
    }
 
    public CustomException(String code, String message) {
        super();
        this.code = code;
        this.message = message;
        logger.debug("");
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
 
 
}
