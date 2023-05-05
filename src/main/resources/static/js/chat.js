var stompClient = null;
var username;
var systemname;
var roleTopic;

$.get("/user/currentuser", function (data) {
    roleTopic = data.role
    username = data.userName;
    systemname = data.sysname;
    //执行定时任务
    var myTimeTarget = setInterval(function () {
        queryData()
    }, 1000);
    //
    //定时任务要做的事情
    function queryData() {
        $.get("/data/" + systemname, function (data) {
            console.log("data:")
        })
        // $.get("/operate/time/" + systemname, function (data) {
        //     $(".showTime").html(
        //         // write_content
        //         "当前时间：" + data
        //     )
        // })
    }

    var socket = new SockJS("http://localhost:8015/public");
    stompClient = Stomp.over(socket);
    stompClient.connect({"username": username, "roleTopic": roleTopic}, function (frame) {
        stompClient.subscribe('/topic/' + systemname + "/" + roleTopic, function (response) {
            data = JSON.parse(response.body)

        })
        stompClient.subscribe('/data/' + systemname, function (response) {
            data = JSON.parse(response.body)
            console.log(data.numberOfPeopleList)
            insertInfo(data.numberOfPeopleList)
        })
    }, {});
})


//展示数据
function showMsg(data) {
    if (data.type == 2) {
        // 普通消息
        var responseData = document.getElementById('responseData');
        var p = document.createElement('p');
        p.appendChild(document.createTextNode(data.fromRole + ":" + data.text));
        responseData.appendChild(p);
    } else if (data.type == 1) {
        // 指令

    } else if (data.type == 4) {
        // 群发

    }
}

function sendMsg(toRoleTopic, eventType) {            //根据
    var content = document.getElementById('content').value;
    //0对照IMessage，
    var imessage = {};
    imessage.systemName = system;
    imessage.type = eventType;
    imessage.toRole = toRoleTopic;
    imessage.text = content;
    imessage.fromRole = roleTopic;
    imessage.dateTime = new Date();
    showMsg(imessage)
    stompClient.send("/topic/" + system + "/" + toRoleTopic, {}, JSON.stringify(imessage));
}

//----新增信息的插入方法-----
function insertInfo(data) {
    //根据id获取表单信息
    for (var j = 0; j < data.length; j++) {
        document.getElementById('table').deleteRow(j+1);
        var arr = new Array();
        arr[0] = data[j].region;
        arr[1] = data[j].number;
        arr[2] = data[j].longitude;
        arr[3] = data[j].latitude;
        arr[4] = data[j].area;
        arr[5] = data[j].vicinity;
        arr[6] = data[j].status;
        arr[7] = data[j].systemName
        var x = document.getElementById('table').insertRow(1); //获取第一行对象
        for (var i = 0; i < arr.length; i++) {
            x.insertCell(i).innerHTML = arr[i]; //用循环把每个数据插入第一行的每一列
        }
    }

}
