axios.interceptors.request.use(function (config) {
    // 在发送请求之前做些什么
    // 统一携带 token 令牌字符串在请求头上
    const adminToken = localStorage.getItem('jwt')
    // console.log(adminToken)
    adminToken && (config.headers.adminToken = `${adminToken}` )
    return config;
  }, function (error) {
    // 对请求错误做些什么
    return Promise.reject(error);
  });