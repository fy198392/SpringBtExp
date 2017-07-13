package com.shinefy.project.controller.base;

import org.springframework.stereotype.Controller;

/**   
 *  
 * @ProjectName: project
 * @Package: com.shinefy.project.controller.base    
 * @ClassName: ErrorPageController     
 * @Description: 测试controller
 * @Author:  lee       
 * @CreateDate:  2016/11/21    
 * @UpdateUser:      
 * @UpdateDate:      
 * @UpdateRemark: 
 * @Version:  v0.0.1     
 *    
 */
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;



@Controller
public class ErrorPageController   {

//	public static final String DEFAULT_ERROR_VIEW = "/error500";
//	@ExceptionHandler(value = NoHandlerFoundException.class)
//    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("exception", e);
//        mav.addObject("url", req.getRequestURL());
//        mav.setViewName(DEFAULT_ERROR_VIEW);
//        return mav;
//    }



	@RequestMapping("/error500")
	public ModelAndView errorPage500(){
		ModelAndView mv = new ModelAndView("/error500");
		return mv;
	}
	
	
	@RequestMapping("/error404")
	public ModelAndView errorPage404(){
		ModelAndView mv = new ModelAndView("/error404");
		return mv;
	}
	
	@RequestMapping("/error403")
	public ModelAndView errorPage403(){
	    ModelAndView mv = new ModelAndView("/error403");
		return mv;
	}




}
