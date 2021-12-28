import React, { Component } from 'react';
import { Modal, Button } from 'antd';

export default class SgModal extends Component {
    constructor(props) {
        super(props);
        this.ok = this.ok.bind(this);
        this.cancel = this.cancel.bind(this);
    }
    ok() {
        const { show } = this.props;
        // this.setState({
        //     ModalText: 'The modal dialog will be closed after two seconds',
        //     confirmLoading: true,
        // });
        setTimeout(() => {
            // this.setState({
            //     confirmLoading: false,
            // });
            show(false);
        }, 1000);
    }
    cancel() {
        const { show } = this.props;
        show(false);
    }
    render() {
        const { visible, title, text, children, footer, confirmLoading } = this.props;
        // const { confirmLoading } = this.state;
        return (
            <Modal title={ title }
            visible={ visible }
            onOk={this.ok}
            confirmLoading={ confirmLoading }
            footer={ footer }
            onCancel={this.cancel}>
            { children }
          </Modal>
        )
    }
}
