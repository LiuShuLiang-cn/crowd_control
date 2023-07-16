var stompClient = null;
var username;
var systemname;
var roleTopic;
var deployList
var peopleList;
var regionList = new Array();
var numberList = new Array();
var status;
var peopleLength;
var countAll;


$.get("/user/currentuser", function (data) {
    roleTopic = data.role
    username = data.userName;
    systemname = data.systemname;

    var myTimeTarget = setInterval(function () {
        /**
         * 执行定时任务
         */
        $.get("/data/" + systemname, function (data) {
            /**
             * 实时(每秒)从redis中获取数据
             */
        })
    }, 1000);

    $(function () {
        /**
         * 加载完浏览器后调用connect（），打开通道
         */
        connect();
    })

    function connect() {
        //连接SockJS的endpoint名称为"endpointMark"
        var socket = new SockJS("http://localhost:8015/public");
        //使用STMOP子协议的WebSocket客户端
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            /**
             * 连接WebSocket服务端
             */
            console.log('Connected:' + frame);
            //接收广播信息
            stompTopic();
        })
    }

    function stompTopic() {
        /**
         * 通过stompClient.subscribe订阅目标(destination)发送的消息（广播接收信息）
         */
        stompClient.subscribe('/data/' + systemname, function (response) {
            data = JSON.parse(response.body)
            peopleLength = length(data.numberOfPeopleList)
            deployList = data.deployList
            peopleList = data.numberOfPeopleList;
            systemTime = data.systemTime;
            changeOfPeople();
        })
    }

    // stompClient.debug = () => {
    //     /**
    //      * 消除浏览器控制台中websocket数据传输
    //      */
    // };

})

function changeOfPeople() {
    if (JSON.stringify(systemname) === '{}')
        console.Tog("error");
    if (roleTopic == "公安" || roleTopic == "交警" || roleTopic == "志愿者" || roleTopic == "城管" || roleTopic == "城管" || roleTopic == "市民") {
        var marker = new AMap.Marker({
            position: new AMap.LngLat(120.160989, 30.253953),
            icon: '/static/img/fountain.png',
            offset: new AMap.Pixel(-20, -30)
        });
        var viaMarker = new AMap.Marker({
            position: new AMap.LngLat(120.160989, 30.253953),
            // 将一张图片的地址设置为 icon
            icon: '/static/img/fountain_close.png',
            // 设置了 icon 以后，设置 icon 的偏移量，以 icon 的 [center bottom] 为原点
            offset: new AMap.Pixel(-20, -30)
        });
        // 喷泉状态
        $.get("/operate/" + systemname + "/", function (res) {
            activity = res.statusActivity;
            if (activity == 1) {
                viaMarker.setMap(null);
                marker.setMap(null);
                map.add(marker);
            } else {
                viaMarker.setMap(null);
                marker.setMap(null);
                map.add(viaMarker);
            }
        })
    }
    countAll = 0;
    for (i = 0; i < peopleList.length; i++) {
        regionList[i] = peopleList[i].region;
        numberList[i] = peopleList[i].number;
        countAll += numberList[i];
    }
    if (roleTopic == "指挥中心") document.getElementById('count').innerHTML = countAll + '人';
    if (roleTopic != "市民") {
        $(document).ready(function () {
            //获取已有echarts实例的DOM节点。判断是否初始化
            let chart = echarts.getInstanceByDom(document.getElementById("realTime-stream-line"));
            let numList = numberList;
            let dataList = regionList;
            if (chart == null) {
                var myChart = echarts.init(document.querySelector("#realTime-stream-line")); //初始化 echarts 实例对象
                option = {
                    tooltip: {  //鼠标放上去时提示属性
                        trigger: "axis"
                    },
                    grid: {
                        left: "-30",
                        top: "25",
                        right: "0",
                        bottom: "0",
                        containLabel: true
                    },
                    xAxis: [
                        {
                            type: "category",
                            boundaryGap: false,
                            // x轴更换数据
                            data: dataList,
                            // 文本颜色为rgba(255,255,255,.6)  文字大小为 12
                            axisLabel: {
                                textStyle: {
                                    color: "rgba(255,255,255,.6)",
                                    fontSize: 12
                                }
                            },
                            // x轴线的颜色为   rgba(255,255,255,.2)
                            axisLine: {
                                lineStyle: {
                                    color: "rgba(255,255,255,.2)"
                                }
                            }
                        }
                    ],
                    yAxis: [
                        {
                            type: "value",
                            name: '人',
                            nameTextStyle: {
                                color: "rgb(255,255,255)"
                            },
                            axisTick: {show: false},
                            axisLine: {
                                lineStyle: {
                                    color: "rgba(255,255,255,.1)"
                                }
                            },
                            axisLabel: {
                                textStyle: {
                                    color: "rgba(255,255,255,.6)",
                                    fontSize: 12
                                },
                            },
                            // 修改分割线的颜色
                            splitLine: {
                                lineStyle: {
                                    color: "rgba(255,255,255,.1)"
                                }
                            }
                        }
                    ],
                    series: [
                        {
                            name: "实时人流量",
                            type: "line",
                            smooth: true,
                            lineStyle: {
                                normal: {
                                    color: "#0d9cec",
                                    width: 2
                                }
                            },
                            areaStyle: {
                                normal: {
                                    color: new echarts.graphic.LinearGradient(
                                        0,
                                        0,
                                        0,
                                        1,
                                        [
                                            {
                                                offset: 0,
                                                color: "rgb(21,196,213,0.4)"
                                            },
                                            {
                                                offset: 0.8,
                                                color: "rgba(0, 216, 135, 0.1)"
                                            }
                                        ],
                                        false
                                    ),
                                    shadowColor: "rgba(0, 0, 0, 0.1)"
                                }
                            },
                            // 设置拐点 小圆点
                            symbol: "circle",
                            // 拐点大小
                            symbolSize: 5,
                            // 设置拐点颜色以及边框
                            itemStyle: {
                                color: "#25b1e7",
                                borderColor: "rgba(221, 220, 107, .1)",
                                borderWidth: 12
                            },
                            // 开始不显示拐点， 鼠标经过显示
                            showSymbol: false,
                            data: numList,
                            markPoint: {
                                label: {
                                    show: true,
                                    position: "top",
                                    distance: 7,
                                    offset: [1, 1],
                                    formatter: '{c}',
                                    color: "#25b1e7",
                                    fontWeight: "1000",
                                },
                                symbol: "circle",
                                symbolSize: 10,
                                symbolOffset: [0, 0],
                                data: [
                                    {type: 'max', name: '最大值'},
                                ]
                            },
                        }
                    ]
                };
                myChart.setOption(option);
                // 4. 让图表跟随屏幕自动的去适应
                window.addEventListener("resize", function () {
                    myChart.resize();
                });
            }
            // 左二：湖滨九里每日峰值人流恢复监测
            let chart1 = echarts.getInstanceByDom(document.getElementById("day-stream-line"))
            let option1;
            let rate = []
            let peakValue = 70000;
            for (let i = 0; i < numList.length; i++) {
                rate[i] = (numList[i] / peakValue).toFixed(2) * 100
            }
            if (chart1 == null) {
                var myChart1 = echarts.init(document.querySelector("#day-stream-line"));
                option1 = {
                    color: "rgb(52,157,213)",
                    tooltip: {
                        trigger: "axis"
                    },
                    grid: {
                        left: "-30",
                        top: "35",
                        right: "0",
                        bottom: "-30",
                        containLabel: true
                    },
                    legend: {
                        textStyle: {
                            color: "#4c9bfd"
                        },
                        data: ['人流峰值', '恢复比例']
                    },
                    xAxis: [
                        {
                            type: "category",
                            boundaryGap: false,
                            // x轴更换数据
                            data: dataList,
                            // 文本颜色为rgba(255,255,255,.6)  文字大小为 12
                            axisLabel: {
                                textStyle: {
                                    color: "rgba(255,255,255,.6)",
                                    fontSize: 12
                                }
                            },
                            // x轴线的颜色为   rgba(255,255,255,.2)
                            axisLine: {
                                lineStyle: {
                                    color: "rgba(255,255,255,.2)"
                                }
                            }
                        }
                    ],
                    yAxis: [
                        {
                            type: "value",
                            name: '人',
                            min: 0,
                            max: 60000,
                            nameTextStyle: {
                                color: "rgb(255,255,255)"
                            },
                            axisTick: {
                                show: false // 去除刻度线
                            },
                            axisLabel: {
                                color: "#4c9bfd" // 文本颜色
                            },
                            axisLine: {
                                show: false // 去除轴线
                            },
                            splitLine: {
                                lineStyle: {
                                    color: "#012f4a" // 分割线颜色
                                }
                            }
                        },
                        {
                            type: "value",
                            min: 0,
                            max: 100,
                            name: '%',
                            nameTextStyle: {
                                color: "rgb(255,255,255)"
                            },
                            axisTick: {
                                show: false // 去除刻度线
                            },
                            axisLabel: {
                                color: "#4c9bfd" // 文本颜色
                            },
                            axisLine: {
                                show: false // 去除轴线
                            },
                            splitLine: {
                                lineStyle: {
                                    color: "#012f4a" // 分割线颜色
                                }
                            }
                        }
                    ],
                    series: [
                        {
                            name: "人流峰值",
                            type: "line",
                            smooth: true,
                            lineStyle: {
                                normal: {
                                    color: "#ece80d",
                                    width: 2
                                }
                            },
                            showSymbol: false,
                            data: numList
                        },
                        {
                            name: "恢复比例",
                            type: "bar",
                            yAxisIndex: 1,
                            data: rate
                        }
                    ]
                };
                myChart1.setOption(option1);
                // 4. 让图表跟随屏幕自动的去适应
                window.addEventListener("resize", function () {
                    myChart1.resize();
                });
            }


        })
    }
}

//object对象长度
function length(obj) {
    var count = 0;
    for (var i in obj) {
        count++;
    }
    return count;
};

//展示数据
function showMsg(data) {
    if (data.type == 2) {
        // 普通消息
        // $("#responseData").html(data.text);
        var responseData = document.getElementById('responseData');
        var p = document.createElement('p');
        p.appendChild(document.createTextNode(data.text));
        responseData.appendChild(p);
        $("#options").html(data.text);
        // var optionsData = document.getElementById('options');
        // var pp = document.createElement('pp');
        // pp.appendChild(document.createTextNode(data.text));
        // optionsData.appendChild(pp);
    } else if (data.type == 1) {
        // 指令
    } else if (data.type == 4) {
        // 群发
    }
}

function sendMsg(toRoleTopic, eventType) {            //根据

    //0对照IMessage，
    var imessage = {};
    imessage.systemName = system;
    imessage.type = eventType;
    imessage.toRole = toRoleTopic;
    imessage.text = result;
    imessage.fromRole = roleTopic;
    imessage.dateTime = new Date();
    showMsg(imessage)
    // showMsg(result)
    stompClient.send("/topic/" + system + "/" + toRoleTopic, {}, JSON.stringify(imessage));
}

