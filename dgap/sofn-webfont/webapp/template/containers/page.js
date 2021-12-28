import '../styles/resource.less'
import React, { Component } from 'react'
import { Tabs, DatePicker, Button, Breadcrumb, Input, InputNumber, Row, Col, Select } from 'antd';
import { SgBreadcrumb, SgModal, Menu, Table } from '../components'

const Option = Select.Option;

function handleChange(value) {
    console.log(`selected ${value}`);
}

export default class Home extends Component {
	constructor(props) {
	    super(props);
	    this.state = {
	        visibleTable: true,
	        visibleField: false
	    }
	    this.showTable = this.showTable.bind(this);
	    this.showField = this.showField.bind(this);
	    this.openTable = this.openTable.bind(this);
	    this.openField = this.openField.bind(this);
	}
	
	showTable(visible){
		this.setState({ visibleTable: visible });
	}
	showField(visible){
		this.setState({ visibleField: visible });
	}

	openTable(){
		this.showTable(true);
	}	
	openField(){
		this.showField(true);
	}

    render() {
        const breads = [{
            name: '数据共享系统',
        }, {
            name: '资源目录',
            link: '#'
        }, {
            name: '资源管理'
        }];
        const { visibleTable, visibleField } = this.state;
        return (
            <div style={{backgroundColor:'#404040'}}>
				{<Row>
					<Col span={24}><br /><br /><br /><br /><br /></Col>
				</Row>}
				<Row justify={'center'}>
					<Col span={4}>
						<Menu />
					</Col>
					<Col span={20} style={{borderLeft:'1px solid #e9e9e9', backgroundColor:'#FFF', borderTopLeftRadius:5, borderBottomLeftRadius:5}}>
						{/* filter */}
						<div style={{ marginBottom: 16, marginTop:12 }}>
							<Row type={'flex'} justify={'center'} align={'middle'}>
								<Col span={23}>
									<SgBreadcrumb breads={ breads } />
								</Col>
							</Row>
						</div>
						<div style={{ marginBottom: 16 }}>
							<Row type={'flex'} justify={'center'} align={'middle'}>
								<Col span={18}>
									<Button type="primary" onClick={this.openField}>新增字段</Button>
								</Col>
								<Col span={5} style={{textAlign:'right'}}>
									<SgModal style={{marginRight:10}} visible={ visibleTable } show={ this.showTable } title={'表名设计'}>
										<div>
											<Row style={{marginBottom:10}} type={'flex'} justify={'center'} align={'middle'}>
												<Col span={4}>
													<span>中文表名</span>
												</Col>
												<Col span={16}><Input placeholder="中文表名" /></Col>
											</Row>
											<Row style={{marginBottom:10}} type={'flex'} justify={'center'} align={'middle'}>
												<Col span={4}>
													<span>英文表名</span>
												</Col>
												<Col span={16}><Input placeholder="英文表名" /></Col>
											</Row>
										</div>
									</SgModal>
									<SgModal style={{marginRight:10}} visible={ visibleField } show={ this.showField } title={'新增字段'}>
										<div>
											<Row style={{marginBottom:10}} type={'flex'} justify={'center'} align={'middle'}>
												<Col span={4}>
													<span>中文字段名</span>
												</Col>
												<Col span={16}><Input placeholder="中文字段名" /></Col>
											</Row>
											<Row style={{marginBottom:10}} type={'flex'} justify={'center'} align={'middle'}>
												<Col span={4}>
													<span>英文字段名</span>
												</Col>
												<Col span={16}><Input placeholder="英文字段名" /></Col>
											</Row>
											<Row style={{marginBottom:10}} type={'flex'} justify={'center'} align={'middle'}>
												<Col span={4}>
													<span>字段类型</span>
												</Col>
												<Col span={16}>
													<Select size="large" defaultValue="text" style={{ width: '100%' }} onChange={handleChange}>
												      <Option value="text">文本</Option>
												      <Option value="int">整数</Option>
												      <Option value="float" disabled>浮点数</Option>
												      <Option value="time">时间</Option>
												      <Option value="date">日期</Option>
												    </Select>
												</Col>
											</Row>
											<Row style={{marginBottom:10}} type={'flex'} justify={'center'} align={'middle'}>
												<Col span={4}>
													<span>字段长度</span>
												</Col>
												<Col span={16}>
													<InputNumber min={1} max={10} defaultValue={3} placeholder="字段长度"/>
												</Col>
											</Row>
										</div>
									</SgModal>
									<Button type="primary" onClick={this.openTable} style={{marginRight: 10}}>生成表</Button>
									<Button type="primary">删除表</Button>
								</Col> 
							</Row>
						</div>
						<Row type={'flex'} justify={'center'}>
							<Col span={23}>
								<Table />
							</Col>
						</Row>
					</Col>
				</Row>
			</div>
        )
    }
}
