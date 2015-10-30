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

<@easyfkSetContextMenu>
{MENU_DATA:[
{id:'contextNewMenuId',title:'新建',desc:'新建组织机构',toggle:'modal',href:'#newPartyClsGroupFormPanel',style:'btn btn-primary tip-bottom',style2:' icon-plus-sign icon-white'},
]}
</@easyfkSetContextMenu>

<@easyfkSetNavBar subTitle="组织机构">
{NAV_BAR_DATA:[{id:'navid1',title:'首页',tip:'返回首页',style:'icon-home',href:'${ctx}'},
{id:'navid2',title:'组织机构'}]}
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
        <@easyfkPageBar pageUrl="${ctx}/partyclsgroup/list?pageSize=10&pageIndex="></@easyfkPageBar>

    </div>
</div>
</@easyfkDecoratorScreen>
<script type="text/javascript">
    $(document).ready(function(){

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


        $("#partyClsGroupFormSubmit").click(function(){
            $("#partyClsGroupForm").submit();
        });

        $("#partyClsGroupForm").submit(function(){
            var ajaxUrl ="${ctx}/partyclsgroup/create";
            var postparams =$("#partyClsGroupForm").serialize();
            easyfk.ajaxpost(ajaxUrl,postparams,function(msg,type,obj){
                alert(msg);
                $("#description").val("");
            });
            return false;
        });

    });

</script>

<div id="newPartyClsGroupFormPanel" class="modal hide">
    <div class="modal-header">
        <button data-dismiss="modal" class="close" type="button">×</button>
        <h3>新建组织机构</h3>
    </div>
    <form action="${ctx}/partyclsgroup/create" method="post" class="form-horizontal" name="partyClsGroupForm" id="partyClsGroupForm">

        <input type="hidden" name="partyClassificationGroupId" id="partyClassificationGroupId">
        <input type="hidden" name="partyClassificationTypeId" id="partyClassificationTypeId" value="ORGANIZATION_CLASSIF">
        <input type="hidden" name="parentGroupId" id="parentGroupId">

        <div class="modal-body">
            <div class="control-group">
                <label class="control-label">组织机构</label>
                <div class="controls">
                    <input type="text" id="description" name="description"/>
                </div>
            </div>
        </div>

        <div class="modal-footer">
            <a  type="submit" data-dismiss="modal" class="btn btn-primary" id="partyClsGroupFormSubmit" >保存</a>
            <a data-dismiss="modal" class="btn" href="#">取消</a>
        </div>
    </form>
</div>



