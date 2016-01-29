<#--
  Project:Easy Web Framework
  Description:
  EasyFK stands for Easy Web Framework.It's an open source product for E-Business / E-Commerce.It
  was launched by a chinese Hezhiping(QQ:110476592) in 2015.The goal of EasyFK is to  provide a
  foundation and starting point for reliable, secure , simple-to-use ,cost-effective ,scalable
  and suitable-for-Chinese E-Business / E-Commerce solutions. With EasyFK, you can get started
  right away without the huge deployment and maintenance costs of E-Business / E-Commerce systems.
  Of course, you can customize it or use it as a framework to implement your most challenging business needs.
  EasyFk is licensed under the Apache License Version 2.0.  You may obtain a copy of the License at
  http://www.apache.org/licenses/LICENSE-2.0
  Author:hezhiping   Email:110476592@qq.com
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


<#macro renderPortalPage columnList portalList params>

	<#list columnList as colum>
		<div class="col-xs-12 col-sm-${colum.columnWidthPercentage?default('')}">
			<#list portalList  as portal>
				<#if portal.columnSeqId == colum.columnSeqId >
					<div id="${colum.portalPageId}_${colum.columnSeqId}_${portal_index}">
						<#--
						<script type="text/javascript">
							$(document).ready(function() {
								easyfk.loadPortlet("${colum.portalPageId}_${colum.columnSeqId}_${portal_index}", "${portal.screenLocation}?${params}");
							});
						</script>-->
						${portal}
					</div>
				</#if>
			</#list>
		</div>
	</#list>

</#macro>

