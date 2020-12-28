const fetchGet = function (url, params) {
    let list = [];
    for (let key in params) {
        let str = `${key}=${params[key]}`;
        list.push(str);
    }
    const data = list.join('&');
    let gUrl = `${url}?${data}`;
    return fetch(gUrl).then(res => {
        return res.json();
    }).catch(err => {
        console.log(err);
    });
};
const fetchPost = function (url, params) {
    let formData = new FormData();
    for (let key in params) {
        formData.append(key, params[key])
    }
    return fetch(url, {
        body: formData,
        method: 'POST'
    }).then(res => {
        console.log(res);
        return res.json();
    }).catch(err => {
        console.log(err);
    })
};
const fetchAll = function (url, params, method = 'GET') {
    if (method === 'GET' || method === 'get') {
        return fetchGet(url, params);
    }
    return fetchPost(url, params);
};
export default {
    fetchGet,
    fetchPost,
    fetchAll
}