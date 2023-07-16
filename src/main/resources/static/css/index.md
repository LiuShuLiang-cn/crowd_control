//Ajax获取
 $.post('url', {}, function(str){
   layer.open({
     type: 1,
     content: str //注意，如果str是object，那么需要字符拼接。
   });
});
[详解](https://blog.csdn.net/DSRCCSDN/article/details/84679067)
layui-form 表单(通常使用item, inline, block)

layui-form-item :默认独占一行
item 内 inline + block 为一行 （标签+输入框)

IMG图片和文字同一行显示，图片和文字居中显示
设定img标签的vertical-align CSS属性
eg:<img src="images/untitled.png" style="width:30px;height:30px;padding:0px;margin:0px;vertical-align:middle;"/><span>居中显示的文字</span>

