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
<@easyfkSetAppMenu menuName="EASYFK_MENU" focusItemIds="{['menupartyClsGroupId']}" location="component://party/webapp/resources/page/appmenus.ftl">
</@easyfkSetAppMenu>
<#--
<@easyfkSetContextMenu>
{MENU_DATA:[
{id:'contextNewMenuId',title:'新建',desc:'新建组织机构',toggle:'modal',href:'#newpartyGroupFormPanel',style:'btn btn-primary tip-bottom',style2:' icon-plus-sign icon-white'},
]}
</@easyfkSetContextMenu>
-->
<@easyfkSetNavBar subTitle="组织机构">
{NAV_BAR_DATA:[{id:'navid1',title:'首页',tip:'返回首页',style:'icon-home',href:'${ctx}'},
{id:'navid2',title:'组织机构'}]}
</@easyfkSetNavBar>

<@easyfkDecoratorScreen name="body" location="component://party/webapp/resources/page/appcommon.ftl">
<div class="row-fluid">
    <div class="span6">
        <div  id="partyClsGroupMenuBar" style="display: none;">
            <@easyfkContextMenu menuStyle ="Dropdown">
                {MENU_DATA:[
                {id:'',title:'新建机构',desc:'新建当前选中的下级组织机构',toggle:'modal',href:'javascript:easyfk.neworgnode()',style:'btn btn-primary tip-bottom',style2:' icon-plus-sign icon-white'},
                {id:'',title:'会员',desc:'属于当前选中机构的会员',toggle:'modal',href:'#partyGroupFormDailog',style:'btn btn-primary tip-bottom',style2:' icon-user icon-white'},
                {id:'',title:'编辑',desc:'编辑当前选中的组织机构',toggle:'modal',href:'javascript:easyfk.editnode()',style:'btn btn-inverse tip-bottom',style2:' icon-pencil icon-white'},
                {id:'',title:'删除',desc:'删除当前选中的组织机构',toggle:'modal',href:'javascript:easyfk.removenode()',style:'btn btn-danger tip-bottom',style2:' icon-remove icon-white'},
                ]}
            </@easyfkContextMenu>
        </div>
        <div >
            <div id="partyClsGroupTree" style="overflow-y: auto;height: 550px;">

            </div>
        </div>
    </div>
    <div class="span6">

        <div class="widget-box">
            <div class="widget-title">
								<span class="icon">
									<i class="icon-th"></i>
								</span>
                <h5>会员组</h5>
                <span class="label label-info">Feature</span>
            </div>
            <div class="widget-content nopadding">
                <table class="table table-bordered table-striped">
                    <thead>
                    <tr>
                        <th>Column name</th>
                        <th>Column name</th>
                        <th>Column name</th>
                        <th>Column name</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>Row 1</td>
                        <td>Row 2</td>
                        <td>Row 3</td>
                        <td>Row 4</td>
                    </tr>
                    <tr>
                        <td>Row 1</td>
                        <td>Row 2</td>
                        <td>Row 3</td>
                        <td>Row 4</td>
                    </tr>
                    <tr>
                        <td>Row 1</td>
                        <td>Row 2</td>
                        <td>Row 3</td>
                        <td>Row 4</td>
                    </tr>

                    </tbody>
                </table>

            </div>
        </div>
        <@easyfkPageBar pageUrl="${ctx}/partyclsgroup/list?pageSize=10&pageIndex="></@easyfkPageBar>

    </div>
</div>
</@easyfkDecoratorScreen>

<style type="text/css">

</style>
<script type="text/javascript">
    $(document).ready(function(){
        $("#partyGroupForm").validate({
            rules:{
                description:{
                    required:true,
                    minlength:2
                },
                url:{
                    required:true,
                    url: true
                }
            },
            errorClass: "help-inline",
            errorElement: "span",
            highlight:function(element, errorClass, validClass) {
                $(element).parents('.control-group').addClass('error');
            },
            unhighlight: function(element, errorClass, validClass) {
                $(element).parents('.control-group').removeClass('error');
                $(element).parents('.control-group').addClass('success');
            }
        });


        $("#partyGroupFormSubmit").click(function(){
            $("#partyGroupForm").submit();
        });

        $("#partyGroupForm").submit(function(){
            var ajaxUrl = $("#partyGroupFormAction").val();
            var postparams =$("#partyGroupForm").serialize();
            var currentParentId = $("#parentGroupId").val();
            easyfk.ajaxpost(ajaxUrl,postparams,function(msg,type,obj){
                if(type){
                    easyfk.storeMenuBar();
                    $("#description").val("");
                    easyfk.getChildNode(currentParentId,'node_'+currentParentId,1);
                    $("#partyGroupFormDailog").modal("hide");
                }else{
                    alert(msg);
                }
            });
            return false;
        });

    });

</script>

<input type="hidden"   id="currentSelectedGroupId" value="">
<input type="hidden"   id="partyGroupFormAction" value="">
<div id="partyGroupFormDailog" class="modal hide fade" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="formDailogLabel">
            新建组织机构
        </h3>
    </div>
    <div class="modal-body">
        <form action="" method="post" class="form-horizontal" name="partyGroupForm" id="partyGroupForm">
            <input type="hidden" name="partyClassificationGroupId" id="partyClassificationGroupId">
            <input type="hidden" name="partyClassificationTypeId" id="partyClassificationTypeId" value="ORGANIZATION_CLASSIF">
            <input type="hidden" name="parentGroupId" id="parentGroupId" value="N/A">

            <div class="control-group">
                <label class="control-label">组织机构</label>
                <div class="controls">
                    <input type="text" id="description" name="description"/>
                </div>
            </div>
        </form>
    </div>
    <div class="modal-footer">
        <button class="btn btn-primary" id="partyGroupFormSubmit" >保存</button>
        <button class="btn" id="partyGroupFormClose" data-dismiss="modal" aria-hidden="true">关闭</button>
    </div>

</div>




