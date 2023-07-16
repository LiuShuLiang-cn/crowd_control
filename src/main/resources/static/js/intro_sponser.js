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
            element: document.querySelector("#sponser_fountain"),
            intro: '点击此处控制音乐喷泉开关'
        },
        {
            element: document.querySelector('.options'),
            intro: '点击此处查看指挥中心发布的指令'
        }

    ]
}).start();