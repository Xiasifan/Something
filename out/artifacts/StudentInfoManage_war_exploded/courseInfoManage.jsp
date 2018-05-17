<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>课程信息管理</title>
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/icon.css">
    <script type="text/javascript" src="jquery-easyui-1.3.3/jquery.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript">
        var url;

            function searchCourse() {
                $('#dg').datagrid('load', {
                gradeId: $('#s_gradeId').combobox("getValue")
            });
        }
        function resetValue() {
            $("#gradeId").combobox("setValue", "");
        }
        function openCourseModifyDialog() {
            var selectedRows = $("#dg").datagrid('getSelections');
            if (selectedRows.length != 1) {
                $.messager.alert("系统提示", "请选择一条要编辑的数据");
                return;
            }
            var row = selectedRows[0];
            $("#dlg").dialog("open").dialog("setTitle", "编辑课程信息");
            $("#fm").form("load", row);
            url = "courseSave?courseId=" + row.courseId;
        }

        function saveCourse() {
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
        function closeCourseDialog() {
            $("#dlg").dialog("close");
            resetValue();
        }
    </script>
</head>
<body style="margin: 5px;">
<table id="dg" title="课程信息" class="easyui-datagrid" fitColumns="true"
       pagination="true" rownumbers="true" url="courseList" fit="true" toolbar="#tb">
    <thead>
    <tr>
        <th field="cb" checkbox="true"></th>

        <th field="gradeName" width="100" align="center">班级名称</th>
        <th field="courseName" width="100" align="center">课程名称</th>
        <th field="courseName2" width="100" align="center">课程名称</th>
        <th field="courseName3" width="100" align="center">课程名称</th>
        <th field="courseName4" width="100" align="center">课程名称</th>
        <th field="courseName5" width="100" align="center">课程名称</th>
        <th field="courseName6" width="100" align="center">课程名称</th>
        <th field="courseName7" width="100" align="center">课程名称</th>
        <th field="courseDesc" width="250" align="center">课程备注</th>
    </tr>
    </thead>
</table>

<div id="tb">
    <div>

        <a href="javascript:openCourseModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>

    </div>
    <table>
        <tr>
            <td>班级名称：</td>
            <td><input class="easyui-combobox" id="s_gradeId" name="s_gradeId"
                       data-options="panelHeight:'auto',editable:false,valueField:'id',textField:'gradeName',url:'gradeComboList'"/>
            </td>
        </tr>
    </table>

    <a href="javascript:searchCourse()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a></div>
</div>

<div id="dlg" class="easyui-dialog" style="width: 570px;height: 350px;padding: 10px 20px" closed="true" buttons="#dlg-buttons">
    <form id="fm" method="post">
        <table cellspacing="5px;">
            <tr>
                <td>课程名称：</td>
                <td><input type="text" name="courseName" id="courseName"></td>
            </tr>
            <tr>
                <td>课程名称：</td>
                <td><input type="text" name="courseName2" id="courseName2"></td>
            </tr>
            <tr>
                <td>课程名称：</td>
                <td><input type="text" name="courseName3" id="courseName3"></td>
            </tr>
            <tr>
                <td>课程名称：</td>
                <td><input type="text" name="courseName4" id="courseName4"></td>
            </tr>
            <tr>
                <td>课程名称：</td>
                <td><input type="text" name="courseName5" id="courseName5"></td>
            </tr>
            <tr>
                <td>课程名称：</td>
                <td><input type="text" name="courseName6" id="courseName6"></td>
            </tr>
            <tr>
                <td>课程名称：</td>
                <td><input type="text" name="courseName7" id="courseName7"></td>
            </tr>
            <tr>
                <td>课程描述：</td>
                <td><textarea name="courseDesc" id="courseDesc" cols="30" rows="10"></textarea></td>
            </tr>
        </table>
    </form>
</div>

<div id="dlg-buttons">
    <a href="javascript:saveCourse()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a href="javascript:closeCourseDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>
</body>

</html>
