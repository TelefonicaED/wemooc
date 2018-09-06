package com.ted.lms.course.eval.evaluation.avg.internal.upgrade.v1_0_0;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Release;
import com.liferay.portal.kernel.service.ReleaseLocalService;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.ted.lms.constants.CourseConstants;
import com.ted.lms.course.eval.evaluation.avg.EvaluationAvgCourseEvalFactory;
import com.ted.lms.course.eval.evaluation.avg.constants.EvaluationAvgConstants;
import com.ted.lms.model.Course;
import com.ted.lms.service.CourseLocalService;

import java.text.DateFormat;
import java.util.List;
import java.util.Locale;

public class UpgradeCourseExtraData extends UpgradeProcess {
	
	public UpgradeCourseExtraData(CourseLocalService courseServiceUtil, ReleaseLocalService releaseLocalService) {
		this.courseLocalService = courseServiceUtil;
		this.releaseLocalService = releaseLocalService;
	}
	
	private static DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:sszzz",Locale.US);
	
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
				Element firedDateElement = null;
				Document document=null;
				Element rootElement = null;
				Element passPuntuation = null;
				List<Element> evaluations = null;
				JSONArray jsonArray = null;
				JSONObject jsonObject = null;
				
				for(Course course: listCourses) {
					log.debug("courseEvalId: " + course.getCourseEvalId());
					if(course.getCourseEvalId() == EvaluationAvgCourseEvalFactory.TYPE) {
						//Pasamos el xml a json
						if(Validator.isNotNull(course.getCourseExtraData())) {
							try {
								document=SAXReaderUtil.read(course.getCourseExtraData());
								rootElement =document.getRootElement();
								courseExtraData = JSONFactoryUtil.createJSONObject();
								
								courseEvaluation = JSONFactoryUtil.createJSONObject();
	
								firedDateElement = rootElement.element("firedDate");
								if(firedDateElement != null) {
									courseEvaluation.put(EvaluationAvgConstants.JSON_FIRED_DATE, dateFormat.parseObject(firedDateElement.getTextTrim()));
								}
								
								passPuntuation = rootElement.element("passPuntuation");
								if(passPuntuation != null) {
									courseEvaluation.put(CourseConstants.JSON_COURSE_EVAL_PASS_PUNTUATION, GetterUtil.getLong(passPuntuation.getText()));
								}
								
								evaluations = rootElement.elements("evaluation");
								if(!evaluations.isEmpty()){
									jsonArray = JSONFactoryUtil.createJSONArray();
									
									for (Element evaluation : evaluations) {
										long id = GetterUtil.getLong(evaluation.elementText("id"));
										if(id!=0){
											jsonObject = JSONFactoryUtil.createJSONObject();
											jsonObject.put("id", id);
											jsonObject.put("weight", GetterUtil.getLong(evaluation.elementText("weight")));
											jsonArray.put(jsonObject);
										}
									}
									
									courseEvaluation.put(EvaluationAvgConstants.JSON_EVALUATIONS, jsonArray);
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
