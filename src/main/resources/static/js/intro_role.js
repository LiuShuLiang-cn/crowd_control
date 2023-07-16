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
            element:document.querySelector('.moveto'),
            intro:'此处市民通过人员转移避免拥堵'
        }

    ]
}).start();