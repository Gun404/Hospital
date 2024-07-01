function goBack() {
    window.location.href = '../manage/doctor_manage.html';
}

function goToEdit() {
    window.location.href = './doctor_information_update.html';
}

const id = localStorage.getItem('id');
console.log(id)
axios({
    url: '/doctor/user/'+id, 
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
    document.getElementById('department').textContent = data.department === 1 ? '内科' : data.department === 2 ? '外科' : data.department === 3 ? '妇产科':data.department === 4 ? '儿科' :data.department === 5 ? '眼科' :data.department === 6 ? '耳鼻喉科' :data.department === 7 ? '皮肤科' :data.department === 8 ? '口腔科' :data.department === 9 ? '精神科' :'放射科';
    document.getElementById('title').textContent = data.title
    document.getElementById('specialty').textContent = data.specialty;
    document.getElementById('password').textContent = '已设置';
}).catch(error => {
    console.error(error);
});