const errorMessage = document.getElementById('error-message');
        const expertTable = document.getElementById('expert-table');
        const submitButton = document.getElementById('submit-button');
        Image()
        // 示例数据（从后端获取的数据）
        let expertsData = [
            { id: '1234567890', name: '专家1', age: 40, gender: '男', address: '北京市', contact: '12345678901', department: '内科', title: '主任医师', specialty: '心血管', password: '********' },
            { id: '0987654321', name: '专家2', age: 35, gender: '女', address: '上海市', contact: '09876543210', department: '外科', title: '副主任医师', specialty: '骨科', password: '********' }
        ];

        // 函数：填充表格数据
        function populateTable(data) {
            expertTable.innerHTML = ''; // 清空表格
            data.forEach((expert, index) => {
                expertTable.innerHTML += `
                    <tr>
                        <td class="serial-number" rowspan="2">${index + 1}</td>
                        <td class="header-cell">身份证号</td>
                        <td class="header-cell">姓名</td>
                        <td class="header-cell">年龄</td>
                        <td class="header-cell">性别</td>
                        <td class="header-cell">住址</td>
                        <td class="header-cell">联系方式</td>
                    </tr>
                    <tr id="row-${index}-1">
                        <td><input type="text" value="${expert.idCard}" id="idCard-${index}"></td>
                        <td><input type="text" value="${expert.name}" id="name-${index}"></td>
                        <td><input type="number" value="${expert.age}" id="age-${index}"></td>
                        <td>
                            <select id="sex-${index}">
                            <option value="男" ${expert.sex === 1 ? 'selected' : ''}>男</option>
                            <option value="女" ${expert.sex === 2 ? 'selected' : ''}>女</option>
                            <option value="其他" ${expert.sex === 0 ? 'selected' : ''}>其他</option>
                            </select>
                        </td>
                        <td><input type="text" value="${expert.address}" id="address-${index}"></td>
                        <td><input type="text" value="${expert.phone}" id="phone-${index}"></td>
                    </tr>
                    <tr>
                        <td></td> <!-- 空单元格，确保对齐 -->
                        <td class="header-cell">科室</td>
                        <td class="header-cell">职称</td>
                        <td class="header-cell">专长</td>
                        <td class="header-cell">密码</td>
                        <td colspan="2" class="header-cell">操作</td>
                    </tr>
                    <tr id="row-${index}-2">
                        <td></td>
                        <td><select id="department-${index}">
                            <option value="1" ${expert.department === 1 ? 'selected' : ''}>内科</option>
                            <option value="2" ${expert.department === 2 ? 'selected' : ''}>外科</option>
                            <option value="3" ${expert.department === 3 ? 'selected' : ''}>妇产科</option>
                            <option value="4" ${expert.department === 4 ? 'selected' : ''}>儿科</option>
                            <option value="5" ${expert.department === 5 ? 'selected' : ''}>眼科</option>
                            <option value="6" ${expert.department === 6 ? 'selected' : ''}>耳鼻喉科</option>
                            <option value="7" ${expert.department === 7 ? 'selected' : ''}>皮肤科</option>
                            <option value="8" ${expert.department === 8 ? 'selected' : ''}>口腔科</option>
                            <option value="9" ${expert.department === 9 ? 'selected' : ''}>精神科</option>
                            <option value="10" ${expert.department === 10 ? 'selected' : ''}>放射科</option>
                            </select></td>
                        <td><input type="text" value="${expert.title}" id="title-${index}"></td>
                        <td><input type="text" value="${expert.specialty}" id="specialty-${index}"></td>
                        <td><input type="password" value="${expert.password}" id="password-${index}"></td>
                        <td colspan="2">
                            <button class="save-button" data-id="${expert.id}" index-id="${index}">保存</button>
                            <button class="delete-button" data-id="${expert.id}">删除</button>
                        </td>
                    </tr>
                    <tr><td colspan="7">&nbsp;</td></tr> <!-- 添加空行 -->
                `;
            });

            // 添加保存和删除按钮的事件监听器
            const saveButtons = document.querySelectorAll('.save-button');
            const deleteButtons = document.querySelectorAll('.delete-button');

            saveButtons.forEach(button => {
                button.addEventListener('click', saveExpert);
            });

            deleteButtons.forEach(button => {
                button.addEventListener('click', deleteExpert);
            });
        }

        // 初始加载表格数据
        populateTable(expertsData);

        // 函数：保存专家信息
        function saveExpert(event) {
            const index = event.target.getAttribute('index-id');
            const id = event.target.getAttribute('data-id');
            const idCard = document.getElementById(`idCard-${index}`).value;
            const name = document.getElementById(`name-${index}`).value;
            const age = document.getElementById(`age-${index}`).value;
            const sex = document.getElementById(`sex-${index}`).value;
            const address = document.getElementById(`address-${index}`).value;
            const phone = document.getElementById(`phone-${index}`).value;
            const department = document.getElementById(`department-${index}`).value;
            const title = document.getElementById(`title-${index}`).value;
            const specialty = document.getElementById(`specialty-${index}`).value;
            const password = document.getElementById(`password-${index}`).value;

            axios({
                url: `/admin/doctor`,
                method: 'PUT',
                data:{
                    id:parseInt(id),
                    name,
                    age:parseInt(age),
                    sex:sex === '男' ? 1 : sex === '女' ? 2 : 0,
                    address,
                    phone,
                    idCard,
                    department:parseInt(department),
                    title,
                    specialty,
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

        // 函数：删除专家信息
        function deleteExpert(event) {
            const id = event.target.getAttribute('data-id');
            axios({
                url: `/admin/doctor/${id}`,
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
            console.log('提交更改...', expertsData);
            showMessage('更改提交成功');
        });

        // 返回按钮点击处理
        function goback() {
            window.location.href = '../admin.html'; // 返回到管理员页面
        }

        function Image(){
            axios({
                url: `/admin/doctor`,
                method: 'GET'
            }).then(result => {
                console.log(result)
                populateTable(result.data.data)
            }).catch(error => {
                console.error(error);
                alert('操作错误，请联系管理员。');
            });
        }