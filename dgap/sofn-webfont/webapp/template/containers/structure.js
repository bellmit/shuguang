import React, { Component } from 'react'
import app from '../app'
import { Api } from '../api/'
import Utils from '../utils/utils'

import { Tabs, DatePicker, Button, Breadcrumb, message, Input, InputNumber, Row, Col, Select, Checkbox, Switch } from 'antd';
import { SgBreadcrumb, SgModal, SgTable, SgTree, SgMenu } from '../components'
import Header from '../components/header';

const Option = Select.Option;

function handleChange(value) {
    console.log(`selected ${value}`);
}

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
    setTimeout(() => {
        hide();
        success()
    }, 1500);
};

class TableActions extends Component {
    render() {
        const { openField } = this.props;
        return (
            <div>
                <a href="#" style={{marginRight: 10}} onClick={ openField }>修改</a>
                <a href="#" style={{marginRight: 10}} onClick={progress}>删除</a>
            </div>
        )
    }
}

export default class PageView extends Component {
    constructor(props) {
        super(props);
        this.state = {
            trees: [],
            breads: [{
                name: '数据共享系统',
                link: '/#/index/content/dgap/resource/resourceDir'
            }, {
                name: '资源目录管理',
                link: '/#/index/content/dgap/resource/resourceMa'
            }, {
                name: '查看表结构'
            }],
            viewButton:{
              loading:false
            },
            exampleModal: {
                visible: false
            },
            publishModal: {
                visible: false
            },
            publishForm: {
                name: {
                    type: 'input'
                },
                directoryId: {
                    type: 'select',
                    options: []
                },
                flag: {
                    value: true
                },
                description: {
                    type: 'textarea'
                }
            },
            tableName: {
                type: 'input'
            },
            table: {
                loading:false,
                selectedRowKeys: [], // Check here to configure the default column
                isSearchRowKeys:[],
                dataSource: [],
                columns: [{
                    title: '中文字段名',
                    dataIndex: 'chineseName',
                }, {
                    title: '英文字段名',
                    dataIndex: 'englishName'
                }, {
                    title: '字段类型',
                    dataIndex: 'cnFileType',
                }, {
                    title: '字段长度',
                    dataIndex: 'len',
                }, {
                    title: '是否可作为查询条件',
                    dataIndex: 'ISSEARCH',
                    render: (text, record, index) => <Checkbox defaultChecked={false}
                      checked={!!~this.state.table.isSearchRowKeys.indexOf(record.id)}
                      onChange={
                        (e) => {
                          const checked = e.target.checked;
                          const table = {...this.state.table};
                          if(checked){
                            const rowIndex= table.selectedRowKeys.indexOf(record.id);

                            if (~rowIndex) {
                                table.selectedRowKeys[rowIndex] = record.id;
                            }else{
                              table.selectedRowKeys[table.selectedRowKeys.length] = record.id;
                            }
                          }

                          const isSearchIndex = table.isSearchRowKeys.indexOf(record.id);
                          if(checked){
                            if (~isSearchIndex) {
                                table.isSearchRowKeys[isSearchIndex] = record.id;
                            }else{
                              table.isSearchRowKeys[table.isSearchRowKeys.length] = record.id;
                            }
                          }else{
                            if (~isSearchIndex) {
                                table.isSearchRowKeys.splice(isSearchIndex, 1);
                            }
                          }
                          this.setState({table});
                        }
                      }/>
                }]
            },
            exampleTable: {
                dataSource: [],
                columns: []
            }
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
            this.setState(Object.assign({}, this.state, prevState), ()=>{console.log('new State:', this.state)});
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
    showModal(keyName, visible) {
        const prevState = this.state;
        return () => {
            prevState[keyName].visible = visible;
            this.setState(Object.assign({}, this.state, prevState), () => { console.log('this.state', this.state) })
        }
    }
    fetchFields() {
      const { tableId, table } = this.state;
        table.loading = true;
        this.setState({ table });
        Api.getFields({ tableId }).then((data) => {
            table.loading = false;
            table.dataSource = data;
            this.setState({ table });
        });
    }
    fetchTrees(tableName='') {
        Api.getTables({tableName}).then((data) => {
            this.setState({ trees: data });
        });
    }
    fetchExample() {
        const { tableId, viewButton } = this.state;
        viewButton.loading = true;
        this.setState({viewButton});
        Api.getPage({ pageSize: 3, pageNum: 1, tableId }).then((data) => {
            const { fields, table } = data;
            viewButton.loading = false;
            this.setState({viewButton});
            this.updateState('exampleTable')({ dataSource: table, columns: fields });
            this.showModal('exampleModal', true)();
        });
    }
    fetchResourceDir(){
        Api.getResourceDir().then((data) => {
            const { publishForm } = this.state;
            publishForm.directoryId.options = data;
            publishForm.directoryId.value = data.length ? data[0].id : '';
            this.setState({ publishForm });
        });
    }
    submitResource(){
        const { publishForm, table:{ selectedRowKeys, isSearchRowKeys }} = this.state;
        const data = {
            name: publishForm.name.value,
            directoryId: publishForm.directoryId.value,
            description: publishForm.description.value,
            flag: publishForm.flag.value ? 'A' : 'N',
            publishFields: selectedRowKeys.join(','),
            conditionFields: isSearchRowKeys.join(',')
        };
        this.updateState('publishModal')({ confirmLoading: true });
        Api.configResource(data).then((response) => {
            this.updateState('publishModal')({ confirmLoading: false, visible: false });
            success();
        }, (error) => {
            this.updateState('publishModal')({ confirmLoading: false });
        });
    }
    onSelectChange(selectedRowKeys, selectedRows) {
        console.log('selectedRowKeys changed: ', selectedRowKeys, selectedRows);
        const table = {...this.state.table};
        const isSearchRowKeys = table.isSearchRowKeys.filter((key, index)=> ~selectedRowKeys.indexOf(key));
        table.selectedRowKeys = selectedRowKeys;
        table.isSearchRowKeys = isSearchRowKeys;
        this.setState({table});
    }
    search(){
        const { tableName } = this.state;
        if (tableName.value != null && tableName.value.length > 50) {
            error('搜索条件不能超过50个字符');
            return;
        }
        this.fetchTrees(tableName.value);
    }
    onSelectTree(selectedKeys, {selected}){
        console.log('selectedKeys', selectedKeys);
        const key = selectedKeys[0];
        if (!selected || key <= 0) {
            return;
        }
        this.setState({tableId: key}, ()=>this.fetchFields());
    }
    onChangeSwitch(checked){
        this.updateState('publishForm.flag')({
            value: checked
        });
    }
    validPublishForm(){
        const { publishForm: { name, description, directoryId } } = this.state;
        if (name.value == null || name.value.length <= 0) {
            error('请输入资源名称');
            return;
        }

        if (name.value.length > 30) {
            error('资源名称长度不能超过30个字符');
            return;
        }

        if (directoryId.value == null) {
            error('请选取资源目录');
            return;
        }

        if (description.value == null || !/^[\u4E00-\u9FA5\uF900-\uFA2D_a-z0-9-\s]+$/i.test(description.value)) {
            error('请输入资源描述');
            return;
        }
        if (description.value.length > 300) {
            error('资源描述长度不能超过300个字符');
            return;
        }
        return true;
    }
    publish(){
        //form 验证
        const isOk = this.validPublishForm();
        if (!isOk) {
            return;
        }
        //form 取值
        this.submitResource();
    }
    componentWillMount() {
    }
    componentDidMount() {
        this.fetchTrees();
        this.fetchResourceDir();
    }
    render() {
        const { exampleModal, publishModal, table, exampleTable, trees, breads, tableName, publishForm, tableId, viewButton } = this.state;
        const rowSelection = {
            selectedRowKeys: table.selectedRowKeys,
            onChange: this.onSelectChange
        };
        return (
            <div>
                {<Row>
                    <Col span={24}><Header /></Col>
                </Row>}
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
                                    <span style={{ marginRight: 10 }}>仓库表</span>
                                    <Input placeholder="仓库表名" value={tableName.value} onChange={this.onChange('tableName')}  style={{ width: 105, marginRight: 10 }}/>
                                    <Button type="primary" onClick={::this.search}>查询</Button>
                                </Col>
                                <Col span={5} style={{textAlign:'right'}}>

                                    <div style={{marginRight:10}}>

                                    </div>

                                    <Button type="primary" disabled={tableId == null} {...viewButton} onClick={::this.fetchExample}>查看仓库表数据</Button>
                                </Col>
                            </Row>
                        </div>
                        <Row type={'flex'} justify={'center'}>
                            <Col span={5}>
                                <div>
                                    <SgTree onSelect={::this.onSelectTree}
                                        trees={trees} />
                                </div>
                            </Col>
                            <Col span={19} style={{marginBottom: 15}}>
                                <SgTable
                                    pagination={false}
                                    rowSelection={rowSelection}
                                    { ...table }
                                />
                            </Col>
                        </Row>
                        <Row type={'flex'} justify={'center'}>
                            <Col>
                                <Button type="primary" size="large" style={{marginBottom: 50}} disabled={table.selectedRowKeys.length <= 0} onClick={::this.showModal('publishModal', true)}>发布成资源</Button>
                            </Col>
                        </Row>
                    </Col>
                </Row>
                {/*modal*/}
                <SgModal title={'仓库表'}
                    updateState={this.updateState('exampleModal')}
                    {...exampleModal}
                    footer={
                        [<Button key="submit" type="primary" size="large" {...exampleModal.confirmButton} onClick={::this.showModal('exampleModal', false)}>保存</Button>]
                    }>
                    <div>
                        <Row style={{marginBottom:10}} type={'flex'} justify={'center'} align={'middle'}>
                            <Col span={19}>
                                <SgTable
                                    { ...exampleTable }
                                    pagination={false}
                                    bordered={true}
                                    size="small"
                                    rowKey={(record, index) => index} />
                            </Col>
                        </Row>
                    </div>
                </SgModal>

                <SgModal title={'资源发布'}
                    updateState={::this.updateState('publishModal')}
                    onOk={::this.publish}
                    {...publishModal}>
                    <div>
                        <Row style={{marginBottom:10}} type={'flex'} justify={'center'} align={'middle'}>
                            <Col span={4}>
                                <span>资源名称</span>
                            </Col>
                            <Col span={16}>
                                <Input placeholder="资源名称" onChange={this.onChange('publishForm.name')} />
                            </Col>
                        </Row>
                        <Row style={{marginBottom:10}} type={'flex'} justify={'center'} align={'middle'}>
                            <Col span={4}>
                                <span>资源目录</span>
                            </Col>
                            <Col span={16}>
                                <Select defaultValue={ publishForm.directoryId.value } style={{ width: '100%' }} value={ publishForm.directoryId.value } onChange={ ::this.onChange('publishForm.directoryId')}>
                                    {
                                        publishForm.directoryId.options.map((option, index)=>{
                                            return <Option value={option.id} key={index}>{option.name}</Option>
                                        })
                                    }
                                </Select>
                            </Col>
                        </Row>
                        <Row style={{marginBottom:10}} type={'flex'} justify={'center'} align={'middle'}>
                            <Col span={4}>
                                <span>是否启用</span>
                            </Col>
                            <Col span={16}>
                                <Switch checkedChildren={'启用'} unCheckedChildren={'禁用'} defaultChecked={publishForm.flag.value} onChange={::this.onChangeSwitch} />
                            </Col>
                        </Row>
                        <Row style={{marginBottom:10}} type={'flex'} justify={'center'} align={'middle'}>
                            <Col span={4}>
                                <span>资源描述</span>
                            </Col>
                            <Col span={16}>
                                <Input type="textarea" rows={4} placeholder="资源描述" onChange={this.onChange('publishForm.description')} />
                            </Col>
                        </Row>
                    </div>
                </SgModal>
            </div>
        )
    }
}
app.value("PageView", PageView);
