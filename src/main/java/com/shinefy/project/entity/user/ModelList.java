package com.shinefy.project.entity.user;

/**   
 *  
 * @ProjectName: project
 * @Package: com.shinefy.project.entity.user     
 * @ClassName: Model     
 * @Description:  javabean，对应model表，菜单或模块表
 * @Author:  lee       
 * @CreateDate:  2016/11/21    
 * @UpdateUser:      
 * @UpdateDate:      
 * @UpdateRemark: 
 * @Version:  v0.0.1     
 *    
 */
import java.io.Serializable;
import java.util.List;

public class ModelList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Model> model;

	public List<Model> getModel() {
		return model;
	}

	public void setModel(List<Model> model) {
		this.model = model;
	}

}
