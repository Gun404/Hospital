const errorMessage = document.getElementById('error-message');
const adminTable = document.getElementById('admin-table');
const submitButton = document.getElementById('submit-button');
Image();
// 示例数据（从后端获取的数据）
let adminsData = [
    { id: '1234567890', name: '管理员1', address: '北京市', contact: '12345678901', password: '********' },
    { id: '0987654321', name: '管理员2', address: '上海市', contact: '09876543210', password: '********' }
];

// 函数：填充表格数据
function populateTable(data) {
    adminTable.innerHTML = ''; // 清空表格
    data.forEach((admin, index) => {
        adminTable.innerHTML += `
            <tr>
                <td class="serial-number">${index + 1}</td>
                <td class="header-cell">身份证号</td>
                <td class="header-cell">姓名</td>
                <td class="header-cell">住址</td>
                <td class="header-cell">联系方式</td>
                <td class="header-cell">密码</td>
                <td class="header-cell">操作</td>
            </tr>
            <tr id="row-${index}">
                <td></td> <!-- 空单元格，确保对齐 -->
                <td><input type="text" value="${admin.idCard}" id="idCard-${index}"></td>
                <td><input type="text" value="${admin.name}" id="name-${index}"></td>
                <td><input type="text" value="${admin.address}" id="address-${index}"></td>
                <td><input type="text" value="${admin.phone}" id="phone-${index}"></td>
                <td><input type="password" value="${admin.password}" id="password-${index}"></td>
                <td>
                    <button class="save-button" data-id="${admin.id}" index-id="${index}">保存</button>
                    <button class="delete-button" data-id="${admin.id}">删除</button>
                </td>
            </tr>
            <tr><td colspan="7">&nbsp;</td></tr> <!-- 添加空行 -->
        `;
    });

    // 添加保存和删除按钮的事件监听器
    const saveButtons = document.querySelectorAll('.save-button');
    const deleteButtons = document.querySelectorAll('.delete-button');

    saveButtons.forEach(button => {
        button.addEventListener('click', saveAdmin);
    });

    deleteButtons.forEach(button => {
        button.addEventListener('click', deleteAdmin);
    });
}

// 初始加载表格数据
populateTable(adminsData);

// 函数：保存管理员信息
function saveAdmin(event) {
    const index = event.target.getAttribute('index-id');
    const id = event.target.getAttribute('data-id');
    const idCard = document.getElementById(`idCard-${index}`).value;
    const name = document.getElementById(`name-${index}`).value;
    const address = document.getElementById(`address-${index}`).value;
    const phone = document.getElementById(`phone-${index}`).value;
    const password = document.getElementById(`password-${index}`).value;

    axios({
        url: `/admin/admin`,
        method: 'PUT',
        data:{
            id:parseInt(id),
            name,   
            address,
            phone,
            idCard,
            password,
            status:parseInt(localStorage.getItem('status')),
            createTime:localStorage.getItem('updateTime'),
            mustChangePassword:localStorage.getItem('mustChangePassword')
        }
    }).then(result => {
        console.log(result)
        alert("保存成功");
        Image()
    }).catch(error => {
        console.error(error);
        alert('操作错误，请联系管理员。');
    });
}

// 函数：删除管理员信息
function deleteAdmin(event) {
    const id = event.target.getAttribute('data-id');
    axios({
        url: `/admin/admin/${id}`,
        method: 'DELETE',
    }).then(result => {
        console.log(result)
        Image()
    }).catch(error => {
        console.error(error);
        alert('操作错误，请联系管理员。');
    });
}

// 函数：显示消息
function showMessage(message) {
    errorMessage.textContent = message;
    setTimeout(() => {
        errorMessage.textContent = '';
    }, 3000); // 3秒后清除消息
}

// 提交按钮点击处理（示例）
submitButton.addEventListener('click', () => {
    // 示例：提交更改到后端
    console.log('提交更改...', adminsData);
    showMessage('更改提交成功');
});

// 返回按钮点击处理
function goback() {
    window.location.href = '../admin.html'; // 返回到管理员页面
}

function Image(){
    axios({
        url: `/admin/admin`,
        method: 'GET'
    }).then(result => {
        console.log(result)
        populateTable(result.data.data)
    }).catch(error => {
        console.error(error);
        alert('操作错误，请联系管理员。');
    });
}