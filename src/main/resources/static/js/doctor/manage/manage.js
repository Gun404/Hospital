function goBack() {
    window.location.href = '../../login/doctor_login.html';
}

function manage() {
    window.location.href = '../information/doctor_information_show.html'; 
}
getAttendance();
function publishAppointment() {
    const form = document.getElementById('appointmentForm');
    if (form.checkValidity()) {
        const dateValue = form.date.value;
        const timeValue = form.time.value;
        
        // 拼接日期和时间
        const attendanceTime = `${dateValue}T${timeValue}:00`;
        const doctorId = localStorage.getItem('id');
        axios({
            url: '/doctor/attendance', 
            method: 'POST',
            data:{
                doctorId:parseInt(doctorId),
                attendanceTime,
                registerNumber: parseInt(form.slots.value),
                status:0
            }
        }).then(result => {
            console.log(result); 
            getAttendance();
        }).catch(error => {
            console.error(error);
        });
        form.reset();
        alert('出诊信息已发布，等待审核');
    }
    else{
        form.reportValidity();
        alert('请完整填写所有信息');
    }
}

function formatTime(timeString) {
    const date = new Date(timeString);
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const day = String(date.getDate()).padStart(2, '0');
    const hours = String(date.getHours()).padStart(2, '0');
    const minutes = String(date.getMinutes()).padStart(2, '0');
    const seconds = String(date.getSeconds()).padStart(2, '0');
    return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
}

function deleteAppointment(id) {
    axios({
        url: '/doctor/attendance/'+id, 
        method: 'DELETE'
    }).then(result => {
        console.log(result); 
        getAttendance();
    }).catch(error => {
        console.error(error);
    });
}

document.addEventListener("DOMContentLoaded", function() {
    const today = new Date();
    const currentDate = today.toISOString().split('T')[0]; // 当前日期
    const currentTime = today.toLocaleTimeString('en-US', { hour12: false , hour: '2-digit', minute: '2-digit'}); // 当前时间，24小时制
    // 设置出诊日期最小值为当前日期
    const dateInput = document.getElementById('date');
    dateInput.setAttribute('min', currentDate);

    // 设置出诊时间最小值为当前时间
    const timeInput = document.getElementById('time');
    timeInput.setAttribute('min', currentTime);
});

function getAttendance(){
    const doctorId = localStorage.getItem('id');
    axios({
        url: '/doctor/attendance', 
        method: 'GET',
        params:{
            doctorId
        }
    }).then(result => {
        console.log(result); 
        const data = result.data.data;
        const appointmentsList = document.getElementById('appointmentsList');
        appointmentsList.innerHTML = '';
        data.forEach(appointment => {
            const appointmentDiv = document.createElement('div');
            appointmentDiv.className = 'appointment';
            const formattedTime = formatTime(appointment.attendanceTime);
            appointmentDiv.innerHTML = `
                <span>出诊时间: ${formattedTime}</span>
                <span>挂号数量: ${appointment.registerNumber}</span>
                <span>状态: ${appointment.status === 0 ? '待审核' : '已审核'}</span>
                <button onclick="deleteAppointment(${appointment.id})">删除</button>
            `;
            appointmentsList.appendChild(appointmentDiv);
        });
    }).catch(error => {
        console.error(error);
    });
}

function timeGetAttendance(){
    const doctorId = localStorage.getItem('id');
    const form = document.getElementById('appointedForm');
    if (form.checkValidity()) {
        const beginDate = form.beginDate.value;
        const beginTime = form.beginTime.value;
        const endDate = form.endDate.value;
        const endTime = form.endTime.value;
        const begin = `${beginDate} ${beginTime}:00`;
        const end = `${endDate} ${endTime}:00`;
        axios({
            url: '/doctor/attendance', 
            method: 'GET',
            params:{
                begin,
                end,
                doctorId
            }
        }).then(result => {
            console.log(result); 
            const data = result.data.data;
            const appointmentsList = document.getElementById('appointmentsList');
            appointmentsList.innerHTML = '';
            data.forEach(appointment => {
                const appointmentDiv = document.createElement('div');
                appointmentDiv.className = 'appointment';
                const formattedTime = formatTime(appointment.attendanceTime);
                appointmentDiv.innerHTML = `
                    <span>出诊时间: ${formattedTime}</span>
                    <span>挂号数量: ${appointment.registerNumber}</span>
                    <span>状态: ${appointment.status === 0 ? '待审核' : '已审核'}</span>
                    <button onclick="deleteAppointment(${appointment.id})">删除</button>
                `;
                appointmentsList.appendChild(appointmentDiv);
            });
        }).catch(error => {
            console.error(error);
        });
    }
    else{
        form.reportValidity();
        alert('请完整填写时间段');
    }
}
