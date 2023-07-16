function operate(btn){
	const optionsNode = document.querySelector(".container .options");
	const optionsContainer = document.querySelector(".options-container");
	const unfold = btn.getAttribute("unfold");
	if(unfold && unfold==="1"){
		btn.innerText = "取消查看";
		optionsNode.classList.add("unfold");
		optionsContainer.style.height = "auto";
	}else{
		btn.innerText = "事件记录";
		optionsNode.classList.remove("unfold");
		optionsContainer.style.height = "0px";
	}
	btn.setAttribute("unfold", unfold === "0" ? "1" : "0");
}
//人流管理查看
function personManager(){
	var pop = document.getElementById('person');
	if(index %2 ==0){
		pop.style.display="block";
		console.log("显示"+index)
	}else{
		pop.style.display="none";
		console.log("关闭"+index)
	}
	// else
	index++;
}
function openRoadMsg(){
	var pop = document.getElementById('road_msg');
	pop.style.display="block";
}
// 视频控制
$(function(){
	$("#show_view").click(function(){
		$("#myVedio").show();
	})
	$("#video-close").click(function(){
		$("#myVedio").hide();
	})
})
// 折叠面板
layui.use('element', function(){
  var element = layui.element;
});