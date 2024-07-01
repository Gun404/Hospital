Image();
const errorMessage = document.getElementById('error-message');
        const expertTable = document.getElementById('expert-table');
        const submitButton = document.getElementById('submit-button');

        // 示例数据（从后端获取的数据）
        let expertsData = [
            { name: '张三', password: '********' },
            { name: '李四', password: '********' },
            { name: '王五', password: '********' }
        ];

        // 函数：填充表格数据
        function populateTable(data) {
            expertTable.innerHTML = `
                <tr>
                    <th>姓名</th>
                    <th>密码</th>
                    <th>操作</th>
                </tr>
            `;
            data.forEach((expert, index) => {
                expertTable.innerHTML += `
                    <tr id="row-${index}">
                        <td>${expert.name}</td>
                        <td>${expert.password}</td>
                        <td>
                            <button class="approve-button" data-id="${expert.id}">通过</button>
                            <button class="reject-button" data-id="${expert.id}">拒绝</button>
                        </td>
                    </tr>
                `;
            });

            // 添加通过和拒绝按钮的事件监听器
            const approveButtons = document.querySelectorAll('.approve-button');
            const rejectButtons = document.querySelectorAll('.reject-button');

            approveButtons.forEach(button => {
                button.addEventListener('click', approveExpert);
            });

            rejectButtons.forEach(button => {
                button.addEventListener('click', rejectExpert);
            });
        }

        // 初始加载表格数据
        populateTable(expertsData);

        // 函数：处理专家通过审核
        function approveExpert(event) {
            const patientId = event.target.getAttribute('data-id');
            // Send approval action to backend
            axios({
                url: `/admin/doctor/new/1/`+patientId,
                method: 'PATCH'
            }).then(result => {
                console.log(result)
                Image();
            }).catch(error => {
                console.error(error);
                alert('操作错误，请联系管理员。');
            });
        }

        // 函数：处理专家拒绝审核
        function rejectExpert(event) {
            const patientId = event.target.getAttribute('data-id');
            // Send approval action to backend
            axios({
                url: `/admin/doctor/new/0/`+patientId,
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
            populateTable(expertsData); // 使用更新后的数据重新填充表格
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
                url: `/admin/doctor/new`,
                method: 'GET'
            }).then(result => {
                console.log(result)
                populateTable(result.data.data)
            }).catch(error => {
                console.error(error);
                alert('操作错误，请联系管理员。');
            });
        }