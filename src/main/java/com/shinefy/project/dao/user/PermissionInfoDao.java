package com.shinefy.project.dao.user;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.shinefy.project.entity.user.Permission;

@Repository("permissionInfoDao")
public interface PermissionInfoDao extends PagingAndSortingRepository<Permission, Integer>, JpaSpecificationExecutor<Permission> {
	void deleteById(int id);
		
}
