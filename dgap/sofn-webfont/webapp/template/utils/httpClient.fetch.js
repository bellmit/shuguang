import '@babel/polyfill';
import 'es6-promise/auto';
import 'isomorphic-fetch';

const Http = {
    searchParams(params) {
        return Object.keys(params).map((key) => encodeURIComponent(key) + '=' + encodeURIComponent(params[key])).join('&');
    },
    defaults: {
        type: 'POST',
        contentType: 'application/json',
        data: {}
    },
    send(options) {
        const settings = Object.assign({}, this.defaults, options);
        const fullUrl = settings.url;
        const token = window.localStorage.getItem("token") || '';
        return fetch(fullUrl, {
                method: settings.type,
                headers: new Headers({
                    "Content-Type": settings.contentType,
                    "X-Custom-Header": "ProcessThisImmediately",
                    token
                }),
                mode: 'cors',
                body: ~settings.contentType.indexOf('application/json') ? settings.data : this.searchParams(settings.data)
            })
            .then(
                response => response.json().then(json => ({ json, response }))
            )
            .then(({ json, response }) => {
                if (!response.ok) { //服务器返回 400，500 错误码时并不会 reject
                    return Promise.reject(json)
                }
                if (json.httpCode != 200) {
                    return Promise.reject({
                        message: typeof json.data == 'string' ? json.data : json.msg
                    });
                }
                return json;
            }, error => Promise.reject({ message: '请求超时或网络连接异常' }))
            .catch(error => Promise.reject(error))
    }
}
export default Http;
