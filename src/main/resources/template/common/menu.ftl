<Sider hide-trigger collapsible :collapsed-width="40" v-model="isCollapsed" :style="{background: '#fff'}">
    <p class="switchMenu" @click="switchMenu">
        <Icon v-if="!isCollapsed" type="md-arrow-dropleft" ></Icon>
        <Icon v-else type="md-arrow-dropright" ></Icon>
    </p>
    <i-Menu v-show="!isCollapsed" :active-name="defaultActive" theme="dark" width="auto" @on-select="selectMenu" :open-names="openNames" accordion>
        <template v-for="menu in menus">
            <Menu-Item v-if="menu.isSun == 1 && menu.url.startsWith('/')" :name="menu.id">
                <Icon type="ios-bookmark"><span>{{menu.name}}</span>
            </Menu-Item>
            <Submenu v-else :name="menu.name">
                <template slot="title">
                    <Icon type="ios-browsers"></Icon>
                    {{menu.name}}
                </template>
                <Menu-Item v-for="subMenu in menu.subMenuTrees" :key="subMenu.id" :name="subMenu.url"
                           v-show="subMenu.name != '缓存管理' || alwaysShow">
                    <Icon type="logo-buffer"></Icon><span>{{subMenu.name}}</span>
                </Menu-Item>
            </Submenu>
        </template>
    </i-Menu>
</Sider>
 