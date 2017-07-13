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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "model")
public class Model implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 交给数据库处理
	private Integer id;
	@Column(name = "model_name")
	private String model_Name;
	@Column(name = "key")
	private String key;
	@Column(name = "url")
	private String url;
	@Column(name = "remark")
	private String remark;
	@Column(name = "state")
	private Integer state;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parentid")
	private Model parent;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
	@OrderBy(value = "sort_value asc")
	private List<Model> children;
	@Column(name = "type")
	private String type;
	@Column(name = "view")
	private int view;
	@Column(name = "edit")
	private int edit;
	@Column(name = "add")
	private int add;
	@Column(name = "del")
	private int del;
	@Column(name = "print")
	private int print;
	@Column(name = "SORT_VALUE")
	private double sortValue;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getModel_Name() {
		return model_Name;
	}

	public void setModel_Name(String model_Name) {
		this.model_Name = model_Name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Model getParent() {
		return parent;
	}

	public void setParent(Model parent) {
		this.parent = parent;
	}

	public List<Model> getChildren() {
		return children;
	}

	public void setChildren(List<Model> children) {
		this.children = children;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getView() {
		return view;
	}

	public void setView(int view) {
		this.view = view;
	}

	public int getEdit() {
		return edit;
	}

	public void setEdit(int edit) {
		this.edit = edit;
	}

	public int getAdd() {
		return add;
	}

	public void setAdd(int add) {
		this.add = add;
	}

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}

	public int getPrint() {
		return print;
	}

	public void setPrint(int print) {
		this.print = print;
	}

	public double getSortValue() {
		return sortValue;
	}

	public void setSortValue(double sortValue) {
		this.sortValue = sortValue;
	}

}
