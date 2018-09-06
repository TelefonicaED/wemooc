package com.ted.lms.course.eval.ponderated.internal.upgrade.v1_0_0;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Release;
import com.liferay.portal.kernel.service.ReleaseLocalService;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.ted.lms.constants.CourseConstants;
import com.ted.lms.course.eval.ponderated.PonderatedCourseEvalFactory;
import com.ted.lms.course.eval.ponderated.constants.PonderatedConstants;
import com.ted.lms.model.Course;
import com.ted.lms.service.CourseLocalService;

import java.util.List;

public class UpgradeCourseExtraData extends UpgradeProcess {
	
	public UpgradeCourseExtraData(CourseLocalService courseServiceUtil, ReleaseLocalService releaseLocalService) {
		this.courseLocalService = courseServiceUtil;
		this.releaseLocalService = releaseLocalService;
	}
	
	private static final Log log = LogFactoryUtil.getLog(UpgradeCourseExtraData.class);

	@Override
	protected void doUpgrade() throws Exception {
		//Comprobamos si se está migrando o no
		Release release = releaseLocalService.fetchRelease("liferaylms-portlet");
		log.debug("release: " + release);
		if(release != null && release.getBuildNumber() > 0) {
			log.debug("buildNumber: " + release.getBuildNumber());
			try {
				//Actualizamos del prefspropsutil al campo CourseExtraData
				List<Course> listCourses = courseLocalService.getCourses(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	
				JSONObject courseEvaluation = null;
				JSONObject courseExtraData = null;
				Document document=null;
				Element rootElement = null;
				Element passPuntuation = null;
				List<Element> required = null;
				List<Element> weights = null;
				JSONArray jsonArray = null;
				JSONObject jsonObject = null;
				
				for(Course course: listCourses) {
					log.debug("courseEvalId: " + course.getCourseEvalId());
					if(course.getCourseEvalId() == PonderatedCourseEvalFactory.TYPE) {
						//Pasamos el xml a json
						if(Validator.isNotNull(course.getCourseExtraData())) {
							try {
								document=SAXReaderUtil.read(course.getCourseExtraData());
								rootElement =document.getRootElement();
								courseExtraData = JSONFactoryUtil.createJSONObject();
								
								courseEvaluation = JSONFactoryUtil.createJSONObject();
	
								passPuntuation = rootElement.element("score");
								if(passPuntuation != null) {
									courseEvaluation.put(CourseConstants.JSON_COURSE_EVAL_PASS_PUNTUATION, Double.parseDouble(passPuntuation.attributeValue("value")));
								}
								
								required = rootElement.elements("required");
								if(!required.isEmpty()){
									jsonArray = JSONFactoryUtil.createJSONArray();
									
									for (Element reqElement : required) {
										jsonObject = JSONFactoryUtil.createJSONObject();
										jsonObject.put(PonderatedConstants.JSON_ACT_ID, Long.parseLong(reqElement.attributeValue("actId")));
										jsonArray.put(jsonObject);
									}
									courseEvaluation.put(PonderatedConstants.JSON_REQUIRED, jsonArray);
								}
								
								weights = rootElement.elements("weight");
								if(!weights.isEmpty()){
									jsonArray = JSONFactoryUtil.createJSONArray();
									
									for (Element reqElement : weights) {
										jsonObject = JSONFactoryUtil.createJSONObject();
										jsonObject.put(PonderatedConstants.JSON_ACT_ID, Long.parseLong(reqElement.attributeValue("actId")));
										jsonObject.put(PonderatedConstants.JSON_WEIGHT_PONDERATION, Long.parseLong(reqElement.attributeValue("ponderation")));
										jsonArray.put(jsonObject);
									}
									courseEvaluation.put(PonderatedConstants.JSON_WEIGHTS, jsonArray);
								}
								
								courseExtraData.put(CourseConstants.JSON_COURSE_EVAL, courseEvaluation);
								course.setCourseExtraData(courseExtraData.toJSONString());
								courseLocalService.updateCourse(course);
							} catch(DocumentException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}	
	}

	private final CourseLocalService courseLocalService;
	private final ReleaseLocalService releaseLocalService;
}
