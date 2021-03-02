<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>JQuery联级查询</title>
    <script type="text/javascript" src="js/jquery-3.4.1.js"></script>
    <script type="text/javascript">
      $(function (){
        //绑定事件
        $("#btnLoad").click(function (){
          //用jQuery做Ajax请求
          $.ajax({
            url:"queryProvince",
            datatype: "json",
            success:function (resp){
              //删除原有的数据，防止堆积
              $("#province").empty();
              //resp就是json数组的数据
              $.each(resp,function (i,n){
                //获取这个select对象
                $("#province").append("<option value='"+n.id+"'>"+n.name+"</option>")
              })
            }
          })
        })
        //select发生变化时触发事件
        $("#province").change(function () {
          var obj=$("#province>option:selected")
          var provinceId = obj.val();
          //再做ajax请求
          $.post("queryCity", {provinceId:proid}, function (resp) {
            //删除原有的数据，防止堆积
            $("#city").empty();
            //resp就是json数组的数据
            $.each(resp,function (i,n){
              //获取这个select对象
              $("#city").append("<option value='"+n.id+"'>"+n.name+"</option>")
            })
          }, "json");
        });
      })
    </script>
  </head>
  <body>
  <center>
    </div><p>JQuery联级查询</p>
    <div>
      <table border="2px">
        <tr>
          <td>
            省份名称
          </td>
          <td>
            <select id="province">
              <option value="0">请选择</option>
            </select>
          </td>
          <td>
            <input type="button" value="load数据" id="btnLoad"/>
          </td>
        </tr>
        <tr>
          <td>
            城市名称
          </td>
          <td>
            <select id="city">
              <option value="0">请选择</option>
            </select>
          </td>
        </tr>
      </table>
    </div>
  </center>
  </body>
</html>
