import React, { Component } from 'react'
import { Form, Icon, Input, Button } from 'antd';
const FormItem = Form.Item;

class SgForm extends Component{
	constructor(props){
		super(props)
	}
	handleSubmit(e) {
	  e.preventDefault();
	  this.props.form.validateFields((err, values) => {
	    if (!err) {
	      console.log('Received values of form: ', values);
	    }
	  });
	}
	render() {
	  const { getFieldDecorator } = this.props.form;
	  return (
	    <Form inline onSubmit={this.handleSubmit}>
	      <FormItem>
	        {getFieldDecorator('userName', {
	          rules: [{ required: true, message: 'Please input your username!' }],
	        })(
	          <Input addonBefore={<Icon type="user" />} placeholder="Username" />
	        )}
	      </FormItem>
	      <FormItem>
	        {getFieldDecorator('password', {
	          rules: [{ required: true, message: 'Please input your Password!' }],
	        })(
	          <Input addonBefore={<Icon type="lock" />} type="password" placeholder="Password" />
	        )}
	      </FormItem>
	      <FormItem>
	        <Button type="primary" htmlType="submit">Log in</Button>
	      </FormItem>
	    </Form>
	  );
	}
}

export default Form.create()(SgForm);