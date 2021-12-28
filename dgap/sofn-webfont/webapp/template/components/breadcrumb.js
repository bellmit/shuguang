import React, { Component } from 'react'
import { Breadcrumb } from 'antd'

export default class SgBreadcrumb extends Component{
	render(){
		const { breads } = this.props;
		return (
			<Breadcrumb separator=">">
				{
					breads.map((bread, index)=>{
						return (
							bread.link ? <Breadcrumb.Item key={ index } href={ bread.link }>{ bread.name }</Breadcrumb.Item> :<Breadcrumb.Item key={ index }>{ bread.name }</Breadcrumb.Item>
						)
					})
				}
			</Breadcrumb>
		)
	}
}
