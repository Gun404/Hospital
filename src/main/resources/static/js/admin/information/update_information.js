const mustChangePassword = localStorage.getItem('mustChangePassword');
if(mustChangePassword === 1){
    alert("首次登录，请修改密码")
}
function goBack() {
    const mustChangePassword = localStorage.getItem('mustChangePassword');
    if(mustChangePassword === '1'){
        alert("请修改密码")
    }
    else{
        window.location.href = './admin_information_show.html';
    }
    
}

function saveExpertInfo() {
    const form = document.getElementById('userInfoForm');
    const formData = new FormData(form);
    const id = localStorage.getItem('id')
    axios({
        url: '/admin/user', // 假设更新信息的接口
        method: 'PUT',
        data: {
            id:parseInt(id),
            idCard: formData.get('idCard'),
            name: formData.get('name'),
            address: formData.get('address'),
            phone: formData.get('phone'),
            password: formData.get('password'),
            status:parseInt(localStorage.getItem('status')),
            createTime:localStorage.getItem('updateTime')
        }
    }).then(response => {
        console.log(response)
        alert('修改成功')
        localStorage.setItem('mustChangePassword', 0)
    }).catch(error => {
        console.error(error);
        alert('操作错误，请联系管理员。');
    });
}

document.addEventListener('DOMContentLoaded', () => {
    const id = localStorage.getItem('id')
    axios({
        url: `/admin/user/${id}`,
        method: 'GET'
    }).then(result => {
        const data = result.data.data;
        document.getElementById('idCard').value = data.idCard;
        document.getElementById('name').value = data.name;
        document.getElementById('address').value = data.address;
        document.getElementById('phone').value = data.phone;
        document.getElementById('password').value = ''; // 密码不展示，用户自行修改
        localStorage.setItem('status',result.data.data.status)
        localStorage.setItem('updateTime',result.data.data.updateTime)
    }).catch(error => {
        console.error(error);
        alert('操作错误，请联系管理员。');
    });
});
