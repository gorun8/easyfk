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

<@easyfkSetAppMenu menuName="EASYFK_MENU" focusItemIds="{['menuPartygpgs']}" location="component://party/webapp/party/resources/page/appmenus.ftl">
</@easyfkSetAppMenu>

<@easyfkSetNavBar subTitle="会员列表">
 {NAV_BAR_DATA:[{id:'navid1',title:'首页',tip:'返回首页',style:'icon-home',href:'${ctx}'},
    {id:'navid2',title:'组织机构',tip:'管理组织机构',href:'${ctx}/partyclsgroup/list'},
    <#list parentClsGroupList as item>
    {id:'navid${item.partyClassificationGroupId}',tip:'${item.description}',title:'${item.description}'},
    </#list>
    {id:'navid3',title:'会员列表'},
 ]}
</@easyfkSetNavBar>

<@easyfkDecoratorScreen name="body" location="component://party/webapp/party/resources/page/appcommon.ftl">
<div class="row-fluid">
    <div class="span12">

        <div id="listdatacontainer"></div>

    </div>
</div>
</@easyfkDecoratorScreen>
<script type="text/javascript">
    easyfk.newPerson = function () {
        $("#partyPersonFormAction").val("${ctx}/party/createperson");
        $("#partyPersonFormLabel").html("新建个人会员");
        $("#partyPersonFormPanel").modal("show");
    }

    easyfk.newGroup = function () {
        $("#partyGroupFormAction").val("${ctx}/party/creategroup");
        $("#partyPersonFormLabel").html("新建组织会员");
        $("#partyPersonFormPanel").modal("show");
    }

    easyfk.loaddata = function(){
        var url ="${ctx}/party/listpartydata?navids=${navids}";
        $("#listdatacontainer").load(url);
    }
    easyfk.partydetial = function(partyId){
        document.location.href = "${ctx}/party/partydetial?id="+partyId;
    }

    easyfk.removeparty = function(){
        alert("ss");
//        easyfk.confirm("",function(){
//            alert("ok");
//        });
    }


    $(document).ready(function(){
        //load data
        easyfk.loaddata();

        // Form Validation
        $("#partyPersonFormSubmit").click(function(){
            $("#partyPersonForm").submit();
        });

        $("#partyPersonForm").submit(function(){
            var ajaxUrl = $("#partyPersonFormAction").val();
            var postparams =$("#partyPersonForm").serialize();
            easyfk.ajaxpost(ajaxUrl,postparams,function(msg,type,obj){
                if(type){
                    //easyfk.storeMenuBar();
                    $("#partyPersonForm").reset();
                }else{
                    alert(msg);
                }
            });
            return false;
        });


        $("#partyGroupFormSubmit").click(function(){
            $("#partyGroupForm").submit();
        });

        $("#partyGroupForm").submit(function(){
            var ajaxUrl = $("#partyGroupFormAction").val();
            var postparams =$("#partyGroupForm").serialize();
           easyfk.ajaxpost(ajaxUrl,postparams,function(msg,type,obj){
                if(type){
                    $("#description").val("");
                }else{
                    alert(msg);
                }
            });
            return false;
        });

    });

</script>



<input type="hidden" id="partyPersonFormAction">
<div id="partyPersonFormPanel" class="modal  fade" role="dialog" aria-labelledby="partyPersonFormLabel" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="partyPersonFormLabel">
            新建个人会员
        </h3>
    </div>
    <div class="modal-body">
        <form action="" method="post" class="form-horizontal" name="partyPersonForm" id="partyPersonForm">
            <input type="hidden" name="partyId" id="partyId">
            <input type="hidden" name="partyTypeId" id="partyTypeId" value="PERSON">
            <input type="hidden" name="preferredCurrencyUomId" id="preferredCurrencyUomId" value="CNY">
            <div class="control-group">
                <label class="control-label">姓名</label>
                <div class="controls">
                    <input type="text" id="firstName" name="firstName"/>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label">${uiLabelMap.CommonDescription}</label>
                <div class="controls">
                    <input type="text" id="description" name="description"/>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">${uiLabelMap.FormFieldTitle_externalId}</label>
                <div class="controls">
                    <input type="text" id="externalId" name="externalId"/>
                </div>
            </div>
        </form>
    </div>
    <div class="modal-footer">
        <button class="btn btn-primary" id="partyPersonFormSubmit" >保存</button>
        <button class="btn" id="partyPersonFormClose" data-dismiss="modal" aria-hidden="true">关闭</button>
    </div>
</div>

<input type="hidden" id="partyGroupFormAction">
<div id="partyGroupFormPanel" class="modal  fade" role="dialog" aria-labelledby="partyGroupFormLabel" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="partyGroupFormLabel">
            新建组织会员
        </h3>
    </div>
    <div class="modal-body">
        <form action="" method="post" class="form-horizontal" name="partyGroupForm" id="partyGroupForm">
            <input type="hidden" name="partyId" id="partyId">
            <input type="hidden" name="partyClassificationTypeId" id="partyClassificationTypeId" value="PARTY_GROUP">
            <input type="hidden" name="groupNameLocal" id="groupNameLocal" value="zh_CN">

            <div class="control-group">
                <label class="control-label">${uiLabelMap.PartyGroupName}</label>
                <div class="controls">
                    <input type="text" id="groupName" name="groupName"/>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">${uiLabelMap.CommonDescription}</label>
                <div class="controls">
                    <input type="text" id="comments" name="comments"/>
                </div>
            </div>
        </form>
    </div>
    <div class="modal-footer">
        <button class="btn btn-primary" id="partyGroupFormSubmit" >保存</button>
        <button class="btn" id="partyGroupFormClose" data-dismiss="modal" aria-hidden="true">关闭</button>
    </div>
</div>








