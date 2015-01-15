package com.radchenko.contact.dao.impl.hibernate;



import org.springframework.stereotype.Repository;

import com.radchenko.contact.dao.AccountRoleDao;
import com.radchenko.contact.entities.AccountRole;

/**
 * @author radchenko.as
 * @version 1.0
 */
@Repository("hiberanteAccountRoleDao")
public class AccountRoleDaoImpl extends AbstractEntityDao<AccountRole> implements AccountRoleDao {

	@Override
	protected Class<AccountRole> getEntityClass() {
		return AccountRole.class;
	}

}
