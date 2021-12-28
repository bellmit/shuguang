import { message } from 'antd';
// import Http from '../utils/httpClient'
import Http from '../utils/httpClient.fetch'
import { resource } from '../mock/resource'
const TIMEOUT = 200;
const success = function(text) {
    message.success(text || '操作成功!', 1);
};
const error = function(text) {
    message.error(text || '操作失败！');
};
const progress = () => {
    const hide = message.loading('处理中...', 0);
    // Dismiss manually and asynchronously
    setTimeout(() => {
        hide();
        success()
    }, 1500);
};
export const Api = {
    getResourceMenu(data) {
        //for debug
        return new Promise(resolve => {
                setTimeout(() => resolve(resource.menu.data), TIMEOUT)
            })
            //Fetch Api
            // return Http.send({
            //     url: '/sofn-dgap-web/resourceDirectory/list',
            //     contentType: 'application/x-www-form-urlencoded',
            //     data
            // })
    },
    getResourceList(data) {
        //for debug
        // return new Promise(resolve => {
        //         setTimeout(() => resolve(resource.list), TIMEOUT)
        //     })
        //Fetch Api
        return Http.send({ url: '/sofn-dgap-web/resourceDirectory/list', contentType: 'application/x-www-form-urlencoded', data })
    },
    addFields(data) { //新增字段名
        //for debug
        // return new Promise(resolve => {
        //     setTimeout(() => resolve(resource.fields.data), TIMEOUT)
        // })
        return Http.send({ url: '/sofn-dgap-web/dataImport/dataImportField/add', data: JSON.stringify(data) }).then((response) => response.data, (err) => {
            error(err.message);
            return Promise.reject(err)
        });
    },
    updateFields(data) { //修改字段名
        return Http.send({ url: '/sofn-dgap-web/dataImport/dataImportField/update', data: JSON.stringify(data) }).then((response) => response.data, (err) => {
            error(err.message);
            return Promise.reject(err)
        });
    },
    deleteFields(data) { //删除字段名
        // return new Promise(resolve => {
        //     setTimeout(() => resolve(resource.fields.data), TIMEOUT)
        // }).then(
        //     (response) => success(),
        //     (err) => {
        //         error(err.message);
        //         return Promise.reject(err)
        //     }
        // );
        return Http.send({ url: '/sofn-dgap-web/dataImport/dataImportField/delete', data: JSON.stringify(data) }).then((response) => success(), (err) => {
            error(err.message);
            return Promise.reject(err)
        });
    },
    getTableInfo(data) {
        return Http.send({ url: '/sofn-dgap-web/dataImport/dataImportTable/getById', data: JSON.stringify(data) }).then((response) => response.data, (err) => {
            error(err.message);
            return Promise.reject(err)
        });
    },
    addTable(data) { //新增表名
        // return new Promise((resolve, reject) => {
        //         setTimeout(() => resolve(resource.success), TIMEOUT)
        //         // setTimeout(() => reject(resource.error), TIMEOUT)
        //     }).then(
        //     response => response,
        //     (err) => {
        //         error(err.message);
        //         return Promise.reject(err)
        //     }
        // );
        return Http.send({ url: '/sofn-dgap-web/dataImport/dataImportTable/add', data: JSON.stringify(data) }).then(response => response, err => {
            error(err.message);
            return Promise.reject(err)
        });
    },
    createTable(data) { //新建表
        // return new Promise((resolve, reject) => {
        //         setTimeout(() => resolve(resource.success), TIMEOUT)
        //         // setTimeout(() => reject(resource.error), TIMEOUT)
        //     }).then(
        //     response => response,
        //     (err) => {
        //         error(err.message);
        //         return Promise.reject(err)
        //     }
        // );
        return Http.send({ url: '/sofn-dgap-web/dataImport/createTable', data: JSON.stringify(data) }).then(response => success(), err => {
            error(err.message);
            return Promise.reject(err)
        });
    },
    deleteTable(data) { //删除表
        // return new Promise((resolve, reject) => {
        //         setTimeout(() => resolve(resource.success), TIMEOUT)
        //         // setTimeout(() => reject(resource.error), TIMEOUT)
        //     }).then(
        //     response => response,
        //     (err) => {
        //         error(err.message);
        //         return Promise.reject(err)
        //     }
        // );
        return Http.send({ url: '/sofn-dgap-web/dataImport/deleteTable', data: JSON.stringify(data) }).then(response => success(), err => {
            error(err.message);
            return Promise.reject(err)
        });
    },
    fieldType(data) { //审核
        // return new Promise(resolve => {
        //     setTimeout(() => resolve(resource.fieldType), TIMEOUT)
        // }).then(
        //     response => response.data,
        //     err => {
        //         error(err.message);
        //         return Promise.reject(err)
        //     }
        // );
        return Http.send({ url: '/sofn-dgap-web/dataImport/fieldType', data: JSON.stringify(data) }).then(response => response.data, err => {
            error(err.message);
            return Promise.reject(err)
        });
    },
    check(data) { //审核
        return Http.send({ url: '/sofn-dgap-web/dataImport/checkData', data: JSON.stringify(data) }).then(response => response, err => {
            error(err.message);
            return Promise.reject(err)
        });
    },
    getFieldList(data) { //字段列表
        // return new Promise(resolve => {
        //     setTimeout(() => resolve(resource.fieldList.data), TIMEOUT)
        // });
        return Http.send({ url: '/sofn-dgap-web/dataImport/fieldList', data: JSON.stringify(data) }).then((response) => response.data, (err) => error(err.message));
    },
    getPage(data) { //表仓库数据
        // return new Promise(resolve => {
        //     setTimeout(() => resolve(resource.page.data), TIMEOUT)
        // });
        return Http.send({ url: '/sofn-dgap-web/resourcePublish/getPage', contentType: 'application/x-www-form-urlencoded', data }).then((response) => response.data, (err) => error(err.message));
    },
    getTables(data) { //表仓库数据
        // return new Promise(resolve => {
        //     setTimeout(() => resolve(resource.tables.data), TIMEOUT)
        // });
        return Http.send({ url: '/sofn-dgap-web/resourcePublish/getTables', contentType: 'application/x-www-form-urlencoded', data }).then((response) => response.data, (err) => {
            error(err.message);
            return Promise.reject(err)
        });
    },
    getDataList(data) {
        // return new Promise(resolve => {
        //     setTimeout(() => resolve(resource.dataList.data), TIMEOUT)
        // });
        return Http.send({ url: '/sofn-dgap-web/dataImport/dataList', data: JSON.stringify(data) }).then((response) => response.data, (err) => {
            error(err.message);
            return Promise.reject(err)
        });
    },
    getFields(data) { //字段列表
        //for debug
        // return new Promise(resolve => {
        //     setTimeout(() => resolve(resource.fields.data), TIMEOUT)
        // })
        return Http.send({ url: '/sofn-dgap-web/resourcePublish/getFields', contentType: 'application/x-www-form-urlencoded', data }).then((response) => response.data, (err) => {
            error(err.message);
            return Promise.reject(err)
        });
    },
    getResourceDir(data = {}) { //字段列表
        //for debug
        // return new Promise(resolve => {
        //     setTimeout(() => resolve(resource.resourceDir.data), TIMEOUT)
        // })
        return Http.send({ url: '/sofn-dgap-web/resourceDirectory/all', contentType: 'application/x-www-form-urlencoded', data }).then((response) => response.data, (err) => {
            error(err.message);
            return Promise.reject(err)
        });
    },
    configResource(data) { //字段列表
        //for debug
        // return new Promise(resolve => {
        //     setTimeout(() => resolve(resource.resourceDir.data), TIMEOUT)
        // })
        return Http.send({ url: '/sofn-dgap-web/resourcePublish/configResource', contentType: 'application/x-www-form-urlencoded', data }).then((response) => response.data, (err) => {
            error(err.message);
            return Promise.reject(err)
        });
    },
    sysCodeRuleAdd(data) {
        return Http.send({
            url: '/sofn-sys-web/sysCodeRule/add',
            // contentType: 'application/x-www-form-urlencoded',
            data: JSON.stringify(data)
        }).then((response) => response.data, (err) => {
            error(err.message);
            return Promise.reject(err)
        });
    },
    fetchSysCodeRule(data) {
        return Http.send({
            url: '/sofn-sys-web/sysCodeRule/read/detail',
            contentType: 'application/x-www-form-urlencoded',
            data
            // data: JSON.stringify(data)
        }).then((response) => response.data, (err) => {
            error(err.message);
            return Promise.reject(err)
        });
    },
    getInfo(data) {
        return Http.send({ url: '/sofn-dgap-web/dataImport/getInfo', contentType: 'application/x-www-form-urlencoded', data }).then((response) => response.data, (err) => {
            error(err.message);
            return Promise.reject(err)
        });
    }
}
