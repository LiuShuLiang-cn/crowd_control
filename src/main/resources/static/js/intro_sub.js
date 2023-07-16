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
            element:document.querySelector('#sub_control'),
            intro: '控制地铁站和公交站运行与否',
            arrow:"display:none"
        }

    ]
}).start();