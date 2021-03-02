<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>使用json格式的数据</title>
    <script type="text/javascript">
        function  doSearch() {
            //1.创建异步对象
            var xmlHttp = new XMLHttpRequest();
            //2.绑定事件
            xmlHttp.onreadystatechange = function() {
                if( xmlHttp.readyState == 4 && xmlHttp.status == 200){
                    var data = xmlHttp.responseText;
                    //eval是执行括号中的代码， 把json字符串转为json对象
                    var jsonobj = eval("(" + data + ")");
                    //更新dom对象
                    callback(jsonobj);
                }
            }

            //3.初始异步对象的请求参数
            var proid = document.getElementById("proid").value;

            // true :异步处理请求。 使用异步对象发起请求后，不用等待数据处理完毕，就可以执行其它的操作。
            // false:同步，异步对象必须处理完成请求，从服务器端获取数据后，才能执行send之后的代码。 任意时刻只能执行一个异步请求。

            xmlHttp.open("get","queryjson?proid="+proid,false);

            //4.发送请求
            xmlHttp.send();


            //也可以创建其它的 XMLHttpRequest， 发送其它的请求处理。

            alert("====在send之后的代码====")
        }

        //定义函数，处理服务器端返回的数据
        function callback(json){
            document.getElementById("proname").value = json.name;
            document.getElementById("projiancheng").value=json.jiancheng;
            document.getElementById("proshenghui").value= json.shenghui;
        }

    </script>
</head>
<body>
    <p>ajax请求使用json格式的数据</p>
    <table>
        <tr>
            <td>省份编号：</td>
            <td><input type="text" id="proid">
                <input type="button" value="搜索" onclick="doSearch()">
            </td>
        </tr>
        <tr>
            <td>省份名称：</td>
            <td><input type="text" id="proname"></td>
        </tr>
        <tr>
            <td>省份简称：</td>
            <td><input type="text" id="projiancheng"></td>
        </tr>
        <tr>
            <td>省会名称：</td>
            <td><input type="text" id="proshenghui"></td>
        </tr>
    </table>
</body>
</html>
