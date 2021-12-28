import '../../styles/resource.less'
import Header from './header'
import Siderbar from './siderbar'
import Content from './content'

import { Row, Col } from 'antd';
import React, { Component } from 'react'

const styles = {
    borderLeft: '1px solid #e9e9e9',
    backgroundColor: '#FFF',
    borderTopLeftRadius: 5,
    borderBottomLeftRadius: 5
};

export default class Page extends Component {
    render() {
        return (
            <div style={{backgroundColor:'#404040'}}>
				{/*通用头部*/}
				<Row>
					<Col span={24}>
						<Header />
					</Col>
				</Row>

				<Row justify={'center'}>
					<Col span={4}>
						{/*侧边栏*/}
						<Siderbar />
					</Col>
					<Col span={20} style={styles}>
						{/*内容区*/}
						<Content />
					</Col>
				</Row>
			</div>
        )
    }
}
