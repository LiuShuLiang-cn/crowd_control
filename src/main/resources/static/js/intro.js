introJs().setOptions({
    tooltipPosition: 'left',
    steps: [{
        title: 'Welcome',
        intro: '欢迎进入湖滨商圈跨年夜人流精细化管理虚拟仿真实验 👋'
    },
    {
        element: document.querySelector('.dropbtn'),
        intro: '这是登录角色'
    },
    {
        element:document.querySelector('.managebutton'),
        intro: '这里查看音乐喷泉是否演出',
        arrow:"display:none"
    },
    {
        title: 'Farewell!',
        element:document.querySelector('#conversationDiv'),
        intro:'这里指挥中心进行人员部署'
    }

    ]
}).start();