let count;
setTimeout(function () {//每10分钟弹窗预警处理提示
    layui.use(["form", "element", "jquery"], function () {
        var layer = layui.layer
        layui.use('layer', function() {
            $.get("/issue/count/",{"systemName":systemname,"roleTopic": roleTopic},function (data){
                count = data;
            })
            if(count>10){
                console.log("已完成10道题目"+count)
            }
            else {
                layer.open({
                    type: 2,
                    // skin: 'layui-layer-molv',
                    title: '选择题',
                    content:['/user/question','no'] ,//不允许出现滚动条
                    area:['900px', '460px']
                });
            }

        });
    })

},6000) //600秒 600000（10分钟