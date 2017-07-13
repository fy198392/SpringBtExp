package com.shinefy.project.dao.user;

import java.util.List;

import javax.persistence.OrderBy;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shinefy.project.entity.user.Model;

@Repository("modelInfoDao")
public interface ModelInfoDao extends PagingAndSortingRepository<Model, Integer>, JpaSpecificationExecutor<Model> {
	/**
	 * 获得模块列表
	 * 
	 * @return
	 */
	List<Model> getByParentIdIsNull(Sort sort);
//	List<Model> getByParentIdIsNullOrderBySortValueAsc();

	/**
	 * 根据id获得模块信息
	 * 
	 * @param id
	 * @return
	 */
	@Query("select u from Model u where u.id=:id")
	Model getModelById(@Param("id") String id);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("update Model u set u.view = ?1,u.edit=?2,u.add=?3,u.del=?4,u.print=?5 where u.id = ?6")
	int updateModel(Integer view, Integer edit, Integer add, Integer del, Integer print, Integer id);
	// @Modifying
	// @Transactional
	// @Query("delete from User u where u.active = false")
	// void deleteInactiveUsers();

	// @Query("select u from Model u where u.emailAddress = ?1")
	// Model findByEmailAddress(String emailAddress);
	//
	// @Query("select u from User u where u.firstname like %?1")
	// List<Model> findByFirstnameEndsWith(String firstname);
	//
	// @Query(value = "SELECT * FROM USERS WHERE EMAIL_ADDRESS = ?1",
	// nativeQuery = true)
	// Model findByEmailAddress2(String emailAddress);
	//
	// @Query(value = "SELECT * FROM USERS WHERE LASTNAME = ?1", countQuery =
	// "SELECT count(*) FROM USERS WHERE LASTNAME = ?1", nativeQuery = true)
	// Page<Model> findByLastname(String lastname, Pageable pageable);
	//
	// @Query("select u from User u where u.lastname like ?1%")
	// List<Model> findByAndSort(String lastname, Sort sort);
	// List<Person> findByEmailAddressAndLastname(EmailAddress emailAddress,
	// String lastname);

	// Enables the distinct flag for the query
	// List<Person> findDistinctPeopleByLastnameOrFirstname(String lastname,
	// String firstname);

	// List<Person> findPeopleDistinctByLastnameOrFirstname(String lastname,
	// String firstname);

	// Enabling ignoring case for an individual property
	// List<Person> findByLastnameIgnoreCase(String lastname);

	// Enabling ignoring case for all suitable properties
	// List<Person> findByLastnameAndFirstnameAllIgnoreCase(String lastname,
	// String firstname);

	// Enabling static ORDER BY for a query
	// List<Person> findByLastnameOrderByFirstnameAsc(String lastname);

	// List<Person> findByLastnameOrderByFirstnameDesc(String lastname);

	// List<Person> findByAddressZipCode(ZipCode zipCode);

	// Page<User> findByLastname(String lastname, Pageable pageable);

	// Slice<User> findByLastname(String lastname, Pageable pageable);

	// List<User> findByLastname(String lastname, Sort sort);

	// List<User> findByLastname(String lastname, Pageable pageable);

	// User findFirstByOrderByLastnameAsc();

	// User findTopByOrderByAgeDesc();

	// Page<User> queryFirst10ByLastname(String lastname, Pageable pageable);

	// Slice<User> findTop3ByLastname(String lastname, Pageable pageable);

	// List<User> findFirst10ByLastname(String lastname, Sort sort);

	// List<User> findTop10ByLastname(String lastname, Pageable pageable);

	// @Query("select u from User u")
	// Stream<User> findAllByCustomQueryAndStream();

	// Stream<User> readAllByFirstnameNotNull();

	// @Query("select u from User u")
	// Stream<User> streamAllPaged(Pageable pageable);

}
