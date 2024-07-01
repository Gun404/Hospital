const errorMessage = document.getElementById('error-message');
const patientTable = document.getElementById('patient-table');
const submitButton = document.getElementById('submit-button');
Image();
// 示例数据（从后端获取的数据）
let patientsData = [
    { id: '1234567890', name: '患者1', age: 30, gender: '男', address: '北京市', contact: '12345678901', medicalHistory: '无', password: '********' },
    { id: '0987654321', name: '患者2', age: 25, gender: '女', address: '上海市', contact: '09876543210', medicalHistory: '无', password: '********' }
];

// 函数：填充表格数据
function populateTable(data) {
    patientTable.innerHTML = `
        <tr>
            <th>序号</th>
            <th>身份证号</th>
            <th>姓名</th>
            <th>年龄</th>
            <th>性别</th>
            <th>住址</th>
            <th>联系方式</th>
            <th>病历</th>
            <th>密码</th>
            <th>操作</th>
        </tr>
    `;
    data.forEach((patient, index) => {
        const rowNum = index + 1; // 计算行号，从1开始
        patientTable.innerHTML += `
            <tr id="row-${index}">
                <td>${rowNum}</td> <!-- 序号列 -->
                <td><input type="text" value="${patient.idCard}" id="idCard-${index}"></td>
                <td><input type="text" value="${patient.name}" id="name-${index}" style="width: 100px;"></td> <!-- 缩短姓名栏宽度 -->
                <td><input type="number" value="${patient.age}" id="age-${index}"></td>
                <td>
                    <select id="sex-${index}">
                        <option value="男" ${patient.sex === 1 ? 'selected' : ''}>男</option>
                        <option value="女" ${patient.sex === 2 ? 'selected' : ''}>女</option>
                        <option value="其他" ${patient.sex === 0 ? 'selected' : ''}>其他</option>
                    </select>
                </td>
                <td><input type="text" value="${patient.address}" id="address-${index}"></td>
                <td><input type="text" value="${patient.phone}" id="phone-${index}"></td>
                <td><input type="text" value="${patient.medicalRecord}" id="medicalRecord-${index}"></td>
                <td><input type="password" value="${patient.password}" id="password-${index}"></td>
                <td>
                    <button class="save-button" data-id="${patient.id}" index-id="${index}">保存</button>
                    <button class="delete-button" data-id="${patient.id}">删除</button>
                </td>
            </tr>
        `;
    });

    // 添加保存和删除按钮的事件监听器
    const saveButtons = document.querySelectorAll('.save-button');
    const deleteButtons = document.querySelectorAll('.delete-button');

    saveButtons.forEach(button => {
        button.addEventListener('click', savePatient);
    });

    deleteButtons.forEach(button => {
        button.addEventListener('click', deletePatient);
    });
}

// 初始加载表格数据
populateTable(patientsData);

// 函数：保存患者信息
function savePatient(event) {
    const index = event.target.getAttribute('index-id');
    const id = event.target.getAttribute('data-id');
    const idCard = document.getElementById(`idCard-${index}`).value;
    const name = document.getElementById(`name-${index}`).value;
    const age = document.getElementById(`age-${index}`).value;
    const sex = document.getElementById(`sex-${index}`).value;
    const address = document.getElementById(`address-${index}`).value;
    const phone = document.getElementById(`phone-${index}`).value;
    const medicalRecord = document.getElementById(`medicalRecord-${index}`).value;
    const password = document.getElementById(`password-${index}`).value;

    axios({
        url: `/admin/patient`,
        method: 'PUT',
        data:{
            id:parseInt(id),
            name,
            age:parseInt(age),
            sex:sex === '男' ? 1 : sex === '女' ? 2 : 0,
            address,
            phone,
            medicalRecord,
            idCard,
            password,
            status:parseInt(localStorage.getItem('status')),
            createTime:localStorage.getItem('updateTime')
        }
    }).then(result => {
        console.log(result)
        Image()
        alert("保存成功");
    }).catch(error => {
        console.error(error);
        alert('操作错误，请联系管理员。');
    });

}

// 函数：删除患者信息
function deletePatient(event) {
    const id = event.target.getAttribute('data-id');
    axios({
        url: `/admin/patient/${id}`,
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
    console.log('提交更改...', patientsData);
    showMessage('更改提交成功');
});

// 返回按钮点击处理
function goback() {
    window.location.href = '../admin.html'; // 返回到管理员页面
}

function Image(){
    axios({
        url: `/admin/patient`,
        method: 'GET'
    }).then(result => {
        console.log(result)
        populateTable(result.data.data)
    }).catch(error => {
        console.error(error);
        alert('操作错误，请联系管理员。');
    });
}