
<%@ page import="Bean.User" %>
<%@ page import="java.util.logging.SimpleFormatter" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
            session.invalidate();
        }
    %>
    <title>学生管理系统首页</title>
    <link rel="icon" href="img/favicon.ico">
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/icon.css">
    <script type="text/javascript" src="jquery-easyui-1.3.3/jquery.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript">
        $(function(){
            // 数据
            var treeData=[{
                text:"操作目录",
                children:[{
                    text:"班级信息管理",
                    attributes:{
                        url:"gradeInfoManage.jsp"
                    }
                },{
                    text:"学生信息管理",
                    attributes:{
                        url:"studentInfoManage.jsp"
                    }
                },{
                    text:"课程信息管理",
                    attributes:{
                        url:"courseInfoManage.jsp"
                    }
                },{
                    text:"成绩管理",
                    attributes:{
                        url:"scoreInfoManage.jsp"
                    }
                }]
            }];

            // 实例化树菜单
            $("#tree").tree({
                data:treeData,
                lines:true,
                onClick:function(node){
                    if(node.attributes){
                        openTab(node.text,node.attributes.url);
                    }
                }
            });

            // 新增Tab
            function openTab(text,url){
                if($("#tabs").tabs('exists',text)){
                    $("#tabs").tabs('select',text);
                }else{
                    var content="<iframe frameborder='0' scrolling='auto' style='width:100%;height:100%' src="+url+"></iframe>";
                    $("#tabs").tabs('add',{
                        title:text,
                        closable:true,
                        content:content
                    });
                }
            }
        });
    </script>
</head>
<body class="easyui-layout">
<div region="north" style="height: 80px;background-color: #E0EDFF">
    <div align="left" style="width: 80%;float: left"><img src="img/main.jpg"></div>
    <div style="padding-top: 50px;padding-right: 20px;">当前用户：&nbsp;<font color="red" >${currentUser.userName }</font>
        <a href="LogOut" style="text-decoration:none; color: blue;" >&nbsp;&nbsp;安全退出</a>
    </div>

</div>
<div region="center">
    <div class="easyui-tabs" fit="true" border="false" id="tabs">
        <div title="首页" >
            <div align="center"><img src="img/main2.jpg"></div>
        </div>
    </div>
</div>
<div region="west" style="width: 150px;" title="导航菜单" split="true">
    <ul id="tree"></ul>
</div>
<div region="south" style="height: 25px;" align="center">Copyright@软件161 第九组 &nbsp;&nbsp;<%=new SimpleDateFormat("yyyy年MM月dd号").format(new Date())%></div>
</body>
</html>
