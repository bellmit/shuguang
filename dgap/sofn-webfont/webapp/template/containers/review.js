import '../styles/review.less'
import React, { Component } from 'react'
import app from '../app'
import { Api } from '../api/'
import Utils from '../utils/utils'

import { Tabs, DatePicker, Button, Breadcrumb, message, Menu, Input, InputNumber, Row, Col, Select, Dropdown, Icon, BackTop } from 'antd';
import { SgBreadcrumb, SgModal, SgTable, SgMenu } from '../components'
import Header from '../components/header';

const Option = Select.Option;

const success = function(text) {
    text = typeof text === 'string' ? text : '操作成功';
    message.success(text, 1);
};

const error = function(text) {
    text = typeof text === 'string' ? text : '操作失败';
    message.error(text);
};

const progress = () => {
    const hide = message.loading('处理中...', 0);
    // Dismiss manually and asynchronously
    setTimeout(() => {
        hide();
        success()
    }, 1500);
};

function handleChange(value) {
    console.log(`selected ${value}`);
}

export default class PageView extends Component {
    constructor(props) {
        super(props);
        this.state = {
            status:{
                type:'selecte',
                value:''
            },
            breads: [{
                name: '数据共享系统',
                link: '/#/index/content/dgap/resource/resourceDir'
            }, {
                name: '资源目录管理',
                link: '/#/index/content/dgap/resource/resourceMa'
            }, {
                name: '数据审核'
            }],
            table: {
                selectedRowKeys: [], // Check here to configure the default column
                loading: false,
                pagination: {
                  current: 1,
                  pageSize:10
                },
                dataSource: [],
                columns: []
            }
        }
    }
    onChange(keysString) {
        const keys = keysString.split('.');
        return (value) => {
            const prevState = Object.assign({}, this.state);
            let current = prevState;
            let currentState = null;
            for (let i = 0, l = keys.length; i < l; i++) {
                current = current[keys[i]];
            }
            switch (current.type) {
                case 'input':
                case 'textarea':
                    current.value = value.target.value;
                    currentState = Object.assign({}, prevState);
                    break;
                default:
                    current.value = value;
                    currentState = Object.assign({}, prevState);
                    break;
            }
            this.setState(Object.assign({}, this.state, currentState), () => { console.log('onChange:', this.state); });
        }
    }
    updateState(keysString) {
        const keys = keysString ? keysString.split('.') : [];
        return (newState) => {
            const prevState = Object.assign({}, this.state);
            let current = prevState;
            for (let i = 0, l = keys.length; i < l; i++) {
                current = current[keys[i]];
            }
            Object.assign(current, newState);
            this.setState(Object.assign({}, this.state, prevState));
        }
    }
    onSelectChange(selectedRowKeys) {
        console.log('selectedRowKeys changed: ', selectedRowKeys);
        const { table } = this.state;
        this.setState({ table: {...table, selectedRowKeys } });
    }
    onInputChange(keyName) {
      return (e) => {
        console.log('keyName', keyName);
        this.setState({[`${keyName}_searchText`]: e.target.value });
      }
    }
    onSearch(keyName){
      return () =>{
        const searchText = this.state[`${keyName}_searchText`];
        const condition = [
          keyName,searchText
        ]
        this.fetchDataList(condition.join('='));
      }
    }
    fetchDataList(condition) {
        const { resourceId, status, table: { pagination } } = this.state;
        condition = condition || '';
        let data = {
            resourceId,
            condition,
            start: (pagination.current - 1) * pagination.pageSize,
            length: pagination.pageSize
        };
        const { table } = this.state;
        table.loading = true;
        this.setState({ table });
        status.length && Object.assign(data, {status});
        Api.getDataList(data).then((res)=>{
          const columns = res.columns.map((field)=>({
                  title: field.chineseName,
                  dataIndex: field.englishName
              }));

          const initColumns = [{
                  title: '审核状态',
                  dataIndex: 'STATUS',
                  render: (text, record, index) => {
                    return (
                      <Dropdown overlay={
                        <Menu onClick={::this.onChangeStatus(record.ID)}>
                          { text.toUpperCase() != 'Y' ? <Menu.Item key="Y">通过审核</Menu.Item> : <Menu.Item key="N">不通过审核</Menu.Item> }
                        </Menu>
                      }>
                          <a className="ant-dropdown-link" href="#">
                            {{Y: '已通过', N:'未通过'}[text.toUpperCase()]}<Icon type="down" />
                          </a>
                      </Dropdown>
                    )
                  }
              }];
              const pagination = this.state.table.pagination;
              pagination.current = res.dataSource.pageNum;
              pagination.total = res.dataSource.total;
          this.updateState('table')({
              loading:false,
              dataSource: res.dataSource.list,
              pagination,
              columns:[...columns, ...initColumns]
          });
        }).catch(() => {
          const { table } = this.state;
          table.loading = false;
          this.setState({ table });
          error();
        });
    }
    handleTableChange(pagination, filters, sorter) {
      const table = {...this.state.table};
      const pager = table.pagination;
      pager.current = pagination.current;
      this.setState({table}, ()=>this.fetchDataList());
    }
    onChangeStatus(ids){
      return ({key})=>{
        this.review(key, ids);
      }
    }
    onChangeAllStatus({key}){
        const { table:{ selectedRowKeys }} = this.state;
        if (selectedRowKeys.length <= 0) {
          error('请选取需要审核的项');
          return;
        }
        this.review(key, selectedRowKeys.join(','));
    }
    review(status, dataId){
        const { resourceId } = this.state;
        const data = {
            status,
            resourceId,
            dataId
        };
        Api.check(data).then((response) => {
            success();
            this.fetchDataList();
        });
    }
    search(status){
        const table = {...this.state.table};
        table.pagination.current = 1;
        table.selectedRowKeys = [];
        this.setState({status, table}, ()=> this.fetchDataList());
    }
    componentWillMount() {
        let resourceId = Utils.parseUri('id');
        resourceId = resourceId.length ? resourceId : error('资源id不能为空');
        console.log('resourceId', resourceId);
        this.setState({ resourceId });
    }
    componentDidMount() {
        this.fetchDataList();
    }
    render() {
        const { breads, table } = this.state;
        const rowSelection = {
            selectedRowKeys: table.selectedRowKeys,
            onChange: ::this.onSelectChange
        };

        const columns = table.columns.map((column)=>{
          return {
            ...column,
            filterDropdown: (
              <div className="custom-filter-dropdown">
                <Input
                  placeholder={`搜索 ${column.title}`}
                  value={this.state[`${column.dataIndex}_searchText`]}
                  onChange={::this.onInputChange(column.dataIndex)}
                  onPressEnter={::this.onSearch(column.dataIndex)}
                />
                <Button type="primary" onClick={::this.onSearch(column.dataIndex)}>搜索</Button>
              </div>
            )
          };
        });

        table.columns = columns;

        return (
            <div>
                {<Row>
                    <Col span={24}><Header /></Col>
                </Row>}
                <BackTop visibilityHeight={100} />
                <Row justify={'center'}>
                    <Col span={4}>
                        <SgMenu />
                    </Col>
                    <Col span={20} style={{borderLeft:'1px solid #e9e9e9', backgroundColor:'#FFF', borderRadius:5}}>
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
                                    <span style={{marginRight: 10}}>审核状态</span>
                                    <Select size="large" defaultValue="" style={{ width: 100, marginRight: 10 }} onChange={::this.search}>
                                      <Option value="">全部</Option>
                                      <Option value="Y">通过</Option>
                                      <Option value="N">不通过</Option>
                                    </Select>
                                    {/*<Button type="primary" onClick={::this.fetchDataList}>查询</Button>*/}
                                </Col>
                                <Col span={5} style={{textAlign:'right'}}>
                                    <Dropdown
                                        overlay={
                                            <Menu onClick={::this.onChangeAllStatus}>
                                                <Menu.Item  key="Y">通过审核</Menu.Item>
                                                <Menu.Item  key="N">不通过审核</Menu.Item>
                                            </Menu>}
                                        >
                                        <Button type="primary" style={{ marginLeft: 8 }}>
                                            批量审核 <Icon type="down" />
                                        </Button>
                                    </Dropdown>
                                    {/*<Dropdown.Button
                                        overlay={reviewMenu}
                                        type="primary"
                                        disabled={!table.selectedRowKeys.length}
                                        style={{ marginLeft: 8 }}
                                    >
                                        批量审核
                                    </Dropdown.Button>*/}
                                    {/*<Button type="primary" onClick={this.openReview} style={{marginRight: 10}}>批量审核</Button>*/}
                                </Col>
                            </Row>
                        </div>
                        <Row type={'flex'} justify={'center'}>
                            <Col span={23}>
                                <SgTable { ...table }
                                  rowKey={(record, index) => index}
                                  rowSelection={rowSelection}
                                  onChange={::this.handleTableChange}
                                />
                            </Col>
                        </Row>
                    </Col>
                </Row>
                {/*modal*/}
                <SgModal
                    style={{marginRight:10}}
                    title={'审核'} >
                    <div>
                        <Row style={{marginBottom:10}} type={'flex'} justify={'center'} align={'middle'}>
                            <Col span={4}>
                                <span>审核状态</span>
                            </Col>
                            <Col span={16}>
                                <Select size="large" defaultValue="yes" style={{ width: '100%' }} onChange={handleChange}>
                                  <Option value="yes">通过</Option>
                                  <Option value="no">不通过</Option>
                                </Select>
                            </Col>
                        </Row>
                    </div>
                </SgModal>
            </div>
        )
    }
}
app.value("PageView", PageView);
