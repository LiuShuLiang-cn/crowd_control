// 全局变量
var flag = false;
var myselect = null;
var cgindex = null;
var myselect1 = null;
var username;
var system;
var roleCurrent;
var result;
$.get("/user/currentuser", function (data1) {
    roleCurrent = data1.role
    username = data1.userName;
    system = data1.systemname;
})

layui.use(["form", "element", "jquery"], function () {
    //触发城管部署事件
    var layer = layui.layer
        , form = layui.form
        , laypage = layui.laypage
        , element = layui.element
        , laydate = layui.laydate
        , util = layui.util;
    util.event('test-active', {
        'test-form': function () {
            layer.open({
                type: 1
                , resize: false
                , shadeClose: true
                , area: '350px'
                , title: '人员部署'
                , content: ['<ul class="layui-form layui-form-pane" style="margin: 15px;">'
                    , '<li class="layui-form-item">'
                    , '<label class="layui-form-label">部署人数</label>'
                    , '<div class="layui-input-block">'
                    , '<input class="layui-input" lay-verify="required number" name="num">'
                    , '</div>'
                    , '</li>',
                    '<li class = "layui-form-item">',
                    '<label class="layui-form-label">对象</label>',
                    '<div class="layui-input-block">',
                    '<input class="layui-input" lay-verify="required number" name="role">',
                    '</div>',
                    '</li>',
                    , '<li class="layui-form-item">'
                    , '<label class="layui-form-label">所在里</label>'
                    , '<div class="layui-input-block">'
                    , '<select name="postTo" lay-filter="category" id="cgchoose">'
                    , '<option value="钱塘里">钱塘里</option>'
                    , '<option value="劝业里">劝业里</option>'
                    , '<option value="学士里">学士里</option>'
                    , '<option value="任和里">任和里</option>'
                    , '<option value="东坡里">东坡里</option>'
                    , '<option value="龙翔里">龙翔里</option>'
                    , '<option value="长生里">长生里</option>'
                    , '<option value="将军里">将军里</option>'
                    , '<option value="泗水里">泗水里</option>'
                    , '<select>'
                    , '</div>'
                    , '</li>'
                    , '<li class="layui-form-item" style="text-align:center;">'
                    , '<button type="button" lay-submit lay-filter="*" class="layui-btn" id="btn">提交</button>'
                    , '</li>'
                    , '</ul>'].join('')
                , success: function (layero, index) {
                    layero.find('.layui-layer-content').css('overflow', 'visible');
                    form.render();
                    form.on('submit(*)', function (data) {
                        layer.msg(JSON.stringify(data.field))
                        var num = data.field.num;
                        var post = data.field.postTo;
                        var role = data.field.role;
                        result = roleCurrent + " : 请"+role+"部署"+num+"人数至"+post;
                        sendMsg(role,2);
                        // $.ajax({
                        //     url: '/topic/' + systemname + "/" + role ,
                        //     type: 'POST',
                        //     dataType: 'JSON',
                        //     success: function () {
                        //         $("#responseData").html(result);
                        //         console.log(result);
                        //     }
                        // })
                        return false;
                    })
                    // 提交按钮点击监听jquery实现
                    // $("#btn").click(function () {
                    //     myselect = document.getElementById("cgchoose");
                    //     cgindex = myselect.selectedIndex;
                    // });

                }

            });
        }
    });
//     地铁中心
    util.event('subway-active', {
        'subway-form': function () {
            layer.open({
                type: 1
                , resize: false
                , shadeClose: true
                , area: '350px'
                , title: '站台部署'
                , content: ['<ul class="layui-form layui-form-pane" style="margin: 15px;">',
                    '<li class = "layui-form-item">',
                    '<label class="layui-form-label">部署操作</label>',
                    '<div class="layui-input-block">',
                    '<input type="radio" name="handle" value="open" title="开启">',
                    '<input type="radio" name="handle" value="close" title="关闭">',
                    '</div>',
                    '</li>',
                    , '<li class="layui-form-item">'
                    , '<label class="layui-form-label">具体站台</label>'
                    , '<div class="layui-input-block">'
                    , '<select name="position" lay-filter="category" id="cgchoose">'
                    , '<option value="龙翔桥A">龙翔桥A</option>'
                    , '<option value="龙翔桥B">龙翔桥B</option>'
                    , '<option value="龙翔桥C">龙翔桥C</option>'
                    , '<option value="龙翔桥D">龙翔桥D</option>'
                    , '<option value="胜利剧院">胜利剧院</option>'
                    , '<option value="延安路解百">延安路解百</option>'
                    , '<option value="延安路湖滨">延安路湖滨</option>'
                    , '<option value="一公园">一公园</option>'
                    , '<select>'
                    , '</div>'
                    , '</li>'
                    , '<li class="layui-form-item" style="text-align:center;">'
                    , '<button type="button" lay-submit lay-filter="*" class="layui-btn" id="btn">提交</button>'
                    , '</li>'
                    , '</ul>'].join('')
                , success: function (layero, index) {
                    form.render();
                    layero.find('.layui-layer-content').css('overflow', 'visible');
                    $ = layui.jquery;
                    form.on('submit(*)', function (data) {
                        layer.msg(JSON.stringify(data.field))
                        var handle = data.field.handle;
                        var position = data.field.position;
                        $.ajax({
                            url: '/chat/' + system + "/" + roleCurrent + "/" +  data.field.role + "/" + handle + "/" + position,
                            type: 'POST',
                            dataType: 'JSON',
                            success: function (data) {
                            }
                        })
                        return false;
                    })

                }

            });

        }
    });
})