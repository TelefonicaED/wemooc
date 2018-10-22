<%@page import="com.ted.lms.util.LMSPrefsPropsValues"%>
<%@page import="com.ted.lms.item.selector.web.internal.display.context.CourseItemSelectorViewDisplayContext"%>
<%@page import="com.liferay.portal.kernel.util.PropsUtil"%>
<%@page import="com.liferay.portal.kernel.util.ListUtil"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="com.liferay.portal.kernel.workflow.WorkflowConstants"%>
<%@page import="com.liferay.document.library.kernel.util.DLUtil"%>
<%@page import="com.liferay.portal.kernel.util.OrderByComparator"%>
<%@page import="com.liferay.portal.kernel.log.LogFactoryUtil"%>
<%@page import="com.liferay.portal.kernel.log.Log"%>
<%@page import="com.liferay.portal.kernel.repository.model.FileEntry"%>
<%@page import="com.liferay.portal.kernel.search.Field"%>
<%@page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.liferay.portal.kernel.search.Document"%>
<%@page import="com.liferay.portal.kernel.portletfilerepository.PortletFileRepositoryUtil"%>
<%@page import="com.liferay.portal.kernel.search.Hits"%>
<%@page import="com.liferay.portal.kernel.search.SearchContextFactory"%>
<%@page import="com.liferay.portal.kernel.search.SearchContext"%>
<%@page import="java.util.List"%>
<%@page import="com.liferay.portal.kernel.repository.model.Folder"%>
<%@page import="com.liferay.portal.kernel.dao.search.SearchPaginationUtil"%>
<%@page import="com.liferay.portal.kernel.dao.search.SearchContainer"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.liferay.document.library.display.context.DLMimeTypeDisplayContext"%>
<%@page import="com.ted.lms.item.selector.web.internal.constants.LMSItemSelectorWebKeys"%>
<%@ include file="init.jsp" %>

<%
CourseItemSelectorViewDisplayContext courseItemSelectorViewDisplayContext = (CourseItemSelectorViewDisplayContext)request.getAttribute(LMSItemSelectorWebKeys.COURSE_ITEM_SELECTOR_VIEW_DISPLAY_CONTEXT);
DLMimeTypeDisplayContext dlMimeTypeDisplayContext = (DLMimeTypeDisplayContext)request.getAttribute(LMSItemSelectorWebKeys.DL_MIME_TYPE_DISPLAY_CONTEXT);

int cur = ParamUtil.getInteger(request, SearchContainer.DEFAULT_CUR_PARAM, SearchContainer.DEFAULT_CUR);
int delta = ParamUtil.getInteger(request, SearchContainer.DEFAULT_DELTA_PARAM, SearchContainer.DEFAULT_DELTA);

int[] startAndEnd = SearchPaginationUtil.calculateStartAndEnd(cur, delta);

int start = startAndEnd[0];
int end = startAndEnd[1];

Folder folder = courseItemSelectorViewDisplayContext.fetchAttachmentsFolder(themeDisplay.getUserId(), scopeGroupId);

List portletFileEntries = null;
int portletFileEntriesCount = 0;

if (folder != null) {
	if (courseItemSelectorViewDisplayContext.isSearch()) {
		SearchContext searchContext = SearchContextFactory.getInstance(request);

		searchContext.setEnd(end);
		searchContext.setFolderIds(new long[] {folder.getFolderId()});
		searchContext.setStart(start);

		Hits hits = PortletFileRepositoryUtil.searchPortletFileEntries(folder.getRepositoryId(), searchContext);

		portletFileEntriesCount = hits.getLength();

		Document[] docs = hits.getDocs();

		portletFileEntries = new ArrayList(docs.length);

		for (Document doc : docs) {
			long fileEntryId = GetterUtil.getLong(doc.get(Field.ENTRY_CLASS_PK));

			FileEntry fileEntry = null;

			try {
				fileEntry = PortletFileRepositoryUtil.getPortletFileEntry(fileEntryId);
			}
			catch (Exception e) {
				if (log.isWarnEnabled()) {
					log.warn("Documents and Media search index is stale and contains file entry {" + fileEntryId + "}");
				}

				continue;
			}

			portletFileEntries.add(fileEntry);
		}
	}
	else {
		String orderByCol = ParamUtil.getString(request, "orderByCol", "title");
		String orderByType = ParamUtil.getString(request, "orderByType", "asc");

		OrderByComparator<FileEntry> orderByComparator = DLUtil.getRepositoryModelOrderByComparator(orderByCol, orderByType);

		portletFileEntries = PortletFileRepositoryUtil.getPortletFileEntries(scopeGroupId, folder.getFolderId(), WorkflowConstants.STATUS_APPROVED, start, end, orderByComparator);
		portletFileEntriesCount = PortletFileRepositoryUtil.getPortletFileEntriesCount(scopeGroupId, folder.getFolderId(), WorkflowConstants.STATUS_APPROVED);
	}
}
%>

<liferay-item-selector:repository-entry-browser
	dlMimeTypeDisplayContext="<%= dlMimeTypeDisplayContext %>"
	emptyResultsMessage='<%= LanguageUtil.get(resourceBundle, "there-are-no-course-attachments") %>'
	extensions="<%= ListUtil.toList(LMSPrefsPropsValues.getCourseImageExtensions(themeDisplay.getCompanyId())) %>"
	itemSelectedEventName="<%= courseItemSelectorViewDisplayContext.getItemSelectedEventName() %>"
	itemSelectorReturnTypeResolver="<%= courseItemSelectorViewDisplayContext.getItemSelectorReturnTypeResolver() %>"
	maxFileSize="<%=LMSPrefsPropsValues.getCourseImageMaxSize(themeDisplay.getCompanyId())%>"
	portletURL="<%= courseItemSelectorViewDisplayContext.getPortletURL(request, liferayPortletResponse) %>"
	repositoryEntries="<%= portletFileEntries %>"
	repositoryEntriesCount="<%= portletFileEntriesCount %>"
	showDragAndDropZone="<%= false %>"
	tabName="<%= courseItemSelectorViewDisplayContext.getTitle(locale) %>"
	uploadURL="<%= courseItemSelectorViewDisplayContext.getUploadURL(liferayPortletResponse) %>"
/>

<%!
private static Log log = LogFactoryUtil.getLog("com_ted_lms_item_selector_web.course_attachments_jsp");
%>