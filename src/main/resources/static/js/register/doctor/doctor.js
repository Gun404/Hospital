const form = document.getElementById('registrationForm');

        // Add submit event listener
        form.addEventListener('submit', function(event) {
            event.preventDefault(); // Prevent the form from submitting
            const name = document.getElementById('name').value;
            //age转数字
            var age = parseInt(document.getElementById('age').value);
            //sex单独处理
            const sexid = document.getElementById('sex').value;
            var sex;
            if(sexid === "male"){
                sex = 1;
            }else if(sexid === "female"){
                sex = 2;
            }
            else{
                sex = 0; 
            }

            const address = document.getElementById('address').value;
            const phone =  document.getElementById('phone').value;
            const idCard = document.getElementById('idCard').value;
            const department = parseInt(document.getElementById('department').value);
            const title = document.getElementById('title').value;
            const specialty = document.getElementById('specialty').value;
            const password = document.getElementById('password').value;
            axios({
                url: '/doctor/register', 
                method: 'POST',
                data: {
                    name, 
                    age,
                    sex,
                    address,
                    phone,
                    idCard,
                    department:parseInt(department),
                    title,
                    specialty,
                    password
                }
            }).then(result => {
                console.log('注册成功:', result);
                if(result.data.msg === "success"){
                    alert("注册成功")
                    setTimeout(() => {
                        location.href = "../login/doctor_login.html";
                    }, 1000);
                }
            }).catch(error => {
                console.error('注册失败:', error);
                alert('注册失败，请重试！');
            });
        });