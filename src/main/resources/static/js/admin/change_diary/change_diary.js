function goBack() {
    window.location.href = '../admin.html'; // 替换为实际的管理员页面路径
}

Image();
// 示例操作记录数据
const records = [
    { time: '2024-06-28 10:00', id: 1, operatorName: '管理员A', objectName: '患者X', objectType: 'Patient' ,operationType: '注册审核通过' },
    { time: '2024-06-28 11:00', id: 2, operatorName: '管理员B', objectName: '专家Y', objectType: 'Doctor' ,operationType: '信息修改' },
    { time: '2024-06-28 12:00', id: 3, operatorName: '管理员A', objectName: '患者Z', objectType: 'Patient' ,operationType: '注册驳回' }
];

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

function populateTable(records) {
    const tableBody = document.getElementById('records-table-body');
    tableBody.innerHTML = ''; // 清空表格内容
    records.forEach(record => {
        const formattedTime = formatTime(record.time); 
        const row = `
            <tr>
                <td>${formattedTime}</td>
                <td>${record.id}</td>
                <td>${record.operatorName}</td>
                <td>${record.objectName}</td>
                <td>${record.objectType}</td>
                <td>${record.operationType}</td>
            </tr>
        `;
        tableBody.innerHTML += row;
    });
}

// 初始加载表格数据
populateTable(records);

function Image(){
    axios({
        url: '/common/operationLog', 
        method: 'GET',
        
    }).then(result => {
        console.log(result); 
        const data = result.data.data;
        populateTable(data);
    }).catch(error => {
        console.error(error);
    });
}