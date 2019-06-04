var activityExcludedId = 0;
AUI.add(
	'lms-activities',
	function(A) {
		Liferay.Activities = {
			getModules: function(callback) {
				Liferay.Service(
					'/lms.module/get-group-modules',
					{
						groupId: themeDisplay.getScopeGroupId()
					},
					callback
				);
			},

			getActivities: function(callback, selectKey) {
				Liferay.Service(
				  '/lms.learningactivity/get-activities-excluded',
				  {
				    moduleId: Number(selectKey),
				    actId: activityExcludedId
				  },
					callback
				);
			}
		};
	},
	'',
	{
		requires: []
	}
);