import React, { Component } from 'react'
import app from '../app'
import { Api } from '../api/'
import Utils from '../utils/utils'

import { Tabs, DatePicker, Button, Breadcrumb, message, Input, InputNumber, Row, Col, Select, Icon, Tooltip } from 'antd';
import { SgBreadcrumb, SgModal, SgMenu, SgTable } from '../components';
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
    setTimeout(() => {
        hide();
        success()
    }, 1500);
};

function onChange(value) {
    console.log('changed', value);
}

export default class PageView extends Component {
    constructor(props) {
        super(props);
        this.state = {
            status: '',
            breads: [{
                name: '数据共享系统',
                link: '/#/index/content/dgap/resource/resourceDir'
            }, {
                name: '资源目录管理',
                link: '/#/index/content/dgap/resource/resourceMa'
            }, {
                name: '表设计'
            }],
            fieldModal: {
                visible: false,
                isEdit: false
            },
            tableModal: {
                visible: false,
                nextButton: {
                    loading: false,
                    disabled: false
                },
            },
            createButton: {
                loading: false,
                disabled: true
            },
            deleteButton: {
                loading: false,
                disabled: true
            },
            tableForm: {
                chineseName: {
                    type: 'input',
                    value:''
                },
                englishName: {
                    type: 'input',
                    value:''
                }
            },
            fieldForm: {
                chineseName: {
                    type: 'input'
                },
                englishName: {
                    type: 'input'
                },
                type: {
                    type: 'select',
                    options: []
                },
                len: {
                    type: 'inputNumber',
                    value: 1
                }
            },
            fieldsTable: {
                dataSource: [],
                columns: [{
                    title: '英文字段名',
                    dataIndex: 'englishName'
                }, {
                    title: '中文字段名',
                    dataIndex: 'chineseName',
                }, {
                    title: '字段类型',
                    dataIndex: 'typeName',
                }, {
                    title: '字段长度',
                    dataIndex: 'len',
                }, {
                    title: '状态',
                    dataIndex: 'statusText',
                },{
                    title: '操作',
                    dataIndex: 'ACTION',
                    render: (text, record, index) => (
                        <div>
                            <a href="#" style={{marginRight: 10}}
                                disabled={record.status.toUpperCase() != 'NE'}
                                onClick={ this.editFields(record) }>
                                <Icon type="edit" />修改
                            </a>
                            <a href="#" style={{marginRight: 10}}
                              disabled={record.status.toUpperCase() != 'NE'}
                              onClick={ this.deleteFields(record.id) }>
                              <Icon type="delete" />删除
                            </a>
                        </div>
                    )
                  }
                ]
            }
        }
    }
    toggleModal(keyName, visible) {
        return () => {
            const prevState = Object.assign({}, this.state);
            prevState[keyName].visible = visible;
            this.setState(
              Object.assign({}, this.state, prevState),
              () => console.log('prevState[keyName].visible', this.state[keyName].visible)
            )
        };
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
        };
    }
    onChange(keysString) {
        const keys = keysString.split('.');
        return (value) => {
            const prevState = {...this.state};
            let current = prevState;
            for (let i = 0, l = keys.length; i < l; i++) {
                current = current[keys[i]];
            }
            switch (current.type) {
                case 'input':
                case 'textarea':
                    current.value = value.target.value;
                    break;
                default:
                    current.value = value;
                    break;
            }
            this.setState(prevState,
              () => console.log('onUpdated', this.state)
            );
        }
    }
    fetchFields() {
        const { resourceId } = this.state;
        Api.getFieldList({ resourceId, start: 0, length: 100 }).then((data) => {
            data = data ? data : [];
            this.updateState('fieldsTable')({ dataSource: data })
        });
    }
    editFields(record) {
        return (evt) => {
            const { fieldModal, fieldForm } = this.state;
            const { chineseName, englishName, type, len, id } = record;
            fieldModal.isEdit = true;
            fieldForm.chineseName.value = chineseName;
            fieldForm.englishName.value = englishName;
            fieldForm.type.value = type;
            fieldForm.len.value = len;
            fieldForm.recordId = id;

            // this.updateState('fieldForm.chineseName')({ value: chineseName });
            // this.updateState('fieldForm.englishName')({ value: englishName });
            // this.updateState('fieldForm.type')({ value: type });
            // this.updateState('fieldForm.len')({ value: len });
            // this.updateState('fieldForm')({ recordId: id });

            this.setState({ fieldModal, fieldForm });
            this.toggleModal('fieldModal', true)();
        }
    }
    updateFields() {
        const { fieldForm, fieldForm: { recordId } } = this.state;
        const data = {
            chineseName: fieldForm.chineseName.value,
            englishName: fieldForm.englishName.value.toUpperCase(),
            type: fieldForm.type.value,
            id: recordId
        };
        ~['varchar', 'integer', 'float'].indexOf(fieldForm.type.value) && Object.assign(data, {len: fieldForm.len.value});
        this.updateState('fieldModal')({ confirmLoading: true });
        Api.updateFields(data).then((response) => {
            this.updateState('fieldModal')({ confirmLoading: false, visible: false });
            success();
            this.fetchFields();
        }, (error) => {
            this.updateState('fieldModal')({ confirmLoading: false });
        });
    }
    deleteFields(id) {
        const { resourceId } = this.state;
        return (evt) => {
            Api.deleteFields({ id, resourceId }).then((response) => {
                success()
                this.fetchFields();
            });
        }
    }
    onOpenFieldModal() {
        const { fieldModal, fieldForm } = this.state;
        fieldModal.visible = true;
        fieldModal.isEdit = false;

        fieldForm.chineseName.value = '';
        fieldForm.englishName.value = '';
        fieldForm.type.value = fieldForm.type.options.length ? fieldForm.type.options[0].type : '';
        fieldForm.len.value = 1;
        this.setState({ fieldModal, fieldForm});
    }
    addFields() {
        const { resourceId, fieldForm } = this.state;
        let data = {
            chineseName: fieldForm.chineseName.value,
            englishName: fieldForm.englishName.value.toUpperCase(),
            type: fieldForm.type.value,
            resourceId
        };
        ~['varchar', 'integer', 'float'].indexOf(fieldForm.type.value) && Object.assign(data, {len: fieldForm.len.value});
        this.updateState('fieldModal')({ confirmLoading: true });
        Api.addFields(data).then((response) => {
            this.updateState('fieldModal')({ confirmLoading: false, visible: false });
            success();
            this.fetchFields();
        }, (error) => {
            this.updateState('fieldModal')({ confirmLoading: false });
        });
    }
    submitFields() {
        //form 验证
        const isOk = this.validFieldForm();
        if (!isOk) {
            return;
        }
        //form 取值
        const { fieldModal: { isEdit } } = this.state;
        !isEdit ? this.addFields() : this.updateFields();
    }
    getTableInfo() {
        const { resourceId } = this.state;
        Api.getTableInfo({ resourceId }).then((data) => {
            const { tableModal, tableForm, deleteButton, createButton} = this.state;
            if (data == null) {
                tableModal.visible = true;
                tableModal.nextButton = {
                    disabled: false,
                    loading: false
                };
                deleteButton.disabled = true;
                this.setState({ tableModal, deleteButton});
                return;
            }
            const { status } = data;
            tableForm.chineseName.value = data.chineseName;
            tableForm.englishName.value = data.englishName;
            tableModal.visible = status.toUpperCase() != 'AE';
            tableModal.nextButton = {
                disabled: false,
                loading: false
            };
            createButton.disabled = data.status.toLowerCase() == 'ae';
            deleteButton.disabled = data.status.toLowerCase() != 'ae';
            this.setState({status, tableForm, tableModal, createButton, deleteButton});

        });
    }
    fieldType() {
        Api.fieldType({}).then((data) => {
            console.log('data', data);
            this.updateState('fieldForm.type.options')(data);
        });
    }
    addTable() {
        const isOk = this.validTableForm();
        if (!isOk) {
            return;
        }

        const { tableForm: { chineseName, englishName }, resourceId } = this.state;
        const data = {
            chineseName: chineseName.value,
            englishName: englishName.value.toUpperCase(),
            resourceId
        };

        this.updateState('tableModal.nextButton')({ loading: true });
        Api.addTable(data).then(() => {
            success();
            this.updateState('tableModal')({ visible: false })
            this.updateState('tableModal.nextButton')({ loading: false, disabled: false });
        }).catch(() => {
            this.updateState('tableModal.nextButton')({ loading: false, disabled: false });
        });
    }
    createTable() {
        const { resourceId } = this.state;
        this.updateState('createButton')({ loading: true });
        Api.createTable({ resourceId }).then(() => {
            success();
            this.getTableInfo();
            this.fetchFields();
            this.updateState('createButton')({ loading: false });
        }).catch(() => {
            this.updateState('createButton')({ loading: false });
        });
    }
    deleteTable() {
        const { resourceId } = this.state;
        this.updateState('deleteButton')({ loading: true });
        Api.deleteTable({ resourceId }).then(() => {
            success();
            this.getTableInfo();
            this.fetchFields();
            this.updateState('deleteButton')({ loading: false });
        }).catch(() => {
            this.updateState('deleteButton')({ loading: false });
        });
    }
    validTableForm() {
        const { tableForm: { chineseName, englishName } } = this.state;
        if (englishName.value == null || !/^[a-z][a-z0-9_]{0,19}$/i.test(englishName.value)) {
            error('[英文字段名] 英文字母、数字、下划线，并以英文字母开头，最多20位。');
            return;
        }

        if (chineseName.value == null || !/^[\u4E00-\u9FA5\uF900-\uFA2D][\u4E00-\u9FA5\uF900-\uFA2D_0-9-]{0,19}$/i.test(chineseName.value)) {
            error('[中文字段名] 中文、数字、下划线，并以中文开头，最多20位。');
            return;
        }
        return true;
    }
    validFieldForm() {
        const { fieldForm: { chineseName, englishName, type, len } } = this.state;
        if (englishName.value == null || !/^[a-z][a-z0-9_]{0,19}$/i.test(englishName.value)) {
            error('[英文字段名] 英文字母、数字、下划线，并以英文字母开头，最多20位。');
            return;
        }

        if (chineseName.value == null || !/^[\u4E00-\u9FA5\uF900-\uFA2D][\u4E00-\u9FA5\uF900-\uFA2D_0-9-]*$/i.test(chineseName.value)) {
            error('[中文字段名] 中文、数字、下划线，并以中文开头，最多20位。');
            return;
        }

        if (type.value == 'varchar' && ( len.value == '' || len.value < 1|| len.value >= 1000)) {
            error('字段长度 >= 1 且 字段长度 < 1000');
            return;
        }
        if (type.value == 'integer' && (!/^\d{1,2}$/i.test(len.value) || len.value <= 0  || len.value >= 38 ) ) {
            error('字段长度 >= 1 且 字段长度 < 38');
            return;
        }
        if (type.value == 'float' && (!/^\d{1,2}$/i.test(len.value) || len.value <= 0  || len.value >= 38 ) ) {
            error('字段长度 >= 1 且 字段长度 < 38');
            return;
        }
        return true;
    }
    componentWillMount() {
        let resourceId = Utils.parseUri('id');
        resourceId = resourceId.length ? resourceId : error('资源id不能为空');;
        console.log('resourceId', resourceId);
        this.setState({ resourceId });
    }
    componentDidMount() {
        this.getTableInfo();
        this.fieldType();
        this.fetchFields();
    }
    onChangeVisible(keysString) {

    }

    back(){
        window.location.href = '/#/index/content/dgap/resource/resourceMa';
    }
    render() {
        const {
          breads,
          fieldModal,
          tableModal,
          fieldsTable,
          fieldForm,
          tableForm,
          createButton,
          deleteButton,
          status
        } = this.state;
        return (
            <div>
                {<Row>
                    <Col span={24}>
                        <Header />
                    </Col>
                </Row>}
                <Row justify={'center'}>
                    <Col span={4}>
                        <SgMenu />
                    </Col>
                    <Col span={20}
                      style={{
                        borderLeft:'1px solid #e9e9e9',
                        backgroundColor:'#FFF',
                        borderRadius:5
                      }}>
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
                                <Col span={15}>
                                    <Button type="primary" icon={'plus'}
                                      onClick={::this.onOpenFieldModal}>
                                      新增字段
                                    </Button>
                                    <Button type="primary" style={{marginLeft:10}}
                                      onClick={::this.back}>
                                      返回
                                    </Button>
                                    {/*<a  style={{marginLeft:10}} className="ant-btn ant-btn-primary" onClick={::this.back}>返回</a>*/}
                                </Col>
                                <Col span={8} style={{textAlign:'right'}}>
                                    <Button type="primary" icon={'rocket'}
                                      onClick={::this.createTable}
                                      {...createButton}
                                      disabled={!fieldsTable.dataSource.length || fieldsTable.dataSource.filter((field)=>field.status == 'NE').length <= 0}
                                      style={{marginRight: 10}}>
                                        生成表
                                    </Button>
                                    <Button type="primary" icon={'delete'}
                                      onClick={::this.deleteTable}
                                      {...deleteButton}>
                                        删除表
                                    </Button>
                                </Col>
                            </Row>
                        </div>
                        <Row type={'flex'} justify={'center'}>
                            <Col span={23} style={{marginBottom: 20}}>
                                <SgTable
                                    size="middle"
                                    pagination={false}
                                    bordered={true}
                                    rowKey={(record, index) => record.englishName}
                                    title={()=>`${tableForm.englishName.value} ${tableForm.chineseName.value}`}
                                    {...fieldsTable}
                                />
                            </Col>
                        </Row>
                    </Col>
                </Row>
            {/*modal*/}
            <SgModal title={'表名设计'}
                updateState={this.updateState('tableModal')}
                maskClosable={false}
                closable={false}
                footer={
                  [<Button key="submit" type="primary" size="large"
                    {...tableModal.nextButton}
                    onClick={::this.addTable}>下一步<Icon type="right" />
                  </Button>]
                }
                {...tableModal}>
                    <div>
                        <Row style={{marginBottom:10}} type={'flex'} justify={'center'} align={'middle'}>
                            <Col span={4}>
                                <span>英文表名</span>
                            </Col>
                            <Col span={16}>
                              <Input placeholder="英文表名"
                              value={tableForm.englishName.value}
                              onChange={this.onChange('tableForm.englishName')}
                              />
                            </Col>
                            <Col span={1} style={{textAlign:'center'}}>
                                <Tooltip title="英文字母、数字、下划线，并以英文字母开头。">
                                    <Icon type="info-circle-o" />
                                </Tooltip>
                            </Col>
                        </Row>
                        <Row style={{marginBottom:10}} type={'flex'} justify={'center'} align={'middle'}>
                            <Col span={4}>
                                <span>中文表名</span>
                            </Col>
                            <Col span={16}>
                                <Input placeholder="中文表名"
                                  value={tableForm.chineseName.value}
                                  onChange={this.onChange('tableForm.chineseName')}
                                />
                            </Col>
                            <Col span={1} style={{textAlign:'center'}}>
                                <Tooltip title="中文、英文字母、数字、下划线，并以中文开头。">
                                    <Icon type="info-circle-o" />
                                </Tooltip>
                            </Col>
                        </Row>
                    </div>
            </SgModal>
            <SgModal title={'字段设计'}
                    onOk={::this.submitFields}
                    updateState={this.updateState('fieldModal')}
                    {...fieldModal}>
                <div>
                    <Row style={{marginBottom:10}} type={'flex'} justify={'center'} align={'middle'}>
                        <Col span={4}>
                            <span>英文字段名</span>
                        </Col>
                        <Col span={16}>
                            <Input placeholder="英文字段名"
                              value={ fieldForm.englishName.value }
                              onChange={ this.onChange('fieldForm.englishName')}
                            />
                        </Col>
                        <Col span={1} style={{textAlign:'center'}}>
                            <Tooltip title="英文字母、数字、下划线，并以英文字母开头。">
                                <Icon type="info-circle-o" />
                            </Tooltip>
                        </Col>
                    </Row>
                    <Row style={{marginBottom:10}} type={'flex'} justify={'center'} align={'middle'}>
                        <Col span={4}>
                            <span>中文字段名</span>
                        </Col>
                        <Col span={16}>
                            <Input placeholder="中文字段名"
                              value={fieldForm.chineseName.value}
                              onChange={this.onChange('fieldForm.chineseName')}
                            />
                        </Col>
                        <Col span={1} style={{textAlign:'center'}}>
                            <Tooltip title="中文、数字、下划线，并以中文开头。">
                                <Icon type="info-circle-o" />
                            </Tooltip>
                        </Col>
                    </Row>
                    <Row style={{marginBottom:10}} type={'flex'} justify={'center'} align={'middle'}>
                        <Col span={4}>
                            <span>字段类型</span>
                        </Col>
                        <Col span={16}>
                            <Select size="large" style={{ width: '100%' }}
                              defaultValue={ fieldForm.type.value }
                              value={ fieldForm.type.value }
                              onChange={ ::this.onChange('fieldForm.type')}>
                                {
                                    fieldForm.type.options.map((option, index)=>{
                                        return <Option value={option.type} key={index}>{option.name}</Option>
                                    })
                                }
                            </Select>
                        </Col>
                        <Col span={1} style={{textAlign:'center'}}>
                            <Tooltip overlay={()=>(
                                <div>
                                    {
                                        fieldForm.type.options.map((option, index)=>{
                                            return option.description &&  option.description.length ? <p key={index}>{`${option.name} : ${option.description};` }</p> : null;
                                        })
                                    }
                                </div>
                            )}>
                                <Icon type="info-circle-o" />
                            </Tooltip>
                        </Col>
                    </Row>
                    <div style={{ display: ~['varchar', 'integer', 'float'].indexOf(fieldForm.type.value) ? 'block' : 'none'}}>
                        <Row style={{marginBottom:10}} type={'flex'} justify={'center'} align={'middle'}>
                            <Col span={4}>
                                <span>字段长度</span>
                            </Col>
                            <Col span={16}>
                                <InputNumber min={1} max={255} defaultValue={8} step={1}
                                  value={ fieldForm.len.value }
                                  onChange={ ::this.onChange('fieldForm.len')}
                                />
                            </Col>
                            <Col span={1}>
                            </Col>
                        </Row>
                    </div>
                </div>
            </SgModal>
            </div>
        )
    }
}

app.value("PageView", PageView);
