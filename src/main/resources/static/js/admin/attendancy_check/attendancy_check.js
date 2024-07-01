const scheduleTable = document.getElementById('schedule-table');
const approveButtons = document.querySelectorAll('.approve-button');
const rejectButtons = document.querySelectorAll('.reject-button');
const submitButton = document.getElementById('submit-button');
const errorMessage = document.createElement('div');
errorMessage.classList.add('error-message');
document.body.appendChild(errorMessage);
showPendingAttendance();
// 函数：处理通过操作
function populateTable(data) {
    scheduleTable.innerHTML = `
        <tr>
            <td>日期</td>
            <td>时间</td>
            <td>专家名字</td>
            <td>挂号数量</td>
            <td>审核状态</td>
            </tr>
            `; // 清空表格内容

    data.forEach(item => {
        const row = document.createElement('tr');
        const { date, time } = parseDateTime(item.attendanceTime);
        row.innerHTML = `
            <td>${date}</td>
            <td>${time}</td>
            <td>${item.doctorName}</td>
            <td>${item.registerNumber}</td>
            <td>${item.status === 0 ? '待审核' : '已审核'}</td>
            <td>
                <button class="approve-button">通过</button>
                <button class="reject-button">拒绝</button>
            </td>
        `;
        
        row.querySelector('.approve-button').addEventListener('click', () => approveSchedule(item.id));
        row.querySelector('.reject-button').addEventListener('click', () => rejectSchedule(item.id));

        scheduleTable.appendChild(row);
    });
}
function approveSchedule(id) {
    axios({
        url: `/admin/doctor/attendance/1/${id}`, 
        method: 'PATCH',
        
    }).then(result => {
        console.log(result); 
        showPendingAttendance();
    }).catch(error => {
        console.error(error);
    });
}

// 函数：处理拒绝操作
function rejectSchedule(id) {
    axios({
        url: `/admin/doctor/attendance/0/${id}`, 
        method: 'PATCH',
        
    }).then(result => {
        console.log(result); 
        showPendingAttendance();
    }).catch(error => {
        console.error(error);
    });
}

function parseDateTime(dateTimeString) {
    const dateTimeParts = dateTimeString.split('T');
    const date = dateTimeParts[0];
    const time = dateTimeParts[1].substring(0, 5); // 只取时间部分，不包括秒

    return { date, time };
}

// 函数：显示消息
function showMessage(message) {
    errorMessage.textContent = message;
    setTimeout(() => {
        errorMessage.textContent = '';
    }, 3000); // 3秒后清除消息
}

// 提交审核按钮点击处理
submitButton.addEventListener('click', () => {
    // 示例：提交审核到后端
    console.log('提交审核...');
    showMessage('提交成功');
});

// 返回按钮点击处理
function goBack() {
    window.location.href = '../admin.html'; // 返回到管理员页面
}

function showPendingAttendance(){
    axios({
        url: '/admin/doctor/attendance', 
        method: 'GET',
        
    }).then(result => {
        console.log(result); 
        const data = result.data.data;
        populateTable(data);
    }).catch(error => {
        console.error(error);
    });
}