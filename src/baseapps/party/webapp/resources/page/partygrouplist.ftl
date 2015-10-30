<#--
Project:Easy Web Framework

Description: This project is based on much more open source projects than ever before,
             and can be applied to mostly web development environment.
Author:hezhiping   Email:110476592@qq.com


===================================================================================================


-->
<@easyfkSetAppMenu menuName="EASYFK_MENU" focusItemIds="{['menuHomeId']}" location="component://party/webapp/resources/page/appmenus.ftl">
</@easyfkSetAppMenu>

<@easyfkSetContextMenu>
{MENU_DATA:[
{id:'contextNewMenuId',title:'新建',desc:'新建会员组',toggle:'modal',href:'#newPartyClsGroupFormPanel',style:'btn btn-primary tip-bottom',style2:' icon-plus-sign icon-white'},
]}
</@easyfkSetContextMenu>

<@easyfkSetNavBar subTitle="会员组">
 {NAV_BAR_DATA:[{id:'navid1',title:'首页',tip:'返回首页',style:'icon-home',href:'${ctx}'},
    {id:'navid2',title:'会员组'}]}
</@easyfkSetNavBar>

<@easyfkDecoratorScreen name="body" location="component://party/webapp/resources/page/appcommon.ftl">
<div class="row-fluid">
    <div class="span12">
        <@easyfkContextMenu></@easyfkContextMenu>
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
    </div>
</div>
</@easyfkDecoratorScreen>
<script type="text/javascript">
    $(document).ready(function(){
        // Form Validation
        $("#partyClsGroupForm").validate({
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

        $("#partyClsGroupFormSubmit").submit(function(){
            var ajaxUrl ="${ctx}/partyclsgroup/create";
            var postparams =$("#partyClsGroupFormSubmit").serialize();
            easyfk.ajaxpost(ajaxUrl,postparams,function(msg,type,obj){
                alert(msg);
            });
        });

    });

</script>

<div id="newPartyClsGroupFormPanel" class="modal hide">
    <div class="modal-header">
        <button data-dismiss="modal" class="close" type="button">×</button>
        <h3>新建会员组</h3>
    </div>
    <form action="#" method="post" class="form-horizontal" name="partyClsGroupForm" id="partyClsGroupForm">

        <input type="hidden" name="partyClassificationGroupId" id="partyClassificationGroupId">
        <input type="hidden" name="partyClassificationTypeId" id="partyClassificationTypeId">
        <input type="hidden" name="parentGroupId" id="parentGroupId">

    <div class="modal-body">
            <div class="control-group">
                <label class="control-label">会员组名称</label>
                <div class="controls">
                    <input type="text" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">分组名称</label>
                <div class="controls">
                    <input type="text" id="description" name="description"/>
                </div>
            </div>
    </div>

    <div class="modal-footer">
        <a data-dismiss="modal" class="btn btn-primary" href="#" id="partyClsGroupFormSubmit">保存</a>
        <a data-dismiss="modal" class="btn" href="#">取消</a>
    </div>
    </form>
</div>


