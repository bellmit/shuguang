import React, { Component } from 'react'
import { SgBreadcrumb, SgTable } from '../../components'
import Panel from './panel'
import { Tabs, DatePicker, Button, Breadcrumb, Input, Row, Col, Select, Table } from 'antd';
import { Api } from '../../api/'

class TableActions extends Component {
    render() {
        return (
            <div>
                <p>
                    <a href="/webapp/template/field.html" style={{marginRight: 10}}>表设计</a>
                    <a href="/webapp/template/review.html" style={{marginRight: 10}}>数据审核</a>
                </p>
                <p>
                    <a href="#" style={{marginRight: 10}}>修改</a>
                    <a href="#" style={{marginRight: 10}}>删除</a>
                    <a href="#">状态</a>
                </p>
            </div>
        )
    }
}

function handleChange(value) {
    console.log(`selected ${value}`);
}

export default class Home extends Component {
    constructor(props) {
        super(props);
        this.state = {
            breads: [{
                name: '数据共享系统',
            }, {
                name: '资源目录',
                link: '#'
            }, {
                name: '资源管理'
            }],
            filter: {
                name: {
                    key: 'name',
                    type: 'select',
                    value: 'all',
                    options: [{
                        text: '全部',
                        value: 'all'
                    }, {
                        text: '通过',
                        value: 'yes'
                    }, {
                        text: '不通过',
                        value: 'no'
                    }]
                },
                type: {
                    key: 'type',
                    type: 'select',
                    value: 'web',
                    options: [{
                        text: 'WEB服务',
                        value: 'web'
                    }, {
                        text: '数据导入',
                        value: 'import'
                    },{
                        text: '表资源',
                        value: 'share'
                    }]
                },
                keyword: {
                    key: 'keyword',
                    type: 'input',
                    defaultValue: ''
                }
            },
            table: {
                selectedRowKeys: [], // Check here to configure the default column
                data: [],
                // loading: false,
                // pagination: {},
                data: [],
                columns: [{
                    title: '名称',
                    dataIndex: 'NAME',
                }, {
                    title: '描述',
                    dataIndex: 'DESCRIPTION',
                }, {
                    title: '操作',
                    dataIndex: 'address',
                    render: () => <TableActions />
                }]
            }
        }
        this.onSelectChange = this.onSelectChange.bind(this);
        this.changeFilter = this.changeFilter.bind(this);
        this.search = this.search.bind(this);
    }
    changeFilter(filterState) {
        const { filter } = this.state;
        console.log('filterState', filterState);
        this.setState({ filter: Object.assign({}, {...filter }, filterState) }, () => { console.log('this.state', this.state) });
    }
    onSelectChange(selectedRowKeys) {
        console.log('selectedRowKeys changed: ', selectedRowKeys);
        const { table } = this.state;
        this.setState({ table: {...table, selectedRowKeys } });
    }
    search() {
        const { filter } = this.state;
        const params = Object.assign({}, {...filter }, params);
        this.fetch(params);
        console.log('----start search----', params);
    }
    fetch(params = { start: 0, length: 15 }) {
        const { table } = this.state;
        Api.getResourceList(params).then((res) => {
            const data = res.data.list;
            this.setState({ table: {...table, data } });
        });
    }
    render() {
        const { filter, table, breads } = this.state;
        const rowSelection = {
            selectedRowKeys: table.selectedRowKeys,
            onChange: this.onSelectChange
        };
        return (
            <div style={{ marginBottom: 16, marginTop:12 }}>
                { /*面包屑*/ }
                <Row type={'flex'} justify={'center'} align={'middle'} style={{ marginBottom: 16, marginTop:12 }}>
                    <Col span={23}>
                        <SgBreadcrumb breads={ breads } />
                    </Col>
                </Row>

                { /*搜索过滤操作面板*/ }
                <Panel change={ this.changeFilter } {...filter} search={ this.search } />

                { /*表格*/ }
                <Row type={'flex'} justify={'center'}>
                    <Col span={23}>
                        <SgTable { ...table } rowSelection={rowSelection}/>
                    </Col>
                </Row>
            </div>
        )
    }
    componentDidMount() {
        this.fetch();
    }
}
