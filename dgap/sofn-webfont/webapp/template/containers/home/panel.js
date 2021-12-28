import React, { Component } from 'react';
import { Menu, SgModal } from '../../components';
import { Tabs, DatePicker, Button, Breadcrumb, Input, Row, Col, Select } from 'antd';
import SgForm from './form';

const Option = Select.Option;

export default class Panel extends Component {
	constructor(props){
		super(props);
		this.handleChange = this.handleChange.bind(this);
	}
	handleChange(data){
		const { change } = this.props;
		return (value) =>{
			let state;
			switch(data.type){
				case 'input':
					state =  Object.assign({}, data, {value: value.target.value})
				break;
				default:
					state = Object.assign({}, data, {value: value})
				break;
			}
			change({[data.key]: state});
		}
	}
    render() {
        const { change, name, type, keyword, search } = this.props;
        return (
            <Row type={'flex'} justify={'center'} align={'middle'} style={{ marginBottom: 16 }} >
				<Col span={18}>
					{/*<SgForm />*/}
					<span style={{marginRight: 10}}>
						<span>资源名称：</span>
						<Select size="large" defaultValue={name.value} style={{ width: 100 }} onChange={ this.handleChange(name) }>
							{
								name.options.map((option, index)=>{
									return <Option value={option.value} key={index}>{option.text}</Option>
								})
							}
					    </Select>
					</span>
					<span style={{marginRight: 10}}>
						<span>资源类型：</span>
						<Select size="large" defaultValue={type.value} style={{ width: 100 }} onChange={ this.handleChange(type) }>
							{
								type.options.map((option, index)=>{
									return <Option value={option.value} key={index}>{option.text}</Option>
								})
							}
					    </Select>
					</span>
{/*					<span style={{marginRight: 10}}>
					    <span>关键字：</span>
					    <Input placeholder="关键字" style={{ width: 100 }} value={ keyword.value } onChange={ this.handleChange(keyword) } />
					</span>*/}
					<Button type="primary" onClick={ search }>查询</Button>
				</Col>
				<Col span={5} style={{textAlign:'right'}}>
					<SgModal style={{marginRight:10}}/>
					<Button type="primary">批量删除</Button>
				</Col> 
			</Row>
        )
    }
}
