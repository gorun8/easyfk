<#--
Project:Easy Web Framework

Description: This project is based on much more open source projects than ever before,
             and can be applied to mostly web development environment.
Author:hezhiping   Email:110476592@qq.com


================================================================================================

-->
 
 
<#macro renderSimpleHTMLEditor fieldName fieldValue customerFileManagerFun="" width="500px" height="300px" customerFileManagerItemId="customerFileManagerItem" outConter="">
  <textarea name="${fieldName}" cols="10" rows="15" id="${fieldName}" alt="${uiLabelMap.CommonRequired}" pattern="required" style="dispaly:none;width:${width};height:${height};">${ fieldValue}</textarea>
  <script language="javascript" type="text/javascript">
	var ${fieldName}Editor = null;
	KindEditor.ready(function(K) {
	${fieldName}Editor = K.create('textarea[name="${fieldName}"]', 
	{
			resizeType : 2,
			allowPreviewEmoticons : true,
			width:500,
			items : [
				'source','fullscreen','|','undo','redo','|','cut','copy','paste','plainpaste','wordpaste','|',
				'justifyleft','justifycenter','justifyright','justifyfull','insertorderedlist','insertunorderedlist',
				'indent','outdent','subscript','superscript','clearhtml','quickformat','|',
				'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline','strikethrough',
				'lineheight','removeformat','|','link','unlink','anchor',
				'table','hr','pagebreak','|','selectall','preview','|','emoticons']
		});
		
		<#if customerFileManagerFun !="">
		var khtml=$('<span class="ke-outline" id="${customerFileManagerItemId}" title="图片" unselectable="on"><span class="ke-toolbar-icon ke-toolbar-icon-url ke-icon-image" unselectable="on"></span></span>');
		$("${outConter} .ke-toolbar").append(khtml);
		$("#${customerFileManagerItemId}").click(function(){
			${customerFileManagerFun}();
		});
		</#if>
	});
</script>
</#macro> 
 
 