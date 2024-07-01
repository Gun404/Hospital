function goBack() {
    window.location.href = './doctor_information_show.html';
}

function saveExpertInfo() {
    const form = document.getElementById('userInfoForm');
    const formData = new FormData(form);
    const id = localStorage.getItem('id')
    axios({
        url: '/doctor/user', // 假设更新信息的接口
        method: 'PUT',
        data: {
            id:parseInt(id),
            idCard: formData.get('idCard'),
            name: formData.get('name'),
            age: parseInt(formData.get('age')),
            sex: formData.get('sex') === 'male' ? 1 : formData.get('sex') === 'female' ? 2 : 0,
            address: formData.get('address'),
            phone: formData.get('phone'),
            department:parseInt(formData.get('department')),
            title: formData.get('title'),
            specialty: formData.get('specialty'),
            password: formData.get('password'),
            status:parseInt(localStorage.getItem('status')),
            createTime:localStorage.getItem('updateTime')
        }
    }).then(response => {
        console.log(response)
        alert('修改成功')
    }).catch(error => {
        console.error(error);
        alert('操作错误，请联系管理员。');
    });
}

document.addEventListener('DOMContentLoaded', () => {
    const id = localStorage.getItem('id')
    axios({
        url: `/doctor/user/${id}`,
        method: 'GET'
    }).then(result => {
        const data = result.data.data;
        document.getElementById('idCard').value = data.idCard;
        document.getElementById('name').value = data.name;
        document.getElementById('age').value = data.age;
        document.getElementById('sex').value = data.sex === 1 ? 'male' : data.sex === 2 ? 'female' : 'other';
        document.getElementById('address').value = data.address;
        document.getElementById('phone').value = data.phone;
        document.getElementById('department').value = data.department === 1 ? '1' : data.department === 2 ? '2' : data.department === 3 ? '3':data.department === 4 ? '4' :data.department === 5 ? '5' :data.department === 6 ? '6' :data.department === 7 ? '7' :data.department === 8 ? '8' :data.department === 9 ? '9' :'10';
        document.getElementById('title').value = data.title;
        document.getElementById('specialty').value = data.specialty;
        document.getElementById('password').value = ''; // 密码不展示，用户自行修改
        localStorage.setItem('status',result.data.data.status)
        localStorage.setItem('updateTime',result.data.data.updateTime)
    }).catch(error => {
        console.error(error);
        alert('操作错误，请联系管理员。');
    });
});