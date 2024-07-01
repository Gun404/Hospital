const errorMessage = document.getElementById('error-message');
const adminTable = document.getElementById('admin-table');
const submitButton = document.getElementById('submit-button');
Image();
// 示例数据（从后端获取的数据）
let adminsData = [
    { name: '管理员1', password: '1234**' },
    { name: '管理员2', password: '********' },
    { name: '管理员3', password: '********' }
];

// 函数：填充表格数据
function populateTable(data) {
    adminTable.innerHTML = `
        <tr>
            <th>姓名</th>
            <th>密码</th>
            <th>操作</th>
        </tr>
    `;
    data.forEach((admin, index) => {
        adminTable.innerHTML += `
            <tr id="row-${index}">
                <td>${admin.name}</td>
                <td>${admin.password}</td>
                <td>
                    <button class="approve-button" data-id="${admin.id}">通过</button>
                    <button class="reject-button" data-id="${admin.id}">拒绝</button>
                </td>
            </tr>
        `;
    });

    // 添加通过和拒绝按钮的事件监听器
    const approveButtons = document.querySelectorAll('.approve-button');
    const rejectButtons = document.querySelectorAll('.reject-button');

    approveButtons.forEach(button => {
        button.addEventListener('click', approveAdmin);
    });

    rejectButtons.forEach(button => {
        button.addEventListener('click', rejectAdmin);
    });
}

// 初始加载表格数据
populateTable(adminsData);

// 函数：处理管理员通过审核
function approveAdmin(event) {
    const patientId = event.target.getAttribute('data-id');
            // Send approval action to backend
    axios({
        url: `/admin/admin/new/1/`+patientId,
        method: 'PATCH'
    }).then(result => {
        console.log(result)
        Image();
    }).catch(error => {
        console.error(error);
        alert('操作错误，请联系管理员。');
    });
}

// 函数：处理管理员拒绝审核
function rejectAdmin(event) {
    const patientId = event.target.getAttribute('data-id');
    // Send approval action to backend
    axios({
        url: `/admin/admin/new/0/`+patientId,
        method: 'PATCH'
    }).then(result => {
        console.log(result)
        Image();
    }).catch(error => {
        console.error(error);
        alert('操作错误，请联系管理员。');
    });
}

// 函数：更新表格数据
function updateTable() {
    populateTable(adminsData); // 使用更新后的数据重新填充表格
}

// 函数：显示错误消息
function showMessage(message) {
    errorMessage.textContent = message;
    setTimeout(() => {
        errorMessage.textContent = '';
    }, 3000); // 3秒后清除消息
}
function goback()
{
    window.location.href = '../admin.html';
}

function Image(){
    axios({
        url: `/admin/admin/new`,
        method: 'GET'
    }).then(result => {
        console.log(result)
        populateTable(result.data.data)
    }).catch(error => {
        console.error(error);
        alert('操作错误，请联系管理员。');
    });
}