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
<script>
    easyfk.changePage = function(url){
        $("#listdatacontainer").load(url);
    }

</script>
    <#list parentClsGroupList as item>
    <#--    {id:'navid${item.partyClassificationGroupId}',tip:'${item.description}',title:'${item.description}'},-->
    /${item.description}
    </#list>

    <div class="widget-box">
        <div class="widget-title">
            <span class="icon">
                <i class="icon-th"></i>
            </span>
            <h5>会员列表</h5>

        </div>
        <div class="widget-content nopadding">
            <table class="table table-bordered table-striped">
                <thead>
                <tr>
                    <th>会员名称</th>
                    <th>类型</th>
                    <th style="width:60px;">类型</th>
                </tr>
                </thead>
                <tbody>
                <#list partyGroupList as item>
                <tr id="row${item.partyId}">
                    <td>${item.description} </td>
                    <td>${uiLabelMap[item.partyTypeId]}</td>
                    <td>
                        <div class="btn-group">
                            <button class="btn  btn-mini">操作</button>
                            <button data-toggle="dropdown" class="btn  btn-mini dropdown-toggle"><span class="caret"></span></button>
                            <ul class="dropdown-menu pull-right">
                                <li><a href="#" onclick="easyfk.partyDetial('${item.partyId}')"><i class="icon-eye-open "></i>${uiLabelMap.CommonView}</a></li>
                                <li><a href="#" onclick="easyfk.disableParty('${item.partyId}');"><i class=" icon-trash "></i>${uiLabelMap.CommonRemove}</a></li>
                            </ul>
                        </div>
                    </td>
                </tr>
                </#list>
                </tbody>
            </table>
        </div>

    </div>
    <@easyfkPageBar pageUrl="${ctx}/party/listpartydata?navids=${navids}&pageSize=10&pageIndex="></@easyfkPageBar>



