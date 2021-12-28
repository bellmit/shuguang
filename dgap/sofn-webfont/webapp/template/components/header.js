import React, {Component} from 'react';
export default class Header extends Component {
  go(){
    window.location.href = '/#/login/loginCheck';
  }
  render(){
    return (
      <header className="gui-header clearfix">
          <div className="section-logo" onClick={this.go}>
              <img src="/assets/imgs/icon/logo.png" alt="国家农产品质量安全追溯管理信息平台（政务办公）" className="logo" />
              <h1>国家农产品质量安全追溯管理信息平台<em></em></h1>
          </div>
      </header>
    )
  }
}
