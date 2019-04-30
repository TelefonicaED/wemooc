var ismobile=navigator.userAgent.match(/(iPad)|(iPhone)|(iPod)|(android)|(webOS)/i);

if(ismobile){
	$("div.question-page-current ul.sortable").sortable({

		start : function(e, ui) {
			var drag = e.target;
		}

	});
}else{
	AUI().ready('node','aui-io-request','aui-parse-content','aui-sortable','dd-constrain', 'dd-proxy', 'dd-drop',function(A) {
		A.all('div.question-page-current ul.sortable').each(function(node) {
			new A.SortableList(
			{
			   container: node,
			    nodes: 'li'
			});
		});
	});
}