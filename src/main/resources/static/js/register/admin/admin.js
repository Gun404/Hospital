const form = document.getElementById('registrationForm');

        // Add submit event listener
        form.addEventListener('submit', function(event) {
            event.preventDefault(); // Prevent the form from submitting
            const idCard = document.getElementById('idCard').value;
            const name = document.getElementById('name').value;
            const address = document.getElementById('address').value;
            const phone =  document.getElementById('phone').value;
            const password = document.getElementById('password').value;
            axios({
                url: '/admin/register', 
                method: 'POST',
                data: {
                    idCard,
                    name, 
                    address,
                    phone,
                    password
                }
            }).then(result => {
                console.log('注册成功:', result);
                if(result.data.msg === "success"){
                    alert("注册成功")
                    setTimeout(() => {
                        location.href = "../login/admin_login.html";
                    }, 1000);
                }
            }).catch(error => {
                console.error('注册失败:', error);
                alert('注册失败，请重试！');
            });
        });