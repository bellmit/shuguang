import React, {Component} from 'react'
import {Menu, Icon} from 'antd';
import {Api} from '../api'

const SubMenu = Menu.SubMenu;

export default class Sider extends Component {
    constructor(props) {
        super(props);
        this.state = {
            current: '1',
            openKeys: [],
            menus: []
        }
        this.handleClick = this.handleClick.bind(this);
        this.onOpenChange = this.onOpenChange.bind(this);
    }
    handleClick(e) {
        console.log('Clicked: ', e);
        this.setState({current: e.key});
    }
    onOpenChange(openKeys) {
        const state = this.state;
        const latestOpenKey = openKeys.find(key => !(state.openKeys.indexOf(key) > -1));
        const latestCloseKey = state.openKeys.find(key => !(openKeys.indexOf(key) > -1));

        let nextOpenKeys = [];
        if (latestOpenKey) {
            nextOpenKeys = this.getAncestorKeys(latestOpenKey).concat(latestOpenKey);
        }
        if (latestCloseKey) {
            nextOpenKeys = this.getAncestorKeys(latestCloseKey);
        }
        this.setState({openKeys: nextOpenKeys});
    }
    getAncestorKeys(key) {
        const map = {
            sub3: ['sub2']
        };
        return map[key] || [];
    }
    each(menus) {
        return menus.map((menu, index) => {
            return menu.children
                ? (
                    <SubMenu key={menu.id} title={< span > <Icon type="mail"/> < span > {
                        menu.text
                    } </span></span >}>
                        {this.each(menu.children)}
                    </SubMenu>
                )
                : <Menu.Item key={menu.id}>
                    <a href={menu.menuUrl}>{menu.text}</a>
                </Menu.Item>;
        })
    }
    render() {
        const {menus} = this.state;
        return (
            <Menu  mode="inline" selectedKeys={[this.state.current]} onOpenChange={this.onOpenChange} onClick={this.handleClick}>
                {menus.length && this.each(menus[0].children)}
            </Menu>
        )
    }
    fetch() {
        const params = {}
        Api.getResourceMenu(params).then((res) => {
            this.setState({menus: res, loading: false})
        })
    }
    componentDidMount() {
        this.fetch()
    }
}
