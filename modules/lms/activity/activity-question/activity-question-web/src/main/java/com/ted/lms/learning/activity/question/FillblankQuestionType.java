package com.ted.lms.learning.activity.question;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.ted.lms.learning.activity.question.model.Answer;
import com.ted.lms.learning.activity.question.model.BaseQuestionType;
import com.ted.lms.learning.activity.question.model.Question;
import com.ted.lms.learning.activity.question.service.AnswerLocalServiceUtil;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.portlet.PortletRequest;

import org.jsoup.Jsoup;

public class FillblankQuestionType extends BaseQuestionType{
	
	private static final Log log = LogFactoryUtil.getLog(FillblankQuestionType.class);

	public FillblankQuestionType(Question question) {
		super(question);
	}
	
	@Override
	public String getClassName() {
		return FillblankQuestionType.class.getName();
	}

	@Override
	public long getType() {
		return FillblankQuestionTypeFactory.TYPE;
	}
	
	@Override
	public String getURLQuestion() {
		return "/question/fillblank/view.jsp";
	}
	
	@Override
	public long correct(Element element) throws PortalException {
		List<Answer> answers = AnswerLocalServiceUtil.getAnswersByQuestionId(question.getQuestionId());
		
		Answer solution = null;
		if(answers!=null && answers.size()>0)
			solution = answers.get(0);
		
		if(solution!=null){
			int correctAnswers=0;
			
			List<String> sols = getQuestionSols(solution.getAnswer());
			String[] userAnswers = element.element("answer").getText().split(",");
			log.debug("sols: " + sols.size());
			for(int i = 0; i < sols.size(); i++){
				if(isCorrect(sols.get(i), userAnswers[i])){
					log.debug("CORRECT "+i);
					correctAnswers++;
				}
			}
			if(sols.size()>0){
				double puntuation = correctAnswers*100.0/sols.size(); 
				log.debug("----PUNTUATION "+puntuation);
				return Math.round(puntuation);
			}
			
			if(correctAnswers==sols.size()){
				return CORRECT;
			}else{
				return INCORRECT;
			}
		}
	
		return INCORRECT;	
	}
	
	@Override
	public Element getResults(PortletRequest portletRequest){
		List<Answer> testAnswers = AnswerLocalServiceUtil.getAnswersByQuestionId(question.getQuestionId());

		Answer solution = null;
		if(testAnswers!=null && testAnswers.size()>0)
			solution = testAnswers.get(0);
		
		String answer = "";
		
		if(solution!=null){
			int i = getQuestionSols(solution.getAnswer()).size();
			for(int k=0; k<i; k++){
				if(answer!="") answer+=",";
				answer+= ParamUtil.getString(portletRequest, "question_"+question.getQuestionId()+"_"+k, "").replace(",", ""); //Quito la , de la respuesta del usaurio
			}
		}
    	
		Element questionXML=SAXReaderUtil.createElement("question");
		questionXML.addAttribute("id", Long.toString(question.getQuestionId()));
		
		long currentQuestionId = ParamUtil.getLong(portletRequest, "currentQuestionId");
		if (currentQuestionId == question.getQuestionId()) {
			questionXML.addAttribute("current", "true");
		}
		
		Element answerXML=SAXReaderUtil.createElement("answer");
		answerXML.addText(answer);
		
		questionXML.add(answerXML);
		
		return questionXML;
	}
	
	public List<String> getQuestionSols(String textAnswer) {
		List<String> sols = new ArrayList<String>();//array con las soluciones {...}
		String temp="";
		int start = textAnswer.indexOf("{"), end = 0;
		while (start != -1){
			end = textAnswer.indexOf("}");
			if(end != -1){
				if(end+1 == textAnswer.length()) temp = textAnswer.substring(start);
				else {
					if(textAnswer.charAt(end+1) == '}')
						if(end+2 == textAnswer.length()) temp = textAnswer.substring(start);
						else temp = textAnswer.substring(start, end+2);
					else temp = textAnswer.substring(start, end+1);
				}
				if(temp.startsWith("{{") || isMoodleAnswer(temp))sols.add(temp);				
				textAnswer = textAnswer.substring(0,start)+textAnswer.substring(start+temp.length());//textAnswer.replace(temp, "");
				
				start = textAnswer.indexOf("{");
			}
		}
		return sols;
	}
	
	public boolean isCorrect(String solution, String answer){
		boolean correct = false;
		Collator c = Collator.getInstance();
		c.setStrength(Collator.PRIMARY);
		List<String> sols = getBlankSols(solution, true);
		for(String sol:sols){
			if(c.compare(answer,sol)==0) {
				correct = true;
				break;
			}
		}
		return correct;
	}
	
	public long isCorrect(PortletRequest portletRequest){
		List<Answer> testAnswers = AnswerLocalServiceUtil.getAnswersByQuestionId(question.getQuestionId());
		
		Answer solution = null;
		if(testAnswers!=null && testAnswers.size()>0)
			solution = testAnswers.get(0);
		
		if(solution!=null){
			int correctAnswers=0;
			
			List<String> sols = getQuestionSols(solution.getAnswer());
			int i=0;
			for(String sol:sols){
				String answer= ParamUtil.getString(portletRequest, "question_"+question.getQuestionId()+"_"+i, "").replace(",", "");
				if(isCorrect(sol, answer)){
					log.debug("CORRECT "+i);
					correctAnswers++;
				}
				i++;
			}
			if(sols.size()>0){
				double puntuation = correctAnswers*100.0/sols.size(); 
				log.debug("----PUNTUATION "+puntuation);
				return Math.round(puntuation);
			}
			
			if(correctAnswers==sols.size()){
				return CORRECT;
			}else{
				return INCORRECT;
			}
		}
	
		return INCORRECT;
	}
	
	private boolean isMoodleAnswer(String temp) {
		if(temp.contains(":SHORTANSWER") || temp.contains(":SA") || temp.contains(":MW")
				|| temp.contains(":NUMERICAL:") || temp.contains(":NM:") || temp.contains("{{") 
				|| temp.contains(":MULTICHOICE_") || temp.contains(":MCV") || temp.contains(":MCH")
				|| temp.contains(":MULTICHOICE:") || temp.contains(":MC:")) return true;
		return false;
	}
	
	public List<String> getBlankSols(String solution, boolean onlyCorrectOnes) {
		List<String> correctSols =new ArrayList<String>();
		if(solution.startsWith("{{")){
			solution = solution.replace("{{", "");
			if(solution.contains("}}")) solution = solution.replace("}}", "");
			solution = Jsoup.parse(solution).text();
			correctSols.add(solution);
		}else if(solution.startsWith("{")){
			boolean isNumerical = false;
			if(solution.contains(":NUMERICAL:") || solution.contains(":NM:")) isNumerical = true;
			String aux = solution.substring(solution.indexOf(":", solution.indexOf(":")+1)+1);
			if(aux.endsWith("}")) aux = aux.substring(0, aux.length()-1);
			String[] sols = aux.split("~");
			for(String sol:sols){
				if(!sol.startsWith("*#")){
					if(sol.startsWith("=")) sol = sol.replace("=", "");
					else if(sol.startsWith("%") && !sol.startsWith("%0%")) sol = sol.replace(sol.substring(sol.indexOf("%"), sol.lastIndexOf("%")+1), "");
					else {
						if(sol.startsWith("%0%")) sol = sol.replace(sol.substring(sol.indexOf("%"), sol.lastIndexOf("%")+1), "");
						if(onlyCorrectOnes) sol = "*#";//para que no incluya las q son falsas
					}
					if(!sol.startsWith("*#")){
						if(sol.contains("#")) sol=sol.substring(0,sol.indexOf("#"));
						if(isNumerical && sol.contains(":")) sol = sol.substring(0, sol.indexOf(":"));
						if(!correctSols.contains(sol)) correctSols.add(sol);	
					}
				}
			}
		}
		return correctSols;
	}
	
	public String[] getAnswersSelected(Document document) {
		String answer = "";
		if(document != null){
			Iterator<Element> nodeItr = document.getRootElement().elementIterator();
			while(nodeItr.hasNext()) {
				Element element = nodeItr.next();
			     if("question".equals(element.getName()) && question.getQuestionId() == Long.valueOf(element.attributeValue("id"))){
			    	 Iterator<Element> elementItr = element.elementIterator();
			    	 if(elementItr.hasNext()) {
			    		 Element elementElement = elementItr.next();
			    		 if("answer".equals(elementElement.getName())) {
			    			 try {
								answer = elementElement.getText();
							} catch (NumberFormatException e) {
								e.printStackTrace();
							}
			    		 }
			    	 }
			     }
			}
		}
		return answer.split(",");
	}
	
}
