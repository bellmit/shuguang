import React, { Component } from 'react'
import { Tree } from 'antd';
const TreeNode = Tree.TreeNode;


export default class SgTree extends Component {
    constructor(props) {
        super(props);
        const keys = this.props.keys;
        this.state = {
            defaultExpandedKeys: keys,
            defaultSelectedKeys: keys,
            defaultCheckedKeys: keys,
        }
        this.onSelect = this.onSelect.bind(this);
        this.onCheck = this.onCheck.bind(this);
    }

    onSelect(info) {
        console.log('selected', info);
    }
    onCheck(info) {
        console.log('onCheck', info);
    }

    render() {
        return (
            <Tree className="myCls" showLine 
        defaultExpandedKeys={this.state.defaultExpandedKeys}
        defaultSelectedKeys={this.state.defaultSelectedKeys}
        defaultCheckedKeys={this.state.defaultCheckedKeys}
        onSelect={this.onSelect} onCheck={this.onCheck}>
        <TreeNode title="仓库表目录" key="0-0">
          <TreeNode title="用户表" key="0-0-0">
            <TreeNode title="基础信息" key="0-0-0-0" />
            <TreeNode title="登录信息" key="0-0-0-1" />
          </TreeNode>
          <TreeNode title="产品表" key="0-0-1">
            <TreeNode title={<span style={{ color: '#08c' }}>产品分类</span>} key="0-0-1-0" />
          </TreeNode>
        </TreeNode>
      </Tree>
        );
    }
}

