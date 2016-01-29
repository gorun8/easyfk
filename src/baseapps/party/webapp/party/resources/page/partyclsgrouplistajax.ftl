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
<script type="text/javascript">
    $(document).ready(function(){
        var zNodes =[
            <#list partyClsGroupList as item>
                { id:"${item.partyClassificationGroupId}", pId:"${item.parentGroupId}", name:"${item.description}[${item.partyClassificationGroupId}]", open:true,count:${item.childCount?default(0)} },
            </#list>
        ];
        easyfk.renderTree("partyClsGroupTree",zNodes);
    });
</script>
<ul id="partyClsGroupTree" class="ztree"></ul>



