<%@ include file="../init.jsp" %>

<h2>${activity.getTitle(themeDisplay.locale) }</h2>
 
<liferay-portlet:runtime portletName="${activityPortletName }" defaultPreferences="${defaultPreferences }" queryString="${queryString }"/>

<script type="text/javascript">
	AUI().ready('liferay-notice', 'collection', function(A) {
		var TimerModule = function(options) {
			var instance = this;
			instance.timeout=options.timeout||false;
			instance.warningText=options.warningText||'Timeout Warning: <span class="countdown-timer"/>';
			instance.expiredText=options.expiredText;
			instance.onClose=options.onClose;
			instance.banner=null;
			if(instance.timeout) {
				instance.banner = new Liferay.Notice({content:instance.warningText,closeText:false,toggleText:false});
				instance.countdowntext=instance.banner.one('.countdown-timer');
				if(instance.countdowntext){
					instance.countdowntext.text(instance._formatTime(instance.timeout));
				}
				var interval=1000;
				instance.finishtime = new Date().getTime()+instance.timeout;
				instance._countdownTimer = setInterval(
					function() {
						var currentTimeout = instance.finishtime-new Date().getTime();
						if (currentTimeout > 0) {
							instance.countdowntext.text(instance._formatTime(currentTimeout));
						}else {
							instance.banner.html(instance.expiredText);
							instance.banner.toggleClass('popup-alert-notice').toggleClass('popup-alert-warning');
							if (instance._countdownTimer) {
								clearInterval(instance._countdownTimer);
							}
							if (instance.onClose) {
								instance.onClose();
							}
						}
					},interval
				);					
			}
		};

		TimerModule.prototype = {
			_formatNumber: function(num) {
				if (num <= 9) {
					num = '0' + num;
				}
				return num;
			},
			_formatTime: function(time) {
				var instance = this;
				time = Math.floor(time/1000);
				var hours = Math.floor(time/3600);
				time = time%3600;
				var minutes = Math.floor(time/60);
				time = time%60;
				var seconds = Math.floor(time);
				return A.Array.map([hours,minutes,seconds], instance._formatNumber).join(':');	
			}
		};
					
		new TimerModule(
				{
				  timeout:Number('${leftTime}'),
				  warningText:'<liferay-ui:message key="module.timeout.warning" />',
				  expiredText:'<liferay-ui:message key="module.timeout" />',
				  onClose:function(){
					  if(typeof window.<portlet:namespace/>finishTryActivity == 'function'){
						  <portlet:namespace/>finishTryActivity();
					  }
			  		}	
				}
		);

	});
</script>	