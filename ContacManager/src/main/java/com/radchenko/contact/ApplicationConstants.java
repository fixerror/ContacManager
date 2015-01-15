package com.radchenko.contact;

import java.util.Arrays;
import java.util.List;


/**
 * @author radchenko.as
 * @version 1.0
 */
public interface ApplicationConstants {
	int ADMIN_ROLE 			= 1;
	int TUTOR_ROLE          = 2;

	List<Integer> ROLES = Arrays.asList(new Integer[]{ADMIN_ROLE, TUTOR_ROLE});
}
