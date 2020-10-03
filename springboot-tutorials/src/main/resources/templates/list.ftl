<html>
    <body>
        <h1>测试freemarker</h1>
        <p>list循环</p>
        <#list userList as user>
            ${user.loginTime}
        </#list>
        <#list userList>
            <ul>
                <#items as user>
                    ${user.loginTime}<#sep>
                </#items>
            </ul>
        </#list>
    <p>
    <#if flag>
        flag is true
    <#else>
        flag is false
    </#if>
    </p>
    <p>include使用</p>
    <#include "footer.ftl"/>
    </body>
    <p>内置函数使用</p>
    <p>${userList?size}</p>
    <p>自定义指令</p>
    <#macro test user remark>
        <font size="+2">${user.loginTime} ${remark}</font>
        <#nested>
    </#macro>
    <@test user=userList[0] remark="测试">插槽</@test>
</html>