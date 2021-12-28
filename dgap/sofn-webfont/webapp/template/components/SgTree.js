import React, { Component } from 'react'
import { Tree } from 'antd';
const TreeNode = Tree.TreeNode;

export default class SgTree extends Component {
    constructor(props) {
        super(props);
    }
	each(trees){
	    return trees.map((tree, index) => {
	        return tree.children && tree.children.length > 0 ? (
	            <TreeNode key={ tree.id } title={tree.text}>
	                { this.each(tree.children) }
	            </TreeNode>
	        ) : <TreeNode key={tree.id} title={tree.text}></TreeNode>;
	    })
	}
    render() {
    	const {trees, ...options} = this.props;
    	const keys = trees.length ? [trees[0].id] : [];
        if (trees.length) {
            return (
                <Tree defaultExpandAll={true} 
                showLine {...options} 
                defaultExpandedKeys={keys}
                defaultSelectedKeys={keys}
                defaultCheckedKeys={keys}>
                    { this.each(trees) }
                </Tree>
            )
        }else{
            return <div></div>
        }
    }
}
