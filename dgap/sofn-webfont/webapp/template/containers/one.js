import React, { Component } from 'react'
import View from './oneView'
const state = {
    BREADS: [{
        name: '数据共享系统',
    }, {
        name: '资源目录',
        link: '#'
    }, {
        name: '资源管理'
    }],
    PANEL: [{
        title: '审核状态',
        key: 'state',
        type: 'select',
        // callback: () => {},
        styles: { minWidth: 80 },
        defaultValue: 'all',
        options: [{
            text: '全部',
            value: 'all',
            selected: true
        }, {
            text: '通过',
            value: 'yes'
        }, {
            text: '不通过',
            value: 'no'
        }]
    }, {
        title: '审核状态',
        key: 'state',
        type: 'input',
        callback: () => {},
        styles: { width: 100 },
        defaultValue: ''
    }, {
        title: '审核状态',
        key: 'state',
        type: 'number',
        callback: () => {},
        styles: { width: 100 },
        defaultValue: ''
    }, {
        title: '新增字段',
        key: 'state',
        type: 'button',
        callback: () => {}
    }],
    ACTIONS: [{
        title: '生成表',
        type: 'button',
        callback: () => {}
    }, {
        title: '删除表',
        type: 'button',
        callback: () => {}
    }]
};

export default class Page extends Component {
    render() {
        return <View {...state} />
    }
}
