function goBack() {
    window.location.href = '../admin.html'; // 返回管理员信息管理页面
}

function goToEdit() {
    window.location.href = './admin_information_update.html';
}

const id = localStorage.getItem('id');
// console.log(id)
axios({
    url: '/admin/user/'+id, 
    method: 'GET',
    
}).then(result => {
    console.log(result); 
    const data = result.data.data;
    document.getElementById('idCard').textContent = data.idCard;
    document.getElementById('name').textContent = data.name;
    document.getElementById('address').textContent = data.address;
    document.getElementById('phone').textContent = data.phone;
    document.getElementById('password').textContent = '已设置';
}).catch(error => {
    console.error(error);
});