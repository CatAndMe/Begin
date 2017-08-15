

var pageSize = "8";//每页行数
var currentPage = "1";//当前页
var totalPage = "4";//总页数
var rowCount = "4";//总条数
var params="";//参数
var emplName_="";
var email_="";
var fistInput_="";
var secondInput_="";
var emplId_=0;
function changeInfo(emplId_Info) {
    var params={fistInput:fistInput_,secondInput:secondInput_,emplId:emplId_Info};
    $.post("/user/modifyPassword.do",params,
        function(data){
            if (data.message==true){
                alert("(づ￣3￣)づ╭❤～成功");
                hideDiv('pop-div');
            }else {
                alert("(づ￣3￣)づ╭❤～失败");
            }

        })

}
function changeEmplEmail(emplId_) {
    var params={emplId:emplId_,emplEmail:email_};
    $.post("/user/modifyEmail.do",params,
        function(data1){
            if (data1.message==true){
                alert("(づ￣3￣)づ╭❤～成功");
                hideDiv('pop-div');
            }else {
                alert("(づ￣3￣)づ╭❤～失败");
            }

        })
}
function addNewUser(userName_,userPassword_,userEmail_,userId_,userAdmin_) {
    var params={userName:userName_,userPassword:userPassword_,userEmail:userEmail_,userId:userId_,userAdmin:userAdmin_};
    $.post("/Action/add.do",params,
        function (data) {
            if (data.message=="true"){
                alert("(づ￣3￣)づ╭❤～成功");
                hideDiv('pop-div');
            }else {
                alert("用户已存在");
            }
        }

    )
}
function deleteUser(fistId,secondId) {
    var params;
    if (checkId($("#fistId").val())==true){
        if ($("#fistId").val()==$("#secondId").val()){
            params={userId:$("#fistId").val()};
        }else {
            alert("两次输入不相等");
        }
    }else {
        alert("格式错误");
    }

    $.post("/Action/delete.do",params,
        function (data) {
            if (data.message=="true"){
                alert("(づ￣3￣)づ╭❤～成功");
                hideDiv('pop-div');
            }else {
                alert("失败");
            }
        }
    )
}
function delete_user() {
    var content;
    content=
        "<br>\n" +
        "                    <h3 style=\"text-align:center;font-size:20px\">信息修改</h3>\n" +
        "                    <h4 style=\"text-align:center;border-top:3px solid #286a3a\"></h4>\n" +
        "                    <br>\n" +
        "                    <div style=\"text-align:center\">\n" +
        "                        <tr>\n" +
        "                            <th style=\"font-size: 16px\">删除ID：</th>\n" +
        "                            <td><input id=\"fistId\" type=\"text\" style=\"width: 90px\"/></td>\n" +
        "\n" +
        "                        </tr>\n" +
        "                        <br>\n" +
        "                        <br>\n" +
        "                        <tr>\n" +
        "                            <th style=\"font-size: 16px\">确认ID：</th>\n" +
        "                            <td><input id=\"secondId\" type=\"text\" style=\"width: 90px\"/></td>\n" +
        "\n" +
        "                        <br>\n" +
        "                        <br>\n" +
        "                        <div id='buttonPanel'  style=\"text-align:center\">\n" +
        "\n" +
        "                            <input style=\"margin:0 20px\" type=\"button\" value=\"取消\" id=\"btn1\" class=\"btn btn-primary btn2\" onclick=\"hideDiv('pop-div') ;\" />\n" +
        "                            <input style=\"margin:0 20px\" type=\"button\" value=\"确认\" id=\"btn2\" class=\"btn btn-primary btn2\" onclick=\"deleteUser();\" />\n" +
        "                        </div>\n" +
        "                        <br>";
    popupDiv(250,200,content);
}
function add_user() {
    var content;
    content=
        "<br>\n" +
        "                    <h3 style=\"text-align:center;font-size:20px\">信息修改</h3>\n" +
        "                    <h4 style=\"text-align:center;border-top:3px solid #286a3a\"></h4>\n" +
        "                    <br>\n" +
        "                    <br>\n" +
        "                    <div style=\"text-align:center\">\n" +
        "                        <tr>\n" +
        "                            <th style=\"font-size: 16px\">输入名字：</th>\n" +
        "                            <td><input id=\"addInfo_Name\" type=\"text\" style=\"width: 130px\"/></td>\n" +
        "\n" +
        "                        </tr>\n" +
        "                        <br>\n" +
        "                        <br>\n" +
        "                        <tr>\n" +
        "                            <th style=\"font-size: 16px\">输入密码：</th>\n" +
        "                            <td><input id=\"changeInfo1\" type=\"text\" style=\"width: 130px\"/></td>\n" +
        "\n" +
        "                        </tr>\n" +
        "                        <br>\n" +
        "                        <br>\n" +
        "                        <tr>\n" +
        "                            <th style=\"font-size: 16px\">再次输入：</th>\n" +
        "                            <td><input id=\"changeInfo2\" type=\"text\" style=\"width: 130px\"/></td>\n" +
        "\n" +
        "                        </tr>\n" +
        "                        <br>\n" +
        "                        <br>\n" +
        "                        <tr>\n" +
        "                            <th style=\"font-size: 16px\">输入工号：</th>\n" +
        "                            <td><input id=\"addInfo_Id\" type=\"text\" style=\"width: 130px\"/></td>\n" +
        "\n" +
        "                        </tr>\n" +
        "                        <br>\n" +
        "                        <br>\n" +
        "                        <tr>\n" +
        "                            <th style=\"font-size: 16px\">输入邮箱：</th>\n" +
        "                            <td><input id=\"addInfo_Email\" type=\"text\" style=\"width: 130px\"/></td>\n" +
        "\n" +
        "                        </tr>\n" +
        "                        <br>\n" +
        "                        <br>\n" +
        "                        <tr>\n" +
        "                            <th style=\"font-size: 16px\">权限设置：</th>\n" +
        "                           <select name=\"search-sort\" id=\"addInfo_Admin\" style=\"width: 60x\">\n" +
        "                                    <option value=\"0\">员工</option>\n" +
        "                                    <option value=\"1\">管理员</option>\n" +
        "                           </select>"+"　　　　　"+
        "                        </tr>\n" +
        "                        <br>\n" +
        "                        <br>\n" +
        "                        <div id='buttonPanel'  style=\"text-align:center\">\n" +
        "\n" +
        "                            <input style=\"margin:0 20px\" type=\"button\" value=\"取消\" id=\"btn1\" class=\"btn btn-primary btn2\" onclick=\"hideDiv('pop-div') ;\" />\n" +
        "                            <input style=\"margin:0 20px\" type=\"button\" value=\"确认\" id=\"btn2\" class=\"btn btn-primary btn2\" onclick=\"checkAdd_user();\" />\n" +
        "                        </div>\n" +
        "                        <br>";
    popupDiv(400,450,content);
}
function update_user(id) {
    var content;
    content=
        "<br>\n" +
        "                    <h3 style=\"text-align:center;font-size:20px\">信息修改</h3>\n" +
        "                    <h4 style=\"text-align:center;border-top:3px solid #286a3a\"></h4>\n" +
        "                    <br>\n" +
        "                    <div style=\"text-align:center\">\n" +
        "                        <tr>\n" +
        "                            <th style=\"font-size: 16px\">输入密码：</th>\n" +
        "                            <td><input id=\"changeInfo1\" type=\"text\" style=\"width: 90px\"/></td>\n" +
        "\n" +
        "                        </tr>\n" +
        "                        <br>\n" +
        "                        <br>\n" +
        "                        <tr>\n" +
        "                            <th style=\"font-size: 16px\">再次输入：</th>\n" +
        "                            <td><input id=\"changeInfo2\" type=\"text\" style=\"width: 90px\"/></td>\n" +
        "\n" +
        "                        </tr>\n" +
        "                        <br>\n" +
        "                        <br>\n" +
        "                        <tr>\n" +
        "                            <th style=\"font-size: 16px\">邮箱修改：</th>\n" +
        "                            <td><input value='"+id+"' id=\"emplId_update\" type=\"hidden\"><input id=\"changeInfo3\" type=\"text\" style=\"width: 90px\"/></td>\n" +
        "                        </tr>\n" +
        "                        <br>\n" +
        "                        <br>\n" +
        "                        <div id='buttonPanel'  style=\"text-align:center\">\n" +
        "\n" +
        "                            <input style=\"margin:0 20px\" type=\"button\" value=\"取消\" id=\"btn1\" class=\"btn btn-primary btn2\" onclick=\"hideDiv('pop-div') ;\" />\n" +
        "                            <input style=\"margin:0 20px\" type=\"button\" value=\"确认\" id=\"btn2\" class=\"btn btn-primary btn2\" onclick=\"checkSuer();\" />\n" +
        "                        </div>\n" +
        "                        <br>";
    popupDiv(250,250,content);
}
function queryForPages(){
    var params={pageNum:currentPage,pageSize:pageSize,emplName_:emplName_,emplId_:emplId_};
    $.post("/Action/userList.do",params,
        function(data){
//                    var results = JSON.parse(data);
            var admin="";
            var results = data;
            var list = results.Data;
            totalPage = results.totalPageCount;
            rowCount = results.totalItemCount;
            pageNo=results.pageNum;
            var message=results.Message;
            var s = '';
            if (message!=null){
                alert(message);
            }

            if(list.length != 0){
                for(var i=0; i<list.length; i++){
                    if (list[i].emplAdmin>0){
                        admin="管理员";
                    }else {
                        admin="用户";
                    }
                    s += '<tr><td>' +(i+1)
                        + '</td><td>' + list[i].username
                        + '</td><td>' + list[i].id
                        + '</td><td>' + list[i].email
                        + '</td><td>' + admin
                        + '</td><td><a href="#" onclick="update_user(\''+list[i].id+'\')">修改</a>'
                        + '</td></tr>';

                }

                $('#tb').html(s);
                $(emplId).val(null);
            } else{
                $('#tb').html("");
                $(emplId).val(null);
            }
        })

}
function popupDiv(width,height,content) {
    // 获取传入的DIV
    var $div_obj = $("#pop-div");
//            style="width: 250px;height:250px;"
    $div_obj.height(height);
    $div_obj.width(width);
    // 计算机屏幕高度
    var windowWidth = $(document.body).width();
    // 计算机屏幕长度
    var windowHeight = $(document.body).height();
    // 取得传入DIV的高度
//            var popupHeight = $div_obj.height();
    // 取得传入DIV的长度
//            var popupWidth = $div_obj.width();

    // // 添加并显示遮罩层
    $("<div id='bg'></div>").width(windowWidth)
        .height(windowHeight).click(function() {
        //hideDiv(div_id);
    }).appendTo("body").fadeIn(200);
    $div_obj.html(
        content
    )
    $div_obj.css({
        "position" : "absloute",
        "left" : windowWidth / 2 - height / 2.3,
        "top" : windowHeight / 2.4 - width /2.3,

        "opacity" : "show"
    }).animate({

        opacity : "show"
    }, "slow");


}
function hideDiv() {
    $("#bg").remove();
    $("#pop-div").animate({
//                left : 0,
//                top : 0,
        opacity : "hide"

    }, "slow");

}
function checkAdd_user() {
    var admin=document.getElementById('addInfo_Admin').value; //选择所有name="'test'"的对象，返回数组
    if(checkName($("#addInfo_Name").val())==true){
        if (checkPassword()==true){
            if (checkId($("#addInfo_Id").val())==true){
                if(checkEmail($("#addInfo_Email").val())==true){
                    addNewUser($("#addInfo_Name").val(),fistInput_,$("#addInfo_Email").val(),$("#addInfo_Id").val(),admin);
                }else {
                    alert("邮箱有误");
                }
            }else {
                alert("ID有误");
            }
        }else {
            alert("密码有误");
        }
    }else {
        alert("名字有误");
    }
}
function checkSuer() {
    fistInput_=$("#changeInfo1").val();
    secondInput_=$("#changeInfo2").val();
    email_=$("#changeInfo3").val();
    var emplId_Info=$("#emplId_update").val();
    if (fistInput_ && secondInput_ && !email_){
        if (checkPassword(emplId_Info)==true){
            changeInfo(emplId_Info);
        }
    }else if(fistInput_ && secondInput_ && email_){

        if (checkEmail(email_)==true){
            changeEmplEmail(emplId_Info);
            if (checkPassword(emplId_Info)==true){
                changeInfo(emplId_Info);
            }
        }else {
            alert("(＝。＝)邮箱格式有误");
        }
    }else if(!fistInput_ && !secondInput_ && email_){
        if (checkEmail(email_)==true){
            changeEmplEmail(emplId_Info);

        }else {
            alert("(＝。＝)邮箱格式有误");
        }

    }else {
        if (!secondInput_ || !fistInput_){
            alert("请输入密码");
        }else {
            hideDiv('pop-div');
        }

    }
}
function checkEmail(str){
    var re = /^[A-Za-z\d]+([-_.][A-Za-z\d]+)*@([A-Za-z\d]+[-.])+[A-Za-z\d]{2,4}$/;
    if (re.test(str)) {
        return true;
    } else {
        return false;
    }
}
function checkId(str){
    var re =/^[0-9]{1,20}$/;
    if (re.test(str)) {
        return true;
    } else {
        return false;
    }
}
function checkName(str){
    var re =/^[\u4e00-\u9fa5]{2,4}$/;
    if (re.test(str)) {
        return true;
    } else {
        return false;
    }
}
function checkPassword(emplId_Info) {
    fistInput_=$("#changeInfo1").val();
    secondInput_=$("#changeInfo2").val();
    var PASSWORD_FORMAT =/^[A-Za-z0-9]+$/;
    if(fistInput_ && secondInput_==null){
        alert("(＝。＝)密码不能为空");
    }else if(secondInput_.length<6||fistInput_.length<6){
        alert("(＝。＝)密码不能小于六位哦");
    }else if(fistInput_!=secondInput_){
        alert("╮(╯_╰)╭两次密码输入不一致");
    }else if(PASSWORD_FORMAT.test(fistInput_)==false||PASSWORD_FORMAT.test(secondInput_)==false){
        alert("╮(╯_╰)╭密码组成只能是数字或字母");
    }else {
        return true
    }
}
$(document).ready(

    function(){

        $('#basic-modal .basic').click(function () {
            $('#basic-modal-content').modal();
            return false;
        });

        //员工号查询查询
        $("#emplIdBotton").click(function() {
            emplId_ = $(emplId).val();
            if(EMOLID_FORMAT.test(emplId_)==false && emplId_!=null){
                alert("(＞﹏＜)您输入的员工号格式有误,请输入数字");
            }else {
                queryForPages();
            }

        });
        //首页
        $("#firstPage").click(function(){
            currentPage="1";
            queryForPages();
        });
        //上一页
        $("#previous").click(function(){
            if(currentPage>1)
            {
                currentPage-- ;
            }
            queryForPages();
        });
        //下一页
        $("#next").click(function(){
            if(currentPage<totalPage)
            {
                currentPage++ ;
            }
            queryForPages();
        });
        //尾页
        $("#last").click(function(){
            currentPage = totalPage;
            queryForPages();
        });
        queryForPages();
    }
);