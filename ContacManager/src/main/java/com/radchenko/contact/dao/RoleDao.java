package com.radchenko.contact.dao;

import com.radchenko.contact.entities.Role;

/**
 * @author radchenko.as
 * @version 1.0
 */
public interface RoleDao extends IEntityDao<Role> {
	
	Role getStudentRole();
}
