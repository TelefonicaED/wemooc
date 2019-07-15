<div class="row widget-mode-card">
	<#if entries?has_content>
		<#list entries as curCourseResult>
			<#if curCourseResult.getCourse().getSmallImageURL(themeDisplay)??>
				<#assign cardImage = true />
			<#else>
				<#assign cardImage = false />
			</#if>

			<div class="col-lg-4">
				<div class="card">
					<#if cardImage>
						<div class="card-header">
							<div class="aspect-ratio aspect-ratio-8-to-3">
								<img alt="thumbnail" class="aspect-ratio-item-center-middle aspect-ratio-item-fluid" src="${curCourseResult.getCourse().getSmallImageURL(themeDisplay)}">
							</div>
						</div>
					</#if>

					<div class="card-body widget-topbar">
						<div class="autofit-row card-title">
							<div class="autofit-col autofit-col-expand">
								<h3 class="title">
									<a class="title-link" href="${curCourseResult.getCourse().getFriendlyURL()}">
									${curCourseResult.getCourse().getTitle(themeDisplay.getLocale())}</a>
								</h3>
							</div>
						</div>

						<#if cardImage>
							<p class="widget-resume">${stringUtil.shorten(htmlUtil.stripHtml(curCourseResult.getCourse().getDescription(themeDisplay.getLocale())), 150)}</p>
						<#else>
							<p class="widget-resume">${stringUtil.shorten(htmlUtil.stripHtml(curCourseResult.getCourse().getDescription(themeDisplay.getLocale())), 400)}</p>
						</#if>
					</div>
				</div>
			</div>
		</#list>
	</#if>
</div>