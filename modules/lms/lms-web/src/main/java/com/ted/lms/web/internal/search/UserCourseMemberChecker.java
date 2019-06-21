package com.ted.lms.web.internal.search;

import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.membershippolicy.SiteMembershipPolicyUtil;
import com.liferay.portal.kernel.service.UserGroupRoleLocalServiceUtil;
import javax.portlet.RenderResponse;

public class UserCourseMemberChecker extends EmptyOnClickRowChecker {

	public UserCourseMemberChecker(RenderResponse renderResponse, Group group, long roleId) {

		super(renderResponse);

		this.group = group;
		this.roleId = roleId;
	}

	@Override
	public boolean isChecked(Object obj) {
		User user = null;

		if (obj instanceof User) {
			user = (User)obj;
		}
		else if (obj instanceof Object[]) {
			user = (User)((Object[])obj)[0];
		}
		else {
			throw new IllegalArgumentException(obj + " is not a user");
		}

		try {
			return UserGroupRoleLocalServiceUtil.hasUserGroupRole(user.getUserId(), group.getGroupId(), roleId);
		}
		catch (Exception e) {
			_log.error(e, e);

			return false;
		}
	}

	@Override
	public boolean isDisabled(Object obj) {
		User user = (User)obj;

		try {
			if (isChecked(user)) {
				return true;
			}

			if (!SiteMembershipPolicyUtil.isMembershipAllowed(user.getUserId(), group.getGroupId())) {
				return true;
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return super.isDisabled(obj);
	}

	private static final Log _log = LogFactoryUtil.getLog(UserCourseMemberChecker.class);

	private final Group group;
	private final long roleId;
}
