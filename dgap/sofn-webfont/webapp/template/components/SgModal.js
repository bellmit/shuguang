import React, { Component } from 'react';
import { Modal, Button } from 'antd';

export default class SgModal extends Component {
    constructor(props) {
        super(props);
    }
    onOk() {
        const { updateState } = this.props;
        // setTimeout(() => {
        //     updateState({key, confirmLoading: false, visible:false});
        // }, 1000);
        updateState({visible: false});
    }
    onCancel() {
            const { updateState } = this.props;
            updateState({visible: false});
        }
        // componentWillMount(){
        //     let { ...options } = this.props;
        //     this.state = Object.assign({}, this.state, options);
        // }
    render() {
        const { children, updateState, ...options } = this.props;
        return (
            <Modal onOk={::this.onOk}  onCancel={::this.onCancel} {...options}>
                { children }
            </Modal>
        )
    }
}
