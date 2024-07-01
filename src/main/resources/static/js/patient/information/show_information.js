function goBack() {
    window.location.href = '../reserve/patient_reserve.html';
}

function goToEdit() {
    window.location.href = './patient_information_update.html';
}

const id = localStorage.getItem('id');
// console.log(id)
axios({
    url: '/patient/user/'+id, 
    method: 'GET',
    
}).then(result => {
    console.log(result); 
    const data = result.data.data;
    document.getElementById('idCard').textContent = data.idCard;
    document.getElementById('name').textContent = data.name;
    document.getElementById('age').textContent = data.age;
    document.getElementById('sex').textContent = data.sex === 1 ? '男' : data.sex === 2 ? '女' : '其他';
    document.getElementById('address').textContent = data.address;
    document.getElementById('phone').textContent = data.phone;
    document.getElementById('medicalRecord').textContent = data.medicalRecord || '无';
    document.getElementById('password').textContent = '已设置';
}).catch(error => {
    console.error(error);
});