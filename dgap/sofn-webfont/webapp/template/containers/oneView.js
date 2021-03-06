import '../styles/resource.less'
import React, {Component} from 'react'
import {
    Tabs,
    DatePicker,
    Button,
    Breadcrumb,
    Input,
    InputNumber,
    Row,
    Col,
    Select
} from 'antd';
import {SgBreadcrumb, SgModal, SgMenu, Table} from '../components'
const Option = Select.Option;
function handleChange(value) {
    console.log(`selected ${value}`);
}
const assembleCompt = (data) => {
    switch (data.type) {
        case 'select':
            return <span>
                {data.title
                    ? <span style={{
                            marginRight: 10
                        }}>{data.title}</span>
                    : null}
                <Select size="large" defaultValue={data.defaultValue} style={{
                    ...data.styles,
                    marginRight: 10
                }} onChange={data.callback}>
                    {data.options.map((option, index) => (
                        <Option value={option.value} key={index}>{option.text}</Option>
                    ))}
                </Select>
            </span>
            break;
        case 'input':
            return <span>
                {data.title
                    ? <span style={{
                            marginRight: 10
                        }}>{data.title}</span>
                    : null}
                <Input placeholder={data.title} style={{
                    ...data.styles,
                    marginRight: 10
                }}/>
            </span>
            break;
        case 'number':
            return <span>
                {data.title
                    ? <span style={{
                            marginRight: 10
                        }}>{data.title}</span>
                    : null}
                <InputNumber min={1} max={10} defaultValue={3} placeholder={data.title} style={{
                    ...data.styles,
                    marginRight: 10
                }}/>
            </span>
            break;
        case 'button':
            return <Button type="primary" onClick={data.callback} style={{
                ...data.styles,
                marginRight: 10
            }}>{data.title}</Button>
            break;
        default:
            break;
    }
}
export default class Home extends Component {
    constructor(props) {
        super(props);
        this.state = {}
    }
    render() {
        const {visibleTable, visibleField, BREADS, PANEL, ACTIONS} = this.props;
        return (
            <div style={{
                backgroundColor: '#404040'
            }}>
                {< Row > <Col span={24}><br/><br/><br/><br/><br/></Col> < /Row>}
                <Row justify={'center'}>
                    <Col span={4}>
                        <SgMenu/>
                    </Col>
                    <Col span={20} style={{
                        borderLeft: '1px solid #e9e9e9',
                        backgroundColor: '#FFF',
                        borderTopLeftRadius: 5,
                        borderBottomLeftRadius: 5
                    }}>
                        {/* filter */}
                        <div style={{
                            marginBottom: 16,
                            marginTop: 12
                        }}>
                            <Row type={'flex'} justify={'center'} align={'middle'}>
                                <Col span={23}>
                                    <SgBreadcrumb breads={BREADS}/>
                                </Col>
                            </Row>
                        </div>
                        <div style={{
                            marginBottom: 16
                        }}>
                            <Row type={'flex'} justify={'center'} align={'middle'}>
                                <Col span={18}>
                                    {PANEL.map((compt, index) => {
                                        return assembleCompt(compt);
                                    })}
                                </Col>
                                <Col span={5} style={{
                                    textAlign: 'right'
                                }}>
                                    <SgModal style={{
                                        marginRight: 10
                                    }} visible={visibleTable} show={this.showTable} title={'????????????'}>
                                        <div>
                                            <Row style={{
                                                marginBottom: 10
                                            }} type={'flex'} justify={'center'} align={'middle'}>
                                                <Col span={4}>
                                                    <span>????????????</span>
                                                </Col>
                                                <Col span={16}><Input placeholder="????????????"/></Col>
                                            </Row>
                                            <Row style={{
                                                marginBottom: 10
                                            }} type={'flex'} justify={'center'} align={'middle'}>
                                                <Col span={4}>
                                                    <span>????????????</span>
                                                </Col>
                                                <Col span={16}><Input placeholder="????????????"/></Col>
                                            </Row>
                                        </div>
                                    </SgModal>
                                    <SgModal style={{
                                        marginRight: 10
                                    }} visible={visibleField} show={this.showField} title={'????????????'}>
                                        <div>
                                            <Row style={{
                                                marginBottom: 10
                                            }} type={'flex'} justify={'center'} align={'middle'}>
                                                <Col span={4}>
                                                    <span>???????????????</span>
                                                </Col>
                                                <Col span={16}><Input placeholder="???????????????"/></Col>
                                            </Row>
                                            <Row style={{
                                                marginBottom: 10
                                            }} type={'flex'} justify={'center'} align={'middle'}>
                                                <Col span={4}>
                                                    <span>???????????????</span>
                                                </Col>
                                                <Col span={16}><Input placeholder="???????????????"/></Col>
                                            </Row>
                                            <Row style={{
                                                marginBottom: 10
                                            }} type={'flex'} justify={'center'} align={'middle'}>
                                                <Col span={4}>
                                                    <span>????????????</span>
                                                </Col>
                                                <Col span={16}>
                                                    <Select size="large" defaultValue="text" style={{
                                                        width: '100%'
                                                    }} onChange={handleChange}>
                                                        <Option value="text">??????</Option>
                                                        <Option value="int">??????</Option>
                                                        <Option value="float" disabled>?????????</Option>
                                                        <Option value="time">??????</Option>
                                                        <Option value="date">??????</Option>
                                                    </Select>
                                                </Col>
                                            </Row>
                                            <Row style={{
                                                marginBottom: 10
                                            }} type={'flex'} justify={'center'} align={'middle'}>
                                                <Col span={4}>
                                                    <span>????????????</span>
                                                </Col>
                                                <Col span={16}>
                                                    <InputNumber min={1} max={10} defaultValue={3} placeholder="????????????"/>
                                                </Col>
                                            </Row>
                                        </div>
                                    </SgModal>
                                    {ACTIONS.map((compt, index) => {
                                        return assembleCompt(compt);
                                    })}
                                </Col>
                            </Row>
                        </div>
                        <Row type={'flex'} justify={'center'}>
                            <Col span={23}>
                                <Table/>
                            </Col>
                        </Row>
                    </Col>
                </Row>
            </div>
        )
    }
}
