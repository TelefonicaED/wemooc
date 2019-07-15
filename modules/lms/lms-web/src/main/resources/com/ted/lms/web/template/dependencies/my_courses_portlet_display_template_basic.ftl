<div class="widget-mode-simple">
	<#if entries?has_content>
		<#list entries as curCourseResult>
			<div class="widget-mode-simple-entry">
				<div class="autofit-row widget-topbar">
					<div class="autofit-col autofit-image">
						<div class="card-header">
							<div class="aspect-ratio">
								<#if curCourseResult.getCourse().getSmallImageURL(themeDisplay)??>
									<img alt="thumbnail" class="aspect-ratio-item-center-middle aspect-ratio-item-fluid" src="${curCourseResult.getCourse().getSmallImageURL(themeDisplay)}">
								</#if>
							</div>
						</div>
					</div>
					<div class="autofit-col autofit-col-expand">
						<#assign accessToCourse = !curCourseResult.getCourse().isLocked(user, permissionChecker) || curCourseResult.getCourse().hasPermissionAccessCourseFinished(user.getUserId()) />
						<h4 class="title">
							<#if accessToCourse> 
								<a class="title-link" href="${curCourseResult.getCourse().getFriendlyURL()}">${curCourseResult.getCourse().getTitle(themeDisplay.getLocale())}</a>
							<#else>
								${curCourseResult.getCourse().getTitle(themeDisplay.getLocale())}
							</#if>
						</h4>
						<h5 class="text-default">
							${stringUtil.shorten(htmlUtil.stripHtml(curCourseResult.getCourse().getDescription(themeDisplay.getLocale())), 150)}
						</h5>
						<#if !curCourseResult.passedDate??>
							<h6 class="text-default">
								<@liferay_ui["message"] key="execution-end-date" />: ${dateUtil.getDate(curCourseResult.getCourse().getExecutionEndDate(), "dd/MM/yy HH:mm", locale, timeZone)}
							</h6>
						</#if>
						<div class="row">
							<div class="col-md-6">
								<#if !curCourseResult.passedDate??>
									<#assign
										percentage = curCourseResult.getProgress()
									/>
									<@liferay_ui["message"] key="progress" />: ${percentage} %
									<div class="active progress progress-lg progress-striped reindex-progress">
										<div class="progress-bar" style="width:${percentage}%">
										</div>
									</div>
								<#else>
									<@liferay_ui["message"] key="result" />: ${curCourseResult.getResult()}
								</#if>
							</div>
							<div class="col-md-6">
								<#if accessToCourse && !curCourseResult.passedDate??> 
									<a class="title-link" href="${curCourseResult.getCourse().getFriendlyURL()}"><@liferay_ui["message"] key="continue" /></a>
								</#if>
							</div>
						</div>
					</div>
				</div>
			</div>
		</#list>
	</#if>
</div>