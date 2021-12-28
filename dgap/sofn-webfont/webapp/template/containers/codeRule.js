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

export default class PageView extends Component {
    constructor(props) {
        super(props);
        this.state = {
            status: '',
            breads: [{
                name: '系统管理',
            }, {
                name: '编码管理',
                link: '/#/dgap/resource/resourceDir'
            }, {
                name: '新增'
            }],
            rule_name: '',
            rules: [{
                name: '农业行业代码',
                disabled: true,
                children: [{
                    item_val: 'ISO',
                    item_num: "1",
                    disabled: true
                }, {
                    item_val: '区域',
                    item_num: "2",
                    disabled: true
                }, {
                    item_val: '国家',
                    item_num: "156",
                    disabled: true
                }, {
                    item_val: '行业',
                    item_num: "326",
                    disabled: true
                }]
            }, {
                name: '平台级别',
                disabled: true,
                children: [{
                    item_val: '级别',
                    item_num: "8",
                    disabled: true
                }]
            }]
        }
    }
    onChange(keysString) {
        const keys = keysString.split('.');
        return (value) => {
            const prevState = { ...this.state };
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
    validTableForm() {
        const { tableForm: { chineseName, englishName } } = this.state;
        if (englishName.value == null || !/^[a-z]+[a-z0-9-_]*$/i.test(englishName.value)) {
            error('英文字母、数字、下划线，并以英文字母开头。');
            return;
        }

        if (chineseName.value == null || !/^[\u4E00-\u9FA5\uF900-\uFA2D]+[\u4E00-\u9FA5\uF900-\uFA2D_a-z0-9-]*$/i.test(chineseName.value)) {
            error('中文、数字、下划线，并以中文开头。');
            return;
        }
        return true;
    }
    addGroup() {
        const { rules } = this.state;
        rules[rules.length] = {
            name: '',
            disabled: false,
            children: [{
                item_val: '',
                item_num: '',
                disabled: false
            }]
        }
        this.setState({ rules });
    }
    add(ruleIndex, childIndex) {
        return () => {
            const { rules } = this.state;
            const item = {
                item_val: '',
                item_num: '',
                disabled: false
            };
            rules[ruleIndex].children.splice(childIndex + 1, 0, item);
            this.setState({ rules });
        }
    }
    remove(ruleIndex, childIndex) {
        return () => {
            const { rules } = this.state;
            rules[ruleIndex].children.length > 1 ? rules[ruleIndex].children.splice(childIndex, 1) : rules.splice(ruleIndex, 1);
            this.setState({ rules });
        }
    }
    updateMainKey(evt) {
        const rule_name = evt.target.value;
        this.setState({ rule_name });
    }
    updateGroupKey(ruleIndex) {
        return (evt) => {
            const { rules } = this.state;
            rules[ruleIndex].name = evt.target.value;
            this.setState({ rules });
        }
    }
    updateKey(ruleIndex, childIndex) {
        return (evt) => {
            const { rules } = this.state;
            rules[ruleIndex].children[childIndex].item_val = evt.target.value;
            this.setState({ rules });
        }
    }
    updateValue(ruleIndex, childIndex) {
        return (evt) => {
            const { rules } = this.state;
            rules[ruleIndex].children[childIndex].item_num = evt.target.value;
            this.setState({ rules });
        }
    }
    fetch() {
        Api.addFields(data).then((response) => {
            success();
        });
    }
    validForm() {
        const { rule_name, rules } = this.state;
        if (rule_name == null || rule_name.length <= 0) {
            error('编码规则名称 不能为空');
            return;
        }
        for (let i = 0, l = rules.length; i < l; i++) {
            if (rules[i].name == null || rules[i].name.length <= 0) {
                error('码段名称 不能为空');
                return;
                break;
            }
            for (let m = 0, n = rules[i].children.length; m < n; m++) {
                if (rules[i].children[m].item_val == null || rules[i].children[m].item_val.length <= 0) {
                    error('属性名称 不能为空');
                    return;
                    break;
                }
                if (rules[i].children[m].item_num == null || !/^\d+$/.test(rules[i].children[m].item_num)) {
                    error('代表数字 仅支持数字');
                    return;
                    break;
                }
            }
        }
        return true;
    }
    format(data) {
        let field_name = [];
        let item_name = [];
        let item_vals = [];
        data.map((rule) => {
            item_name[item_name.length] = rule.name;
            let tmp_field_name = [];
            let tmp_item_vals = [];
            rule.children.map((child) => {
                tmp_field_name[tmp_field_name.length] = child.item_num;
                tmp_item_vals[tmp_item_vals.length] = {
                    item_val: child.item_val,
                    item_num: child.item_num
                };
            });
            field_name[field_name.length] = tmp_field_name.join('.');
            item_vals[item_vals.length] = tmp_item_vals;
        });
        return {
            field_name,
            item_name,
            item_vals
        };
    }
    save() {
        if (!this.validForm()) {
            return;
        }
        const { rule_name, rules, create_by, create_time, id } = this.state;
        const data = {
            rule_name,
            rules,
            create_by,
            create_time,
            id
        };
        console.log('data', data);
        Api.sysCodeRuleAdd(data).then((response) => {
            success();
        });
    }
    fetchSysCodeRule() {
        const { ruleId } = this.state;
        if (ruleId == null) {
            return;
        }
        const data = {
            ruleId
        };
        Api.fetchSysCodeRule(data).then((response) => {
            if (!response) {
                error();
                return;
            }
            const json = JSON.parse(response);
            console.log('json', json);
            const { rule_name, rules, create_by, create_time, id } = json;
            this.setState({ rule_name, rules, create_by, create_time, id });
            success();
        });
    }
    componentWillMount() {
        let ruleId = Utils.parseUri('id');
        console.log(ruleId);
        ruleId.length ? this.setState({ ruleId }) : null;
    }
    componentDidMount() {
        this.fetchSysCodeRule();
    }
    render() {
        const { breads, rule_name, rules } = this.state;
        return (
            <div>
                {<Row>
                    <Col span={24}><Header /></Col>
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
                                <Col span={20}>
                                  <SgBreadcrumb breads={ breads } />
                                </Col>
                                <Col span={3}>
                                    <Button type="primary" onClick={::this.save} disabled={rules.length <= 2}>保存</Button>
                                </Col>
                            </Row>
                        </div>
                        <div style={{ marginBottom: 16 }}>
                          <Row type={'flex'} justify={'center'} align={'middle'}>
                            <Col span={20}>
                              <Row align={'middle'} style={{marginBottom:35}}>
                                <Col span={4} style={{lineHeight:'28px'}}>
                                    <span>编码规则名称</span>
                                </Col>
                                <Col span={20}>
                                  <Input placeholder="编码规则名称" value={rule_name} onChange={::this.updateMainKey}/>
                                </Col>
                              </Row>
                              <Row style={{marginBottom:10}}>
                                <Col span={4}>
                                  <span style={{fontWeight:700}}>码段名称</span>
                                </Col>
                                <Col span={20}>
                                  <Row>
                                    <Col span={6} offset={1}>
                                      <span style={{fontWeight:700}}>属性名称</span>
                                    </Col>
                                    <Col span={12} offset={1}>
                                      <span style={{fontWeight:700}}>代表数字</span>
                                    </Col>
                                    <Col offset={3}>
                                      <Button type="primary" shape="circle" icon="plus" onClick={::this.addGroup}/>
                                    </Col>
                                  </Row>
                                </Col>
                              </Row>
                              {
                                rules.map((rule, ruleIndex)=>{
                                  return (
                                    <Row type={'flex'} style={{marginBottom:15}} key={ruleIndex} align={'middle'}>
                                      <Col span={4} style={{lineHeight:'28px', textAlign:'right'}}>
                                        {
                                          rule.disabled ? <span>{rule.name}</span> : <Input placeholder="码段名称" value={rule.name} onChange={::this.updateGroupKey(ruleIndex)}/>
                                        }
                                      </Col>
                                      <Col span={20}>
                                        {
                                          rule.children.map((child, childIndex)=>{
                                            return (
                                              <Row style={{margin:'8px 0'}} type={'flex'} key={childIndex} align={'middle'}>
                                                <Col span={6} offset={1}>
                                                  <Input placeholder="属性名称" value={child.item_val} disabled={child.disabled} onChange={::this.updateKey(ruleIndex, childIndex)}/>
                                                </Col>
                                                <Col span={12} offset={1}>
                                                  <Input placeholder="代表数字" value={child.item_num} disabled={child.disabled} onChange={::this.updateValue(ruleIndex, childIndex)}/>
                                                </Col>
                                                {
                                                  child.disabled ? null : <Col span={3}>
                                                    <Button type="primary" shape="circle" icon="plus" style={{marginLeft:5}} size={'small'} onClick={::this.add(ruleIndex, childIndex)}/>
                                                    <Button type="primary" shape="circle" icon="minus" style={{marginLeft:5}} size={'small'} onClick={::this.remove(ruleIndex, childIndex)} />
                                                  </Col>
                                                }
                                              </Row>
                                            )
                                          })
                                        }
                                      </Col>
                                    </Row>
                                  )
                                })
                              }
                            </Col>
                          </Row>
                        </div>
                    </Col>
                </Row>
            </div>
        )
    }
}

app.value("PageView", PageView);