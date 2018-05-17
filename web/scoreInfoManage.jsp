<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>学生成绩管理</title>
        <link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/icon.css">
    <script type="text/javascript" src="jquery-easyui-1.3.3/jquery.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
    
    
        <script type="text/javascript">
        var url;

      

        function searchScore(){
            $('#dg').datagrid('load',{
                stuNo:$('#s_stuNo').val(),
                stuName:$('#s_stuName').val(), 
            });
        }


        function savescore(){
            $("#fm").form("submit",{
                url:url,
                onSubmit:function(){
                
                    return $(this).form("validate");
                },
                success:function(result){
                    if(result.errorMsg){
                        $.messager.alert("系统提示",result.errorMsg);
                        return;
                    }else{
                        $.messager.alert("系统提示","保存成功");
                        resetValue();
                        $("#dlg").dialog("close");
                        $("#dg").datagrid("reload");
                    }
                }
            });
        }
        function closeStudentDialog(){
            $("#dlg").dialog("close");
            resetValue();
        }

        function resetValue(){
            $("#stuNo").val("");
            $("#stuName").val("");
            $("#courseName").val("");
            $("#courseName2").val("");
            $("#courseName3").val("");
            $("#courseName4").val("");
            $("#courseName5").val("");
            $("#courseName6").val("");
            $("#courseName7").val("");
        }

        function openScoreModifyDialog(){
            var selectedRows=$("#dg").datagrid('getSelections');
            if(selectedRows.length!=1){
                $.messager.alert("系统提示","请选择一条要编辑的数据！");
                return;
            }
            var row=selectedRows[0];
          /*   $("#dlg").dialog("open").dialog("setTitle","编辑成绩信息");
            $("#fm").form("load",row); */
            $("#dlg").dialog("open").dialog("setTitle","编辑成绩信息");
            $("#fm").form("load",row);
            url="ScoreSave?scoreId="+row.scoreId; 
        }

    </script>
   
   </head>
  
  <body style="margin: 5px;">
<table id="dg" title="成绩信息管理" class="easyui-datagrid" fitColumns="true"
       pagination="true" rownumbers="true" url="scoreList" fit="true" toolbar="#tb">
    <thead>
    <tr>
        <th field="cb" checkbox="true"></th>
        <th field="stuNo" width="100" align="center">学号</th>
        <th field="stuName" width="100" align="center">姓名</th>
        <th field="courseName" width="100" align="center">科目1</th>
        <th field="courseName2" width="100" align="center">科目2</th>
        <th field="courseName3" width="100" align="center">科目3</th>
        <th field="courseName4" width="100" align="center">科目4</th>
        <th field="courseName5" width="100" align="center">科目5</th>
        <th field="courseName6" width="100" align="center">科目6</th>
        <th field="courseName7" width="100" align="center">科目7</th>
        
    </tr>
    </thead>
</table>

<div id="tb">
    <div>
      <!-- <a href="javascript:openScoreAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
        <a href="javascript:deleteScore()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a> -->
        <a href="javascript:openScoreModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
    </div>
    <div>&nbsp;学号：&nbsp;<input type="text" name="s_stuNo" id="s_stuNo" size="10"/>
        &nbsp;姓名：&nbsp;<input type="text" name="s_stuName" id="s_stuName" size="10"/>
        <a href="javascript:searchScore()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a></div>
</div>

<div id="dlg" class="easyui-dialog" style="width: 570px;height: 350px;padding: 10px 20px"
     closed="true" buttons="#dlg-buttons">
    <form id="fm" method="post">
        <table cellspacing="5px;">
            <tr>
                <td>学号：</td>
                <td><input type="text" name="stuNo" id="stuNo" class="easyui-validatebox" required="true" disabled="disabled"/></td>
                <td>姓名：</td>
                <td><input type="text" name="stuName" id="stuName" class="easyui-validatebox" required="true"  disabled="disabled"/></td>
            </tr>
            <tr>
                <td>课程名称</td>
                <td>
                <input type="text" name="courseName" id="courseName" class="easyui-validatebox" />
                    </td>
               <td> 课程名称</td>
                <td>
                <input type="text" name="courseName2" id="courseName2" class="easyui-validatebox" />
                    </td>
            </tr>
            <tr>
                   <td>课程名称</td>
                <td>
                <input type="text" name="courseName3" id="courseName3" class="easyui-validatebox" />
                    </td>
               <td> 课程名称</td>
                <td>
                <input type="text" name="courseName4" id="courseName4" class="easyui-validatebox"/>
                    </td>
            </tr>
                 <tr>
                     <td>课程名称</td>
                <td>
                <input type="text" name="courseName5" id="courseName5" class="easyui-validatebox" />
                    </td>
               <td> 课程名称</td>
                <td>
                <input type="text" name="courseName6" id="courseName6" class="easyui-validatebox"/>
                    </td>
            </tr>
            <tr>
                     <td>课程名称</td>
                <td>
                <input type="text" name="courseName7" id="courseName7" class="easyui-validatebox"/>
                    </td>      
            </tr>
        </table>
    </form>
</div>

<div id="dlg-buttons">
    <a href="javascript:savescore()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a href="javascript:closeStudentDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>

   </body>
   </html>
