package com.ted.lms.service.persistence.impl;

import com.liferay.expando.kernel.model.ExpandoColumn;
import com.liferay.expando.kernel.service.ExpandoColumnLocalServiceUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.dao.orm.hibernate.SessionFactoryImpl;
import com.liferay.portal.kernel.bean.BeanLocator;
import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.CustomSQLParam;
import com.liferay.portal.kernel.dao.orm.ORMException;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.permission.InlineSQLHelperUtil;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.persistence.UserFinder;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.impl.UserImpl;
import com.liferay.portal.service.persistence.impl.UserFinderImpl;
import com.ted.lms.constants.CourseParams;
import com.ted.lms.constants.LMSRoleConstants;
import com.ted.lms.constants.StudentParams;
import com.ted.lms.model.Course;
import com.ted.lms.model.impl.CourseImpl;
import com.ted.lms.service.persistence.CourseFinder;
import com.ted.lms.util.LMSPrefsPropsValues;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = CourseFinder.class)
public class CourseFinderImpl extends CourseFinderBaseImpl implements CourseFinder{
	
	@Reference
	private CustomSQL customSQL;
	
	public static final String GET_DISTINCT_COURSE_GROUPS = 
			CourseFinder.class.getName() + ".getDistinctCourseGroups";
	
	public static final String FIND_BY_C =
		    CourseFinder.class.getName() +
		        ".findByC";
	public static final String COUNT_C =
		    CourseFinder.class.getName() +
		        ".countC";
	public static final String JOIN_BY_ASSET_ENTRY =
		    CourseFinder.class.getName() +
		        ".joinC_ByAssetEntry";
	public static final String JOIN_BY_ASSET_CATEGORY =
		    CourseFinder.class.getName() +
		        ".joinC_ByAssetCategory";
	public static final String JOIN_BY_ASSET_TAG =
		    CourseFinder.class.getName() +
		        ".joinC_ByAssetTag";
	public static final String JOIN_BY_TEMPLATES =
		    CourseFinder.class.getName() +
		        ".joinC_ByTemplates";
	public static final String JOIN_BY_RESOURCE_PERMISSION =
		    CourseFinder.class.getName() +
		        ".joinC_ByResourcePermission";
	public static final String JOIN_BY_RESOURCE_PERMISSION_VIEW =
			CourseFinder.class.getName() + 
				".joinC_ByResourcePermissionView";
	public static final String JOIN_BY_CUSTOM_ATTRIBUTE = 
			CourseFinder.class.getName() + 
				".joinCustomAttribute";
	public static final String WHERE_VISIBLE = 
			CourseFinder.class.getName() + 
				".whereVisible";
	public static final String WHERE_TYPE = 
			CourseFinder.class.getName() + ".whereType";
	public static final String WHERE_TITLE_DESCRIPTION = 
			CourseFinder.class.getName() + ".whereTitleDescription";
	public static final String WHERE_GROUP_ID = 
			CourseFinder.class.getName() + ".whereGroupId";
	public static final String WHERE_STATUS = 
			CourseFinder.class.getName() + ".whereStatus";
	public static final String WHERE_PARENT_COURSE_ID = 
			CourseFinder.class.getName() + ".whereParentCourseId";
	public static final String FIND_STUDENTS = 
			CourseFinder.class.getName() +
				".findStudents";
	public static final String COUNT_STUDENTS = 
			CourseFinder.class.getName() +
				".countStudents";
	public static final String WHERE_SCREEN_NAME =
		    CourseFinder.class.getName() +
		        ".whereScreenName";
	public static final String WHERE_FIRST_NAME =
		    CourseFinder.class.getName() +
		        ".whereFirstName";
	public static final String WHERE_LAST_NAME =
		    CourseFinder.class.getName() +
		        ".whereLastName";
	public static final String WHERE_EMAIL_ADDRESS =
		    CourseFinder.class.getName() +
		        ".whereEmailAddress";	
	public static final String WHERE_USER_SEARCH = 
			CourseFinder.class.getName() + 
				".whereUserSearch";
	public static final String WHERE_USER_STATUS = 
			CourseFinder.class.getName() + 
				".whereUserStatus";
	public static final String INNER_JOIN_TEAM = 
			CourseFinder.class.getName() + ".innerJoinTeam";
	public static final String JOIN_BY_FINISHED = 
			CourseFinder.class.getName() + ".innerFinished";
	
	public static final String FIND_CHILD_REGISTRED_USER = 
			CourseFinder.class.getName() + ".findChildRegistredUser";
	
	private static final Log log = LogFactoryUtil.getLog(CourseFinderImpl.class);
	
	public List<Course> findByKeywords(long companyId, String freeText, String languageId, int[] status, long parentCourseId, long groupId, LinkedHashMap<String, Object> params, 
			int start, int end, OrderByComparator<Course> obc){
		return doFindByC(companyId, freeText, freeText, languageId, status, parentCourseId, groupId, params, false, start, end, obc, false);
	}
	
	public List<Course> filterByKeywords(long companyId, String freeText, String languageId, int[] status, long parentCourseId, long groupId, LinkedHashMap<String, Object> params, 
			int start, int end, OrderByComparator<Course> obc){
		return doFindByC(companyId, freeText, freeText, languageId, status, parentCourseId, groupId, params, false, start, end, obc, true);
	}
	
	public List<Course> filterByC(long companyId, String title, String description, String languageId, int[] status, long parentCourseId, long groupId, 
			LinkedHashMap<String, Object> params, boolean andOperator, int start, int end, OrderByComparator<Course> obc){
		return doFindByC(companyId, title, description, languageId, status, parentCourseId, groupId, params, false, start, end, obc,true);
	}
	
	public List<Course> findByC(long companyId, String title, String description, String languageId, int[] status, long parentCourseId, long groupId, 
			LinkedHashMap<String, Object> params, boolean andOperator, int start, int end, OrderByComparator<Course> obc){
		return doFindByC(companyId, title, description, languageId, status, parentCourseId, groupId, params, false, start, end, obc,false);
	}
	
	public List<Group> getDistinctCourseGroups(long companyId){
		
		Session session = null;
		List<Group> listGroups = new ArrayList<Group>();
		
		try{
			session = openSession();
			
			String sql = customSQL.get(getClass(), GET_DISTINCT_COURSE_GROUPS);
			
			if(log.isDebugEnabled()){
				log.debug("sql: " + sql);
			}
			
			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar("groupId", Type.LONG);
			
			QueryPos qPos = QueryPos.getInstance(q);			
			qPos.add(companyId);	
			qPos.add(WorkflowConstants.STATUS_IN_TRASH);
			
			Iterator<Long> listGroupIds = q.iterate();
			
			while(listGroupIds.hasNext()) {
				listGroups.add(GroupLocalServiceUtil.fetchGroup(listGroupIds.next()));
			}
				
		} catch (Exception e) {
	       e.printStackTrace();
	    } finally {
	        closeSession(session);
	    }
	
		return listGroups;
	}
	
	@SuppressWarnings("unchecked")
	public List<Course> doFindByC(long companyId, String title, String description, String languageId, int[] status, long parentCourseId, long groupId, 
			LinkedHashMap<String, Object> params, boolean andOperator, int start, int end, OrderByComparator<Course> obc, boolean inlineSQLHelper){
		
		Session session = null;
		List<Course> listCourse = null;
		
		if(log.isDebugEnabled()){
			log.debug("CourseFinderImpl:findByC_T_D_S_PC_G");
			log.debug("companyId: " + companyId);
			log.debug("title: " + title);
			log.debug("description: " + description);
			log.debug("languageId: " + languageId);
			log.debug("status: " + status);
			log.debug("parentCourseId: " + parentCourseId);
			log.debug("groupId: " + groupId);
			log.debug("andOperator: " + andOperator);
			log.debug("start: " + start);
			log.debug("end: " + end);
		}
		
		
		try{
			
			if(Validator.isNotNull(title))
				title = "%" + title + "%";
			if(Validator.isNotNull(description))
				description = "%" + description + "%";
			
			if (params == null) {
				params = new LinkedHashMap<>();
			}
			
			if(Validator.isNotNull(title) || Validator.isNotNull(description)){
				String[] titleDescription = {title, description};
				params.put(PARAM_TITLE_DESCRIPTION, titleDescription);
			}
			
			log.debug("status: " + status);
			if(status != null) {
				log.debug("status length: " + status.length);
				if(status.length > 0) {
					log.debug("status 0: " + status[0]);
				}
			}
			
			if(status != null && (status.length > 1 || (status.length == 1 && status[0] != WorkflowConstants.STATUS_ANY))){
				for(int i: status) {
					log.debug("status valores: " + i);
				}
				params.put(PARAM_STATUS, status);
			}
			
			if(!params.containsKey(CourseParams.PARAM_SEARCH_PARENT_AND_CHILD_COURSES)){
				params.put(PARAM_PARENT_COURSE_ID, parentCourseId);
			}
			
			if(groupId > 0){
				params.put(PARAM_GROUP_ID, groupId);
			}
			
			session = openSession();
			
			String sql = customSQL.get(getClass(), FIND_BY_C);
			log.debug("FIND_BY_C: " + FIND_BY_C);
			log.debug("sql: " + sql);
			log.debug("getClass(): " + getClass());
			
			if (inlineSQLHelper) {
				long[] groupIds = null;
				if (groupId > 0) {
					groupIds = new long[]{groupId};
				}else {
					groupIds = new long[0];
				}
				
				sql = InlineSQLHelperUtil.replacePermissionCheck(
						sql, Course.class.getName(), "Lms_Course.courseId",
						groupIds);
			}
			
			StringBundler sb = new StringBundler();

			sb.append(StringPool.OPEN_PARENTHESIS);
			sb.append(replaceJoinAndWhere(sql, params, languageId, companyId));
			sb.append(StringPool.CLOSE_PARENTHESIS);

			String orderBy = "";
			if (obc != null) {
				log.debug("obc: " + obc.getOrderBy());
				if(Validator.isNull(obc.getOrderBy()) || "title".equals(obc.getOrderBy())){
					log.debug("obc desc: " + obc.isAscending());
					orderBy = " ORDER BY IF (ExtractValue(lms_Course.title, '//Title[@language-id=\"[$LANGUAGE$]\"]' )='', ExtractValue(lms_Course.title,  '//root[@default-locale]//Title' ), ExtractValue(lms_Course.title, '//Title[@language-id=\"[$LANGUAGE$]\"]' )) ";
					if(!obc.isAscending()) orderBy += " DESC ";
					orderBy = StringUtil.replace(orderBy, "[$LANGUAGE$]", languageId);
				}else{
					log.debug("obc: " + obc.toString());
					orderBy = "ORDER BY " + obc.toString();
				}
			}else{
				log.debug("obc null ");
				orderBy = " ORDER BY IF (ExtractValue(lms_Course.title, '//Title[@language-id=\"[$LANGUAGE$]\"]' )='', ExtractValue(lms_Course.title,  '//root[@default-locale]//Title' ), ExtractValue(lms_Course.title, '//Title[@language-id=\"[$LANGUAGE$]\"]' )) ";
				orderBy = StringUtil.replace(orderBy, "[$LANGUAGE$]", languageId);
			}
			log.debug("order by");
			sb.append(orderBy);

			sql = sb.toString();
			
			sql = customSQL.replaceAndOperator(sql, andOperator);
			
			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("Lms_Course", CourseImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			setJoin(qPos, params);
			
			qPos.add(companyId);
			qPos.add(WorkflowConstants.STATUS_IN_TRASH);
			
			if(log.isDebugEnabled()){
				log.debug("sql: " + sql);
			}
			
			listCourse = (List<Course>) QueryUtil.list(
					q, getDialect(), start, end);
			
		} catch (Exception e) {
	       e.printStackTrace();
	    } finally {
	        closeSession(session);
	    }
	
	    return listCourse;
	}
	
	protected String replaceJoinAndWhere(
		String sql, LinkedHashMap<String, Object> params, String languageId, long companyId) throws PortalException, SystemException {

		sql = StringUtil.replace(sql, "[$JOIN$]", getJoin(params, companyId));
		sql = StringUtil.replace(sql, "[$WHERE$]", getWhere(params, languageId));

		return sql;
	}
	
	protected String getJoin(LinkedHashMap<String, Object> params, long companyId) throws PortalException, SystemException {
		if ((params == null) || params.isEmpty()) {
			return StringPool.BLANK;
		}
		
		StringBundler sb = new StringBundler(params.size());
		
		if(params.containsKey(CourseParams.PARAM_CATEGORIES) || params.containsKey(CourseParams.PARAM_TAGS) ||
				params.containsKey(CourseParams.PARAM_AND_CATEGORIES) || params.containsKey(CourseParams.PARAM_AND_TAGS)
				|| params.containsKey(CourseParams.PARAM_VISIBLE)){
			String join = customSQL.get(getClass(), JOIN_BY_ASSET_ENTRY);
			long classNameId = ClassNameLocalServiceUtil.getClassNameId(Course.class.getName());
			join = StringUtil.replace(join, "[$CLASSNAMEID$]", String.valueOf(classNameId));
			sb.append(join);
		}

		Iterator<Map.Entry<String, Object>> itr = params.entrySet().iterator();

		while (itr.hasNext()) {
			Map.Entry<String, Object> entry = itr.next();

			String key = entry.getKey();

			Object value = entry.getValue();

			sb.append(getJoin(key, value, companyId));

		}

		return sb.toString();
	}
	
	protected String replaceJoinAndWhereStudents(String sql, LinkedHashMap<String, Object> params) {

			sql = StringUtil.replace(sql, "[$JOIN$]", getJoinStudents(params));
			sql = StringUtil.replace(sql, "[$WHERE$]", getWhereStudents(params));

			return sql;
		}
	
	protected String getJoinStudents(LinkedHashMap<String, Object> params) {
		if ((params == null) || params.isEmpty()) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(params.size());

		for (Map.Entry<String, Object> entry : params.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();

			if (value != null) {
				sb.append(getJoinStudents(key, value));
			}
		}

		return sb.toString();
	}
	
	protected String getJoinStudents(String key, Object value) {
		String join = StringPool.BLANK;
		
		if (key.equals(StudentParams.PARAM_FINISHED)) {
			join = customSQL.get(getClass(),JOIN_BY_FINISHED);
		} else if (value instanceof CustomSQLParam) {
			CustomSQLParam customSQLParam = (CustomSQLParam)value;

			join = customSQLParam.getSQL();
		}

		if (Validator.isNotNull(join)) {
			int pos = join.indexOf("WHERE");

			if (pos != -1) {
				join = join.substring(0, pos);
			}
		}

		return join;
	}
	
	protected String getWhereStudents(LinkedHashMap<String, Object> params) {
		if ((params == null) || params.isEmpty()) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(params.size());

		for (Map.Entry<String, Object> entry : params.entrySet()) {
			String key = entry.getKey();

			Object value = entry.getValue();

			if (value != null) {
				sb.append(getWhereStudents(key, value));
			}
		}

		return sb.toString();
	}
	
	protected String getWhereStudents(String key, Object value) {
		String join = StringPool.BLANK;
		if (value instanceof CustomSQLParam) {
			CustomSQLParam customSQLParam = (CustomSQLParam)value;

			join = customSQLParam.getSQL();
		}

		if (Validator.isNotNull(join)) {
			int pos = join.indexOf("WHERE");

			if (pos != -1) {
				join = join.substring(pos + 5).concat(" AND ");
			}
			else {
				join = StringPool.BLANK;
			}
		}
		
		return join;
	}
	
	protected String getJoin(String key, Object value, long companyId) throws PortalException, SystemException {
		String join = StringPool.BLANK;
		
		log.debug("join: " + key + " - " + value);

		if (key.equals(CourseParams.PARAM_CATEGORIES)) {
			join = customSQL.get(getClass(),JOIN_BY_ASSET_CATEGORY);
			join = StringUtil.replace(join, "[$i$]", "");
			if (value instanceof Long){
				Long category = (Long)value;
				join = StringUtil.replace(join, "[$CATEGORYIDS$]", String.valueOf(category));
			}else if(value instanceof long[]){
				long[] categories = (long[])value;
				if(categories.length == 1){
					join = StringUtil.replace(join, "[$CATEGORYIDS$]", String.valueOf(categories[0]));
				}else{
					String inCategories = StringPool.BLANK;
					for(long category: categories){
						inCategories += category + ",";
					}
					if(inCategories.length() > 0) inCategories = inCategories.substring(0, inCategories.length()-1);
					join = StringUtil.replace(join, "[$CATEGORYIDS$]", inCategories);
				}
			}	
		}
		else if (key.equals(CourseParams.PARAM_TAGS)) {
			join = customSQL.get(getClass(),JOIN_BY_ASSET_TAG);
			join = StringUtil.replace(join, "[$i$]", "");
			if (value instanceof Long){
				Long tag = (Long)value;
				join = StringUtil.replace(join, "[$TAGIDS$]", String.valueOf(tag));
			}else if(value instanceof long[]){
				long[] tags = (long[])value;
				if(tags.length == 1){
					join = StringUtil.replace(join, "[$TAGIDS$]", String.valueOf(tags[0]));
				}else{
					String inTags = StringPool.BLANK;
					for(long tag: tags){
						inTags += tag + ",";
					}
					if(inTags.length() > 0) inTags = inTags.substring(0, inTags.length()-1);
					join = StringUtil.replace(join, "[$TAGIDS$]", inTags);
				}
			}	
		}
		else if (key.equals(CourseParams.PARAM_TEMPLATES)) {
			join = customSQL.get(getClass(),JOIN_BY_TEMPLATES);
		}
		else if (key.equals(CourseParams.PARAM_CUSTOM_ATTRIBUTE)) {
			join = customSQL.get(getClass(),JOIN_BY_CUSTOM_ATTRIBUTE);
		}
		else if(key.equals(CourseParams.PARAM_AND_TAGS)){
			if (value instanceof Long){
				join += customSQL.get(getClass(),JOIN_BY_ASSET_TAG);
				join = StringUtil.replace(join, "[$i$]", "");
				Long tag = (Long)value;
				join = StringUtil.replace(join, "[$TAGIDS$]", String.valueOf(tag));
			}else if(value instanceof long[]){
				long[] tags = (long[])value;
				for(int i = 0; i < tags.length; i++){
					join += customSQL.get(getClass(),JOIN_BY_ASSET_TAG);
					join = StringUtil.replace(join, "[$i$]", String.valueOf(i));
					join = StringUtil.replace(join, "[$TAGIDS$]", String.valueOf(tags[i]));
				}
			}
		}
		else if(key.equals(CourseParams.PARAM_AND_CATEGORIES)){
			if (value instanceof Long){
				join += customSQL.get(getClass(),JOIN_BY_ASSET_CATEGORY);
				join = StringUtil.replace(join, "[$i$]", "");
			}else if(value instanceof long[]){	
				long[] categories = (long[])value;
				for(int i = 0; i < categories.length; i++){
					join += customSQL.get(getClass(),JOIN_BY_ASSET_CATEGORY);
					join = StringUtil.replace(join, "[$i$]", String.valueOf(i));
					join = StringUtil.replace(join, "[$CATEGORYIDS$]", String.valueOf(categories[i]));
				}
			}
		}
		else if (value instanceof CustomSQLParam) {
			CustomSQLParam customSQLParam = (CustomSQLParam)value;

			join = customSQLParam.getSQL();
		}

		if (Validator.isNotNull(join)) {
			int pos = join.indexOf("WHERE");

			if (pos != -1) {
				join = join.substring(0, pos);
			}
		}

		return join;
	}
	
	protected String getWhere(LinkedHashMap<String, Object> params, String languageId) {
		if ((params == null) || params.isEmpty()) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(params.size());

		Iterator<Map.Entry<String, Object>> itr = params.entrySet().iterator();

		while (itr.hasNext()) {
			Map.Entry<String, Object> entry = itr.next();

			String key = entry.getKey();

			Object value = entry.getValue();

			sb.append(getWhere(key, value, languageId));
		}

		return sb.toString();
	}

	protected String getWhere(String key, Object value, String languageId) {
		String join = StringPool.BLANK;
		
		log.debug("where: " + key + " - " + value);

		if (key.equals(CourseParams.PARAM_TEMPLATES)) {
			join = customSQL.get(getClass(),JOIN_BY_TEMPLATES);
			if(value instanceof String){
				join = StringUtil.replace(join, "IN ([$TEMPLATES$])", "= " + value);
			}else if(value instanceof String[]){
				String[] ids = (String[])value;
				String idsPos = StringPool.BLANK;
				for(int i = 0; i < ids.length; i++){
					idsPos += "?,";
				}
				if(idsPos.length() > 0) idsPos = idsPos.substring(0, idsPos.length()-1);
				join = StringUtil.replace(join, "[$TEMPLATES$]", idsPos);
			}
		}
		else if (key.equals(CourseParams.PARAM_PERMISSIONS_ADMIN)) {
			join = customSQL.get(getClass(),JOIN_BY_RESOURCE_PERMISSION);
		}else if(key.equals(CourseParams.PARAM_PERMISSIONS_VIEW)){
			join = customSQL.get(getClass(),JOIN_BY_RESOURCE_PERMISSION_VIEW);
		}
		else if (key.equals(CourseParams.PARAM_CUSTOM_ATTRIBUTE)) {
			join = customSQL.get(getClass(),JOIN_BY_CUSTOM_ATTRIBUTE);
		}
		else if (key.equals(CourseParams.PARAM_VISIBLE)) {
			join = customSQL.get(getClass(),WHERE_VISIBLE);
		}
		else if (key.equals(CourseParams.PARAM_TYPE)) {
			join = customSQL.get(getClass(),WHERE_TYPE);
			if(value instanceof Integer){
				join = StringUtil.replace(join, "IN [$TYPE$]", "= ?");
			}else if(value instanceof int[]){
				int[] types = (int[])value;
				String typePos = StringPool.BLANK;
				for(int i = 0; i < types.length; i++) {
					typePos += "?,";
				}
				if(typePos.length() > 0) typePos = typePos.substring(0, typePos.length()-1);
				join = StringUtil.replace(join, "[$TYPE$]", typePos);
			}		
		}
		else if (key.equals(PARAM_TITLE_DESCRIPTION)) {
			join = customSQL.get(getClass(),WHERE_TITLE_DESCRIPTION);
			join = StringUtil.replace(join, "[$LANGUAGE$]", languageId);
		}
		else if (key.equals(PARAM_GROUP_ID)) {
			join = customSQL.get(getClass(),WHERE_GROUP_ID);
		}
		else if (key.equals(PARAM_STATUS)) {
			join = customSQL.get(getClass(),WHERE_STATUS);
			int[] valueArray = (int[])value;
			String status = "?";
			for(int i = 1; i < valueArray.length; i++) {
				status += ",?"; 
			}
			join = StringUtil.replace(join, "[$STATUS$]", status);
		}
		else if (key.equals(PARAM_PARENT_COURSE_ID)) {
			join = customSQL.get(getClass(),WHERE_PARENT_COURSE_ID);
		}
		else if (value instanceof CustomSQLParam) {
			CustomSQLParam customSQLParam = (CustomSQLParam)value;

			join = customSQLParam.getSQL();
		}

		if (Validator.isNotNull(join)) {
			int pos = join.indexOf("WHERE");

			if (pos != -1) {
				join = join.substring(pos + 5, join.length()).concat(" AND ");
			}
			else {
				join = StringPool.BLANK;
			}
		}

		return join;
	}
	
	protected void setJoin(
		QueryPos qPos, LinkedHashMap<String, Object> params) throws PortalException, SystemException {

		if (params == null) {
			return;
		}
		

		for (Map.Entry<String, Object> entry : params.entrySet()) {
			String key = entry.getKey();

			Object value = entry.getValue();
						
			log.debug("setJoin: " + key + " - " + value);
			
			if(key.equals(CourseParams.PARAM_CUSTOM_ATTRIBUTE)){
				Object[] paramsValue = (Object[]) value;
				Long columnId = (Long)paramsValue[0];
				String dataValue = (String)paramsValue[1];
				dataValue = "%" + dataValue + "%";
				ExpandoColumn expandoColumn = ExpandoColumnLocalServiceUtil.getColumn(columnId);
				qPos.add(dataValue);
				log.debug("qPos: CourseParams.PARAM_CUSTOM_ATTRIBUTE): " + dataValue);
				qPos.add(expandoColumn.getTableId());
				log.debug("qPos: CourseParams.PARAM_CUSTOM_ATTRIBUTE): " + expandoColumn.getTableId());
				qPos.add(columnId);
				log.debug("qPos: CourseParams.PARAM_CUSTOM_ATTRIBUTE): " + columnId);
				log.debug("*****QPOS****** Custom attribute: " + dataValue);
				log.debug("*****QPOS****** Custom attribute: " + expandoColumn.getTableId());
				log.debug("*****QPOS****** Custom attribute: " + columnId);
			}else if(key.equals(PARAM_PARENT_COURSE_ID)){
				Long valueLong = (Long)value;
				qPos.add(valueLong);
				qPos.add(valueLong);
				log.debug("*****QPOS****** Parent Course Id: " + valueLong);
				log.debug("*****QPOS****** Parent Course Id: " + valueLong);
			}else if(key.equals(PARAM_TITLE_DESCRIPTION)){
				String[] valueArray = (String[])value;
				
				for (String element : valueArray) {
					if (Validator.isNotNull(element)) {
						qPos.add(element);
						qPos.add(element);
						log.debug("*****QPOS****** " + key + ": " + element);
						log.debug("*****QPOS****** " + key + ": " + element);
					}
				}
			
			}else if(!key.equals(CourseParams.PARAM_CATEGORIES) && !key.equals(CourseParams.PARAM_TAGS) &&
					!key.equals(CourseParams.PARAM_AND_TAGS) && !key.equals(CourseParams.PARAM_AND_CATEGORIES) &&
					!key.equals(CourseParams.PARAM_PERMISSIONS_ADMIN) && !key.equals(CourseParams.PARAM_SEARCH_PARENT_AND_CHILD_COURSES)
					&& !key.equals(CourseParams.PARAM_PERMISSIONS_VIEW)){
				if (value instanceof Long) {
					Long valueLong = (Long)value;
	
					if (valueLong != null) {
						qPos.add(valueLong);
						log.debug("*****QPOS****** " + key + ": " + valueLong);
					}
				}
				else if (value instanceof Long[]) {
					Long[] valueArray = (Long[])value;
	
					for (Long element : valueArray) {
						if (element != null) {
							qPos.add(element);
							log.debug("*****QPOS****** " + key + ": " + element);
						}
					}
				}
				else if (value instanceof long[]) {
					long[] valueArray = (long[])value;
	
					for (long element : valueArray) {
						qPos.add(element);
						log.debug("*****QPOS****** " + key + ": " + element);
					}
				}
				else if (value instanceof int[]) {
					int[] valueArray = (int[])value;
	
					for (int element : valueArray) {
						qPos.add(element);
						log.debug("*****QPOS****** " + key + ": " + element);
					}
				}
				else if (value instanceof Integer) {
					Integer valueInteger = (Integer)value;
					
					if(valueInteger != null) {
						qPos.add(valueInteger);
						log.debug("*****QPOS****** " + key + ": " + valueInteger);
					}
				}
				else if (value instanceof Long[][]) {
					Long[][] valueDoubleArray = (Long[][])value;
	
					for (Long[] valueArray : valueDoubleArray) {
						for (Long valueLong : valueArray) {
							qPos.add(valueLong);
							log.debug("*****QPOS****** " + key + ": " + valueLong);
						}
					}
				}
				else if (value instanceof String) {
					String valueString = (String)value;
	
					if (Validator.isNotNull(valueString)) {
						qPos.add(valueString);
						log.debug("*****QPOS****** " + key + ": " + valueString);
					}
				}
				else if (value instanceof String[]) {
					String[] valueArray = (String[])value;
	
					for (String element : valueArray) {
						if (Validator.isNotNull(element)) {
							qPos.add(element);
							log.debug("*****QPOS****** " + key + ": " + element);
						}
					}
				}
				else if (value instanceof Boolean) {
					Boolean valueBoolean = (Boolean)value;
	
					if (Validator.isNotNull(valueBoolean)) {
						qPos.add(valueBoolean);
						log.debug("*****QPOS****** " + key + ": " + valueBoolean);
					}
				}
				else if (value instanceof CustomSQLParam) {
					CustomSQLParam customSQLParam = (CustomSQLParam)value;
	
					customSQLParam.process(qPos);
				}
			}
		}
	}

	public int countByKeywords(long companyId, String freeText, String languageId, int[] status, long parentCourseId, long groupId, LinkedHashMap<String, Object> params, boolean inlineSQLHelper){
		return doCountByC(companyId, freeText, freeText, languageId, status, parentCourseId, groupId, params, false, false);
	}
	
	public int filterCountByKeywords(long companyId, String freeText, String languageId, int[] status, long parentCourseId, long groupId, LinkedHashMap<String, Object> params, boolean inlineSQLHelper){
		return doCountByC(companyId, freeText, freeText, languageId, status, parentCourseId, groupId, params, false, true);
	}
	
	public int countByC(long companyId, String title, String description, String languageId, int[] status, long parentCourseId, long groupId, 
			LinkedHashMap<String, Object> params, boolean andOperator){
		return doCountByC(companyId, title, description, languageId, status, parentCourseId, groupId, params, andOperator, false);
	}
	
	public int filterCountByC(long companyId, String title, String description, String languageId, int[] status, long parentCourseId, long groupId, 
			LinkedHashMap<String, Object> params, boolean andOperator){
		return doCountByC(companyId, title, description, languageId, status, parentCourseId, groupId, params, andOperator, true);
	}
	
	public int doCountByC(long companyId, String title, String description, String languageId, int[] status, long parentCourseId, long groupId, 
			LinkedHashMap<String, Object> params, boolean andOperator, boolean inlineSQLHelper){
		Session session = null;
		
		int countValue = 0;
		
		try{
			
			if(Validator.isNotNull(title))
				title = "%" + title + "%";
			if(Validator.isNotNull(description))
				description = "%" + description + "%";
			
			if (params == null) {
				params = new LinkedHashMap<>();
			}
			
			if(Validator.isNotNull(title) || Validator.isNotNull(description)){
				String[] titleDescription = {title, description};
				params.put(PARAM_TITLE_DESCRIPTION, titleDescription);
			}
			
			if(status != null && status.length > 0 && (status.length == 1 && status[0] != WorkflowConstants.STATUS_ANY)){
				params.put(PARAM_STATUS, status);
			}
			
			if(!params.containsKey(CourseParams.PARAM_SEARCH_PARENT_AND_CHILD_COURSES)){
				params.put(PARAM_PARENT_COURSE_ID, parentCourseId);
			}
			
			if(groupId > 0){
				params.put(PARAM_GROUP_ID, groupId);
			}
			
			session = openSession();
			
			String sql = customSQL.get(getClass(),COUNT_C);
			
			if (inlineSQLHelper) {
				long[] groupIds = null;
				if (groupId > 0) {
					groupIds = new long[]{groupId};
				}else {
					groupIds = new long[0];
				}
				
				sql = InlineSQLHelperUtil.replacePermissionCheck(
						sql, Course.class.getName(), "Lms_Course.courseId",
						groupIds);
			}
			
			StringBundler sb = new StringBundler();

			sb.append(StringPool.OPEN_PARENTHESIS);
			sb.append(replaceJoinAndWhere(sql, params, languageId, companyId));
			sb.append(StringPool.CLOSE_PARENTHESIS);

			sql = sb.toString();
			
			sql = customSQL.replaceAndOperator(sql, andOperator);
			
			SQLQuery q = session.createSQLQuery(sql);

			QueryPos qPos = QueryPos.getInstance(q);

			setJoin(qPos, params);
			
			qPos.add(companyId);
			qPos.add(WorkflowConstants.STATUS_IN_TRASH);
			
			log.debug("qPos: companyId: " + companyId);
			
			if(log.isDebugEnabled()){
				log.debug("sql: " + sql);
				log.debug("parentCourseId: " + parentCourseId);
				log.debug("status: " + status);
				log.debug("companyId: " + companyId);
				log.debug("groupId: " + groupId);
				log.debug("title: " + title);
				log.debug("description: " + description);
				log.debug("andOperator: " + andOperator);
			}

			Iterator<Object> itr = q.iterate();

			if (itr.hasNext()) {
				Object count = itr.next();
				
				if (count != null) {
					if(count instanceof Long){
						countValue = ((Long)count).intValue();
					}else if(count instanceof BigInteger){
						countValue = ((BigInteger)count).intValue();
					}else if(count instanceof Integer){
						countValue = (Integer)count;
					}
					
				}
			}
			
		} catch (Exception e) {
	       e.printStackTrace();
	    } finally {
	        closeSession(session);
	    }
	
	    return countValue;
	}
	
	@SuppressWarnings("unchecked")
	public List<Course> findChildRegistredUser(long parentCourseId, long userId){
		
		Session session = null;
		List<Course> listCourse = null;
		
		if(log.isDebugEnabled()){
			log.debug("CourseFinderImpl:findChildRegistredUser");
			log.debug("parentCourseId: " + parentCourseId);
			log.debug("userId: " + userId);
		}
		
		try{
			
			session = openSession();
			
			String sql = customSQL.get(getClass(), FIND_CHILD_REGISTRED_USER);
			log.debug("sql: " + sql);
			
			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("Lms_Course", CourseImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);
			
			qPos.add(userId);
			qPos.add(parentCourseId);
			
			if(log.isDebugEnabled()){
				log.debug("sql: " + sql);
			}
			
			listCourse = (List<Course>) QueryUtil.list(
					q, getDialect(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
			
		} catch (Exception e) {
	       e.printStackTrace();
	    } finally {
	        closeSession(session);
	    }
	
	    return listCourse;
	}
	
	private List<User> findUsers(long courseId, long companyId, String screenName, String firstName, String lastName, String emailAddress, int status, 
			long[] teamIds,LinkedHashMap<String,Object> params, long roleId, boolean andOperator, int start, int end, OrderByComparator obc){
		Session session = null;
		
		try{
			
			// Para la query es necesario si no es null o vacío que añade los porcentajes, y si es vacío ponerlo a null
			if(log.isDebugEnabled()){
				log.debug("ScreenName:"+screenName);
				log.debug("firstName:"+firstName);
				log.debug("lastName:"+lastName);
				log.debug("emailAddress:"+emailAddress);
				log.debug("start: " + start);
				log.debug("end: " + end);
				log.debug("order: " + (obc != null ? obc.toString() : "null"));
			}
			
			session = openSessionUser();
			
			String sql = customSQL.get(getClass(), FIND_STUDENTS);
			
			sql = replaceJoinWhereUser(sql, screenName, firstName, lastName, emailAddress, status, teamIds, params, andOperator);
			
			sql = customSQL.replaceAndOperator(sql, andOperator);
			
			sql = replaceOrderUser(sql, obc, companyId);
			
			if(start >= 0 && end >= 0){
				sql += " LIMIT " + start + ", " + (end-start);
			}
			
			if(log.isDebugEnabled()) log.debug("sql: " + sql);
			
			SQLQuery q = session.createSQLQuery(sql);
			q.addEntity("User_",UserImpl.class);
			
			//Obtenemos el rol de editor del curso y profesor
			
			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(roleId);
			setParametersStudents(params, qPos);
			qPos.add(courseId);

			if(status != WorkflowConstants.STATUS_ANY){
				qPos.add(status);
			}
			
			qPos = replaceQPosJoinWhereUser(qPos, screenName, firstName, lastName, emailAddress);
					
			
			if(log.isDebugEnabled()){
				log.debug("courseId: " + courseId);
				log.debug("firstName: " + firstName);
				log.debug("lastName: " + lastName);
				log.debug("screenName: " + screenName);
				log.debug("emailAddress: " + emailAddress);	
			}
			
			List<User> listUsers = (List<User>) q.list();
			return listUsers;
			
		} catch (Exception e) {
	       e.printStackTrace();
	    } finally {
	    	closeSessionUser(session);
	    }
	
	    return new ArrayList<User>();
	}
	
	public List<User> findStudents(long courseId, long companyId, String screenName, String firstName, String lastName, String emailAddress, int status, 
			long[] teamIds,LinkedHashMap<String,Object> params, boolean andOperator, int start, int end, OrderByComparator obc) throws PortalException{
		long roleId = RoleLocalServiceUtil.getRole(companyId, LMSRoleConstants.STUDENT).getRoleId();
		return findUsers(courseId, companyId, screenName, firstName, lastName, emailAddress, status, teamIds, params, roleId, andOperator, start, end, obc);
	}
	
	public List<User> findTeachers(long courseId, long companyId, String screenName, String firstName, String lastName, String emailAddress, int status, 
			long[] teamIds,LinkedHashMap<String,Object> params, boolean andOperator, int start, int end, OrderByComparator obc) throws PortalException{
		long roleId = RoleLocalServiceUtil.getRole(companyId, LMSRoleConstants.TEACHER_ROLE).getRoleId();
		return findUsers(courseId, companyId, screenName, firstName, lastName, emailAddress, status, teamIds, params, roleId, andOperator, start, end, obc);
	}
	
	private void setParametersStudents(LinkedHashMap<String, Object> params, QueryPos qPos) {
		if(params != null) {
			for (Map.Entry<String, Object> entry : params.entrySet()) {
				Object value = entry.getValue();
				
				if (value != null) {
					if (value instanceof CustomSQLParam) {
						CustomSQLParam customSQLParam = (CustomSQLParam)value;
	
						customSQLParam.process(qPos);
					}
				}
			}
		}
	}

	public int countStudents(long courseId, long companyId, String screenName, String firstName, String lastName, String emailAddress, int status, long[] teamIds,
			LinkedHashMap<String,Object> params, boolean andOperator){
		Session session = null;
		try{
			
			if(log.isDebugEnabled()){
				log.debug("ScreenName:"+screenName);
				log.debug("firstName:"+firstName);
				log.debug("lastName:"+lastName);
				log.debug("emailAddress:"+emailAddress);
			}
						
			session = openSession();
			
			String sql = customSQL.get(getClass(), COUNT_STUDENTS);
			
			sql = replaceJoinWhereUser(sql, screenName, firstName, lastName, emailAddress, status, teamIds, params, andOperator);
			
			sql = customSQL.replaceAndOperator(sql, andOperator);
			
			log.debug("sql: " + sql);
			
			SQLQuery q = session.createSQLQuery(sql);
			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);
			
			QueryPos qPos = QueryPos.getInstance(q);
			qPos.add(RoleLocalServiceUtil.getRole(companyId, LMSRoleConstants.STUDENT).getRoleId());
			setParametersStudents(params, qPos);
			qPos.add(courseId);
			if(status != WorkflowConstants.STATUS_ANY){
				qPos.add(status);
			}
			
			qPos = replaceQPosJoinWhereUser(qPos, screenName, firstName, lastName, emailAddress);
			
			Iterator<Long> itr = q.iterate();

			if (itr.hasNext()) {
				Long count = itr.next();

				if (count != null) {
					return count.intValue();
				}
			}
			
		} catch (Exception e) {
	       e.printStackTrace();
	    } finally {
	    	closeSession(session);
	    }
	
	    return 0;
	}
	
	private String replaceJoinWhereUser(String sql, String screenName, String firstName, String lastName, String emailAddress, int status, long[] teamIds, LinkedHashMap<String, Object> params, boolean andOperator){
		
		sql = replaceJoinAndWhereStudents(sql, params);
		log.debug("sql replaceJoinWhereUser: " + sql);
		
		boolean whereClause = false;
		if(teamIds != null && teamIds.length > 0){
			sql = StringUtil.replace(sql, "[$JOINTEAM$]", customSQL.get(getClass(), INNER_JOIN_TEAM));
			String teams = "";
			for(int i = 0; i < teamIds.length;i++){
				teams += teamIds[i] + ",";
			}
			if(teams.length() > 0){
				teams = teams.substring(0, teams.length()-1);
			}
			sql = StringUtil.replace(sql, "[$TEAMIDS$]", teams);
		}else{
			sql = sql.replace("[$JOINTEAM$]", "");
		}
		
		String sqlWhere = customSQL.get(getClass(), WHERE_USER_SEARCH);
		if(Validator.isNotNull(screenName)){
			sqlWhere = sqlWhere.replace("[$WHERESCREENNAME$]", customSQL.get(getClass(), WHERE_SCREEN_NAME));
			whereClause=true;
		}else{
			sqlWhere = sqlWhere.replace("[$WHERESCREENNAME$]", "");
		}
		if(Validator.isNotNull(firstName)){
			sqlWhere = sqlWhere.replace("[$WHEREFIRSTNAME$]", customSQL.get(getClass(), WHERE_FIRST_NAME));
			whereClause=true;
		}else{
			sqlWhere = sqlWhere.replace("[$WHEREFIRSTNAME$]", "");
		}
		if(Validator.isNotNull(lastName)){
			sqlWhere = sqlWhere.replace("[$WHERELASTNAME$]", customSQL.get(getClass(), WHERE_LAST_NAME));
			whereClause=true;
		}else{
			sqlWhere = sqlWhere.replace("[$WHERELASTNAME$]", "");
		}
		if(Validator.isNotNull(emailAddress)){
			sqlWhere = sqlWhere.replace("[$WHEREEMAILADDRESS$]", customSQL.get(getClass(), WHERE_EMAIL_ADDRESS));
			whereClause=true;
		}else{
			sqlWhere = sqlWhere.replace("[$WHEREEMAILADDRESS$]", "");
		}
		if(status != WorkflowConstants.STATUS_ANY){
			sql = StringUtil.replace(sql, "[$WHERESTATUS$]", customSQL.get(getClass(), WHERE_USER_STATUS));
		}else{
			sql = StringUtil.replace(sql, "[$WHERESTATUS$]", "");
		}

		if(whereClause && !andOperator){
			sqlWhere = sqlWhere.replace("[$DEFAULT$]", " 1 = 0 ");	
			sql = StringUtil.replace(sql, "[$WHERESEARCH$]", sqlWhere);
		}else if(whereClause && andOperator){
			sqlWhere = sqlWhere.replace("[$DEFAULT$]", " 1 = 1 ");	
			sql = StringUtil.replace(sql, "[$WHERESEARCH$]", sqlWhere);
		}else{
			sql = StringUtil.replace(sql, "[$WHERESEARCH$]", "");
		}
		
		return sql;
	}
	
	private String replaceOrderUser(String sql, OrderByComparator obc, long companyId) {
		if (obc != null && obc.getOrderBy() != null && !obc.getOrderBy().equals("")) {
			sql = sql.replace("[$ORDERBY$]", obc.toString());
		}else{
			if(LMSPrefsPropsValues.getUsersFirstLastName(companyId)){
				sql = sql.replace("[$ORDERBY$]", "User_.lastName, User_.firstName, User_.middleName ");
			}else{
				sql = sql.replace("[$ORDERBY$]", "User_.firstName, User_.middleName, User_.lastName ");
			}
		}
		
		return sql;
	}
	
	private QueryPos replaceQPosJoinWhereUser(QueryPos qPos, String screenName, String firstName, String lastName, String emailAddress) {
		
		if(Validator.isNotNull(screenName)){
			screenName = "%"+screenName+"%";
			qPos.add(screenName);
		}
		if(Validator.isNotNull(firstName)){
			firstName = "%"+firstName+"%";
			qPos.add(firstName);
		}
		if(Validator.isNotNull(lastName)){
			lastName = "%"+lastName+"%";
			qPos.add(lastName);
		}			
		if(Validator.isNotNull(emailAddress)){
			emailAddress = "%"+emailAddress+"%";
			qPos.add(emailAddress);
		}
		
		return qPos;
	}	
	
	private SessionFactory getUserSessionFactory() {
		return (SessionFactory) PortalBeanLocatorUtil.locate(SESSION_FACTORY);
	}

	public void closeSessionUser(Session session) {
		getUserSessionFactory().closeSession(session);
	}

	public Session openSessionUser() throws ORMException {
		return getUserSessionFactory().openSession();
	}
	
	private static final String PARAM_TITLE_DESCRIPTION = "title";
	private static final String PARAM_GROUP_ID = "groupId";
	private static final String PARAM_STATUS = "status";
	private static final String PARAM_PARENT_COURSE_ID = "parentCourseId";
	
	private static final String SESSION_FACTORY = UserFinder.class.getName();

}
