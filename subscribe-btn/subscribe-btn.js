// components/subscript-btn.js
Component({
  /**
   * 组件的方法列表
   */
  methods: {
    subscribe: function(){
      //调用订阅
      wx.requestSubscribeMessage({
         tmplIds: ["P3_l-4yF_8uDikq_xxxx"],
         success: (res) => {
          if (res['P3_l-4yF_8uDikq_xxxx'] === 'accept'){
            //登录(获取用户授权码，服务端记录用户订阅次数用到)
            wx.login({
              success: function(res) {
                //调用后端接口，记录订阅次数
                wx.request({
                  url:"https://xxxx.com/subscribe?templateId=P3_l-4yF_8uDikq_xxxx="+res["code"],
                  success: (res) =>{
                    wx.showToast({
                      title: '已成功订阅！',
                      icon: 'success',
                      duration: 1500
                  })
                  }
                })
              }
            });
          }
         }
      })
    }
  }
})
