import $ from 'jquery';

export default {
    defaults: {
        type: 'POST',
        contentType: 'application/json',
        async: true,
        timeout: 10 * 1000,
        dataType: 'json'
    },
    send(options) {
        const settings = Object.assign({}, this.defaults, options);
        return $.ajax(settings).then(
            (response) => response.httpCode === 200 ? response : Promise.reject({ message: typeof response.data == 'string' ? response.data : response.msg }),
            (error) => Promise.reject({ message: '请求超时或网络连接异常' })
        );
    }
}
