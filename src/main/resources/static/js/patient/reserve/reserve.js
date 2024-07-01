//日期管理三天
document.addEventListener('DOMContentLoaded', function () {
    const today = new Date();
    for (let i = 1; i <= 3; i++) {
        const futureDate = new Date(today);
        futureDate.setDate(today.getDate() + i);
        const dateString = futureDate.toISOString().split('T')[0];
        document.getElementById('date').insertAdjacentHTML('beforeend', `<option value="${dateString}">${dateString}</option>`);
    }
});
//科室管理
const doctorsByDepartment = {
    "内科": ["张医生", "李医生"],
    "外科": ["王医生", "赵医生"],
    "儿科": ["刘医生", "陈医生"],
    "妇科": ["杨医生", "周医生"]
};
getAllDepartment();
InquireInfo();
function updateDoctors() {
    const selectedDepartmentId = document.getElementById('department').value;
    const doctorSelect = document.getElementById('doctor');
    console.log(selectedDepartmentId)
    const id = parseInt(selectedDepartmentId)
    // 清空之前的医生选项
    doctorSelect.innerHTML = '<option value="">请选择专家</option>';

    // 如果没有选择科室，则不发送请求
    if (!selectedDepartmentId) return;

    axios({
        url: `/common/department/doctor/${id}`, // 替换为实际的后端医生数据接口地址
        method: 'GET',
    }).then(result => {
        console.log(result);
        const doctors = result.data.data;
        populateDoctors(doctors);
    }).catch(error => {
        console.error(error);
    });
}

function logout() {
    // 处理退出逻辑
    window.location.href = '../../login/patient_login.html'
}
function goToEdit() {
    window.location.href = '../information/patient_information_show.html';
}
function register(item) {
    // 挂号成功后，修改按钮为支付按钮，并更新点击事件为支付函数
    const registerButton = document.querySelector(`.register-button-${item.id}`);
    registerButton.textContent = '支付';
    registerButton.classList.remove('register-button');
    registerButton.classList.add('pay-button');
    registerButton.addEventListener('click', () => pay(item));
}

function pay(item) {
    const patientId = localStorage.getItem('id')
    axios({
        url: `/patient/register/${patientId}/1`,
        method: 'POST',
        data:{
            id:parseInt(item.id),
            doctorId:parseInt(item.doctorId),
            attendanceTime:item.attendanceTime,
            registerNumber:item.registerNumber
        }
    }).then(result => {
        console.log(result);
        alert("支付成功")
        searchRegistration();
        InquireInfo();
    }).catch(error => {
        console.error(error);
    });
}
function getAllDepartment(){
    axios({
        url: '/common/department', 
        method: 'GET'
    }).then(result => {
        console.log(result); 
        const departments = result.data.data;
        populateDepartments(departments);
    }).catch(error => {
        console.error(error);
    });
}

function populateDepartments(departments) {
    const departmentSelect = document.getElementById('department');
    departmentSelect.textContent = '';
    departments.forEach(dep => {
        const option = document.createElement('option');
        option.value = dep.id;
        option.textContent = dep.name;
        departmentSelect.appendChild(option);
    });
}

function populateDoctors(doctors) {
    const doctorSelect = document.getElementById('doctor');

    doctors.forEach(dep => {
        const option = document.createElement('option');
        option.value = dep.id;
        option.textContent = dep.name;
        doctorSelect.appendChild(option);
    });
}

function searchRegistration() {
    const selectedDate = document.getElementById('date').value;
    const selectedDoctorId = document.getElementById('doctor').value;
    // 根据选择的日期和专家ID发送查询请求
    axios({
        url: '/patient/register/attendance',
        method: 'GET',
        params: {
            diagnosisDate: selectedDate,
            doctorId: parseInt(selectedDoctorId)
        }
    }).then(result => {
        console.log(result);
        const registrations = result.data.data;
        displayRegistrations(registrations);
    }).catch(error => {
        console.error(error);
    });
}

function displayRegistrations(registrations) {
    const resultsContainer = document.getElementById('registrationResults');
    resultsContainer.innerHTML = ''; // 清空之前的结果
    registrations.forEach(reg => {
        const registrationDiv = document.createElement('div');
        registrationDiv.className = 'registration-item';
        const { date, time } = parseDateTime(reg.attendanceTime);
        registrationDiv.innerHTML = `
            <p>日期：${date}</p>
            <p>时间：${time}</p>
            <p>专家名字：${reg.doctorName}</p>
            <p>剩余挂号数：${reg.registerNumber}</p>
            <button class="register-button-${reg.id}" onclick="register()">挂号</button>
        `;
        registrationDiv.querySelector(`.register-button-${reg.id}`).addEventListener('click', () => register(reg));
        resultsContainer.appendChild(registrationDiv);
    });
}
function parseDateTime(dateTimeString) {
    const dateTimeParts = dateTimeString.split('T');
    const date = dateTimeParts[0];
    const time = dateTimeParts[1].substring(0, 5); // 只取时间部分，不包括秒

    return { date, time };
}

function InquireInfo(){
    const patientId = localStorage.getItem('id'); // Assuming you store patient ID in localStorage

    axios({
        url: `/patient/register/${patientId}`, // Adjust URL according to your backend API
        method: 'GET',
    }).then(result => {
        console.log(result);
        const personalRegistrations = result.data.data;
        displayPersonalRegistrations(personalRegistrations);
    }).catch(error => {
        console.error('Error fetching personal registrations:', error);
    });
}


function displayPersonalRegistrations(personalRegistrations) {
    const personalRegistrationContainer = document.getElementById('personalRegistration');
    personalRegistrationContainer.innerHTML = ''; // Clear previous content

    personalRegistrations.forEach(reg => {
        const registrationDiv = document.createElement('div');
        registrationDiv.className = 'registration-item';
        const { date, time } = parseDateTime(reg.diagnosisTime); // Use diagnosisTime for personal registration

        registrationDiv.innerHTML = `
            <p>序号：${reg.id}</p>
            <p>就诊日期：${date}</p>
            <p>就诊时间：${time}</p>
            <p>专家名字：${reg.doctorName}</p><br>
        `;
        personalRegistrationContainer.appendChild(registrationDiv);
    });
}