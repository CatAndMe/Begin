
function loadData(pageNum, pageSize){
    $(".detail").remove(); //每次重新从 API 数据接口获取数据都要先清除原先表格 `<tr>` 的内容
    $.ajax({
        url: "/history_alarm",
        type: "POST",
        data: JSON.stringify({pageNum:pageNum,pageSize:pageSize}),
        success:function(result){
            var results = JSON.parse(result);
            var list = results.Data;
            var totalCount = results.totalItemCount;
            var pages = results.totalPages;
            var pageNo=results.pageNum;
            if(list.length != 0){
                for(var i=0; i<list.length; i++){
                    var formId = list[i].formId;
                    var emplName = list[i].emplName;
                    var emplId = list[i].emplId;
                    var time = list[i].time;
                    var login = list[i].login;
                    var loginState = list[i].loginState;
                    var reason = list[i].reason;
                    var trueTime = list[i].trueTime;
                    innerHTML+=<tr></tr>
                    innerHTML+="<t"

                }
                $("#table").show();
                $("#footer").show();
                displayFooter(totalCount, pages, pageNo);
            } else{
                $("#table").hide();
                $("#footer").hide();
            }
        },
        error:function(){
            //error handle function
        }
    });


}

function displayFooter(totalCount, pages, pageNo){
    var newText = '共' + totalCount + '条，' + '第' + pageNo + '页，' + '共' + pages + '页';
    $("#summary").text(newText);
}