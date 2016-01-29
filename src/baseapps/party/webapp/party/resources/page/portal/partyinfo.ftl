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
<div id="partyinfo_po"></div>

<div class="row">
<div class="col-xs-12 col-sm-12">
    <div class="widget-box">
        <div class="widget-title">
								<span class="icon">
									<i class="fa  fa-th-list"></i>
								</span>
            <h5>会员简介</h5>
            <button class="btn btn-info btn-mini" onclick="easyfk.editpartyInfo()" style="float: right;margin: 1px 10px 0 0;">编辑</button>
        </div>
        <div class="widget-content  ">
        <#if party?has_content >
            <#assign desc = Static["cn.gorun8.easyfk.base.util.StringUtil"].ctlStrLength(party.description?default(''),250)>
            ${desc?default('')}
        </#if>
        </div>
    </div>
</div>
</div>


<div id="partyInfoFormPanel" class="modal fade" role="dialog" aria-labelledby="partyInfoFormLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h3 id="partyInfoFormLabel">
                    编辑会员简介
                </h3>
            </div>
            <form action="" method="post" class="form-horizontal" name="partyInfoForm" id="partyInfoForm">
                <input type="hidden" id="partyInfoFormAction" value="${ctx}/dyn/party/savePartyDesc">
                <input type="hidden" id="partyId" name="partyId" value="${currnetPartyId}">
                <div class="modal-body">
                        <div class="form-group">
                            <div class="col-sm-12 col-md-12 col-lg-12">
                                <textarea rows="15" id="description" name="description" class="form-control focus"><#if party?has_content >${party.description?default('')}</#if></textarea>
                            </div>
                        </div>
                </div>
                <div class="modal-footer">
                    <input type="submit" value="${uiLabelMap.CommonSave}" class="btn btn-primary">
                    <button class="btn" id="userLoginFormClose" data-dismiss="modal" aria-hidden="true">关闭</button>
                </div>
            </form>

        </div>
    </div>
</div>

<script type="text/javascript">
    easyfk.editpartyInfo = function () {
       var param = {};
       easyfk.showDailog("partyInfoFormPanel",param);
    }

    easyfk.submitpartyInfo = function(form){
        if(easyfk.isFormSubmited("partyInfoForm")){
            return ;
        }
        var ajaxUrl = $("#partyInfoFormAction").val();
        var postparams =$("#partyInfoForm").serialize();

        easyfk.ajaxpost(ajaxUrl,postparams,function(msg,type,obj){
            easyfk.resetFormSubmitStatus("partyInfoForm");
            if(type){
                $("#partyInfoFormPanel").modal("hide");
                easyfk.refreshSelPortlet("partyinfo_po","${ctx}/dyn/portal/partyinfo?currnetPartyId=${currnetPartyId}");
            }else{
                alert(msg);
            }
        });
        $("#partyInfoFormPanel").modal("hide");

    }

    $(document).ready(function(){
        var partyInfoRules = {description:{required:true,minlength:10,maxlength:5000}
        };
        easyfk.validateAndSubmit("partyInfoForm",partyInfoRules,function(form){
            easyfk.submitpartyInfo(form);
            return false;
        });

    });
</script>