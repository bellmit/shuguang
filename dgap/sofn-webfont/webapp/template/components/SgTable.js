import React, { Component } from 'react'
import { Table, Button, Spin } from 'antd';

export default class SgTable extends Component {
    constructor(props) {
        super(props)
        // this.state = {
        //     loading: false,
        //     pagination: {}
        // }
    }
    handleTableChange(pagination, filters, sorter) {
        const pager = this.state.pagination;
        pager.current = pagination.current;
        this.setState({
            pagination: pager,
        });
        const { fetch } = this.props;
        fetch({
            results: pagination.pageSize,
            page: pagination.current,
            sortField: sorter.field,
            sortOrder: sorter.order,
            ...filters,
        });
    }
/*    render() {
        const { loading } = this.state;
        const { data, columns, rowSelection, pagination } = this.props;

        return <Table columns={ columns }
                rowKey={ record => record.id }
                rowSelection={ rowSelection }
                dataSource={ data }
                pagination={ pagination }
                loading={ loading }
                onChange={ this.handleTableChange }
                />
    }*/
    render() {
        const { ...options } = this.props;
        // const { onOk, onCancel } = this;
        // const defaults = {};
        // const settings = Object.assign({}, defaults, options);
        return (
            <Table rowKey={record => record.id}  {...options}/>
        )
    }
}
