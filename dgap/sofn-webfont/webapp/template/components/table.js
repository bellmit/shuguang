import React, { Component } from 'react'
import { Table, Button, Spin } from 'antd';

import {
    Api
} from '../api'

class Actions extends Component{
    render(){
        return (
            <span>
                <a href="#" style={{marginRight: 10}}>修改</a>
                <a href="#" style={{marginRight: 10}}>删除</a>
                <a href="#">状态</a>
            </span>
        )
    }
}

const columns = [{
    title: '名称',
    dataIndex: 'NAME',
}, {
    title: '描述',
    dataIndex: 'DESCRIPTION',
}, {
    title: '操作',
    dataIndex: 'address',
    render: () => (<Actions />)
}];

export default React.createClass({
    getInitialState() {
        return {
            selectedRowKeys: [], // Check here to configure the default column
            data: [],
            loading: false,
            pagination: {},
            data: [],
        };
    },
    onSelectChange(selectedRowKeys) {
        console.log('selectedRowKeys changed: ', selectedRowKeys);
        this.setState({ selectedRowKeys });
    },
    handleTableChange(pagination, filters, sorter) {
        const pager = this.state.pagination;
        pager.current = pagination.current;
        this.setState({
            pagination: pager,
        });
        this.fetch({
            results: pagination.pageSize,
            page: pagination.current,
            sortField: sorter.field,
            sortOrder: sorter.order,
            ...filters,
        });
    },
    fetch() {
        const params = {
            start: 0,
            length: 15
        }
        Api.getResourceList(params).then((res) => {
            console.log('res.data.list', res.data.list);
            this.setState({
                data: res.data.list,
                loading: false,
            });
        });
    },
    render() {
        const { loading, selectedRowKeys, data, pagination } = this.state;
        const rowSelection = {
            selectedRowKeys,
            onChange: this.onSelectChange,
        };
        const hasSelected = selectedRowKeys.length > 0; {
            /*
                    <div>      
                    <div style={{ marginBottom: 16 }}>
                        <Button type="primary" onClick={this.start}
                          disabled={!hasSelected} loading={loading}
                        >Reload</Button>
                        <span style={{ marginLeft: 8 }}>{hasSelected ? `Selected ${selectedRowKeys.length} items` : ''}</span>
                      </div>
                      <Table rowSelection={rowSelection} columns={columns} dataSource={data} />
                      <Spin spinning={loading} />
                    </div> 
                    */
        }
        return (
            <Table columns={ columns }
            rowKey={ record => record.ID }
            rowSelection={ rowSelection }
            dataSource={ data }
            pagination={ pagination }
            loading={ loading }
            onChange={ this.handleTableChange }
            />
        )
    },
    componentWillMount() {
        this.fetch();
    }
});
