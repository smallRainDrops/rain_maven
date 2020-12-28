<Header>
    <i-Menu style="background: #1c2b36;box-shadow:0 0 10px #006064;" mode="horizontal" theme="dark" active-name="1">
        <div class="layout-logo"><img  style="width: 38%;vertical-align: middle;margin-right: 23px;" src="http://sums.suning.com/static/temp/logo-02.png" />结算中台管理系统
        </div>
        <div class="layout-nav">
        </div>
        <div style="color:#999; float:right;"><span @click="addMenu">您好</span>,<span>${user!"未知用户"}</span> <span style="padding:0 16px;cursor:pointer;"><a href="${ssoHost}/logout?service=${susssHost}" style="color:#999;">退出登录</a></span></div>
    </i-Menu>
</Header>